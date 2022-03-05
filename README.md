# instagram clone coding

## Account 기능

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

## Profile 기능

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