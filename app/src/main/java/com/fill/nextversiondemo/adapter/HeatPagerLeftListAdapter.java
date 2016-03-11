package com.fill.nextversiondemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fill.nextversiondemo.R;
import com.fill.nextversiondemo.bean.HeatPagerRecycleLeftBean;
import com.fill.nextversiondemo.view.SlideFramelayout;


/**
 * Created by hyb on 2016/2/27.
 */
public class HeatPagerLeftListAdapter extends RecyclerView.Adapter<HeatPagerLeftListAdapter.ViewHolder>{
    private SparseArray<HeatPagerRecycleLeftBean> leftBeans;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private boolean isShow = false;
    private int rightWidth;


    /**
     * 设置监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private OnItemClickListener onItemClickListener;

    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    public void setRightWidth(int rightWidth) {
        this.rightWidth = rightWidth;
    }

    public HeatPagerLeftListAdapter(SparseArray<HeatPagerRecycleLeftBean> leftBeans, Context mContext, LayoutInflater mLayoutInflater) {
        this.leftBeans = leftBeans;
        this.mContext = mContext;
        this.mLayoutInflater = mLayoutInflater;
    }

    @Override
    public HeatPagerLeftListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.heat_pager_left_adapter, parent, false);
        HeatPagerLeftListAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        HeatPagerRecycleLeftBean bean = leftBeans.get(position);
        holder.icon.setImageResource(bean.getId());
        holder.name.setText(bean.getName());
        holder.menu.setImageResource(bean.getId());
        holder.menu.setVisibility(View.VISIBLE);
        holder.menu.setAlpha(0f);
//        if (isShow) {
//            holder.icon.setVisibility(View.VISIBLE);
//            holder.name.setVisibility(View.VISIBLE);
//            holder.menu.setVisibility(View.GONE);
//        } else {
//            holder.icon.setVisibility(View.GONE);
//            holder.name.setVisibility(View.GONE);
//            holder.menu.setVisibility(View.VISIBLE);
//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(v,position);
                }
            }
        });
    }

    @Override
    public void onViewAttachedToWindow(final ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ((SlideFramelayout)holder.itemView.getParent().getParent())   //设置动画监听
                .setmOnScrollChangeListener(new SlideFramelayout.OnScrollListener() {
                    @Override
                    public void onScroll(float arg0) {
                        if (isShow) {
                            holder.icon.setAlpha(1 - arg0);
                            holder.name.setAlpha(1 - arg0);
                            holder.menu.setAlpha(arg0);
                            if (arg0 == 0) {
                                holder.icon.setAlpha(1f);
                                holder.name.setAlpha(1f);
                                holder.menu.setAlpha(0f);
                            }
                        } else {
                            holder.icon.setAlpha(1 - arg0);
                            holder.name.setAlpha(1 - arg0);
                            holder.menu.setAlpha(arg0);
                            if (arg0 == 0) {
                                holder.icon.setAlpha(0f);
                                holder.name.setAlpha(0f);
                                holder.menu.setAlpha(1f);
                            }
                        }
                    }
                });
    }

    @Override
    public int getItemCount() {
        return leftBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView name;
        public ImageView menu;
        public View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            icon = (ImageView) itemView.findViewById(R.id.iv_left_rv_icon);
            name = (TextView) itemView.findViewById(R.id.tv_left_rv_name);
            menu = (ImageView) itemView.findViewById(R.id.iv_left_iv_menu);
        }
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        ((SlideFramelayout)holder.itemView.getParent().getParent()).removeViewHolder(holder);
    }

    /**
     * 自定义监听
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
