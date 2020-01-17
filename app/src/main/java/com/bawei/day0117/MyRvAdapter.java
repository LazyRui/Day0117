package com.bawei.day0117;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ProjectName: Day0117
 * PackageName: com.bawei.day0117
 * ClassName:   MyRvAdapter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/17_9:06
 */
public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.VH> {



    private Context context;
    private List<FindOrderEntity.OrderListBean> orderList;

    public MyRvAdapter(Context context, List<FindOrderEntity.OrderListBean> orderList) {

        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(View.inflate(context, R.layout.item_all_list, null));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        FindOrderEntity.OrderListBean list = orderList.get(position);

        long orderTime = list.getOrderTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        Date date = new Date(orderTime);

        String format = simpleDateFormat.format(date);

        holder.tvOrderTime.setText(format);

        Log.e("xxx",format);

        holder.tvOrderId.setText("订单号：  " + list.getOrderId());

        holder.iRv.setLayoutManager(new LinearLayoutManager(context));

        MyIRVAdapter myIRVAdapter = new MyIRVAdapter(context,list.getDetailList());

        holder.iRv.setAdapter(myIRVAdapter);

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_order_id)
        TextView tvOrderId; @BindView(R.id.tv_order_time)
        TextView tvOrderTime;
        @BindView(R.id.i_rv)
        RecyclerView iRv;
        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
