-- --------------------------------------------------------
--          "TO DO" application
-- --------------------------------------------------------
-- --------------------------------------------------------
--          MESSAGES
-- --------------------------------------------------------

CREATE TABLE `unlock_request_messages` (
    `message_id` int(11) NOT NULL,
    `user_id` int(11) NOT NULL,
    `user_name` varchar(255),
    `message_body` varchar(600)
);
--
-- Indexes for table `unlock_request_messages`
--
ALTER TABLE `unlock_request_messages`
    ADD PRIMARY KEY (`message_id`);
--
-- AUTO_INCREMENT for table `unlock_request_messages`
--
ALTER TABLE `unlock_request_messages`
ALTER COLUMN `message_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------
--          USERS
-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `credentials_id` int(11) NOT NULL,
  `personal_information_id` int(11) NOT NULL,
  `profile_enable` boolean(1) NOT NULL
);
--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  ALTER COLUMN `user_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `personal_information`
--

CREATE TABLE `personal_information` (
  `personal_information_id` int(11) NOT NULL,
  `first_name` varchar(255),
  `last_name` varchar(255)
);
--
-- Indexes for table `personal_information`
--
ALTER TABLE `personal_information`
  ADD PRIMARY KEY (`personal_information_id`);
--
-- AUTO_INCREMENT for table `personal_information`
--
ALTER TABLE `personal_information`
  ALTER COLUMN `personal_information_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `credentials`
--

CREATE TABLE `credentials` (
  `credentials_id` int(11) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
);
--
-- Indexes for table `credentials`
--
ALTER TABLE `credentials`
  ADD PRIMARY KEY (`credentials_id`);
--
-- AUTO_INCREMENT for table `credentials`
--
ALTER TABLE `credentials`
  ALTER COLUMN `credentials_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) NOT NULL
);
--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);
--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  ALTER COLUMN `role_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `roles_map`
--

CREATE TABLE `roles_map` (
  `roles_map_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
);
--
-- Indexes for table `roles_map`
--
ALTER TABLE `roles_map`
  ADD PRIMARY KEY (`roles_map_id`);
--
-- AUTO_INCREMENT for table `roles_map`
--
ALTER TABLE `roles_map`
  ALTER COLUMN `roles_map_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------
--          TASKS
-- --------------------------------------------------------
--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `task_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `task_name` varchar(255),
  `description` varchar(600),
  `deadline` datetime NOT NULL,
  `fixed` boolean(1) NOT NULL,
  `in_basket` boolean(1) NOT NULL
);
--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`task_id`);
--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  ALTER COLUMN `task_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for table `tasks`
--

-- --------------------------------------------------------

--
-- Table structure for table `task_files`
--

CREATE TABLE `task_files` (
  `task_file_id` BIGINT NOT NULL,
  `file_name` varchar(255),
  `file` BLOB(10M)
);
--
-- Indexes for table `task_files`
--
ALTER TABLE `task_files`
  ADD PRIMARY KEY (`task_file_id`);
--
-- AUTO_INCREMENT for table `task_files`
--
-- ALTER TABLE `task_files`
--   ALTER COLUMN `task_file_id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `collaborators`
--
--
--CREATE TABLE `collaborators` (
--  `collaborator_id` int(11) NOT NULL,
--  `task_id` int(11) NOT NULL,
--  `user_id` int(11) NOT NULL
--);
----
---- Indexes for table `collaborators`
----
--ALTER TABLE `collaborators`
--  ADD PRIMARY KEY (`collaborator_id`);
----
---- AUTO_INCREMENT for table `collaborators`
----
--ALTER TABLE `collaborators`
--  ALTER COLUMN `collaborator_id` int(11) NOT NULL AUTO_INCREMENT;
----
---- Constraints for table `collaborators`
----

-- --------------------------------------------------------
--                          CONSTRAINTS
-- --------------------------------------------------------

--
-- Constraints for table `collaborators`
--
--ALTER TABLE `collaborators`
--  ADD CONSTRAINT `FK_Collaborators` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
--ALTER TABLE `collaborators`
--  ADD CONSTRAINT `FK_Task_collaborators` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`task_id`);
--
-- Constraints for table `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `FK_Users_task` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
--
-- Constraints for table `task_files`
--
-- ALTER TABLE `task_files`
--     ADD CONSTRAINT `FK_Task_files` FOREIGN KEY (`task_id`) REFERENCES `tasks` (`task_id`);
--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK_credentials` FOREIGN KEY (`credentials_id`) REFERENCES `credentials` (`credentials_id`);
ALTER TABLE `users`
  ADD CONSTRAINT `FK_Personal_information` FOREIGN KEY (`personal_information_id`) REFERENCES `personal_information` (`personal_information_id`);

--
-- Constraints for table `roles_map`
--deleted
ALTER TABLE `roles_map`
  ADD CONSTRAINT `FK_role_map_to_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);
ALTER TABLE `roles_map`
  ADD CONSTRAINT `FK_roles_map_to_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

