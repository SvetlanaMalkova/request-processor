CREATE TABLE IF NOT EXISTS notification_outbox
(
    id         UUID PRIMARY KEY NOT NULL,
    created_at TIMESTAMP        NOT NULL DEFAULT now(),
    topic      VARCHAR          NOT NULL,
    key        VARCHAR          NOT NULL,
    value      TEXT             NOT NULL,
    sent       BOOLEAN          NOT NULL DEFAULT false,
    attempt    INTEGER          NOT NULL DEFAULT 1
);
