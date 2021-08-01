# backend

## 비밀번호 찾기
### 1. MemberService파일에서 passFind() 수정 필요.
** 이메일 보낼 때 image url 수정 필요. **   
image 파일 위치 : frontend/src/assets/send_email.jpg, main/resources/static/img/send_email.jpg   
### 2. application.properties 수정 필요.
spring.mail.username에 이메일, spring.mail.password에 비밀번호 입력.
