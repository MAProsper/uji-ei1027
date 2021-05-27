package app.controller;

import app.ApplicationException;
import app.dao.generic.Dao;
import app.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class MailDao extends Dao<Mail> {
    @Autowired Logger logger;

    @Override
    public void add(Mail object) {
        logger.info(String.format("Enviado: %s", object));
        super.add(object);
    }

    @Override
    protected void executeUpdate(String query, Object... values) {
        throw new ApplicationException("Mail no es actualizable");
    }
}
