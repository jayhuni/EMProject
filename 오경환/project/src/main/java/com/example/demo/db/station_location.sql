
/* Drop Tables */

DROP TABLE station_location CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE station_location
(
	station_id varchar2(20) NOT NULL,
	addr1 varchar2(100),
	addr2 varchar2(100),
	lat number(30),
	lon number(30),
	PRIMARY KEY (station_id)
);



