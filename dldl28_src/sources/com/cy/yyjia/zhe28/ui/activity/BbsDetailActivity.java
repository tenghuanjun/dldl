package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import androidx.databinding.ViewDataBinding;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.base.FastDialog;
import com.cy.yyjia.zhe28.base.QuickDialog;
import com.cy.yyjia.zhe28.databinding.ActivityBbsDetail2Binding;
import com.cy.yyjia.zhe28.databinding.ItemBbsBinding;
import com.cy.yyjia.zhe28.domain.BbsBean;
import com.cy.yyjia.zhe28.domain.ChampionshipBean;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.Result;
import com.cy.yyjia.zhe28.domain.TypeBean;
import com.cy.yyjia.zhe28.ui.adapter.PicAdapter;
import com.cy.yyjia.zhe28.util.ObjectInterface;
import com.cy.yyjia.zhe28.util.Repository;
import com.mobile.auth.gatewayauth.Constant;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: BbsDetailActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J \u0010%\u001a\u00020&2\u0006\u0010\u0018\u001a\u00020\u00132\u0010\b\u0002\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0(J\u0006\u0010)\u001a\u00020&J\u0006\u0010*\u001a\u00020&J\b\u0010+\u001a\u00020&H\u0016J\u0006\u0010,\u001a\u00020&J\"\u0010-\u001a\u00020&2\u0006\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u00020\u00132\b\u00100\u001a\u0004\u0018\u000101H\u0014J\u0010\u00102\u001a\u00020&2\u0006\u00103\u001a\u000204H\u0016J\u0006\u00105\u001a\u00020&R'\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\u00020\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u0019\u0010\u0015R\u001a\u0010\u001b\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001a\u0010\u001e\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010!R\u001b\u0010\"\u001a\u00020\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\f\u001a\u0004\b#\u0010\u0015¨\u00066"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/BbsDetailActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityBbsDetail2Binding;", "Landroid/view/View$OnClickListener;", "()V", "adapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/BbsBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemBbsBinding;", "getAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "adapter$delegate", "Lkotlin/Lazy;", "child", "", "getChild", "()Z", "child$delegate", "deletePosition", "", "getDeletePosition", "()I", "setDeletePosition", "(I)V", "id", "getId", "id$delegate", "page", "getPage", "setPage", "targetComplete", "getTargetComplete", "setTargetComplete", "(Z)V", "targetId", "getTargetId", "targetId$delegate", "delete", "", "callback", "Lkotlin/Function0;", "getData", "getReply", "init", "initWv", "onActivityResult", Constant.LOGIN_ACTIVITY_REQUEST_CODE, "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "reply", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class BbsDetailActivity extends BaseActivity<ActivityBbsDetail2Binding> implements View.OnClickListener {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: adapter$delegate, reason: from kotlin metadata */
    private final Lazy adapter;

    /* JADX INFO: renamed from: child$delegate, reason: from kotlin metadata */
    private final Lazy child;
    private int deletePosition;

    /* JADX INFO: renamed from: id$delegate, reason: from kotlin metadata */
    private final Lazy id;
    private int page;
    private boolean targetComplete;

    /* JADX INFO: renamed from: targetId$delegate, reason: from kotlin metadata */
    private final Lazy targetId;

    public BbsDetailActivity() {
        super(R.layout.activity_bbs_detail2, 0, 2, null);
        this.id = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$id$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("id", 0));
            }
        });
        this.targetId = LazyKt.lazy(new Function0<Integer>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$targetId$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getIntent().getIntExtra("targetId", 0));
            }
        });
        this.targetComplete = true;
        this.child = LazyKt.lazy(new Function0<Boolean>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$child$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(this.this$0.getIntent().getBooleanExtra("child", false));
            }
        });
        this.adapter = LazyKt.lazy(new Function0<BaseAdapter<BbsBean, ItemBbsBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$adapter$2
            {
                super(0);
            }

            /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$adapter$2$1, reason: invalid class name */
            /* JADX INFO: compiled from: BbsDetailActivity.kt */
            @Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "h", "Lcom/chad/library/adapter/base/viewholder/BaseDataBindingHolder;", "Lcom/cy/yyjia/zhe28/databinding/ItemBbsBinding;", "p", "", "i", "Lcom/cy/yyjia/zhe28/domain/BbsBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
            static final class AnonymousClass1 extends Lambda implements Function3<BaseDataBindingHolder<ItemBbsBinding>, Integer, BbsBean, Unit> {
                final /* synthetic */ BbsDetailActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(BbsDetailActivity bbsDetailActivity) {
                    super(3);
                    this.this$0 = bbsDetailActivity;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemBbsBinding> baseDataBindingHolder, Integer num, BbsBean bbsBean) {
                    invoke(baseDataBindingHolder, num.intValue(), bbsBean);
                    return Unit.INSTANCE;
                }

                public final void invoke(BaseDataBindingHolder<ItemBbsBinding> h, final int i, final BbsBean bbsBean) {
                    Intrinsics.checkNotNullParameter(h, "h");
                    if (bbsBean != null) {
                        bbsBean.initContent();
                    }
                    ViewDataBinding dataBinding = h.getDataBinding();
                    Intrinsics.checkNotNull(dataBinding);
                    ((ItemBbsBinding) dataBinding).setChild(this.this$0.getChild());
                    BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_bbs, BbsDetailActivity$adapter$2$1$a$1.INSTANCE);
                    ViewDataBinding dataBinding2 = h.getDataBinding();
                    Intrinsics.checkNotNull(dataBinding2);
                    ((ItemBbsBinding) dataBinding2).rv.setAdapter(baseAdapter);
                    final BbsDetailActivity bbsDetailActivity = this.this$0;
                    baseAdapter.setOnItemClickListener(
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0040: INVOKE 
                          (r0v3 'baseAdapter' com.cy.yyjia.zhe28.base.BaseAdapter)
                          (wrap:com.chad.library.adapter.base.listener.OnItemClickListener:0x003d: CONSTRUCTOR 
                          (r4v4 'bbsDetailActivity' com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity A[DONT_INLINE])
                          (r5v0 'i' int A[DONT_INLINE])
                          (r6v0 'bbsBean' com.cy.yyjia.zhe28.domain.BbsBean A[DONT_INLINE])
                         A[MD:(com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity, int, com.cy.yyjia.zhe28.domain.BbsBean):void (m), WRAPPED] call: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$adapter$2$1$$ExternalSyntheticLambda0.<init>(com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity, int, com.cy.yyjia.zhe28.domain.BbsBean):void type: CONSTRUCTOR)
                         VIRTUAL call: com.cy.yyjia.zhe28.base.BaseAdapter.setOnItemClickListener(com.chad.library.adapter.base.listener.OnItemClickListener):void A[MD:(com.chad.library.adapter.base.listener.OnItemClickListener):void (m)] in method: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$adapter$2.1.invoke(com.chad.library.adapter.base.viewholder.BaseDataBindingHolder<com.cy.yyjia.zhe28.databinding.ItemBbsBinding>, int, com.cy.yyjia.zhe28.domain.BbsBean):void, file: classes2.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
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
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$adapter$2$1$$ExternalSyntheticLambda0, state: NOT_LOADED
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
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                        	... 90 more
                        */
                    /*
                        this = this;
                        java.lang.String r0 = "h"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                        if (r6 == 0) goto La
                        r6.initContent()
                    La:
                        androidx.databinding.ViewDataBinding r0 = r4.getDataBinding()
                        kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                        com.cy.yyjia.zhe28.databinding.ItemBbsBinding r0 = (com.cy.yyjia.zhe28.databinding.ItemBbsBinding) r0
                        com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity r1 = r3.this$0
                        boolean r1 = r1.getChild()
                        r0.setChild(r1)
                        com.cy.yyjia.zhe28.base.BaseAdapter r0 = new com.cy.yyjia.zhe28.base.BaseAdapter
                        com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$adapter$2$1$a$1 r1 = com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$adapter$2$1$a$1.INSTANCE
                        kotlin.jvm.functions.Function3 r1 = (kotlin.jvm.functions.Function3) r1
                        r2 = 2131558662(0x7f0d0106, float:1.8742646E38)
                        r0.<init>(r2, r1)
                        androidx.databinding.ViewDataBinding r4 = r4.getDataBinding()
                        kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
                        com.cy.yyjia.zhe28.databinding.ItemBbsBinding r4 = (com.cy.yyjia.zhe28.databinding.ItemBbsBinding) r4
                        androidx.recyclerview.widget.RecyclerView r4 = r4.rv
                        r1 = r0
                        androidx.recyclerview.widget.RecyclerView$Adapter r1 = (androidx.recyclerview.widget.RecyclerView.Adapter) r1
                        r4.setAdapter(r1)
                        com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity r4 = r3.this$0
                        com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$adapter$2$1$$ExternalSyntheticLambda0 r1 = new com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$adapter$2$1$$ExternalSyntheticLambda0
                        r1.<init>(r4, r5, r6)
                        r0.setOnItemClickListener(r1)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$adapter$2.AnonymousClass1.invoke(com.chad.library.adapter.base.viewholder.BaseDataBindingHolder, int, com.cy.yyjia.zhe28.domain.BbsBean):void");
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invoke$lambda$1(BbsDetailActivity this$0, int i, BbsBean bbsBean, BaseQuickAdapter baseQuickAdapter, View v, int i2) {
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
                    Intrinsics.checkNotNullParameter(v, "v");
                    this$0.setDeletePosition(i);
                    Intent intent = new Intent(v.getContext(), (Class<?>) BbsDetailActivity.class);
                    Intrinsics.checkNotNull(bbsBean);
                    intent.putExtra("id", bbsBean.getId());
                    intent.putExtra("child", true);
                    this$0.startActivityForResult(intent, 2914);
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseAdapter<BbsBean, ItemBbsBinding> invoke() {
                return new BaseAdapter<>(R.layout.item_bbs, new AnonymousClass1(this.this$0));
            }
        });
        this.page = 1;
    }

    public static final /* synthetic */ ActivityBbsDetail2Binding access$getMBinding(BbsDetailActivity bbsDetailActivity) {
        return bbsDetailActivity.getMBinding();
    }

    public final int getId() {
        return ((Number) this.id.getValue()).intValue();
    }

    public final int getTargetId() {
        return ((Number) this.targetId.getValue()).intValue();
    }

    public final boolean getTargetComplete() {
        return this.targetComplete;
    }

    public final void setTargetComplete(boolean z) {
        this.targetComplete = z;
    }

    public final boolean getChild() {
        return ((Boolean) this.child.getValue()).booleanValue();
    }

    public final BaseAdapter<BbsBean, ItemBbsBinding> getAdapter() {
        return (BaseAdapter) this.adapter.getValue();
    }

    public final int getPage() {
        return this.page;
    }

    public final void setPage(int i) {
        this.page = i;
    }

    public final int getDeletePosition() {
        return this.deletePosition;
    }

    public final void setDeletePosition(int i) {
        this.deletePosition = i;
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity
    public void init() {
        getMBinding().navigation.setMoreClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BbsDetailActivity.init$lambda$0(this.f$0, view);
            }
        });
        getMBinding().rvPic.setAdapter(new PicAdapter());
        getMBinding().rv.setAdapter(getAdapter());
        getAdapter().getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public final void onLoadMore() {
                BbsDetailActivity.init$lambda$1(this.f$0);
            }
        });
        BaseAdapter.setMyEmptyView$default(getAdapter(), null, 1, null);
        getAdapter().addChildClickViewIds(R.id.iv_delete);
        getAdapter().setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                BbsDetailActivity.init$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$$ExternalSyntheticLambda6
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                BbsDetailActivity.init$lambda$4(this.f$0, baseQuickAdapter, view, i);
            }
        });
        initWv();
        getData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$0(final BbsDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BbsBean data = this$0.getMBinding().getData();
        Intrinsics.checkNotNull(data);
        this$0.delete(data.getId(), new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$init$1$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                this.this$0.setResult(2914);
                this.this$0.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$1(BbsDetailActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getReply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$2(final BbsDetailActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "view");
        this$0.delete(this$0.getAdapter().getItem(i).getId(), new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$init$3$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                this.this$0.getAdapter().removeAt(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$4(final BbsDetailActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (this$0.getChild()) {
            return;
        }
        new QuickDialog(this$0, R.layout.dialog_bbs_reply).setData(this$0.getAdapter().getItem(i)).setOnClickListener(R.id.btn, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$$ExternalSyntheticLambda2
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view2) {
                BbsDetailActivity.init$lambda$4$lambda$3(this.f$0, i, baseDialog, view2);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void init$lambda$4$lambda$3(final BbsDetailActivity this$0, int i, final BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditText editText = (EditText) baseDialog.findViewById(R.id.et);
        Intrinsics.checkNotNull(editText);
        String string = editText.getText().toString();
        if (TextUtils.isEmpty(string)) {
            this$0.toast("请先输入回复内容");
        } else {
            this$0.hideSoftKeyboard();
            Repository.INSTANCE.replyBbs(this$0.getAdapter().getItem(i).getId(), string, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$init$4$1$1
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
                    baseDialog.dismiss();
                    this.this$0.setPage(1);
                    this.this$0.getReply();
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$init$4$1$2
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

    public final void initWv() {
        WebView wv = getMBinding().wv;
        Intrinsics.checkNotNullExpressionValue(wv, "wv");
        ObjectInterface objectInterface = new ObjectInterface(this, wv);
        getMBinding().wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getMBinding().wv.getSettings().setJavaScriptEnabled(true);
        getMBinding().wv.getSettings().setLoadsImagesAutomatically(true);
        getMBinding().wv.getSettings().setCacheMode(2);
        getMBinding().wv.getSettings().setDomStorageEnabled(true);
        getMBinding().wv.getSettings().setUserAgentString(getMBinding().wv.getSettings().getUserAgentString() + "/androidbox;");
        getMBinding().wv.addJavascriptInterface(objectInterface, com.cy.yyjia.zhe28.util.Constant.INSTANCE.getJS_NAME());
    }

    public final void getData() {
        Repository.INSTANCE.getBbsDetail(getId(), new Function1<BbsBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity.getData.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BbsBean bbsBean) {
                invoke2(bbsBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BbsBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                it.initContent();
                BbsDetailActivity.access$getMBinding(BbsDetailActivity.this).setData(it);
                BbsDetailActivity.this.getReply();
                if (it.getActivity() != null) {
                    Repository repository = Repository.INSTANCE;
                    int id = it.getActivity().getId();
                    final BbsDetailActivity bbsDetailActivity = BbsDetailActivity.this;
                    Function1<ChampionshipBean, Unit> function1 = new Function1<ChampionshipBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity.getData.1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(ChampionshipBean championshipBean) {
                            invoke2(championshipBean);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ChampionshipBean activity) {
                            Intrinsics.checkNotNullParameter(activity, "activity");
                            BbsDetailActivity.access$getMBinding(bbsDetailActivity).setActivity(activity);
                            BbsDetailActivity.access$getMBinding(bbsDetailActivity).wv.loadData(activity.getActivity_info().getDescription(), "", "");
                        }
                    };
                    final BbsDetailActivity bbsDetailActivity2 = BbsDetailActivity.this;
                    repository.getBbsActivity(id, function1, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity.getData.1.2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                            invoke2(exc);
                            return Unit.INSTANCE;
                        }

                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Exception it2) {
                            Intrinsics.checkNotNullParameter(it2, "it");
                            bbsDetailActivity2.netFail(it2);
                        }
                    });
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity.getData.2
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
                BbsDetailActivity.this.netFail(it);
            }
        });
    }

    public final void getReply() {
        Repository.INSTANCE.getBbsReply(getId(), this.page, new Function1<PageBean<BbsBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity.getReply.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PageBean<BbsBean> pageBean) {
                invoke2(pageBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PageBean<BbsBean> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (BbsDetailActivity.this.getPage() == 1) {
                    if (BbsDetailActivity.this.getTargetId() != 0) {
                        BbsDetailActivity.this.setTargetComplete(false);
                    }
                    BbsDetailActivity.this.getAdapter().setNewInstance(it.getList());
                } else {
                    BbsDetailActivity.this.getAdapter().addData(it.getList());
                }
                BbsDetailActivity bbsDetailActivity = BbsDetailActivity.this;
                bbsDetailActivity.setPage(bbsDetailActivity.getPage() + 1);
                bbsDetailActivity.getPage();
                if (it.getCurrent_page() >= it.getLast_page()) {
                    BaseLoadMoreModule.loadMoreEnd$default(BbsDetailActivity.this.getAdapter().getLoadMoreModule(), false, 1, null);
                } else {
                    BbsDetailActivity.this.getAdapter().getLoadMoreModule().loadMoreComplete();
                }
                if (BbsDetailActivity.this.getTargetId() != 0) {
                    int size = BbsDetailActivity.this.getAdapter().getData().size() - 1;
                    while (true) {
                        if (-1 >= size) {
                            size = -1;
                            break;
                        }
                        BbsDetailActivity.this.getAdapter().getItem(size).setTarget(BbsDetailActivity.this.getTargetId() == BbsDetailActivity.this.getAdapter().getItem(size).getId());
                        if (BbsDetailActivity.this.getAdapter().getItem(size).getTarget()) {
                            BbsDetailActivity.this.setTargetComplete(true);
                            break;
                        }
                        size--;
                    }
                    if (size != -1) {
                        BbsDetailActivity.access$getMBinding(BbsDetailActivity.this).rv.scrollToPosition(size);
                    } else {
                        if (BbsDetailActivity.this.getTargetComplete()) {
                            return;
                        }
                        BbsDetailActivity.this.getReply();
                    }
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity.getReply.2
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
                BbsDetailActivity.this.getAdapter().getLoadMoreModule().loadMoreFail();
                BbsDetailActivity.this.netFail(it);
            }
        }, getMBinding().tvSort.isSelected());
    }

    public final void reply() {
        if (TextUtils.isEmpty(getMBinding().getText())) {
            toast("请先输入回复内容");
            return;
        }
        hideSoftKeyboard();
        Repository repository = Repository.INSTANCE;
        int id = getId();
        String text = getMBinding().getText();
        Intrinsics.checkNotNull(text);
        repository.replyBbs(id, text, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity.reply.1
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
                BbsDetailActivity.this.toast(it.getMsg());
                BbsDetailActivity.access$getMBinding(BbsDetailActivity.this).setText("");
                BbsDetailActivity.this.setPage(1);
                BbsDetailActivity.this.getReply();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity.reply.2
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
                BbsDetailActivity.this.netFail(it);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void delete$default(BbsDetailActivity bbsDetailActivity, int i, Function0 function0, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function0 = new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity.delete.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        bbsDetailActivity.delete(i, function0);
    }

    public final void delete(final int id, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        new FastDialog(this).setContentView(R.layout.dialog_bbs_delete).setOnClickListener(R.id.tv1, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$$ExternalSyntheticLambda0
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                BbsDetailActivity.delete$lambda$5(id, this, callback, baseDialog, view);
            }
        }).setOnClickListener(R.id.tv2, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$$ExternalSyntheticLambda1
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                baseDialog.dismiss();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void delete$lambda$5(int i, final BbsDetailActivity this$0, final Function0 callback, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Repository.INSTANCE.deleteBbs(i, new Function1<Result, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$delete$2$1
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
                callback.invoke();
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.BbsDetailActivity$delete$2$2
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
        baseDialog.dismiss();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2914) {
            getAdapter().removeAt(this.deletePosition);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Intrinsics.checkNotNullParameter(v, "v");
        switch (v.getId()) {
            case R.id.btn /* 2131361939 */:
                reply();
                break;
            case R.id.iv_activity /* 2131362159 */:
                Intent intent = new Intent(this, (Class<?>) ChampionshipActivity.class);
                BbsBean data = getMBinding().getData();
                Intrinsics.checkNotNull(data);
                TypeBean activity = data.getActivity();
                Intrinsics.checkNotNull(activity);
                intent.putExtra("id", activity.getId());
                startActivity(intent);
                break;
            case R.id.tv_praise /* 2131362748 */:
                BbsBean data2 = getMBinding().getData();
                Intrinsics.checkNotNull(data2);
                data2.praise(this);
                break;
            case R.id.tv_sort /* 2131362776 */:
                if (!checkClick(1)) {
                    v.setSelected(!v.isSelected());
                    this.page = 1;
                    getAdapter().setNewInstance(null);
                    getReply();
                    break;
                }
                break;
        }
    }
}
