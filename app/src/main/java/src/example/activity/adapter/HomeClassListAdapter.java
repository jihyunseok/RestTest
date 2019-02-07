package src.example.activity.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import src.example.activity.view.HomeClassListView;
import src.example.vo.HomeClassListVO;

public class HomeClassListAdapter extends BaseAdapter {

    ArrayList<HomeClassListVO> items = new ArrayList<HomeClassListVO>();

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(HomeClassListVO item) {
        items.add(item);
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        HomeClassListView view = new HomeClassListView(context);

        HomeClassListVO item = items.get(position);
        view.setName(item.getName());
        view.setMobile(item.getMobile());
        view.setAge(item.getAge());
        view.setImage(item.getResId());

        return view;
    }
}
