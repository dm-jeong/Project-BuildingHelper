-- ========== 5차 테이블 설계 시작 ==========
-- 5차 설계의 목표: 
-- 단순한 메시지 기능이 아닌, 프로젝트를 위한 메시지 기능을 구현합니다.
-- 받는 세대원의 아이디를 모를 수 있습니다. -> 동과 호수로 보낼 수 있어야 합니다.
-- 테이블 2개(발신과 수신)를 하나의 테이블로 합쳤습니다.
/*
	아래 INDEX를 생성해야 msg 테이블을 생성할 수 있습니다.
*/
CREATE INDEX idx_house_dong ON house(house_dong);
CREATE INDEX idx_house_hosu ON house(house_hosu);

CREATE TABLE IF NOT EXISTS msg(
	msg_id INT AUTO_INCREMENT PRIMARY KEY,
    
    msg_sender_r_login_id VARCHAR(20), /* FK 보내는 사람 ID */
    
    msg_sender_house_dong VARCHAR(14), /* FK 보내는 사람 동*/
    msg_sender_house_hosu VARCHAR(14), /* FK 보내는 사람 호수*/
    
    msg_receiver_house_dong VARCHAR(14), /* FK 받는 사람의 동*/
    msg_receiver_house_hosu VARCHAR(14), /* FK 받는 사람의 호수*/
    
    msg_address_id INT, /* FK */
    msg_title VARCHAR(20),
    msg_content VARCHAR(1000), /* 메시지 내용 */
    
    msg_sent_time DATETIME, /* 전송시간을 저장합니다. 메시지가 쌓이는걸 대비해서 시간 기준으로 조회하는걸 추천합니다.*/
    msg_receiver_read_time DATETIME, /* 읽은 시간이 NULL이 아니면 읽었는지 아닌지 파악이 가능합니다. */
    
	FOREIGN KEY (msg_sender_r_login_id) REFERENCES resident(r_login_id),
    
    FOREIGN KEY (msg_sender_house_dong) REFERENCES house(house_dong),
    FOREIGN KEY (msg_sender_house_hosu) REFERENCES house(house_hosu),
	FOREIGN KEY (msg_receiver_house_dong) REFERENCES house(house_dong),
    FOREIGN KEY (msg_receiver_house_hosu) REFERENCES house(house_hosu),
    FOREIGN KEY (msg_address_id) REFERENCES address(address_id)
)default charset=utf8;

-- ========== 5차 테이블 설계 종료 ==========
desc msg;

SELECT * FROM resident WHERE r_login_id = 'test2' || r_house_id = 3;

SELECT * FROM house WHERE house_dong = '테스트-동-101' AND house_hosu='테스트-호수-202' AND house_address_id = '같음, ? 처리 예정';

select * from msg;
insert into msg VALUES(null, 'test6', 
"101동-테스트-5", "202호-테스트-5", "101동-테스트-5", "202호-테스트-5", 
3, "테스트 메시지입니다", now(), null );

select * from msg;
desc msg;

select * from resident;

select address_id, house_id from address a join house h on a.address_id = h.house_address_id;

select * from msg WHERE msg_id = 2;

select msg_id, msg_sender_r_login_id, msg_sender_house_dong, msg_sender_house_hosu, msg_content FROM msg WHERE msg_address_id =3 AND msg_receiver_house_dong = ? AND msg_receiver_house_hosu = ? ;

-- ========== 단체 메시지(Group Message) 시작 ==========
/*
	그룹 메시지 
*/
CREATE TABLE IF NOT EXISTS msg_group(
	msg_group_id INT AUTO_INCREMENT PRIMARY KEY,
    
    msg_group_sender_r_login_id VARCHAR(20), /* FK 보내는 사람 ID */
    msg_group_sender_house_dong VARCHAR(14), /* FK 보내는 사람 동*/
    msg_group_sender_house_hosu VARCHAR(14), /* FK 보내는 사람 호수*/
    
    msg_group_sender_r_category VARCHAR(10), /* 관리소, 동대표 */
    
    /* 
		FK 받는 사람의 동을 의미합니다. 
		관리소 -> NULL을 삽입하여 address_id가 같은 모든 사람에게 전송합니다.
        동대표 -> NULL이 아닌 자신의 house_dong 값을 넣어, 해당 동에 거주하는 세대로 메시지를 보냅니다.
    */
    msg_group_receiver_house_dong VARCHAR(14), 
    
    msg_group_address_id INT, /* FK */
    msg_group_title VARCHAR(20), /* 메시지 제목 */
    msg_group_content VARCHAR(1000), /* 메시지 내용 */
    
    msg_sent_time DATETIME, /* 전송시간을 저장합니다. 메시지가 쌓이는걸 대비해서 시간 기준으로 조회하는걸 추천합니다.*/
    
    FOREIGN KEY (msg_group_sender_r_login_id) REFERENCES resident(r_login_id),
    FOREIGN KEY (msg_group_sender_house_dong) REFERENCES house(house_dong),
    FOREIGN KEY (msg_group_sender_house_hosu) REFERENCES house(house_hosu),
    FOREIGN KEY (msg_group_address_id) REFERENCES address(address_id)
)default charset=utf8;

/*
	그룹 메시지 수신 확인
*/ 
CREATE TABLE IF NOT EXISTS msg_group_receive (
    msg_group_receive_id INT AUTO_INCREMENT PRIMARY KEY, /* PK */
    msg_group_id INT, /* FK */
    msg_group_receive_dong VARCHAR(14), /* 수신한 동 */
    msg_group_receive_hosu VARCHAR(14), /* 수신한 호수 */
    msg_group_receive_time DATETIME, /* 수신한 시간 */

    FOREIGN KEY (msg_group_id) REFERENCES msg_group(msg_group_id)
) default charset=utf8;

desc msg_group;

drop table msg_group;

select * from address;

select * from house;
select * from resident;
select * from msg_group WHERE msg_group_id=6;

SELECT * FROM msg_group WHERE msg_group_address_id = 1 AND (msg_group_receiver_house_dong = 102);

INSERT INTO msg_group VALUES(NULL, "admin", null, null, "관리소", null, "1", "제목2", "내용2", NOW());

SELECT msg_group_title, msg_group_sender_r_category, msg_sent_time from msg_group WHERE msg_group_address_id = 1 AND (msg_group_receiver_house_dong = "102" OR msg_group_receiver_house_dong IS NULL) ORDER BY msg_sent_time DESC;