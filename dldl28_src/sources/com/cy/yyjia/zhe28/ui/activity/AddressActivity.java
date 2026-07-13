package com.cy.yyjia.zhe28.ui.activity;

import android.text.TextUtils;
import android.view.View;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.databinding.ActivityAddressBinding;
import com.cy.yyjia.zhe28.domain.AddressResult;
import com.cy.yyjia.zhe28.domain.Area;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.util.Repository;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AddressActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010\u0013\u001a\u00020\u0012H\u0016J\u0006\u0010\u0014\u001a\u00020\u0012J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/AddressActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityAddressBinding;", "()V", "area", "Lcom/cy/yyjia/zhe28/domain/Area;", "getArea", "()Lcom/cy/yyjia/zhe28/domain/Area;", "area$delegate", "Lkotlin/Lazy;", "selector", "Lcom/bigkoo/pickerview/view/OptionsPickerView;", "", "getSelector", "()Lcom/bigkoo/pickerview/view/OptionsPickerView;", "setSelector", "(Lcom/bigkoo/pickerview/view/OptionsPickerView;)V", "getMyAddress", "", "init", "initArea", "selectArea", "v", "Landroid/view/View;", "submit", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class AddressActivity extends BaseActivity<ActivityAddressBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: area$delegate, reason: from kotlin metadata */
    private final Lazy area;
    public OptionsPickerView<String> selector;

    public AddressActivity() {
        super(R.layout.activity_address, 0, 2, null);
        this.area = LazyKt.lazy(new Function0<Area>() { // from class: com.cy.yyjia.zhe28.ui.activity.AddressActivity$area$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Area invoke() {
                return new Area(this.this$0);
            }
        });
    }

    public static final /* synthetic */ ActivityAddressBinding access$getMBinding(AddressActivity addressActivity) {
        return addressActivity.getMBinding();
    }

    public final OptionsPickerView<String> getSelector() {
        OptionsPickerView<String> optionsPickerView = this.selector;
        if (optionsPickerView != null) {
            return optionsPickerView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("selector");
        return null;
    }

    public final void setSelector(OptionsPickerView<String> optionsPickerView) {
        Intrinsics.checkNotNullParameter(optionsPickerView, "<set-?>");
        this.selector = optionsPickerView;
    }

    public final Area getArea() {
        return (Area) this.area.getValue();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        initArea();
        getMyAddress();
    }

    public final void initArea() {
        OptionsPickerView<String> optionsPickerViewBuild = new OptionsPickerBuilder(this, new OnOptionsSelectListener() { // from class: com.cy.yyjia.zhe28.ui.activity.AddressActivity$$ExternalSyntheticLambda0
            @Override // com.bigkoo.pickerview.listener.OnOptionsSelectListener
            public final void onOptionsSelect(int i, int i2, int i3, View view) {
                AddressActivity.initArea$lambda$0(this.f$0, i, i2, i3, view);
            }
        }).setTitleText("选择省市区").build();
        Intrinsics.checkNotNullExpressionValue(optionsPickerViewBuild, "build(...)");
        setSelector(optionsPickerViewBuild);
        getSelector().setPicker(getArea().getProvinceList(), getArea().getCityList(), getArea().getAreaList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initArea$lambda$0(AddressActivity this$0, int i, int i2, int i3, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AddressResult.DataBean data = this$0.getMBinding().getData();
        Intrinsics.checkNotNull(data);
        data.setProvince(this$0.getArea().getProvinceList().get(i));
        AddressResult.DataBean data2 = this$0.getMBinding().getData();
        Intrinsics.checkNotNull(data2);
        data2.setCity(this$0.getArea().getCityList().get(i).get(i2));
        AddressResult.DataBean data3 = this$0.getMBinding().getData();
        Intrinsics.checkNotNull(data3);
        data3.setCountry(this$0.getArea().getAreaList().get(i).get(i2).get(i3));
        this$0.getMBinding().setData(this$0.getMBinding().getData());
    }

    public final void selectArea(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        hideSoftKeyboard();
        getSelector().show();
    }

    public final void getMyAddress() {
        Repository.INSTANCE.getAddress(new Function1<AddressResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.AddressActivity.getMyAddress.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AddressResult addressResult) {
                invoke2(addressResult);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AddressResult addressResult) {
                AddressResult.DataBean dataBean;
                ActivityAddressBinding activityAddressBindingAccess$getMBinding = AddressActivity.access$getMBinding(AddressActivity.this);
                if (addressResult == null || (dataBean = addressResult.getAddress()) == null) {
                    dataBean = new AddressResult.DataBean(null, null, null, null, null, null, null, null, null, null, 1023, null);
                }
                activityAddressBindingAccess$getMBinding.setData(dataBean);
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.AddressActivity.getMyAddress.2
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
                AddressActivity.this.netFail(it);
            }
        });
    }

    public final void submit(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        AddressResult.DataBean data = getMBinding().getData();
        if (data != null) {
            if (TextUtils.isEmpty(data.getName())) {
                toast("请输入收货人");
                return;
            }
            if (TextUtils.isEmpty(data.getTelephone())) {
                toast("请输入手机号码");
                return;
            }
            if (TextUtils.isEmpty(data.getAddress())) {
                toast("请输入详细地址");
                return;
            }
            if (TextUtils.isEmpty(data.getArea())) {
                toast("请输入所在地区");
                return;
            }
            Repository repository = Repository.INSTANCE;
            String name = data.getName();
            Intrinsics.checkNotNull(name);
            String telephone = data.getTelephone();
            Intrinsics.checkNotNull(telephone);
            String address = data.getAddress();
            Intrinsics.checkNotNull(address);
            repository.setAddress(name, telephone, address, data.getArea(), new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.AddressActivity$submit$1$1
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
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.AddressActivity$submit$1$2
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
    }
}
