--db관리 sql : vc(version control)에서 확인 가능하다. (아직 확실히 공감은 안된다.. 해보면 알겠지.)

drop table if exists member CASCADE;
create table member
(
 id bigint generated by default as identity,
 name varchar(255),
 primary key (id)
);