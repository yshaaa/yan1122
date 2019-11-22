package com.bawei.day01;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<ShopBean.ResultBean> result;

    public MyAdapter(List<ShopBean.ResultBean> result) {

        this.result = result;
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if(view!=null){
            viewHolder=new ViewHolder();
            view=View.inflate(viewGroup.getContext(),R.layout.item,null);

        }
        return view;
    }
    public class ViewHolder{
        ImageView imageView;
        TextView name;
    }
}
