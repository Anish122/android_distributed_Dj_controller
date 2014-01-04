-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 04, 2013 at 07:15 AM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `minor2`
--

-- --------------------------------------------------------

--
-- Table structure for table `songs`
--

CREATE TABLE IF NOT EXISTS `songs` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `length` decimal(10,2) NOT NULL,
  `description` text,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=456 ;

--
-- Dumping data for table `songs`
--

INSERT INTO `songs` (`pid`, `name`, `length`, `description`, `created_at`, `updated_at`) VALUES
(426, '01 - Guzarish - Javed Ali & Sonu Nigam @ Fmw11', 5.22, 'Javed Ali & Sonu Niga', '2013-04-04 05:15:05', '0000-00-00 00:00:00'),
(427, '01 - Saibo', 3.11, 'Shreya Ghoshal & Tochi Raina ', '2013-04-04 05:15:06', '0000-00-00 00:00:00'),
(428, 'Akon - I Wanna Love You ft Snoop Dogg - YouTube', 4.90, '', '2013-04-04 05:15:06', '0000-00-00 00:00:00'),
(429, 'Akon - Lonely - YouTube', 4.19, '', '2013-04-04 05:15:06', '0000-00-00 00:00:00'),
(430, 'Akon - Right Now (Na Na Na) - YouTube', 4.55, '', '2013-04-04 05:15:06', '0000-00-00 00:00:00'),
(431, 'Akon - Smack That ft Eminem - YouTube', 4.70, '', '2013-04-04 05:15:06', '0000-00-00 00:00:00'),
(432, 'aserje las ketchup', 3.46, '', '2013-04-04 05:15:06', '0000-00-00 00:00:00'),
(433, 'David Guetta - Little Bad Girl ft Taio Cruz, Ludacris - YouTube', 3.25, '', '2013-04-04 05:15:07', '0000-00-00 00:00:00'),
(434, 'David Guetta - Sexy Chick (Featuring Akon) - YouTube', 3.26, '', '2013-04-04 05:15:07', '0000-00-00 00:00:00'),
(435, 'David Guetta - Turn Me On ft Nicki Minaj - YouTube', 3.25, '', '2013-04-04 05:15:07', '0000-00-00 00:00:00'),
(436, 'Eminem - Love The Way You Lie ft Rihanna - YouTube', 4.21, '', '2013-04-04 05:15:07', '0000-00-00 00:00:00'),
(437, 'Flo Rida - Good Feeling [Official Video] - YouTube', 4.24, '', '2013-04-04 05:15:08', '0000-00-00 00:00:00'),
(438, 'Flo Rida - I Cry [Official Video] - YouTube', 3.47, '', '2013-04-04 05:15:08', '0000-00-00 00:00:00'),
(439, 'Flo Rida - Let It Roll (Keith Apicary video) - YouTube', 3.41, '', '2013-04-04 05:15:08', '0000-00-00 00:00:00'),
(440, 'Flo Rida - Low - feat T-Pain (Official Music Video) - YouTube', 3.52, '', '2013-04-04 05:15:08', '0000-00-00 00:00:00'),
(441, 'Flo Rida - Right Round (US Version Video) - YouTube', 3.22, '', '2013-04-04 05:15:08', '0000-00-00 00:00:00'),
(442, 'Flo Rida - Run ft RedFoo of LMFAO [Audio] - YouTube', 3.50, '', '2013-04-04 05:15:08', '0000-00-00 00:00:00'),
(443, 'Flo Rida - Turn Around (5,4,3,2,1) [Clean Edit] - YouTube', 3.56, '', '2013-04-04 05:15:09', '0000-00-00 00:00:00'),
(444, 'Flo Rida - Whistle [Official Video] - YouTube', 3.49, '', '2013-04-04 05:15:09', '0000-00-00 00:00:00'),
(445, 'Flo Rida - Who Dat Girl ft Akon [Official Video] - YouTube', 3.34, '', '2013-04-04 05:15:09', '0000-00-00 00:00:00'),
(446, 'Flo Rida - Wild Ones ft Sia [Official Video] - YouTube', 3.52, '', '2013-04-04 05:15:09', '0000-00-00 00:00:00'),
(447, 'Flo Rida - _Hey Jasmin_ Official Video - YouTube', 3.52, '', '2013-04-04 05:15:09', '0000-00-00 00:00:00'),
(448, 'Nickelback - Gotta Be Somebody - YouTube', 4.60, '', '2013-04-04 05:15:09', '0000-00-00 00:00:00'),
(449, 'Pitbull - International Love ft Chris Brown - YouTube', 4.30, '', '2013-04-04 05:15:10', '0000-00-00 00:00:00'),
(450, 'pitbull shakira', 4.22, '', '2013-04-04 05:15:10', '0000-00-00 00:00:00'),
(451, 'resident evil', 7.31, '', '2013-04-04 05:15:10', '0000-00-00 00:00:00'),
(452, 'Sean Paul - Got 2 Luv U Ft Alexis Jordan [Official Music Video] - YouTube', 3.29, '', '2013-04-04 05:15:10', '0000-00-00 00:00:00'),
(453, 'Taio Cruz - Dynamite - YouTube', 4.11, '', '2013-04-04 05:15:11', '0000-00-00 00:00:00'),
(454, 'Taio Cruz - There She Goes - YouTube', 3.33, '', '2013-04-04 05:15:11', '0000-00-00 00:00:00'),
(455, 'Taio Cruz - Troublemaker - YouTube', 3.36, '', '2013-04-04 05:15:11', '0000-00-00 00:00:00');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
