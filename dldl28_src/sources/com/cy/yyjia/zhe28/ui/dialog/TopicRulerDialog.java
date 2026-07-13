package com.cy.yyjia.zhe28.ui.dialog;

import androidx.fragment.app.FragmentActivity;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDataBindingDialog;
import com.cy.yyjia.zhe28.databinding.DialogTopicRuleBinding;
import com.cy.yyjia.zhe28.domain.TopicDetailBean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TopicRulerDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/TopicRulerDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDataBindingDialog;", "Lcom/cy/yyjia/zhe28/databinding/DialogTopicRuleBinding;", "activity", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "setData", "data", "Lcom/cy/yyjia/zhe28/domain/TopicDetailBean;", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class TopicRulerDialog extends BaseDataBindingDialog<DialogTopicRuleBinding, TopicRulerDialog> {
    public static final int $stable = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TopicRulerDialog(FragmentActivity activity) {
        super(activity, R.layout.dialog_topic_rule);
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public final TopicRulerDialog setData(TopicDetailBean data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ((DialogTopicRuleBinding) this.mBinding).setData(data);
        ((DialogTopicRuleBinding) this.mBinding).wv.loadData(data.getRule(), "", "");
        return this;
    }
}
