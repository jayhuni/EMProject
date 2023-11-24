
/* Drop Tables */

DROP TABLE Rent CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE Rent
(
	-- rent date
	rent_date varchar2(20),
	rent_no number(10),
	station_name varchar2(50),
	rent_code varchar2(10),
	sex number(10),
	age varchar2(20),
	use_no number(10),
	exer_amount number(20),
	carbon_amount number(20),
	moving_length number(20),
	usage_time number(10)
);



/* Comments */

COMMENT ON COLUMN Rent.rent_date IS 'rent date';



