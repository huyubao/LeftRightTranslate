package com.fill.nextversiondemo.bean;

/**
 * 主界面效果的右边ListView的Bean
 * Created by hyb on 2016/2/27.
 */
public class HeatPagerRecycleRightBean {
    private int id;
    private String elc;
    private String mode;
    private String show;
    private int type;

    public HeatPagerRecycleRightBean(int id, String elc, String mode, String show, int type) {
        this.id = id;
        this.elc = elc;
        this.mode = mode;
        this.show = show;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getElc() {
        return elc;
    }

    public void setElc(String elc) {
        this.elc = elc;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "HeatPagerRecycleRightBean{" +
                "id=" + id +
                ", elc='" + elc + '\'' +
                ", mode='" + mode + '\'' +
                ", show='" + show + '\'' +
                ", type=" + type +
                '}';
    }
}
