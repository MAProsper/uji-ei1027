package app.dao.generic;


import app.dao.*;
import app.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestDao {
    @Autowired SqlUtil sqlUtil;
    @Autowired PeriodDao periodDao;
    @Autowired PlaceDao placeDao;
    @Autowired MunicipalityDao municipalityDao;
    @Autowired AreaDao areaDao;
    @Autowired AreaPeriodDao areaPeriodDao;
    @Autowired ZoneDao zoneDao;
    @Autowired PersonDao personDao;
    @Autowired MunicipalManagerDao municipalManagerDao;
    @Autowired ControlStaffDao controlStaffDao;
    @Autowired CitizenDao citizenDao;
    @Autowired ServiceTypeDao serviceTypeDao;
    @Autowired ServiceDao serviceDao;
    @Autowired ReservationDao reservationDao;

    public void run() {
        sqlUtil.executeScript("reset");
        sqlUtil.executeScript("data");

        periodDao.test();
        placeDao.test();
        municipalityDao.test();
        areaDao.test();
        areaPeriodDao.test();
        zoneDao.test();
        personDao.test();
        municipalManagerDao.test();
        controlStaffDao.test();
        citizenDao.test();
        serviceTypeDao.test();
        serviceDao.test();
        reservationDao.test();
    }
}
