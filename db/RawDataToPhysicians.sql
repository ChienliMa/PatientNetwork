-- data source
-- https://www.cms.gov/Research-Statistics-Data-and-Systems/Statistics-Trends-and-Reports/Medicare-Provider-Charge-Data/index.html

-- load data local infile 'Volumes/BANK/Medicare_Provider_Utilization_and_Payment_Data__Physician_and_Other_Supplier_PUF_CY2015.csv'
-- into table RawData
-- FIELDS TERMINATED BY ',' ENCLOSED BY '"'
-- LINES TERMINATED BY '\n'
-- IGNORE 1 LINES;

use CS5200;

drop table if exists Physicians;
create table Physicians(
PhysicianId int(11) not null,
LastName text,
FirstName text,
MiddleInitial text,
Credentials text,
Gender text, 
EntityType text,
StreetAddress1 text,
StreetAddress2 text,
City text,
ZipCode int(11),
State text,
ProviderType text,
PlaceOfService text,
constraint pk_Physicians_Id primary key (PhysicianId)
);

INSERT ignore INTO Physicians(PhysicianId,        LastName,                    FirstName, MiddleInitial, Credentials, Gender, EntityType,StreetAddress1,StreetAddress2,City,ZipCode,State,ProviderType,PlaceOfService)
select                        NationalProviderId, `LastName/OrganizationName`, Firstname, MiddleInitial, Credentials, Gender, EntityType,StreetAddress1,StreetAddress2,City,ZipCode,State,ProviderType,PlaceOfService
from RawData limit 9445728Physicians;

ALTER TABLE Physicians MODIFY COLUMN Gender ENUM('M','F');
