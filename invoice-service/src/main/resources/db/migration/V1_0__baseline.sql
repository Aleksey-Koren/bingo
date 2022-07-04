create table bet
(
    id              bigint auto_increment primary key,
    user_id         bigint                                not null,
    money           bigint                                not null,
    bet_id          varchar(500)                          not null,
    previous_bet_id varchar(500)                          null,
    created         timestamp default current_timestamp() not null on update current_timestamp()
);


INSERT INTO bingo_invoice.bet (id, user_id, money, bet_id, previous_bet_id, created) VALUES (35, 1, 500, '352475b1-9364-47e8-afcc-1913bfbc49bb', null, '2022-06-22 08:20:42');
INSERT INTO bingo_invoice.bet (id, user_id, money, bet_id, previous_bet_id, created) VALUES (36, 1, 100, '0c9b19ba-a1ce-490e-977e-908319c67928', '352475b1-9364-47e8-afcc-1913bfbc49bb', '2022-06-22 08:21:05');
INSERT INTO bingo_invoice.bet (id, user_id, money, bet_id, previous_bet_id, created) VALUES (37, 1, -100, '5ea2efc1-2b87-4ce5-b16b-f96afd015be9', '0c9b19ba-a1ce-490e-977e-908319c67928', '2022-06-22 08:21:17');
INSERT INTO bingo_invoice.bet (id, user_id, money, bet_id, previous_bet_id, created) VALUES (38, 1, -100, '5ea2efc1-2b87-4ce5-b16b-f96afd015be9', '5ea2efc1-2b87-4ce5-b16b-f96afd015be9', '2022-06-22 08:21:30');
INSERT INTO bingo_invoice.bet (id, user_id, money, bet_id, previous_bet_id, created) VALUES (39, 1, 100, '93c597cf-792a-432f-8f73-1711ea83fb7f', '885ffcd3-196d-44aa-967a-d2d4e9b0634e', '2022-06-22 08:21:40');
INSERT INTO bingo_invoice.bet (id, user_id, money, bet_id, previous_bet_id, created) VALUES (40, 1, -30, '6e17ca5f-4174-4757-9fc3-3037bf79e1dd', '93c597cf-792a-432f-8f73-1711ea83fb7f', '2022-06-22 08:21:58');
INSERT INTO bingo_invoice.bet (id, user_id, money, bet_id, previous_bet_id, created) VALUES (41, 1, 30000, '0b4550a3-8bab-4ade-97bc-be824b6469b2', '6e17ca5f-4174-4757-9fc3-3037bf79e1dd', '2022-06-22 08:22:07');
INSERT INTO bingo_invoice.bet (id, user_id, money, bet_id, previous_bet_id, created) VALUES (42, 1, 30, '8e6ec69d-c8cb-4650-bf34-7a82aff2e77a', '0b4550a3-8bab-4ade-97bc-be824b6469b2', '2022-06-22 08:22:16');
INSERT INTO bingo_invoice.bet (id, user_id, money, bet_id, previous_bet_id, created) VALUES (43, 1, -30, '19a62104-409b-4728-9141-124171addc19', '8e6ec69d-c8cb-4650-bf34-7a82aff2e77a', '2022-06-22 08:22:25');
INSERT INTO bingo_invoice.bet (id, user_id, money, bet_id, previous_bet_id, created) VALUES (44, 1, -30, 'b1604a89-6380-43dc-bceb-7725760fd878', '19a62104-409b-4728-9141-124171addc19', '2022-06-22 08:22:32');