package com.simple.jylsc.fragment.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.simple.jylsc.fragment.R;
import com.simple.jylsc.fragment.dao.model.Fragment;
import com.simple.jylsc.fragment.tool.Tools_Random_number;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.indicator.PtrIndicator;


/**
 * Created by Administrator on 2015/10/4.
 */
public class RefreshFragmentView extends Activity {
    private final String[] mStringList = {"Fragment"};
    private TextView title_text;
    private TextView content_text;
    private ImageView imageView;
    private PtrFrameLayout frame;
    private ImageView back;
    private DisplayImageOptions options;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    private String bg;
    private String title;
    private String content;
    private int memory_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_fragment);

        imageView = (ImageView) this.findViewById(R.id.store_house_ptr_image);
        frame = (PtrFrameLayout) this.findViewById(R.id.store_house_ptr_frame);
        content_text = (TextView) this.findViewById(R.id.store_house_ptr_content);
        title_text = (TextView) this.findViewById(R.id.store_house_ptr_title);
        back = (ImageView) this.findViewById(R.id.id_fragment_back);

        Bundle bundle = getIntent().getExtras();
        bg = bundle.getString("background");
        content = bundle.getString("content");
        title = bundle.getString("title");
        memory_no = bundle.getInt("memory_no");
        fragmentList = DataSupport.where("memory_id = ?", String.valueOf(memory_no)).find(Fragment.class);

        //显示图片的配置
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoader.getInstance().displayImage(bg, imageView, options);
        if(content.length()<15)
        {
            content_text.setTextSize(45);
            content_text.setGravity(Gravity.CENTER);
        }
        else if(content.length()<25)
        {
            content_text.setTextSize(30);
            content_text.setGravity(Gravity.CENTER_VERTICAL);
        }
        else if(content.length()<35)
        {
            content_text.setTextSize(25);
            content_text.setGravity(Gravity.CENTER_VERTICAL);
        }
        else
        {
            content_text.setTextSize(20);
            content_text.setGravity(Gravity.CENTER_VERTICAL);
        }
        content_text.setText(content);
        title_text.setText(title);

        frame.setPullToRefresh(true);
        refresh();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(2);
                overridePendingTransition(R.anim.default_anim_in, R.anim.default_anim_out);
                RefreshFragmentView.this.finish();
                System.gc();
            }
        });
    }

    public void refresh()
    {
        // header
        final StoreHouseHeader header = new StoreHouseHeader(RefreshFragmentView.this);
        header.setPadding(0, LocalDisplay.dp2px(15), 0, 0);
        header.initWithString(mStringList[0]);

        // for changing string
        frame.addPtrUIHandler(new PtrUIHandler() {

            private int mLoadTime = 0;

            @Override
            public void onUIReset(PtrFrameLayout frame) {
                mLoadTime++;
                String string = mStringList[mLoadTime % mStringList.length];
                header.initWithString(string);
            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {
            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

            }
        });
        frame.setDurationToCloseHeader(3000);
        frame.setHeaderView(header);

        frame.addPtrUIHandler(header);
        frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                frame.autoRefresh(false);
                /*imageView = (ImageView) findViewById(R.id.store_house_ptr_image);
                int i = new Tools_Random_number(fragmentList.size()).get_random().nextInt(fragmentList.size());
                ImageLoader.getInstance().displayImage(fragmentList.get(i).getImagepath(), imageView, options);
                content_text.setText(fragmentList.get(i).getContent());
                title_text.setText(fragmentList.get(i).getTitle());*/
            }
        }, 100);

        frame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 2000);
            }
        });
    }
}
