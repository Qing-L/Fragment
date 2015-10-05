package com.simple.jylsc.fragment.view;

import java.util.ArrayList;
import java.util.List;

import com.simple.jylsc.fragment.R;
import com.simple.jylsc.fragment.adapter.FragmentAdapter;
import com.simple.jylsc.fragment.dao.model.Fragment;
import com.simple.jylsc.fragment.tool.Tools_Random_number;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

public class FragmentView extends Activity
{
    // 刷新界面时所需要用到的参数

    private GridView gridview;
    private FragmentAdapter adapter;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    private ImageView back;
    private ImageView add;
    private ImageView random_btn;

    private int memory_no;
    private int parsetime;
    private Tools_Random_number rand;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout);

        gridview = (GridView)this.findViewById(R.id.image_gridview);
        adapter = new FragmentAdapter(FragmentView.this);
        back=(ImageView)this.findViewById(R.id.id_fragment_back);
        add=(ImageView)this.findViewById(R.id.id_fragment_add);
        random_btn = (ImageView)this.findViewById(R.id.id_fragment_random_button);
        memory_no = getIntent().getExtras().getInt("memory_no");

        // 刷新Gridview
        fragmentList = DataSupport.where("memory_id = ?",String.valueOf(memory_no)).order("createdate desc").find(Fragment.class);
        adapter.bindData(fragmentList);// 将数据与Gridview绑定
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(gridview_itemclicklistener);
        gridview.setOnItemLongClickListener(gridview_longclick);

        back.setOnClickListener(back_click);
        add.setOnClickListener(add_click);
        random_btn.setOnTouchListener(rand_button_OnTouchListener);


    }

    // 处理碎片被点击的事件
    OnItemClickListener gridview_itemclicklistener = new OnItemClickListener()
    {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("background", fragmentList.get(position).getImagepath());
            bundle.putString("content", fragmentList.get(position).getContent());
            bundle.putString("title",fragmentList.get(position).getTitle());
            bundle.putInt("memory_no", memory_no);
            intent.putExtras(bundle);
            intent.setClass(FragmentView.this, RefreshFragmentView.class);
            startActivityForResult(intent,2);
            overridePendingTransition(R.anim.default_anim_in, R.anim.default_anim_out);
            System.gc();
        }
    };
    OnItemLongClickListener gridview_longclick = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            dialog(position);
            return false;
        }
    };
    public void removeData(int position) {
        DataSupport.delete(Fragment.class, fragmentList.get(position).getId());
        fragmentList.remove(position);
        adapter.bindData(fragmentList);
        gridview.setAdapter(adapter);
    }
    protected void dialog(int position) {
        final int pos = position;
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(FragmentView.this);
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

    View.OnClickListener back_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finishActivity(0);
            overridePendingTransition(R.anim.default_anim_in, R.anim.default_anim_out);
            FragmentView.this.finish();
            System.gc();
        }
    };
    View.OnClickListener add_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra("memory_no", memory_no);
            intent.setClass(FragmentView.this, AddFragmentView.class);
            startActivityForResult(intent,1);
            overridePendingTransition(R.anim.default_anim_in, R.anim.default_anim_out);
            System.gc();
        }
    };
    /*
	 * 用于实现按钮长按，并且计时生成随机种子
	 */

    View.OnTouchListener rand_button_OnTouchListener = new View.OnTouchListener()
    {
        public boolean onTouch(View v, MotionEvent event)
        {
            Animation operatingAnim = AnimationUtils.loadAnimation(FragmentView.this, R.anim.rotatebtn);
            LinearInterpolator lin = new LinearInterpolator();
            operatingAnim.setInterpolator(lin);
            random_btn.setAnimation(operatingAnim);

            int[] a = new int[10];
            int number;

            // TODO Auto-generated method stub
            switch (event.getAction())
            {
                case MotionEvent.ACTION_DOWN:
                {
                    // 按下
                    parsetime = 0;
                    handler.post(updateThread);
                    random_btn.startAnimation(operatingAnim);
                }
                break;
                case MotionEvent.ACTION_UP:
                    // 抬起
                    handler.removeCallbacks(updateThread);

                    // 10 为size。表示生成10以内的数，这里你自己改
                    rand = new Tools_Random_number(parsetime, fragmentList.size());
                    // number 为获取到的随机数
                    number = rand.get_rand();
                    Toast toast = Toast.makeText(getApplicationContext(), "时间片:" + parsetime + "ms", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("background", fragmentList.get(number).getImagepath());
                    bundle.putString("content", fragmentList.get(number).getContent());
                    bundle.putString("title",fragmentList.get(number).getTitle());
                    bundle.putInt("memory_no", memory_no);
                    intent.putExtras(bundle);
                    intent.setClass(FragmentView.this, RefreshFragmentView.class);
                    startActivityForResult(intent,2);
                    overridePendingTransition(R.anim.default_anim_in, R.anim.default_anim_out);
                    random_btn.clearAnimation();
                    break;
            }
            return true;
        }
    };

    private Handler handler = new Handler();

    Runnable updateThread = new Runnable()
    {
        // 将要执行的操作写在线程对象的run方法当中
        public void run()
        {
            parsetime++;
            // 调用Handler的postDelayed()方法
            // 这个方法的作用是：将要执行的线程对象放入到队列当中，待时间结束后，运行制定的线程对象
            // 第一个参数是Runnable类型：将要执行的线程对象
            // 第二个参数是long类型：延迟的时间，以毫秒为单位
            handler.postDelayed(updateThread, 1);
        }
    };


}
