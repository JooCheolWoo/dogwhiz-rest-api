INSERT INTO `role` (`id`, `type_code`, `status_code`) VALUES
                                                          (1, 'ADMIN_MASTER', 'APPROVED'),
                                                          (2, 'ADMIN_MASTER', 'PENDING'),
                                                          (3, 'ADMIN_MASTER', 'SUSPENDED'),
                                                          (4, 'ADMIN_MASTER', 'DEACTIVATED'),
                                                          (5, 'ADMIN_MANAGER', 'APPROVED'),
                                                          (6, 'ADMIN_MANAGER', 'PENDING'),
                                                          (7, 'ADMIN_MANAGER', 'SUSPENDED'),
                                                          (8, 'ADMIN_MANAGER', 'DEACTIVATED'),
                                                          (9, 'ADMIN_NORMAL', 'APPROVED'),
                                                          (10, 'ADMIN_NORMAL', 'PENDING'),
                                                          (11, 'ADMIN_NORMAL', 'SUSPENDED'),
                                                          (12, 'ADMIN_NORMAL', 'DEACTIVATED'),
                                                          (13, 'SELLER_CORPORATE', 'APPROVED'),
                                                          (14, 'SELLER_CORPORATE', 'PENDING'),
                                                          (15, 'SELLER_CORPORATE', 'SUSPENDED'),
                                                          (16, 'SELLER_CORPORATE', 'DEACTIVATED'),
                                                          (17, 'SELLER_INDIVIDUAL', 'APPROVED'),
                                                          (18, 'SELLER_INDIVIDUAL', 'PENDING'),
                                                          (19, 'SELLER_INDIVIDUAL', 'SUSPENDED'),
                                                          (20, 'SELLER_INDIVIDUAL', 'DEACTIVATED'),
                                                          (21, 'USER_SPECIAL', 'APPROVED'),
                                                          (22, 'USER_SPECIAL', 'PENDING'),
                                                          (23, 'USER_SPECIAL', 'SUSPENDED'),
                                                          (24, 'USER_SPECIAL', 'DEACTIVATED'),
                                                          (25, 'USER_CERTIFIED', 'APPROVED'),
                                                          (26, 'USER_CERTIFIED', 'PENDING'),
                                                          (27, 'USER_CERTIFIED', 'SUSPENDED'),
                                                          (28, 'USER_CERTIFIED', 'DEACTIVATED'),
                                                          (29, 'USER_NORMAL', 'APPROVED'),
                                                          (30, 'USER_NORMAL', 'PENDING'),
                                                          (31, 'USER_NORMAL', 'SUSPENDED'),
                                                          (32, 'USER_NORMAL', 'DEACTIVATED');


INSERT INTO `banner` (`id`, `title`, `url`, `exposure`, `created_at`, `updated_at`, `deleted_at`) VALUES
                                                                                                      (1, '도그위즈 오픈', '', 1, '2023-07-05 17:02:01', '2023-07-05 17:02:01', NULL),
                                                                                                      (2, '사지말고 입양하세요', 'https://www.zooseyo.or.kr/zooseyo_or_kr.html', 1, '2023-07-05 17:05:51', '2023-07-05 17:05:51', NULL),
                                                                                                      (3, '포비는 귀여워', '', 1, '2023-07-05 17:06:59', '2023-07-05 17:06:59', NULL);


INSERT INTO `banner_file` (`banner_id`, `name`, `url`, `path`, `size`, `created_at`, `updated_at`, `deleted_at`) VALUES
                                                                                                                     (1, '001.png', 'https://dev.webserver.hellodogwhiz.com/banner/471a432a-89ff-4caa-9d8b-3abd021a4430.png', '/home/banner/471a432a-89ff-4caa-9d8b-3abd021a4430.png', 147132, '2023-07-05 17:02:01', '2023-07-05 17:02:01', NULL),
                                                                                                                     (2, '002.png', 'https://dev.webserver.hellodogwhiz.com/banner/a67126e8-fb84-495c-b93e-ec367fc774e6.png', '/home/banner/a67126e8-fb84-495c-b93e-ec367fc774e6.png', 338628, '2023-07-05 17:05:51', '2023-07-05 17:05:51', NULL),
                                                                                                                     (3, '003.png', 'https://dev.webserver.hellodogwhiz.com/banner/ad4f4fa3-d869-43d3-920f-10d85cae2e00.png', '/home/banner/ad4f4fa3-d869-43d3-920f-10d85cae2e00.png', 281497, '2023-07-05 17:06:59', '2023-07-05 17:06:59', NULL);








