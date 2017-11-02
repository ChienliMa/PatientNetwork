#PatientSurveyHospital to SurveyResult
DROP TABLE IF EXISTS SurveyItem;

CREATE TABLE SurveyItem (
    MeasureId TEXT,
    Question TEXT,
    AnswerDescription TEXT
);


DROP TABLE IF EXISTS SurveyResult;
CREATE TABLE SurveyResult (
    ResultItemId INT(11) NOT NULL AUTO_INCREMENT,
    ProviderId INT(11),
    SurveyItem TEXT,
    Measurement ENUM('Star Rating', 'Answer Percentage', 'Mean Value'),
    StatisticalResult INT(11),
    CONSTRAINT pk_SurveyResult_ResultItemId PRIMARY KEY (ResultItemId),
    CONSTRAINT fk_SurveyResult_ProviderId FOREIGN KEY (ProviderId)
        REFERENCES Hospitals (ProviderId)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_SurveyResult_SurveyItem FOREIGN KEY (SurveyItem)
        REFERENCES SurveyItem (MeasureId)
        ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO SurveyResult
(SurveyItem,
ProviderId,
Measurement,
StatisticalResult)
SELECT
    `HCAHPS Measure ID`,
    `Provider ID`,
  CASE
    WHEN NOT `Patient Survey Star Rating` = 'Not Applicable' THEN 'Star Rating'
    WHEN NOT `HCAHPS Answer Percent` = 'Not Applicable' THEN 'Answer Percentage'
    WHEN NOT `HCAHPS Linear Mean Value` = 'Not Applicable' THEN 'Mean Value'
  END,
  CASE
    WHEN NOT `Patient Survey Star Rating` = 'Not Applicable' 
      THEN if(`Patient Survey Star Rating` = 'Not Available', NULL, `Patient Survey Star Rating`)
    WHEN NOT `HCAHPS Answer Percent` = 'Not Applicable' 
      THEN if(`HCAHPS Answer Percent`  = 'Not Available', NULL, `HCAHPS Answer Percent`)
    WHEN NOT `HCAHPS Linear Mean Value` = 'Not Applicable'
      THEN if(`HCAHPS Linear Mean Value` = 'Not Available', NULL, `HCAHPS Linear Mean Value`)
  END
FROM PatientSurveyHospital_WA;
