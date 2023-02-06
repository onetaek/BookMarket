create schema book_market;
create table `book_market`.member
(
    id bigint primary key auto_increment,
    user_id varchar(255),
    password varchar(255),
    name varchar(255),
    gender varchar(255),
    birth varchar(255),
    mail varchar(255),
    phone varchar(255),
    address varchar(255),
    regist_day varchar(255)
)DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

create table `book_market`.book
(
    id bigint primary key auto_increment,
    book_id varchar(255),
    name varchar(255),
    unit_price int(255),
    author varchar(255),
    description varchar(255),
    publisher varchar(255),
    category varchar(255),
    units_in_stock bigint,
    total_pages bigint,
    release_date varchar(255),
    `condition` varchar(255),
    filename varchar(255)
)DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

create table `book_market`.cart
(
    id bigint primary key auto_increment,
    member_id bigint,
    book_id bigint,
    order_no varchar(255),
    name varchar(255),
    unit_price int,
    cnt int,
    insert_date datetime default now()
)DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

create table `book_market`.`order_date`
(
    id bigint primary key auto_increment,
    order_no varchar(255),
    cart_id int(255),
    book_id varchar(255),
    book_name varchar(255),
    unit_price int(255),
    cnt int(255),
    sum_price int(255)
)DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE TABLE `book_market`.`order_info`
(
    `order_no`        varchar(50),                              # 주문 번호
    `member_id`       varchar(50),                              # 주문자 아이디
        `order_name`      varchar(20),                              # 주문자 이름
        `order_tel`       varchar(20),                              # 주문자 전화번호
        `order_email`     varchar(30),                              # 주문자 이메일

        `receive_name`    varchar(20),                              # 받는이 이름
        `receive_tel`     varchar(20),                              # 받는이 전화번호
        `receive_address` varchar(200),                             # 받는이 주소

        `pay_amount`      int(255),
    `pay_method`      varchar(20) null     default null,        # 결제 방법 선택
        `carry_no`        varchar(20) null     default null,        # 운반 번호
        `order_step`      varchar(20) not null default 'orderFail', # 주문 단계

        `date_order`      datetime    not null,                     # 주문 일
        `date_pay`        datetime    null     default null,        # 입금 일
        `date_carry`      datetime    null     default null,        # 배송 일
        `date_done`       date        null     default null,        # 배송 완료 일
        constraint primary key (`order_no`)
)DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;