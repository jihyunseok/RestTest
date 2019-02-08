package src.example.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.resttest.R;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import src.example.service.ApiUtils;
import src.example.service.LoginService;

public class LoginActivity extends AppCompatActivity implements Button.OnClickListener{

    private LoginService loginService;
    private EditText user_id;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginService = ApiUtils.getLoginService();

        //Button Event
        Button loginBtn = (Button) findViewById(R.id.login) ;
        loginBtn.setOnClickListener(this) ;
        Button backBtn = (Button) findViewById(R.id.back) ;
        backBtn.setOnClickListener(this) ;

        user_id = (EditText) findViewById(R.id.etId);
        password = (EditText) findViewById(R.id.etPassword);

    }

    @Override
    public void onClick(View view){
        Log.d("클릭한 버튼 아이디 = ",String.valueOf(view.getId()));
        switch(view.getId()){
            case R.id.login :
                login();
                break;
            case R.id.back :
                Toast.makeText(this, "back button", Toast.LENGTH_SHORT).show();
                Intent backIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backIntent);
                break;
        }
    }

    public void login(){

        checkValue();

        HashMap<String, Object> logininfo = new HashMap();
        logininfo.put("user_id",user_id.getText().toString());
        logininfo.put("password",password.getText().toString());

        loginService.login(logininfo).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    String body = response.body();
                    if (body != null) {
                        Log.d("body", body);
                        switch (body) {
                            case "0": //ログイン成功
                                Intent goHome = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(goHome);
                                break;
                            case "1": //ログイン失敗
                                Toast.makeText(getApplicationContext(), "パスワードが間違っています。!", Toast.LENGTH_SHORT).show();
                                break;
                            case "2":
                                Toast.makeText(getApplicationContext(), "idが存在しません。", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                showErrorMessage();
            }
        });

    }

    public void checkValue(){
        // 이메일 입력 확인
        if( user_id.getText().toString().length() == 0 ) {
            Toast.makeText(this, "idを入力してください!", Toast.LENGTH_SHORT).show();
            user_id.requestFocus();
            return;
        }

        // 비밀번호 입력 확인
        if( password.getText().toString().length() == 0 ) {
            Toast.makeText(this, "パスワードを入力してください!", Toast.LENGTH_SHORT).show();
            password.requestFocus();
            return;
        }

    }

    public void showErrorMessage() {
        Toast.makeText(this, "エラー発生", Toast.LENGTH_SHORT).show();
    }
}
