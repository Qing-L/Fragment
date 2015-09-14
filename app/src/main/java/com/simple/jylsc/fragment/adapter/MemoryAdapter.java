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

            LayoutParams lp = holder.textView.getLayoutParams();
            lp.height = mHeights.get(position);
            holder.textView.setLayoutParams(lp);
            holder.textView.setText(mDatas.get(position));
            holder.itemView.setLayoutParams(lp);
            GradientDrawable p = (GradientDrawable) holder.itemView.getBackground();
            p.setColor(Color.parseColor(s));

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
            ImageView itemView;
            TextView textView;
            public MyViewHolder(View view)
            {
                super(view);
                textView = (TextView) view.findViewById(R.id.id_MainViewItemText);
                itemView = (ImageView) view.findViewById(R.id.id_MainViewItemImage);
                itemView.setOnLongClickListener(new OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        final int position = getAdapterPosition();
                        removeData(position);
                        return false;
                    }
                });


            }
        }
    }
}
