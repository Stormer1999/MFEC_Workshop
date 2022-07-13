CREATE TABLE USER
(
    id           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_name    VARCHAR(256)    NOT NULL UNIQUE,
    password     VARCHAR(512)    NOT NULL,
    is_enabled   CHAR(1)         NOT NULL DEFAULT ('Y'),
    is_locked    CHAR(1)         NOT NULL DEFAULT ('N'),
    expired_date DATETIME,
    deleted_flag  CHAR(1)         NOT NULL DEFAULT ('N'),
    created_by   BIGINT UNSIGNED NOT NULL DEFAULT (1),
    created_at   DATETIME        NOT NULL,
    updated_by   BIGINT UNSIGNED,
    updated_at   DATETIME,
    deleted_by   BIGINT UNSIGNED,
    deleted_at    DATETIME,
    PRIMARY KEY (id)
);