drop table if exists TEST;
create table TEST (FIELD int not null primary key);

delimiter $$

drop procedure if exists fill_numbers $$
create procedure fill_numbers()
deterministic
begin
  declare counter int default 1;
  insert into TEST values (1);
  while counter < 1000000
  do
      insert into TEST (FIELD)
          select FIELD + counter
          from TEST;
      select count(*) into counter from TEST;
      select counter;
  end while;
end $$
delimiter ;

call fill_numbers();