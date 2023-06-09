/*
Script for the fill the initial register of dbusersrole
*/
USE dbusers;

INSERT INTO role (id, description, name) VALUES ('1', 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO role (id, description, name) VALUES ('2', 'ROLE_OWNER', 'ROLE_OWNER');
INSERT INTO role (id, description, name) VALUES ('3', 'ROLE_EMPLOYEE', 'ROLE_EMPLOYEE');
INSERT INTO role (id, description, name) VALUES ('4', 'ROLE_CUSTOMER', 'ROLE_CUSTOMER');

INSERT INTO user (id, name, last_Name, number_document, phone, date_birth, email, password, id_role)
VALUES (1, 'nameADMIN', 'lastNameADMIN', '9999999999', '+573149999999', STR_TO_DATE('29-03-1999', '%d-%m-%Y'), 'ADMIN@ADMIN.COM', '$2a$10$GlsGSNhkbVon6ZOSNMptOu5RikedRzlCAhMa7YpwvUSS0c88WT99S', 1);
