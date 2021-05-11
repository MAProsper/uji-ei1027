package app.controller.generic;

import app.model.generic.Model;
import javax.servlet.http.HttpSession;


/** Para que compruebe que hay inicio de sesión antes de hacer nada; en caso de no haber sessión, redirige al add.
 *
 */
public abstract class SessionableController<M extends Model> extends Controller<M>{

    @Override
    public String list(org.springframework.ui.Model model, HttpSession session) {
        return session.getAttribute("user") == null ?  "redirect:/session/add" : super.list(model, session);
    }
}
