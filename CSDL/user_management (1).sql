-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 15, 2024 lúc 05:00 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `user_management`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hosouv`
--

CREATE TABLE `hosouv` (
  `MaHS` int(11) NOT NULL COMMENT 'AUTO_INCREMENT',
  `HovaTen` varchar(50) NOT NULL,
  `Ngaysinh` date NOT NULL,
  `Gioitinh` varchar(5) NOT NULL,
  `CCCD` varchar(12) NOT NULL,
  `SDT` varchar(15) NOT NULL,
  `TrinhDoChuyenMon` text NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `Anh` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hosouv`
--

INSERT INTO `hosouv` (`MaHS`, `HovaTen`, `Ngaysinh`, `Gioitinh`, `CCCD`, `SDT`, `TrinhDoChuyenMon`, `user_id`, `email`, `Anh`) VALUES
(4, 'Nguyễn Trần Ngọc Tùng', '2024-06-13', 'Nam', '2147483647', '012345679', 'Công nghệ thông tin', 38, 'qevxus@gmail.com', './assets/uploads/Ảnh chụp màn hình (20).png'),
(5, 'Nguyễn Trần Ngọc Tùng', '2024-06-21', 'Nam', '08720322222', '0912345678', 'Thông thạo lập trình C++/ lập trình C#', 33, 'qevxus@gmail.com', './assets/uploads/lovepik-peach-blossom-png-image_401307409_wh1200.png'),
(6, 'Nguyễn Tùng', '2024-06-22', 'Nam', '08720322222', '0912345678', 'Kỹ thuật phần mềm', 39, 'tungngic12@gmail.com', ''),
(7, 'Nguyễn Trần Ngọc Tùng', '2024-06-13', 'Nam', '08720322222', '0912345678', 'Quản trị kinh doanh', 45, 'qevxus@gmail.com', './assets/uploads/Ảnh chụp màn hình 2024-05-25 235418.png'),
(8, 'Nguyễn Thị Mộng Nghi', '2003-11-20', 'Nữ', '087203123456', '0123456789', 'Sư phạm tiểu học', 46, 'mongnghi@gmail.com', './assets/uploads/Ảnh chụp màn hình 2024-06-04 152040.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lich_phong_van`
--

CREATE TABLE `lich_phong_van` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `user_data_id` int(11) NOT NULL,
  `ngay_phong_van` date NOT NULL,
  `thoi_gian` time NOT NULL,
  `dia_diem` varchar(255) NOT NULL,
  `QuyTac` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `lich_phong_van`
--

INSERT INTO `lich_phong_van` (`id`, `user_id`, `user_data_id`, `ngay_phong_van`, `thoi_gian`, `dia_diem`, `QuyTac`) VALUES
(8, 30, 88, '2024-06-12', '09:38:00', 'TpHCM', 'Ảnh chụp màn hình (1).png'),
(10, 33, 86, '2024-06-15', '22:00:00', 'TpHCM', 'Yêu cầu cụ thể về thiết bị.docx');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhatuyendung`
--

CREATE TABLE `nhatuyendung` (
  `MaNhaTD` int(11) NOT NULL COMMENT 'AUTO_INCREMENT',
  `Logo` text DEFAULT NULL,
  `TenCTY` text DEFAULT NULL,
  `MucLuong` int(11) DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `DiaChiLamViec` varchar(100) DEFAULT NULL,
  `GioiTinh` varchar(5) NOT NULL,
  `TrinhDoYeuCau` text DEFAULT NULL,
  `ChucVu` varchar(50) DEFAULT NULL,
  `TTLH` varchar(255) NOT NULL,
  `NgayYeuCau` datetime DEFAULT NULL,
  `NgayKetThuc` datetime DEFAULT NULL,
  `MoTaFile` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhatuyendung`
--

INSERT INTO `nhatuyendung` (`MaNhaTD`, `Logo`, `TenCTY`, `MucLuong`, `SoLuong`, `DiaChiLamViec`, `GioiTinh`, `TrinhDoYeuCau`, `ChucVu`, `TTLH`, `NgayYeuCau`, `NgayKetThuc`, `MoTaFile`) VALUES
(2, './assets/uploads/logos/Ảnh_chụp_màn_hình_2024-05-13_221406.png', 'Công ty Hoa Mai Đỏ', 20, 2, 'Hà Nội', '', 'Biết lập trình wed ', 'Nhân viên', 'nhatuyend1ung@gmail.com', '2024-06-14 01:06:00', '2024-06-15 01:06:00', './assets/uploads/files/Mota.docx'),
(6, './assets/uploads/logos/KBFQap.jpg', 'Công ty Hoa Mai Vàng', 20, 2, 'Cần Thơ, Đồng Tháp', '', 'Biết lập trình wed ', 'Nhân viên làm cổng hoa cưới ', '0147852645', '2024-06-14 20:40:00', '2024-06-16 20:40:00', './assets/uploads/files/Mota.docx'),
(8, './assets/uploads/logos/Ảnh_chụp_màn_hình_(42).png', 'Công ty Hoa Mai Vàng', 500, 3, 'Hà Nội', '0', 'Thông thạo lập trình C/C++', 'Nhân viên', 'nhatuyend1ung@gmail.com', '2024-06-14 20:48:00', '2024-06-21 20:48:00', './assets/uploads/files/Mota.docx'),
(9, './assets/uploads/logos/Ảnh_chụp_màn_hình_2024-05-08_230413.png', 'Công ty Hoa Mai Vàng', 20, 34, 'Cần Thơ, Đồng Tháp', 'Nữ', 'Thông thạo lập trình C/C++', 'Nhân viên làm cổng hoa cưới ', '0147852645', '2024-06-14 20:50:00', '2024-06-14 12:50:00', './assets/uploads/files/Mota.docx');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thongbao`
--

CREATE TABLE `thongbao` (
  `MaTB` int(11) NOT NULL,
  `MaUV` int(11) NOT NULL,
  `MaNhaTD` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `HovaTen` varchar(255) NOT NULL,
  `CCCD` varchar(20) NOT NULL,
  `SDT` varchar(15) NOT NULL,
  `Ngaysinh` date NOT NULL,
  `TrinhDoChuyenMon` varchar(255) NOT NULL,
  `noidung` text NOT NULL,
  `loaitb` enum('Dau','Rot') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `thongbao`
--

INSERT INTO `thongbao` (`MaTB`, `MaUV`, `MaNhaTD`, `username`, `email`, `HovaTen`, `CCCD`, `SDT`, `Ngaysinh`, `TrinhDoChuyenMon`, `noidung`, `loaitb`) VALUES
(1, 12, 0, 'ngoctung20', 'qevxus@gmail.com', 'Nguyễn Trần Ngọc Tùng', '08720322222', '0912345678', '2024-06-21', 'Công nghệ thông tin', 'xin chào', 'Dau'),
(3, 4, 0, 'ngoctung20', 'qevxus@gmail.com', 'Nguyễn Trần Ngọc Tùng', '08720322222', '0912345678', '2024-06-21', 'Công nghệ thông tin', 'ngày 23/8/2024 tại tphcm', 'Rot'),
(4, 4, 0, 'ngoctung20', 'qevxus@gmail.com', 'Nguyễn Trần Ngọc Tùng', '08720322222', '0912345678', '2024-06-21', 'Công nghệ thông tin', 'Rớt', 'Dau'),
(5, 4, 2, 'ngoctung20', 'qevxus@gmail.com', 'Nguyễn Trần Ngọc Tùng', '08720322222', '0912345678', '2024-06-21', 'Công nghệ thông tin', 'xin chào bạn đã đậu', 'Dau');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ungvien`
--

CREATE TABLE `ungvien` (
  `MaUV` int(11) NOT NULL COMMENT 'AUTO_INCREMENT',
  `username` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `HovaTen` varchar(50) NOT NULL,
  `Ngaysinh` date NOT NULL,
  `Gioitinh` varchar(5) NOT NULL,
  `CCCD` varchar(12) NOT NULL,
  `SDT` varchar(15) NOT NULL,
  `TrinhDoChuyenMon` text NOT NULL,
  `MaNhaTD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ungvien`
--

INSERT INTO `ungvien` (`MaUV`, `username`, `email`, `HovaTen`, `Ngaysinh`, `Gioitinh`, `CCCD`, `SDT`, `TrinhDoChuyenMon`, `MaNhaTD`) VALUES
(4, 'ngoctung20', 'qevxus@gmail.com', 'Nguyễn Trần Ngọc Tùng', '2024-06-21', 'Nam', '08720322222', '0912345678', 'Công nghệ thông tin', 2),
(5, 'ngoctung20', 'qevxus@gmail.com', 'Nguyễn Trần Ngọc Tùng', '2024-06-21', 'Nam', '08720322222', '0912345678', 'Công nghệ thông tin', 2),
(8, 'ngoctung20', 'qevxus@gmail.com', 'Nguyễn Trần Ngọc Tùng', '2024-06-21', 'Nam', '08720322222', '0912345678', 'Công nghệ thông tin', 2),
(12, 'ngoctung20', 'qevxus@gmail.com', 'Nguyễn Trần Ngọc Tùng', '2024-06-21', 'Nam', '08720322222', '0912345678', 'Công nghệ thông tin', 2),
(25, 'ngoctung20', 'qevxus@gmail.com', 'Nguyễn Trần Ngọc Tùng', '2024-06-21', 'Nam', '08720322222', '0912345678', 'Công nghệ thông tin', 6),
(30, 'ngoctung7', 'qevxus@gmail.com', 'Nguyễn Trần Ngọc Tùng', '2024-06-13', 'Nam', '08720322222', '0912345678', 'Quản trị kinh doanh', 9),
(31, 'ngoctung7', 'qevxus@gmail.com', 'Nguyễn Trần Ngọc Tùng', '2024-06-13', 'Nam', '08720322222', '0912345678', 'Quản trị kinh doanh', 9),
(32, 'Mongnghi', 'mongnghi@gmail.com', 'Nguyễn Thị Mộng Nghi', '2003-11-20', 'Nữ', '087203123456', '0123456789', 'Sư phạm tiểu học', 8),
(33, 'Mongnghi', 'mongnghi@gmail.com', 'Nguyễn Thị Mộng Nghi', '2003-11-20', 'Nữ', '087203123456', '0123456789', 'Sư phạm tiểu học', 8),
(34, 'Mongnghi', 'mongnghi@gmail.com', 'Nguyễn Thị Mộng Nghi', '2003-11-20', 'Nữ', '087203123456', '0123456789', 'Sư phạm tiểu học', 6),
(35, 'Mongnghi', 'mongnghi@gmail.com', 'Nguyễn Thị Mộng Nghi', '2003-11-20', 'Nữ', '087203123456', '0123456789', 'Sư phạm tiểu học', 6),
(36, 'Mongnghi', 'mongnghi@gmail.com', 'Nguyễn Thị Mộng Nghi', '2003-11-20', 'Nữ', '087203123456', '0123456789', 'Sư phạm tiểu học', 6),
(37, 'Mongnghi', 'mongnghi@gmail.com', 'Nguyễn Thị Mộng Nghi', '2003-11-20', 'Nữ', '087203123456', '0123456789', 'Sư phạm tiểu học', 6);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`) VALUES
(30, 'ngoctung7771', '$2y$10$VhObnz4s.r5z2ORHGxC21Ofn7IzOPAQQxA/W3to0hwlVXqbNi1GyG', 'tungngoc2308@gmail.com'),
(33, 'ngoctung20', '$2y$10$EmXSo9DWCLssi63a5lHaHOsUyV1hEyMpmLFAw5jFt.SgHwf1o6S/G', 'qevxus@gmail.com'),
(38, 'ngoctung27', '$2y$10$rMadrp1q8nD/pRkCi2v3YO72xb.prRTcAG/.j32RQwtoFWQhpSkwG', 'qevxus@gmail.com'),
(39, 'ngoctungdepchai', '$2y$10$tEh6.85vDaNQ98HeOk9/GuU9m23phX/b8TwpkbVqtyGTGHk4IIjMu', 'tungngic12@gmail.com'),
(45, 'ngoctung7', '$2y$10$6oc0NcYuJ7D1pUZxKKrYS.o47Z/Tmmret5LvIxw7Lo2vQuwguOFqC', 'qevxus@gmail.com'),
(46, 'Mongnghi', '$2y$10$0/aSUNlecAn4Bpb0ssW.5uZYdEIKqWQc3pIr.rywvRrYC084bFTJq', 'mongnghi@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `usersntd`
--

CREATE TABLE `usersntd` (
  `IdUserNTD` int(11) NOT NULL COMMENT 'AUTO_INCREMENT',
  `usernameNTD` varchar(50) NOT NULL,
  `passwordNTD` varchar(15) NOT NULL,
  `emailNTD` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `usersntd`
--

INSERT INTO `usersntd` (`IdUserNTD`, `usernameNTD`, `passwordNTD`, `emailNTD`) VALUES
(3, 'ngoctung7771', '$2y$10$bvEL.cc9', 'qevxus@gmail.com'),
(4, 'ngoctung12', '$2y$10$8STy4b6Y', 'ngoctungttt@gmail.com'),
(5, 'ngoctung13', '$2y$10$A8jsl4jl', 'ngoctungttt@gmail.com'),
(6, 'ngoctung14', '1', 'ngoctungttt@gmail.com'),
(7, 'ngoctung771', '1', 'ngocung@gmail.com');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hosouv`
--
ALTER TABLE `hosouv`
  ADD PRIMARY KEY (`MaHS`),
  ADD KEY `_user_id` (`user_id`);

--
-- Chỉ mục cho bảng `lich_phong_van`
--
ALTER TABLE `lich_phong_van`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `user_data_id` (`user_data_id`);

--
-- Chỉ mục cho bảng `nhatuyendung`
--
ALTER TABLE `nhatuyendung`
  ADD PRIMARY KEY (`MaNhaTD`);

--
-- Chỉ mục cho bảng `thongbao`
--
ALTER TABLE `thongbao`
  ADD PRIMARY KEY (`MaTB`),
  ADD KEY `MaUV` (`MaUV`);

--
-- Chỉ mục cho bảng `ungvien`
--
ALTER TABLE `ungvien`
  ADD PRIMARY KEY (`MaUV`),
  ADD KEY `FK_kMaNHaTD_id` (`MaNhaTD`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Chỉ mục cho bảng `usersntd`
--
ALTER TABLE `usersntd`
  ADD PRIMARY KEY (`IdUserNTD`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `hosouv`
--
ALTER TABLE `hosouv`
  MODIFY `MaHS` int(11) NOT NULL AUTO_INCREMENT COMMENT 'AUTO_INCREMENT', AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `lich_phong_van`
--
ALTER TABLE `lich_phong_van`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `nhatuyendung`
--
ALTER TABLE `nhatuyendung`
  MODIFY `MaNhaTD` int(11) NOT NULL AUTO_INCREMENT COMMENT 'AUTO_INCREMENT', AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `thongbao`
--
ALTER TABLE `thongbao`
  MODIFY `MaTB` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `ungvien`
--
ALTER TABLE `ungvien`
  MODIFY `MaUV` int(11) NOT NULL AUTO_INCREMENT COMMENT 'AUTO_INCREMENT', AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT cho bảng `usersntd`
--
ALTER TABLE `usersntd`
  MODIFY `IdUserNTD` int(11) NOT NULL AUTO_INCREMENT COMMENT 'AUTO_INCREMENT', AUTO_INCREMENT=8;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `hosouv`
--
ALTER TABLE `hosouv`
  ADD CONSTRAINT `_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `lich_phong_van`
--
ALTER TABLE `lich_phong_van`
  ADD CONSTRAINT `lich_phong_van_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `lich_phong_van_ibfk_2` FOREIGN KEY (`user_data_id`) REFERENCES `user_data` (`id`);

--
-- Các ràng buộc cho bảng `thongbao`
--
ALTER TABLE `thongbao`
  ADD CONSTRAINT `thongbao_ibfk_1` FOREIGN KEY (`MaUV`) REFERENCES `ungvien` (`MaUV`);

--
-- Các ràng buộc cho bảng `ungvien`
--
ALTER TABLE `ungvien`
  ADD CONSTRAINT `FK_kMaNHaTD_id` FOREIGN KEY (`MaNhaTD`) REFERENCES `nhatuyendung` (`MaNhaTD`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
