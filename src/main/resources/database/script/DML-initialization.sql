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
-- Data for table `task_information`
--

INSERT INTO task_information (description, file_path) VALUES ('Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat.','');
INSERT INTO task_information (description, file_path) VALUES ('','');
INSERT INTO task_information (description, file_path) VALUES ('','');
INSERT INTO task_information (description, file_path) VALUES ('','');
INSERT INTO task_information (description, file_path) VALUES ('In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.','');
INSERT INTO task_information (description, file_path) VALUES ('Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum.','');
INSERT INTO task_information (description, file_path) VALUES ('Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.','');
INSERT INTO task_information (description, file_path) VALUES ('','');
INSERT INTO task_information (description, file_path) VALUES ('Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.','');
INSERT INTO task_information (description, file_path) VALUES ('Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi.','');

-- --------------------------------------------------------

--
-- Data for table `tasks`
--

INSERT INTO tasks (user_id, task_information_id, deadline, fixed, deleted) VALUES (1, 1, '2020-07-17 13:54:18', true, true);
INSERT INTO tasks (user_id, task_information_id, deadline, fixed, deleted) VALUES (1, 2, '2020-05-25 13:11:34', true, true);
INSERT INTO tasks (user_id, task_information_id, deadline, fixed, deleted) VALUES (3, 3, '2020-10-02 20:20:28', false, true);
INSERT INTO tasks (user_id, task_information_id, deadline, fixed, deleted) VALUES (4, 4, '2020-08-27 12:25:14', false, true);
INSERT INTO tasks (user_id, task_information_id, deadline, fixed, deleted) VALUES (5, 5, '2021-01-06 17:30:44', false, true);
INSERT INTO tasks (user_id, task_information_id, deadline, fixed, deleted) VALUES (1, 6, '2020-11-24 05:21:53', false, true);
INSERT INTO tasks (user_id, task_information_id, deadline, fixed, deleted) VALUES (7, 7, '2020-03-09 00:11:56', true, true);
INSERT INTO tasks (user_id, task_information_id, deadline, fixed, deleted) VALUES (8, 8, '2020-12-19 08:09:12', true, false);
INSERT INTO tasks (user_id, task_information_id, deadline, fixed, deleted) VALUES (9, 9, '2020-09-04 11:36:04', true, false);
INSERT INTO tasks (user_id, task_information_id, deadline, fixed, deleted) VALUES (10, 10, '2020-10-30 01:33:08', true, true);


-- --------------------------------------------------------


--
-- Data for table `collaborators`
--


INSERT INTO collaborators (task_id, user_id) VALUES (5, 9);
INSERT INTO collaborators (task_id, user_id) VALUES (7, 9);
INSERT INTO collaborators (task_id, user_id) VALUES (6, 4);
INSERT INTO collaborators (task_id, user_id) VALUES (3, 1);
INSERT INTO collaborators (task_id, user_id) VALUES (8, 1);
INSERT INTO collaborators (task_id, user_id) VALUES (5, 8);
INSERT INTO collaborators (task_id, user_id) VALUES (10, 10);
INSERT INTO collaborators (task_id, user_id) VALUES (6, 7);
INSERT INTO collaborators (task_id, user_id) VALUES (1, 3);
INSERT INTO collaborators (task_id, user_id) VALUES (6, 5);


-- --------------------------------------------------------


--
-- Data for table `roles_map`
--


INSERT INTO roles_map (role_id, user_id) VALUES (1, 1);
INSERT INTO roles_map (role_id, user_id) VALUES (2, 2);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 3);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 4);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 5);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 6);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 7);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 8);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 9);
INSERT INTO roles_map (role_id, user_id) VALUES (1, 10);