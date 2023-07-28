# instagram clone coding

![png](/_image/diagram.png)

<br>

# 기능

- 계정: https://github.com/evelyn82ny/instagram-api/blob/main/_review/account.md
- 프로필: https://github.com/evelyn82ny/instagram-api/blob/main/_review/profile.md

<br>

# issues
## 사용자 간의 관계

> 자세한 내용은 https://velog.io/@evelyn82ny/instagram-follow 에서 확인 가능합니다.

![png](/_image/failed_attempt2_diagram.png)

초기에는 Follow 객체가 fromUser(관계를 요청하는 사용자)와 toUser(다른 사용자에 의해 관계가 변하는 사용자)를 @ManyToOne 으로 참조하도록 구현했다.
즉, Follow 와 User 는 연관 관계이다. 연관 관계란 서로가 연간이 있다는 뜻인데 여기서 의문이 생겼다.<br>

**Follow와 User의 관계**일까? **User와 User의 관계**일까?
고민 끝에 내린 결론은 **Follow 관계는 User와 User의 관계**이므로 연관 관계가 필요하지 않다고 판단했다.<br>

이를 위해 연관 관계 대신 두 사용자의 변하지 않는 id 값으로 **복합키**를 생성했다.
id 값은 변하지 않기 때문에 사용자가 데이터를 변경해도 **Follow 객체가 영향을 받지 않는다**.
또한, 중복되지도 않는다.
<br>

![png](/_image/clustered_index.png)

하지만 **Full Table Scan** 이라는 새로운 문제가 발생한다.<br>

복합키는 Clustered Index로 생성되며 from_user, to_user 알파벳 순으로 정렬된다. 
id가 1인 사용자가 id가 3인 사용자를 팔로우 했다면 위와 같이 중간에 삽입된다.
즉, 순서가 바뀌기 때문에 이를 로드하는 Page도 계속해서 수정해야하는 문제가 발생한다.

<br>

![png](/_image/full_table_scan.png)

id가 2인 사용자가 팔로우하는 관계를 가져온다면 from_user 칼럼이 기준이다. 
왼쪽을 보면 알 수 있듯이 from_user 칼럼으로 먼저 정렬되기 때문에 Range Scan이 가능하다.<br>

하지만 id가 2인 사용자를 팔로우하는 관계를 가져온다면 to_user 칼럼이 기준이다. 
오른쪽을 보면 알 수 있듯이 from_user 칼럼으로 정렬된 다음 to_user 칼럼으로 정렬되므로 결국은 full table scan을 한다.<br>

만약 서비스가 커진다면 full table scan은 성능 이슈가 될 것이다. 
그러므로 이를 해결할 수 있는 새로운 방법이 필요하지만 아직 찾지 못했다.

<br>

## 부가 기능

> 자세한 내용은 https://velog.io/@evelyn82ny/Spring-AOP-Aspect 에서 확인 가능합니다.

![png](/_image/TraceAspect_result.png)

JPA를 공부하면서 불필요한 Query 발생을 줄이는 것도 중요하다고 느꼈다.
Query 발생을 파악하고자 콘솔에 찍힌 로그를 확인했지만 사실상 너무 많아 구분하기 어려웠다.
고민 끝에 Query가 발생한 메소드명을 출력하는 부가 기능이 있다면 불필요한 Query 발생을 최소화하는 과정을 **효율적 처리할 수 있을 것**이라고 생각했다.<br>

처음에는 핵심 코드에 부가 기능을 적용했지만 같은 부가 기능 코드가 중복되고 핵심 기능을 한눈에 알아보기 힘들다는 문제가 발생했다.
그래서 여러 디자인 패턴을 공부했고 Proxy, Decorator Pattern 을 통해 Proxy 객체로 부가 기능을 적용할 수 있다는 것을 알게 되었다.<br>

Spring AOP를 통해 Proxy 객체로 부가 기능을 적용해 효율적인 작업을 할 수 있었고 **핵심 기능에 부가 기능 코드가 추가 되지 않으니 유지 보수가 좋은 코드를 유지**할 수 있었다.
