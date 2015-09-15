package com.simple.jylsc.fragment.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simple.jylsc.fragment.R;
import com.simple.jylsc.fragment.tool.stikkyheader.IO2014HeaderAnimator;
import com.simple.jylsc.fragment.adapter.MemoryAdapter;
import com.simple.jylsc.fragment.tool.stikkyheader.StikkyHeaderBuilder;

public class MemoryView extends Fragment {

    private RecyclerView mRecyclerView;

    public MemoryView() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.layout_memory_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_MemoryRecyclerView);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        IO2014HeaderAnimator animator = new IO2014HeaderAnimator(getActivity());

        StikkyHeaderBuilder.stickTo(mRecyclerView)
                .setHeader(R.id.id_MemoryHeader, (ViewGroup) getView())
                .minHeightHeaderDim(R.dimen.title_max_height)
                .animator(animator)
                .build();
        MemoryAdapter.populateRecyclerView(mRecyclerView);
    }

}
