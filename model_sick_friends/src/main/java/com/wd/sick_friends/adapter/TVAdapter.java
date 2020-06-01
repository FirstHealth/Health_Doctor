package com.wd.sick_friends.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.model_base.Bean.KeShiBean;
import com.wd.sick_friends.R;
import com.wd.sick_friends.R2;
import com.wd.sick_friends.activity.Sick_HomeActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @ClassName TVAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/5/2219:38
 */
public class TVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<KeShiBean.ResultBean> list;

    public TVAdapter(Context context, List<KeShiBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_tv, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder)holder).tv.setText(list.get(position).getDepartmentName());

        if (list.get(position).getColor() == 0) {
            ((ViewHolder) holder).tv.setTextColor(R.color.gray2);
        }else {
            ((ViewHolder) holder).tv.setTextColor(Color.GREEN);
        }

        ((ViewHolder)holder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++){
                    list.get(i).setColor(0);
                }
                Toast.makeText(context, "11", Toast.LENGTH_SHORT).show();
                KeShiBean.ResultBean resultBean = list.get(position);
                EventBus.getDefault().post(resultBean);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv;
        private final LinearLayout ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
