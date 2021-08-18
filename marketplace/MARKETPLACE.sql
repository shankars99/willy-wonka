-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2021 at 06:54 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `MARKETPLACE`
--

-- --------------------------------------------------------

--
-- Table structure for table `ACCOUNTS`
--

CREATE TABLE `ACCOUNTS` (
  `NAME` varchar(30) NOT NULL,
  `PWD` varchar(40) NOT NULL,
  `EMAIL` varchar(30) NOT NULL,
  `CARD` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ACCOUNTS`
--

INSERT INTO `ACCOUNTS` (`NAME`, `PWD`, `EMAIL`, `CARD`) VALUES
('dragon', 'af8978b1797b72acfff9595a5a2a373ec3d9106d', 'dragon@gmail.com', '1234-1234-1234-1234'),
('pegasus', '493aec791a7595dce622346edc7554e3711109ca', 'pegasus@gmail.com', '4321-4321-4321-4321');

-- --------------------------------------------------------

--
-- Table structure for table `FRIENDS`
--

CREATE TABLE `FRIENDS` (
  `NAME` varchar(30) NOT NULL,
  `FRIEND` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `FRIENDS`
--

INSERT INTO `FRIENDS` (`NAME`, `FRIEND`) VALUES
('dragon', 'pegasus'),
('pegasus', 'dragon');

-- --------------------------------------------------------

--
-- Table structure for table `PRODUCTS`
--

CREATE TABLE `PRODUCTS` (
  `TYPE` varchar(5) NOT NULL,
  `DATE` date NOT NULL,
  `NAME` varchar(30) NOT NULL,
  `COST` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `PRODUCTS`
--

INSERT INTO `PRODUCTS` (`TYPE`, `DATE`, `NAME`, `COST`) VALUES
('GAME', '2021-05-29', 'Raya : Last Dragon', '499'),
('MOVIE', '2021-05-28', 'Incredibles 2', '249'),
('GAME', '2021-05-27', 'Tomb Raider Returns', '499'),
('MOVIE', '2021-05-21', 'Smurfs 2', '249');

-- --------------------------------------------------------

--
-- Table structure for table `PURCHASES`
--

CREATE TABLE `PURCHASES` (
  `DATE` date NOT NULL,
  `NAME` varchar(40) NOT NULL,
  `PROD_NAME` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `PURCHASES`
--

INSERT INTO `PURCHASES` (`DATE`, `NAME`, `PROD_NAME`) VALUES
('2021-05-30', 'pegasus', 'Raya : Last Dragon'),
('2021-05-30', 'pegasus', 'Smurfs 2'),
('2021-05-30', 'dragon', 'Tomb Raider Returns');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
