package com.zrdb.app.ui.model.modelImpl;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.zrdb.app.netcallback.AppStringCallback;
import com.zrdb.app.ui.callback.IMeBespokeCallback;
import com.zrdb.app.ui.model.IMeBespokeModel;
import com.zrdb.app.util.ApiUtils;
import com.zrdb.app.util.EncryptUtil;
import com.zrdb.app.util.TimeUtil;

public class MeBespokeModelImpl implements IMeBespokeModel {
    @Override
    public void sendNetBespoke(String token, String uid, final IMeBespokeCallback callback) {
        String url = ApiUtils.Config.getDimen() + ApiUtils.BESPOKE_LIST_URL + EncryptUtil.getMD5("Subscribelistindex"
                + TimeUtil.date2String(TimeUtil.getNowDate(), TimeUtil.YEAR_MONTH_DAY)
                + ApiUtils.clientSecret);
        OkGo.<String>post(url)
                .params("token", token)
                .params("uid", uid)
                .execute(new AppStringCallback(callback) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        callback.getBespokeList(response.body());
                    }
                });
    }
}
