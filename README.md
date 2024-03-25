# Project BuildingHelper

## 프로젝트 개요
Java 17과 스프링 프레임워크를 활용하여 아파트(공동주택)에 필요한 서비스를 프로그래밍으로 구현해보았습니다.

## 프로젝트 발표 자료

프로젝트 발표 자료는 다음 링크에서 확인이 가능합니다. [링크](https://www.canva.com/design/DAGAGhQkuDY/JjlPU_lV5y2EXH5YFJTzKQ/edit)

## 프로젝트 개발 환경
### Back-end
- Programming Language: Java 17
- Framework: Spring 5.3.19
- IDE: Eclipse
- Web Server: Apache Tomcat 9.0
- DB: MySQL 8.0
### Front-end
- Bootstrap 5
- jQuery
- Chart.js
### API
- Kakao Map API
- Daum Postcode API

## 프로젝트 실행 방법
1. Github에서 소스코드를 다운로드합니다.
2. 이클립스(Eclipse)에서 소스코드를 프로젝트에 추가합니다.
3. 아래 파일을 추가합니다.
	1. src\main\resources에 카카오 맵 API를 사용하기 위해 api_key.properties 파일을 생성합니다. api_key.properties 파일의 내용은 kakao_api_key = (kakao에서 발급받은 키 값)으로 이루어져있습니다.
	2. src\main\webapp\WEB-INF\spring\appServlet\ 디렉터리에 database-servlet.xml을 추가합니다.
4. 서버가 실행되는데 필요한 쿼리문을 실행합니다. SQL 파일들은 src/main/webapp/resources/sql에 있습니다.
5. pom.xml에서 (이클립스 기준) Alt + F5를 눌러서 필요한 의존 라이브러리를 설치합니다.

## 개발 모델: MVC
프로젝트 빌딩헬퍼는 MVC 모델로 개발되었습니다.
M: 서버가 요청받은 연산을 실제로 하는 부분이며 service와 repository 패키지에 있는 .java 파일입니다.
V: 클라이언트에게 페이지를 렌더링하여 보여주며 src/main/webapp/WEB-INF/views/ 디렉터리 안에 있습니다.
C: 서버에서 요청이 이루어지는 부분이며 controller 패키지에 있는 .java 파일입니다.