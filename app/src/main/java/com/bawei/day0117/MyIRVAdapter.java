package com.bawei.day0117;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.day0117.wight.NumLayout;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ProjectName: Day0117
 * PackageName: com.bawei.day0117
 * ClassName:   MyIRVAdapter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/17_9:28
 */
public class MyIRVAdapter extends RecyclerView.Adapter<MyIRVAdapter.VH> {


    private final Context context;
    private final List<FindOrderEntity.OrderListBean.DetailListBean> detailList;


    public MyIRVAdapter(Context context, List<FindOrderEntity.OrderListBean.DetailListBean> detailList) {

        this.context = context;
        this.detailList = detailList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(View.inflate(context, R.layout.item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        FindOrderEntity.OrderListBean.DetailListBean list = detailList.get(position);
        String commodityPic = list.getCommodityPic();
        String[] split = commodityPic.split(",");

        Glide.with(context)
                .load(split[0])
                .into(holder.ivPhoto);
        holder.tvPName.setText(list.getCommodityName());
        holder.tvPrice.setText("￥:" + Double.valueOf(list.getCommodityPrice()));

        TextView jian = holder.numLayout.findViewById(R.id.jian);
        TextView num = holder.numLayout.findViewById(R.id.num);
        TextView jia = holder.numLayout.findViewById(R.id.jia);

        jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = num.getText().toString();
                int integer = Integer.valueOf(s);
                integer--;
                if (integer < 1) {
                    integer = 1;
                } else {
                    num.setText(integer + "");
                }

            }
        });

        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = num.getText().toString();
                int integer = Integer.valueOf(s);
                integer++;
                num.setText(integer + "");

            }
        });

    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_photo)
        ImageView ivPhoto;
        @BindView(R.id.tv_p_name)
        TextView tvPName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.num_layout)
        NumLayout numLayout;

        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
