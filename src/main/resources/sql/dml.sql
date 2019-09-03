INSERT INTO dealer_stat.user (first_name, last_name, password, email, created_at, role)
VALUES ('Админус', 'Великолепный', 'admin', 'admin@mail.com', CURRENT_TIMESTAMP(0), 'ADMIN'),
       ('Анна', 'Бриг', 'anna', 'anna@mail.com', CURRENT_TIMESTAMP(0), 'TRADER'),
       ('Алексей', 'Грыж', 'alex', 'alex@mail.com', CURRENT_TIMESTAMP(0), 'TRADER');

INSERT INTO dealer_stat.game(name)
VALUES ('CS:GO'),
       ('DOTA');

INSERT INTO dealer_stat.user_game(user_id, game_id)
VALUES ((SELECT id FROM dealer_stat.user u WHERE u.email = 'anna@mail.com'),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'CS:GO')),
       ((SELECT id FROM dealer_stat.user u WHERE u.email = 'anna@mail.com'),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'DOTA')),
       ((SELECT id FROM dealer_stat.user u WHERE u.email = 'alex@mail.com'),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'CS:GO'));

INSERT INTO dealer_stat.game_object(title, text, status, author_id, created_at, updated_at, game_id)
VALUES ('АК-74', 'Самый эпический автомат, который вы могли бы видеть', 'APPROVED',
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'anna@mail.com'), CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'CS:GO')),
       ('Амулет Маны', 'Амулет дарующий магическую силу носителю', 'APPROVED',
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'anna@mail.com'), CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'DOTA')),
       ('Амулет Жизни', 'Да самый простой амулет жизни', 'APPROVED',
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'anna@mail.com'), CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'DOTA')),
       ('ПМ', 'Пистолет на все времена', 'APPROVED',
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'alex@mail.com'), CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'CS:GO')),
       ('М16', 'Пушка настоящего НАТО-вца', 'APPROVED',
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'alex@mail.com'), CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'CS:GO')),
       ('ПКМ', 'Просто лучше в мире пушка', 'DECLINED',
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'alex@mail.com'), CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'CS:GO'));

INSERT INTO dealer_stat.comment(message, rating, post_id, author_id, created_at, approved)
VALUES ('Пушка огонь!', 5, (SELECT id FROM dealer_stat.game_object WHERE id = 1), NULL, CURRENT_TIMESTAMP(0), TRUE),
       ('Лучший!!!', 4, (SELECT id FROM dealer_stat.game_object WHERE id = 1), NULL, CURRENT_TIMESTAMP(0), TRUE),
       ('х**ня это все', 1, (SELECT id FROM dealer_stat.game_object WHERE id = 1), NULL, CURRENT_TIMESTAMP(0), FALSE),
       ('Такой себе...', 2, (SELECT id FROM dealer_stat.game_object WHERE id = 2), NULL, CURRENT_TIMESTAMP(0), TRUE),
       ('Мне понравился', 3, (SELECT id FROM dealer_stat.game_object WHERE id = 3), NULL, CURRENT_TIMESTAMP(0), TRUE),
       ('Переходим ко мне!', 5, (SELECT id FROM dealer_stat.game_object WHERE id = 4),
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'anna@mail.com'), CURRENT_TIMESTAMP(0), TRUE),
       ('МООЩЩЩ!!!', 5, (SELECT id FROM dealer_stat.game_object WHERE id = 5), NULL, CURRENT_TIMESTAMP(0), TRUE);
