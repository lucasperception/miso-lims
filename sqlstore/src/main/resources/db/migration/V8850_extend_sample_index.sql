ALTER TABLE SampleIndexFamily
  ADD COLUMN platformType varchar(20) NULL,
  ADD COLUMN archived tinyint(1) NULL DEFAULT 0,
  ADD COLUMN fakeSequence tinyint(1) NULL DEFAULT 0,
  ADD COLUMN uniqueDualIndex tinyint NULL DEFAULT 0;

ALTER TABLE SampleIndex
    ADD COLUMN sequence varchar(120) NULL,
    ADD COLUMN position int NULL DEFAULT 1,
    ADD COLUMN realSequences varchar(255) NULL,
    MODIFY COLUMN name varchar(50) NOT NULL;

ALTER TABLE Sample
    ADD COLUMN index2Id bigint NULL;
