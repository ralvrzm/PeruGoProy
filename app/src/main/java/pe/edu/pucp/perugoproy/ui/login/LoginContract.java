package pe.edu.pucp.perugoproy.ui.login;

import com.google.firebase.auth.AuthCredential;

import pe.edu.pucp.perugoproy.base.BasePresenter;
import pe.edu.pucp.perugoproy.base.BaseView;

public class LoginContract {
    public interface LoginView extends BaseView{
        void onLoginResult(boolean isSuccess);
        void onUserLogged(boolean isSuccess);
    }

    public interface LoginPresenter<v extends  LoginView> extends BasePresenter<v>{
        void onCheckedUserLogged();
        void login(String username, String password);
        void googleLogin(AuthCredential credential);
    }
}
