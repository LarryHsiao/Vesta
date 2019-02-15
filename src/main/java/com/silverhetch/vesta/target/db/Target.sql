create table if not exists targets
(
  uri char unique
);

drop table targets;
select *
from targets;

insert into targets(uri)
values (?);

delete from targets where uri=?;