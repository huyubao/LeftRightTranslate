package com.fill.nextversiondemo.bean;

/**
 * 耳机界面recycleView 左边的bean
 * Created by hyb on 2016/2/27.
 */
public class HeatPagerRecycleLeftBean {
    private int id;
    private String name;

    public HeatPagerRecycleLeftBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeatPagerRecycleLeftBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
