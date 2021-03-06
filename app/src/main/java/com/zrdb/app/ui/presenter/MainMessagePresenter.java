package com.zrdb.app.ui.presenter;

import com.zrdb.app.ui.callback.IMainMessageCallback;
import com.zrdb.app.ui.model.modelImpl.MainMessageModelImpl;
import com.zrdb.app.ui.viewImpl.IMainMessageView;

public class MainMessagePresenter extends BasePresenter<IMainMessageView> implements IMainMessageCallback {

    private final MainMessageModelImpl model;

    public MainMessagePresenter(IMainMessageView view) {
        super(view);
        model = new MainMessageModelImpl();
    }

    public void sendNetMessage(String token, String uid) {
        if (mView != null) mView.showLoading();
        if (model != null) model.sendNetMessage(token, uid, this);
    }

    public void sendNetEnsureState(String token, String uid) {
        if (mView != null) mView.showLoading();
        if (model != null) model.sendNetEnsureState(token, uid, this);
    }

    @Override
    public void getMessageList(String result) {
        if (!checkResultError(result)) {
            mView.getMessageList(result);
        }
    }

    @Override
    public void ensureState(String result) {
        if (!checkResultError(result)) {
            mView.ensureState(result);
        }
    }
}
