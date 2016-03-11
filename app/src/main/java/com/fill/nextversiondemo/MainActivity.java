package com.fill.nextversiondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.fill.nextversiondemo.adapter.HeatPagerLeftListAdapter;
import com.fill.nextversiondemo.adapter.HeatPagerRightAdapter;
import com.fill.nextversiondemo.bean.HeatPagerRecycleLeftBean;
import com.fill.nextversiondemo.bean.HeatPagerRecycleRightBean;
import com.fill.nextversiondemo.utils.ToastUtils;
import com.fill.nextversiondemo.view.SlideFramelayout;
import com.fill.nextversiondemo.view.SyLinearLayoutManager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.sfl)
    private SlideFramelayout sfl;
    @ViewInject(R.id.rv_left)
    private RecyclerView rv_left;
    @ViewInject(R.id.rv_right)
    private RecyclerView rv_right;

    /**
     * 左边的数据集合
     */
    private SparseArray<HeatPagerRecycleLeftBean> leftBeans;
    /**
     * 右边的数据集合
     */
    private SparseArray<HeatPagerRecycleRightBean> rightBeans;
    private HeatPagerLeftListAdapter leftListAdapter;
    private HeatPagerRightAdapter rightAdapter;
    private boolean left = false;  //false 是左移，true 右移


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slide_buttom_menu);
        WindowManager wm = getWindowManager();
        Display dis = wm.getDefaultDisplay();
        android.view.WindowManager.LayoutParams lay = getWindow().getAttributes();
        lay.width = dis.getWidth()*1;
        getWindow().setAttributes(lay);
        x.view().inject(this);

        sfl.setAnimationTime(1000);   //设置滚动的时间

        loadLeftData();

        setLeftAdapter();
    }

    /**
     * 设置左边的数据Adapter
     */
    private void setLeftAdapter() {
        leftListAdapter = new HeatPagerLeftListAdapter(leftBeans, this, LayoutInflater.from(this));
        rv_left.setHasFixedSize(true);
        rv_left.setLayoutManager(new SyLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_left.setAdapter(leftListAdapter);
        leftListAdapter.setOnItemClickListener(new MyOnItemClickListener());

    }

    private class MyOnItemClickListener implements HeatPagerLeftListAdapter.OnItemClickListener{
        @Override
        public void onItemClick(View view, int position) {
            if (position == 0 || position == 1 || position == 2 || position == 3) {
                if (!left) {
                    if(position == 0){
                        loadRightData(1);
                    }else{
                        loadRightData(2);
                    }
                    LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(MainActivity.this, R.anim.rv_right_animation);
                    rv_right.setLayoutAnimation(controller);
                    rv_right.setLayoutManager(new SyLinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                    rightAdapter = new HeatPagerRightAdapter(rightBeans, MainActivity.this, LayoutInflater.from(MainActivity.this));
                    rv_right.setAdapter(rightAdapter);
                    rightAdapter.setOnItemClickListner(new MyRightOnItemClickListener());
                }
                nihao();
            }else if(position == 4){

            }else if(position == 5){
            }else if(position == 6){

            }
        }
    }

    private class MyRightOnItemClickListener implements HeatPagerRightAdapter.OnItemClickListner{
        @Override
        public void onItemClickListner(View view, int position) {
            ToastUtils.getInstanse(MainActivity.this).showToast(position +"");
        }
    }
    /**
     * 加载左边的数据   可以自己定义
     */
    private void loadLeftData() {
        leftBeans = new SparseArray<>();
        leftBeans.put(0,new HeatPagerRecycleLeftBean(R.mipmap.electricity, "电量详情"));
        leftBeans.put(1,new HeatPagerRecycleLeftBean(R.mipmap.jiangzao, "降噪设置"));
        leftBeans.put(2,new HeatPagerRecycleLeftBean(R.mipmap.style, "风格设置"));
        leftBeans.put(3,new HeatPagerRecycleLeftBean(R.mipmap.viode, "声场设置"));
        leftBeans.put(4,new HeatPagerRecycleLeftBean(R.mipmap.setting, "更多设置"));
        leftBeans.put(5,new HeatPagerRecycleLeftBean(R.mipmap.regsit, "产品注册"));
        leftBeans.put(6,new HeatPagerRecycleLeftBean(R.mipmap.operate, "操作指导"));
    }

    /**
     * 加载的右边的bean类集合   可以自己定义
     * @param flag
     */
    private void loadRightData(int flag){
        rightBeans = new SparseArray<>();
        if(flag == 1) {
            rightBeans.put(0,new HeatPagerRecycleRightBean(0, 1 + "", 2 + "", "", 1));
            rightBeans.put(1,new HeatPagerRecycleRightBean(R.mipmap.tupian, "", "", "通话时间\n" + 1, 2));
            rightBeans.put(2,new HeatPagerRecycleRightBean(R.mipmap.tupian, "", "", "待机时间\n" + 1, 2));
            rightBeans.put(3,new HeatPagerRecycleRightBean(R.mipmap.tupian, "", "", "音乐时间\n" + 1, 2));
        }else if(flag == 2){
            rightBeans.put(0,new HeatPagerRecycleRightBean(R.mipmap.tupian, "", "", "普通", 2));
            rightBeans.put(1,new HeatPagerRecycleRightBean(R.mipmap.tupian, "", "", "降噪", 2));
            rightBeans.put(2,new HeatPagerRecycleRightBean(R.mipmap.tupian, "", "", "监听", 2));
        }
    }

    private void nihao(){
        if(left){
            sfl.rightMove();
            setIconShow();
            left = false;
        }else{
            sfl.leftMove();
            setIconShow();
            left = true;
        }
    }

    /**
     * 设置图标的显示
     */
    private void setIconShow(){
        if(left){
            leftListAdapter.setIsShow(true);
            leftListAdapter.notifyDataSetChanged();
        }else{
            leftListAdapter.setIsShow(false);
            leftListAdapter.notifyDataSetChanged();
        }
    }

    //实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }
}
