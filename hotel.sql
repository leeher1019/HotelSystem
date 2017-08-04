-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- 主機: 127.0.0.1
-- 產生時間： 2017-08-04 09:07:05
-- 伺服器版本: 5.7.14
-- PHP 版本： 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `hotel`
--

-- --------------------------------------------------------

--
-- 資料表結構 `book`
--

CREATE TABLE `book` (
  `bookId` int(11) NOT NULL,
  `roomType` varchar(255) DEFAULT NULL,
  `clientName` varchar(255) DEFAULT NULL,
  `clientPhone` varchar(255) DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `checkInDate` date DEFAULT NULL,
  `checkOutDate` date DEFAULT NULL,
  `memOrNot` varchar(255) DEFAULT NULL,
  `balancePayed` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表結構 `bossaccount`
--

CREATE TABLE `bossaccount` (
  `empId` int(5) NOT NULL,
  `password` int(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `bossaccount`
--

INSERT INTO `bossaccount` (`empId`, `password`) VALUES
(1, 123456);

-- --------------------------------------------------------

--
-- 資料表結構 `checkin`
--

CREATE TABLE `checkin` (
  `roomId` int(11) NOT NULL,
  `clientName` varchar(255) DEFAULT NULL,
  `clientPhone` varchar(255) DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `checkin`
--

INSERT INTO `checkin` (`roomId`, `clientName`, `clientPhone`, `clientId`) VALUES
(1, NULL, NULL, NULL),
(2, NULL, NULL, NULL),
(3, NULL, NULL, NULL),
(4, NULL, NULL, NULL),
(5, NULL, NULL, NULL),
(6, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- 資料表結構 `empdata`
--

CREATE TABLE `empdata` (
  `empId` int(11) NOT NULL,
  `empName` varchar(255) DEFAULT NULL,
  `empPhone` varchar(255) DEFAULT NULL,
  `empBirth` date DEFAULT NULL,
  `empTitle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `empdata`
--

INSERT INTO `empdata` (`empId`, `empName`, `empPhone`, `empBirth`, `empTitle`) VALUES
(1, 'Lee', '15161502222', '1993-10-10', '經理');

-- --------------------------------------------------------

--
-- 資料表結構 `emptitle`
--

CREATE TABLE `emptitle` (
  `empTitle` varchar(255) NOT NULL,
  `empPay` varchar(255) DEFAULT NULL,
  `empPermissions` varchar(45) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `emptitle`
--

INSERT INTO `emptitle` (`empTitle`, `empPay`, `empPermissions`) VALUES
('經理', '15000', 'Yes'),
('工讀生', '2000', 'No');

-- --------------------------------------------------------

--
-- 資料表結構 `empworktime`
--

CREATE TABLE `empworktime` (
  `Id` int(11) NOT NULL,
  `empId` varchar(255) DEFAULT NULL,
  `empDate` date DEFAULT NULL,
  `empTime_On` time DEFAULT NULL,
  `empTime_Off` time DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表結構 `member`
--

CREATE TABLE `member` (
  `memId` int(11) NOT NULL,
  `memName` varchar(255) DEFAULT NULL,
  `memPhone` varchar(255) DEFAULT NULL,
  `memBirYear` varchar(255) DEFAULT NULL,
  `memBirMonth` varchar(255) DEFAULT NULL,
  `memBirDay` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 資料表結構 `room`
--

CREATE TABLE `room` (
  `roomId` int(11) NOT NULL,
  `roomType` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `room`
--

INSERT INTO `room` (`roomId`, `roomType`) VALUES
(1, 'A1'),
(2, 'A2'),
(3, 'A4'),
(4, 'B1'),
(5, 'B2'),
(6, 'B4');

-- --------------------------------------------------------

--
-- 資料表結構 `roomprice`
--

CREATE TABLE `roomprice` (
  `roooType` varchar(5) NOT NULL,
  `roomPrice` int(10) NOT NULL,
  `roomPrice_mem` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 資料表的匯出資料 `roomprice`
--

INSERT INTO `roomprice` (`roooType`, `roomPrice`, `roomPrice_mem`) VALUES
('A1', 500, 450),
('A2', 900, 850),
('A4', 1500, 1450),
('B1', 400, 350),
('B2', 800, 700),
('B4', 1300, 1200);

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`bookId`);

--
-- 資料表索引 `bossaccount`
--
ALTER TABLE `bossaccount`
  ADD PRIMARY KEY (`empId`);

--
-- 資料表索引 `checkin`
--
ALTER TABLE `checkin`
  ADD PRIMARY KEY (`roomId`);

--
-- 資料表索引 `empdata`
--
ALTER TABLE `empdata`
  ADD PRIMARY KEY (`empId`);

--
-- 資料表索引 `emptitle`
--
ALTER TABLE `emptitle`
  ADD PRIMARY KEY (`empTitle`);

--
-- 資料表索引 `empworktime`
--
ALTER TABLE `empworktime`
  ADD PRIMARY KEY (`Id`);

--
-- 資料表索引 `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`memId`);

--
-- 資料表索引 `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`roomId`);

--
-- 資料表索引 `roomprice`
--
ALTER TABLE `roomprice`
  ADD PRIMARY KEY (`roooType`);

--
-- 在匯出的資料表使用 AUTO_INCREMENT
--

--
-- 使用資料表 AUTO_INCREMENT `book`
--
ALTER TABLE `book`
  MODIFY `bookId` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用資料表 AUTO_INCREMENT `empdata`
--
ALTER TABLE `empdata`
  MODIFY `empId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- 使用資料表 AUTO_INCREMENT `empworktime`
--
ALTER TABLE `empworktime`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用資料表 AUTO_INCREMENT `member`
--
ALTER TABLE `member`
  MODIFY `memId` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用資料表 AUTO_INCREMENT `room`
--
ALTER TABLE `room`
  MODIFY `roomId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
