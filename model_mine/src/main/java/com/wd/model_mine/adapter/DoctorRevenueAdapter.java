package com.wd.model_mine.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.model_base.Bean.RevenueBean;
import com.wd.model_mine.R;
import com.wd.model_mine.R2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: Doctor
 * @Package: com.wd.mine.adapter
 * @ClassName: DoctorRevenueAdapter
 * @Description: (java类作用描述)
 * @Author: 谭亚森
 * @CreateDate: 2020/5/31 0:26
 */
public class DoctorRevenueAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<RevenueBean.ResultBean> list;


    public DoctorRevenueAdapter(Context context, List<RevenueBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_doctorrevenue, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int incomeType = list.get(position).getIncomeType();
        if(incomeType==1){
            ((ViewHolder)holder).tv1.setText("问诊咨询");
        }else if(incomeType==2){
            ((ViewHolder)holder).tv1.setText("病友圈建议被采纳");
        }else if(incomeType ==3){
            ((ViewHolder)holder).tv1.setText("收到礼物");
        }else{
            ((ViewHolder)holder).tv1.setText("提现");
        }
        String data = new SimpleDateFormat("yyyy-MM-dd").format(new Date(list.get(position).getRecordTime()));
        String[] split = data.split("-");
        ((ViewHolder)holder).tv2.setText(split[0]+"."+split[1]+"."+split[2]);

        int direction = list.get(position).getDirection();
        if(direction==1){
            ((ViewHolder)holder).tv3.setText("+"+list.get(position).getMoney()+"H币");
        }else{
            @SuppressLint("ResourceType") int color = context.getResources().getColor(Color.BLUE);
            ((ViewHolder)holder).tv3.setTextColor(color);
            ((ViewHolder)holder).tv3.setText("-"+list.get(position).getMoney()+"H币");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv1)
        TextView tv1;
        @BindView(R2.id.tv2)
        TextView tv2;
        @BindView(R2.id.tv3)
        TextView tv3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
