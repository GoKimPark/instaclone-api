# instagram clone coding

![png](/_image/diagram.png)

## FOLLOW 관계 처리 방식

- follower : 나의 인스타그램을 구독하는 사람
- following : 내가 구독하고 있는 target

FOLLOW 관계를 효율적으로 관리하는 방법을 계속해서 변경하는 중이며 아래 링크에 FOLLOW 관계에 대해 시도했던 과정을 작성했다.

> https://velog.io/@evelyn82ny/instagram-follow

## 기능

- [Account](#Account)
- [Profile](#Profile)
- [Relation](#Relation)

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


### login 로그인

#### AccountController 

- ```loginId```, ```password``` 2가지 형식인 LoginDto 를 입력받는다.
- ```loginId``` 에는 ```email``` 또는 ```username(활동 아이디)``` 를 입력할 수 있다.

#### UserService

- ```email``` 또는 ```username(활동 아이디)``` 가 존재한다면 비밀번호를 확인한다.
- 비밀번호가 다를경우 ```UserException``` 을 날린다.

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
- ```followerCount(팔로워 수)```, ```followingCount(팔로잉 수)``` 필드는 실제 관계가 아닌 개수만 가져온다.
- 사용자가 ```followerCount(팔로워 수)```, ```followingCount(팔로잉 수)``` 필드를 눌렀을 때 해당 정보를 다시 탐색하여 가져온다.
- 기본 정보인 ```ProfileUserInfoDto``` 와 게시물 정보인 ```PostProfileDto``` list 를 함께 처리하는 ``` ProfileDto``` 로 최종 반환한다.

### 프로필 정보 수정 

#### getProfileEditDto 

- 입력받은 ```username(활동 아이디)``` 에 대한 정보를 ```EditDto``` 로 반환한다.

#### updateProfile

- 수정하려고 하는 정보를 ```EditDto``` 로 넘겨받고 처리한다.
<br>

## Relation

- follower : 나의 인스타그램을 구독하는 사람
- following : 내가 구독하고 있는 target

### Failed Attempt 1

```User entity``` 에 ```follower list```, ```following list``` 필드를 갖도록 구현했다.

```java
public class User {
    
    private List<Integer> followerList;
    private List<Integer> followingList;
}
```

```user A``` 가 ```user B``` 를 unfollow 했다면 **2가지**를 처리해야한다.

- ```user A``` 의 ```following list``` 필드에서 ```user B``` 를 삭제
- ```user B``` 의 ```follower list``` 필드에서 ```user A``` 를 삭제

```user A``` 가 요청한 처리에 대해 ```user A``` 의 정보에만 접근하는 것이 아니고 ```user B``` 의 정보에서 접근해야 한다. 즉, 이는 위험하다고 판단해 해당 로직을 사용하지 않기도 함.

### Failed Attempt 2

- from_user : 나
- to_user : 내가 구독하려는 target;

```Follow entity``` 에서 ```from_user``` 와 ```to_user``` 의 ```ID field``` 만 갖는다.

```java
public class Follow {

    @Id
    @Column(name = "to_user")
    private Integer toUser;

    @Id
    @Column(name = "from_user")
    private Integer fromUser;
}
```

```user A``` 가 ```user B``` 를 unfollow 했다면 ```user B``` 데이터에 접근할 필요없이 ```user B(toUser) - user A(fromUser)``` 관계만 제거하면 된다.<br>

하지만 **follower, following list** 를 가져오는 로직에서 N + 1 문제가 발생한다. Follow entity 는 User 의 id 를 참조하는 것이 아니라 **User 의 id 값을 별도로 가지고 있다**. 즉, Follow entity 와 User entity 는 서로 연결되어 있지 않다.<br>
그래서 UserRepository 에서 User id 값과 일치하는 User 를 찾아야 하기 때문에 N + 1 문제가 발생한다.