package app.dao;

import app.dao.generic.Dao;
import app.model.Citizen;
import org.springframework.stereotype.Repository;

@Repository
public class CitizenDao extends Dao<Citizen> {
    public CitizenDao() {
        super(Citizen.class);
    }
}
