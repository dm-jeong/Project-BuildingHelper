-- 마지막 수정 날짜 2024-02-29-목-10:25
-- drop database if exists buildinghelper;
CREATE DATABASE 
IF NOT EXISTS
buildingHelper DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE buildingHelper;
-- 주소
CREATE TABLE address (
	address_id INT AUTO_INCREMENT PRIMARY KEY,
	address VARCHAR(200),
    house_name VARCHAR(20) /* 행복 빌라, 창원아파트 등 */
)default charset=utf8;

-- 101동 102호 같은 아파트 한 채의 정보를 담는 테이블
CREATE TABLE house(
	house_id INT AUTO_INCREMENT PRIMARY KEY,
    
    house_address_id INT, /* FK */
    
    house_dong varchar(14), /* 아파트의 101동 */
    house_hosu varchar(14), /* 아파트의 805호 */

    FOREIGN KEY(house_address_id) REFERENCES address(address_id)
)default charset=utf8;

-- 세대원 
CREATE TABLE resident(
	resident_id INT AUTO_INCREMENT PRIMARY KEY,
    r_house_id INT,
    r_login_id VARCHAR(20) UNIQUE, /* 로그인 아이디 */
    r_login_pw VARCHAR(20), /* 로그인 비밀번호 */
    r_nickname VARCHAR(12) UNIQUE, /* 닉네임, 유니크 */
    r_phone_number VARCHAR(13), /* 휴대폰 번호 : 010-1234-5678 */
    r_email VARCHAR(30) UNIQUE, /* 이메일 */
    r_category VARCHAR(10), /* 관리소, 세대원, 동대표 */
    
    FOREIGN KEY(r_house_id) REFERENCES house(house_id)
)default charset=utf8;

-- 가입 링크 테이블
CREATE TABLE register_link (
	register_link_id INT AUTO_INCREMENT PRIMARY KEY,
    
    register_address_id INT, /*FK, 실제 존재하는 주소인지 address 테이블에서 대조합니다.*/
    
    register_url_link varchar(150), /* 가입 링크 */
    expired_date datetime default '9999-12-31 23:59:59',
    register_expired_check boolean default false, /* false는 아직 만료되지 않았습니다. true는 만료되어 사용하지 않는 링크입니다. */
    
	register_house_name VARCHAR(16), /* 행복 빌라, 창원아파트 등 */
    register_house_dong VARCHAR(14), /* 아파트의 101동 */
    register_house_hosu VARCHAR(14), /* 아파트의 805호 */
    
    register_category VARCHAR(10) default '세대원', /* 관리소, 세대원, 동대표 */
    
    FOREIGN KEY(register_address_id) REFERENCES address(address_id)
)default charset=utf8;
-- ========== 테이블 확인 코드 시작 ==========
SELECT * FROM address;
SELECT * FROM house;
SELECT * FROM resident;
SELECT * FROM register_link;
-- ========== 테이블 확인 코드 종료 ==========

-- ========== 뷰 생성 코드 시작 ==========
-- ========== 회원정보 수정시 사용하는 view 시작 ==========
CREATE VIEW v_select_resident_information AS
SELECT r.r_login_id, r.r_login_pw, r.r_category, r.r_nickname, r.r_phone_number, r.r_email, r.r_house_id, a.house_name, h.house_dong, h.house_hosu
FROM resident r
INNER JOIN house h ON r.r_house_id = h.house_id
INNER JOIN address a ON h.house_address_id = a.address_id; 
-- ========== 회원정보 수정시 사용하는 view 종료 ==========

SELECT r_login_id, r_category, house_dong, house_hosu FROM v_select_resident_information WHERE r_house_id = 2 ORDER BY house_dong, house_hosu;
SELECT * FROM RESIDENT;
SELECT * FROM v_select_resident_information;


-- ========== 테스트 데이터 시작 ==========
-- 테스트 데이터 address 테이블

INSERT INTO address values(null, "주소 없음", "아파트 없음"); 
INSERT INTO address values(null, "경남 창원시 마산회원구 양덕북12길 113 (경민 인터빌)", "경민 인터빌");
INSERT INTO address values(null, "경남 창원시 테스트1길 1004", "테스트 아파트");

-- 테스트 데이터 house 테이블
INSERT INTO house values(null, 1, "동-없음", "호수-없음"); -- house_id = 1
INSERT INTO house values(null, 2, "1", "102"); -- house_id = 2 -- 1동 102호
INSERT INTO house values(null, 2, "1", "203"); -- house_id = 3 -- 1동 202호
INSERT INTO house values(null, 2, "1", "303"); -- house_id = 4 -- 1동 303호
INSERT INTO house values(null, 3, "101", "101"); -- house_id = 5 -- 101동 101호 
INSERT INTO house values(null, 3, "101", "102"); -- house_id = 6 -- 101동 102호
INSERT INTO house values(null, 2, "관리소", "관리소"); -- house_id = 7 --관리소동 관리소호
SELECT * FROM house;

-- 테스트 데이터 resident 테이블 생성 시작 ==========
INSERT INTO resident values(null, 1, "admin", "1234", "테스트", "010-1234-5678", "test@gmail.com", "세대원");
INSERT INTO resident values(null, 2, "test2", "1234", "테스트2", "010-1111-2222", "test2@gmail.com", "동대표");
INSERT INTO resident values(null, 2, "test3", "1234", "테스트3", "010-2222-3333", "test3@gmail.com", "관리소");
INSERT INTO resident values(null, 3, "test4", "1234", "테스트4", "010-1111-2222", "test4@gmail.com", "동대표");
INSERT INTO resident values(null, 3, "test5", "1234", "테스트5", "010-1111-2222", "test5@gmail.com", "관리소");
INSERT INTO resident values(null, 3, "test6", "1234", "테스트6", "010-1111-2222", "test6@gmail.com", "세대원");
INSERT INTO resident values(null, 2, "test7", "1234", "테스트7", "010-1111-2222", "test7@gmail.com", "세대원");
INSERT INTO resident values(null, 2, "test8", "1234", "테스트8", "010-1111-2222", "test8@gmail.com", "세대원");
-- 테스트 데이터 resident 테이블 생성 종료 ==========
select * from address;
select * from house;
select * from register_link;
SELECT * FROM resident;