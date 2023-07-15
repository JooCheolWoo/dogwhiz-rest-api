INSERT INTO `banner` (`id`, `title`, `url`, `exposure`, `created_at`, `updated_at`, `deleted_at`) VALUES
                                                                                                      (1, '도그위즈 오픈', '', 1, '2023-07-05 17:02:01', '2023-07-05 17:02:01', NULL),
                                                                                                      (2, '사지말고 입양하세요', 'https://www.zooseyo.or.kr/zooseyo_or_kr.html', 1, '2023-07-05 17:05:51', '2023-07-05 17:05:51', NULL),
                                                                                                      (3, '포비는 귀여워', '', 1, '2023-07-05 17:06:59', '2023-07-05 17:06:59', NULL);


INSERT INTO `banner_file` (`banner_id`, `name`, `url`, `path`, `size`, `created_at`, `updated_at`, `deleted_at`) VALUES
                                                                                                                     (1, '001.png', 'https://dev.webserver.hellodogwhiz.com/banner/471a432a-89ff-4caa-9d8b-3abd021a4430.png', '/home/banner/471a432a-89ff-4caa-9d8b-3abd021a4430.png', 147132, '2023-07-05 17:02:01', '2023-07-05 17:02:01', NULL),
                                                                                                                     (2, '002.png', 'https://dev.webserver.hellodogwhiz.com/banner/a67126e8-fb84-495c-b93e-ec367fc774e6.png', '/home/banner/a67126e8-fb84-495c-b93e-ec367fc774e6.png', 338628, '2023-07-05 17:05:51', '2023-07-05 17:05:51', NULL),
                                                                                                                     (3, '003.png', 'https://dev.webserver.hellodogwhiz.com/banner/ad4f4fa3-d869-43d3-920f-10d85cae2e00.png', '/home/banner/ad4f4fa3-d869-43d3-920f-10d85cae2e00.png', 281497, '2023-07-05 17:06:59', '2023-07-05 17:06:59', NULL);


INSERT INTO `member` (`id`, `email`, `nickname`, `password`, `status`, `name`, `gender`, `birth`, `phone`, `image_url`, `image_path`, `login_ip`, `last_login_date`, `update_pwd_date`, `email_auth`, `email_key`, `created_at`, `updated_at`, `deleted_at`) VALUES
    (1, 'tkfkdal@naver.com', '포비', '$2a$10$OYWaoM7sFoynKOiDtg1Sv.SzjhxsuJZnd6jD2JgPF7KK/v1Wtyf0m', 'APPROVED', NULL, 0, NULL, NULL, 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', '/home/profile/e823155b-d273-45b7-980e-f3157e45b738.png', '192.168.45.1', '2023-07-16 06:33:36', '2023-07-16 06:32:35', 1, '6aUQmLkbw8xV1oF2xrXunhcGX4cRDK', '2023-07-16 06:32:35', NULL, NULL);


INSERT INTO `member_role` (`id`, `member_id`, `role`) VALUES
                                                          (2, 1, 'ADMIN_MASTER'),
                                                          (1, 1, 'USER_NORMAL');

INSERT INTO `board` (`id`, `member_id`, `writer`, `writer_image_url`, `category`, `sub_category`, `pin_to_top`, `title`, `content`, `like_count`, `view_count`, `created_at`, `updated_at`, `deleted_at`) VALUES
                                                                                                                                                                                                              (1, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지1', '테스트 내용1', 0, 0, '2023-07-16 06:33:53', '2023-07-16 06:33:53', NULL),
                                                                                                                                                                                                              (2, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지2', '테스트 내용2', 0, 0, '2023-07-16 06:33:58', '2023-07-16 06:33:58', NULL),
                                                                                                                                                                                                              (3, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지3', '테스트 내용3', 0, 0, '2023-07-16 06:34:03', '2023-07-16 06:34:03', NULL),
                                                                                                                                                                                                              (4, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지4', '테스트 내용4', 0, 0, '2023-07-16 06:34:08', '2023-07-16 06:34:08', NULL),
                                                                                                                                                                                                              (5, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지5', '테스트 내용5', 0, 0, '2023-07-16 06:34:13', '2023-07-16 06:34:13', NULL),
                                                                                                                                                                                                              (6, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 1, '테스트 공지6', '테스트 내용6', 0, 0, '2023-07-16 06:34:17', '2023-07-16 06:34:17', NULL),
                                                                                                                                                                                                              (7, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지7', '테스트 내용7', 0, 0, '2023-07-16 06:34:22', '2023-07-16 06:34:22', NULL),
                                                                                                                                                                                                              (8, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지8', '테스트 내용8', 0, 0, '2023-07-16 06:34:26', '2023-07-16 06:34:26', NULL),
                                                                                                                                                                                                              (9, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지9', '테스트 내용9', 0, 0, '2023-07-16 06:34:34', '2023-07-16 06:34:34', NULL),
                                                                                                                                                                                                              (10, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지10', '테스트 내용10', 0, 0, '2023-07-16 06:34:40', '2023-07-16 06:34:40', NULL),
                                                                                                                                                                                                              (11, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지11', '테스트 내용11', 0, 0, '2023-07-16 06:34:45', '2023-07-16 06:34:45', NULL),
                                                                                                                                                                                                              (12, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지12', '테스트 내용12', 0, 0, '2023-07-16 06:34:49', '2023-07-16 06:34:49', NULL),
                                                                                                                                                                                                              (13, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 1, '테스트 공지13', '테스트 내용13', 0, 0, '2023-07-16 06:34:54', '2023-07-16 06:34:54', NULL),
                                                                                                                                                                                                              (14, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지14', '테스트 내용14', 0, 0, '2023-07-16 06:34:59', '2023-07-16 06:34:59', NULL),
                                                                                                                                                                                                              (15, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지15', '테스트 내용15', 0, 0, '2023-07-16 06:35:04', '2023-07-16 06:35:04', NULL),
                                                                                                                                                                                                              (16, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지16', '테스트 내용16', 0, 0, '2023-07-16 06:35:09', '2023-07-16 06:35:09', NULL),
                                                                                                                                                                                                              (17, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지17', '테스트 내용17', 0, 0, '2023-07-16 06:35:13', '2023-07-16 06:35:13', NULL),
                                                                                                                                                                                                              (18, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지18', '테스트 내용18', 0, 0, '2023-07-16 06:35:17', '2023-07-16 06:35:17', NULL),
                                                                                                                                                                                                              (19, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지19', '테스트 내용19', 0, 0, '2023-07-16 06:35:22', '2023-07-16 06:35:22', NULL),
                                                                                                                                                                                                              (20, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지20', '테스트 내용20', 0, 0, '2023-07-16 06:35:27', '2023-07-16 06:35:27', NULL),
                                                                                                                                                                                                              (21, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 1, '테스트 공지21', '테스트 내용21', 0, 0, '2023-07-16 06:35:32', '2023-07-16 06:35:32', NULL),
                                                                                                                                                                                                              (22, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지22', '테스트 내용22', 0, 0, '2023-07-16 06:35:37', '2023-07-16 06:35:37', NULL),
                                                                                                                                                                                                              (23, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지23', '테스트 내용23', 0, 0, '2023-07-16 06:35:43', '2023-07-16 06:35:43', NULL),
                                                                                                                                                                                                              (24, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지24', '테스트 내용24', 0, 0, '2023-07-16 06:35:50', '2023-07-16 06:35:50', NULL),
                                                                                                                                                                                                              (25, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지25', '테스트 내용25', 0, 0, '2023-07-16 06:35:55', '2023-07-16 06:35:55', NULL),
                                                                                                                                                                                                              (26, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지26', '테스트 내용26', 0, 0, '2023-07-16 06:35:59', '2023-07-16 06:35:59', NULL),
                                                                                                                                                                                                              (27, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지27', '테스트 내용27', 0, 0, '2023-07-16 06:36:04', '2023-07-16 06:36:04', NULL),
                                                                                                                                                                                                              (28, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지28', '테스트 내용28', 0, 0, '2023-07-16 06:36:10', '2023-07-16 06:36:10', NULL),
                                                                                                                                                                                                              (29, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지29', '테스트 내용29', 0, 0, '2023-07-16 06:36:14', '2023-07-16 06:36:14', NULL),
                                                                                                                                                                                                              (30, 1, '포비', 'https://dev.webserver.hellodogwhiz.com/profile/e823155b-d273-45b7-980e-f3157e45b738.png', 'DWB010', 'DWB011', 0, '테스트 공지30', '테스트 내용30', 0, 0, '2023-07-16 06:36:20', '2023-07-16 06:36:20', NULL);






