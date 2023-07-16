INSERT INTO `banner` (`id`, `title`, `url`, `exposure`, `created_at`, `updated_at`, `deleted_at`)
VALUES
    (1, '도그위즈 오픈', '', 1, '2023-07-05 17:02:01', '2023-07-05 17:02:01', NULL),
    (2, '사지말고 입양하세요', 'https://www.zooseyo.or.kr/zooseyo_or_kr.html', 1, '2023-07-05 17:05:51', '2023-07-05 17:05:51', NULL),
    (3, '포비는 귀여워', '', 1, '2023-07-05 17:06:59', '2023-07-05 17:06:59', NULL);


INSERT INTO `banner_file` (`banner_id`, `name`, `url`, `path`, `size`, `created_at`, `updated_at`, `deleted_at`)
VALUES
    (1, '001.png', 'https://dev.webserver.hellodogwhiz.com/banner/471a432a-89ff-4caa-9d8b-3abd021a4430.png', '/home/banner/471a432a-89ff-4caa-9d8b-3abd021a4430.png', 147132, '2023-07-05 17:02:01', '2023-07-05 17:02:01', NULL),
    (2, '002.png', 'https://dev.webserver.hellodogwhiz.com/banner/a67126e8-fb84-495c-b93e-ec367fc774e6.png', '/home/banner/a67126e8-fb84-495c-b93e-ec367fc774e6.png', 338628, '2023-07-05 17:05:51', '2023-07-05 17:05:51', NULL),
    (3, '003.png', 'https://dev.webserver.hellodogwhiz.com/banner/ad4f4fa3-d869-43d3-920f-10d85cae2e00.png', '/home/banner/ad4f4fa3-d869-43d3-920f-10d85cae2e00.png', 281497, '2023-07-05 17:06:59', '2023-07-05 17:06:59', NULL);



INSERT INTO `category` (`id`, `name`, `read_auth`, `cud_auth`)
VALUES
    (1, '공지사항', NULL, 'ADMIN_MASTER/ADMIN_MANAGER/ADMIN_NORMAL'),
    (2, '커뮤니티', NULL, NULL);


INSERT INTO `sub_category` (`id`, `category_id`, `name`)
VALUES
    (1, 2, '일상'),
    (2, 2, '재미'),
    (3, 2, '자랑'),
    (4, 2, '정보공유'),
    (5, 2, '질문'),
    (6, 1, '일반'),
    (7, 1, '필독'),
    (8, 1, '긴급');




