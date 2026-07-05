package com.sq.diagnostic.assistant.ui;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import com.sq.diagnostic.assistant.DiagnosticAssistant;
import com.sq.diagnostic.assistant.http.entity.UploadLogRequest;
import com.sq.diagnostic.assistant.log.ILogCallBack;
import com.sq.diagnostic.assistant.log.LogManager;
import com.sq.diagnostic.assistant.log.SQLogUtils;
import com.sq.diagnostic.assistant.log.utils.ExtraUtil;
import com.sq.diagnostic.assistant.other.IBusinessParameters;
import com.sq.diagnostic.assistant.other.LogConfig;
import com.sq.diagnostic.assistant.ui.BaseDialog;
import com.sqwan.bugless.core.Constant;
import java.io.File;
import java.util.Calendar;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class UpdateLogDialog {

    public static final class Builder extends BaseDialog.Builder<Builder> implements BaseDialog.OnDismissListener {
        private static final String TAG = "UpdateLogDialog";
        private DatePickerDialog datePickerDialog;
        private final TextView mContactTextView;
        private final View mCopyLayout;
        private final TextView mDateTextView;
        private final TextView mDeviceIdView;
        private final TextView mFailHintView;
        private final View mGoBackView;
        private final TextView mReasonTextView;
        private final View mSelectDateView;
        private final View mUpdateView;
        private ProgressDialog progressDialog;
        private int selectDay;
        private int selectMonth;
        private int selectYear;

        public Builder(Context context) {
            Object objValueOf;
            Object objValueOf2;
            super(context);
            setWidth(-1);
            setHeight(-1);
            setContentView("sdk_assistant_update_log_dialog");
            this.mGoBackView = findViewById("iv_assistant_update_log_go_back");
            this.mDeviceIdView = (TextView) findViewById("tv_assistant_update_log_device_id");
            this.mCopyLayout = findViewById("ll_assistant_update_log_copy");
            this.mSelectDateView = findViewById("fl_assistant_update_log_select_date");
            this.mDateTextView = (TextView) findViewById("tv_assistant_update_log_date_text");
            this.mContactTextView = (TextView) findViewById("tv_assistant_update_log_contact");
            this.mReasonTextView = (TextView) findViewById("et_assistant_update_log_reason_text");
            this.mFailHintView = (TextView) findViewById("tv_assistant_update_fail_hint");
            this.mUpdateView = findViewById("btn_assistant_update_log_button");
            this.mGoBackView.setOnClickListener(this);
            this.mCopyLayout.setOnClickListener(this);
            this.mSelectDateView.setOnClickListener(this);
            this.mUpdateView.setOnClickListener(this);
            addOnDismissListener(this);
            IBusinessParameters businessParameters = DiagnosticAssistant.getInstance().getBusinessParameters();
            if (businessParameters != null) {
                this.mDeviceIdView.setText(businessParameters.getDeviceId());
            }
            Calendar calendar = Calendar.getInstance();
            this.selectYear = calendar.get(1);
            this.selectMonth = calendar.get(2) + 1;
            this.selectDay = calendar.get(5);
            TextView textView = this.mDateTextView;
            StringBuilder sb = new StringBuilder();
            sb.append(this.selectYear);
            int i = this.selectMonth;
            if (i >= 10) {
                objValueOf = Integer.valueOf(i);
            } else {
                objValueOf = "0" + this.selectMonth;
            }
            sb.append(objValueOf);
            int i2 = this.selectDay;
            if (i2 >= 10) {
                objValueOf2 = Integer.valueOf(i2);
            } else {
                objValueOf2 = "0" + this.selectDay;
            }
            sb.append(objValueOf2);
            textView.setText(sb.toString());
        }

        @Override // com.sq.diagnostic.assistant.ui.BaseDialog.Builder, com.sq.diagnostic.assistant.ui.action.ClickAction, android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == this.mGoBackView) {
                dismiss();
                return;
            }
            if (view == this.mCopyLayout) {
                ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService("clipboard");
                if (clipboardManager != null) {
                    clipboardManager.setPrimaryClip(ClipData.newPlainText(Constant.DEVICE_ID, this.mDeviceIdView.getText().toString()));
                    Toast.makeText(getContext(), getString("sdk_assistant_update_log_dialog_copy_tips"), 0).show();
                    return;
                }
                return;
            }
            if (view == this.mSelectDateView) {
                if (this.datePickerDialog == null) {
                    Calendar calendar = Calendar.getInstance();
                    this.datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() { // from class: com.sq.diagnostic.assistant.ui.-$$Lambda$UpdateLogDialog$Builder$rdokFOmPS9W3oSt0b8yi_X0vZq8
                        @Override // android.app.DatePickerDialog.OnDateSetListener
                        public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                            this.f$0.lambda$onClick$0$UpdateLogDialog$Builder(datePicker, i, i2, i3);
                        }
                    }, calendar.get(1), calendar.get(2), calendar.get(5));
                }
                this.datePickerDialog.show();
                return;
            }
            if (view == this.mUpdateView) {
                if (!UpdateLogDialog.isNetworkConnected(getContext())) {
                    SQLogUtils.i(TAG, "当前没有网络连接，无法上传日志");
                    Toast.makeText(getContext(), getString("sdk_assistant_update_log_dialog_not_network_tips"), 1).show();
                    return;
                }
                this.mFailHintView.setVisibility(8);
                if (TextUtils.isEmpty(this.mDateTextView.getText())) {
                    Toast.makeText(getContext(), getString("sdk_assistant_update_log_dialog_select_date_tips"), 0).show();
                    SQLogUtils.i(TAG, "请选择日志的日期");
                    return;
                }
                if (TextUtils.isEmpty(this.mReasonTextView.getText())) {
                    Toast.makeText(getContext(), getString("sdk_assistant_update_log_dialog_problem_description_tips"), 0).show();
                    SQLogUtils.i(TAG, "请简单描述一下你的问题");
                    return;
                }
                SQLogUtils.i(TAG, "日志上传开始");
                if (this.progressDialog == null) {
                    ProgressDialog progressDialog = new ProgressDialog(getContext());
                    this.progressDialog = progressDialog;
                    progressDialog.setTitle(getString("sdk_assistant_update_log_dialog_progress_title"));
                    this.progressDialog.setMessage(getString("sdk_assistant_update_log_dialog_progress_message"));
                    this.progressDialog.setIndeterminate(true);
                    this.progressDialog.setCancelable(false);
                    SQLogUtils.i(TAG, "ProgressDialog 创建了");
                }
                if (!this.progressDialog.isShowing()) {
                    this.progressDialog.show();
                    SQLogUtils.i(TAG, "ProgressDialog 显示了");
                }
                UploadLogRequest uploadLogRequest = new UploadLogRequest();
                uploadLogRequest.uploadReason = this.mReasonTextView.getText().toString();
                uploadLogRequest.contact = this.mContactTextView.getText().toString();
                uploadLogRequest.logStartDate = this.mDateTextView.getText().toString();
                uploadLogRequest.logEndDate = this.mDateTextView.getText().toString();
                uploadLogRequest.updateLogType = 0;
                SQLogUtils.i(TAG, "用户上传日志的原因：" + uploadLogRequest.uploadReason);
                if (!LogManager.getInstance().uploadLog(uploadLogRequest, new AnonymousClass1(uploadLogRequest))) {
                    this.progressDialog.dismiss();
                    Toast.makeText(getContext(), getString("sdk_assistant_update_log_dialog_update_fail_no_support"), 1).show();
                    SQLogUtils.i(TAG, "上传日志失败，可能是你当前的 SDK 版本太旧，请尝试升级游戏版本后再试");
                }
                SQLogUtils.i(TAG, "日志上传结束");
            }
        }

        public /* synthetic */ void lambda$onClick$0$UpdateLogDialog$Builder(DatePicker datePicker, int i, int i2, int i3) {
            Object objValueOf;
            Object objValueOf2;
            this.selectYear = i;
            this.selectMonth = i2 + 1;
            this.selectDay = i3;
            TextView textView = this.mDateTextView;
            StringBuilder sb = new StringBuilder();
            sb.append(this.selectYear);
            int i4 = this.selectMonth;
            if (i4 >= 10) {
                objValueOf = Integer.valueOf(i4);
            } else {
                objValueOf = "0" + this.selectMonth;
            }
            sb.append(objValueOf);
            int i5 = this.selectDay;
            if (i5 >= 10) {
                objValueOf2 = Integer.valueOf(i5);
            } else {
                objValueOf2 = "0" + this.selectDay;
            }
            sb.append(objValueOf2);
            textView.setText(sb.toString());
            SQLogUtils.i(TAG, "用户选择了日志时间：" + this.mDateTextView.getText().toString());
        }

        /* JADX INFO: renamed from: com.sq.diagnostic.assistant.ui.UpdateLogDialog$Builder$1, reason: invalid class name */
        class AnonymousClass1 implements ILogCallBack<Void> {
            final /* synthetic */ UploadLogRequest val$request;

            AnonymousClass1(UploadLogRequest uploadLogRequest) {
                this.val$request = uploadLogRequest;
            }

            @Override // com.sq.diagnostic.assistant.log.ILogCallBack
            public void onSuccess(Void r2) {
                if (Builder.this.isShowing()) {
                    Builder.this.post(new Runnable() { // from class: com.sq.diagnostic.assistant.ui.-$$Lambda$UpdateLogDialog$Builder$1$4TReoqyWZWf_SoaaLCVesdDnjAw
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onSuccess$0$UpdateLogDialog$Builder$1();
                        }
                    });
                }
            }

            public /* synthetic */ void lambda$onSuccess$0$UpdateLogDialog$Builder$1() {
                Builder.this.progressDialog.dismiss();
                Toast.makeText(Builder.this.getContext(), Builder.this.getString("sdk_assistant_update_log_dialog_update_success"), 1).show();
                SQLogUtils.i(Builder.TAG, "日志上传成功啦");
                DiagnosticAssistant.CallBack callBack = DiagnosticAssistant.getInstance().getCallBack();
                if (callBack != null) {
                    callBack.onUpdateLogSuccess();
                }
                Builder.this.dismiss();
            }

            @Override // com.sq.diagnostic.assistant.log.ILogCallBack
            public void onFailed(final int i, final String str) {
                if (Builder.this.isShowing()) {
                    Builder builder = Builder.this;
                    final UploadLogRequest uploadLogRequest = this.val$request;
                    builder.post(new Runnable() { // from class: com.sq.diagnostic.assistant.ui.-$$Lambda$UpdateLogDialog$Builder$1$dg8aAiH0Qxh14LRHfsCUlDYr-7M
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onFailed$1$UpdateLogDialog$Builder$1(str, i, uploadLogRequest);
                        }
                    });
                }
            }

            public /* synthetic */ void lambda$onFailed$1$UpdateLogDialog$Builder$1(String str, int i, UploadLogRequest uploadLogRequest) {
                LogConfig logConfig;
                Builder.this.progressDialog.dismiss();
                Toast.makeText(Builder.this.getContext(), str, 1).show();
                SQLogUtils.i(Builder.TAG, Builder.this.getString("sdk_assistant_update_log_dialog_update_fail") + str);
                DiagnosticAssistant.CallBack callBack = DiagnosticAssistant.getInstance().getCallBack();
                if (callBack != null) {
                    callBack.onUpdateLogFail(i, str);
                }
                if (i == 10006 && (logConfig = DiagnosticAssistant.getInstance().getLogConfig()) != null) {
                    Builder.this.mFailHintView.setVisibility(0);
                    File file = new File(logConfig.zipLogSavePath, ExtraUtil.genZipFileName(uploadLogRequest.logStartDate, uploadLogRequest.logEndDate));
                    if (file.exists()) {
                        Builder.this.mFailHintView.setText(Builder.this.getString("sdk_assistant_update_log_dialog_update_log_fail_hint_1") + file);
                        SQLogUtils.i(Builder.TAG, "日志上传失败，日志文件路径：" + file);
                        return;
                    }
                    Builder.this.mFailHintView.setText(Builder.this.getString("sdk_assistant_update_log_dialog_update_log_fail_hint_2") + file);
                    SQLogUtils.i(Builder.TAG, "日志上传失败，日志文件不存在：" + file);
                }
            }
        }

        @Override // com.sq.diagnostic.assistant.ui.BaseDialog.OnDismissListener
        public void onDismiss(BaseDialog baseDialog) {
            SQLogUtils.i(TAG, "监听到上传日志界面关闭了");
            baseDialog.removeOnDismissListener(this);
            DatePickerDialog datePickerDialog = this.datePickerDialog;
            if (datePickerDialog != null && datePickerDialog.isShowing()) {
                this.datePickerDialog.dismiss();
            }
            ProgressDialog progressDialog = this.progressDialog;
            if (progressDialog == null || !progressDialog.isShowing()) {
                return;
            }
            this.progressDialog.dismiss();
        }
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.isConnected();
        }
        return false;
    }
}
