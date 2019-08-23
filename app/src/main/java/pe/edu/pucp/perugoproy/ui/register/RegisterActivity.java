package pe.edu.pucp.perugoproy.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pe.edu.pucp.perugoproy.MainActivity;
import pe.edu.pucp.perugoproy.R;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.RegisterView{


    EditText _etUsername;
    EditText _etPassword;

    Button _btnRegister;

    RegisterContract.RegisterPresenter<RegisterContract.RegisterView> _presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        _presenter = new RegisterPresenterImpl<RegisterContract.RegisterView>();
        _presenter.attach(this);

        _initialize();
        _setListeners();
    }

    private void _initialize(){
        _etUsername = (EditText) findViewById(R.id.et_username);
        _etPassword = (EditText) findViewById(R.id.et_password);
        _btnRegister = (Button) findViewById(R.id.btn_register);
    }

    private void _setListeners(){
        _btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = _etUsername.getText().toString();
                String password = _etPassword.getText().toString();
                _presenter.register(username,password);
            }
        });
    }

    @Override
    public void onRegisterResult(Boolean isSuccess) {

        if(isSuccess){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"An Error Ocurred",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
