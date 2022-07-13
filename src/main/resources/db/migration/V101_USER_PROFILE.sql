CREATE TABLE USER_PROFILE
(
    id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id       BIGINT UNSIGNED NOT NULL,
    first_name_th VARCHAR(256)    NOT NULL,
    last_name_th  VARCHAR(256)    NOT NULL,
    first_name_en VARCHAR(256)    NOT NULL,
    last_name_en  VARCHAR(256)    NOT NULL,
    mobile_number VARCHAR(20),
    birth_date    DATE,
    delete_flag   CHAR(1)         NOT NULL DEFAULT ('N'),
    created_by    BIGINT UNSIGNED NOT NULL DEFAULT (1),
    created_at    DATETIME        NOT NULL,
    updated_by    BIGINT UNSIGNED,
    updated_at    DATETIME,
    deleted_by    BIGINT UNSIGNED,
    deleted_at    DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES USER (id) ON DELETE CASCADE
)