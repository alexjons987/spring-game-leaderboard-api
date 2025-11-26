INSERT INTO roles (id, name)
    VALUES  (1, 'ADMIN'),
            (2, 'USER');

INSERT INTO users (id, username, password, score)
    VALUES  (1, "admin", "$2a$12$OVQyV8RPN221Hzy2qJxzcuDJ0dlFGL.Zb27Oxxv5XrA9QT3pVe/QO", 0);

INSERT INTO user_roles (user_id, role_id)
    VALUES  (1, 1),
            (1, 2);