package app.validator;

import app.model.Citizen;
import app.validator.generic.SessionValidator;
import org.springframework.stereotype.Service;

@Service
public class CitizenSessionValidator extends SessionValidator<Citizen> {
}
