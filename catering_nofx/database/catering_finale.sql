-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 09, 2024 at 06:05 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `catering`
--

-- --------------------------------------------------------

--
-- Table structure for table `Events`
--

CREATE TABLE `Events` (
  `id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `date_start` date DEFAULT NULL,
  `date_end` date DEFAULT NULL,
  `expected_participants` int(11) DEFAULT NULL,
  `organizer_id` int(11) NOT NULL,
  `chef_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `Events`
--

INSERT INTO `Events` (`id`, `name`, `date_start`, `date_end`, `expected_participants`, `organizer_id`, `chef_id`) VALUES
(1, 'Convegno Agile Community', '2020-09-25', '2020-09-25', 100, 2, 2),
(2, 'Compleanno di Manuela', '2020-08-13', '2020-08-13', 25, 2, 2),
(3, 'Fiera del Sedano Rapa', '2020-10-02', '2020-10-04', 400, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `MenuFeatures`
--

CREATE TABLE `MenuFeatures` (
  `menu_id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL DEFAULT '',
  `value` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `MenuFeatures`
--

INSERT INTO `MenuFeatures` (`menu_id`, `name`, `value`) VALUES
(80, 'Richiede cuoco', 0),
(80, 'Buffet', 0),
(80, 'Richiede cucina', 0),
(80, 'Finger food', 0),
(80, 'Piatti caldi', 0),
(82, 'Richiede cuoco', 0),
(82, 'Buffet', 0),
(82, 'Richiede cucina', 0),
(82, 'Finger food', 0),
(82, 'Piatti caldi', 0),
(86, 'Richiede cuoco', 0),
(86, 'Buffet', 0),
(86, 'Richiede cucina', 0),
(86, 'Finger food', 0),
(86, 'Piatti caldi', 0),
(89, 'Richiede cuoco', 1),
(89, 'Buffet', 1),
(89, 'Richiede cucina', 1),
(89, 'Finger food', 1),
(89, 'Piatti caldi', 1),
(90, 'Richiede cuoco', 1),
(90, 'Buffet', 1),
(90, 'Richiede cucina', 1),
(90, 'Finger food', 1),
(90, 'Piatti caldi', 1),
(92, 'Richiede cuoco', 1),
(92, 'Buffet', 1),
(92, 'Richiede cucina', 1),
(92, 'Finger food', 1),
(92, 'Piatti caldi', 1),
(93, 'Richiede cuoco', 1),
(93, 'Buffet', 1),
(93, 'Richiede cucina', 1),
(93, 'Finger food', 1),
(93, 'Piatti caldi', 1),
(94, 'Richiede cuoco', 1),
(94, 'Buffet', 1),
(94, 'Richiede cucina', 1),
(94, 'Finger food', 1),
(94, 'Piatti caldi', 1),
(96, 'Richiede cuoco', 1),
(96, 'Buffet', 1),
(96, 'Richiede cucina', 1),
(96, 'Finger food', 1),
(96, 'Piatti caldi', 1),
(98, 'Richiede cuoco', 1),
(98, 'Buffet', 1),
(98, 'Richiede cucina', 1),
(98, 'Finger food', 1),
(98, 'Piatti caldi', 1),
(99, 'Richiede cuoco', 1),
(99, 'Buffet', 1),
(99, 'Richiede cucina', 1),
(99, 'Finger food', 1),
(99, 'Piatti caldi', 1);

-- --------------------------------------------------------

--
-- Table structure for table `MenuItems`
--

CREATE TABLE `MenuItems` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `section_id` int(11) DEFAULT NULL,
  `description` tinytext DEFAULT NULL,
  `recipe_id` int(11) NOT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `MenuItems`
--

INSERT INTO `MenuItems` (`id`, `menu_id`, `section_id`, `description`, `recipe_id`, `position`) VALUES
(96, 80, 0, 'Croissant vuoti', 9, 0),
(97, 80, 0, 'Croissant alla marmellata', 9, 1),
(98, 80, 0, 'Pane al cioccolato mignon', 10, 2),
(99, 80, 0, 'Panini al latte con prosciutto crudo', 12, 4),
(100, 80, 0, 'Panini al latte con prosciutto cotto', 12, 5),
(101, 80, 0, 'Panini al latte con formaggio spalmabile alle erbe', 12, 6),
(102, 80, 0, 'Girelle all\'uvetta mignon', 11, 3),
(103, 82, 0, 'Biscotti', 13, 1),
(104, 82, 0, 'Lingue di gatto', 14, 2),
(105, 82, 0, 'Bigné alla crema', 15, 3),
(106, 82, 0, 'Bigné al caffè', 15, 4),
(107, 82, 0, 'Pizzette', 16, 5),
(108, 82, 0, 'Croissant al prosciutto crudo mignon', 9, 6),
(109, 82, 0, 'Tramezzini tonno e carciofini mignon', 17, 7),
(112, 86, 41, 'Vitello tonnato', 1, 0),
(113, 86, 41, 'Carpaccio di spada', 2, 1),
(114, 86, 41, 'Alici marinate', 3, 2),
(115, 86, 42, 'Penne alla messinese', 5, 0),
(116, 86, 42, 'Risotto alla zucca', 20, 1),
(117, 86, 43, 'Salmone al forno', 8, 0),
(118, 86, 44, 'Sorbetto al limone', 18, 0),
(119, 86, 44, 'Torta Saint Honoré', 19, 1),
(121, 89, 47, 'Vitello tonnato', 1, 0),
(122, 89, 47, 'Carpaccio di spada', 2, 1),
(123, 89, 47, 'Alici marinate', 3, 2),
(124, 89, 48, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(125, 89, 48, 'Salmone al forno', 8, 1),
(126, 89, 0, 'Insalata di riso', 4, 0),
(127, 89, 0, 'Penne al sugo di baccalà', 5, 1),
(128, 90, 49, 'Vitello tonnato', 1, 0),
(129, 90, 49, 'Carpaccio di spada', 2, 1),
(130, 90, 49, 'Alici marinate', 3, 2),
(131, 90, 50, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(132, 90, 50, 'Salmone al forno', 8, 1),
(133, 90, 0, 'Insalata di riso', 4, 0),
(134, 90, 0, 'Penne al sugo di baccalà', 5, 1),
(142, 92, 53, 'Vitello tonnato', 1, 0),
(143, 92, 53, 'Carpaccio di spada', 2, 1),
(144, 92, 53, 'Alici marinate', 3, 2),
(145, 92, 54, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(146, 92, 54, 'Salmone al forno', 8, 1),
(147, 92, 0, 'Insalata di riso', 4, 0),
(148, 92, 0, 'Penne al sugo di baccalà', 5, 1),
(149, 93, 55, 'Vitello tonnato', 1, 0),
(150, 93, 55, 'Carpaccio di spada', 2, 1),
(151, 93, 55, 'Alici marinate', 3, 2),
(152, 93, 56, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(153, 93, 56, 'Salmone al forno', 8, 1),
(154, 93, 0, 'Insalata di riso', 4, 0),
(155, 93, 0, 'Penne al sugo di baccalà', 5, 1),
(156, 94, 57, 'Vitello tonnato', 1, 0),
(157, 94, 57, 'Carpaccio di spada', 2, 1),
(158, 94, 57, 'Alici marinate', 3, 2),
(159, 94, 58, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(160, 94, 58, 'Salmone al forno', 8, 1),
(161, 94, 0, 'Insalata di riso', 4, 0),
(162, 94, 0, 'Penne al sugo di baccalà', 5, 1),
(170, 96, 61, 'Vitello tonnato', 1, 0),
(171, 96, 61, 'Carpaccio di spada', 2, 1),
(172, 96, 61, 'Alici marinate', 3, 2),
(173, 96, 62, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(174, 96, 62, 'Salmone al forno', 8, 1),
(175, 96, 0, 'Insalata di riso', 4, 0),
(176, 96, 0, 'Penne al sugo di baccalà', 5, 1),
(184, 98, 65, 'Vitello tonnato', 1, 0),
(185, 98, 65, 'Carpaccio di spada', 2, 1),
(186, 98, 65, 'Alici marinate', 3, 2),
(187, 98, 66, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(188, 98, 66, 'Salmone al forno', 8, 1),
(189, 98, 0, 'Insalata di riso', 4, 0),
(190, 98, 0, 'Penne al sugo di baccalà', 5, 1),
(191, 99, 67, 'Vitello tonnato', 1, 0),
(192, 99, 67, 'Carpaccio di spada', 2, 1),
(193, 99, 67, 'Alici marinate', 3, 2),
(194, 99, 68, 'Hamburger con bacon e cipolla caramellata', 7, 0),
(195, 99, 68, 'Salmone al forno', 8, 1),
(196, 99, 0, 'Insalata di riso', 4, 0),
(197, 99, 0, 'Penne al sugo di baccalà', 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Menus`
--

CREATE TABLE `Menus` (
  `id` int(11) NOT NULL,
  `title` tinytext DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `published` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `Menus`
--

INSERT INTO `Menus` (`id`, `title`, `owner_id`, `published`) VALUES
(80, 'Coffee break mattutino', 2, 1),
(82, 'Coffee break pomeridiano', 2, 1),
(86, 'Cena di compleanno pesce', 3, 1),
(89, 'Titolo Nuovo', 2, 1),
(90, 'Titolo Nuovo', 2, 1),
(92, 'Titolo Nuovo', 2, 1),
(93, 'Titolo Nuovo', 2, 1),
(94, 'Titolo Nuovo', 2, 1),
(96, 'Titolo Nuovo', 2, 1),
(98, 'Titolo Nuovo', 2, 1),
(99, 'Titolo Nuovo', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `MenuSections`
--

CREATE TABLE `MenuSections` (
  `id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  `name` tinytext DEFAULT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `MenuSections`
--

INSERT INTO `MenuSections` (`id`, `menu_id`, `name`, `position`) VALUES
(41, 86, 'Antipasti', 0),
(42, 86, 'Primi', 1),
(43, 86, 'Secondi', 2),
(44, 86, 'Dessert', 3),
(45, 87, 'Antipasti', 0),
(47, 89, 'Antipasti', 0),
(48, 89, 'Secondi', 1),
(49, 90, 'Antipasti', 0),
(50, 90, 'Secondi', 1),
(53, 92, 'Antipasti', 0),
(54, 92, 'Secondi', 1),
(55, 93, 'Antipasti', 0),
(56, 93, 'Secondi', 1),
(57, 94, 'Antipasti', 0),
(58, 94, 'Secondi', 1),
(61, 96, 'Antipasti', 0),
(62, 96, 'Secondi', 1),
(65, 98, 'Antipasti', 0),
(66, 98, 'Secondi', 1),
(67, 99, 'Antipasti', 0),
(68, 99, 'Secondi', 1);

-- --------------------------------------------------------

--
-- Table structure for table `Recipes`
--

CREATE TABLE `Recipes` (
  `id` int(11) NOT NULL,
  `name` tinytext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `Recipes`
--

INSERT INTO `Recipes` (`id`, `name`) VALUES
(1, 'Vitello tonnato'),
(2, 'Carpaccio di spada'),
(3, 'Alici marinate'),
(4, 'Insalata di riso'),
(5, 'Penne al sugo di baccalà'),
(6, 'Pappa al pomodoro'),
(7, 'Hamburger con bacon e cipolla caramellata'),
(8, 'Salmone al forno'),
(9, 'Croissant'),
(10, 'Pane al cioccolato'),
(11, 'Girelle all\'uvetta'),
(12, 'Panini al latte'),
(13, 'Biscotti di pasta frolla'),
(14, 'Lingue di gatto'),
(15, 'Bigné farciti'),
(16, 'Pizzette'),
(17, 'Tramezzini'),
(18, 'Sorbetto al limone'),
(19, 'Torta Saint Honoré'),
(20, 'Risotto alla zucca');

-- --------------------------------------------------------

--
-- Table structure for table `Roles`
--

CREATE TABLE `Roles` (
  `id` char(1) NOT NULL,
  `role` varchar(128) NOT NULL DEFAULT 'servizio'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `Roles`
--

INSERT INTO `Roles` (`id`, `role`) VALUES
('c', 'cuoco'),
('h', 'chef'),
('o', 'organizzatore'),
('s', 'servizio');

-- --------------------------------------------------------

--
-- Table structure for table `Services`
--

CREATE TABLE `Services` (
  `id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `proposed_menu_id` int(11) NOT NULL DEFAULT 0,
  `approved_menu_id` int(11) DEFAULT 0,
  `service_date` date DEFAULT NULL,
  `time_start` time DEFAULT NULL,
  `time_end` time DEFAULT NULL,
  `expected_participants` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `Services`
--

INSERT INTO `Services` (`id`, `event_id`, `name`, `proposed_menu_id`, `approved_menu_id`, `service_date`, `time_start`, `time_end`, `expected_participants`) VALUES
(1, 2, 'Cena', 86, 0, '2020-08-13', '20:00:00', '23:30:00', 25),
(2, 1, 'Coffee break mattino', 0, 80, '2020-09-25', '10:30:00', '11:30:00', 100),
(3, 1, 'Colazione di lavoro', 0, 0, '2020-09-25', '13:00:00', '14:00:00', 80),
(4, 1, 'Coffee break pomeriggio', 0, 82, '2020-09-25', '16:00:00', '16:30:00', 100),
(5, 1, 'Cena sociale', 0, 0, '2020-09-25', '20:00:00', '22:30:00', 40),
(6, 3, 'Pranzo giorno 1', 0, 0, '2020-10-02', '12:00:00', '15:00:00', 200),
(7, 3, 'Pranzo giorno 2', 0, 0, '2020-10-03', '12:00:00', '15:00:00', 300),
(8, 3, 'Pranzo giorno 3', 0, 0, '2020-10-04', '12:00:00', '15:00:00', 400);

-- --------------------------------------------------------

--
-- Table structure for table `Shifts`
--

CREATE TABLE `Shifts` (
  `id` int(11) NOT NULL,
  `startTime` datetime NOT NULL,
  `endTime` datetime NOT NULL,
  `deadline` datetime DEFAULT NULL,
  `place` varchar(128) NOT NULL,
  `shift_type` varchar(128) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Shifts`
--

INSERT INTO `Shifts` (`id`, `startTime`, `endTime`, `deadline`, `place`, `shift_type`) VALUES
(1, '2024-07-16 20:00:00', '2024-07-16 23:00:00', '2024-07-05 00:00:00', 'Cucina Via Verdi', 'servizio'),
(2, '2024-07-06 10:00:00', '2024-07-06 14:00:00', '2024-06-20 16:00:00', 'Downtown Abbey', 'cucina');

-- --------------------------------------------------------

--
-- Table structure for table `SummarySheets`
--

CREATE TABLE `SummarySheets` (
  `id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `SummarySheets`
--

INSERT INTO `SummarySheets` (`id`, `service_id`, `event_id`) VALUES
(11, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Tasks`
--

CREATE TABLE `Tasks` (
  `id` int(11) NOT NULL,
  `summarysheet_id` int(11) NOT NULL,
  `cook_id` int(11) DEFAULT NULL,
  `shift_id` int(11) DEFAULT NULL,
  `recipe_id` int(11) NOT NULL,
  `portions` int(11) DEFAULT NULL,
  `amount` varchar(128) DEFAULT NULL,
  `estimated_time` int(11) DEFAULT NULL,
  `completed` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Tasks`
--

INSERT INTO `Tasks` (`id`, `summarysheet_id`, `cook_id`, `shift_id`, `recipe_id`, `portions`, `amount`, `estimated_time`, `completed`) VALUES
(17, 11, 5, 2, 1, 50, '5 vassoi', 30, 1),
(18, 11, -1, -1, -1, 0, NULL, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `UserRoles`
--

CREATE TABLE `UserRoles` (
  `user_id` int(11) NOT NULL,
  `role_id` char(1) NOT NULL DEFAULT 's'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `UserRoles`
--

INSERT INTO `UserRoles` (`user_id`, `role_id`) VALUES
(1, 'o'),
(2, 'o'),
(2, 'h'),
(3, 'h'),
(4, 'h'),
(4, 'c'),
(5, 'c'),
(6, 'c'),
(7, 'c'),
(8, 's'),
(9, 's'),
(10, 's'),
(7, 's');

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `username` varchar(128) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`id`, `username`) VALUES
(1, 'Carlin'),
(2, 'Lidia'),
(3, 'Tony'),
(4, 'Marinella'),
(5, 'Guido'),
(6, 'Antonietta'),
(7, 'Paola'),
(8, 'Silvia'),
(9, 'Marco'),
(10, 'Piergiorgio');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Events`
--
ALTER TABLE `Events`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `MenuItems`
--
ALTER TABLE `MenuItems`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Menus`
--
ALTER TABLE `Menus`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `MenuSections`
--
ALTER TABLE `MenuSections`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Recipes`
--
ALTER TABLE `Recipes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Roles`
--
ALTER TABLE `Roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Services`
--
ALTER TABLE `Services`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Shifts`
--
ALTER TABLE `Shifts`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `SummarySheets`
--
ALTER TABLE `SummarySheets`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Tasks`
--
ALTER TABLE `Tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Tasks_Users_id_fk` (`cook_id`),
  ADD KEY `Tasks_Recipes_id_fk` (`recipe_id`),
  ADD KEY `Tasks_SummarySheets_id_fk` (`summarysheet_id`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Events`
--
ALTER TABLE `Events`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `MenuItems`
--
ALTER TABLE `MenuItems`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=198;

--
-- AUTO_INCREMENT for table `Menus`
--
ALTER TABLE `Menus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- AUTO_INCREMENT for table `MenuSections`
--
ALTER TABLE `MenuSections`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT for table `Recipes`
--
ALTER TABLE `Recipes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `Services`
--
ALTER TABLE `Services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `Shifts`
--
ALTER TABLE `Shifts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `SummarySheets`
--
ALTER TABLE `SummarySheets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `Tasks`
--
ALTER TABLE `Tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Tasks`
--
ALTER TABLE `Tasks`
  ADD CONSTRAINT `Tasks_SummarySheets_id_fk` FOREIGN KEY (`summarysheet_id`) REFERENCES `SummarySheets` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
