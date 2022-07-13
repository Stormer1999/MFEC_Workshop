CREATE TABLE ADDRESS
(
    id          BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id     BIGINT UNSIGNED NOT NULL,
    line_1      VARCHAR(512)    NOT NULL,
    line_2      VARCHAR(512)    NOT NULL,
    postcode    VARCHAR(20)     NOT NULL,
    type        VARCHAR(20)     NOT NULL,
    prefer      CHAR(1)         NOT NULL DEFAULT ('N'),
    delete_flag CHAR(1)         NOT NULL DEFAULT ('N'),
    created_by  BIGINT UNSIGNED NOT NULL DEFAULT (1),
    created_at  DATETIME        NOT NULL,
    updated_by  BIGINT UNSIGNED,
    updated_at  DATETIME,
    deleted_by  BIGINT UNSIGNED,
    deleted_at  DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES USER (id) ON DELETE CASCADE
)