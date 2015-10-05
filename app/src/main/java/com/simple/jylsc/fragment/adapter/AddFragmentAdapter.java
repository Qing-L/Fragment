package com.simple.jylsc.fragment.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.simple.jylsc.fragment.R;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/10/2.
 */
public class AddFragmentAdapter extends BaseAdapter {
    List<String> imageList;
    private Context context;
    private FrameLayout frameLayout;

    public AddFragmentAdapter(Context context)
    {
        this.context = context;
    }

    public void bindData(List<String> imageList)
    {
        this.imageList = imageList;
    }
    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(null == convertView)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.add_fragment_gridview_item,null);
            imageView = (ImageView) convertView.findViewById(R.id.add_fragment_imageview);
            convertView.setTag(imageView);
        }
        else{
            imageView = (ImageView) convertView.getTag();
        }
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().displayImage(imageList.get(position), imageView, options);
        return convertView;
    }
}
