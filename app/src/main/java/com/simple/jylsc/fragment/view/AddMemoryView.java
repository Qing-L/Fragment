/**
 * Copyright 2015 Bartosz Lipinski
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.simple.jylsc.fragment.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.simple.jylsc.fragment.R;
import com.simple.jylsc.fragment.adapter.AddMemoryAdapter;
import com.simple.jylsc.fragment.tool.recyclerviewheader.RecyclerViewHeader;


/**
 * Created by Bartosz Lipinski
 * 01.04.15
 */
public class AddMemoryView extends Fragment {

    private RecyclerViewHeader mRecyclerHeader;
    private RecyclerView mRecycler;

    public static AddMemoryView newInstance() {
        AddMemoryView fragment = new AddMemoryView();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_addmemory_view, container, false);
        ImageView buttonBack;
        buttonBack =(ImageView) view.findViewById(R.id.id_AddMemoryButtonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MainActivity.setPage(1);
            }
        });



        setupViews(view);
        return view;
    }

    private void setupViews(View view) {
        mRecycler = (RecyclerView) view.findViewById(R.id.id_AddMemoryRecyclerView);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        AddMemoryAdapter adapter = new AddMemoryAdapter(getActivity(), 100);

        mRecycler.setAdapter(adapter);



        mRecyclerHeader = (RecyclerViewHeader) view.findViewById(R.id.id_AddMemoryHeader);
        mRecyclerHeader.attachTo(mRecycler, true);
    }

}
