-- Start dropping tables --
DROP TABLE IF EXISTS Municipality CASCADE;
DROP TABLE IF EXISTS Area CASCADE;
DROP TABLE IF EXISTS Zone CASCADE;
DROP TABLE IF EXISTS MunicipalManager CASCADE;
DROP TABLE IF EXISTS ControlStaff CASCADE;
DROP TABLE IF EXISTS Citizen CASCADE;
DROP TABLE IF EXISTS Service CASCADE;
DROP TABLE IF EXISTS ServiceType CASCADE;
DROP TABLE IF EXISTS AreaPeriod CASCADE;
DROP TABLE IF EXISTS Reservation CASCADE;
-- End dropping tables --

-- Start creating tables --
CREATE TABLE Municipality(
  id INTEGER PRIMARY KEY,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NULL,
  name TEXT NOT NULL,
  CONSTRAINT municipality_ca1 UNIQUE (name, sign_up),
  CONSTRAINT municipality_ca2 UNIQUE (name, sign_down),
  CONSTRAINT municipality_c1 CHECK (sign_down IS NULL OR sign_up < sign_down)
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
  CONSTRAINT area_ca1 UNIQUE (name, municipality, sign_up),
  CONSTRAINT area_ca2 UNIQUE (name, municipality, sign_down),
  CONSTRAINT area_caMunicipality FOREIGN KEY (municipality) REFERENCES Municipality(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT area_c1 CHECK (sign_down IS NULL OR sign_up < sign_down),
  CONSTRAINT area_eType CHECK (type >= 0 AND type <= 5)
);

CREATE TABLE AreaPeriod(
  id INTEGER PRIMARY KEY,
  schedule_start DATE NOT NULL,
  schedule_end DATE NULL,
  period_start TIME NOT NULL,
  period_end TIME NOT NULL,
  area INTEGER NOT NULL,
  CONSTRAINT area_period_ca1 UNIQUE (area, schedule_start, schedule_end, period_start),
  CONSTRAINT area_period_ca2 UNIQUE (area, schedule_start, schedule_end, period_end),
  CONSTRAINT area_period_c1 CHECK (schedule_start < schedule_end),
  CONSTRAINT area_period_c2 CHECK (period_start < period_end),
  CONSTRAINT area_period_caArea FOREIGN KEY (area) REFERENCES Area(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE Zone(
  id INTEGER PRIMARY KEY,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NULL,
  name TEXT NOT NULL,
  area INTEGER NOT NULL,
  capacity INTEGER NOT NULL,
  CONSTRAINT zone_ca1 UNIQUE (name, area, sign_up),
  CONSTRAINT zone_ca2 UNIQUE (name, area, sign_down),
  CONSTRAINT zone_caArea FOREIGN KEY (area) REFERENCES Area(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT zone_c1 CHECK (sign_down IS NULL OR sign_up < sign_down),
  CONSTRAINT zone_c3 CHECK (capacity > 0)
);

CREATE TABLE ControlStaff(
  id INTEGER PRIMARY KEY,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NOT NULL,
  identification TEXT NOT NULL,
  name TEXT NOT NULL,
  mail TEXT NOT NULL,
  password TEXT NOT NULL,
  area_period INTEGER NOT NULL,
  CONSTRAINT control_staff_ca1 UNIQUE (identification, sign_up),
  CONSTRAINT control_staff_ca2 UNIQUE (identification, sign_down),
  CONSTRAINT control_staff_c1 CHECK (mail LIKE '%@%'),
  CONSTRAINT control_staff_caAreaPeriod FOREIGN KEY (area_period) REFERENCES AreaPeriod(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT control_staff_c2 CHECK (sign_down IS NULL OR sign_up < sign_down)
);

CREATE TABLE MunicipalManager(
  id INTEGER PRIMARY KEY,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NOT NULL,
  identification TEXT NOT NULL,
  name TEXT NOT NULL,
  mail TEXT NOT NULL,
  password TEXT NOT NULL,
  municipality INTEGER NOT NULL,
  phone TEXT NOT NULL,
  CONSTRAINT municipal_manager_ca1 UNIQUE (identification, sign_up),
  CONSTRAINT municipal_manager_ca2 UNIQUE (identification, sign_down),
  CONSTRAINT municipal_manager_c1 CHECK (mail LIKE '%@%'),
  CONSTRAINT municipal_manager_c2 CHECK (LENGTH(phone) >= 9),
  CONSTRAINT municipal_manager_caMunicipality FOREIGN KEY (municipality) REFERENCES Municipality(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT municipal_manager_c3 CHECK (sign_down IS NULL OR sign_up < sign_down)
);

CREATE TABLE Citizen(
  id INTEGER PRIMARY KEY,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NOT NULL,
  identification TEXT NOT NULL,
  name TEXT NOT NULL,
  mail TEXT NOT NULL,
  password TEXT NOT NULL,
  country INTEGER NOT NULL,
  town TEXT NOT NULL,
  address TEXT NOT NULL,
  CONSTRAINT citizen_ca1 UNIQUE (identification, sign_up),
  CONSTRAINT citizen_ca2 UNIQUE (identification, sign_down),
  CONSTRAINT citizen_c1 CHECK (mail LIKE '%@%'),
  CONSTRAINT citizen_c2 CHECK (sign_down IS NULL OR sign_up < sign_down),
  CONSTRAINT citizen_eCountry CHECK (country >= 0 AND country <= 248)
);

CREATE TABLE ServiceType(
  id INTEGER PRIMARY KEY,
  name TEXT NOT NULL,
  CONSTRAINT service_type_ca1 UNIQUE (name)
);

CREATE TABLE Service(
  id INTEGER PRIMARY KEY,
  schedule_start DATE NOT NULL,
  schedule_end DATE NOT NULL,
  period_start TIME NOT NULL,
  period_end TIME NOT NULL,
  area INTEGER NOT NULL,
  service_type INTEGER NOT NULL,
  CONSTRAINT service_c1 CHECK (schedule_start < schedule_end),
  CONSTRAINT service_c2 CHECK (period_start < period_end),
  CONSTRAINT service_caServiceType FOREIGN KEY (service_type) REFERENCES ServiceType(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT service_caArea FOREIGN KEY (area) REFERENCES Area(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE Reservation(
  id INTEGER PRIMARY KEY,
  code INTEGER NOT NULL,
  area_period INTEGER NOT NULL,
  date DATE NOT NULL,
  citizen INTEGER NOT NULL,
  occupied INTEGER NOT NULL,
  enter TIME NULL,
  exit TIME NULL,
  zone INTEGER NOT NULL,
  CONSTRAINT reservation_ca1 UNIQUE (code),
  CONSTRAINT reservation_c2 CHECK (enter < exit),
  CONSTRAINT reservation_c3 CHECK (occupied > 0),
  CONSTRAINT reservation_caCitizen FOREIGN KEY (citizen) REFERENCES Citizen(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT reservation_caZone FOREIGN KEY (zone) REFERENCES Zone(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT reservation_caArea_period FOREIGN KEY (area_period) REFERENCES AreaPeriod(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- End creating tables --