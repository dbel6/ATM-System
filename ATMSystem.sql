-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 14, 2026 at 10:08 AM
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
-- Database: `ATMSystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `Account`
--

CREATE TABLE `Account` (
  `accountID` int(10) NOT NULL,
  `customerID` int(10) NOT NULL,
  `type` enum('Checking','Saving') NOT NULL,
  `balance` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Account`
--

INSERT INTO `Account` (`accountID`, `customerID`, `type`, `balance`) VALUES
(1, 1, 'Checking', 798.00),
(2, 1, 'Saving', 800.00),
(3, 4, 'Checking', 400.00),
(4, 5, 'Saving', 9908.00),
(5, 1, 'Checking', 15838.00),
(7, 1, 'Checking', 68379.00),
(9, 8, 'Saving', 78234.00);

-- --------------------------------------------------------

--
-- Table structure for table `Card`
--

CREATE TABLE `Card` (
  `cardNumber` bigint(20) NOT NULL,
  `accountID` int(10) NOT NULL,
  `pin` varchar(4) NOT NULL,
  `expirationDate` date NOT NULL,
  `cvc` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Card`
--

INSERT INTO `Card` (`cardNumber`, `accountID`, `pin`, `expirationDate`, `cvc`) VALUES
(1, 1, '1234', '2027-03-02', 234),
(3478234621356, 2, '4527', '2027-06-06', 6745445),
(4324221342134, 1, '4343', '2025-01-01', 343),
(66436452654723, 3, '3458', '2030-07-25', 554),
(1234567890123456, 1, '1234', '2027-12-31', 123),
(4754536344566865, 5, '5354', '2030-05-06', 878);

-- --------------------------------------------------------

--
-- Table structure for table `Customer`
--

CREATE TABLE `Customer` (
  `customerID` int(10) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `address` text NOT NULL,
  `phone` varchar(18) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Customer`
--

INSERT INTO `Customer` (`customerID`, `firstname`, `lastname`, `address`, `phone`, `email`) VALUES
(1, 'Sam', 'Delaney', '12 Fairgreen View', '0853453234', 'sd@email.com'),
(5, 'gsdfgsfga', 'pppp', 'pppp31212rewdewq', '08765440', 'p@p.fdfs'),
(8, 'Qdfbsajhf', 'aadfadsdaf', 'akdgkagdha', '32412222', 'hjdjgdf@ndjasdf.dafas'),
(9, 'asdafda', 'lkajfjahsdflhas', 'ahkajfdjsd', '3198433726', '1afasdFSA@afasdf.sfasd'),
(10, 'aafasfdasdafasd', 'aafAfdsa', 'wfFAdfsfwrfrefsrf', '445234353', 'dsfsd@sfs.dsfsfs'),
(12, 'jhadskfjdzhjf', 'dfhkajdgfkjaj', '2352 dasfkhsadu', '52634642236', 'ahfkah3553@agsag.fjhj'),
(14, 'Liam', 'Smith', '12 Fairgreen View', '747687628', 'liamsmith@email.com');

-- --------------------------------------------------------

--
-- Table structure for table `Transaction`
--

CREATE TABLE `Transaction` (
  `transactionID` int(10) NOT NULL,
  `accountID` int(10) NOT NULL,
  `type` enum('Withdraw','Deposit','Transfer') NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `dateOfTransaction` date NOT NULL,
  `targetAccountID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Transaction`
--

INSERT INTO `Transaction` (`transactionID`, `accountID`, `type`, `amount`, `dateOfTransaction`, `targetAccountID`) VALUES
(1, 1, 'Transfer', 100.00, '2026-04-18', 2),
(2, 1, 'Transfer', 100.00, '2026-04-18', 4),
(4, 7, 'Transfer', 4654.00, '2026-04-21', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Account`
--
ALTER TABLE `Account`
  ADD PRIMARY KEY (`accountID`);

--
-- Indexes for table `Card`
--
ALTER TABLE `Card`
  ADD PRIMARY KEY (`cardNumber`);

--
-- Indexes for table `Customer`
--
ALTER TABLE `Customer`
  ADD PRIMARY KEY (`customerID`);

--
-- Indexes for table `Transaction`
--
ALTER TABLE `Transaction`
  ADD PRIMARY KEY (`transactionID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Account`
--
ALTER TABLE `Account`
  MODIFY `accountID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `Customer`
--
ALTER TABLE `Customer`
  MODIFY `customerID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `Transaction`
--
ALTER TABLE `Transaction`
  MODIFY `transactionID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
