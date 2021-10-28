-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hostiteľ: 127.0.0.1
-- Čas generovania: Št 28.Okt 2021, 12:45
-- Verzia serveru: 10.1.37-MariaDB
-- Verzia PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáza: `blog`
--

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `comment`
--

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Sťahujem dáta pre tabuľku `comment`
--

INSERT INTO `comment` (`id`, `body`, `created_at`, `post_id`, `user_id`) VALUES
(1, '<p>Test</p>', '2021-10-28 08:42:57', 1, 1),
(2, '<p>WOW.</p>', '2021-10-28 09:23:32', 1, 2);

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `post`
--

CREATE TABLE `post` (
  `id` bigint(20) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `content` varchar(10000) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `title_img` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Sťahujem dáta pre tabuľku `post`
--

INSERT INTO `post` (`id`, `author`, `content`, `created`, `title`, `title_img`) VALUES
(1, 'marek', '<p>fwefewfewfewfew</p>', '2021-10-28 08:24:37', 'Test Test Test Tesss', '62-900x500.jpg'),
(2, 'marek', '<p>few</p>', '2021-10-28 08:34:11', 'few', '1043-900x500.jpg'),
(3, 'marek', '<p>fewfewfew</p>', '2021-10-28 08:34:53', 'ewfew', '944-900x500.jpg'),
(4, 'marek', '<p>rgergereg</p>', '2021-10-28 08:35:05', 'rbregrgre', '965-900x500.jpg');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `number_of_posts` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  `surename` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Sťahujem dáta pre tabuľku `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `note`, `number_of_posts`, `password`, `profile_picture`, `register_date`, `surename`, `username`) VALUES
(1, 'm.sulhanek@gmail.com', 'marek', 'Now or never!', 4, '$2a$10$P6.Jmss2voYjwjpi8NmrwO2NSoWKtEW9DfXAHMSuDJrXrpXLIn.Im', 'avatar.png', '2021-10-28 07:56:01', 'sulhanek', 'marek'),
(2, 'test@test.sk', 'test', 'No text', 0, '$2a$10$W6MJ.uv2c2KNes4Ga/4nE.fUTJWjOqItOZHDhCwIuAYYmufkzE1CG', 'no-image.jpg', '2021-10-28 09:23:21', 'test', 'test');

--
-- Kľúče pre exportované tabuľky
--

--
-- Indexy pre tabuľku `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs1slvnkuemjsq2kj4h3vhx7i1` (`post_id`),
  ADD KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`);

--
-- Indexy pre tabuľku `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`);

--
-- Indexy pre tabuľku `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pre exportované tabuľky
--

--
-- AUTO_INCREMENT pre tabuľku `comment`
--
ALTER TABLE `comment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pre tabuľku `post`
--
ALTER TABLE `post`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pre tabuľku `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Obmedzenie pre exportované tabuľky
--

--
-- Obmedzenie pre tabuľku `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKs1slvnkuemjsq2kj4h3vhx7i1` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
