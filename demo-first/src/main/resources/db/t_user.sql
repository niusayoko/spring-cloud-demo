create table rocket_transaction.t_user
(
    id              int auto_increment comment '用户表'
        primary key,
    name            varchar(16)          not null comment '姓名',
    id_card         varchar(32)          not null comment '身份证号',
    balance         int        default 0 not null comment '余额',
    state           tinyint(1)           null comment '状态（1在线，0离线）',
    vip_flag        tinyint(1) default 0 not null comment 'VIP用户标识（1是，0否）',
    create_time     datetime             not null comment '创建时间',
    last_login_time datetime             null comment '最后一次登录时间'
);


