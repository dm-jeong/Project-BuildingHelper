USE buildingHelper;
desc item_trade;
--  개인 거래 게시판 
create table if not exists item_trade (
	item_trade int auto_increment primary key,
	address_id int,  -- 우리 아파트 구분
	title varchar(20) NOT NULL,  -- 글 제목
	category varchar(20) NOT NULL,  -- 상품종류
	reg_date timestamp default current_timestamp not null,  -- 글 작성 시간/타임스탬프 사용
	price int NOT NULL,  -- 가격
	description TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,  -- UTF8MB4 문자 세트
	board_count int,  -- 조회수
	file_name varchar(200),
	r_login_id varchar(20),
	update_date timestamp default current_timestamp not null, -- 글 수정 시간/타임스탬프 사용
	foreign key(address_id) references address(address_id) 
)default charset=utf8mb4;


-- 게시글 등록 시간 reg_date 가 시간 차이가 날 때(timestamp의 timezone관련 기본 옵션)
-- reg_date 에러날 경우, 등록시간이 8시간 등 차이날 경우 사용
select @@global.time_zone;
select @@session.time_zone;
SET GLOBAL time_zone='utc';
set session time_zone='utc';
SELECT b.name, a.time_zone_id
FROM mysql.time_zone a, mysql.time_zone_name b
WHERE a.time_zone_id = b.time_zone_id AND b.name LIKE '%Seoul';


-- 부동산 거래 게시판
create table if not exists estate_trade(
	estate_trade int auto_increment not null primary key,
    building_type varchar(20),  -- 건물종류
    sales_status varchar(10) default "판매중",  -- 등록에서는 판매중 자동입력, 수정에서 상태바꿀수있음
    title varchar(20),  -- 제목
    reg_date timestamp default current_timestamp not null, -- 작성시간 자동저장
    description TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,  -- UTF8MB4 문자 세트
    board_count int,  -- 조회수
    file_name varchar(20),  -- 이미지파일이름
    address_api varchar(200),  -- 등록 주소
    move_in date,  -- 입주가능일
    r_login_id varchar(20),
    update_date timestamp default current_timestamp not null, -- 글 수정 시간/타임스탬프 사용
    foreign key(r_login_id) references resident(r_login_id) 
)default charset=utf8; -- mb4;

desc estate_trade;
select * from estate_trade;

-- 관리비 조회 
create table expense(
	expense_id int auto_increment primary key,
    house_id int,  /* house_name, house_dong , house_hosu */
    e_charge int, -- 전기세
    water_charge int, -- 수도세
    p_charge int, -- 공용관리비
    
    expense_state varchar(10) default '미납',
	expense_year year,
    expense_month tinyint,
    expense_paid date,
	expense_price INT AS (e_charge + water_charge + p_charge) STORED,  -- 합계
	FOREIGN KEY (house_id) REFERENCES house(house_id)  
)default charset=utf8mb4;



/* 납부일 등록(insert, update)시 미납이 납부로 변경하는 트리거 설정 */

DELIMITER //
CREATE TRIGGER update_expense_state
BEFORE INSERT ON expense
FOR EACH ROW
BEGIN
    IF NEW.expense_paid IS NOT NULL THEN
        SET NEW.expense_state = '납부완료';
	else
		set new.expense_state = '미납';
    END IF;
END; 
//

CREATE TRIGGER update_expense_state_before_update
BEFORE UPDATE ON expense
FOR EACH ROW
BEGIN
    IF NEW.expense_paid IS NOT NULL THEN
        SET NEW.expense_state = '납부완료';
	else
		set new.expense_state = '미납';
    END IF;
END; 
//
DELIMITER ;