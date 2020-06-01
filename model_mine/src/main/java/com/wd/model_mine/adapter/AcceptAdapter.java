package com.wd.model_mine.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wd.model_base.Bean.AcceptedBean;
import com.wd.model_mine.R;
import com.wd.model_mine.R2;
import com.wd.model_mine.activity.AcceptedActivity;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName AcceptAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/5/2820:17
 */
public class AcceptAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<AcceptedBean.ResultBean> list;

    public AcceptAdapter(Context context, List<AcceptedBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_accep, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).na.setText(list.get(position).getReleaseUserNickName());
        ((ViewHolder)holder).name.setText(list.get(position).getTitle());
        ((ViewHolder)holder).zheng.setText("主要症状："+list.get(position).getDisease());
        long adoptTime = list.get(position).getAdoptTime();
        String date = new SimpleDateFormat("yyyy.MM.dd").format(
                new java.util.Date(adoptTime));
        ((ViewHolder)holder).time.setText("采纳时间："+date);
        Glide.with(context).load(list.get(position).getReleaseUserHeadPic()).apply(new RequestOptions().circleCrop()).into(((ViewHolder)holder).iv);
        ((ViewHolder)holder).jianyi.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView jianyi;
        private final TextView time;
        private final TextView zheng;
        private final TextView na;
        private final TextView name;
        private final ImageView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.head);
            name = itemView.findViewById(R.id.name);
            na = itemView.findViewById(R.id.na);
            zheng = itemView.findViewById(R.id.zheng);
            time = itemView.findViewById(R.id.time);
            jianyi = itemView.findViewById(R.id.jianyi);
        }
    }
}
