package com.wd.sick_friends.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.model_base.Bean.SearchSickBean;
import com.wd.model_base.Bean.SickBean;
import com.wd.sick_friends.R;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @ClassName SickAdapter
 * @Description TODO
 * @Author tys
 * @Date 2020/5/2221:02
 */
public class Sick2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<SearchSickBean.ResultBean> list;

    public Sick2Adapter(Context context, List<SearchSickBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            View view = View.inflate(context, R.layout.item_sick, null);
            ViewHolderOne viewHolderOne = new ViewHolderOne(view);
            return viewHolderOne;
        }else {
            View view = View.inflate(context, R.layout.item_sick2, null);
            ViewHolderTwo viewHolderTwo = new ViewHolderTwo(view);
            return viewHolderTwo;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == 0){
            ((ViewHolderOne)holder).name.setText(list.get(position).getTitle()+"");
            ((ViewHolderOne)holder).count.setText(list.get(position).getDetail()+"");
            ((ViewHolderOne)holder).maney.setText("");
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(
                    new java.util.Date(list.get(position).getReleaseTime()));
            ((ViewHolderOne)holder).data.setText(date);


        }else {
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(
                    new java.util.Date(list.get(position).getReleaseTime()));
            ((ViewHolderTwo)holder).data.setText(date);
            ((ViewHolderTwo)holder).name.setText(list.get(position).getTitle()+"");
            ((ViewHolderTwo)holder).count.setText(list.get(position).getDetail()+"");
            ((ViewHolderTwo)holder).maney.setText("");
            ((ViewHolderTwo)holder).money.setText(list.get(position).getAmount()+"");

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getAmount() == 0){
            return 0;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder{

        private final TextView name;
        private final TextView count;
        private final TextView maney;
        private final TextView data;
        private final RelativeLayout ll;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            count = itemView.findViewById(R.id.count);
            maney = itemView.findViewById(R.id.maney);
            data = itemView.findViewById(R.id.data);
            ll = itemView.findViewById(R.id.ll);
        }
    }

    public class ViewHolderTwo extends RecyclerView.ViewHolder{
        private final TextView name;
        private final TextView count;
        private final TextView maney;
        private final TextView data;
        private final TextView money;
        private final RelativeLayout ll;

        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            count = itemView.findViewById(R.id.count);
            maney = itemView.findViewById(R.id.maney);
            data = itemView.findViewById(R.id.data);
            money = itemView.findViewById(R.id.money);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
