package com.bawei.dianshangjinweek03.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dianshangjinweek03.R;
import com.bawei.dianshangjinweek03.bean.OrderList;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailListAdapter extends RecyclerView.Adapter<DetailListAdapter.MyViewHouler> {
    //定义
    private List<OrderList.OrderListBean.DetailListBean> list = new ArrayList<>();
    private Unbinder unbinder;
    //添加数据方法
    public void addAllList(List<OrderList.OrderListBean.DetailListBean> dataList){
        list.addAll(dataList);
    }
    //视图
    @NonNull
    @Override
    public MyViewHouler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listcontents2, parent, false);
        return new MyViewHouler(view);
    }
    //设置数据
    @Override
    public void onBindViewHolder(@NonNull MyViewHouler holder, int position) {
        //获取数据
        OrderList.OrderListBean.DetailListBean detailListBean = list.get(position);
        //设置图片
        String[] split = detailListBean.getCommodityPic().split(",");
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.zhanpict)
                .fallback(R.drawable.zhanpict)
                .error(R.drawable.zhanpict)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(5)));
        Glide.with(holder.commodityPic.getContext())
                .applyDefaultRequestOptions(requestOptions)
                .load(split[0])
                .into(holder.commodityPic);
        //设置文本
        holder.commodityCount.setText("×" + detailListBean.getCommodityCount());
        holder.commodityName.setText(detailListBean.getCommodityName());
        holder.commodityPrice.setText("￥" + detailListBean.getCommodityPrice());
    }
    //数量
    @Override
    public int getItemCount() {
        return list.size();
    }
    //寄存器
    public class MyViewHouler extends RecyclerView.ViewHolder {
        @BindView(R.id.commodity_pic)
        protected ImageView commodityPic;
        @BindView(R.id.commodity_price)
        protected TextView commodityPrice;
        @BindView(R.id.commodity_count)
        protected TextView commodityCount;
        @BindView(R.id.commodity_name)
        protected TextView commodityName;
        public MyViewHouler(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
    //释放资源
    public void destroy(){
        unbinder.unbind();
        list.clear();
    }
}
