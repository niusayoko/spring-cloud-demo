create table rocket_transaction.t_credit
(
    id          int auto_increment comment '积分表'
        primary key,
    user_id     int           not null comment '用户id',
    username    varchar(16)   not null comment '用户姓名',
    integration int default 0 not null comment '积分'
);


