USE buildingHelper;

CREATE TABLE IF NOT EXISTS vote(
	v_id INTEGER AUTO_INCREMENT not null,
    v_title varchar(30),
    v_option1 varchar(50),
    v_option2 varchar(50),
    v_option3 varchar(50),
    v_option4 varchar(50),
    v_option5 varchar(50),
    v_option6 varchar(50),
    v_option7 varchar(50),
    v_option8 varchar(50),
    v_option9 varchar(50),
    v_option10 varchar(50),
    v_option11 varchar(50),
    v_option12 varchar(50),
    v_option13 varchar(50),
    v_option14 varchar(50),
    v_option15 varchar(50),
    v_option16 varchar(50),
    v_option17 varchar(50),
    v_option18 varchar(50),
    v_option19 varchar(50),
    v_option20 varchar(50),
    v_result1 int,
    v_result2 int,
    v_result3 int,
    v_result4 int,
    v_result5 int,
    v_result6 int,
    v_result7 int,
    v_result8 int,
    v_result9 int,
    v_result10 int,
    v_result11 int,
    v_result12 int,
    v_result13 int,
    v_result14 int,
    v_result15 int,
    v_result16 int,
    v_result17 int,
    v_result18 int,
    v_result19 int,
    v_result20 int,
    v_start_time datetime,
    v_end_time varchar(1000),
    v_creator_id varchar(20),
    v_category varchar(20),
    v_address_id int,
    v_house_dong varchar(14),
    primary key(v_id)
)default charset=utf8;

CREATE TABLE IF NOT EXISTS vote_participant(
    vp_id INTEGER auto_increment not null,
    vp_house_id int not null,
    vp_v_id INTEGER not null,
    vp_result varchar(50),
    primary key(vp_id)
)default charset=utf8;

select * from vote;
select * from vote_participant;

SELECT COUNT(*) FROM vote WHERE v_category = '관리소';
SELECT COUNT(*) FROM vote WHERE v_category = '세대원' or v_category = '동대표';

-- DROP TABLE IF EXISTS vote;
-- DROP TABLE IF EXISTS vote_participant;