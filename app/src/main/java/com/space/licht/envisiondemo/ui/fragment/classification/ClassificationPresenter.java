package com.space.licht.envisiondemo.ui.fragment.classification;


import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.model.DataManager;
import com.space.licht.envisiondemo.model.bean.VideoRes;
import com.space.licht.envisiondemo.model.http.response.VideoHttpResponse;
import com.space.licht.envisiondemo.preserter.RxPresenter;
import com.space.licht.envisiondemo.utils.RxUtil;
import com.space.licht.envisiondemo.utils.StringUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Description: ClassificationPresenter
 */
public class ClassificationPresenter extends RxPresenter<ClassificationContract.View> implements ClassificationContract.Presenter {
    int page = 0;
    private DataManager mDataManager;

    @Inject
    public ClassificationPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void onRefresh() {
        page = 0;
        getPageHomeInfo();
    }

    private void getPageHomeInfo() {
        Subscription rxSubscription = mDataManager.fetchHomePage()
                .compose(RxUtil.<VideoHttpResponse<VideoRes>>rxSchedulerHelper())
                .compose(RxUtil.<VideoRes>handleResult())
                .subscribe(new Action1<VideoRes>() {
                               @Override
                               public void call(final VideoRes res) {
                                   if (res != null) {
                                       mView.showContent(res);
                                   }
                               }
                           }, new Action1<Throwable>() {
                               @Override
                               public void call(Throwable throwable) {

                                    mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                               }
                           }, new Action0() {
                               @Override
                               public void call() {
                               }
                           }
                );

        addSubscribe(rxSubscription);

    }


    /**
     * 初始化数据
     */
    private void initDataI() {
        ArrayList<DataBean> mSGDatas = new ArrayList<DataBean>();
        DataBean sgbean1 = new DataBean();
        sgbean1.setSgName("刘备");
        sgbean1.setSgPetName("玄德");
        sgbean1.setSgHeadBp(R.drawable.father);
        sgbean1.setSgDescribe("刘备（161年－223年6月10日），字玄德，东汉末年幽州涿郡涿县（今河北省涿州市）人");
        DataBean sgbean2 = new DataBean();
        sgbean2.setSgName("关羽");
        sgbean2.setSgPetName("云长");
        sgbean2.setSgHeadBp(R.drawable.father_large);
        sgbean2.setSgDescribe("关羽（？－220年），本字长生，后改字云长，河东郡解良（今山西运城）人");
        DataBean sgbean3 = new DataBean();
        sgbean3.setSgName("张飞");
        sgbean3.setSgPetName("翼德");
        sgbean3.setSgHeadBp(R.drawable.mother);
        sgbean3.setSgDescribe("张飞（？－221年），字益德[1]  ，幽州涿郡（今河北省保定市涿州市）人氏");
        DataBean sgbean4 = new DataBean();
        sgbean4.setSgName("赵云");
        sgbean4.setSgPetName("子龙");
        sgbean4.setSgHeadBp(R.drawable.son);
        sgbean4.setSgDescribe("赵云（？－229年），字子龙，常山真定（今河北省正定）人");
        DataBean sgbean5 = new DataBean();
        sgbean5.setSgName("马超");
        sgbean5.setSgPetName("孟起");
        sgbean5.setSgHeadBp(R.drawable.daughter);
        sgbean5.setSgDescribe("马超（176年－222年），字孟起，司隶部扶风郡茂陵（今陕西兴平）人");
        mSGDatas.add(sgbean1);
        mSGDatas.add(sgbean2);
        mSGDatas.add(sgbean3);
        mSGDatas.add(sgbean4);
        mSGDatas.add(sgbean5);
    }
}
