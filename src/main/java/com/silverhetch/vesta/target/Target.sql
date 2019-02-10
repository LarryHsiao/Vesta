create table if not exists target
(
  uri char unique
);

drop table target;
select *
from target;

insert into target(uri)
values (?);

delete from target where uri=?;