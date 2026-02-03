CREATE TABLE `person`
(
    `id`         BIGINT NOT NULL auto_increment,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name`  VARCHAR(50) NOT NULL,
    `birth_day`  DATE DEFAULT NULL,
    `address`    VARCHAR(255) NOT NULL,
    `gender`     INTEGER NOT NULL,
    PRIMARY KEY (`id`)
);