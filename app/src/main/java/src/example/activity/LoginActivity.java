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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import src.example.vo.LoginVO;
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
                loginMock();
                break;
            case R.id.back :
                Toast.makeText(this, "back button", Toast.LENGTH_SHORT).show();
                Intent backIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(backIntent);
                break;
        }
    }

    public void login(){
        loginService.getData("1").enqueue(new Callback<LoginVO>() {
            @Override
            public void onResponse(@NonNull Call<LoginVO> call, @NonNull Response<LoginVO> response) {
                if (response.isSuccessful()) {
                    LoginVO body = response.body();
                    if (body != null) {
                        Log.d("data.getUserId()", body.getUserId() + "");
                        Log.d("data.getId()", body.getId() + "");
                        Log.d("data.getTitle()", body.getTitle());
                        Log.d("data.getBody()", body.getBody());
                        Log.e("getData end", "======================================");
                        Toast.makeText(LoginActivity.this, body.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginVO> call, @NonNull Throwable t) {
                showErrorMessage();
            }
        });

    }

    public void login2(){
        loginService.getData2("1").enqueue(new Callback<List<LoginVO>>(){
            @Override
            public void onResponse(@NonNull Call<List<LoginVO>> call, @NonNull Response<List<LoginVO>> response) {
                if (response.isSuccessful()) {
                    List<LoginVO> datas = response.body();
                    if (datas != null) {
                        for (int i = 0; i < datas.size(); i++) {
                            Log.e("data" + i, datas.get(i).getUserId() + "");
                        }
                        Log.e("getData2 end", "======================================");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<LoginVO>> call, @NonNull Throwable t) {
                showErrorMessage();
            }
        });
    }

    public void loginMock(){

        checkValue();

        if( !user_id.getText().toString().equals("hyunseok")) {
            Toast.makeText(this, "idが存在しません。", Toast.LENGTH_SHORT).show();
            user_id.requestFocus();
            return;
        }

        if( !password.getText().toString().equals("hyunseok")) {
            Toast.makeText(this, "パスワードが間違っています。!", Toast.LENGTH_SHORT).show();
            password.requestFocus();
            return;
        }

        Intent goHome = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(goHome);
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
