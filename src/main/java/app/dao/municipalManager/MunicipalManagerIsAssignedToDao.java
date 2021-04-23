package app.dao.municipalManager;

import app.dao.Dao;
import app.model.municipalManager.MunicipalManager;
import app.model.municipalManager.MunicipalManagerIsAssignedTo;
import app.model.municipality.Municipality;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MunicipalManagerIsAssignedToDao extends Dao<MunicipalManagerIsAssignedTo> {
    public MunicipalManagerIsAssignedToDao() {
        super(MunicipalManagerIsAssignedTo.class);
    }

    public List<MunicipalManagerIsAssignedTo> getByMunicipalManager(MunicipalManager municipalManager) {
        try {
            return jdbc.query("SELECT * FROM MunicipalManagerIsAssignedTo WHERE municipal_manager =?", mapper, municipalManager.getPerson());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public List<MunicipalManagerIsAssignedTo> getByMunicipality(Municipality municipality) {
        try {
            return jdbc.query("SELECT * FROM MunicipalManagerIsAssignedTo WHERE municipality =?", mapper, municipality.getPlace());
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public MunicipalManagerIsAssignedTo getByDate(LocalDateTime date) {
        try {
            return jdbc.queryForObject("SELECT * FROM MunicipalManagerIsAssignedTo AS t1 JOIN Period AS p ON t1.period = p.id WHERE p.start <= =? AND COALESCE(p.end, =?) >= =?", mapper, date);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
