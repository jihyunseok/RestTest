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

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import src.example.activity.adapter.HomeClassListAdapter;
import src.example.service.ApiUtils;
import src.example.service.HomeService;
import src.example.vo.HomeClassListVO;

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
                Toast.makeText(getApplicationContext(), "選択 : " + item.getClassName(), Toast.LENGTH_LONG).show();
            }
        });

        //初期ロード
        getHomeData();
    }

    private void getHomeData() {

//        HomeClassListVO classinfo = new HomeClassListVO();
//        classinfo.setClassId(3);

        HashMap<String, Object> classinfo = new HashMap<>();

        homeService.getClassList(classinfo).enqueue(new Callback<List<HomeClassListVO>>(){
            @Override
            public void onResponse(@NonNull Call<List<HomeClassListVO>> call, @NonNull Response<List<HomeClassListVO>> response) {
                if (response.isSuccessful()) {
                    List<HomeClassListVO> datas = response.body();
                    if (datas != null) {
                        adapter = new HomeClassListAdapter();
                        for (int i = 0; i < datas.size(); i++) {
                            adapter.addItem(new HomeClassListVO(datas.get(i).getClassName(), datas.get(i).getClassInfo(), datas.get(i).getStudyCost(), R.drawable.singer));
                            Log.d("data" + i, datas.get(i).getClassName() + "");
                        }
                        listView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<HomeClassListVO>> call, @NonNull Throwable t) {
                showErrorMessage();
            }
        });

    }

    public void showErrorMessage() {
        Toast.makeText(this, "エラー発生", Toast.LENGTH_SHORT).show();
    }

}
