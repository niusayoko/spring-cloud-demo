create table rocket_transaction.t_mq_transaction_log
(
    transaction_id varchar(64) not null comment '事务id'
        primary key,
    log            varchar(64) not null comment '日志'
);


