select user() from dual; 
use nitdb;

show tables

create table AUDIT ( TABLE_NAME varchar(50), ID integer, VERSION integer, CREATED_BY varchar(50), CREATED_DATE date);

create table test_table ( test_name varchar(10));
select * from test_table;

truncate table ADDRESS CASCADE;
delete from table ADDRESS_MASTER CASCADE;

drop table ADDRESS;
drop table BASE_ADDRESS;

select COLUMN_NAME, CONSTRAINT_NAME, REFERENCED_COLUMN_NAME, REFERENCED_TABLE_NAME
from information_schema.KEY_COLUMN_USAGE
where TABLE_NAME = 'ADDRESS';


desc BASE_ADDRESS;

select * from ADDRESS;
delete from ADDRESS where bldg = 4;
select * from BASE_ADDRESS;
update BASE_ADDRESS set COUNTRY = 'Japan' where id = 1;
