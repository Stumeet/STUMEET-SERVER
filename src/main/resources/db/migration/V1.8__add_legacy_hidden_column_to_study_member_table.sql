ALTER TABLE study_member
    ADD COLUMN is_legacy_hidden TINYINT(1) NOT NULL DEFAULT false;