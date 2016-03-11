package com.fill.nextversiondemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fill.nextversiondemo.R;
import com.fill.nextversiondemo.bean.HeatPagerRecycleRightBean;


/**
 * Created by hyb on 2016/2/29.
 */
public class HeatPagerRightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private SparseArray<HeatPagerRecycleRightBean> rightBeans;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnItemClickListner onItemClickListner;

    private int mode;    //0 降噪  1：声场  2：风格


    public HeatPagerRightAdapter(SparseArray<HeatPagerRecycleRightBean> rightBeans, Context mContext, LayoutInflater mLayoutInflater) {
        this.rightBeans = rightBeans;
        this.mContext = mContext;
        this.mLayoutInflater = mLayoutInflater;
    }

    @Override
    public int getItemCount() {
        return rightBeans.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if(viewType == 1){
            view = mLayoutInflater.inflate(R.layout.heat_pager_right_adapter1, parent,false);
            return new ViewHolder1(view);
        }else if(viewType == 2){
            view = mLayoutInflater.inflate(R.layout.heat_pager_right_adapter2, parent,false);
            return new ViewHolder2(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        HeatPagerRecycleRightBean rightBean = rightBeans.get(position);
        if (holder instanceof ViewHolder1){    //类型1 item
            ViewHolder1 holder1 = (ViewHolder1) holder;
            holder1.elc.setText(rightBean.getElc());
            holder1.mode.setText(rightBean.getMode());
            holder1.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListner!=null){
                        onItemClickListner.onItemClickListner(v,position);
                    }
                }
            });
        }else if(holder instanceof ViewHolder2){  //类型1 item
            ViewHolder2 holder2 = (ViewHolder2) holder;
            holder2.show.setText(rightBean.getShow());
            holder2.rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListner!=null){
                        onItemClickListner.onItemClickListner(v,position);
                    }
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        return rightBeans.get(position).getType();
    }

    class ViewHolder2 extends RecyclerView.ViewHolder{
        public TextView show;
        public RelativeLayout rl;
        public View itemView;

        public ViewHolder2(View itemView) {
            super(itemView);
            this.itemView = itemView;
            show = (TextView) itemView.findViewById(R.id.tv_right_show);
            rl = (RelativeLayout) itemView.findViewById(R.id.rl_right_bg);
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder{
        public TextView elc;
        public TextView mode;
        public RelativeLayout rl;
        public View itemView;

        public ViewHolder1(View itemView) {
            super(itemView);
            this.itemView = itemView;
            rl = (RelativeLayout) itemView.findViewById(R.id.rl_right_text_bg);
            elc = (TextView) itemView.findViewById(R.id.tv_right_elc);
            mode = (TextView) itemView.findViewById(R.id.tv_right_mode);
        }
    }

    public interface OnItemClickListner{
        void onItemClickListner(View view, int position);
    }
    public void getMode(int mode){
        this.mode = mode;
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }
}
