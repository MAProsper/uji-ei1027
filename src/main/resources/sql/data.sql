INSERT INTO Municipality VALUES(1, '2021-01-01 00:00:00', '2021-01-01 12:00:00', 'Benicassim');
INSERT INTO Area VALUES(1, '2021-01-01 00:00:00', '2021-01-01 12:00:00', 'area1', 1, 1, 'A very nice description', 'location', 'http://example.com/');
INSERT INTO AreaPeriod VALUES(1, '2021-01-01', '2021-01-02', '00:00:00', '12:00:00', 1);
INSERT INTO Citizen VALUES(1, '2021-01-01 00:00:00', '2021-01-01 12:00:00', 'identification01', 'Carlos', 'CarlosEmail@gmial.com', 'password', 12, 'Benicassim', 'addres');
INSERT INTO ControlStaff VALUES(1, '2021-01-01 00:00:00', '2021-01-01 12:00:00', 'identification02', 'Alberto', 'AlbertoEmail@gmail.com', 'querty', 1);
INSERT INTO MunicipalManager VALUES(1, '2021-01-01 00:00:00', '2021-01-01 12:00:00', 'identification03', 'Juan', 'JuanEmail@gmail.com', '123456', 1, '653577395');
INSERT INTO EnviromentalManager VALUES(1, '2021-01-01 00:00:00', '2021-01-01 12:00:00', 'identification04', 'Pepe', 'PepeEmail@gmail.com', '123456');
INSERT INTO Reservation VALUES(1, 1, '2021-01-02', 1, 12, '00:00:00', '12:00:00');
INSERT INTO Zone VALUES(1, '2021-01-01 00:00:00', '2021-01-01 12:00:00', 'zone01', 1, 12);
INSERT INTO Reservationzone VALUES(1, 1, 1);
INSERT INTO ServiceType VALUES(1, 'servicetypename');
INSERT INTO Service VALUES(1, '2021-01-01', '2021-01-02', '00:00:00', '12:00:00', 1, 1);