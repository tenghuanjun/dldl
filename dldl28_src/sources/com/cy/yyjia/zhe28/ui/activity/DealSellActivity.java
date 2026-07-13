package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.ActivityDealSellBinding;
import com.cy.yyjia.zhe28.databinding.ItemDealSellBinding;
import com.cy.yyjia.zhe28.domain.AccountListBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: DealSellActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0019"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/DealSellActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityDealSellBinding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/AccountListBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemDealSellBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "page", "", "getPage", "()I", "setPage", "(I)V", "getData", "", "init", "onClick", "v", "Landroid/view/View;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DealSellActivity extends BaseActivity<ActivityDealSellBinding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;
    private int page;

    public DealSellActivity() {
        super(R.layout.activity_deal_sell, 1);
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<AccountListBean, ItemDealSellBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2
            {
                super(0);
            }

            /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2$1, reason: invalid class name */
            /* JADX INFO: compiled from: DealSellActivity.kt */
            @Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "holder", "Lcom/chad/library/adapter/base/viewholder/BaseDataBindingHolder;", "Lcom/cy/yyjia/zhe28/databinding/ItemDealSellBinding;", ImageSelector.POSITION, "", "item", "Lcom/cy/yyjia/zhe28/domain/AccountListBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
            static final class AnonymousClass1 extends Lambda implements Function3<BaseDataBindingHolder<ItemDealSellBinding>, Integer, AccountListBean, Unit> {
                final /* synthetic */ DealSellActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(DealSellActivity dealSellActivity) {
                    super(3);
                    this.this$0 = dealSellActivity;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemDealSellBinding> baseDataBindingHolder, Integer num, AccountListBean accountListBean) {
                    invoke(baseDataBindingHolder, num.intValue(), accountListBean);
                    return Unit.INSTANCE;
                }

                public final void invoke(BaseDataBindingHolder<ItemDealSellBinding> holder, int i, final AccountListBean accountListBean) {
                    Intrinsics.checkNotNullParameter(holder, "holder");
                    ItemDealSellBinding itemDealSellBinding = (ItemDealSellBinding) holder.getDataBinding();
                    if (itemDealSellBinding != null) {
                        final DealSellActivity dealSellActivity = this.this$0;
                        final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_deal_sell_child, accountListBean != null ? accountListBean.getAccount_list() : null, 
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0023: CONSTRUCTOR (r0v0 'baseAdapter' com.cy.yyjia.zhe28.base.BaseAdapter) = 
                              (wrap:int:SGET  A[WRAPPED] com.cy.yyjia.zhe28.R.layout.item_deal_sell_child int)
                              (wrap:java.util.List<com.cy.yyjia.zhe28.domain.DealBean>:?: TERNARY null = ((r6v0 'accountListBean' com.cy.yyjia.zhe28.domain.AccountListBean) != (null com.cy.yyjia.zhe28.domain.AccountListBean)) ? (wrap:??:0x0013: INVOKE (r6v0 'accountListBean' com.cy.yyjia.zhe28.domain.AccountListBean) VIRTUAL call: com.cy.yyjia.zhe28.domain.AccountListBean.getAccount_list():java.util.List A[MD:():java.util.List<com.cy.yyjia.zhe28.domain.DealBean> (m), WRAPPED] (LINE:32)) : (null java.util.List<com.cy.yyjia.zhe28.domain.DealBean>))
                              (wrap:kotlin.jvm.functions.Function3<com.chad.library.adapter.base.viewholder.BaseDataBindingHolder<com.cy.yyjia.zhe28.databinding.ItemDealSellChildBinding>, java.lang.Integer, com.cy.yyjia.zhe28.domain.DealBean, kotlin.Unit>:0x001b: CONSTRUCTOR (r6v0 'accountListBean' com.cy.yyjia.zhe28.domain.AccountListBean A[DONT_INLINE]) A[MD:(com.cy.yyjia.zhe28.domain.AccountListBean):void (m), WRAPPED] (LINE:30) call: com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2$1$1$childAdapter$1.<init>(com.cy.yyjia.zhe28.domain.AccountListBean):void type: CONSTRUCTOR)
                             A[DECLARE_VAR, MD:(int, java.util.List<T>, kotlin.jvm.functions.Function3<? super com.chad.library.adapter.base.viewholder.BaseDataBindingHolder<DB extends androidx.databinding.ViewDataBinding>, ? super java.lang.Integer, ? super T, kotlin.Unit>):void (m)] (LINE:30) call: com.cy.yyjia.zhe28.base.BaseAdapter.<init>(int, java.util.List, kotlin.jvm.functions.Function3):void type: CONSTRUCTOR in method: com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2.1.invoke(com.chad.library.adapter.base.viewholder.BaseDataBindingHolder<com.cy.yyjia.zhe28.databinding.ItemDealSellBinding>, int, com.cy.yyjia.zhe28.domain.AccountListBean):void, file: classes2.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                            	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:272)
                            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:159)
                            	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:312)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                            	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:845)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:487)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                            	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                            	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                            	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                            	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                            	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                            	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
                            	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:272)
                            	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:159)
                            	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
                            	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
                            	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
                            	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
                            	at jadx.core.ProcessClass.process(ProcessClass.java:88)
                            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:126)
                            	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
                            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
                            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:343)
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2$1$1$childAdapter$1, state: NOT_LOADED
                            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:782)
                            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                            	... 98 more
                            */
                        /*
                            this = this;
                            java.lang.String r5 = "holder"
                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r5)
                            androidx.databinding.ViewDataBinding r4 = r4.getDataBinding()
                            com.cy.yyjia.zhe28.databinding.ItemDealSellBinding r4 = (com.cy.yyjia.zhe28.databinding.ItemDealSellBinding) r4
                            if (r4 == 0) goto L36
                            com.cy.yyjia.zhe28.ui.activity.DealSellActivity r5 = r3.this$0
                            com.cy.yyjia.zhe28.base.BaseAdapter r0 = new com.cy.yyjia.zhe28.base.BaseAdapter
                            if (r6 == 0) goto L18
                            java.util.List r1 = r6.getAccount_list()
                            goto L19
                        L18:
                            r1 = 0
                        L19:
                            com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2$1$1$childAdapter$1 r2 = new com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2$1$1$childAdapter$1
                            r2.<init>(r6)
                            kotlin.jvm.functions.Function3 r2 = (kotlin.jvm.functions.Function3) r2
                            r6 = 2131558699(0x7f0d012b, float:1.8742721E38)
                            r0.<init>(r6, r1, r2)
                            androidx.recyclerview.widget.RecyclerView r4 = r4.rv
                            r6 = r0
                            androidx.recyclerview.widget.RecyclerView$Adapter r6 = (androidx.recyclerview.widget.RecyclerView.Adapter) r6
                            r4.setAdapter(r6)
                            com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2$1$$ExternalSyntheticLambda0 r4 = new com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2$1$$ExternalSyntheticLambda0
                            r4.<init>(r5, r0)
                            r0.setOnItemClickListener(r4)
                        L36:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2.AnonymousClass1.invoke(com.chad.library.adapter.base.viewholder.BaseDataBindingHolder, int, com.cy.yyjia.zhe28.domain.AccountListBean):void");
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    /* JADX WARN: Multi-variable type inference failed */
                    public static final void invoke$lambda$3$lambda$2(final DealSellActivity this$0, BaseAdapter childAdapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                        Intrinsics.checkNotNullParameter(childAdapter, "$childAdapter");
                        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
                        if (Constant.INSTANCE.getNoPin()) {
                            new QuickDialog(this$0.getMContext(), R.layout.dialog_deal_sell_notice1).setOnClickListener(R.id.tv_cancel, 
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0046: INVOKE 
                                  (wrap:com.cy.yyjia.zhe28.base.QuickDialog:0x0040: INVOKE 
                                  (wrap:com.cy.yyjia.zhe28.base.QuickDialog:0x0032: INVOKE 
                                  (wrap:com.cy.yyjia.zhe28.base.QuickDialog:0x0027: CONSTRUCTOR 
                                  (wrap:androidx.fragment.app.FragmentActivity:0x001e: INVOKE (r1v0 'this$0' com.cy.yyjia.zhe28.ui.activity.DealSellActivity) VIRTUAL call: com.cy.yyjia.zhe28.ui.activity.DealSellActivity.getMContext():androidx.fragment.app.FragmentActivity A[MD:():androidx.fragment.app.FragmentActivity (m), WRAPPED] (LINE:25))
                                  (wrap:int:SGET  A[WRAPPED] com.cy.yyjia.zhe28.R.layout.dialog_deal_sell_notice1 int)
                                 A[MD:(android.content.Context, int):void (m), WRAPPED] (LINE:37) call: com.cy.yyjia.zhe28.base.QuickDialog.<init>(android.content.Context, int):void type: CONSTRUCTOR)
                                  (wrap:int:SGET  A[WRAPPED] com.cy.yyjia.zhe28.R.id.tv_cancel int)
                                  (wrap:com.cy.yyjia.zhe28.base.BaseDialog$OnClickListener:0x002c: CONSTRUCTOR  A[MD:():void (m), WRAPPED] (LINE:41) call: com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2$1$$ExternalSyntheticLambda1.<init>():void type: CONSTRUCTOR)
                                 VIRTUAL call: com.cy.yyjia.zhe28.base.QuickDialog.setOnClickListener(int, com.cy.yyjia.zhe28.base.BaseDialog$OnClickListener):com.cy.yyjia.zhe28.base.BaseDialog$Builder A[MD:(int, com.cy.yyjia.zhe28.base.BaseDialog$OnClickListener):B extends com.cy.yyjia.zhe28.base.BaseDialog$Builder (m), WRAPPED] (LINE:41))
                                  (wrap:int:SGET  A[WRAPPED] com.cy.yyjia.zhe28.R.id.tv_go int)
                                  (wrap:com.cy.yyjia.zhe28.base.BaseDialog$OnClickListener:0x003a: CONSTRUCTOR (r1v0 'this$0' com.cy.yyjia.zhe28.ui.activity.DealSellActivity A[DONT_INLINE]) A[MD:(com.cy.yyjia.zhe28.ui.activity.DealSellActivity):void (m), WRAPPED] (LINE:42) call: com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2$1$$ExternalSyntheticLambda2.<init>(com.cy.yyjia.zhe28.ui.activity.DealSellActivity):void type: CONSTRUCTOR)
                                 VIRTUAL call: com.cy.yyjia.zhe28.base.QuickDialog.setOnClickListener(int, com.cy.yyjia.zhe28.base.BaseDialog$OnClickListener):com.cy.yyjia.zhe28.base.BaseDialog$Builder A[MD:(int, com.cy.yyjia.zhe28.base.BaseDialog$OnClickListener):B extends com.cy.yyjia.zhe28.base.BaseDialog$Builder (m), WRAPPED] (LINE:42))
                                 VIRTUAL call: com.cy.yyjia.zhe28.base.QuickDialog.show():com.cy.yyjia.zhe28.base.BaseDialog A[MD:():com.cy.yyjia.zhe28.base.BaseDialog (m)] (LINE:49) in method: com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2.1.invoke$lambda$3$lambda$2(com.cy.yyjia.zhe28.ui.activity.DealSellActivity, com.cy.yyjia.zhe28.base.BaseAdapter, com.chad.library.adapter.base.BaseQuickAdapter, android.view.View, int):void, file: classes2.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:272)
                                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:159)
                                	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:312)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:845)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:487)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:186)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                                	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(ReferencePipeline.java:284)
                                	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:571)
                                	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:560)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:153)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:176)
                                	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:265)
                                	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:632)
                                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:299)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:288)
                                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:272)
                                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:159)
                                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
                                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
                                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
                                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
                                	at jadx.core.ProcessClass.process(ProcessClass.java:88)
                                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:126)
                                	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:405)
                                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:393)
                                	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:343)
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2$1$$ExternalSyntheticLambda1, state: NOT_LOADED
                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:97)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:878)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                                	at jadx.core.codegen.InsnGen.addArgDot(InsnGen.java:97)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:878)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                                	... 98 more
                                */
                            /*
                                java.lang.String r0 = "this$0"
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
                                java.lang.String r0 = "$childAdapter"
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                                java.lang.String r0 = "<anonymous parameter 0>"
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                                java.lang.String r3 = "<anonymous parameter 1>"
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r3)
                                com.cy.yyjia.zhe28.util.Constant r3 = com.cy.yyjia.zhe28.util.Constant.INSTANCE
                                boolean r3 = r3.getNoPin()
                                if (r3 == 0) goto L4a
                                com.cy.yyjia.zhe28.base.QuickDialog r2 = new com.cy.yyjia.zhe28.base.QuickDialog
                                androidx.fragment.app.FragmentActivity r3 = com.cy.yyjia.zhe28.ui.activity.DealSellActivity.access$getMContext(r1)
                                android.content.Context r3 = (android.content.Context) r3
                                r4 = 2131558569(0x7f0d00a9, float:1.8742458E38)
                                r2.<init>(r3, r4)
                                com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2$1$$ExternalSyntheticLambda1 r3 = new com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2$1$$ExternalSyntheticLambda1
                                r3.<init>()
                                r4 = 2131362667(0x7f0a036b, float:1.8345121E38)
                                com.cy.yyjia.zhe28.base.BaseDialog$Builder r2 = r2.setOnClickListener(r4, r3)
                                com.cy.yyjia.zhe28.base.QuickDialog r2 = (com.cy.yyjia.zhe28.base.QuickDialog) r2
                                com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2$1$$ExternalSyntheticLambda2 r3 = new com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2$1$$ExternalSyntheticLambda2
                                r3.<init>(r1)
                                r1 = 2131362715(0x7f0a039b, float:1.8345218E38)
                                com.cy.yyjia.zhe28.base.BaseDialog$Builder r1 = r2.setOnClickListener(r1, r3)
                                com.cy.yyjia.zhe28.base.QuickDialog r1 = (com.cy.yyjia.zhe28.base.QuickDialog) r1
                                r1.show()
                                goto L6d
                            L4a:
                                android.content.Intent r3 = new android.content.Intent
                                androidx.fragment.app.FragmentActivity r4 = com.cy.yyjia.zhe28.ui.activity.DealSellActivity.access$getMContext(r1)
                                android.content.Context r4 = (android.content.Context) r4
                                java.lang.Class<com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity> r0 = com.cy.yyjia.zhe28.ui.activity.DealSellInfoActivity.class
                                r3.<init>(r4, r0)
                                java.lang.Object r2 = r2.getItem(r5)
                                com.cy.yyjia.zhe28.domain.DealBean r2 = (com.cy.yyjia.zhe28.domain.DealBean) r2
                                int r2 = r2.getId()
                                java.lang.String r4 = "id"
                                r3.putExtra(r4, r2)
                                androidx.fragment.app.FragmentActivity r1 = com.cy.yyjia.zhe28.ui.activity.DealSellActivity.access$getMContext(r1)
                                r1.startActivity(r3)
                            L6d:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.ui.activity.DealSellActivity$adapter$2.AnonymousClass1.invoke$lambda$3$lambda$2(com.cy.yyjia.zhe28.ui.activity.DealSellActivity, com.cy.yyjia.zhe28.base.BaseAdapter, com.chad.library.adapter.base.BaseQuickAdapter, android.view.View, int):void");
                        }

                        /* JADX INFO: Access modifiers changed from: private */
                        public static final void invoke$lambda$3$lambda$2$lambda$1(DealSellActivity this$0, BaseDialog baseDialog, View view) {
                            Intrinsics.checkNotNullParameter(this$0, "this$0");
                            this$0.startActivity(new Intent(this$0.getMContext(), (Class<?>) PhoneActivity.class).putExtra("type", 2));
                            baseDialog.dismiss();
                        }
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final BaseAdapter<AccountListBean, ItemDealSellBinding> invoke() {
                        return new BaseAdapter<>(R.layout.item_deal_sell, new AnonymousClass1(this.this$0));
                    }
                });
                this.page = 1;
            }

            public final BaseAdapter<AccountListBean, ItemDealSellBinding> getAdapter() {
                return (BaseAdapter) this.adapter.getValue();
            }

            public final int getPage() {
                return this.page;
            }

            public final void setPage(int i) {
                this.page = i;
            }

            @Override // com.cy.yyjia.zhe28.base.BaseActivity
            public void init() {
                getMBinding().setGame("");
                getMBinding().rv.setAdapter(getAdapter());
                getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellActivity$$ExternalSyntheticLambda0
                    @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
                    public final void onLoadMore() {
                        DealSellActivity.init$lambda$0(this.f$0);
                    }
                });
                getAdapter().setMyEmptyView("deal");
                getMBinding().et.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellActivity$$ExternalSyntheticLambda1
                    @Override // android.widget.TextView.OnEditorActionListener
                    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        return DealSellActivity.init$lambda$1(this.f$0, textView, i, keyEvent);
                    }
                });
                getData();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void init$lambda$0(DealSellActivity this$0) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                this$0.getData();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final boolean init$lambda$1(DealSellActivity this$0, TextView textView, int i, KeyEvent keyEvent) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                if (i != 3) {
                    return false;
                }
                textView.clearFocus();
                this$0.hideSoftKeyboard();
                this$0.page = 1;
                this$0.getAdapter().setNewInstance(null);
                this$0.getData();
                return false;
            }

            public final void getData() {
                Repository repository = Repository.INSTANCE;
                String game = getMBinding().getGame();
                Intrinsics.checkNotNull(game);
                repository.getDealTrumpetList(game, this.page, new Function1<PageBean<AccountListBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellActivity.getData.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(PageBean<AccountListBean> pageBean) {
                        invoke2(pageBean);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(PageBean<AccountListBean> it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        if (DealSellActivity.this.getPage() == 1) {
                            DealSellActivity.this.getAdapter().setNewInstance(it.getList());
                        } else {
                            DealSellActivity.this.getAdapter().addData(it.getList());
                        }
                        DealSellActivity dealSellActivity = DealSellActivity.this;
                        dealSellActivity.setPage(dealSellActivity.getPage() + 1);
                        dealSellActivity.getPage();
                        if (it.getCurrent_page() >= it.getLast_page()) {
                            BaseLoadMoreModule.loadMoreEnd$default(DealSellActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                        } else {
                            DealSellActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                        }
                    }
                }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.DealSellActivity.getData.2
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
                        DealSellActivity dealSellActivity = DealSellActivity.this;
                        String localizedMessage = it.getLocalizedMessage();
                        Intrinsics.checkNotNullExpressionValue(localizedMessage, "getLocalizedMessage(...)");
                        dealSellActivity.log(localizedMessage);
                        DealSellActivity dealSellActivity2 = DealSellActivity.this;
                        String localizedMessage2 = it.getLocalizedMessage();
                        Intrinsics.checkNotNullExpressionValue(localizedMessage2, "getLocalizedMessage(...)");
                        dealSellActivity2.toast(localizedMessage2);
                        DealSellActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
                    }
                });
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intrinsics.checkNotNullParameter(v, "v");
                int id = v.getId();
                if (id == R.id.iv_intro) {
                    Util.openProtocol(this, "交易说明", "transactionInstructions");
                } else {
                    if (id != R.id.iv_record) {
                        return;
                    }
                    startActivity(new Intent(this, (Class<?>) DealRecordActivity.class).putExtra(ImageSelector.POSITION, 2));
                }
            }
        }
