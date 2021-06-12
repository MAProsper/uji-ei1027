-- Start dropping tables --
DROP TABLE IF EXISTS Municipality CASCADE;
DROP TABLE IF EXISTS Area CASCADE;
DROP TABLE IF EXISTS Zone CASCADE;
DROP TABLE IF EXISTS MunicipalManager CASCADE;
DROP TABLE IF EXISTS EnviromentalManager CASCADE;
DROP TABLE IF EXISTS ControlStaff CASCADE;
DROP TABLE IF EXISTS Citizen CASCADE;
DROP TABLE IF EXISTS Service CASCADE;
DROP TABLE IF EXISTS ServiceType CASCADE;
DROP TABLE IF EXISTS AreaPeriod CASCADE;
DROP TABLE IF EXISTS Reservation CASCADE;
DROP TABLE IF EXISTS ReservationZone CASCADE;
DROP TABLE IF EXISTS Mail CASCADE;
-- End dropping tables --

-- Start creating tables --
CREATE TABLE Municipality(
  id INTEGER PRIMARY KEY,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NULL,
  name TEXT NOT NULL,
  CONSTRAINT municipality_unique_name_signup UNIQUE (name, sign_up),
  CONSTRAINT municipality_unique_name_signdown UNIQUE (name, sign_down),
  CONSTRAINT municipality_check_signable CHECK (sign_down IS NULL OR sign_up < sign_down)
);

CREATE TABLE Area(
  id INTEGER PRIMARY KEY,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NULL,
  name TEXT NOT NULL,
  municipality INTEGER NOT NULL,
  type INTEGER NOT NULL,
  description TEXT NOT NULL,
  location TEXT NOT NULL,
  image TEXT NULL,
  CONSTRAINT area_unique_name_municipality_signup UNIQUE (name, municipality, sign_up),
  CONSTRAINT area_unique_name_municipality_signdown UNIQUE (name, municipality, sign_down),
  CONSTRAINT area_references_municipality FOREIGN KEY (municipality) REFERENCES Municipality(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT area_check_signable CHECK (sign_down IS NULL OR sign_up < sign_down),
  CONSTRAINT area_check_type CHECK (type >= 0 AND type <= 5)
);

CREATE TABLE AreaPeriod(
  id INTEGER PRIMARY KEY,
  schedule_start DATE NOT NULL,
  schedule_end DATE NULL,
  period_start TIME NOT NULL,
  period_end TIME NOT NULL,
  area INTEGER NOT NULL,
  CONSTRAINT area_period_unique_area_schedule_periodstart UNIQUE (area, schedule_start, schedule_end, period_start),
  CONSTRAINT area_period_unique_area_schedule_periodend UNIQUE (area, schedule_start, schedule_end, period_end),
  CONSTRAINT area_period_check_scheduable CHECK (schedule_start <= schedule_end),
  CONSTRAINT area_period_check_periodable CHECK (period_start < period_end),
  CONSTRAINT area_period_references_area FOREIGN KEY (area) REFERENCES Area(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE Zone(
  id INTEGER PRIMARY KEY,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NULL,
  name TEXT NOT NULL,
  area INTEGER NOT NULL,
  capacity INTEGER NOT NULL,
  CONSTRAINT zone_unique_area_signup UNIQUE (name, area, sign_up),
  CONSTRAINT zone_unique_area_signdown UNIQUE (name, area, sign_down),
  CONSTRAINT zone_references_area FOREIGN KEY (area) REFERENCES Area(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT zone_check_signable CHECK (sign_down IS NULL OR sign_up < sign_down),
  CONSTRAINT zone_check_capacity CHECK (capacity > 0)
);

CREATE TABLE ControlStaff(
  id INTEGER PRIMARY KEY,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NULL,
  identification TEXT NOT NULL,
  name TEXT NOT NULL,
  mail TEXT NOT NULL,
  password TEXT NOT NULL,
  area_period INTEGER NOT NULL,
  CONSTRAINT control_staff_unique_identification_signup UNIQUE (identification, sign_up),
  CONSTRAINT control_staff_unique_identification_signdown UNIQUE (identification, sign_down),
  CONSTRAINT control_staff_check_mail CHECK (mail LIKE '%@%'),
  CONSTRAINT control_staff_references_period FOREIGN KEY (area_period) REFERENCES AreaPeriod(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT control_staff_check_signable CHECK (sign_down IS NULL OR sign_up < sign_down)
);

CREATE TABLE EnviromentalManager(
  id INTEGER PRIMARY KEY,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NULL,
  identification TEXT NOT NULL,
  name TEXT NOT NULL,
  mail TEXT NOT NULL,
  password TEXT NOT NULL,
  CONSTRAINT enviromental_manager_unique_identification_signup UNIQUE (identification, sign_up),
  CONSTRAINT enviromental_manager_unique_identification_signdown UNIQUE (identification, sign_down),
  CONSTRAINT enviromental_manager_check_mail CHECK (mail LIKE '%@%'),
  CONSTRAINT enviromental_manager_check_signable CHECK (sign_down IS NULL OR sign_up < sign_down)
);

CREATE TABLE MunicipalManager(
  id INTEGER PRIMARY KEY,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NULL,
  identification TEXT NOT NULL,
  name TEXT NOT NULL,
  mail TEXT NOT NULL,
  password TEXT NOT NULL,
  municipality INTEGER NOT NULL,
  phone TEXT NOT NULL,
  CONSTRAINT municipal_manager_unique_identification_signup UNIQUE (identification, sign_up),
  CONSTRAINT municipal_manager_unique_identification_signdown UNIQUE (identification, sign_down),
  CONSTRAINT municipal_manager_check_mail CHECK (mail LIKE '%@%'),
  CONSTRAINT municipal_manager_check_phone CHECK (LENGTH(phone) >= 9),
  CONSTRAINT municipal_manager_references_municipality FOREIGN KEY (municipality) REFERENCES Municipality(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT municipal_manager_check_signable CHECK (sign_down IS NULL OR sign_up < sign_down)
);

CREATE TABLE Citizen(
  id INTEGER PRIMARY KEY,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NULL,
  identification TEXT NOT NULL,
  name TEXT NOT NULL,
  mail TEXT NOT NULL,
  password TEXT NOT NULL,
  country INTEGER NOT NULL,
  city TEXT NOT NULL,
  address TEXT NOT NULL,
  CONSTRAINT citizen_unique_identification_signup UNIQUE (identification, sign_up),
  CONSTRAINT citizen_unique_identification_signdown UNIQUE (identification, sign_down),
  CONSTRAINT citizen_check_mail CHECK (mail LIKE '%@%'),
  CONSTRAINT citizen_check_signable CHECK (sign_down IS NULL OR sign_up < sign_down),
  CONSTRAINT citizen_check_country CHECK (country >= 0 AND country <= 248)
);

CREATE TABLE ServiceType(
  id INTEGER PRIMARY KEY,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NULL,
  name TEXT NOT NULL,
  CONSTRAINT service_type_check_signable CHECK (sign_down IS NULL OR sign_up < sign_down),
  CONSTRAINT service_type_unique_name_signup UNIQUE (name, sign_up),
  CONSTRAINT service_type_unique_name_signdown UNIQUE (name, sign_down)
);

CREATE TABLE Service(
  id INTEGER PRIMARY KEY,
  schedule_start DATE NOT NULL,
  schedule_end DATE NULL,
  period_start TIME NOT NULL,
  period_end TIME NOT NULL,
  area INTEGER NOT NULL,
  service_type INTEGER NOT NULL,
  CONSTRAINT service_check_scheduable CHECK (schedule_start <= schedule_end),
  CONSTRAINT service_check_periodable CHECK (period_start < period_end),
  CONSTRAINT service_references_type FOREIGN KEY (service_type) REFERENCES ServiceType(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT service_references_area FOREIGN KEY (area) REFERENCES Area(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE Reservation(
  id INTEGER PRIMARY KEY,
  area_period INTEGER NOT NULL,
  date DATE NOT NULL,
  citizen INTEGER NOT NULL,
  occupied INTEGER NOT NULL,
  enter TIME NULL,
  exit TIME NULL,
  CONSTRAINT reservation_check_io CHECK (enter < exit),
  CONSTRAINT reservation_check_occupied CHECK (occupied > 0),
  CONSTRAINT reservation_references_citizen FOREIGN KEY (citizen) REFERENCES Citizen(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT reservation_references_period FOREIGN KEY (area_period) REFERENCES AreaPeriod(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE ReservationZone(
  id INTEGER PRIMARY KEY,
  reservation INTEGER NOT NULL,
  zone INTEGER NOT NULL,
  CONSTRAINT reservationZone_unique_reservation_zone UNIQUE (reservation, zone),
  CONSTRAINT reservationZone_references_reservation FOREIGN KEY (reservation) REFERENCES Reservation(id) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT reservationZone_references_zone FOREIGN KEY (zone) REFERENCES Zone(id) ON DELETE RESTRICT ON UPDATE CASCADE
);


--Correo simulado--

CREATE TABLE Mail(
  id INTEGER PRIMARY KEY,
  mail TEXT NOT NULL,
  subject TEXT NOT NULL,
  body TEXT NOT NULL,
  CONSTRAINT mail_check_mail CHECK (mail LIKE '%@%')
);
-- End creating tables --