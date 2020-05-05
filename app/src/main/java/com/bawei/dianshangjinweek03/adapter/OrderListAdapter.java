package com.bawei.dianshangjinweek03.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dianshangjinweek03.R;
import com.bawei.dianshangjinweek03.bean.OrderList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyViewHouler> {
    //定义
    private List<OrderList.OrderListBean> list = new ArrayList<>();
    private Unbinder unbinder;
    private DetailListAdapter detailListAdapter;
    //添加数据方法
    public void addAllList(List<OrderList.OrderListBean> dataList){
        list.addAll(dataList);
    }
    //清除数据方法
    public void clearList(){
        list.clear();
    }
    //视图
    @NonNull
    @Override
    public MyViewHouler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listcontents1, parent, false);
        return new MyViewHouler(view);
    }
    //设置数据
    @Override
    public void onBindViewHolder(@NonNull MyViewHouler holder, int position) {
        //获取数据
        OrderList.OrderListBean orderListBean = list.get(position);
        //设置文本
        holder.orderId.setText(orderListBean.getOrderId());
        holder.payAmount.setText("￥" + orderListBean.getPayAmount());
        //判断订单状态
        int orderStatus = orderListBean.getOrderStatus();
        switch (orderStatus){
            case 1:{
                holder.orderStatus.setText("尚未支付");
                holder.status1.setVisibility(View.VISIBLE);
                holder.status2.setVisibility(View.GONE);
                holder.status3.setVisibility(View.GONE);
                holder.status4.setVisibility(View.GONE);
            }break;
            case 2:{
                holder.orderStatus.setText("已发货");
                holder.status1.setVisibility(View.GONE);
                holder.status2.setVisibility(View.VISIBLE);
                holder.status3.setVisibility(View.GONE);
                holder.status4.setVisibility(View.GONE);
            }break;
            case 3:{
                holder.orderStatus.setText("待评价");
                holder.status1.setVisibility(View.GONE);
                holder.status2.setVisibility(View.GONE);
                holder.status3.setVisibility(View.VISIBLE);
                holder.status4.setVisibility(View.GONE);
            }break;
            case 9:{
                holder.orderStatus.setText("已完成");
                holder.status1.setVisibility(View.GONE);
                holder.status2.setVisibility(View.GONE);
                holder.status3.setVisibility(View.GONE);
                holder.status4.setVisibility(View.VISIBLE);
            }break;
        }
        //设置适配器
        holder.detailList.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(),
                LinearLayoutManager.VERTICAL,false));
        detailListAdapter = new DetailListAdapter();
        holder.detailList.setAdapter(detailListAdapter);
        detailListAdapter.addAllList(orderListBean.getDetailList());
    }
    //数量
    @Override
    public int getItemCount() {
        return list.size();
    }
    //寄存器
    public class MyViewHouler extends RecyclerView.ViewHolder {
        @BindView(R.id.order_id)
        protected TextView orderId;
        @BindView(R.id.order_status)
        protected TextView orderStatus;
        @BindView(R.id.detail_list)
        protected RecyclerView detailList;
        @BindView(R.id.pay_amount)
        protected TextView payAmount;
        @BindView(R.id.status1)
        protected RelativeLayout status1;
        @BindView(R.id.status2)
        protected RelativeLayout status2;
        @BindView(R.id.status3)
        protected RelativeLayout status3;
        @BindView(R.id.status4)
        protected RelativeLayout status4;
        public MyViewHouler(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
    //释放资源
    public void destroy(){
        detailListAdapter.destroy();
        unbinder.unbind();
        list.clear();
    }
}
