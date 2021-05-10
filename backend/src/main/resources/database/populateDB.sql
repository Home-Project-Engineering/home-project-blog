INSERT INTO "role" (name) VALUES ('ADMIN');
INSERT INTO "role" (name) VALUES ('MODERATOR');
INSERT INTO "role" (name) VALUES ('BLOGGER');

INSERT INTO "user" (name, first_name, last_name, email, password, role_id) VALUES ('Tertey', 'Vladyslav', 'Frolov', 'tertey7@mail.com', '$2a$12$jnDONqLCCjSIz3tbG6SS.uLRX4wdpxWw2V23JRr0ludGcWWuz4phS', '1');
INSERT INTO "user" (name, first_name, last_name, email, password, role_id) VALUES ('Proselit', 'Evhenii', 'Suleimanov', 'proselit@mail.com', '$2a$12$jnDONqLCCjSIz3tbG6SS.uLRX4wdpxWw2V23JRr0ludGcWWuz4phS', '2');
INSERT INTO "user" (name, first_name, last_name, email, password, role_id) VALUES ('CheGevara', 'Roman', 'Mohyla', 'chegevara@mail.com', '$2a$12$jnDONqLCCjSIz3tbG6SS.uLRX4wdpxWw2V23JRr0ludGcWWuz4phS', '3');