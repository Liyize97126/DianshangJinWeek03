package com.bawei.dianshangjinweek03.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.dianshangjinweek03.R;
import com.bawei.dianshangjinweek03.adapter.OrderListAdapter;
import com.bawei.dianshangjinweek03.bean.OrderList;
import com.bawei.dianshangjinweek03.contact.IContact;
import com.bawei.dianshangjinweek03.presenter.OrderPresenter;
import com.bawei.dianshangjinweek03.util.RetrofitUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderListFragment extends Fragment implements IContact.IView<OrderList> {
    //定义
    @BindView(R.id.order_list)
    protected RecyclerView orderList;
    private Unbinder unbinder;
    private OrderPresenter orderPresenter;
    private OrderListAdapter orderListAdapter;
    private int userId;
    private String sessionId;
    private int status;
    //构造
    public OrderListFragment(int userId, String sessionId, int status) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.status = status;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_order_list, null);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        orderList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        orderListAdapter = new OrderListAdapter();
        orderList.setAdapter(orderListAdapter);
        orderPresenter = new OrderPresenter(this);
        //判断网络
        if(RetrofitUtil.getRetrofitUtil().hasNet(getContext())){
            //发起请求
            orderPresenter.request(userId,sessionId,status,1,10);
        } else {
            //提示
            Toast.makeText(getContext(),"当前设备无网络，请检查网络设置！",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除绑定
        unbinder.unbind();
        if(orderPresenter != null){
            orderPresenter.destroy();
            orderPresenter = null;
        }
        if(orderListAdapter != null){
            orderListAdapter.destroy();
            orderListAdapter = null;
        }
    }
    @Override
    public void success(OrderList orderList) {
        if(orderList.getStatus().equals("0000")){
            List<OrderList.OrderListBean> orderListBeans = orderList.getOrderList();
            orderListAdapter.addAllList(orderListBeans);
            orderListAdapter.notifyDataSetChanged();
        } else {
            //提示
            Toast.makeText(getContext(),orderList.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void fail(String err) {
        //提示
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setMessage("发生错误了，请检查应用！\n错误信息：\n" + err);
        builder.setTitle("很抱歉");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
    }
}
