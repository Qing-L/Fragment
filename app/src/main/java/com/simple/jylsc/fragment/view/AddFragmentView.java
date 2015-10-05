package com.simple.jylsc.fragment.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.simple.jylsc.fragment.R;
import com.simple.jylsc.fragment.adapter.AddFragmentAdapter;
import com.simple.jylsc.fragment.dao.model.Fragment;
import com.simple.jylsc.fragment.dao.model.Memory;
import com.simple.jylsc.fragment.tool.Tools_Random_number;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/10/2.
 */
public class AddFragmentView extends Activity {

    private GridView gridview;
    private AddFragmentAdapter adapter;
    private List<String> imageList = new ArrayList<String>();

    private ImageView back;
    private ImageView add;
    private EditText editText;
    private ImageView imageView;
    DisplayImageOptions options;

    private int memory_no;
    private int random;
    private int current_position;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_fragment_layout);

        add=(ImageView) this.findViewById(R.id.id_add_fragment_add);
        back=(ImageView) this.findViewById(R.id.id_add_fragment_back);
        editText=(EditText) this.findViewById(R.id.id_add_fragment_edittext);
        gridview = (GridView) this.findViewById(R.id.id_add_fragment_gridview);
        imageView = (ImageView) this.findViewById(R.id.id_add_fragment_imageview);
        adapter = new AddFragmentAdapter(AddFragmentView.this);
        //显示图片的配置
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        AddData();

        adapter.bindData(imageList);
        gridview.setAdapter(adapter);

        WindowManager wm = this.getWindowManager();
        int height = wm.getDefaultDisplay().getHeight() - gridview.getHeight()-70;
        editText.setHeight(height);
        editText.setHint("请输入文字");
        Log.i("height", String.valueOf(height));


        back.setOnClickListener(back_click);
        add.setOnClickListener(add_click);
        gridview.setOnItemClickListener(item_click);


        memory_no = getIntent().getExtras().getInt("memory_no");
        random = new Tools_Random_number(imageList.size()).get_random().nextInt(imageList.size());
        ImageLoader.getInstance().displayImage(imageList.get(random), imageView, options);
        current_position = random;
    }

    public void back()
    {
        finishActivity(1);
        overridePendingTransition(R.anim.default_anim_in, R.anim.default_anim_out);
        AddFragmentView.this.finish();
        System.gc();
    }

    View.OnClickListener back_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            back();
        }
    };
    View.OnClickListener add_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(editText.getText().toString().length()==0)
            {
                Toast.makeText(AddFragmentView.this, "请输入内容", Toast.LENGTH_SHORT).show();
            }else{
                SampleViewHolder viewHolder = new SampleViewHolder(v);
            }
        }
    };

    AdapterView.OnItemClickListener item_click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ImageLoader.getInstance().displayImage(imageList.get(position), imageView, options);
            current_position=position;
        }
    };
    private void AddData()
    {
        //图片源于assets
        for(int i=1;i<9;i++) {
            String imageUrl = ImageDownloader.Scheme.ASSETS.wrap("Image/bg"+i+".jpg");
            imageList.add(imageUrl);
        }
    }

    public  class SampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemView;

        public void showDialog()
        {
            final LayoutInflater inflater = LayoutInflater.from(AddFragmentView.this);
            View view = inflater.inflate(R.layout.dialog_addmemory_edittext, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(AddFragmentView.this);
            builder.setView(view);
            final AlertDialog dialog = builder.create();
            dialog.show();
            ImageView buttonCancel;
            ImageView buttonConfirm;
            final EditText nameInput;
            buttonCancel=(ImageView) view.findViewById(R.id.id_AddMemoryButtonCancel);
            buttonConfirm=(ImageView) view.findViewById(R.id.id_AddMemoryButtonConfirm);
            nameInput=(EditText) view.findViewById(R.id.id_AddMemoryEditText);
            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            buttonConfirm.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Fragment fragment = new Fragment();

                    if(nameInput.getText().toString().length()==0)
                    {
                        Toast.makeText(AddFragmentView.this, "请输入标题", Toast.LENGTH_SHORT).show();
                    }else{
                        fragment.setTitle(nameInput.getText().toString());
                        fragment.setContent(editText.getText().toString());
                        fragment.setCreatedate(new Date());
                        fragment.setImagepath(imageList.get(current_position));
                        fragment.setMemory(DataSupport.find(Memory.class, memory_no));
                        fragment.save();
                        Intent intent = new Intent();
                        intent.putExtra("memory_no",memory_no);
                        intent.setClass(AddFragmentView.this,FragmentView.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.default_anim_in, R.anim.default_anim_out);
                        AddFragmentView.this.finish();
                        System.gc();
                        Toast.makeText(AddFragmentView.this, "Succeed", Toast.LENGTH_SHORT).show();
                    }//标题不为空
                }
            });
        }

        public SampleViewHolder(View view) {
            super(view);
            showDialog();
        }
    }
}
