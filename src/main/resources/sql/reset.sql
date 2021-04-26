-- Drop all the tables --
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
DROP TABLE IF EXISTS ReservationZone CASCADE;
-- End of dropping all the tables --

-- Start creating the tables --
CREATE TABLE Municipality(
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NULL,
  CONSTRAINT ca1 UNIQUE (name, sign_up),
  CONSTRAINT ca2 UNIQUE (name, sign_down),
  CONSTRAINT c1 CHECK (sign_down IS NULL OR sign_up < sign_down),
);

CREATE TABLE Area(
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NULL,
  type INTEGER NOT NULL,
  description TEXT NOT NULL,
  location TEXT NOT NULL,
  municipality INTEGER NOT NULL,
  CONSTRAINT ca1 UNIQUE (name, sign_up),
  CONSTRAINT ca2 UNIQUE (name, sign_down),
  CONSTRAINT caMunicipality FOREIGN KEY (municipality) REFERENCES Municipality(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT c1 CHECK (sign_down IS NULL OR sign_up < sign_down),
  CONSTRAINT eType CHECK (type >= 0 AND type <= 5)
);

CREATE TABLE Zone(
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NULL,
  capacity INTEGER NOT NULL,
  area INTEGER NOT NULL,
  CONSTRAINT ca1 UNIQUE (name, sign_up),
  CONSTRAINT ca2 UNIQUE (name, sign_down),
  CONSTRAINT caArea FOREIGN KEY (area) REFERENCES Area(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT c1 CHECK (sign_down IS NULL OR sign_up < sign_down),
  CONSTRAINT c3 CHECK (capacity > 0)
);

CREATE TABLE ControlStaff(
  id SERIAL PRIMARY KEY,
  identification TEXT NOT NULL,
  name TEXT NOT NULL,
  password TEXT NOT NULL,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NOT NULL,
  area_period INTEGER NOT NULL,
  CONSTRAINT ca1 UNIQUE (name, identification),
  CONSTRAINT ca2 UNIQUE (name, sign_up),
  CONSTRAINT ca3 UNIQUE (name, sign_down),
  CONSTRAINT caAreaPeriod FOREIGN KEY (area_period) REFERENCES AreaPeriod(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT c1 CHECK (sign_down IS NULL OR sign_up < sign_down)
);

CREATE TABLE MunicipalManager(
  id SERIAL PRIMARY KEY,
  identification TEXT NOT NULL,
  name TEXT NOT NULL,
  password TEXT NOT NULL,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NOT NULL,
  phone TEXT NOT NULL,
  municipality INTEGER NOT NULL,
  CONSTRAINT ca1 UNIQUE (name, identification),
  CONSTRAINT ca2 UNIQUE (name, sign_up),
  CONSTRAINT ca3 UNIQUE (name, sign_down),
  CONSTRAINT caMunicipality FOREIGN KEY (municipality) REFERENCES Municipality(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT c1 CHECK (sign_down IS NULL OR sign_up < sign_down)
);

CREATE TABLE Citizen(
  id SERIAL PRIMARY KEY,
  identification TEXT NOT NULL,
  name TEXT NOT NULL,
  password TEXT NOT NULL,
  sign_up TIMESTAMP NOT NULL,
  sign_down TIMESTAMP NOT NULL,
  mail TEXT NOT NULL,
  country INTEGER NOT NULL,
  town TEXT NOT NULL,
  address TEXT NOT NULL,
  CONSTRAINT ca1 UNIQUE (name, identification),
  CONSTRAINT ca2 UNIQUE (name, sign_up),
  CONSTRAINT ca3 UNIQUE (name, sign_down),
  CONSTRAINT c1 CHECK (sign_down IS NULL OR sign_up < sign_down),
  CONSTRAINT eCountry CHECK (country >= 0 AND country <= 248)
);

CREATE TABLE Service(
  id SERIAL PRIMARY KEY,
  serviceType INTEGER NOT NULL,
  schedule_start DATE NOT NULL,
  schedule_end DATE NOT NULL,
  period_start TIME NOT NULL,
  period_end TIME NOT NULL,
  CONSTRAINT c1 CHECK (schedule_start < schedule_end),
  CONSTRAINT c2 CHECK (period_start < period_end),
  CONSTRAINT caServiceType FOREIGN KEY (serviceType) REFERENCES ServiceType(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE ServiceType(
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  CONSTRAINT ca1 UNIQUE(name)
);

CREATE TABLE AreaPeriod(
  id SERIAL PRIMARY KEY,
  area INTEGER NOT NULL,
  schedule_start DATE NOT NULL,
  schedule_end DATE NOT NULL,
  period_start TIME NOT NULL,
  period_end TIME NOT NULL,
  CONSTRAINT c1 CHECK (schedule_start < schedule_end),
  CONSTRAINT c2 CHECK (period_start < period_end),
  CONSTRAINT caArea FOREIGN KEY (area) REFERENCES Area(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE Reservation(
  id SERIAL PRIMARY KEY,
  code INTEGER NOT NULL,
  date DATE NOT NULL,
  occupied INTEGER NOT NULL,
  enter TIME NOT NULL,
  exit TIME NOT NULL,
  citizen INTEGER NOT NULL,
  area_period INTEGER NOT NULL,
  CONSTRAINT ca1 UNIQUE (name, code),
  CONSTRAINT c2 CHECK (enter < exit),
  CONSTRAINT c2 CHECK (occupied > 0),
  CONSTRAINT caCitizen FOREIGN KEY (citizen) REFERENCES Citizen(id) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT caArea_period FOREIGN KEY (area_period) REFERENCES AreaPeriod(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE ReservationZone(
  id SERIAL PRIMARY KEY,
  reservation INTEGER NOT NULL,
  zone INTEGER NOT NULL,
  CONSTRAINT ca1 UNIQUE(reservation, zone),
  CONSTRAINT caReservation FOREIGN KEY (reservation) REFERENCES Reservation(id) ON DELETE RESTRICT ON UPDATE CASCADE
  CONSTRAINT caZone FOREIGN KEY (zone) REFERENCES Zone(id) ON DELETE RESTRICT ON UPDATE CASCADE
);
-- All Tables created --