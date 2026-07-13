package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.utils.LunarCalendar;
import com.bigkoo.pickerview.view.TimePickerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivitySafeBinding;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import java.util.Calendar;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: SafeActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0014J\u0006\u0010\f\u001a\u00020\u0006¨\u0006\r"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/SafeActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivitySafeBinding;", "Landroid/view/View$OnClickListener;", "()V", "getData", "", "init", "onClick", "v", "Landroid/view/View;", "onResume", "setBirthday", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SafeActivity extends BaseActivity<ActivitySafeBinding> implements View.OnClickListener {
    public static final int $stable = 0;

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
    }

    public SafeActivity() {
        super(R.layout.activity_safe, 0, 2, null);
    }

    public static final /* synthetic */ ActivitySafeBinding access$getMBinding(SafeActivity safeActivity) {
        return safeActivity.getMBinding();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (Constant.INSTANCE.getCancellation()) {
            finish();
        } else {
            getData();
        }
    }

    public final void getData() {
        Repository.INSTANCE.getUserData(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SafeActivity.getData.1
            {
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
                SafeActivity.access$getMBinding(SafeActivity.this).setData(it);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SafeActivity.getData.2
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
                SafeActivity.this.netFail(it);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.ll_address /* 2131362242 */:
                startActivity(AddressActivity.class);
                break;
            case R.id.ll_ali /* 2131362243 */:
                startActivity(AlipayBindActivity.class);
                break;
            case R.id.ll_auth /* 2131362244 */:
                UserBean data = getMBinding().getData();
                Intrinsics.checkNotNull(data);
                if (!data.isAuth()) {
                    startActivity(AuthActivity.class);
                }
                break;
            case R.id.ll_birthday /* 2131362246 */:
                UserBean data2 = getMBinding().getData();
                Intrinsics.checkNotNull(data2);
                if (TextUtils.isEmpty(data2.getBirthDay())) {
                    setBirthday();
                }
                break;
            case R.id.ll_cancellation /* 2131362249 */:
                startActivity(CancellationActivity.class);
                break;
            case R.id.ll_password /* 2131362268 */:
                startActivity(ChangePasswordActivity.class);
                break;
            case R.id.ll_phone /* 2131362269 */:
                Intent intent = new Intent(this, (Class<?>) PhoneActivity.class);
                UserBean data3 = getMBinding().getData();
                Intrinsics.checkNotNull(data3);
                startActivity(intent.putExtra("type", !TextUtils.isEmpty(data3.getTelphone()) ? 1 : 0));
                break;
            case R.id.ll_pin /* 2131362270 */:
                Intent intent2 = new Intent(this, (Class<?>) PhoneActivity.class);
                UserBean data4 = getMBinding().getData();
                Intrinsics.checkNotNull(data4);
                startActivity(intent2.putExtra("type", TextUtils.isEmpty(data4.getTrade_pin()) ? 2 : 3));
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [T, com.bigkoo.pickerview.view.TimePickerView] */
    public final void setBirthday() {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(LunarCalendar.MIN_YEAR, 0, 1);
        Calendar calendar3 = Calendar.getInstance();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new TimePickerBuilder(this, new OnTimeSelectListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SafeActivity$$ExternalSyntheticLambda2
            @Override // com.bigkoo.pickerview.listener.OnTimeSelectListener
            public final void onTimeSelect(Date date, View view) {
                SafeActivity.setBirthday$lambda$0(this.f$0, date, view);
            }
        }).setLayoutRes(R.layout.layout_my_wheel, new CustomListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SafeActivity$$ExternalSyntheticLambda3
            @Override // com.bigkoo.pickerview.listener.CustomListener
            public final void customLayout(View view) {
                SafeActivity.setBirthday$lambda$3(objectRef, view);
            }
        }).setType(new boolean[]{true, true, true, false, false, false}).setDate(calendar).setRangDate(calendar2, calendar3).build();
        ((TimePickerView) objectRef.element).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setBirthday$lambda$0(final SafeActivity this$0, Date date, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Repository.INSTANCE.setBirthday(calendar.get(1) + "-" + (calendar.get(2) + 1) + "-" + calendar.get(5), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SafeActivity$setBirthday$builder$1$1
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
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SafeActivity$setBirthday$builder$1$2
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
    public static final void setBirthday$lambda$3(final Ref.ObjectRef pvTime, View view) {
        Intrinsics.checkNotNullParameter(pvTime, "$pvTime");
        ((ImageView) view.findViewById(R.id.iv)).setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SafeActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SafeActivity.setBirthday$lambda$3$lambda$1(pvTime, view2);
            }
        });
        ((TextView) view.findViewById(R.id.f438tv)).setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SafeActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SafeActivity.setBirthday$lambda$3$lambda$2(pvTime, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setBirthday$lambda$3$lambda$1(Ref.ObjectRef pvTime, View view) {
        Intrinsics.checkNotNullParameter(pvTime, "$pvTime");
        T t = pvTime.element;
        Intrinsics.checkNotNull(t);
        ((TimePickerView) t).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void setBirthday$lambda$3$lambda$2(Ref.ObjectRef pvTime, View view) {
        Intrinsics.checkNotNullParameter(pvTime, "$pvTime");
        T t = pvTime.element;
        Intrinsics.checkNotNull(t);
        ((TimePickerView) t).returnData();
        T t2 = pvTime.element;
        Intrinsics.checkNotNull(t2);
        ((TimePickerView) t2).dismiss();
    }
}
