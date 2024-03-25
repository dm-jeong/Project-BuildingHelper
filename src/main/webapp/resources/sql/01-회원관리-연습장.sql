SELECT a.address_id, h.house_name, h.house_dong, h.house_hosu, h.house_id, h.house_address_id
FROM house h
JOIN address a ON h.house_address_id = a.address_id
WHERE h.house_id
ORDER BY h.house_address_id;



SELECT * from house;
SELECT * FROM address where address_id = 2;
SELECt house_address_id FROM house WHERE house_id = 2;
SELECt * FROM house WHERE house_id = 5;

SELECT * FROM address;

-- 테이블 3개 조인하여 address_id를 기준으로 출력
SELECT r.resident_id, r.r_login_id, r.r_login_pw, r.r_nickname, r.r_phone_number, r.r_email, r.r_category,
       h.house_id, h.house_dong, h.house_hosu,
       a.address_id, a.road_address, a.jibun_address, a.si_do, a.si_gu_gun, a.dong_myun, a.detail_road_address, a.detail_jibun_address, a.post_code
FROM resident r
INNER JOIN house h ON r.r_house_id = h.house_id
INNER JOIN address a ON h.house_address_id = a.address_id WHERE resident_id = 1;


-- 2024-02-20-화-연습
use buildinghelper;
select * from v_select_resident_information;

SELECT r_login_id, r_category, r_nickname, r_phone_number, r_email FROM v_select_resident_information WHERE r_house_id = 2;

UPDATE v_select_resident_information SET r_login_pw = "1234" WHERE r_login_id="test5" AND r_login_pw="4321";
UPDATE v_select_resident_information SET r_login_pw = "4321", r_nickname = "테스트5", r_phone_number="010", r_email="test5@gmail.com"  WHERE r_login_id="test5" ;
UPDATE v_select_resident_information SET r_login_pw = "4321", r_nickname = "테스트5", r_phone_number="010", r_email="test5@gmail.com"  WHERE r_login_id="test5" AND r_login_pw = ?;

-- 관리사무소에서 세대원 가입자 링크 생성중
SELECT a.address_id, h.house_id, h.house_name, h.house_dong, h.house_hosu
FROM house h
JOIN address a ON h.house_address_id = a.address_id
WHERE h.house_id
ORDER BY h.house_address_id;

desc register_link;

select * from register_link;

select * from house;

-- 회원 가입 링크에 테스테 데이터를 넣어보았습니다.
INSERT INTO register_link VALUES(NULL, 	2, "test", default, false, "test1아파트-2", "101동-테스트-2", "202호-테스트2", default);

update register_link set register_category = "관리소" where register_link_id=1;

-- 아직 유효하지 않은 가입 링크만 SELECT 합니다.
SELECT * FROM register_link WHERE register_expired_check = false;


-- 
SELECT r.r_login_id, r.r_login_pw, r.r_category, r.r_house_id, 
				h.house_name, h.house_dong, h.house_hosu 
				FROM resident as `r` 
				JOIN house AS `h` 
				ON r.r_house_id = h.house_id 
				WHERE r_login_id = 'test5';
                
SELECT * FROM resident ;
desc register_link;
-- ==========회원 가입 링크 생성 SQL문 연습==========
INSERT INTO register_link(register_address_id, register_url_link, expired_date, register_expired_check,  register_house_name, register_house_dong, register_house_hosu, register_category) VALUES(2, 'test-url', default, default, 'test아파트-2', 'test-2동', 'test-3호', default);

select * from register_link;

desc register_link;

INSERT INTO register_link(register_house_address_id, register_url_link, expired_date, register_expired_check, 
register_house_name, register_house_dong, `register-house_hosu`, register_category) 
VALUES(2, 'test-url', default, default,
'test아파트2', 'test1아파트-2-3', '101동-테스트-2-3', '202호-테스트-2-3');

SELECT register_house_name, register_house_dong FROM register_link;

SELECT * FROM register_link;

SELECT register_house_dong, register_house_hosu, register_url_link FROM register_link WHERE register_address_id = 2 AND expired_date = '9999-12-31 23:59:59' AND register_expired_check = FALSE GROUP BY register_house_name, register_house_dong, register_house_hosu ORDER BY register_house_dong, register_house_hosu;

SELECT a.address_id, h.house_id, h.house_name, h.house_dong, h.house_hosu, r.resident_id, r.r_login_id, r.r_login_pw, r.r_nickname, r.r_phone_number, r.r_email, r.r_category
FROM house h
JOIN address a ON h.house_address_id = a.address_id
LEFT JOIN resident r ON h.house_id = r.r_house_id
ORDER BY h.house_address_id AND resident_id;

SELECT * FROM resident;

select * from register_link;

SELECT register_address_id, register_house_name, register_house_dong, register_house_hosu 
FROM register_link
WHERE register_url_link = '2-1708475501636';

select * from register_link;

desc resident;

select * from resident;
SELECT * FROM house;

SELECT house_id, house_name, house_dong, house_hosu FROM house WHERE house_address_id =2 AND house_name = "test1아파트-2" AND house_dong = "101동-테스트-2" AND house_hosu="202호-테스트-2";
SELECT house_id, house_name, house_dong, house_hosu FROM house WHERE house_address_id =? AND house_name = ? AND house_dong = ? AND house_hosu=?;

-- 2024-02-22-목 SQL문 연습
SELECT * FROM house;
SELECT house_id, house_name, house_dong, house_hosu FROM house
WHERE house_address_id = 2 AND house_name;

INSERT INTO house(house_address_id, house_name, house_dong, house_hosu) VALUES(3, "test3아파트", "3동-test", "4호-테스트");

SELECT * FROM resident;
SELECT * FROm house;
SELECt * FROM register_link;

INSERT INTO resident(r_house_id, r_login_id, r_login_pw, r_nickname, r_phone_number, r_email, r_category) VALUES(4, "test7", "1234", "테스트7", "7", "test7@gmail.com", "세대원");
select * from resident;

-- 2024-02-23-금-로그인 후 DB 접근 쿼리 재작성중
-- 3단 조인으로 로그인시 세션에 담을 컬럼들 가져오는 쿼리문입니다.
/*
	r_login_id, r_nickname, r_category,
    house_id, house_dong, house_hosu,
    address_id, house_name
*/
SELECT r.r_login_id,r.r_nickname, r.r_category, h.house_id, 
		a.house_name,  h.house_dong, h.house_hosu, a.address_id
FROM resident r
INNER JOIN house h ON r.r_house_id = h.house_id
INNER JOIN address a ON h.house_address_id = a.address_id WHERE r_login_id = 'admin';

-- address_id를 기준으로 세대원 정보 확인하는 SQL문 작성중입니다.
SELECT h.house_dong, h.house_hosu, r.r_nickname, r.r_category, a.address_id 
FROM resident r 
INNER JOIN house h ON r.r_house_id = h.house_id
INNER JOIN address a ON h.house_address_id = a.address_id
WHERE address_id = 2
ORDER BY h.house_dong, h.house_hosu ASC;


SELECT * FROM resident r 
INNER JOIN house h ON r.r_house_id = h.house_id
INNER JOIN address a ON h.house_address_id = a.address_id;

SELECT * FROM resident;
SELECT r_category FROM resident WHERE r_login_id = 'admin';

-- 2024-02-28-수
-- 연습장 시작
SELECT h.house_dong, h.house_hosu, r.r_login_id, r.r_nickname, r.r_category, a.address_id FROM resident r INNER JOIN house h ON r.r_house_id = h.house_id INNER JOIN address a ON h.house_address_id = a.address_id WHERE address_id = 1 ORDER BY h.house_dong, h.house_hosu ASC;	
SELECT * FROM resident;
SELECT * from house;
select * from address;

UPDATE resident SET r_house_id = 1 WHERE r_login_id="test7";

SELECT * FROM resident;

UPDATE resident SET r_house_id = 1 WHERE r_house_id = 3;
desc register_link;
SELECT * FROM register_link ORDER BY register_house_dong * 1, register_house_dong * 1;

/*
	 * 설명:
	 * 	- 실제 거주자(resident)가 살고 있는 집(house)만 조회합니다.
	 */
SELECT h.house_dong
FROM house h
WHERE EXISTS (
    SELECT 1
    FROM resident r
    WHERE r.r_house_id = h.house_id
) AND house_address_id = 2;

SELECT distinct house_dong FROM house h WHERE EXISTS (SELECT 1 FROM resident r WHERE r.r_house_id = h.house_id) AND h.house_address_id = 2;
SELECT distinct house_dong FROM house h WHERE EXISTS (SELECT 1 FROM resident r WHERE r.r_house_id = h.house_id) AND h.house_address_id = 2 ORDER BY CAST(house_dong AS UNSIGNED);

SELECT * FROM resident;
SELECT * FROM house;
desc house;
UPDATE resident set r_house_id = 18 WHERE r_login_id = "test15";

SELECT h.house_dong FROM house h WHERE EXISTS (SELECT 1 FROM resident r WHERE r.r_house_id = h.house_id) AND h.house_address_id = 2 ORDER BY CAST(house_dong AS UNSIGNED);

select * from resident;

SELECT * FROM house WHERE house_address_id = 2 AND house_dong="테스트-동-101" ORDER BY CAST(house_hosu AS UNSIGNED);

SELECT * FROM resident;

SELECT h.*, r.*, a.*
FROM resident r
JOIN house h ON r.r_house_id = h.house_id -- house_id를 기반으로 동대표를 SELECT하였습니다.
JOIN address a on h.house_id = a.address_id
WHERE r.r_category = '동대표' AND a.address_id = 3;

SELECT * FROM address;

SELECT h.*, r.*
FROM resident r
JOIN house h ON r.r_house_id = h.house_id -- house_id를 기반으로 동대표를 SELECT하였습니다.
WHERE r_house_id = 3;

-- house_id를 기반으로 
-- SELECT h.*, r.*
SELECT r.* FROM resident r JOIN house h ON r.r_house_id = h.house_id WHERE house_address_id = 2 AND house_dong="테스트-동-101" AND house_hosu="테스트-호수-303";


SELECT * FROM resident WHERE r_category = '동대표';

SELECT * FROM house;

-- 특정 address(아파트 단지)의 동대표 조회 SQL문
SELECT * FROM house h 
JOIN resident r ON r.r_house_id = h.house_id
WHERE house_address_id = 2 AND r_category = '동대표';

SELECT * FROM house 
WHERE house_address_id = ? AND house_dong=? 
GROUP BY house_id
HAVING COUNT(*) > 0
ORDER BY CAST(house_hosu AS UNSIGNED);