use hotel;

# hotel 테이블
CREATE TABLE hotel
(
    `hotel_number`       INT             NOT NULL    AUTO_INCREMENT COMMENT '호텔 번호', 
    `hotel_name`         VARCHAR(45)     NOT NULL    COMMENT '호텔 이름', 
    `hotel_address`      VARCHAR(100)    NOT NULL    COMMENT '호텔 주소', 
    `hotel_telephone`    VARCHAR(45)     NOT NULL    COMMENT '호텔 연락처', 
    `hotel_information`  VARCHAR(500)    NOT NULL    COMMENT '호텔 정보', 
    `hotel_review`       VARCHAR(500)    NOT NULL    COMMENT '호텔 리뷰', 
     PRIMARY KEY (hotel_number)
);

ALTER TABLE hotel COMMENT '호텔정보';


# customer 테이블
CREATE TABLE customer
(
    `customer_id`  INT            NOT NULL    AUTO_INCREMENT COMMENT '고객번호', 
    `name`         VARCHAR(45)    NOT NULL    COMMENT '이름', 
    `id`           VARCHAR(45)    NOT NULL    COMMENT '아이디', 
    `password`     VARCHAR(45)    NOT NULL    COMMENT '비밀번호', 
    `email`        VARCHAR(45)    NOT NULL    COMMENT '이메일', 
    `phone`        VARCHAR(45)    NOT NULL    COMMENT '전화번호', 
    `address`      VARCHAR(45)    NOT NULL    COMMENT '주소', 
     PRIMARY KEY (customer_id)
);

ALTER TABLE customer COMMENT '고객정보';


# room 테이블
CREATE TABLE room
(
    `room_id`           INT             NOT NULL    AUTO_INCREMENT COMMENT '객실 번호', 
    `room_name`         VARCHAR(45)     NOT NULL    COMMENT '객실 이름', 
    `room_price`        INT             NOT NULL    COMMENT '객실 가격', 
    `room_people`       VARCHAR(45)     NOT NULL    COMMENT '객실 정원', 
    `room_information`  VARCHAR(45)    NOT NULL    COMMENT '객실 정보', 
    `room_review`       VARCHAR(45)    NOT NULL    COMMENT '객실 리뷰', 
    `hotel_id`          INT             NOT NULL    COMMENT '호텔 번호', 
     PRIMARY KEY (room_id, hotel_id)
);

ALTER TABLE room COMMENT '객실 정보';

ALTER TABLE room
    ADD CONSTRAINT FK_room_hotel_id_hotel_hotel_number FOREIGN KEY (hotel_id)
        REFERENCES hotel (hotel_number) ON DELETE RESTRICT ON UPDATE RESTRICT;


# orders 테이블
CREATE TABLE orders
(
    `order_id`             INT             NOT NULL    AUTO_INCREMENT COMMENT '예약 번호', 
    `customer_idx`         INT             NOT NULL    COMMENT '고객번호', 
    `order_name`           VARCHAR(45)     NOT NULL    COMMENT '이름', 
    `customer_phone`       VARCHAR(45)     NOT NULL    COMMENT '고객 연락처', 
    `order_hotel_id`       INT             NOT NULL    COMMENT '예약 호텔 번호', 
    `order_hotel`          VARCHAR(45)     NOT NULL    COMMENT '예약 호텔', 
    `order_hotel_address`  VARCHAR(100)    NOT NULL    COMMENT '예약 호텔 주소', 
    `hotel_phone`          VARCHAR(45)     NOT NULL    COMMENT '호텔 연락처', 
    `order_room_id`        INT             NOT NULL    COMMENT '예약 객실 번호', 
    `order_room`           VARCHAR(45)     NOT NULL    COMMENT '예약 객실', 
    `totel_people`         INT             NOT NULL    COMMENT '인원 수', 
    `check_in_date`        DATE            NOT NULL    COMMENT '체크인 날짜', 
    `check_out_date`       DATE            NOT NULL    COMMENT '체크아웃 날짜', 
    `amount`               INT             NOT NULL    COMMENT '금액', 
    `pay_day`              DATE            NOT NULL    COMMENT '결제일', 
     PRIMARY KEY (order_id)
);

ALTER TABLE orders COMMENT '예약정보';

ALTER TABLE orders
    ADD CONSTRAINT FK_orders_order_hotel_id_hotel_hotel_number FOREIGN KEY (order_hotel_id)
        REFERENCES hotel (hotel_number) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE orders
    ADD CONSTRAINT FK_orders_order_room_id_room_room_id FOREIGN KEY (order_room_id)
        REFERENCES room (room_id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE orders
    ADD CONSTRAINT FK_orders_customer_idx_customer_customer_id FOREIGN KEY (customer_idx)
        REFERENCES customer (customer_id) ON DELETE RESTRICT ON UPDATE RESTRICT;


# pay 테이블
CREATE TABLE pay
(
    `pay_id`          INT            NOT NULL    AUTO_INCREMENT COMMENT '결제 번호', 
    `order_pay_id`    INT            NOT NULL    COMMENT '예약 번호', 
    `pay`             INT            NOT NULL    COMMENT '결제 금액', 
    `pay_kind`        VARCHAR(45)    NOT NULL    COMMENT '결제 수단', 
    `pay_bank`        VARCHAR(45)    NOT NULL    COMMENT '결제 은행', 
    `pay_cardnumber`  VARCHAR(45)    NOT NULL    COMMENT '결제 카드 번호', 
     PRIMARY KEY (pay_id)
);

ALTER TABLE pay COMMENT '결제정보';

ALTER TABLE pay
    ADD CONSTRAINT FK_pay_order_pay_id_orders_order_id FOREIGN KEY (order_pay_id)
        REFERENCES orders (order_id) ON DELETE RESTRICT ON UPDATE RESTRICT;
        
# board 테이블
CREATE TABLE board
(
    `board_id`  INT             NOT NULL    AUTO_INCREMENT COMMENT '게시판 번호', 
    `board_type`    VARCHAR(45) NOT NULL        COMMENT '게시판 유형', 
    `content_name`  VARCHAR(45) NOT NULL        COMMENT '제목', 
    `content`  VARCHAR(500)     NOT NULL    COMMENT '내용', 
     PRIMARY KEY (board_id)
);

ALTER TABLE board COMMENT '게시판';
