use CS5200;


drop table if exists Users;
drop table if exists Physicians;
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
	NPI int(11) DEFAULT NULL,
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
    references Organizations(OrganizationId) on delete cascade
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

-- DATA MIGRATION --

INSERT ignore INTO Organizations 
(Name)
SELECT 
OrganizationName
FROM PhysiciansRaw;

INSERT ignore INTO Organizations 
(Address,City,State,ZipCode,Phone,Location)
SELECT 
Address,City,State,`ZIP Code`,`Phone Number`,Location
FROM HospitalInformation_WA;

INSERT INTO Physicians 
(ProviderId,LastName,FirstName,MiddleInitial,Credentials,Gender,EntityType,StreetAddress1,StreetAddress2,City,ZipCode,State,ProviderType,PlaceOfService,OrganizationName)
SELECT 
ProviderId,LastName,FirstName,MiddleInitial,Credentials,Gender,EntityType,StreetAddress1,StreetAddress2,City,ZipCode,State,ProviderType,PlaceOfService,OrganizationName
FROM PhysiciansRaw;
