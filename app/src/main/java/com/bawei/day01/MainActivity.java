package com.bawei.day01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.bawei.day01.base.BaseActivity;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends BaseActivity {

    private ListView listview;
    private int page=1;

    @Override
    protected void initView() {
        listview = findViewById(R.id.listview);
    }

    @Override
    protected void initData() {
        String url="";
        if(page==1){
            url="http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=板鞋&page=1&count=5";
        }
        List<ShopBean.ResultBean> result = new Gson().fromJson(url, ShopBean.class).getResult();
        MyAdapter myAdapter = new MyAdapter(result);
        listview.setAdapter(myAdapter);

    }



    @Override
    protected int LayoutID() {
        return R.layout.activity_main;
    }
}
