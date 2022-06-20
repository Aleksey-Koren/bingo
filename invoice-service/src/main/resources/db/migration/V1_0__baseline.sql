create table bet
(
    id              bigint auto_increment primary key,
    user_id         bigint                                not null,
    money           bigint                                not null,
    bet_id          varchar(500)                          not null,
    previous_bet_id varchar(500)                          null,
    created         timestamp default current_timestamp() not null on update current_timestamp()
);


INSERT INTO bingo_invoice.bet (id, user_id, money, bet_id, previous_bet_id, created)
VALUES
	(1, 1, 500, '9b99f2e6-0d6e-4242-8abc-4cc505177d75', null, '2022-06-15 10:21:31');