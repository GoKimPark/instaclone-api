# instagram clone coding

![png](/_image/diagram.png)

## 기능

- [Account](#Account)
- [Profile](#Profile)
- [Follow Relationship](#Relation)

## Account

계정을 생성 및 삭제하고 로그인을 처리한다.

### createAccount 계정 생성

#### AccountController

- ```email```, ```name(주민등록상 이름)```, ```username(활동 아이디)```, ```password``` 4가지 형식인 JoinDto 를 입력받는다.
- ```email```, ```username(활동 아이디)``` 가 기존 회원들과 중복되지 않는다면 해당 형식으로 가입이 가능하다.
- 가입 가능하다면 UserDto 로 변경 후 service 에게 넘긴다.

#### UserService

- controller 에게 JoinDto 를 넘겨받는다.
- JoinDto 를 ```User entity``` 로 변경후 ```UserRepository``` 에게 넘겨준다.

<br>

### login 로그인

#### AccountController 

- ```loginId```, ```password``` 2가지 형식인 LoginDto 를 입력받는다.
- ```loginId``` 에는 ```email``` 또는 ```username(활동 아이디)``` 를 입력할 수 있다.
  - 현재 ```username(활동 아이디)``` 만 입력 가능하다.

#### UserService

- ```email``` 또는 ```username(활동 아이디)``` 가 존재한다면 비밀번호를 확인한다.
- 비밀번호가 다를경우 ```UserException``` 을 날린다.

<br>

### deleteAccount 계정 삭제

#### AccountController & UserService

- ```username(활동 아이디)``` 으로 계정을 삭제할 수 있다.
- 해당 계정과 관련된 ```follower```, ```following``` 관계를 모두 삭제한다.
- 해당 계정의 ```post(게시물)``` 을 모두 삭제한다.

<br>

## Profile

사용자의 프로필 화면을 출력하고, 프로필 내용을 수정한다.

### getProfile 프로필 화면 출력하기

#### ProfileController

- ```username(활동 아이디)``` 을 입력받으면 프로필 화면에 출력할 해당 아이디에 대한 정보를 반환한다.
- ```ProfileUserInfoDto``` 에 ```posCount(게시물 수)```, ```followerCount(팔로워 수)```, ```followingCount(팔로잉 수)``` 정보를 저장한다.
- 프로필 화면 출력시 ```post(게시물)``` 도 함께 출력되므로 ```PostProfileDto``` 에 게시물 url 을 저장한다.
  - 현재 게시물 기능을 구현하지 않음
- ```followerCount(팔로워 수)```, ```followingCount(팔로잉 수)``` 필드는 실제 관계가 아닌 개수만 가져온다.
- 사용자가 ```followerCount(팔로워 수)```, ```followingCount(팔로잉 수)``` 필드를 눌렀을 때 해당 정보를 다시 탐색하여 가져온다.
- 기본 정보인 ```ProfileUserInfoDto``` 와 게시물 정보인 ```PostProfileDto``` list 를 함께 처리하는 ``` ProfileDto``` 로 최종 반환한다.

<br>

### 프로필 정보 수정 

#### getProfileEditDto 

- 입력받은 ```username(활동 아이디)``` 에 대한 정보를 ```EditDto``` 로 반환한다.

#### updateProfile

- 수정하려고 하는 정보를 ```EditDto``` 로 넘겨받고 처리한다.

<br>

## Relation

- follower : 나의 인스타그램을 follow 하는 사용자
- following : 내가 follow 하고 있는 사용자

FOLLOW 관계를 효율적으로 관리하는 방법을 계속해서 변경하는 중이며 아래 링크에 FOLLOW 관계에 대해 시도했던 과정을 작성했다.

> https://velog.io/@evelyn82ny/instagram-follow

아래 내용은 위 게시물(링크)에 작성한 내용을 간략히 간추린 내용입니다.

### Failed Attempt 1: List

```User entity``` 에 ```follower list```, ```following list``` 필드를 갖도록 구현했다.

```java
public class User {
    
    private List<Long> followerList;
    private List<Long> followingList;
}
```

```user A``` 가 ```user B``` 를 unfollow 했다면 **2가지**를 처리해야한다.

- ```user A``` 의 ```following list``` 필드에서 ```user B``` 를 삭제
- ```user B``` 의 ```follower list``` 필드에서 ```user A``` 를 삭제

```user A``` 가 요청한 처리에 대해 ```user A``` 의 정보에만 접근하는 것이 아니고 ```user B``` 의 정보에서 접근해야 한다. 즉, 이는 위험하다고 판단해 해당 로직을 사용하지 않기도 함.

<br>

### Failed Attempt 2: Follow class

- from_user : 나 자신
- to_user : 내가 follow 하는 사용자

```java
@Entity
public class Follow {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne  // default EAGER
  @JoinColumn(name = "to_user")
  private User toUser;

  @ManyToOne
  @JoinColumn(name = "from_user")
  private User fromUser;

  public Follow(User toUser, User fromUser){
    this.toUser = toUser;
    this.fromUser = fromUser;
  }
}
```

Failed Attempt1에서 발생한 Follow 관계에서 ```toUser``` 객체의 직접 접근을 막기 위해 ```toUser```와 ```fromUser``` field를 갖는 Follow class 생성했다.<br>

```Followers(팔로워 목록)```, ```Following(팔로잉 목록)```을 가져오는 과정에서 **JPA N+1 query 문제가 발생**한다.

<br>

### Failed Attempt 3: Composite key로 변경

Failed Attempt 2의 Follow class에서 Id field는 사실상 필요없다고 판단해 ```toUser```와 ```fromUser```를 복합키로 변경했다.

```java
public class Follow {

    @Id
    @Column(name = "to_user")
    private Long toUser;

    @Id
    @Column(name = "from_user")
    private Long fromUser;
}
```

```from_user``` 와 ```to_user``` field는 User 객체의```ID field``` 값만 갖는다.<br>

```user A``` 가 ```user B``` 를 unfollow 했다면 ```user B``` 데이터에 접근할 필요없이 ```user B(toUser) - user A(fromUser)``` 관계만 제거하면 된다.<br>

하지만 여전히 **follower, following list** 를 가져오는 로직에서 JPA N+1 query 문제가 발생한다.

- Follow entity 는 User 의 id 를 참조하는 것이 아니라 **User 의 id 값**만 가지고 있다. 
- 즉, Follow Table와 User Table는 서로 연관이 없다.
- 그래서 UserRepository 에서 User id 값과 일치하는 User 를 찾아야 하기 때문에 N + 1 문제가 발생한다.

<br>

## Attempt 4: @Query 사용

```java
@Query(value = "select new com...파일 경로...FollowSimpleListDto(u.username, u.name)"
        + "from Follow f JOIN User u"
        + "ON f.fromUser = u.id where f.toUser = :userId")
List<FollowSimpleListDto> findAllByToUser(@Param("userId") Long userId);
```

```@Query```를 사용해 JPA N+1 query 문제를 해결했다.

- Follow 와 User table을 JOIN 해 일치하는 Instance를 가지고 온다.
- 해당 Instance의 정보 중 필요한 정보만 Dto에 mapping 한다.

```@Query```를 사용해 JPA N+1 query 문제를 해결했지만 출력되는 정보가 변경되면 ```@Query```를 수정해야된다.
즉, 활용성이 떨어진다. 하지만 여태까지 좋은 방법을 찾지 못했다.