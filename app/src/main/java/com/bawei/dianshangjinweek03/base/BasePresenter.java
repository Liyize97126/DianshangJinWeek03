package com.bawei.dianshangjinweek03.base;

import com.bawei.dianshangjinweek03.contact.IContact;
import com.bawei.dianshangjinweek03.util.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter {
    //定义
    private IContact.IView iView;
    protected IContact.IRequest iRequest;
    //构造
    public BasePresenter(IContact.IView iView) {
        this.iView = iView;
        iRequest = RetrofitUtil.getRetrofitUtil().create(IContact.IRequest.class);
    }
    //发起请求
    public void request(Object...args){
        getObservable(args).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        iView.success(o);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        iView.fail(throwable.getMessage());
                    }
                });
    }
    //方法封装
    protected abstract Observable getObservable(Object...args);
    //释放资源
    public void destroy(){
        if(iView != null){
            iView = null;
        }
    }
}
