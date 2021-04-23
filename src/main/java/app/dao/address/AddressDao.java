package app.dao.address;

import app.dao.Dao;
import app.model.address.Address;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao extends Dao<Address> {
    public AddressDao() {
        super(Address.class);
    }
}
