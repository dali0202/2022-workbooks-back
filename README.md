## 서비스 소개
워크북스는 수학 문제집 생성 서비스입니다.

<br/>

## 업데이트 내역
* 0.0.1
  * API 서버 완성(모의고사 제외)
  * OAuth2 인증 완성
* 0.0.2
  * 프론트 페이지 완성
* 0.0.3
  * no offset 페이지네이션 구현
* 0.0.4
  * ~~TLS with session(Nginx ssl termination)~~
  * Load Balancing(Round Robin)
  * OpenID 도입 및 인증 flow 변경 (Spring Security, Spring OAuth2 client 의존 제거)
  * Client <-> Nginx Http2 protocol로 변경
  * Nginx upstream keep-alive 사용
* 0.0.5
  * TLS resumption by ticket
  
<br/>

## 서비스 미리보기
![overview](https://user-images.githubusercontent.com/93257581/176131401-1e3852e6-ca4d-47a2-8d3d-a91c0060b94b.gif)

<details>
<summary>소셜 로그인</summary>
  <img src="https://user-images.githubusercontent.com/93257581/176123534-db420f2a-6c2a-4967-aa60-ee20f5ff315d.gif"/>
</details>

<details>
<summary>문제집 검색 및 조회</summary>
  <img src="https://user-images.githubusercontent.com/93257581/176121689-6c219a63-ac0a-4131-a472-ae017391e4d2.gif"/>
</details>

<details>
<summary>범위 선택으로 만들기</summary>
  <img src="https://user-images.githubusercontent.com/93257581/176122612-aecc764f-d3c1-4f13-9b8d-6054c9daf3bc.gif"/>
</details>

<details>
<summary>문제 선택으로 만들기</summary>
  <img src="https://user-images.githubusercontent.com/93257581/176117025-e6df4d41-230d-4a7b-8290-f90db6908e81.gif"/>
</details>

<br/>

## 아키텍쳐
![Architecture](https://user-images.githubusercontent.com/93257581/201508920-9ae87e2c-4e08-4b8f-9883-662f2115dbe7.svg)

<br/>

## 기술스택
![Stacks](https://user-images.githubusercontent.com/93257581/201508925-877f7d50-5869-4b2f-964a-20ce06336d9f.svg)

<br/>

## 연관사이트
* 서비스 링크 : <https://workbooks.ga>
* 프론트엔드 리포지토리 : <https://github.com/dali0202/2022-workbooks-front>

<br/>
