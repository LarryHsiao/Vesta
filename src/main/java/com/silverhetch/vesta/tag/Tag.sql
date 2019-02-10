CREATE TABLE IF NOT EXISTS tags
(
  name CHAR unique
);

insert into tags
values (?);

select * from tags;