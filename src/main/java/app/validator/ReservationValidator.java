package app.validator;

import app.model.Reservation;
import app.validator.generic.SessionableValidator;
import org.springframework.stereotype.Service;

@Service
public class ReservationValidator extends SessionableValidator<Reservation> {
}
