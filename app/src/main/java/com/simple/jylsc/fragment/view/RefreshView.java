package com.simple.jylsc.fragment.view;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.simple.jylsc.fragment.R;
import com.simple.jylsc.fragment.dao.model.Fragment;
import com.simple.jylsc.fragment.tool.waveswiperefreshlayout.WaveSwipeRefreshLayout;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RefreshView extends AppCompatActivity implements WaveSwipeRefreshLayout.OnRefreshListener {


  private WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
  private ImageView imageView;
  private DisplayImageOptions options;
  private List<Fragment> fragmentList = new ArrayList<Fragment>();
  private String bg;
  private String content;
  private int memory_no;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    super.onCreate(savedInstanceState);

    imageView = (ImageView) findViewById(R.id.id_refresh_view_imageview);
    Bundle bundle = getIntent().getExtras();
    bg=bundle.getString("background");
    content=bundle.getString("content");
    memory_no=bundle.getInt("memory_no");
    fragmentList= DataSupport.where("memory_id = ?",String.valueOf(memory_no)).find(Fragment.class);

    //显示图片的配置
    options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build();

    setContentView(R.layout.refresh_view);
    initView();

  }

  private void initView() {
    mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.main_swipe);
    mWaveSwipeRefreshLayout.setColorSchemeColors(Color.WHITE, Color.WHITE);
    mWaveSwipeRefreshLayout.setOnRefreshListener(this);
    mWaveSwipeRefreshLayout.setWaveColor(getResources().getColor(R.color.primary));
    mWaveSwipeRefreshLayout.setShadowRadius(0);
    imageView = (ImageView) findViewById(R.id.id_refresh_view_imageview);
    ImageLoader.getInstance().displayImage(bg, imageView, options);
  }


  private void refresh(){
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        // 更新が終了したらインジケータ非表示
        mWaveSwipeRefreshLayout.setRefreshing(false);
        imageView = (ImageView) findViewById(R.id.id_refresh_view_imageview);
        int i = new Random().nextInt(fragmentList.size());
        Log.i("random", String.valueOf(i));
        //使用displayimage（）加载图片
        ImageLoader.getInstance().displayImage(fragmentList.get(i).getImagepath(), imageView, options);
      }
    }, 300);
  }

  @Override
  protected void onResume() {
    //mWaveSwipeRefreshLayout.setRefreshing(true);
    refresh();
    super.onResume();
  }

  @Override
  public void onRefresh() {
    refresh();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      mWaveSwipeRefreshLayout.setRefreshing(true);
      refresh();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
