package com.huya.berry.modifynickname;

import android.text.TextUtils;
import com.duowan.HUYA.ModifyUserNickRsp;
import com.duowan.HUYA.UserNickStatusRsp;
import com.duowan.auk.ArkUtils;
import com.duowan.auk.signal.IASlot;
import com.duowan.auk.ui.widget.ArkToast;
import com.duowan.auk.util.L;
import com.duowan.live.common.framework.AbsPresenter;
import com.huya.berry.gamesdk.module.commonevent.CommonEvent;
import com.huya.component.login.api.LoginApi;
import com.huya.component.user.api.IUserService;
import com.huya.component.user.api.UserCallback;
import com.huya.live.ns.rxjava.WupObserver;
import com.huya.live.rxutils.SchedulerUtils;
import com.huya.live.service.ServiceCenter;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.hyns.stat.NSStatUtil;
import com.huya.mtp.hyns.wup.WupError;
import com.rxjava.rxlife.ObservableLife;
import com.rxjava.rxlife.RxLife;
import io.reactivex.Observer;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ModifyNicknamePresenterImpl extends AbsPresenter implements IModifyNicknamePresenter {
    private static final String BASE_CLASS_NAME = ModifyNicknamePresenterImpl.class.getName();
    private WeakReference<IModifyNickNameView> mView;

    public ModifyNicknamePresenterImpl(IModifyNickNameView iModifyNickNameView) {
        this.mView = new WeakReference<>(iModifyNickNameView);
    }

    @Override // com.duowan.live.common.framework.AbsPresenter, com.duowan.live.common.framework.IPresenter
    public void onCreate() {
        super.onCreate();
        ArkUtils.register(this);
    }

    @Override // com.duowan.live.common.framework.AbsPresenter, com.duowan.live.common.framework.IPresenter
    public void onDestroy() {
        super.onDestroy();
        ArkUtils.unregister(this);
    }

    @Override // com.huya.berry.modifynickname.IModifyNicknamePresenter
    public void getUserNickNameStatus() {
        IUserService iUserService = (IUserService) ServiceCenter.instance().getService(IUserService.class);
        if (iUserService != null) {
            ((ObservableLife) iUserService.getUserNickNameStatus().compose(SchedulerUtils.net()).as(RxLife.as(this))).subscribe((Observer) new WupObserver<UserNickStatusRsp>() { // from class: com.huya.berry.modifynickname.ModifyNicknamePresenterImpl.1
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(UserNickStatusRsp userNickStatusRsp) {
                    if (ModifyNicknamePresenterImpl.this.mView == null || ModifyNicknamePresenterImpl.this.mView.get() == null) {
                        return;
                    }
                    ((IModifyNickNameView) ModifyNicknamePresenterImpl.this.mView.get()).handleNickNameStatus(userNickStatusRsp);
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    if (ModifyNicknamePresenterImpl.this.mView == null || ModifyNicknamePresenterImpl.this.mView.get() == null) {
                        return;
                    }
                    ((IModifyNickNameView) ModifyNicknamePresenterImpl.this.mView.get()).handleNickNameStatusFail();
                }
            });
        }
    }

    @Override // com.huya.berry.modifynickname.IModifyNicknamePresenter
    public void saveUserNickName(String str, String str2, int i) {
        IUserService iUserService = (IUserService) ServiceCenter.instance().getService(IUserService.class);
        if (iUserService != null) {
            ((ObservableLife) iUserService.modifyUserNickname(str2, str, i).compose(SchedulerUtils.net()).as(RxLife.as(this))).subscribe((Observer) new WupObserver<ModifyUserNickRsp>() { // from class: com.huya.berry.modifynickname.ModifyNicknamePresenterImpl.2
                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onNext(ModifyUserNickRsp modifyUserNickRsp) {
                    L.info(this, "OnModifyUserNickname success " + modifyUserNickRsp);
                    if (ModifyNicknamePresenterImpl.this.mView == null || ModifyNicknamePresenterImpl.this.mView.get() == null) {
                        return;
                    }
                    ModifyNicknamePresenterImpl.this.getUserProfile();
                    ((IModifyNickNameView) ModifyNicknamePresenterImpl.this.mView.get()).handleModifyNickNameRsp(modifyUserNickRsp);
                    ArkUtils.send(new CommonEvent.GoUserCenter());
                }

                @Override // com.huya.live.ns.rxjava.WupObserver, io.reactivex.Observer
                public void onError(Throwable th) {
                    ModifyUserNickRsp modifyUserNickRsp;
                    L.error(this, "OnModifyUserNickname error " + th);
                    if (th instanceof DataException) {
                        Throwable throwable = NSStatUtil.parseThrowable((DataException) th);
                        if (throwable instanceof WupError) {
                            WupError wupError = (WupError) throwable;
                            String str3 = null;
                            if (wupError.mResponse instanceof ModifyUserNickRsp) {
                                ModifyUserNickRsp modifyUserNickRsp2 = (ModifyUserNickRsp) wupError.mResponse;
                                str3 = modifyUserNickRsp2.sMessage;
                                modifyUserNickRsp = modifyUserNickRsp2;
                            } else {
                                modifyUserNickRsp = null;
                            }
                            int i2 = wupError.mCode;
                            if (i2 == 911) {
                                if (ModifyNicknamePresenterImpl.this.mView == null || ModifyNicknamePresenterImpl.this.mView.get() == null) {
                                    return;
                                }
                                ((IModifyNickNameView) ModifyNicknamePresenterImpl.this.mView.get()).popupMoneyNotEnough();
                                return;
                            }
                            if (i2 == 924) {
                                if (!TextUtils.isEmpty(str3)) {
                                    ArkToast.show(str3);
                                    return;
                                } else {
                                    ArkToast.show("保存失败，昵称包含敏感词汇");
                                    return;
                                }
                            }
                            if (i2 != 925) {
                                if (TextUtils.isEmpty(str3)) {
                                    return;
                                }
                                ArkToast.show(str3);
                            } else {
                                if (modifyUserNickRsp == null) {
                                    return;
                                }
                                ((IModifyNickNameView) ModifyNicknamePresenterImpl.this.mView.get()).navToVerify(modifyUserNickRsp.sVerifyUrl);
                            }
                        }
                    }
                }
            });
        }
    }

    @IASlot(executorID = 1)
    public void onSaveUserNickName(UserCallback.OnSaveUserNickName onSaveUserNickName) {
        if (onSaveUserNickName == null || TextUtils.isEmpty(onSaveUserNickName.verifyCode)) {
            ArkToast.show("验证码错误");
            return;
        }
        WeakReference<IModifyNickNameView> weakReference = this.mView;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mView.get().saveWithVerifyCode(onSaveUserNickName.verifyCode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserProfile() {
        IUserService iUserService = (IUserService) ServiceCenter.instance().getService(IUserService.class);
        if (iUserService != null) {
            ((ObservableLife) iUserService.getUserProfile(LoginApi.getUid()).compose(SchedulerUtils.ioio()).as(RxLife.as(this))).subscribe((Observer) new WupObserver());
        }
    }
}
