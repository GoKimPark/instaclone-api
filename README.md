# instagram clone coding

## Account 

계정을 생성 및 삭제하고 로그인을 처리한다.

### createAccount 계정 생성

- ```email```, ```name(주민등록상 이름)```, ```username(활동 아이디)```, ```password``` 4가지 형식인 JoinDto 를 입력받는다.
- ```email```, ```username(활동 아이디)``` 가 기존 회원들과 중복되지 않는다면 해당 형식으로 가입이 가능하다.
- 가입 가능하다면 UserDto 로 변경 후 service 에게 넘긴다.

### login 로그인

- ```loginId```, ```password``` 2가지 형식인 LoginDto 를 입력받는다.
- ```loginId``` 에는 ```email``` 또는 ```username(활동 아이디)``` 를 입력할 수 있다.
- ```email``` 또는 ```username(활동 아이디)``` 가 존재한다면 비밀번호를 확인한다.
- 비밀번호가 다를경우 UserException 을 날린다.

### deleteAccount 계정 삭제

- ```username(활동 아이디)``` 으로 계정을 삭제할 수 있다.
