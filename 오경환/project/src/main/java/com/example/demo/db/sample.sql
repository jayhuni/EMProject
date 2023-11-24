
/* Drop Tables */

DROP TABLE Rent CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE Rent
(
	-- rent date
	rent_date varchar2(20),
	rent_no varchar2(10),
	station_name varchar2(50),
	rent_code varchar2(10),
	sex varchar2(10),
	age varchar2(20),
	use_no varchar2(10),
	exer_amount varchar2(20),
	carbon_amount varchar2(20),
	moving_length varchar2(20),
	usage_time varchar2(10)
);



/* Comments */

COMMENT ON COLUMN Rent.rent_date IS 'rent date';



