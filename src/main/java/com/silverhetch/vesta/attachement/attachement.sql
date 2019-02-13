create table if not exists attachments
(
  target_id integer,
  tag_id    integer,
  unique (target_id, tag_id)
);

insert into attachments
values (?, ?);

select *
from attachments;

