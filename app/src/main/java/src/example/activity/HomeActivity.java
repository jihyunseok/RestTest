package src.example.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.resttest.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import src.example.activity.adapter.HomeClassListAdapter;
import src.example.service.ApiUtils;
import src.example.service.HomeService;
import src.example.vo.HomeClassListVO;
import src.example.vo.LoginVO;

public class HomeActivity extends AppCompatActivity {

    private HomeService homeService;

    EditText editText;
    ListView listView;
    HomeClassListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeService = ApiUtils.getHomeService();

        listView = (ListView) findViewById(R.id.listView);
//        editText = (EditText) findViewById(R.id.editText);

//        Button button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = editText.getText().toString();
//                String mobile = "010-1000-1000";
//                int age = 20;
//
//                adapter.addItem(new HomeClassListVO(name, mobile, age, R.drawable.singer3));
//                adapter.notifyDataSetChanged();
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                HomeClassListVO item = (HomeClassListVO) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "選択 : " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });

        //初期ロード
        getHomeData();
    }

    private void getHomeData() {

        homeService.getData2("1").enqueue(new Callback<List<LoginVO>>(){
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

        adapter = new HomeClassListAdapter();

        adapter.addItem(new HomeClassListVO("和食", "0９0-1000-1000", 20, R.drawable.singer));
        adapter.addItem(new HomeClassListVO("洋食", "080-2000-2000", 22, R.drawable.singer2));
        adapter.addItem(new HomeClassListVO("デザート", "070-3000-3000", 21, R.drawable.singer3));
        adapter.addItem(new HomeClassListVO("中華", "090-4000-4000", 24, R.drawable.singer4));
        adapter.addItem(new HomeClassListVO("フレンチ", "080-5000-5000", 25, R.drawable.singer5));

        listView.setAdapter(adapter);

    }

    public void showErrorMessage() {
        Toast.makeText(this, "エラー発生", Toast.LENGTH_SHORT).show();
    }

}
