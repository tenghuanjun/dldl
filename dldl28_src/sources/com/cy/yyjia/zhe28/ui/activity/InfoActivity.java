package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.FastDialog;
import com.cy.yyjia.zhe28.databinding.ActivityInfoBinding;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.ui.activity.InfoActivity;
import com.cy.yyjia.zhe28.util.Repository;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.mobile.auth.gatewayauth.Constant;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.lang3.BooleanUtils;

/* JADX INFO: compiled from: InfoActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\b\u0010\b\u001a\u00020\u0006H\u0016J\"\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0006H\u0014¨\u0006\u0013"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/InfoActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityInfoBinding;", "Landroid/view/View$OnClickListener;", "()V", "getData", "", "getPic", "init", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "", "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "onResume", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class InfoActivity extends BaseActivity<ActivityInfoBinding> implements View.OnClickListener {
    public static final int $stable = 0;

    public InfoActivity() {
        super(R.layout.activity_info, 0, 2, null);
    }

    public static final /* synthetic */ ActivityInfoBinding access$getMBinding(InfoActivity infoActivity) {
        return infoActivity.getMBinding();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().setData(new UserBean());
    }

    /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.InfoActivity$getData$1, reason: invalid class name */
    /* JADX INFO: compiled from: InfoActivity.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/cy/yyjia/zhe28/domain/UserBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function1<UserBean, Unit> {
        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(UserBean userBean) {
            invoke2(userBean);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(UserBean it) {
            Intrinsics.checkNotNullParameter(it, "it");
            InfoActivity.access$getMBinding(InfoActivity.this).setData(it);
            RadioGroup radioGroup = InfoActivity.access$getMBinding(InfoActivity.this).rg;
            final InfoActivity infoActivity = InfoActivity.this;
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.cy.yyjia.zhe28.ui.activity.InfoActivity$getData$1$$ExternalSyntheticLambda0
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup2, int i) {
                    InfoActivity.AnonymousClass1.invoke$lambda$0(infoActivity, radioGroup2, i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(final InfoActivity this$0, RadioGroup radioGroup, int i) {
            String str;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            switch (i) {
                case R.id.rb1 /* 2131362424 */:
                    str = "male";
                    break;
                case R.id.rb2 /* 2131362425 */:
                    str = "female";
                    break;
                default:
                    str = BooleanUtils.NO;
                    break;
            }
            UserBean data = InfoActivity.access$getMBinding(this$0).getData();
            Intrinsics.checkNotNull(data);
            data.setUserSex(str);
            Repository.INSTANCE.setUserSex(str, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InfoActivity$getData$1$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                    invoke2(result);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Result it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    this$0.toast(it.getMsg());
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InfoActivity$getData$1$1$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    this$0.netFail(it);
                }
            });
        }
    }

    public final void getData() {
        Repository.INSTANCE.getUserData(new AnonymousClass1(), new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InfoActivity.getData.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                InfoActivity.this.netFail(it);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        getData();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        int id = v.getId();
        if (id != R.id.ll_avatar) {
            if (id != R.id.ll_nickname) {
                return;
            }
            new FastDialog(getMContext()).setContentView(R.layout.dialog_modify_nick).setOnClickListener(R.id.f438tv, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.InfoActivity$$ExternalSyntheticLambda0
                @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
                public final void onClick(BaseDialog baseDialog, View view) {
                    InfoActivity.onClick$lambda$0(this.f$0, baseDialog, view);
                }
            }).show();
        } else {
            List<String> listListOf = CollectionsKt.listOf(Permission.READ_MEDIA_IMAGES);
            if (XXPermissions.isGranted(getMContext(), listListOf)) {
                getPic();
            } else {
                XXPermissions.with(getMContext()).permission(listListOf).request(new OnPermissionCallback() { // from class: com.cy.yyjia.zhe28.ui.activity.InfoActivity$$ExternalSyntheticLambda1
                    @Override // com.hjq.permissions.OnPermissionCallback
                    public /* synthetic */ void onDenied(List list, boolean z) {
                        OnPermissionCallback.CC.$default$onDenied(this, list, z);
                    }

                    @Override // com.hjq.permissions.OnPermissionCallback
                    public final void onGranted(List list, boolean z) {
                        InfoActivity.onClick$lambda$1(this.f$0, list, z);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$0(final InfoActivity this$0, final BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View viewFindViewById = baseDialog.findViewById(R.id.et);
        Intrinsics.checkNotNull(viewFindViewById);
        Repository.INSTANCE.setNickname(((EditText) viewFindViewById).getText().toString(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InfoActivity$onClick$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                invoke2(result);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Result it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.toast(it.getMsg());
                this.this$0.getData();
                baseDialog.dismiss();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InfoActivity$onClick$1$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                this.this$0.netFail(it);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onClick$lambda$1(InfoActivity this$0, List list, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(list, "<anonymous parameter 0>");
        if (z) {
            this$0.getPic();
        }
    }

    public final void getPic() {
        ImageSelector.builder().useCamera(false).setSingle(true).setCrop(true).start(getMContext(), 11);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 11 || data == null) {
            return;
        }
        ArrayList<String> stringArrayListExtra = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
        Intrinsics.checkNotNull(stringArrayListExtra);
        if (stringArrayListExtra.size() > 0) {
            Repository.INSTANCE.setAvatar(new File(stringArrayListExtra.get(0)), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InfoActivity.onActivityResult.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Result result) {
                    invoke2(result);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Result it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    InfoActivity.this.toast(it.getMsg());
                    InfoActivity.this.getData();
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.InfoActivity.onActivityResult.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    InfoActivity.this.netFail(it);
                }
            });
        }
    }
}
