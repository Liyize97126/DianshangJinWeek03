package com.bawei.dianshangjinweek03.activity;

import android.content.Intent;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bawei.dianshangjinweek03.R;
import com.bawei.dianshangjinweek03.base.BaseActivity;
import com.bawei.dianshangjinweek03.bean.FragmentData;
import com.bawei.dianshangjinweek03.fragment.OrderListFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class OrderListActivity extends BaseActivity {
    //定义
    @BindView(R.id.head_pic)
    protected ImageView headPic;
    @BindView(R.id.phone)
    protected TextView phone;
    @BindView(R.id.tablay)
    protected TabLayout tablay;
    @BindView(R.id.viewp)
    protected ViewPager viewp;
    private List<FragmentData> list;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_list;
    }
    @Override
    protected void initData() {
        //获取值
        Intent intent = getIntent();
        String sessionId = intent.getStringExtra("sessionId");
        int userId = intent.getIntExtra("userId", 0000);
        String phone = intent.getStringExtra("phone");
        String headPic = intent.getStringExtra("headPic");
        //设置图片
        RequestOptions options = new RequestOptions()
                .fallback(R.drawable.headpic)
                .error(R.drawable.headpic)
                .placeholder(R.drawable.headpic)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()));
        Glide.with(this.headPic.getContext())
                .applyDefaultRequestOptions(options)
                .load(headPic)
                .into(this.headPic);
        //设置文本
        this.phone.setText(phone);
        //初始化集合
        list = new ArrayList<>();
        list.add(new FragmentData(new OrderListFragment(userId,sessionId,0),"全部"));
        list.add(new FragmentData(new OrderListFragment(userId,sessionId,1),"待付款"));
        list.add(new FragmentData(new OrderListFragment(userId,sessionId,2),"待收货"));
        list.add(new FragmentData(new OrderListFragment(userId,sessionId,3),"待评价"));
        list.add(new FragmentData(new OrderListFragment(userId,sessionId,9),"已完成"));
        //设置适配器
        viewp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position).getFragmentTitle();
            }
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position).getOrderListFragment();
            }
            @Override
            public int getCount() {
                return list.size();
            }
        });
        //关联
        tablay.setupWithViewPager(viewp);
        tablay.setTabTextColors(Color.parseColor("#000000"),Color.parseColor("#2196F3"));
    }
    @Override
    protected void initDestroy() {
    }
}
