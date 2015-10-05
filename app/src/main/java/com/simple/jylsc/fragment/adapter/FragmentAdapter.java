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
import com.simple.jylsc.fragment.dao.model.Fragment;

import java.util.List;

/**
 * Created by Administrator on 2015/9/21.
 */
public class FragmentAdapter extends BaseAdapter {
    List<Fragment> fragmentList;
    private Context context;
    private FrameLayout frameLayout;

    public FragmentAdapter(Context context)
    {
        this.context = context;
    }

    public void bindData(List<Fragment> fragmentList)
    {
        this.fragmentList = fragmentList;
    }
    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Object getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(null == convertView)
        {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_gridview_item,null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.image_imageview);
            holder.name = (TextView) convertView.findViewById(R.id.image_textview_name);
            holder.time = (TextView) convertView.findViewById(R.id.image_textview_time);
            convertView.setTag(holder);
        }
        else{
            holder = (Holder) convertView.getTag();
        }
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().displayImage(fragmentList.get(position).getImagepath(), holder.imageView, options);
        holder.time.setText(fragmentList.get(position).getCreatedate().toString());
        holder.name.setText(fragmentList.get(position).getTitle());
        return convertView;
    }
}

class Holder{
    TextView name;
    TextView time;
    ImageView imageView;
}
