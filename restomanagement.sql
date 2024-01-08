-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 08, 2024 at 03:22 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `restomanagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `detailreservasi`
--

CREATE TABLE `detailreservasi` (
  `DetailReservasiID` int(11) NOT NULL,
  `ReservasiID` int(11) DEFAULT NULL,
  `MenuID` int(11) DEFAULT NULL,
  `TipeMeja` enum('Romantic','General','Family') NOT NULL,
  `JumlahOrang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detailreservasi`
--

INSERT INTO `detailreservasi` (`DetailReservasiID`, `ReservasiID`, `MenuID`, `TipeMeja`, `JumlahOrang`) VALUES
(1, 1, 1, 'Romantic', 2),
(3, 1, 1, 'Romantic', 2),
(4, 2, 3, 'Family', 8),
(5, 1, 1, 'Romantic', 2),
(6, 2, 3, 'Family', 8),
(7, 3, 4, 'General', 5),
(8, 4, 2, 'Family', 7),
(9, 5, 8, 'General', 3),
(10, 6, 7, 'Romantic', 2),
(11, NULL, NULL, 'Romantic', 2),
(12, NULL, NULL, 'Family', 2);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `EmployeeID` int(11) NOT NULL,
  `Nama` varchar(255) NOT NULL,
  `RestoCabang` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`EmployeeID`, `Nama`, `RestoCabang`) VALUES
(1, 'John Doe', 'Bandung'),
(2, 'Jane Doe', 'Jakarta'),
(3, 'John Doe', 'Bandung'),
(4, 'Jane Doe', 'Jakarta'),
(5, 'John Doe', 'Bandung'),
(6, 'Jane Doe', 'Jakarta'),
(7, 'Bob Smith', 'Surabaya'),
(8, 'Alice Johnson', 'Samarinda'),
(9, 'Charlie Brown', 'Padang'),
(10, 'Eva Martinez', 'Kuta');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `MenuID` int(11) NOT NULL,
  `NamaMenu` varchar(255) NOT NULL,
  `Harga` decimal(10,2) NOT NULL,
  `RestoCabang` varchar(50) NOT NULL,
  `JenisMenu` enum('Special','Local Special','Regular') NOT NULL,
  `Narasi` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`MenuID`, `NamaMenu`, `Harga`, `RestoCabang`, `JenisMenu`, `Narasi`) VALUES
(1, 'Special Menu 1', '50.00', 'Bandung', 'Special', 'Cerita khas menu ini...'),
(2, 'Local Special 1', '40.00', 'Surabaya', 'Local Special', 'Ciri khas menu ini...'),
(3, 'Special Menu 1', '50.00', 'Bandung', 'Special', 'Cerita khas menu ini...'),
(4, 'Local Special 1', '40.00', 'Surabaya', 'Local Special', 'Ciri khas menu ini...'),
(5, 'Special Menu 1', '50.00', 'Bandung', 'Special', 'Cerita khas menu ini...'),
(6, 'Special Menu 2', '55.00', 'Jakarta', 'Special', 'Cerita khas menu ini...'),
(7, 'Special Menu 3', '60.00', 'Bali', 'Special', 'Cerita khas menu ini...'),
(8, 'Local Special 1', '40.00', 'Surabaya', 'Local Special', 'Ciri khas menu ini...'),
(9, 'Local Special 2', '45.00', 'Samarinda', 'Local Special', 'Ciri khas menu ini...'),
(10, 'Local Special 3', '35.00', 'Padang', 'Local Special', 'Ciri khas menu ini...'),
(11, 'Regular Menu 1', '25.00', 'Kuta', 'Regular', NULL),
(12, 'Regular Menu 2', '30.00', 'Bandung', 'Regular', NULL),
(13, 'Nasi', '45.00', 'Tangerang', 'Special', 'Enak'),
(15, 'pete', '54.09', 'Tangerang', 'Regular', 'Nothing');

-- --------------------------------------------------------

--
-- Table structure for table `reservasi`
--

CREATE TABLE `reservasi` (
  `ReservasiID` int(11) NOT NULL,
  `EmployeeID` int(11) DEFAULT NULL,
  `NamaPemesan` varchar(255) NOT NULL,
  `RestoCabang` varchar(50) NOT NULL,
  `Status` enum('In Reserve','In Order','Finalized') NOT NULL,
  `TanggalReservasi` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reservasi`
--

INSERT INTO `reservasi` (`ReservasiID`, `EmployeeID`, `NamaPemesan`, `RestoCabang`, `Status`, `TanggalReservasi`) VALUES
(1, 1, 'Customer A', 'Bandung', 'In Reserve', '2024-01-01'),
(2, 2, 'Customer B', 'Jakarta', 'In Order', '2024-01-02'),
(3, 1, 'Customer A', 'Bandung', 'In Reserve', '2024-01-01'),
(4, 2, 'Customer B', 'Jakarta', 'In Order', '2024-01-02'),
(5, 1, 'Customer A', 'Bandung', 'In Reserve', '2024-01-01'),
(6, 2, 'Customer B', 'Jakarta', 'In Order', '2024-01-02'),
(7, 3, 'Customer C', 'Surabaya', 'Finalized', '2024-01-03'),
(8, 4, 'Customer D', 'Samarinda', 'In Reserve', '2024-01-04'),
(9, 5, 'Customer E', 'Padang', 'In Order', '2024-01-05'),
(10, 6, 'Customer F', 'Kuta', 'Finalized', '2024-01-06'),
(11, NULL, 'ahmed', 'Bandung', 'Finalized', '2022-01-30'),
(12, NULL, 'ahmed', 'Bandung', 'Finalized', '2022-12-12');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detailreservasi`
--
ALTER TABLE `detailreservasi`
  ADD PRIMARY KEY (`DetailReservasiID`),
  ADD KEY `ReservasiID` (`ReservasiID`),
  ADD KEY `MenuID` (`MenuID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`EmployeeID`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`MenuID`);

--
-- Indexes for table `reservasi`
--
ALTER TABLE `reservasi`
  ADD PRIMARY KEY (`ReservasiID`),
  ADD KEY `EmployeeID` (`EmployeeID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detailreservasi`
--
ALTER TABLE `detailreservasi`
  MODIFY `DetailReservasiID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `EmployeeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `MenuID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `reservasi`
--
ALTER TABLE `reservasi`
  MODIFY `ReservasiID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detailreservasi`
--
ALTER TABLE `detailreservasi`
  ADD CONSTRAINT `detailreservasi_ibfk_1` FOREIGN KEY (`ReservasiID`) REFERENCES `reservasi` (`ReservasiID`),
  ADD CONSTRAINT `detailreservasi_ibfk_2` FOREIGN KEY (`MenuID`) REFERENCES `menu` (`MenuID`);

--
-- Constraints for table `reservasi`
--
ALTER TABLE `reservasi`
  ADD CONSTRAINT `reservasi_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
