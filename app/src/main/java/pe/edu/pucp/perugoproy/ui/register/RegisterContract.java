package pe.edu.pucp.perugoproy.ui.register;

import pe.edu.pucp.perugoproy.base.BasePresenter;
import pe.edu.pucp.perugoproy.base.BaseView;

public class RegisterContract {

    public interface RegisterView extends BaseView {
        void onRegisterResult(Boolean isSuccess);
    }


    public interface RegisterPresenter<V extends RegisterView> extends BasePresenter<V> {
        void register(String username, String password);
    }
}
