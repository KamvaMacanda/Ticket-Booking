CREATE DATABASE IF NOT EXISTS RAILROAD; 

use RAILROAD ; 


CREATE TABLE TRAIN (
Train_ID INT AUTO_INCREMENT PRIMARY KEY,
Train_Name VARCHAR (50) NOT NULL , 
Total_Seats INT NOT NULL CHECK (Total_Seat>0 ) , 
Open_Seats INT NOT NULL , 
Train_type ENUM ("Bullet " , "Rush-hour" , "Regular" )NOT NULL ,
Created_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
 Status ENUM( "Inactive" , "Active" , "Repairs " ) DEFAULT "Active " , 
 CONSTRAINT read_Open_Seats CHECK (Open_Seats <= Total_Seats) 

); 
 
 CREATE TABLE STATION (  
 Station_ID INT AUTO_INCREMENT PRIMARY KEY , 
 Station_Name VARCHAR(50) NOT NULL , 
 Station_Code VARCHAR (60) NOT NULL , 
 City VARCHAR (50) NOT NULL,  
 Created_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
 ); 


CREATE TABLE  SCHEDULE( 
Schedule_ID INT AUTO_INCREMENT PRIMARY KEY , 
Train_ID INT NOT NULL , 
Station_ID INT NOT NULL , 
Arrival_time TIME ,
Departure_time TIME , 
Platform_Number INT , 
Weekdays ENUM ("Monday" , "Tuesday " , "Wednesday" , "Thursday" , "Friday" ) , 
FOREIGN KEY (Train_ID ) REFERENCES TRAIN(Train_ID) ON DELETE CASCADE  ,  
FOREIGN KEY (Station_ID) REFERENCES STATION(Station_ID) ON DELETE CASCADE ,  
UNIQUE KEY stop_rule ( Train_ID , Station_ID ,Platform_Number ) 
) ;


CREATE TABLE USERS ( 
User_ID INT AUTO_INCREMENT PRIMARY KEY , 
First_Name VARCHAR (30) NOT NULL , 
Surname VARCHAR(30) NOT NULL 

) ;


-- booking recipets and details 

CREATE TABLE BOOKINGS (  
Booking_ID INT AUTO_INCREMENT PRIMARY KEY ,
User_ID INT NOT NULL,
Train_ID INT NOT NULL, 
Departure_StationID INT NOT NULL, 
Destination_StationID INT NOT NULL ,
Booking_Date TIMESTAMP DEFAULT CURRENT_TIMESTAMP , 
Seat_Number VARCHAR(5)  , 
Seat_Type ENUM (" Economy " , " Business" , "Private" , " Presidential " ) NOT NULL ,
Stauts ENUM ( "Booked " , "Pending " , " Cancelled" ) DEFAULT "Pending ", 
FOREIGN KEY ( User_ID ) REFERENCES USERS(User_ID) ON DELETE CASCADE , 
 FOREIGN KEY ( Train_ID) REFERENCES Train(Train_ID) ON DELETE CASCADE ,
 FOREIGN KEY (Departure_StationID ) REFERENCES STATION (Staion_ID) , 
 FOREIGN KEY (Destination_StationID ) REFERENCES STATION (Staion_ID) 
 
);

-- SAMPLE DATA TO TEST  FOR TRAIN
INSERT INTO TRAIN (Train_Name ,Train_Type, Total_Seats, Open_Seats, Status)VALUES 
('Flash express ', 'Superfast', 500, 450, 'Active'),
('Metro', 'Express', 400, 380, 'Active'),
('masihambi', 'Local', 800, 750, 'Active'),
('Gautrain Express', 'Superfast', 500, 450, 'Active');


-- samples for station 
INSERT INTO STATION (Station_Name, Station_Code, City) VALUES  
('Cape town main station' , 'CPT' , 'Cape town' ) , 
('Pretoria Station', 'PTA', 'Pretoria'),
('Mobrwey station' ,'MWB' , 'Cape town') , 
('kalk Bay' , 'KLK' , 'Cape Town '  ) ,
 ('Bellville Station', 'BEL', 'Cape Town'),
 ('Paarl Station', 'PRL', 'Paarl'); 
 
 
 -- Insert Schedule (Cape Town routes)
INSERT INTO SCHEDULE (Train_ID, Station_ID, Stop_Number, Arrival_Time, Departure_Time, Distance_From_Origin, Platform_Number, Day_Of_Week) VALUES

(1, 1, 1, NULL, '08:00:00', 0.00, 1, 'Daily'),        
(1, 3, 2, '08:15:00', '08:18:00', 8.50, 2, 'Daily'), 
(1, 4, 3, '08:35:00', NULL, 25.00, 1, 'Daily'),      

-- Metro Route 
(2, 1, 1, NULL, '10:00:00', 0.00, 3, 'Daily'),
(2, 3, 2, '10:18:00', '10:20:00', 8.50, 1, 'Daily'),
(2, 4, 3, '10:40:00', NULL, 25.00, 2, 'Daily'),

-- Masihambi Route 
(3, 1, 1, NULL, '14:00:00', 0.00, 4, 'Daily'),
(3, 3, 2, '14:20:00', '14:25:00', 8.50, 3, 'Daily'),
(3, 4, 3, '14:50:00', NULL, 25.00, 1, 'Daily');
 
INSERT INTO USERS (First_Name, Surname) VALUES
('Thabo', 'Mbeki'),
('Zanele', 'Nkosi'),
('Pieter', 'van der Merwe'),
('Lerato', 'Mokoena'),
('Johan', 'Botha'),
('Nomsa', 'Dlamini'); 


INSERT INTO BOOKINGS (User_ID, Train_ID, Departure_StationID, Destination_StationID, Seat_Number, Seat_Type, Status) VALUES
(1, 1, 1, 4, 'A1-23', 'Business', 'Booked'),
(2, 2, 1, 3, 'B2-45', 'Economy', 'Booked'),
(3, 3, 1, 4, 'S3-12', 'Economy', 'Booked'),
(4, 4, 1, 6, 'E1-10', 'Business', 'Pending'),
(5, 1, 3, 4, 'A2-34', 'Economy', 'Booked'),
(6, 2, 1, 4, 'B1-05', 'Business', 'Booked'); 
 
 
 
 -- View 1: Train Schedule with Station Names
CREATE OR REPLACE VIEW view_train_schedule AS
SELECT 
    t.Train_Name,
    t.Train_Type,
    s.Station_Name,
    s.Station_Code,
    s.City,
    sc.Stop_Number,
    sc.Arrival_Time,
    sc.Departure_Time,
    sc.Distance_From_Origin,
    sc.Platform_Number,
    sc.Day_Of_Week 
    
    FROM SCHEDULE sc
JOIN TRAIN t ON sc.Train_ID = t.Train_ID
JOIN STATION s ON sc.Station_ID = s.Station_ID
ORDER BY t.Train_Name, sc.Stop_Number;
 View 2: Booking Details
CREATE OR REPLACE VIEW view_booking_details AS
SELECT 
    b.Booking_ID,
    CONCAT(u.First_Name, ' ', u.Surname) AS Passenger_Name,
    t.Train_Name,
    t.Train_Type,
    fs.Station_Name AS From_Station,
    ts.Station_Name AS To_Station,
    b.Booking_Date,
    b.Seat_Number,
    b.Seat_Type,
    b.Status
FROM BOOKINGS b
JOIN USERS u ON b.User_ID = u.User_ID
JOIN TRAIN t ON b.Train_ID = t.Train_ID
JOIN STATION fs ON b.Departure_StationID = fs.Station_ID
JOIN STATION ts ON b.Destination_StationID = ts.Station_ID;

-- View 3: Train Availability
CREATE OR REPLACE VIEW vw_train_availability AS
SELECT 
    t.Train_ID,
    t.Train_Name,
    t.Train_Type,
    t.Total_Seats,
    t.Open_Seats,
    (t.Total_Seats - t.Open_Seats) AS Booked_Seats,
    ROUND((t.Open_Seats / t.Total_Seats) * 100, 2) AS Availability_Percentage,
    t.Status
FROM TRAIN t
ORDER BY t.Train_Name;

 



