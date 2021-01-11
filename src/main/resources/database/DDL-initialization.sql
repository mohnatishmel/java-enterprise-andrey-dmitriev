-- --------------------------------------------------------
--          "TO DO" application
-- --------------------------------------------------------

-- --------------------------------------------------------
--          USERS
-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `authentication_id` int(11) NOT NULL,
  `personal_information_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `profile_enable` tinyint(1) NOT NULL
);
--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  ALTER COLUMN `id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `personal_information`
--

CREATE TABLE `personal_information` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL
);
--
-- Indexes for table `personal_information`
--
ALTER TABLE `personal_information`
  ADD PRIMARY KEY (`id`);
--
-- AUTO_INCREMENT for table `personal_information`
--
ALTER TABLE `personal_information`
  ALTER COLUMN `id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `authenticate`
--

CREATE TABLE `authenticate` (
  `id` int(11) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
);
--
-- Indexes for table `authenticate`
--
ALTER TABLE `authenticate`
  ADD PRIMARY KEY (`id`);
--
-- AUTO_INCREMENT for table `authenticate`
--
ALTER TABLE `authenticate`
  ALTER COLUMN `id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `role` varchar(255) NOT NULL
);
--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);
--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  ALTER COLUMN `id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------
--          TASKS
-- --------------------------------------------------------
--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `task_information_id` int(11) NOT NULL,
  `deadline` datetime NOT NULL,
  `fixed` tinyint(1) NOT NULL,
  `deleted` tinyint(1) NOT NULL
);
--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`);
--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  ALTER COLUMN `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for table `tasks`
--

-- --------------------------------------------------------

--
-- Table structure for table `task_information`
--

CREATE TABLE `task_information` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `file_path` varchar(255) NOT NULL
);
--
-- Indexes for table `task_information`
--
ALTER TABLE `task_information`
  ADD PRIMARY KEY (`id`);
--
-- AUTO_INCREMENT for table `task_information`
--
ALTER TABLE `task_information`
  ALTER COLUMN `id` int(11) NOT NULL AUTO_INCREMENT;

-- --------------------------------------------------------

--
-- Table structure for table `collaborators`
--

CREATE TABLE `collaborators` (
  `id` int(11) NOT NULL,
  `task_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
);
--
-- Indexes for table `collaborators`
--
ALTER TABLE `collaborators`
  ADD PRIMARY KEY (`id`);
--
-- AUTO_INCREMENT for table `collaborators`
--
ALTER TABLE `collaborators`
  ALTER COLUMN `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for table `collaborators`
--