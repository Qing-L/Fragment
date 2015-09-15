package com.simple.jylsc.fragment.dao.model;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2015/9/14.
 */
public class Memory extends DataSupport {

    private int id;//ID自增长列
    private String memoryName;//记忆名称
    private String memoryColor;//记忆颜色

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemoryName() {
        return memoryName;
    }

    public void setMemoryName(String memoryName) {
        this.memoryName = memoryName;
    }

    public String getMemoryColor() {
        return memoryColor;
    }

    public void setMemoryColor(String memoryColor) {
        this.memoryColor = memoryColor;
    }
}
