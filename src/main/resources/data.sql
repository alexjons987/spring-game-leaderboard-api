INSERT INTO roles (id, name)
    VALUES  (1, 'ADMIN'),
            (2, 'USER');

INSERT INTO users (id, username, password, score)
    VALUES  (1, "admin", "$2a$12$OVQyV8RPN221Hzy2qJxzcuDJ0dlFGL.Zb27Oxxv5XrA9QT3pVe/QO", 0),
            (2, "alex", "$2a$12$XGhgKF7tu7uS7J0Gp8i8eOjoyBaSz3tmbaiq6JPaap2vFVEVtIWki", 444),
            (3, "depi", "$2a$12$EFbEqwJ6h83kmQBP9jdQi.8wRt0V4I9o/90okKn9R2wFGCWy4nTV.", 702);

INSERT INTO user_roles (user_id, role_id)
    VALUES  (1, 1),
            (1, 2),
            (2, 2),
            (3, 2);

INSERT INTO sessions (id, user_id, score, duration)
    VALUES  (1, 2, 123, 300),
            (2, 2, 321, 400),
            (3, 3, 123, 300),
            (4, 3, 234, 350),
            (5, 3, 345, 425);

INSERT INTO achievements (id, name, description)
    VALUES  (1, "First Blood", "Play your first session."),
            (2, "Damage Dealer", "Acquire over 1000 total score."),
            (3, "NET Master", "Acquire >= 1000 score in a single session.");

INSERT INTO user_achievements (user_id, achievement_id)
    VALUES  (2, 1),
            (3, 1);
