package com.cy.yyjia.zhe28.base;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.donkingliang.imageselector.utils.ImageSelector;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseAdapter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0017\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u00042\u00020\u0006Bd\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012S\u0010\t\u001aO\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0015\u0012\u0013\u0018\u00018\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\n¢\u0006\u0002\u0010\u0011Bt\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0013\u0012S\u0010\t\u001aO\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0015\u0012\u0013\u0018\u00018\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\n¢\u0006\u0002\u0010\u0014B\u001f\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0013¢\u0006\u0002\u0010\u0015J#\u0010\u001b\u001a\u00020\u00102\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010\u000f\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0012\u0010 \u001a\u00020\u00102\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000Rg\u0010\u0016\u001aO\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0015\u0012\u0013\u0018\u00018\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\nX\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006#"}, d2 = {"Lcom/cy/yyjia/zhe28/base/BaseAdapter;", ExifInterface.GPS_DIRECTION_TRUE, "DB", "Landroidx/databinding/ViewDataBinding;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseDataBindingHolder;", "Lcom/chad/library/adapter/base/module/LoadMoreModule;", "layoutId", "", "function", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "holder", ImageSelector.POSITION, "item", "", "(ILkotlin/jvm/functions/Function3;)V", "data", "", "(ILjava/util/List;Lkotlin/jvm/functions/Function3;)V", "(ILjava/util/List;)V", "mFun", "getMFun", "()Lkotlin/jvm/functions/Function3;", "setMFun", "(Lkotlin/jvm/functions/Function3;)V", "convert", "(Lcom/chad/library/adapter/base/viewholder/BaseDataBindingHolder;Ljava/lang/Object;)V", "onAttachedToRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "setMyEmptyView", "type", "", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public class BaseAdapter<T, DB extends ViewDataBinding> extends BaseQuickAdapter<T, BaseDataBindingHolder<DB>> implements LoadMoreModule {
    public static final int $stable = 8;
    private final int layoutId;
    protected Function3<? super BaseDataBindingHolder<DB>, ? super Integer, ? super T, Unit> mFun;

    @Override // com.chad.library.adapter.base.module.LoadMoreModule
    public /* synthetic */ BaseLoadMoreModule addLoadMoreModule(BaseQuickAdapter baseQuickAdapter) {
        return LoadMoreModule.CC.$default$addLoadMoreModule(this, baseQuickAdapter);
    }

    public /* synthetic */ BaseAdapter(int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : list);
    }

    public BaseAdapter(int i, List<T> list) {
        super(i, list);
        this.layoutId = i;
        setMFun(new Function3<BaseDataBindingHolder<DB>, Integer, T, Unit>(this) { // from class: com.cy.yyjia.zhe28.base.BaseAdapter.1
            final /* synthetic */ BaseAdapter<T, DB> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
                this.this$0 = this;
            }

            public final void invoke(BaseDataBindingHolder<DB> baseDataBindingHolder, int i2, T t) {
                Intrinsics.checkNotNullParameter(baseDataBindingHolder, "<anonymous parameter 0>");
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Object obj, Integer num, Object obj2) {
                invoke((BaseDataBindingHolder) obj, num.intValue(), obj2);
                return Unit.INSTANCE;
            }
        });
    }

    protected final Function3<BaseDataBindingHolder<DB>, Integer, T, Unit> getMFun() {
        Function3<? super BaseDataBindingHolder<DB>, ? super Integer, ? super T, Unit> function3 = this.mFun;
        if (function3 != null) {
            return function3;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mFun");
        return null;
    }

    protected final void setMFun(Function3<? super BaseDataBindingHolder<DB>, ? super Integer, ? super T, Unit> function3) {
        Intrinsics.checkNotNullParameter(function3, "<set-?>");
        this.mFun = function3;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BaseAdapter(int i, Function3<? super BaseDataBindingHolder<DB>, ? super Integer, ? super T, Unit> function) {
        this(i, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(function, "function");
        setMFun(function);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BaseAdapter(int i, List<T> list, Function3<? super BaseDataBindingHolder<DB>, ? super Integer, ? super T, Unit> function) {
        this(i, list);
        Intrinsics.checkNotNullParameter(function, "function");
        setMFun(function);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseDataBindingHolder<DB> holder, T item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        getMFun().invoke(holder, Integer.valueOf(holder.getLayoutPosition()), item);
        ViewDataBinding dataBinding = holder.getDataBinding();
        if (dataBinding != null) {
            dataBinding.setVariable(23, item);
            dataBinding.executePendingBindings();
        }
    }

    @Override // com.chad.library.adapter.base.BaseQuickAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static /* synthetic */ void setMyEmptyView$default(BaseAdapter baseAdapter, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setMyEmptyView");
        }
        if ((i & 1) != 0) {
            str = "";
        }
        baseAdapter.setMyEmptyView(str);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final void setMyEmptyView(String type) {
        setEmptyView(R.layout.layout_empty);
        FrameLayout emptyLayout = getEmptyLayout();
        if (emptyLayout == null || type == null) {
            return;
        }
        switch (type.hashCode()) {
            case -1668866777:
                if (type.equals("teamJoin")) {
                    ((TextView) emptyLayout.findViewById(R.id.f438tv)).setText("无申请信息");
                    break;
                }
                break;
            case -1059471140:
                if (type.equals("mygift")) {
                    ((TextView) emptyLayout.findViewById(R.id.f438tv)).setText("还没有礼包，快去领取礼包吧！");
                    break;
                }
                break;
            case -1048622135:
                if (type.equals("trumpet")) {
                    ((TextView) emptyLayout.findViewById(R.id.f438tv)).setText("暂时没有小号，快去创建吧！");
                    break;
                }
                break;
            case 3079276:
                if (type.equals("deal")) {
                    ((ImageView) emptyLayout.findViewById(R.id.iv)).setImageResource(R.mipmap.ic_empty_deal);
                    break;
                }
                break;
        }
    }
}
