insert into user values(10001, sysdate(), '노현성', '1234', '121212-1212121');
insert into user values(10002, sysdate(), 'fhhwe', '1234', '121212-1212121');
insert into user values(10003, sysdate(), 'egq2', '1234', '121212-1212121');
insert into user values(10004, sysdate(), 'gk3k1', '1234', '121212-1212121');

insert into post values(20001, 'my first post', 10001);
insert into post values(20002, 'my second post', 10001);
insert into post values(20003, 'my third post', 10002);

-- ' 가 아닌 " 을 쓰면 에러남