package com.bawei.dianshangjinweek03.presenter;

import com.bawei.dianshangjinweek03.base.BasePresenter;
import com.bawei.dianshangjinweek03.contact.IContact;

import io.reactivex.Observable;

public class OrderPresenter extends BasePresenter {
    public OrderPresenter(IContact.IView iView) {
        super(iView);
    }
    @Override
    protected Observable getObservable(Object... args) {
        return iRequest.findOrderListByStatus((int) args[0],(String) args[1],(int) args[2],(int) args[3],(int) args[4]);
    }
}
