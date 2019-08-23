package pe.edu.pucp.perugoproy.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

import pe.edu.pucp.perugoproy.MainActivity;
import pe.edu.pucp.perugoproy.R;
import pe.edu.pucp.perugoproy.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, LoginContract.LoginView  {


    EditText _etUsername;
    EditText _etPassword;

    Button _loginButton;
    Button _resgisterButton;
    SignInButton _googleButton;
    private static final int RC_SIGN_IN = 5000;

    private GoogleSignInClient googleSignInClient;
    LoginContract.LoginPresenter<LoginContract.LoginView> _presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _presenter = new LoginPresenterImpl<LoginContract.LoginView>();
        _presenter.attach(this);
        _presenter.onCheckedUserLogged();
        _initialice();
        _googleConfiguration();
        _setListener();
    }

    private void _googleConfiguration(){
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);
    }

    private void _initialice(){
        _etUsername = (EditText)findViewById(R.id.et_username);
        _etPassword = (EditText)findViewById(R.id.et_password);
        _loginButton = (Button) findViewById(R.id.login );
        _resgisterButton = (Button)findViewById(R.id.btn_register);
        _googleButton = (SignInButton)findViewById(R.id.google_button);
    }

    private void _setListener(){
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = _etUsername.getText().toString();
                String password = _etPassword.getText().toString();
                _presenter.login(username,password);
            }
        });
        _resgisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        _googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _signIn();
            }
        });
    }

    private void _signIn(){
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount account = result.getSignInAccount();
                if(account != null){
                    _authWithGoogleAccount(account);
                }
            }
        }
    }

    private void _authWithGoogleAccount (final GoogleSignInAccount account){
        final AuthCredential authCredential = GoogleAuthProvider.getCredential(account.getIdToken(),null );
        _presenter.googleLogin(authCredential);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if(connectionResult.getErrorMessage() != null){
            Log.d("GOOGLE_SIGN_ERROR",connectionResult.getErrorMessage());
        }
    }

    @Override
    public void onLoginResult(boolean isSuccess) {
        if(isSuccess){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(LoginActivity.this,"El correo electrónico que has introducido no coincide con ninguna cuenta. Registrate para acceder", Toast.LENGTH_LONG ).show();
        }

    }

    @Override
    public void onUserLogged(boolean isSuccess) {
        if(isSuccess){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
