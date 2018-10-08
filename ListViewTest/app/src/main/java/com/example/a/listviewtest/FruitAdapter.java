package com.example.a.listviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;
    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        Fruit fruit = getItem(position);//获取当前Fruit实例
        View view;
        ViewHolder viewHolder;
        if(convertView==null){//判断列表缓存是否为空提高加载速度
               view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
               viewHolder= new ViewHolder();//使用内部类缓存列表内容
               viewHolder.fruitImage=view.findViewById(R.id.fruit_image);
               viewHolder.fruitName=view.findViewById(R.id.fruit_name);
               view.setTag(viewHolder);//在view用viewHolder保存当前获得的控件
        }
       else {
            view = convertView;
            viewHolder =(ViewHolder) view.getTag();//获取存储的viewHolder
        }
//        TextView fruitName = view.findViewById(R.id.fruit_name);
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
//        fruitName.setText(fruit.getName());

        return view;
    }

     class ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
    }
}
