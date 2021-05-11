package app.controller.generic;

import app.model.generic.Model;
import app.model.generic.Person;

import javax.servlet.http.HttpSession;


/** Para que compruebe que hay inicio de sesión antes de hacer nada; en caso de no haber sessión, redirige al add.
 *
 */
public abstract class SessionableController<M extends Model> extends Controller<M>{
    @Override
    public String list(HttpSession session, org.springframework.ui.Model model) {
        return validator.validate(session) ? super.list(session, model) : "redirect:/session/add";
    }
}
