

-- ==========1차 테이블 설계 시작==========
-- 메시지 기능을 구현하기 위한 SQL문입니다.
-- 메시지
-- CREATE TABLE IF NOT EXISTS msg(
-- 	msg_id INTEGER AUTO_INCREMENT PRIMARY KEY,
--     msg_sender_id VARCHAR(20),
--     msg_receiver_id VARCHAR(20),
--     msg_content VARCHAR(1000),
--     msg_sent_time TIMESTAMP,
--     msg_read_time_by_receiver TIMESTAMP,
--     FOREIGN KEY (msg_sender_id) REFERENCES resident(r_login_id),
--     FOREIGN KEY (msg_receiver_id) REFERENCES resident(r_login_id)
-- )default charset=utf8;

-- INSERT INTO msg(msg_sender_id, msg_receiver_id, msg_content, msg_sent_time)
-- VALUES("admin", "test2", "안녕하세요 테스트메시지입니다.", now());

-- select * from msg;

-- 위 테이블의 문제점: 받은 사람이 메시지를 삭제하면 보낸 사람도 메시지가 삭제됩니다.
-- ==========1차 테이블 설계 종료(폐기)==========

-- ==========2차 테이블 설계 시작==========
-- CREATE TABLE IF NOT EXISTS msg(
-- 	msg_id INT AUTO_INCREMENT PRIMARY KEY,
--     msg_sender_r_login_id VARCHAR(20),
--     msg_content VARCHAR(1000),
--     msg_sent_time TIMESTAMP, /* 전송시간을 저장합니다. 메시지가 쌓이는걸 대비해서 시간 기준으로 조회하는걸 추천합니다.*/
--     
-- 	FOREIGN KEY (msg_sender_r_login_id) REFERENCES resident(r_login_id)
-- )default charset=utf8;

-- CREATE TABLE IF NOT EXISTS msg_mid_receiver(
-- 	msg_mid_id INT AUTO_INCREMENT PRIMARY KEY,
--     msg_id INT,
--     msg_receiver_id VARCHAR(20),
--     msg_mid_read_time TIMESTAMP, /* 읽은 시간이 NULL이 아니면 읽었는지 아닌지 파악이 가능합니다.*/
--     is_deleted BOOLEAN DEFAULT FALSE, /* true면 더이상 표시하지 않습니다.*/
--     
--     FOREIGN KEY (msg_id) REFERENCES msg(msg_id),
--     FOREIGN KEY (msg_receiver_id) REFERENCES resident(r_login_id)
-- )default charset=utf8;

-- desc msg;
-- desc msg_mid_receiver;

-- SELECT * from resident WHERE r_login_id = 'admin' OR r_house_id = 2;

-- INSERT INTO msg VALUES(NULL, "admin", "test", NOW());

-- INSERT INTO msg_mid_receiver(msg_id, msg_receiver_id, msg_mid_read_time, is_deleted)
-- VALUES(1, NULL, NOW(), NULL);

-- select * from msg;
-- select * from msg_mid_receiver;
-- ==========2차 테이블 설계 종료(보류)==========

-- ========== 4차 테이블 설계 시작 ==========
-- 4차 설계의 목표: 
-- 3차 설계 목표 + 메시지/메시지 보내는 사람/메시지 받는 사람 총 3가지 테이블로 구분
-- 
CREATE TABLE IF NOT EXISTS msg_sender(
	msg_sender_id INT AUTO_INCREMENT PRIMARY KEY,
    
    msg_id INT AUTO_INCREMENT, /* FK */
    
    msg_sender_r_login_id VARCHAR(20), /* FK */
	msg_sender_house_id INT, /* FK */
    msg_sender_address_id INT, /* FK */
    msg_sender_content VARCHAR(1000),
    
	FOREIGN KEY (msg_sender_r_login_id) REFERENCES resident(r_login_id),
    FOREIGN KEY (msg_sender_house_id) REFERENCES house(house_id),
    FOREIGN KEY (msg_sender_address_id) REFERENCES address(address_id)
)default charset=utf8;

CREATE TABLE IF NOT EXISTS msg(
	msg_id INT AUTO_INCREMENT PRIMARY KEY,
    msg_content VARCHAR(1000),
    msg_sent_time TIMESTAMP,
    msg_read_time TIMESTAMP
)default charset=utf8;

CREATE TABLE IF NOT EXISTS msg_receiver(
	msg_receiver_id INT AUTO_INCREMENT PRIMARY KEY,
    
    msg_receiver_r_login_id VARCHAR(20), /* FK */
	msg_receiver_house_id INT, /* FK */
    msg_receiver_house_dong VARCHAR(14), /* FK */
    msg_receiver_house_hosu VARCHAR(14), /* FK */
    
    msg_receiver_address_id INT, /* FK */
    
    FOREIGN KEY (msg_sender_id) REFERENCES msg_sender(msg_sender_id),
    FOREIGN KEY (msg_receiver_r_login_id) REFERENCES resident(r_login_id),
    FOREIGN KEY (msg_receiver_house_id) REFERENCES house(house_id),
    FOREIGN KEY (msg_receiver_house_dong) REFERENCES house(house_dong),
    FOREIGN KEY (msg_receiver_house_hosu) REFERENCES house(house_hosu),
    FOREIGN KEY (msg_receiver_address_id) REFERENCES address(address_id)
)default charset=utf8;
-- ========== 4차 테이블 설계 종료 ==========

use buildingHelper;
select * from msg;

alter table msg MODIFY msg_sent_time datetime;
alter table msg MODIFY msg_receiver_read_time datetime;

desc msg;