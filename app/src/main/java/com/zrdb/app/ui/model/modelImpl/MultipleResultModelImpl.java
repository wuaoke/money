package com.zrdb.app.ui.model.modelImpl;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.zrdb.app.netcallback.AppStringCallback;
import com.zrdb.app.ui.callback.IMultipleResultCallback;
import com.zrdb.app.ui.model.IMultipleResultModel;
import com.zrdb.app.util.ApiUtils;
import com.zrdb.app.util.EncryptUtil;
import com.zrdb.app.util.TimeUtil;

public class MultipleResultModelImpl implements IMultipleResultModel {
    @Override
    public void sendNetMultiple(String token, String uid, String keyword, final IMultipleResultCallback callback) {
        String url = ApiUtils.Config.getDimen() + ApiUtils.MULTIPLE_RESULT_URL + EncryptUtil.getMD5("Indexsearch"
                + TimeUtil.date2String(TimeUtil.getNowDate(), TimeUtil.YEAR_MONTH_DAY)
                + ApiUtils.clientSecret);
        OkGo.<String>post(url)
                .params("token", token)
                .params("uid", uid)
                .params("keywords", keyword)
                .execute(new AppStringCallback(callback) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callback.getMultipleResult(response.body());
                    }
                });
    }
}
