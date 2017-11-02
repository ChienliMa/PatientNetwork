#PatientSurveyHospital to SurveyResult
DROP TABLE IF EXISTS SurveyResult;
DROP TABLE IF EXISTS SurveyItem;

CREATE TABLE SurveyItem (
    MeasureId VARCHAR(255),
    Question TEXT,
    AnswerDescription TEXT,
    CONSTRAINT pk_SurveyItem_MeasureId PRIMARY KEY(MeasureId)
);

CREATE TABLE SurveyResult (
    ResultItemId INT(11) NOT NULL AUTO_INCREMENT,
    ProviderId INT(11),
    SurveyItem VARCHAR(255),
    Measurement ENUM('Star Rating', 'Answer Percentage', 'Mean Value'),
    StatisticalResult INT(11),
    CONSTRAINT pk_SurveyResult_ResultItemId PRIMARY KEY (ResultItemId),
    CONSTRAINT fk_SurveyResult_ProviderId FOREIGN KEY (ProviderId)
        REFERENCES Hospitals (NPI)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_SurveyResult_SurveyItem FOREIGN KEY (SurveyItem)
        REFERENCES SurveyItem (MeasureId)
        ON UPDATE CASCADE ON DELETE CASCADE
);

-- INSERT INTO SurveyItem(
-- MeasureId,
-- Question,
-- AnswerDescription)
-- SELECT
--     `HCAHPS Measure ID`,
--     `HCAHPS Question`,
--     `HCAHPS Answer Description`
-- FROM PatientSurveyHospital_WA;
-- 
-- INSERT INTO SurveyResult(
-- SurveyItem,
-- ProviderId,
-- Measurement,
-- StatisticalResult)
-- SELECT
--     `HCAHPS Measure ID`,
--     `Provider ID`,
--   CASE
--     WHEN NOT `Patient Survey Star Rating` = 'Not Applicable' THEN 'Star Rating'
--     WHEN NOT `HCAHPS Answer Percent` = 'Not Applicable' THEN 'Answer Percentage'
--     WHEN NOT `HCAHPS Linear Mean Value` = 'Not Applicable' THEN 'Mean Value'
--   END,
--   CASE
--     WHEN NOT `Patient Survey Star Rating` = 'Not Applicable' 
--       THEN IF(`Patient Survey Star Rating` = 'Not Available', NULL, `Patient Survey Star Rating`)
--     WHEN NOT `HCAHPS Answer Percent` = 'Not Applicable' 
--       THEN IF(`HCAHPS Answer Percent`  = 'Not Available', NULL, `HCAHPS Answer Percent`)
--     WHEN NOT `HCAHPS Linear Mean Value` = 'Not Applicable'
--       THEN IF(`HCAHPS Linear Mean Value` = 'Not Available', NULL, `HCAHPS Linear Mean Value`)
--   END
-- FROM PatientSurveyHospital_WA;
