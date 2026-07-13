package com.cy.yyjia.zhe28.ui.dialog;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.base.BaseDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TaskDialog.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\n"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/dialog/TaskDialog;", "Lcom/cy/yyjia/zhe28/base/BaseDialog$Builder;", "activity", "Landroid/content/Context;", "(Landroid/content/Context;)V", "setDetail", "text", "", "setName", "setTitle", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class TaskDialog extends BaseDialog.Builder<TaskDialog> {
    public static final int $stable = 0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TaskDialog(Context activity) {
        super(activity);
        Intrinsics.checkNotNullParameter(activity, "activity");
        setContentView(R.layout.dialog_task_content);
        setOnClickListener(R.id.sure, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.dialog.TaskDialog$$ExternalSyntheticLambda0
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                baseDialog.dismiss();
            }
        });
    }

    public final TaskDialog setTitle(CharSequence text) {
        Intrinsics.checkNotNullParameter(text, "text");
        setText(R.id.title, text);
        return this;
    }

    public final TaskDialog setName(CharSequence text) {
        Intrinsics.checkNotNullParameter(text, "text");
        setText(R.id.task_name, text);
        return this;
    }

    public final TaskDialog setDetail(CharSequence text) {
        SpannableString spannableString = new SpannableString("任务流程：" + ((Object) text));
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0, 5, 33);
        spannableString.setSpan(new StyleSpan(1), 0, 5, 33);
        setText(R.id.task_content, spannableString);
        return this;
    }
}
