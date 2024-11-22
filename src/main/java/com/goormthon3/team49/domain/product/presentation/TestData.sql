
--테이블 삭제
DROP TABLE IF EXISTS comment, product, product_reservation, school, user;




-- user 테이블에 데이터 추가
INSERT INTO user (user_id, kakao_user_id, user_name, school, latitude, longitude, activated, created_at, profile_image_uri)
VALUES
(1, 1234567890, 'Test User', 'Test Uni', 37.5665, 126.9780, TRUE, CURRENT_TIMESTAMP, 'https://example.com/image.jpg');

-- school 테이블에 데이터 추가
INSERT INTO school (school_id, school_name, domain)
VALUES
(1, 'Test School', 'testschool.com');


-- product 테이블에 데이터 추가
INSERT INTO product (school_id, product_id, title, price, save_price, link, pickup_location, total_num, current_num, is_active, created_at)
VALUES
(1, 1, 'Test Product', 10000, 5000, 'http://example.com', 'Test Location', 100, 100, TRUE, CURRENT_TIMESTAMP);

-- product_reservation 테이블에 데이터 추가
INSERT INTO product_reservation (user_id, product_id, school_id, quantity, role, created_at)
VALUES
(1, 1, 1, 2, '참여자', CURRENT_TIMESTAMP);

