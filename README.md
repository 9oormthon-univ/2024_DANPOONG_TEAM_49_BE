# 기숙상점 Backend Server

Spring Boot 기반의 학교 기숙사 상품 공동구매 서비스 백엔드입니다.
</br>
사용자 인증, 이메일 인증, 상품 등록/조회/참여, 학교 위치 기반 조회, 이미지 업로드( AWS S3 )와 같은 기능을 제공합니다.

## 1. 프로젝트 개요

경제적 부담을 느끼는 기숙사생들이 재학생 인증과 실명제를 통해 신뢰할 수 있는 공동구매 플랫폼
</br>
함께 필요한 물품을 저렴하게 구매할 수 있도록 제공합니다.

이 프로젝트는 다음과 같은 기능을 제공하는 백엔드 서버입니다.

- 사용자 로그인/회원 정보 저장 (Kakao OAuth 기반)
- 이메일 인증 코드 발송 및 인증 처리
- 상품 등록, 조회, 최근 상품 확인, 사용자 관련 상품 조회
- 상품 참여(예약) 기능
- 위치 기반 학교 조회
- AWS S3 파일 업로드 연동
- Redis 기반 인증/세션 관련 설정

## 2. 기술 스택

- Java 17
- Spring Boot 3.3.5
- Spring Web / Spring Data JPA
- MySQL
- Redis
- Kafka/카카오 로그인 연동을 위한 WebClient
- SMTP 메일 인증
- AWS S3
- Gradle
- Lombok

## 3. 프로젝트 구조

```text
src/main/java/com/goormthon3/team49
├── common/              # 공통 설정, 예외 처리, 인증/보안 관련 공통 코드
├── domain/
│   ├── awsS3/           # S3 업로드 관련 컨트롤러/서비스
│   ├── comment/         # 댓글 도메인
│   ├── email/           # 이메일 인증 도메인
│   ├── product/         # 상품 등록/조회/참여 도메인
│   ├── school/          # 학교 위치 검색 도메인
│   └── user/            # 사용자 로그인/회원 관리 도메인
└── Team49Application.java
```

## 4. 주요 기능

### 사용자 인증
- Kakao OAuth 인증 코드 교환
- Access Token 기반 사용자 정보 조회
- 사용자 정보 저장/갱신

### 이메일 인증
- 인증 메일 전송
- 인증 코드 검증
- 인증 성공 시 이메일 정보 갱신

### 상품 기능
- 전체 상품 조회
- 최근 등록 상품 조회
- 특정 사용자 관련 상품 조회
- 상품 상세 조회
- 상품 참여자 조회
- 상품 등록
- 상품 참여(예약)

### 학교 조회
- 위도/경도 기반 가까운 학교 조회

### 파일 업로드
- Amazon S3 기반 업로드 설정 및 연동 구조 포함

## 5. 도메인별 API 엔드포인트

### 1) 상품 도메인 (/products)

| Method | Endpoint | 설명 |
|---|---|---|
| GET | /products | 전체 상품 목록 조회 |
| GET | /products/recent | 최근 등록된 상품 목록 조회 |
| GET | /products/{userId}/recent | 특정 사용자가 관련된 최근 상품 조회 |
| GET | /products/{productId} | 상품 상세 정보 조회 |
| GET | /products/{productId}/participant | 특정 상품 참여자 목록 조회 |
| POST | /products/{userId}/leadCreate | 상품 등록 |
| POST | /products/participantCreate | 상품 참여(예약) |

### 2) 학교 도메인 (/schools)

| Method | Endpoint | 설명 |
|---|---|---|
| GET | /schools?latitude=...&longitude=... | 사용자의 위치 기준으로 가장 가까운 학교 조회 |

### 3) 인증 도메인 (/api/auth)

| Method | Endpoint | 설명 |
|---|---|---|
| POST | /api/auth/email | 이메일 인증 코드 발송 |
| POST | /api/auth/email/verify | 인증 코드 검증 및 이메일 갱신 |
| GET | /api/auth/callback | Kakao OAuth callback 처리 |
| POST | /api/auth/code/kakao | 인가 코드로 Kakao access token 요청 |
| GET | /api/auth/token | Access token 기반 사용자 정보 조회 |
| POST | /api/auth/token/save | 사용자 정보 저장 |
| GET | /api/auth/kakao-user-id | Access token 기반 Kakao 사용자 ID 조회 |

### 4) S3 업로드 도메인

| Method | Endpoint | 설명 |
|---|---|---|
| POST | /s3/upload | 이미지 업로드 요청 |
| GET | /s3/list | 업로드된 파일 목록 조회 |

## 6. 연락처

프로젝트에 대한 질문이나 피드백이 있으면:

- **Email**: lilloo04@naver.com
- **Issues**: GitHub Issues 탭에서 이슈 생성
- **Discussions**: GitHub Discussions 탭에서 토론

---

**마지막 업데이트**: 2026년 7월
