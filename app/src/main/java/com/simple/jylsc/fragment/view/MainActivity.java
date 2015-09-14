package com.simple.jylsc.fragment.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simple.jylsc.fragment.R;
import com.simple.jylsc.fragment.tool.guidebackgroundcoloranimation.ColorAnimationView;


public class MainActivity
        extends FragmentActivity

{
    private static ViewPager viewPager;
    private static final int[] resource = new int[]{R.layout.layout_setting, 0, 0};
    private static int[] colors = new int[5];
    private static final String TAG = MainActivity.class.getSimpleName();
    private MemoryView memoryView = new MemoryView();
    private AddMemoryView addMemoryView = new AddMemoryView();

    public static void setPage(int position) {
        viewPager.setCurrentItem(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        MyFragmentStatePager adpter = new MyFragmentStatePager(getSupportFragmentManager());
        ColorAnimationView colorAnimationView = (ColorAnimationView) findViewById(R.id.ColorAnimationView);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adpter);
        this.setPage(1);
        /**
         *  首先，你必须在 设置 Viewpager的 adapter 之后在调用这个方法
         *  第二点，setmViewPager(ViewPager mViewPager,Object obj, int count, int... colors)
         *         第一个参数 是 你需要传人的 viewpager
         *         第二个参数 是 一个实现了ColorAnimationView.OnPageChangeListener接口的Object,用来实现回调
         *         第三个参数 是 viewpager 的 孩子数量
         *         第四个参数 int... colors ，你需要设置的颜色变化值~~ 如何你传人 空，那么触发默认设置的颜色动画
         * */
        /**
         *  Frist: You need call this method after you set the Viewpager adpter;
         * Second: setmViewPager(ViewPager mViewPager,Object obj， int count, int... colors)
         *          so,you can set any length colors to make the animation more cool!
         * Third: If you call this method like below, make the colors no data, it will create
         *          a change color by default.
         * */

        colors[0] = this.getResources().getColor(R.color.setting_background);
        colors[1] = this.getResources().getColor(R.color.main_to_setting);
        colors[2] = this.getResources().getColor(R.color.main_bg);
        colors[3] = this.getResources().getColor(R.color.addmemory_bg);

        //  colors[3]=this.getResources().getColor(R.color.main_to_addmemeory);
        colors[4] = this.getResources().getColor(R.color.addmemory_bg);
        colorAnimationView.setmViewPager(viewPager, resource.length, colors);
        colorAnimationView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("TAG", "onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("TAG", "onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("TAG", "onPageScrollStateChanged"+state);
            }
        });
        // Four : Also ,you can call this method like this:
        // colorAnimationView.setmViewPager(viewPager,this,resource.length,0xffFF8080,0xff8080FF,0xffffffff,0xff80ff80);
    }


    public class MyFragmentStatePager
            extends FragmentStatePagerAdapter {

        public MyFragmentStatePager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 1) {
                return memoryView;
            }
            if (position == 2) {
                return addMemoryView;
            }


            return new MyFragment(0);
        }

        @Override
        public int getCount() {
            return resource.length;
        }
    }

    @SuppressLint("ValidFragment")
    public class MyFragment
            extends Fragment {
        private int position;

        public MyFragment(int position) {
            this.position = position;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(resource[position], null);
//			ImageView imageView = new ImageView(getActivity());
//			imageView.setImageResource(resource[position]);
//			return imageView;
        }
    }
}
