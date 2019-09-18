INSERT INTO dealer_stat.user (first_name, last_name, password, email, created_at, role, enabled)
VALUES ('Админус', 'Великолепный', '$2a$10$iovx.bvZ32slhCSIQZLA/ehRZJl113HJ4vnbwPRke/H93VeEFGQoG', 'admin@mail.com', CURRENT_TIMESTAMP(0), 'ADMIN', TRUE),
       ('Анна', 'Бриг', '$2a$10$FwyWiDUF2GJRvCGQ72qwwO/HfH0zhVU12KzWFGHjn9HuuHYECePYa', 'anna@mail.com', CURRENT_TIMESTAMP(0), 'TRADER', TRUE),
       ('Алексей', 'Грыж', '$2a$10$1U7u4NX1RzxPsp1hTMl9xeF1R.a./fm0ZuARV76i8pQ21aZBFzlKq', 'alex@mail.com', CURRENT_TIMESTAMP(0), 'TRADER', TRUE);

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
VALUES ('Пушка огонь!', 10, (SELECT id FROM dealer_stat.game_object WHERE id = 1), NULL, CURRENT_TIMESTAMP(0), TRUE),
       ('Лучший!!!', 9, (SELECT id FROM dealer_stat.game_object WHERE id = 1), NULL, CURRENT_TIMESTAMP(0), TRUE),
       ('х**ня это все', 1, (SELECT id FROM dealer_stat.game_object WHERE id = 1), NULL, CURRENT_TIMESTAMP(0), FALSE),
       ('Такой себе...', 3, (SELECT id FROM dealer_stat.game_object WHERE id = 2), NULL, CURRENT_TIMESTAMP(0), TRUE),
       ('Мне понравился', 6, (SELECT id FROM dealer_stat.game_object WHERE id = 3), NULL, CURRENT_TIMESTAMP(0), TRUE),
       ('Переходим ко мне!', 8, (SELECT id FROM dealer_stat.game_object WHERE id = 4),
        (SELECT id FROM dealer_stat.user u WHERE u.email = 'anna@mail.com'), CURRENT_TIMESTAMP(0), TRUE),
       ('МООЩЩЩ!!!', 7, (SELECT id FROM dealer_stat.game_object WHERE id = 5), NULL, CURRENT_TIMESTAMP(0), TRUE);
