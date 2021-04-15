package app.util;


import app.dao.address.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Test {
    @Autowired SqlUtil sqlUtil;
    @Autowired AddressDao addressDao;

    public void run() {
        sqlUtil.executeScript("reset");
        sqlUtil.executeScript("data");
        addressDao.test();
    }
}
