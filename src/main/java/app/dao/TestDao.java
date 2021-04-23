package app.dao;


import app.dao.address.AddressDao;
import app.dao.area.AreaDao;
import app.dao.area.AreaCharacteristicDao;
import app.dao.area.AreaCommentDao;
import app.dao.area.AreaImageDao;
import app.dao.area.AreaPeriodDao;
import app.dao.citizen.CitizenDao;
import app.dao.controlStaff.ControlStaffDao;
import app.dao.controlStaff.ControlStaffIsAssignedToDao;
import app.dao.municipalManager.MunicipalManagerDao;
import app.dao.municipalManager.MunicipalManagerIsAssignedToDao;
import app.dao.municipality.MunicipalityDao;
import app.dao.period.PeriodDao;
import app.dao.person.PersonDao;
import app.dao.person.PersonEmailDao;
import app.dao.person.PersonPeriodDao;
import app.dao.person.PersonPhoneDao;
import app.dao.person.PersonResidenceDao;
import app.dao.place.PlaceDao;
import app.dao.place.PlacePeriodDao;
import app.dao.reservation.ReservationDao;
import app.dao.reservation.ReservationPeriodDao;
import app.dao.reservation.ReservationZoneDao;
import app.dao.service.ServiceDao;
import app.dao.service.ServicePeriodDao;
import app.dao.service.ServiceTypeDao;
import app.dao.zone.ZoneDao;
import app.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestDao {
    @Autowired SqlUtil sqlUtil;
    @Autowired PeriodDao periodDao;
    @Autowired AddressDao addressDao;
    @Autowired PlaceDao placeDao;
    @Autowired PlacePeriodDao placePeriodDao;
    @Autowired MunicipalityDao municipalityDao;
    @Autowired AreaDao areaDao;
    @Autowired AreaCommentDao areaCommentDao;
    @Autowired AreaCharacteristicDao areaCharacteristicDao;
    @Autowired AreaImageDao areaImageDao;
    @Autowired AreaPeriodDao areaPeriodDao;
    @Autowired ZoneDao zoneDao;
    @Autowired PersonDao personDao;
    @Autowired PersonResidenceDao personResidenceDao;
    @Autowired PersonEmailDao personEmailDao;
    @Autowired PersonPhoneDao personPhoneDao;
    @Autowired PersonPeriodDao personPeriodDao;
    @Autowired MunicipalManagerDao municipalManagerDao;
    @Autowired ControlStaffDao controlStaffDao;
    @Autowired CitizenDao citizenDao;
    @Autowired ServiceTypeDao serviceTypeDao;
    @Autowired ServiceDao serviceDao;
    @Autowired ServicePeriodDao servicePeriodDao;
    @Autowired ReservationDao reservationDao;
    @Autowired ReservationPeriodDao reservationPeriodDao;
    @Autowired ReservationZoneDao reservationZoneDao;
    @Autowired MunicipalManagerIsAssignedToDao municipalManagerIsAssignedToDao;
    @Autowired ControlStaffIsAssignedToDao controlStaffIsAssignedToDao;

    public void run() {
        sqlUtil.executeScript("reset");
        sqlUtil.executeScript("data");

        periodDao.test();
        addressDao.test();
        placeDao.test();
        placePeriodDao.test();
        municipalityDao.test();
        areaDao.test();
        areaCommentDao.test();
        areaCharacteristicDao.test();
        areaImageDao.test();
        areaPeriodDao.test();
        zoneDao.test();
        personDao.test();
        personResidenceDao.test();
        personEmailDao.test();
        personPhoneDao.test();
        personPeriodDao.test();
        municipalManagerDao.test();
        controlStaffDao.test();
        citizenDao.test();
        serviceTypeDao.test();
        serviceDao.test();
        servicePeriodDao.test();
        reservationDao.test();
        reservationPeriodDao.test();
        reservationZoneDao.test();
        municipalManagerIsAssignedToDao.test();
        controlStaffIsAssignedToDao.test();
    }
}
