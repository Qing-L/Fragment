package com.simple.jylsc.fragment.dao.model;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/14.
 */
public class MemoryColor {

    private List<String> colorList = new ArrayList<>();

    //构造函数
    public MemoryColor()
    {
        AddColor();
    }

    //向colorList中添加色值
    protected void AddColor()
    {
        colorList.add("#f16d7a");
        colorList.add("#e27386");
        colorList.add("#ca7497");
        colorList.add("#f55066");
        colorList.add("#ef5464");
        colorList.add("#aa5b71");
        colorList.add("#ae716e");
        colorList.add("#c86f67");
        colorList.add("#cb8e85");
        colorList.add("#cf8878");
        colorList.add("#ff9b6a");
        colorList.add("#f1ccb8");
        colorList.add("#f2debd");
        colorList.add("#b7d28d");
        colorList.add("#dcff93");
        colorList.add("#f1b8e4");
        colorList.add("#d9b8f1");
        colorList.add("#f1ccb8");
        colorList.add("#f1f1b8");
        colorList.add("#b8f1ed");
        colorList.add("#b8f1cc");
        colorList.add("#e7dac9");
        colorList.add("#e1622f");
        colorList.add("#ff8240");
        colorList.add("#fd7d36");
        colorList.add("#fe9778");
        colorList.add("#c38e9e");
        colorList.add("#f28860");
        colorList.add("#de772c");
        colorList.add("#e96a25");
        colorList.add("#e29e4b");
        colorList.add("#f3d64e");
        colorList.add("#fecf45");
        colorList.add("#edbf2b");
        colorList.add("#f0b631");
        colorList.add("#f9b747");
        colorList.add("#c17e61");
        colorList.add("#ed9678");
        colorList.add("#cf8888");
    }

    //获取colorList
    public List<String> getColorList()
    {
        return colorList;
    }
}
