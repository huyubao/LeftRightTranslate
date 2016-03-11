package com.fill.nextversiondemo.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义视图，主界面的效果
 * Created by hyb on 2016/2/27.
 */
public class SlideFramelayout extends FrameLayout{
    private int leftHeigth;
    private int leftWidth;
    private int rightWidth;

    private int animationTime = 1000;  //左右移的动画时间

//    private OnScrollListener mOnScrollChangeListener;
    private List<OnScrollListener> mOnScrollListener;

    private Scroller mScroller;
    public SlideFramelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        mOnScrollListener = new ArrayList<>();
    }

    /**
     * 设置滚动监听
     * @param mOnScrollChangeListener
     */
    public void setmOnScrollChangeListener(OnScrollListener mOnScrollChangeListener) {
//        this.mOnScrollChangeListener = mOnScrollChangeListener;
        mOnScrollListener.add(mOnScrollChangeListener);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        leftHeigth = getChildAt(0).getMeasuredHeight();
        leftWidth = getChildAt(0).getMeasuredWidth();
        rightWidth = getChildAt(1).getMeasuredWidth();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        getChildAt(1).layout(leftWidth,getChildAt(0).getTop(),leftWidth+rightWidth,getChildAt(0).getTop()+leftHeigth);
    }

    @Override
    public void computeScroll() {
        //先判断mScroller滚动是否完成
        if (mScroller.computeScrollOffset()) {
            //这里调用View的scrollTo()完成实际的滚动
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
//            LogUtil.e("getTranslationX()"+getScrollX());
//            LogUtil.e(((float) getScrollX()) / rightWidth + "---");
            if(mOnScrollListener.size() >0){
                for (int i = 0; i < mOnScrollListener.size(); i++) {
                    mOnScrollListener.get(i).onScroll(((float)getScrollX())/rightWidth);
                }
            }
            //必须调用该方法，否则不一定能看到滚动效果
            invalidate();
        }
    }

    /**
     * 左移
     */
    public void leftMove(){
        mScroller.startScroll(0,0,rightWidth,0,animationTime);
        invalidate();
    }

    /**
     * 右移
     */
    public void rightMove(){
        mScroller.startScroll(rightWidth,0,-rightWidth,0,animationTime);
        invalidate();
    }


    /**
     * 设置动画时间
     * @param animationTime
     */
    public void setAnimationTime(int animationTime) {
        this.animationTime = animationTime;
    }


    public int getRightWidth() {
        return rightWidth;
    }

    /**
     * 定义滚动兼听
     */
    public interface OnScrollListener{
        void onScroll(float arg0);
    }

    public void removeViewHolder(RecyclerView.ViewHolder vh){
        mOnScrollListener.remove(vh);
    }

}
