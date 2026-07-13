package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseActivity;
import com.cy.yyjia.zhe28.base.BaseAdapter;
import com.cy.yyjia.zhe28.databinding.ActivityServiceBinding;
import com.cy.yyjia.zhe28.databinding.ItemServiceBinding;
import com.cy.yyjia.zhe28.domain.PageBean;
import com.cy.yyjia.zhe28.domain.ProblemBean;
import com.cy.yyjia.zhe28.domain.ServiceResult;
import com.cy.yyjia.zhe28.domain.ServiceTypeBean;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.donkingliang.imageselector.utils.ImageSelector;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: ServiceActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\rH\u0016R'\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/ServiceActivity;", "Lcom/cy/yyjia/zhe28/base/BaseActivity;", "Lcom/cy/yyjia/zhe28/databinding/ActivityServiceBinding;", "()V", "typeAdapter", "Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "Lcom/cy/yyjia/zhe28/domain/ServiceTypeBean;", "Lcom/cy/yyjia/zhe28/databinding/ItemServiceBinding;", "getTypeAdapter", "()Lcom/cy/yyjia/zhe28/base/BaseAdapter;", "typeAdapter$delegate", "Lkotlin/Lazy;", "getData", "", "getServiceProblem", ImageSelector.POSITION, "", "init", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ServiceActivity extends BaseActivity<ActivityServiceBinding> {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: typeAdapter$delegate, reason: from kotlin metadata */
    private final Lazy typeAdapter;

    public ServiceActivity() {
        super(R.layout.activity_service, 1);
        this.typeAdapter = LazyKt.lazy(new Function0<BaseAdapter<ServiceTypeBean, ItemServiceBinding>>() { // from class: com.cy.yyjia.zhe28.ui.activity.ServiceActivity$typeAdapter$2
            {
                super(0);
            }

            /* JADX INFO: renamed from: com.cy.yyjia.zhe28.ui.activity.ServiceActivity$typeAdapter$2$1, reason: invalid class name */
            /* JADX INFO: compiled from: ServiceActivity.kt */
            @Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "h", "Lcom/chad/library/adapter/base/viewholder/BaseDataBindingHolder;", "Lcom/cy/yyjia/zhe28/databinding/ItemServiceBinding;", "p", "", "t", "Lcom/cy/yyjia/zhe28/domain/ServiceTypeBean;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
            static final class AnonymousClass1 extends Lambda implements Function3<BaseDataBindingHolder<ItemServiceBinding>, Integer, ServiceTypeBean, Unit> {
                final /* synthetic */ ServiceActivity this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(ServiceActivity serviceActivity) {
                    super(3);
                    this.this$0 = serviceActivity;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(BaseDataBindingHolder<ItemServiceBinding> baseDataBindingHolder, Integer num, ServiceTypeBean serviceTypeBean) {
                    invoke(baseDataBindingHolder, num.intValue(), serviceTypeBean);
                    return Unit.INSTANCE;
                }

                public final void invoke(BaseDataBindingHolder<ItemServiceBinding> h, int i, ServiceTypeBean serviceTypeBean) {
                    Intrinsics.checkNotNullParameter(h, "h");
                    ItemServiceBinding itemServiceBinding = (ItemServiceBinding) h.getDataBinding();
                    if (itemServiceBinding != null) {
                        final ServiceActivity serviceActivity = this.this$0;
                        final BaseAdapter baseAdapter = new BaseAdapter(R.layout.item_service_problem, null, 2, null);
                        itemServiceBinding.rv.setAdapter(baseAdapter);
                        baseAdapter.setOnItemClickListener(
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0026: INVOKE 
                              (r6v1 'baseAdapter' com.cy.yyjia.zhe28.base.BaseAdapter)
                              (wrap:com.chad.library.adapter.base.listener.OnItemClickListener:0x0023: CONSTRUCTOR 
                              (r5v2 'serviceActivity' com.cy.yyjia.zhe28.ui.activity.ServiceActivity A[DONT_INLINE])
                              (r6v1 'baseAdapter' com.cy.yyjia.zhe28.base.BaseAdapter A[DONT_INLINE])
                             A[MD:(com.cy.yyjia.zhe28.ui.activity.ServiceActivity, com.cy.yyjia.zhe28.base.BaseAdapter):void (m), WRAPPED] (LINE:28) call: com.cy.yyjia.zhe28.ui.activity.ServiceActivity$typeAdapter$2$1$$ExternalSyntheticLambda0.<init>(com.cy.yyjia.zhe28.ui.activity.ServiceActivity, com.cy.yyjia.zhe28.base.BaseAdapter):void type: CONSTRUCTOR)
                             VIRTUAL call: com.cy.yyjia.zhe28.base.BaseAdapter.setOnItemClickListener(com.chad.library.adapter.base.listener.OnItemClickListener):void A[MD:(com.chad.library.adapter.base.listener.OnItemClickListener):void (m)] (LINE:28) in method: com.cy.yyjia.zhe28.ui.activity.ServiceActivity$typeAdapter$2.1.invoke(com.chad.library.adapter.base.viewholder.BaseDataBindingHolder<com.cy.yyjia.zhe28.databinding.ItemServiceBinding>, int, com.cy.yyjia.zhe28.domain.ServiceTypeBean):void, file: classes2.dex
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
                            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.cy.yyjia.zhe28.ui.activity.ServiceActivity$typeAdapter$2$1$$ExternalSyntheticLambda0, state: NOT_LOADED
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
                            	... 98 more
                            */
                        /*
                            this = this;
                            java.lang.String r5 = "h"
                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r5)
                            androidx.databinding.ViewDataBinding r4 = r4.getDataBinding()
                            com.cy.yyjia.zhe28.databinding.ItemServiceBinding r4 = (com.cy.yyjia.zhe28.databinding.ItemServiceBinding) r4
                            if (r4 == 0) goto L29
                            com.cy.yyjia.zhe28.ui.activity.ServiceActivity r5 = r3.this$0
                            com.cy.yyjia.zhe28.base.BaseAdapter r6 = new com.cy.yyjia.zhe28.base.BaseAdapter
                            r0 = 2131558814(0x7f0d019e, float:1.8742954E38)
                            r1 = 2
                            r2 = 0
                            r6.<init>(r0, r2, r1, r2)
                            androidx.recyclerview.widget.RecyclerView r4 = r4.rv
                            r0 = r6
                            androidx.recyclerview.widget.RecyclerView$Adapter r0 = (androidx.recyclerview.widget.RecyclerView.Adapter) r0
                            r4.setAdapter(r0)
                            com.cy.yyjia.zhe28.ui.activity.ServiceActivity$typeAdapter$2$1$$ExternalSyntheticLambda0 r4 = new com.cy.yyjia.zhe28.ui.activity.ServiceActivity$typeAdapter$2$1$$ExternalSyntheticLambda0
                            r4.<init>(r5, r6)
                            r6.setOnItemClickListener(r4)
                        L29:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.ui.activity.ServiceActivity$typeAdapter$2.AnonymousClass1.invoke(com.chad.library.adapter.base.viewholder.BaseDataBindingHolder, int, com.cy.yyjia.zhe28.domain.ServiceTypeBean):void");
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    /* JADX WARN: Multi-variable type inference failed */
                    public static final void invoke$lambda$1$lambda$0(ServiceActivity this$0, BaseAdapter problemAdapter, BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        Intrinsics.checkNotNullParameter(this$0, "this$0");
                        Intrinsics.checkNotNullParameter(problemAdapter, "$problemAdapter");
                        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
                        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
                        this$0.startActivity(new Intent(this$0.getMContext(), (Class<?>) ServiceDetailActivity.class).putExtra("id", ((ProblemBean) problemAdapter.getItem(i)).getId()));
                    }
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final BaseAdapter<ServiceTypeBean, ItemServiceBinding> invoke() {
                    return new BaseAdapter<>(R.layout.item_service, new AnonymousClass1(this.this$0));
                }
            });
        }

        public static final /* synthetic */ ActivityServiceBinding access$getMBinding(ServiceActivity serviceActivity) {
            return serviceActivity.getMBinding();
        }

        public final BaseAdapter<ServiceTypeBean, ItemServiceBinding> getTypeAdapter() {
            return (BaseAdapter) this.typeAdapter.getValue();
        }

        @Override // com.cy.yyjia.zhe28.base.BaseActivity
        public void init() {
            getMBinding().rv.setAdapter(getTypeAdapter());
            getTypeAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ServiceActivity$$ExternalSyntheticLambda0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    ServiceActivity.init$lambda$1(this.f$0, baseQuickAdapter, view, i);
                }
            });
            getMBinding().tvCopy.setOnClickListener(new View.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.ServiceActivity$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ServiceActivity.init$lambda$2(this.f$0, view);
                }
            });
            getData();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$1(ServiceActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
            Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
            ServiceTypeBean item = this$0.getTypeAdapter().getItem(i);
            item.setSelected(!item.getSelected());
            if (item.getSelected()) {
                this$0.getServiceProblem(i);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void init$lambda$2(ServiceActivity this$0, View view) {
            ServiceResult.ServiceList service_list;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            ServiceActivity serviceActivity = this$0;
            ServiceResult data = this$0.getMBinding().getData();
            Util.copy(serviceActivity, (data == null || (service_list = data.getService_list()) == null) ? null : service_list.getService_name());
        }

        public final void getData() {
            Repository.INSTANCE.getService(new Function1<ServiceResult, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ServiceActivity.getData.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ServiceResult serviceResult) {
                    invoke2(serviceResult);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ServiceResult it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    ServiceActivity.access$getMBinding(ServiceActivity.this).setData(it);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ServiceActivity.getData.2
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
                    ServiceActivity.this.netFail(it);
                }
            });
            Repository.INSTANCE.getServiceType(new Function1<List<ServiceTypeBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ServiceActivity.getData.3
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(List<ServiceTypeBean> list) {
                    invoke2(list);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(List<ServiceTypeBean> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    ServiceActivity.this.getTypeAdapter().setNewInstance(it);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ServiceActivity.getData.4
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
                    ServiceActivity.this.netFail(it);
                }
            });
        }

        public final void getServiceProblem(final int position) {
            Repository.INSTANCE.getServiceProblem(getTypeAdapter().getItem(position).getId(), new Function1<PageBean<ProblemBean>, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ServiceActivity.getServiceProblem.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PageBean<ProblemBean> pageBean) {
                    invoke2(pageBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PageBean<ProblemBean> it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    ServiceActivity.this.getTypeAdapter().getItem(position).setProblem(it.getList());
                    ServiceActivity.this.log("看看长度" + it.getList().size());
                    ServiceActivity serviceActivity = ServiceActivity.this;
                    List<ProblemBean> problem = serviceActivity.getTypeAdapter().getItem(position).getProblem();
                    serviceActivity.log("看看长度" + (problem != null ? Integer.valueOf(problem.size()) : null));
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ServiceActivity.getServiceProblem.2
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
                    ServiceActivity.this.netFail(it);
                }
            });
        }
    }
