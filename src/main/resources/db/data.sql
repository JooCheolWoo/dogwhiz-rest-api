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

INSERT INTO `permission` (`id`, `name`) VALUES
                                            (1, '권한1'),
                                            (2, '권한2'),
                                            (3, '권한3'),
                                            (4, '권한4'),
                                            (5, '권한5');

INSERT INTO `banner` (`id`, `title`, `url`, `exposure`, `created_at`, `updated_at`, `deleted_at`) VALUES
                      (1, '배너1', 'www.naver.com', 1, '2023-07-03 07:44:23', '2023-07-03 07:44:23', NULL),
                      (2, '배너2', 'www.naver.com', 1, '2023-07-03 07:44:32', '2023-07-03 07:44:32', NULL),
                      (3, '배너3', 'www.naver.com', 1, '2023-07-03 07:44:40', '2023-07-03 07:44:40', NULL);


INSERT INTO `banner_file` (`id`, `banner_id`, `name`, `url`, `path`, `size`, `created_at`, `updated_at`, `deleted_at`) VALUES
(1, 1, '1.jpg', 'https://dev.webserver.hellodogwhiz.com/profile/72ed0b42-f2b0-4bdf-8e73-c31fe6e77f33.jpg', '/home/profile/72ed0b42-f2b0-4bdf-8e73-c31fe6e77f33.jpg', 14910, '2023-07-03 07:44:23', '2023-07-03 07:44:23', NULL),
(2, 2, '2.png', 'https://dev.webserver.hellodogwhiz.com/profile/d6d9e6dc-818c-4459-aacf-96f02cbc828d.png', '/home/profile/d6d9e6dc-818c-4459-aacf-96f02cbc828d.png', 634100, '2023-07-03 07:44:32', '2023-07-03 07:44:32', NULL),
(3, 3, '3.jpg', 'https://dev.webserver.hellodogwhiz.com/profile/fcdb8903-5221-4754-9841-9125c2a9adec.jpg', '/home/profile/fcdb8903-5221-4754-9841-9125c2a9adec.jpg', 112456, '2023-07-03 07:44:40', '2023-07-03 07:44:40', NULL);




