package src.example.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.resttest.R;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ボタン　リスナー設定
        Button loginBtn = (Button) findViewById(R.id.login);
        loginBtn.setOnClickListener(this);
    }


    //ボタンクリックイベント
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login:
                Intent myIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(myIntent);
                break;
        }
    }
}
