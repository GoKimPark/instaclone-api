# Account

계정을 생성 및 삭제하고 로그인을 처리한다.

## createAccount 계정 생성

### AccountController

- ```email```, ```name(주민등록상 이름)```, ```username(활동 아이디)```, ```password``` 4가지 형식인 **JoinDto**로 회원 가입 진행
- ```email```, ```username(활동 아이디)``` 가 **기존 회원들과 중복되지 않는다면** 해당 형식으로 가입 가능
- service에게 **JoinDto** 전달

### UserService

- controller에게 **JoinDto** 전달 받음
- JoinDto를 ```User entity``` 로 변경후 ```UserRepository``` 에게 전달
- ```UserRepository``` 에 저장되면 **UserDto**로 Controller에게 반환

<br>

## login 로그인

### AccountController

- ```loginId```, ```password``` 2가지 형식인 LoginDto 로 로그인 진행
- ```loginId```에는 ```username(활동 아이디)``` 만 입력 가능
  - ```loginId```에 ```username(활동 아이디)```와 ```email```을 입력받는 기능을 구현할 예정

### UserService

```username(활동 아이디)``` 가 존재한다면 비밀번호를 확인
- 비밀번호가 다를경우 ```UserException``` 발생

<br>

## deleteAccount 계정 삭제

### AccountController & UserService

- ```username(활동 아이디)``` 으로 계정 삭제 
- 해당 계정과 관련된 ```follower```, ```following``` 관계를 모두 삭제
- 해당 계정의 ```post(게시물)``` 을 모두 삭제
