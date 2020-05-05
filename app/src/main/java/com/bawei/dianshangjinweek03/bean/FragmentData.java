package com.bawei.dianshangjinweek03.bean;

import com.bawei.dianshangjinweek03.fragment.OrderListFragment;

public class FragmentData {
    private OrderListFragment orderListFragment;
    private String fragmentTitle;
    public FragmentData(OrderListFragment orderListFragment, String fragmentTitle) {
        this.orderListFragment = orderListFragment;
        this.fragmentTitle = fragmentTitle;
    }
    public OrderListFragment getOrderListFragment() {
        return orderListFragment;
    }
    public void setOrderListFragment(OrderListFragment orderListFragment) {
        this.orderListFragment = orderListFragment;
    }
    public String getFragmentTitle() {
        return fragmentTitle;
    }
    public void setFragmentTitle(String fragmentTitle) {
        this.fragmentTitle = fragmentTitle;
    }
}
