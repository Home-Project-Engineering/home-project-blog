INSERT INTO "role" (id, name) VALUES (0, 'BLOGGER');
INSERT INTO "role" (id, name) VALUES (1, 'MODERATOR');
INSERT INTO "role" (id, name) VALUES (2, 'ADMIN');

INSERT INTO "user" (name, first_name, last_name, email, password, role_id) VALUES ('Tertey', 'Vladyslav', 'Frolov', 'tertey7@mail.com', '$2y$12$Whxnsf.Iti8wrLoOivdSPucdXIcILTZddQ0.bTz2rkyO29cEopspa', '0');
INSERT INTO "user" (name, first_name, last_name, email, password, role_id) VALUES ('Proselit', 'Evhenii', 'Suleimanov', 'proselit@mail.com', '$2y$12$Whxnsf.Iti8wrLoOivdSPucdXIcILTZddQ0.bTz2rkyO29cEopspa', '1');
INSERT INTO "user" (name, first_name, last_name, email, password, role_id) VALUES ('CheGevara', 'Roman', 'Mohyla', 'chegevara@mail.com', '$2y$12$Whxnsf.Iti8wrLoOivdSPucdXIcILTZddQ0.bTz2rkyO29cEopspa', '2');