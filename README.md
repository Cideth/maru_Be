# maru
K-pop과 K-drama의 영상에서 단어를 추출하여, 개인의 관심사와 수준에 맞춰 재미있게 한국어를 배울 수 있는 AI 기반 어학 애플리케이션입니다.





### 개발시 설정 순서
1. git clone
2. githook setting을 위해 아래 명령어 실행

```shell
  sh init_config
```

3. .env 설정

# Back-End 현 상황
1. JPA를 이용한 API 서버 구현
2. Swagger-ui를 이용하여 API 확인 가능
3. 


# Back-End Migration
- [ ] 토큰 검증 별도로 받기 (security 적용)
- [ ] Swagger 재정의
- [ ] DB 최적화
  - [ ] 데이터 삽입
  - [ ] Slow Query 분석
  - [ ] 인덱스 설정
  - [ ] Replica Set 구성
  - [ ] multi-Az 구성
  - [ ] Transaction -> locking
- Spring Data Type
  - [ ] Controller 역할 분리
  - [ ] 로깅
  - [ ] 예외 처리
- Redis 적용
  - [ ] 랭킹 시스템
- [ ] API GateWay
- [ ] OAuth Server
