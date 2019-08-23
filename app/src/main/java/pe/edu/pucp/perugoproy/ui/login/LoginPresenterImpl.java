package pe.edu.pucp.perugoproy.ui.login;

import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pe.edu.pucp.perugoproy.base.BaseView;
import pe.edu.pucp.perugoproy.ui.CommonPresenter;

public class LoginPresenterImpl<v extends LoginContract.LoginView> extends CommonPresenter<v> implements  LoginContract.LoginPresenter<v> {


    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    LoginPresenterImpl(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
    }
    @Override
    public void onCheckedUserLogged() {
        getView().onUserLogged(firebaseUser != null);
    }

    @Override
    public void login(String username, String password) {
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(username,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()&&task.getResult() != null){
                            getView().onLoginResult(true);
                        }else{
                            getView().onLoginResult(false);
                        }
                    }
                })
        ;
    }

    @Override
    public void googleLogin(AuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()&&task.getResult() != null){
                    getView().onLoginResult(true);
                }else{
                    getView().onLoginResult(false);
                }
            }
        });
    }
/*
    @Override
    public void attach(BaseView view) {

    }*/
}
