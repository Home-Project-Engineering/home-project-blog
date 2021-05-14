INSERT INTO "role" (id, name) VALUES (1,'ADMIN');
INSERT INTO "role" (id, name) VALUES (2, 'MODERATOR');
INSERT INTO "role" (id, name) VALUES (3, 'BLOGGER');

INSERT INTO "user" (id, name, first_name, last_name, email, password, role_id)
VALUES (1, 'Tertey', 'Vladyslav', 'Frolov', 'tertey7@mail.com', '$2a$12$jnDONqLCCjSIz3tbG6SS.uLRX4wdpxWw2V23JRr0ludGcWWuz4phS', '1');
INSERT INTO "user" (id, name, first_name, last_name, email, password, role_id)
VALUES (2, 'Proselit', 'Evhenii', 'Suleimanov', 'proselit@mail.com', '$2a$12$jnDONqLCCjSIz3tbG6SS.uLRX4wdpxWw2V23JRr0ludGcWWuz4phS', '2');
INSERT INTO "user" (id, name, first_name, last_name, email, password, role_id)
VALUES (3, 'CheGevara', 'Roman', 'Mohyla', 'chegevara@mail.com', '$2a$12$jnDONqLCCjSIz3tbG6SS.uLRX4wdpxWw2V23JRr0ludGcWWuz4phS', '3');