package com.simple.jylsc.fragment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import com.simple.jylsc.fragment.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemoryAdapter {

    private static final int COUNT_ITEMS = 500;

    public static void populateRecyclerView(RecyclerView recyclerView) {
        List<String> elements = new ArrayList<>(COUNT_ITEMS);
        for (int i = 0; i < COUNT_ITEMS; i++) {
            elements.add("row " + i);
        }

        SimpleRecyclerAdapter recyclerAdapter = new SimpleRecyclerAdapter(recyclerView.getContext(), elements);
        recyclerView.setAdapter(recyclerAdapter);
    }


    private static class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.MyViewHolder> {

        private List<String> mDatas;
        private LayoutInflater mInflater;

        private List<Integer> mHeights;

        public interface OnItemClickLitener
        {
            void onItemClick(View view, int position);

            void onItemLongClick(View view, int position);
        }

        private OnItemClickLitener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
        {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }

        public int getRandNumber()
        {
            return (int) (200 + Math.random() * 400);
        }

        public SimpleRecyclerAdapter(Context context, List<String> datas)
        {
            mInflater = LayoutInflater.from(context);
            mDatas = datas;

            mHeights = new ArrayList<Integer>();
            for (int i = 0; i < mDatas.size(); i++)
            {
                mHeights.add(getRandNumber());
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                    R.layout.item_mainview, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position)
        {

            Random r = new Random();
            Double d = r.nextDouble();
            String s = d + "";
            s = s.substring(3, 3 + 6);
            s="#"+"50"+s;

            LayoutParams lp = holder.tv.getLayoutParams();
            lp.height = mHeights.get(position);

            holder.tv.setLayoutParams(lp);
            holder.tv.setText(mDatas.get(position));
            holder.iv.setLayoutParams(lp);
            GradientDrawable p = (GradientDrawable) holder.iv.getBackground();
            p.setColor(Color.parseColor(s));

            // 如果设置了回调，则设置点击事件
            if (mOnItemClickLitener != null)
            {
                holder.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                        removeData(pos);
                        return false;
                    }
                });
            }
        }

        @Override
        public int getItemCount()
        {
            return mDatas.size();
        }

        public void addData(int position)
        {
            mDatas.add(position, "Insert One");
            mHeights.add( getRandNumber());
            notifyItemInserted(position);
        }

        public void removeData(int position)
        {
            mDatas.remove(position);
            notifyItemRemoved(position);
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            ImageView iv;
            TextView tv;
            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_MainViewItemText);
                iv = (ImageView) view.findViewById(R.id.id_MainViewItemImage);



            }
        }
    }
}
