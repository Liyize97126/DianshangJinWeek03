package com.bawei.dianshangjinweek03.presenter;

import com.bawei.dianshangjinweek03.base.BasePresenter;
import com.bawei.dianshangjinweek03.contact.IContact;

import io.reactivex.Observable;

public class LoginPresenter extends BasePresenter {
    public LoginPresenter(IContact.IView iView) {
        super(iView);
    }
    @Override
    protected Observable getObservable(Object... args) {
        return iRequest.login((String) args[0], (String) args[1]);
    }
}
