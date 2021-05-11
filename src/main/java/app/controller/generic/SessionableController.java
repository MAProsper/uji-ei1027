package app.controller.generic;

import app.model.generic.Model;


/**
 * Para que compruebe que hay inicio de sesión antes de hacer nada; en caso de no haber sessión, redirige al add.
 */
public abstract class SessionableController<M extends Model> extends Controller<M>{
}
