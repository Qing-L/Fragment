/**
 * Copyright 2015 Bartosz Lipinski
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.simple.jylsc.fragment.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.simple.jylsc.fragment.R;

import java.util.Random;


/**
 * Created by Bartosz Lipinski
 * 01.04.15
 */
public class AddMemoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int[] mColors;
    private static Context content;

    public AddMemoryAdapter(Context context, int numberOfItems) {
        this.content = context;
        mColors = new int[numberOfItems];
        for (int i = 0; i < numberOfItems; ++i) {
            Random r = new Random();
            Double d = r.nextDouble();
            String s = d + "";
            s = s.substring(3, 3 + 6);
            s = "#" + "50" + s;
            mColors[i] = Color.parseColor(s);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addmemory, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final SampleViewHolder viewHolder = (SampleViewHolder) holder;
        viewHolder.itemView.setBackgroundColor(mColors[position]);
    }

    @Override
    public int getItemCount() {
        return mColors.length;
    }



    public static class SampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemView;

        public void showDialog(final int position)
        {
            LayoutInflater inflater = LayoutInflater.from(content);
            View view = inflater.inflate(R.layout.dialog_addmemory_edittext, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(content);
            builder.setView(view);
            final AlertDialog dialog = builder.create();
            dialog.show();
            ImageView buttonCancel;
            ImageView buttonConfirm;
            buttonCancel=(ImageView) view.findViewById(R.id.id_AddMemoryButtonCancel);
            buttonConfirm=(ImageView) view.findViewById(R.id.id_AddMemoryButtonConfirm);
            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    Log.i("dialog--->",position+"");
                }
            });
        }

        public SampleViewHolder(View view) {
            super(view);
            itemView = (ImageView) view.findViewById(R.id.id_AddMemoryItemImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = getAdapterPosition();
                    showDialog(position);
                }
            });
        }
    }
}