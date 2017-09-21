-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 25, 2017 lúc 03:41 AM
-- Phiên bản máy phục vụ: 10.1.22-MariaDB
-- Phiên bản PHP: 7.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `orderfood`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `billdetail`
--

CREATE TABLE `billdetail` (
  `ID` int(6) NOT NULL,
  `NameFood` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `QuantumFood` int(3) NOT NULL,
  `CostFood` bigint(15) NOT NULL,
  `TimeOrder` datetime NOT NULL,
  `CodeBill` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `billdetail`
--

INSERT INTO `billdetail` (`ID`, `NameFood`, `QuantumFood`, `CostFood`, `TimeOrder`, `CodeBill`) VALUES
(33, 'Cánh Gà Chiên Nước Mắm', 2, 60000, '2017-07-10 22:01:28', 120),
(34, 'Lẩu Thái Chay', 2, 80000, '2017-07-10 22:01:31', 120),
(35, 'Bò Nhúng Giấm', 3, 90000, '2017-07-10 22:01:33', 120),
(36, 'Bia Tiger', 3, 17000, '2017-07-10 22:01:36', 120),
(37, 'Bánh Kem Dâu Tây', 3, 150000, '2017-07-10 22:01:40', 120),
(38, 'Mực Nướng Muối Ớt', 2, 55000, '2017-07-11 01:23:45', 121),
(39, 'Bia Tiger', 4, 17000, '2017-07-11 01:23:49', 121),
(40, 'Rau Câu Flan Cafe', 3, 20000, '2017-07-11 01:23:52', 121),
(41, 'Cánh Gà Chiên Nước Mắm', 1, 60000, '2017-07-13 01:07:01', 124),
(42, 'Cá Sapa Nướng Giấy Bạc', 3, 70000, '2017-07-13 01:07:04', 124),
(43, 'Bia Tiger', 3, 17000, '2017-07-13 01:07:08', 124),
(44, 'Bánh Kem Dâu Tây', 3, 150000, '2017-07-13 01:07:12', 124),
(45, 'Cánh Gà Chiên Nước Mắm', 2, 60000, '2017-07-13 02:32:25', 125),
(46, 'Bò Lúc Lắc', 3, 70000, '2017-07-13 02:32:28', 125),
(47, 'Rau Muống Xào Tỏi', 2, 40000, '2017-07-13 02:32:31', 125),
(48, 'Pepsi', 4, 15000, '2017-07-13 02:32:35', 125),
(49, 'Rượu Rumbacardi', 1, 550000, '2017-07-13 02:32:37', 125),
(50, 'Rau Câu Flan Cafe', 2, 20000, '2017-07-13 02:32:42', 125),
(51, 'Bánh Tiramisu', 2, 165000, '2017-07-13 02:32:45', 125),
(52, 'Bánh Cuộn Phú Sĩ', 1, 135000, '2017-07-13 02:32:48', 125),
(53, 'Cánh Gà Chiên Nước Mắm', 2, 60000, '2017-07-13 04:28:14', 126),
(54, 'Bò Lúc Lắc', 3, 70000, '2017-07-13 04:28:18', 126),
(55, 'Cam Ép', 3, 18000, '2017-07-13 04:28:21', 126),
(56, 'Bánh Su Kem', 3, 30000, '2017-07-13 04:28:25', 126),
(57, 'Cánh Gà Chiên Nước Mắm', 2, 60000, '2017-07-13 04:31:53', 127),
(58, 'Lẩu Thái Chay', 1, 80000, '2017-07-13 04:31:55', 127),
(59, 'Coca Cola', 3, 15000, '2017-07-13 04:31:58', 127),
(60, 'Mousse Trà Xanh', 1, 120000, '2017-07-13 04:32:02', 127),
(61, 'Cánh Gà Chiên Nước Mắm', 2, 60000, '2017-07-13 04:39:35', 128),
(62, 'Pepsi', 1, 15000, '2017-07-13 04:39:40', 128),
(63, 'Mousse Trà Xanh', 1, 120000, '2017-07-13 04:39:42', 128),
(64, 'Mì Xào Giòn', 2, 30000, '2017-07-13 05:33:47', 129),
(65, 'Cá Sapa Nướng Giấy Bạc', 1, 70000, '2017-07-13 05:33:50', 129),
(66, 'Bia Tiger', 5, 17000, '2017-07-13 05:33:57', 129),
(67, 'Crepe Sầu Riêng', 1, 25000, '2017-07-13 05:34:01', 129),
(68, 'Bò Nhúng Giấm', 2, 90000, '2017-07-13 05:40:05', 130),
(69, 'Cá Sapa Nướng Giấy Bạc', 1, 70000, '2017-07-13 05:40:07', 130),
(70, 'Bánh Kem Dâu Tây', 1, 150000, '2017-07-13 05:40:11', 130),
(71, 'Rượu Chivas Regal', 1, 600000, '2017-07-13 05:40:15', 130),
(72, 'Crepe Sầu Riêng', 2, 25000, '2017-07-13 05:51:12', 131),
(73, 'Warrior (Sting Thái)', 4, 12000, '2017-07-13 05:51:17', 131),
(74, 'Cá Kèo Nướng', 3, 65000, '2017-07-13 05:51:21', 131),
(75, 'Cánh Gà Chiên Nước Mắm', 2, 60000, '2017-07-13 06:29:08', 132),
(76, 'Bò Lúc Lắc', 1, 70000, '2017-07-13 06:29:10', 132),
(77, 'Bia Tiger', 2, 17000, '2017-07-13 06:29:13', 132),
(78, 'Bánh Tiramisu', 1, 165000, '2017-07-13 06:29:17', 132),
(79, 'Chè Khúc Bạch Trái Cây', 3, 20000, '2017-07-20 15:51:12', 133),
(80, 'Coca Cola', 2, 15000, '2017-07-20 15:51:15', 133),
(81, 'Bò Lúc Lắc', 1, 70000, '2017-07-20 15:51:19', 133),
(82, 'Cút Chiên Bơ', 2, 50000, '2017-07-20 16:01:39', 134),
(83, 'Bia Heiniken', 3, 20000, '2017-07-20 16:01:43', 134),
(84, 'Bánh Tiramisu', 1, 165000, '2017-07-20 16:01:46', 134),
(85, 'Cánh Gà Chiên Nước Mắm', 2, 60000, '2017-07-20 16:38:31', 135),
(86, 'Coca Cola', 1, 15000, '2017-07-20 16:38:33', 135),
(87, 'Bánh Tiramisu', 1, 165000, '2017-07-20 16:38:35', 135),
(88, 'Cút Chiên Bơ', 2, 50000, '2017-07-20 17:28:58', 136),
(89, 'Bia Heiniken', 2, 20000, '2017-07-20 17:29:01', 136),
(90, 'Crepe Sầu Riêng', 1, 25000, '2017-07-20 17:29:04', 136),
(91, 'Mực Nướng Muối Ớt', 2, 55000, '2017-07-20 17:30:21', 136),
(92, 'Rượu Rumbacardi', 2, 550000, '2017-07-20 17:30:25', 136),
(93, 'Bánh Tiramisu', 2, 165000, '2017-07-20 17:30:28', 136),
(94, 'Mực Nướng Muối Ớt', 2, 55000, '2017-07-20 17:30:39', 137),
(95, 'Pepsi', 1, 15000, '2017-07-20 17:30:42', 137),
(96, 'Cút Chiên Bơ', 2, 50000, '2017-07-20 18:15:35', 138),
(97, 'Rượu Chivas Regal', 2, 600000, '2017-07-20 18:15:38', 138),
(98, 'Bánh Tiramisu', 1, 165000, '2017-07-20 18:15:41', 138),
(99, 'Ba Rọi Nướng', 2, 50000, '2017-07-20 18:50:19', 139),
(100, 'Lẩu Dê', 2, 120000, '2017-07-20 19:06:29', 141),
(101, 'Bia Heiniken', 1, 20000, '2017-07-20 19:06:32', 141),
(102, 'Rau Câu Flan Cafe', 1, 20000, '2017-07-20 19:06:35', 141),
(103, 'Mực Nướng Muối Ớt', 2, 55000, '2017-07-20 19:25:05', 142),
(104, 'Pepsi', 4, 15000, '2017-07-20 19:25:09', 142),
(105, 'Bánh Tiramisu', 1, 165000, '2017-07-20 19:25:12', 142),
(106, 'Lẩu Thái Chay', 2, 80000, '2017-07-20 19:26:09', 143),
(107, 'Rượu Chivas Regal', 2, 600000, '2017-07-20 19:26:12', 143),
(108, 'Mousse Trà Xanh', 1, 120000, '2017-07-20 19:26:15', 143),
(109, 'Cút Chiên Bơ', 3, 50000, '2017-07-21 03:09:17', 144),
(110, 'Pepsi', 2, 15000, '2017-07-21 03:09:20', 144),
(111, 'Rau Câu Flan Cafe', 1, 20000, '2017-07-21 03:09:23', 144),
(112, 'Cánh Gà Chiên Nước Mắm', 2, 60000, '2017-07-21 07:49:25', 145),
(113, 'Pepsi', 4, 15000, '2017-07-21 07:49:31', 145),
(114, 'Crepe Sầu Riêng', 2, 25000, '2017-07-21 07:49:37', 145),
(115, 'Cánh Gà Chiên Nước Mắm', 3, 60000, '2017-07-21 08:23:16', 146),
(116, 'Lẩu Dê', 1, 120000, '2017-07-21 08:23:20', 146),
(117, 'Bia Tiger', 3, 17000, '2017-07-21 08:23:26', 146),
(118, 'Mousse Trà Xanh', 2, 120000, '2017-07-21 08:23:32', 146);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `billorder`
--

CREATE TABLE `billorder` (
  `StatusCode` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `BillCode` int(6) NOT NULL,
  `NumberTable` int(6) NOT NULL,
  `EmployeeCode` int(6) NOT NULL,
  `EmployeeName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `TimeOrderTab` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `billorder`
--

INSERT INTO `billorder` (`StatusCode`, `BillCode`, `NumberTable`, `EmployeeCode`, `EmployeeName`, `TimeOrderTab`) VALUES
('success', 120, 6, 24, 'Vũ Phi Hùng', '2017-07-10 22:01:24'),
('success', 121, 4, 24, 'Vũ Phi Hùng', '2017-07-11 01:23:42'),
('success', 122, 8, 24, 'Vũ Phi Hùng', '2017-07-11 15:57:35'),
('success', 123, 2, 24, 'Vũ Phi Hùng', '2017-07-11 19:48:14'),
('success', 124, 1, 24, 'Vũ Phi Hùng', '2017-07-13 00:48:21'),
('success', 125, 3, 24, 'Vũ Phi Hùng', '2017-07-13 02:32:21'),
('success', 126, 5, 24, 'Vũ Phi Hùng', '2017-07-13 04:28:11'),
('success', 127, 8, 24, 'Vũ Phi Hùng', '2017-07-13 04:31:48'),
('success', 128, 6, 24, 'Vũ Phi Hùng', '2017-07-13 04:39:32'),
('success', 129, 12, 24, 'Vũ Phi Hùng', '2017-07-13 05:33:40'),
('success', 130, 10, 24, 'Vũ Phi Hùng', '2017-07-13 05:40:01'),
('success', 131, 14, 24, 'Vũ Phi Hùng', '2017-07-13 05:51:03'),
('success', 132, 3, 24, 'Vũ Phi Hùng', '2017-07-13 06:29:05'),
('success', 133, 4, 24, 'Vũ Phi Hùng', '2017-07-20 15:51:04'),
('success', 134, 8, 24, 'Vũ Phi Hùng', '2017-07-20 16:01:35'),
('success', 135, 6, 24, 'Vũ Phi Hùng', '2017-07-20 16:38:19'),
('success', 136, 12, 24, 'Vũ Phi Hùng', '2017-07-20 17:28:54'),
('success', 137, 14, 24, 'Vũ Phi Hùng', '2017-07-20 17:30:36'),
('success', 138, 9, 24, 'Vũ Phi Hùng', '2017-07-20 18:15:31'),
('success', 139, 7, 24, 'Vũ Phi Hùng', '2017-07-20 18:49:56'),
('success', 140, 11, 24, 'Vũ Phi Hùng', '2017-07-20 18:56:50'),
('success', 141, 5, 24, 'Vũ Phi Hùng', '2017-07-20 19:06:27'),
('success', 142, 6, 24, 'Vũ Phi Hùng', '2017-07-20 19:25:02'),
('success', 143, 11, 24, 'Vũ Phi Hùng', '2017-07-20 19:26:05'),
('success', 144, 4, 24, 'Vũ Phi Hùng', '2017-07-21 03:09:06'),
('success', 145, 3, 24, 'Vũ Phi Hùng', '2017-07-21 07:49:21'),
('success', 146, 4, 20, 'Hung', '2017-07-21 08:23:04');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dessert`
--

CREATE TABLE `dessert` (
  `ID` int(4) NOT NULL,
  `NameDessert` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CostDessert` int(10) NOT NULL,
  `LinkDessert` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `dessert`
--

INSERT INTO `dessert` (`ID`, `NameDessert`, `CostDessert`, `LinkDessert`) VALUES
(1, 'Chè Khúc Bạch Trái Cây', 20000, 'https://media.lamsao.com/Data/diepptn/26032015/cach_lam_che_khuc_bach_trai_cay_ngot_mat_sieu_hap_dan_8.jpg'),
(2, 'Bánh Su Kem', 30000, 'http://gtmart.com.vn/uploads/news/su-kem.jpg'),
(3, 'Mousse Trà Xanh', 120000, 'https://blog.beemart.vn/wp-content/uploads/2016/05/cach-lam-mousse-tra-xanh-mat-lanh-ngon-khong-the-choi-tu-2.jpg'),
(4, 'Rau Câu Flan Cafe', 20000, 'http://www.savourydays.com/wp-content/uploads/2016/08/c%C3%A1ch-l%C3%A0m-rau-c%C3%A2u-flan-c%C3%A0-ph%C3%AA-banner-300x300.jpg'),
(5, 'Bánh Kem Dâu Tây', 150000, 'http://baophunu.info/wp-content/uploads/2014/07/acfa5801-249c-4755-8217-7314ce372ed01.jpg'),
(6, 'Crepe Sầu Riêng', 25000, 'https://blog.beemart.vn/wp-content/uploads/2016/06/doc-dao-voi-cach-lam-banh-crepe-sau-rieng-thom-nuc-ca-nha-1.jpg'),
(7, 'Bánh Tiramisu', 165000, 'http://hoclambanhvn.com/wp-content/uploads/2013/10/day-nghe-banh-Tiramisu-hoc-lam-banh.jpg'),
(8, 'Flan Bí Đỏ', 140000, 'http://www.bepgiadinh.com/wp-content/uploads/2013/10/24/b%C3%A1nh-flan-b%C3%AD-%C4%91%E1%BB%8F.jpg'),
(9, 'Bánh Cuộn Phú Sĩ', 135000, 'http://s1-cdn.webgiadinh.org/2014/8/7/87549a41bba3fccaf4d940bf0557552d.jpg'),
(10, 'Chè Sương Sa Hạt Lựu', 120000, 'http://img.news.zing.vn/img/387/t387318.jpg'),
(11, 'Rau Câu Phúc Bồn Tử', 100000, 'https://tea-1.lozi.vn/v1/images/resized/rau-cau-pho-mai-phuc-bon-tu-108854-1444914089?w=640&type=s'),
(12, 'Chè Nhãn Nhục', 150000, 'http://thegioihinhanh.com/uploads/images/Cach-nau-che-nhan-nhuc-ngon-3.jpg'),
(13, 'Chè Thái Sầu Riêng', 50000, 'https://media.foody.vn/res/g8/71866/s600x600/2016824182240-che-thai-sau-rieng.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `drink`
--

CREATE TABLE `drink` (
  `ID` int(4) NOT NULL,
  `NameDrink` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `CostDrink` int(10) NOT NULL,
  `LinkDrink` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `drink`
--

INSERT INTO `drink` (`ID`, `NameDrink`, `CostDrink`, `LinkDrink`) VALUES
(1, 'Nước Suối ', 6000, 'http://www.mangnuocnhanh.com/uploads/Images/aquafina_hoangtranwater.jpeg'),
(2, 'Cam Ép', 18000, 'http://dailybiaruou.com/files/sanpham/30/1/jpg/Cam-%C3%A9p-24lon/th%C3%B9ng.jpg'),
(3, 'Coca Cola', 15000, 'http://www.satrafoods.com.vn/uploads/san-pham-cung-loai/8935049510116.jpg'),
(4, 'Pepsi', 15000, 'http://thegioihangmy.net/uploads/admin/Images/Products/N-04/N4L-05/01201303_1.jpg'),
(5, 'Bia Tiger', 17000, 'http://dathangsieuthi.com/wp-content/uploads/2013/03/bia-tiger-lon-330-ml.jpg'),
(6, 'Bia Heiniken', 20000, 'http://thuonghieuvietnoitieng.vn/images/products/2016/09/15/original/bia-heineken_1473913597.jpg'),
(7, 'Rượu Chivas Regal', 600000, 'http://media.bizwebmedia.net/sites/3693/data/images/2011/10/0342061Chivas12YO02.jpg'),
(8, 'Rượu Rumbacardi', 550000, 'http://www.sieuthiruouvang.com/wp-content/uploads/2016/08/ruou-rhum-bacardi-chat-luong.jpg'),
(9, 'Warrior (Sting Thái)', 12000, 'http://ruouvanggiasi.com/image/data/Nuoc%20giai%20khat/warroir%20red.png'),
(10, 'Aquarius', 10000, 'http://toplist.vn/images/800px/nuoc-the-thao-aquarius-93244.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `eating`
--

CREATE TABLE `eating` (
  `ID` int(4) NOT NULL,
  `NameFood` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `CostFood` int(10) NOT NULL,
  `LinkFood` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `eating`
--

INSERT INTO `eating` (`ID`, `NameFood`, `CostFood`, `LinkFood`) VALUES
(1, 'Cánh Gà Chiên Nước Mắm', 60000, 'http://satrafb.com.vn/upload/news/canh-ga-chien-nuoc-mam_1475662444_300x300.jpg'),
(2, 'Cút Chiên Bơ', 50000, 'http://duli.vn/data/showcase/44/44010-3860a477042ce08c9463d7885e795a5c.jpg'),
(3, 'Mực Nướng Muối Ớt', 55000, 'https://lh3.googleusercontent.com/-MYE_sLAYYfE/Um9hiL1MlBI/AAAAAAAAAIM/PQYq9IL4784/s300-no/mucnuong.jpg'),
(4, 'Lẩu Dê', 120000, 'http://duli.vn/data/showcase/136/136763-7855934625e9b084df027f2febb7078f.jpg'),
(5, 'Lẩu Thái Chay', 80000, 'https://media.cooky.vn/recipe/g1/182/s300x300/Recipe182-635363758513389687.jpg'),
(6, 'Bò Lúc Lắc', 70000, 'http://lh4.ggpht.com/-bF7VKRcNFWA/VSaqcGpq-3I/AAAAAAAADCI/gu16QHh0sEs/s1600/cach-lam-bo-luc-lac.jpeg'),
(7, 'Mì Xào Giòn', 30000, 'http://www.nhahangthoangviet.com/thumb/300x300/1/upload/product/mixaogion-9151.jpg'),
(8, 'Bò Nhúng Giấm', 90000, 'https://media.cooky.vn/recipe/g1/2838/s300x300/recipe2838-636138495665481442.jpg'),
(9, 'Rau Muống Xào Tỏi', 40000, 'https://media.cooky.vn/recipe/g1/9620/s300x300/recipe9620-635806009765034777.jpg'),
(10, 'Vịt Nấu Chao', 85000, 'https://media.cooky.vn/recipe/g1/4701/s300x300/recipe4701-635706549417675951.jpg'),
(11, 'Cá Sapa Nướng Giấy Bạc', 70000, 'http://orderfood.com.vn/wp-content/uploads/2016/10/N%C6%B0%E1%BB%9Bng-gi%E1%BA%A5y-b%E1%BA%A1c-compressed-300x300.jpg'),
(12, 'Cá Kèo Nướng', 65000, 'https://media.foody.vn/res/g1/3718/s300x300/foody-lau-ca-keo-ton-that-huyet-21673cb7-c9ac-4ec5-b4aa-d33afa8218a4-635259211340755000.jpg'),
(13, 'Gỏi Ngó Sen', 45000, 'http://www.longlontietcanh.com/wp-content/uploads/2016/11/bi-quyet-de-co-mon-goi-luoi-heo-tron-ngo-sen-ngon-mieng-300x300.jpg'),
(14, 'Vịt Quay Bắc Kinh', 160000, 'https://vn.allcoupon.asia/img/pic/201510/7fb1f7d81d23764125f92b7e83d03b4d.jpg'),
(15, 'Ba Rọi Nướng', 50000, 'https://media.foody.vn/res/g23/229759/s300x300/foody-hallyu-korean-fast-food-duong-3-thang-2-951-635984878761556080.jpg'),
(16, 'Gỏi Bò Bóp Thấu', 55000, 'http://media.cooky.vn/recipe/g1/489/s300x300/recipe489-prepare-step5-635851709909792850.jpg'),
(17, 'Cá Lóc Nướng Trui', 80000, 'http://i.vinface.com/biz/photo/27_3552845185431d3f6b7170/300x300.jpg'),
(18, 'Cơm Chiên Hải Sản', 55000, 'http://foodbike.vn/wp-content/uploads/2016/12/com-chien-thai-e1489323220776-300x300.jpg'),
(19, 'Nai Nướng Ngũ Vị', 90000, 'http://2.bp.blogspot.com/-bqs2hwd6JmE/VDZUcdMWwsI/AAAAAAAAAc4/AXdWJcF-YsI/s1600/Sotaydulich_De_nui_Ninh_Binh_02.jpg'),
(20, 'Đà Điểu Xào Xả Ớt', 100000, 'http://quanocminhhuong.com/Image/Picture/Da%20dieu/%C4%90%C3%A0%20%C4%91i%E1%BB%83u%20n%C6%B0%E1%BB%9Bng%20x%E1%BA%A3%20%E1%BB%9Bt.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `register`
--

CREATE TABLE `register` (
  `ID` int(4) NOT NULL,
  `EmployeeCode` int(4) NOT NULL,
  `Name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Position` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `register`
--

INSERT INTO `register` (`ID`, `EmployeeCode`, `Name`, `Password`, `Position`) VALUES
(99, 24, 'Vũ Phi Hùng', 'Mezcal_2410', 'Employee'),
(101, 90, 'Minh Hieu', 'minhhieu90', 'Chef'),
(102, 20, 'Hung', '123456', 'Employee');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `billdetail`
--
ALTER TABLE `billdetail`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `fk_bill_detail` (`CodeBill`);

--
-- Chỉ mục cho bảng `billorder`
--
ALTER TABLE `billorder`
  ADD PRIMARY KEY (`BillCode`);

--
-- Chỉ mục cho bảng `dessert`
--
ALTER TABLE `dessert`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `drink`
--
ALTER TABLE `drink`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `eating`
--
ALTER TABLE `eating`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `billdetail`
--
ALTER TABLE `billdetail`
  MODIFY `ID` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=119;
--
-- AUTO_INCREMENT cho bảng `billorder`
--
ALTER TABLE `billorder`
  MODIFY `BillCode` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=147;
--
-- AUTO_INCREMENT cho bảng `dessert`
--
ALTER TABLE `dessert`
  MODIFY `ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT cho bảng `drink`
--
ALTER TABLE `drink`
  MODIFY `ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT cho bảng `eating`
--
ALTER TABLE `eating`
  MODIFY `ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT cho bảng `register`
--
ALTER TABLE `register`
  MODIFY `ID` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;
--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `billdetail`
--
ALTER TABLE `billdetail`
  ADD CONSTRAINT `fk_bill_detail` FOREIGN KEY (`CodeBill`) REFERENCES `billorder` (`BillCode`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
