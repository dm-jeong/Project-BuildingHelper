USE buildingHelper;

CREATE TABLE IF NOT EXISTS club(
	c_id INTEGER AUTO_INCREMENT not null,
    c_title varchar(20),
    c_description varchar(100),
    c_time datetime,
    c_file_name varchar(20),
    c_writer_id varchar(20),
    c_address_id int,
    primary key(c_id)
)default charset=utf8;

-- DROP TABLE IF EXISTS club;

select * from club;

CREATE TABLE IF NOT EXISTS club_board(
	cb_id INTEGER AUTO_INCREMENT not null,
    cb_c_id Integer not null,
    cb_title varchar(20),
    cb_description varchar(1000),
    cb_time datetime,
    cb_file_name varchar(20),
    cb_writer_id varchar(20),
    cb_count int,
    primary key(cb_id)
)default charset=utf8;

-- DROP TABLE IF EXISTS club_board;

desc club_board;

select * from club_board;
SELECT COUNT(*) FROM club_board WHERE cb_c_id ;
