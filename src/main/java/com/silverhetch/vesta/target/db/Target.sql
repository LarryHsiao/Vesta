create table if not exists targets
(
  id   integer primary key auto_increment,
  name char unique
);

drop table targets;
select *
from targets;

insert into targets(name)
values (?);

delete
from targets
where name = ?;


-- Select by keyword
select targets.*
from targets
       left join attachments
                 on targets.id = attachments.TARGET_ID
       left join tags
                 on tags.id = attachments.tag_id
where tags.name like ?
   or targets.name like ?
group by targets.name;

-- insert a target
insert into targets(name)
values ('Simplef');