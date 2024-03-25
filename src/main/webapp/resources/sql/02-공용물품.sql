USE buildingHelper;

CREATE TABLE IF NOT EXISTS s_item(
	share_item_id INTEGER AUTO_INCREMENT not null,
    name varchar(16),
    s_category varchar(8),
    s_description varchar(500),
    s_condition varchar(4),
    resident_id varchar(20),
    s_start_time datetime,
    s_return_time varchar(1000),
    s_file_name varchar(20),
    s_request BOOLEAN NOT NULL DEFAULT false,
    s_approval BOOLEAN NOT NULL DEFAULT false,
    s_address_id int,
    primary key(share_item_id)
)default charset=utf8;

-- DROP TABLE IF EXISTS s_item;

select * from s_item;
SELECT DISTINCT s_category, s_address_id FROM s_item;