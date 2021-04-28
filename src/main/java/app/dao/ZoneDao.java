package app.dao;

import app.dao.generic.PlaceDao;
import app.model.Zone;
import org.springframework.stereotype.Repository;

@Repository
public class ZoneDao extends PlaceDao<Zone> {
}
