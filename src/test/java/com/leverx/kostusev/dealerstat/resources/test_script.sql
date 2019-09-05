INSERT INTO dealer_stat.user (id, first_name, last_name, password, email, created_at, role)
VALUES (1, 'Админус', 'Великолепный', 'admin', 'admin@mail.com', CURRENT_TIMESTAMP(0), 'ADMIN'),
       (2, 'Анна', 'Бриг', 'anna', 'anna@mail.com', CURRENT_TIMESTAMP(0), 'TRADER'),
       (3, 'Алексей', 'Грыж', 'alex', 'alex@mail.com', CURRENT_TIMESTAMP(0), 'TRADER');

INSERT INTO dealer_stat.game(id, name)
VALUES (1, 'CS:GO'),
       (2, 'DOTA');

INSERT INTO dealer_stat.user_game(user_id, game_id)
VALUES ((SELECT id FROM dealer_stat.user u WHERE u.email = 'anna@mail.com'),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'CS:GO')),
       ((SELECT id FROM dealer_stat.user u WHERE u.email = 'anna@mail.com'),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'DOTA')),
       ((SELECT id FROM dealer_stat.user u WHERE u.email = 'alex@mail.com'),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'CS:GO'));

INSERT INTO dealer_stat.game_object(id, title, text, status, author_id, created_at, updated_at, game_id)
VALUES (1, 'АК-74', 'Самый эпический автомат, который вы могли бы видеть', 'APPROVED',
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'anna@mail.com'), CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'CS:GO')),
       (2, 'Амулет Маны', 'Амулет дарующий магическую силу носителю', 'APPROVED',
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'anna@mail.com'), CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'DOTA')),
       (3, 'Амулет Жизни', 'Да самый простой амулет жизни', 'APPROVED',
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'anna@mail.com'), CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'DOTA')),
       (4, 'ПМ', 'Пистолет на все времена', 'APPROVED',
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'alex@mail.com'), CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'CS:GO')),
       (5, 'М16', 'Пушка настоящего НАТО-вца', 'APPROVED',
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'alex@mail.com'), CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'CS:GO')),
       (6, 'ПКМ', 'Просто лучше в мире пушка', 'DECLINED',
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'alex@mail.com'), CURRENT_TIMESTAMP(0), CURRENT_TIMESTAMP(0),
        (SELECT id FROM dealer_stat.game g WHERE g.name = 'CS:GO'));

INSERT INTO dealer_stat.comment(id, message, rating, post_id, author_id, created_at, approved)
VALUES (1, 'Пушка огонь!', 5, (SELECT id FROM dealer_stat.game_object WHERE id = 1), NULL, CURRENT_TIMESTAMP(0), TRUE),
       (2, 'Лучший!!!', 4, (SELECT id FROM dealer_stat.game_object WHERE id = 1), NULL, CURRENT_TIMESTAMP(0), TRUE),
       (3, 'х**ня это', 1, (SELECT id FROM dealer_stat.game_object WHERE id = 1), NULL, CURRENT_TIMESTAMP(0), FALSE),
       (4, 'Такой себе...', 2, (SELECT id FROM dealer_stat.game_object WHERE id = 2), NULL, CURRENT_TIMESTAMP(0), TRUE),
       (5, 'понравился', 3, (SELECT id FROM dealer_stat.game_object WHERE id = 3), NULL, CURRENT_TIMESTAMP(0), TRUE),
       (6, 'Переходим ко мне!', 5, (SELECT id FROM dealer_stat.game_object WHERE id = 4),
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'anna@mail.com'), CURRENT_TIMESTAMP(0), TRUE),
       (7, 'МООЩЩЩ!!!', 5, (SELECT id FROM dealer_stat.game_object WHERE id = 5), NULL, CURRENT_TIMESTAMP(0), TRUE);
