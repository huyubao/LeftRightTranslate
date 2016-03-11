package com.fill.nextversiondemo.utils;

import android.content.Context;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * 提示工具类
 * Created by hyb on 2016/1/30.
 */
public class ToastUtils {
    private static ToastUtils utils;
    private static Context context;
    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };

    private ToastUtils() {

    }

    public static ToastUtils getInstanse(Context context) {
        if (utils == null) {
            synchronized (ToastUtils.class) {
                if (utils == null) {
                    utils = new ToastUtils();
                }
            }
        }
        ToastUtils.context = context;
        return utils;
    }

    /**
     * 老方法显示
     *
     * @param s
     */
    public void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * 5.0以后添加的显示风格
     */
    public void showSnack(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 可以根据用户的动作做出相应的处理
     *
     * @param view
     * @param text
     * @param actionName
     * @param listener
     */
    public void showSnack(View view, String text, String actionName, View.OnClickListener listener) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
                .setAction(actionName, listener).show();
    }


    /**
     * 直接是字符串
     *
     * @param mContext
     * @param text
     * @param duration
     */
    public static void showToast(Context mContext, String text, int duration) {

        mHandler.removeCallbacks(r);
        if (mToast != null)
            mToast.setText(text);
        else
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        mHandler.postDelayed(r, duration);

        mToast.show();
    }

    /**
     * 传的是里面的资源
     *
     * @param mContext
     * @param resId
     * @param duration
     */
    public static void showToast(Context mContext, int resId, int duration) {
        showToast(mContext, mContext.getResources().getString(resId), duration);
    }

    /**
     * 直接写好的时间，穿内容就可以了
     *
     * @param mContext
     */
    public static void showToast(Context mContext, String text) {
        showToast(mContext, text, 1000);
    }


}
