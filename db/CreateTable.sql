# uml: https://drive.google.com/file/d/0B-6wk5P5zOeTdzZmaEpvdkl3dWM/view?usp=sharing

use CS5200;

drop table if exists Comments;
drop table if exists Reviews;
drop table if exists PlacesOfService;
drop table if exists Specialties;
drop table if exists Users;
drop table if exists Physicians;
DROP TABLE IF EXISTS SurveyResult;
DROP TABLE IF EXISTS SurveyItem;
drop table if exists Hospitals;
drop table if exists Organizations;


create table Organizations(
	OrganizationId int auto_increment,
    Name  varchar(255) not null unique,
    Address varchar(255) default '',
    City varchar(255) default '',
    State varchar(2) default '',
    ZipCode int,
    Phone int,
    Location varchar(255) default '',
	constraint pk_Organizations_OrganizationId primary key (OrganizationId)
);

create table Hospitals(
	OrganizationId int(11) not null,
	NPI int(11) not null unique,
	Type text,
	Ownership varchar(255),
	EmergencyServices text,
	OverAllRating text,
	MortalityNationalComparison enum('SAME', 'ABOVE', 'BELOW') default null,
	SafetyNationalComparison enum('SAME', 'ABOVE', 'BELOW') default null,
	ReadmissionNationalComparison enum('SAME', 'ABOVE', 'BELOW') default null,
	PatientExperienceNationalComparison enum('SAME', 'ABOVE', 'BELOW') default null,
	CareEffectivenessNationalComparison enum('SAME', 'ABOVE', 'BELOW') default null,
	TimelinessNationalComparison enum('SAME', 'ABOVE', 'BELOW') default null,
	MedicalImageUsageEfficientNationalComparison enum('SAME', 'ABOVE', 'BELOW') default null,
    constraint pk_Hospitals_OrganizationId primary key (OrganizationId),
    constraint fk_Hospitals_OrganizationId foreign key (OrganizationId) 
    references Organizations(OrganizationId)
);

CREATE TABLE Physicians (
  ProviderId int(11) NOT NULL,
  LastName varchar(255),
  FirstName varchar(255),
  MiddleName varchar(255),
  Credential varchar(255),
  Gender varchar(255),
  StreetAddress1 varchar(255),
  StreetAddress2 varchar(255),
  City varchar(255),
  ZipCode int(11) DEFAULT NULL,
  State varchar(255),
  PrimarySpecialty varchar(255),
  SecondarySpecialties text,
  constraint pk_Physicians_PrividerId primary key (ProviderId)
);

create table Specialties (
  ProviderId int(11) NOT NULL,
  Speciality  text,
  IsPrimary boolean
);

create table PlacesOfService (
	ProviderId int(11) not null,
    OrganizationName varchar(255)
);

Create table Users(
  Username varchar(255),
  Password varchar(255),
  Type enum('ORDINARY', 'ORGANIZATION', 'PHYSICIAN'),
  OrganizationId int,
  PhysicianId int,
  FirstName varchar(255) default '',
  LastName varchar(255) default '',
  constraint pk_Users_Username primary key (UserName),
  constraint fk_Users_OrganizationId foreign key (OrganizationId)
  references Organizations(OrganizationId) on delete cascade,
  constraint fk_Users_PhysicianId foreign key (PhysicianId)
  references Physicians(ProviderId) on delete cascade
);


Create table Reviews(
  ReviewId int auto_increment,
  Created timestamp,
  Content text,
  Rating int,
  UserName varchar(55),
  Type enum('PhysicianReview', 'OrganizationReview'),
  OrganizationId int,
  ProviderId int,
  constraint pk_Reviews_ReviewId primary key (ReviewId),
  constraint fk_Reviews_UserName foreign key (UserName)
  references Users(UserName) on delete set null,
  constraint fk_Reviews_Physicians foreign key (ProviderId)
  references Physicians(ProviderId) on delete set null,
  constraint fk_Organazations_OrganizationId foreign key (OrganizationId)
  references Organizations(OrganizationId) on delete set null
);

Create table Comments(
  CommentId int auto_increment,
  Created timestamp,
  Content text,
  UserName varchar(55),
  ReviewId int,
  constraint pk_Comments_CommentId primary key (CommentId),
  constraint fk_Comments_UserName foreign key (UserName)
  references Users(UserName) on delete set null,
  constraint fk_Comments_ReviewId foreign key (ReviewId)
  references Reviews(ReviewId) on delete cascade
);

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

-- DATA MIGRATION --
SET SQL_SAFE_UPDATES = 0;


-- Organizations
INSERT ignore INTO Organizations 
	(Name)
select distinct Name
from 
	(select distinct OrganizationLegalName as Name  from Physician_Compare_National_WA) as o
	union select distinct HospitalLBN1 as Name from Physician_Compare_National_WA
	union select distinct HospitalLBN2 as Name from Physician_Compare_National_WA
	union select distinct HospitalLBN3 as Name from Physician_Compare_National_WA
	union select distinct HospitalLBN4 as Name from Physician_Compare_National_WA
	union select distinct HospitalLBN5 as Name from Physician_Compare_National_WA;

delete from Organizations where Name = '';

UPDATE 
	Organizations o,
	HospitalInformation_WA h
SET
	o.Address = h.Address,
	o.City = h.City,
	o.State = h.State,
	o.ZipCode = h.`ZIP Code`,
	o.Phone = h.`Phone Number`,
	o.Location = h.Location
Where
	o.Name = h.`Hospital Name` ;

update
	Organizations o,
    Physician_Compare_National_WA p
SET
	o.Address = case when p.StreetAddress2 = '' then concat(p.StreetAddress1)
					 else concat(p.StreetAddress1, ', ', p.StreetAddress2) end,
    o.City = p.City,
    o.State = p.State,
    o.ZipCode = p.ZipCode,
    o.Phone = p.Phone
where
	o.Name = p.OrganizationLegalName and o.City = '';

-- Hospitals
insert ignore into Hospitals
(`OrganizationId`,
`NPI`,
`Type`,
`Ownership`,
`EmergencyServices`,
`OverAllRating`,
`MortalityNationalComparison`,
`SafetyNationalComparison`,
`ReadmissionNationalComparison`,
`PatientExperienceNationalComparison`,
`CareEffectivenessNationalComparison`,
`TimelinessNationalComparison`,
`MedicalImageUsageEfficientNationalComparison`)
select
	o.OrganizationId, h.`Provider ID`, h.`Hospital Type`, h.`Hospital Ownership`, h.`Emergency Services`,
	h.`Hospital overall rating`, 
    case when h.`Mortality national comparison` like 'Above%' then 'ABOVE' when h.`Mortality national comparison` like 'Same%' then 'SAME' when h.`Mortality national comparison` like 'Below%' then 'BELOW' end,
	case when h.`Safety of care national comparison` like '%Above%' then 'ABOVE' when h.`Safety of care national comparison` like 'Same%' then 'SAME' when h.`Safety of care national comparison` like 'Below%' then 'BELOW' end,
    case when h.`Readmission national comparison` like '%Above%' then 'ABOVE' when h.`Readmission national comparison` like 'Same%' then 'SAME' when h.`Readmission national comparison` like 'Below%' then 'BELOW' end,
    case when h.`Patient experience national comparison` like '%Above%' then 'ABOVE' when  h.`Patient experience national comparison` like 'Same%' then 'SAME' when h.`Patient experience national comparison` like 'Below%' then 'BELOW' end,
    case when h.`Effectiveness of care national comparison` like '%Above%' then 'ABOVE' when h.`Effectiveness of care national comparison` like 'Same%' then 'SAME' when h.`Effectiveness of care national comparison` like 'Below%' then 'BELOW' end,
    case when h.`Timeliness of care national comparison` like '%Above%' then 'ABOVE' when h.`Timeliness of care national comparison`  like 'Same%' then 'SAME' when h.`Timeliness of care national comparison` like 'Below%' then 'BELOW' end,
    case when h.`Efficient use of medical imaging national comparison` like '%Above%' then 'ABOVE' when h.`Efficient use of medical imaging national comparison` like 'Same%' then 'SAME' when h.`Efficient use of medical imaging national comparison` like 'Below%' then 'BELOW' end
from
	HospitalInformation_WA as h inner join Organizations as o on h.`Hospital Name` = o.Name;

INSERT ignore INTO Physicians 
	(ProviderId,LastName,FirstName,MiddleName,Credential,Gender,StreetAddress1,StreetAddress2,City,ZipCode,State, PrimarySpecialty, SecondarySpecialties)
SELECT 
	NPI,        LastName,FirstName,MiddleName,Credential,Gender,StreetAddress1,StreetAddress2,City,ZipCode,State, PrimarySpecialty, ALLSecondarySpecialties
FROM Physician_Compare_National_WA;


-- PlacesOfService
insert into PlacesOfService
select NPI, OrganizationLegalName from Physician_Compare_National_WA where OrganizationLegalName != ''
union select NPI, HospitalLBN1 from Physician_Compare_National_WA where HospitalLBN1 != ''
union select NPI, HospitalLBN2 from Physician_Compare_National_WA where HospitalLBN2 != ''
union select NPI, HospitalLBN3 from Physician_Compare_National_WA where HospitalLBN3 != ''
union select NPI, HospitalLBN4 from Physician_Compare_National_WA where HospitalLBN4 != ''
union select NPI, HospitalLBN5 from Physician_Compare_National_WA where HospitalLBN5 != '';

-- Specialties
insert into Specialties
select NPI, PrimarySpecialty, true from Physician_Compare_National_WA where PrimarySpecialty != '' 
union select NPI, SecondarySpecialty1, false from Physician_Compare_National_WA where SecondarySpecialty1 != '' 
union select NPI, SecondarySpecialty2, false  from Physician_Compare_National_WA where SecondarySpecialty2 != '' 
union select NPI, SecondarySpecialty3, false  from Physician_Compare_National_WA where SecondarySpecialty3 != '' 
union select NPI, SecondarySpecialty4, false  from Physician_Compare_National_WA where SecondarySpecialty4 != '';

INSERT INTO SurveyItem(
MeasureId,
Question,
AnswerDescription)
SELECT
    `HCAHPS Measure ID`,
    `HCAHPS Question`,
    `HCAHPS Answer Description`
FROM PatientSurveyHospital_WA
GROUP BY `HCAHPS Measure ID`;



-- TEST DATA INSERTATION --

insert into Users values('physician', 'physician', 'PHYSICIAN', null, 1003009796, null, null);
insert into Users values('ordinary', 'ordinary', 'ORDINARY', null, null, 'foo', 'bar');
insert into Users values('Organization', 'Organization', 'ORGANIZATION', 12, null, 'foo', 'bar');


insert into Reviews 
(Content, Rating, UserName, Type, OrganizationId, ProviderId)
values('good', 5, 'physician', 'PhysicianReview', null, 1003009796);

insert into Reviews 
(Content, Rating, UserName, Type, OrganizationId, ProviderId)
values('bad', 4, 'Organization', 'OrganizationReview', 12, NULL);

insert into Comments 
(Content, UserName, ReviewId)
values('good', 'physician', 1);

insert into Comments 
(Content, UserName, ReviewId)
values('bad', 'Organization', 2);


