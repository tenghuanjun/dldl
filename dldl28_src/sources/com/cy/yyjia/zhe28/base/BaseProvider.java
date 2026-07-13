package com.cy.yyjia.zhe28.base;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.exifinterface.media.ExifInterface;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.donkingliang.imageselector.utils.ImageSelector;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseProvider.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0010\b\u0017\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B{\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012b\u0010\b\u001a^\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00018\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\t¢\u0006\u0002\u0010\u0012B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\u0013J\u001d\u0010\u001c\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001dJ\u0018\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u0006H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0007\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015Rv\u0010\u0017\u001a^\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00018\u0000¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00110\tX\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006!"}, d2 = {"Lcom/cy/yyjia/zhe28/base/BaseProvider;", ExifInterface.GPS_DIRECTION_TRUE, "DB", "Landroidx/databinding/ViewDataBinding;", "Lcom/chad/library/adapter/base/provider/BaseItemProvider;", "itemViewType", "", "layoutId", "function", "Lkotlin/Function4;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "Lkotlin/ParameterName;", "name", "holder", "binding", ImageSelector.POSITION, "item", "", "(IILkotlin/jvm/functions/Function4;)V", "(II)V", "getItemViewType", "()I", "getLayoutId", "mFun", "getMFun", "()Lkotlin/jvm/functions/Function4;", "setMFun", "(Lkotlin/jvm/functions/Function4;)V", "convert", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Ljava/lang/Object;)V", "onViewHolderCreated", "viewHolder", "viewType", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public class BaseProvider<T, DB extends ViewDataBinding> extends BaseItemProvider<T> {
    public static final int $stable = 8;
    private final int itemViewType;
    private final int layoutId;
    protected Function4<? super BaseViewHolder, ? super DB, ? super Integer, ? super T, Unit> mFun;

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public int getItemViewType() {
        return this.itemViewType;
    }

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public int getLayoutId() {
        return this.layoutId;
    }

    public BaseProvider(int i, int i2) {
        this.itemViewType = i;
        this.layoutId = i2;
        setMFun(new Function4<BaseViewHolder, DB, Integer, T, Unit>(this) { // from class: com.cy.yyjia.zhe28.base.BaseProvider.1
            final /* synthetic */ BaseProvider<T, DB> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(4);
                this.this$0 = this;
            }

            public final void invoke(BaseViewHolder baseViewHolder, DB db, int i3, T t) {
                Intrinsics.checkNotNullParameter(baseViewHolder, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(db, "<anonymous parameter 1>");
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, Object obj, Integer num, Object obj2) {
                invoke(baseViewHolder, (ViewDataBinding) obj, num.intValue(), obj2);
                return Unit.INSTANCE;
            }
        });
    }

    protected final Function4<BaseViewHolder, DB, Integer, T, Unit> getMFun() {
        Function4<? super BaseViewHolder, ? super DB, ? super Integer, ? super T, Unit> function4 = this.mFun;
        if (function4 != null) {
            return function4;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mFun");
        return null;
    }

    protected final void setMFun(Function4<? super BaseViewHolder, ? super DB, ? super Integer, ? super T, Unit> function4) {
        Intrinsics.checkNotNullParameter(function4, "<set-?>");
        this.mFun = function4;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BaseProvider(int i, int i2, Function4<? super BaseViewHolder, ? super DB, ? super Integer, ? super T, Unit> function) {
        this(i, i2);
        Intrinsics.checkNotNullParameter(function, "function");
        setMFun(function);
    }

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public void onViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        DataBindingUtil.bind(viewHolder.itemView);
    }

    @Override // com.chad.library.adapter.base.provider.BaseItemProvider
    public void convert(BaseViewHolder holder, T item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        Intrinsics.checkNotNull(binding);
        getMFun().invoke(holder, binding, Integer.valueOf(holder.getLayoutPosition()), item);
        binding.setVariable(23, item);
        binding.executePendingBindings();
    }
}
