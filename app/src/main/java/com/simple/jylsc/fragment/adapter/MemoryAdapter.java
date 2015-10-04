package com.simple.jylsc.fragment.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnLongClickListener;

import com.simple.jylsc.fragment.R;
import com.simple.jylsc.fragment.dao.model.Memory;
import com.simple.jylsc.fragment.view.FragmentView;
import com.simple.jylsc.fragment.view.MainActivity;
import com.simple.jylsc.fragment.view.RefreshView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MemoryAdapter extends RecyclerView.Adapter<MemoryAdapter.MyViewHolder> {

    private static List<Memory> mDatas;
    private LayoutInflater mInflater;
    private static List<Integer> mHeights;
    private Context context;


    public static int getRandNumber() {
        return (int) (200 + Math.random() * 400);
    }

    public MemoryAdapter(Context context, List<Memory> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        mHeights = new ArrayList<Integer>();
        this.context = context;
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add(getRandNumber());
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.item_mainview, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        LayoutParams lp = holder.textView.getLayoutParams();
        lp.height = mHeights.get(position);
        holder.textView.setLayoutParams(lp);
        holder.textView.setText(mDatas.get(position).getMemoryName());
        holder.itemView.setLayoutParams(lp);
        GradientDrawable p = (GradientDrawable) holder.itemView.getBackground();
        p.setColor(Color.parseColor(mDatas.get(position).getMemoryColor()));

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public final static void addData(Memory newmemory) {
        mDatas.add(newmemory);
        mHeights.add(getRandNumber());
        //notifyItemInserted(mDatas.size());
    }

    public void removeData(int position) {
        DataSupport.delete(Memory.class, mDatas.get(position).getId());
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    protected void dialog(int position) {
        final int pos = position;
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setMessage("小主你真的要删除我么 ::>_<:: ");
        builder.setTitle("警告！");
        builder.setPositiveButton("是的！不要你了！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                removeData(pos);

            }
        });
        builder.setNegativeButton("罢了,继续留着你吧~", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {


        ImageView itemView;
        TextView textView;

        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.id_MainViewItemText);
            itemView = (ImageView) view.findViewById(R.id.id_MainViewItemImage);
            itemView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final int position = getAdapterPosition();
                    dialog(position);

                    return false;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    //Intent传递参数
                    intent.putExtra("memory_no", getAdapterPosition()+1);
                    intent.setClass(context, FragmentView.class);
                    context.startActivity(intent);


                }
            });


        }
    }
}

