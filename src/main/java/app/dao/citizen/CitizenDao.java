package app.dao.citizen;

import app.dao.Dao;
import app.model.citizen.Citizen;
import org.springframework.stereotype.Repository;

@Repository
public class CitizenDao extends Dao<Citizen> {
    public CitizenDao() {
        super(Citizen.class);
    }
}
