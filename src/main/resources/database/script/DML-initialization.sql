-- --------------------------------------------------------
--          "TO DO" application
-- --------------------------------------------------------

-- --------------------------------------------------------
--          USERS
-- --------------------------------------------------------

--
-- Data for table `roles`
--

INSERT INTO `roles` (`role`) VALUES ('USER_ROLE');
INSERT INTO `roles` (`role`) VALUES ('ADMIN_ROLE');

-- --------------------------------------------------------


--
-- Data for table `personal_information`
--

INSERT INTO personal_information (first_name, last_name) VALUES ('Andrey', 'Dmitriev');
INSERT INTO personal_information (first_name, last_name) VALUES ('Vina', 'McCowan');
INSERT INTO personal_information (first_name, last_name) VALUES ('Erv', 'Yeats');
INSERT INTO personal_information (first_name, last_name) VALUES ('Yuma', 'Lewzey');
INSERT INTO personal_information (first_name, last_name) VALUES ('Gardner', 'Tizzard');
INSERT INTO personal_information (first_name, last_name) VALUES ('Alvy', 'Simonnin');
INSERT INTO personal_information (first_name, last_name) VALUES ('Vitia', 'Walas');
INSERT INTO personal_information (first_name, last_name) VALUES ('Mathilde', 'Le Bosse');
INSERT INTO personal_information (first_name, last_name) VALUES ('Wally', 'Torricina');
INSERT INTO personal_information (first_name, last_name) VALUES ('Simeon', 'Belhome');

-- --------------------------------------------------------

--
-- Table structure for table `credentials`
--

INSERT INTO credentials (login, password) VALUES ('user', 'pass');
INSERT INTO credentials (login, password) VALUES ('admin', 'pass');
INSERT INTO credentials (login, password) VALUES ('user1', 'OypZ5Qdjb');
INSERT INTO credentials (login, password) VALUES ('user2', 'ZNpoafWzXkX');
INSERT INTO credentials (login, password) VALUES ('user3', 'iICPFhu');
INSERT INTO credentials (login, password) VALUES ('user4', 'B2SWfGyiKQPB');
INSERT INTO credentials (login, password) VALUES ('user5', 'uRRqzDMsM');
INSERT INTO credentials (login, password) VALUES ('user6', 'J2wtx7RW');
INSERT INTO credentials (login, password) VALUES ('user7', 'OyA4vT4nQOa');
INSERT INTO credentials (login, password) VALUES ('user8', 'QlH8W4F');


-- --------------------------------------------------------

--
-- Data for table `users`
--

INSERT INTO users (credentials_id, personal_information_id, profile_enable) VALUES (1, 1, true);
INSERT INTO users (credentials_id, personal_information_id, profile_enable) VALUES (2, 2, true);
INSERT INTO users (credentials_id, personal_information_id, profile_enable) VALUES (3, 3, true);
INSERT INTO users (credentials_id, personal_information_id, profile_enable) VALUES (4, 4, false);
INSERT INTO users (credentials_id, personal_information_id, profile_enable) VALUES (5, 5, true);
INSERT INTO users (credentials_id, personal_information_id, profile_enable) VALUES (6, 6, true);
INSERT INTO users (credentials_id, personal_information_id, profile_enable) VALUES (7, 7, true);
INSERT INTO users (credentials_id, personal_information_id, profile_enable) VALUES (8, 8, true);
INSERT INTO users (credentials_id, personal_information_id, profile_enable) VALUES (9, 9, false);
INSERT INTO users (credentials_id, personal_information_id, profile_enable) VALUES (10, 10, false);

-- --------------------------------------------------------
--          TASKS
-- --------------------------------------------------------


--
-- Data for table `tasks`
--

INSERT INTO tasks (user_id, deadline, task_name, fixed, in_basket, description) VALUES (1, '2021-07-17 13:54:18', 'task1', false, false, 'Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.');
INSERT INTO tasks (user_id, deadline, task_name, fixed, in_basket, description) VALUES (1, '2021-05-25 13:11:34', 'task2', true, false,'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.');
INSERT INTO tasks (user_id, deadline, task_name, fixed, in_basket, description) VALUES (3, '2021-10-02 20:20:28', 'task3', false, false, 'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.');
INSERT INTO tasks (user_id, deadline, task_name, fixed, in_basket, description) VALUES (4, '2021-08-27 12:25:14', 'task4', false, true, 'Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.');
INSERT INTO tasks (user_id, deadline, task_name, fixed, in_basket, description) VALUES (5, '2021-01-06 17:30:44', 'task5', false, true, 'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.');
INSERT INTO tasks (user_id, deadline, task_name, fixed, in_basket, description) VALUES (1, '2021-11-24 05:21:53', 'task6', false, true, 'Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.');
INSERT INTO tasks (user_id, deadline, task_name, fixed, in_basket, description) VALUES (7, '2021-03-09 00:11:56', 'task7', true, true, 'Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.');
INSERT INTO tasks (user_id, deadline, task_name, fixed, in_basket, description) VALUES (8, '2021-12-19 08:09:12', 'task8', true, false, 'Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.');
INSERT INTO tasks (user_id, deadline, task_name, fixed, in_basket, description) VALUES (9, '2021-09-04 11:36:04', 'task9', true, false, 'Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.');
INSERT INTO tasks (user_id, deadline, task_name, fixed, in_basket, description) VALUES (10, '2021-10-30 01:33:08', 'task10', true, true, 'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.');


-- --------------------------------------------------------


--
-- Data for table `collaborators`
--

--
--INSERT INTO collaborators (task_id, user_id) VALUES (5, 9);
--INSERT INTO collaborators (task_id, user_id) VALUES (7, 9);
--INSERT INTO collaborators (task_id, user_id) VALUES (6, 4);
--INSERT INTO collaborators (task_id, user_id) VALUES (3, 1);
--INSERT INTO collaborators (task_id, user_id) VALUES (8, 1);
--INSERT INTO collaborators (task_id, user_id) VALUES (5, 8);
--INSERT INTO collaborators (task_id, user_id) VALUES (10, 10);
--INSERT INTO collaborators (task_id, user_id) VALUES (6, 7);
--INSERT INTO collaborators (task_id, user_id) VALUES (1, 3);
--INSERT INTO collaborators (task_id, user_id) VALUES (6, 5);


-- --------------------------------------------------------


--
-- Data for table `roles_map`
--


INSERT INTO roles_map (role_id, user_id) VALUES (1, 1);
INSERT INTO roles_map (role_id, user_id) VALUES (2, 2);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 2);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 3);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 4);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 5);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 6);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 7);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 8);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 9);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 10);



-- INSERT INTO task_information (description, deadline) VALUES ('Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.', '2020-07-17 13:54:18');
-- INSERT INTO task_information (description, deadline) VALUES ('','2020-05-25 13:11:34');
-- INSERT INTO task_information (description, deadline) VALUES ('','2020-10-02 20:20:28');
-- INSERT INTO task_information (description, deadline) VALUES ('','2020-08-27 12:25:14');
-- INSERT INTO task_information (description, deadline) VALUES ('In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.','2021-01-06 17:30:44');
-- INSERT INTO task_information (description, deadline) VALUES ('Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.','2020-11-24 05:21:53');
-- INSERT INTO task_information (description, deadline) VALUES ('Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.','2020-03-09 00:11:56');
-- INSERT INTO task_information (description, deadline) VALUES ('','2020-12-19 08:09:12');
-- INSERT INTO task_information (description, deadline) VALUES ('Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.','2020-09-04 11:36:04');
-- INSERT INTO task_information (description, deadline) VALUES ('Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.','2020-10-30 01:33:08')
