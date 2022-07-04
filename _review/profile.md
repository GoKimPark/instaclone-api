# Profile

사용자의 프로필 화면을 출력하고, 프로필 내용을 수정한다.

## getProfile 프로필 화면 출력하기

### ProfileController

- ```username(활동 아이디)``` 을 입력받으면 프로필 화면에 출력할 해당 아이디에 대한 정보를 반환
- ```ProfileUserInfoDto``` 에 ```posCount(게시물 수)```, ```followerCount(팔로워 수)```, ```followingCount(팔로잉 수)``` 정보를 저장
- ```followerCount(팔로워 수)```, ```followingCount(팔로잉 수)``` 필드는 실제 관계가 아닌 개수만 가져옴
- 사용자가 ```followerCount(팔로워 수)```, ```followingCount(팔로잉 수)``` 필드를 눌렀을 때 실제 정보를 다시 탐색하여 가져옴
- 기본 정보인 ```ProfileUserInfoDto``` 와 게시물 정보인 ```PostProfileDto``` list 를 함께 처리하는 ``` ProfileDto``` 로 최종 반환
  - 현재 PostProfileDto 에 관한 게시물 기능 구현하지 않음

<br>

## 프로필 정보 수정

### getProfileEditDto

- 입력받은 ```username(활동 아이디)``` 에 대한 정보를 ```EditDto``` 로 반환

### updateProfile

- 수정하려고 하는 정보를 ```EditDto``` 로 넘겨받고 처리