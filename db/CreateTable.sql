# uml: https://drive.google.com/file/d/0B-6wk5P5zOeTdzZmaEpvdkl3dWM/view?usp=sharing

use CS5200;

drop table if exists Comments;
drop table if exists Reviews;
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
	EmergencyServices bool,
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
  MiddleInitial varchar(255),
  Credentials varchar(255),
  Gender varchar(255),
  EntityType varchar(255),
  StreetAddress1 varchar(255),
  StreetAddress2 varchar(255),
  City varchar(255),
  ZipCode int(11) DEFAULT NULL,
  State varchar(255),
  ProviderType varchar(255),
  PlaceOfService varchar(255),
  OrganizationName varchar(45) DEFAULT NULL,
  constraint pk_Physicians_PrividerId primary key (ProviderId),
  constraint fk_Physicians_OrganizationName foreign key (OrganizationName)
  references Organizations(Name) on delete set null
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

INSERT ignore INTO Organizations 
(Name)
SELECT 
OrganizationName
FROM PhysiciansRaw;

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

INSERT INTO Physicians 
(ProviderId,LastName,FirstName,MiddleInitial,Credentials,Gender,EntityType,StreetAddress1,StreetAddress2,City,ZipCode,State,ProviderType,PlaceOfService,OrganizationName)
SELECT 
ProviderId,LastName,FirstName,MiddleInitial,Credentials,Gender,EntityType,StreetAddress1,StreetAddress2,City,ZipCode,State,ProviderType,PlaceOfService,OrganizationName
FROM PhysiciansRaw;

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

insert into Users values('physician', 'physician', 'PHYSICIAN', null, 1003004573, null, null);
insert into Users values('ordinary', 'ordinary', 'ORDINARY', null, null, 'foo', 'bar');
insert into Users values('Organization', 'Organization', 'ORGANIZATION', 12, null, 'foo', 'bar');


insert into Reviews 
(Content, Rating, UserName, Type, OrganizationId, ProviderId)
values('good', 5, 'physician', 'PhysicianReview', null, 1003004573);

insert into Reviews 
(Content, Rating, UserName, Type, OrganizationId, ProviderId)
values('bad', 4, 'Organization', 'OrganizationReview', 12, NULL);

insert into Comments 
(Content, UserName, ReviewId)
values('good', 'physician', 1);

insert into Comments 
(Content, UserName, ReviewId)
values('bad', 'Organization', 2);


