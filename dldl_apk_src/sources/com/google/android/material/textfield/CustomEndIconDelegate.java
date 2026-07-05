package com.google.android.material.textfield;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
class CustomEndIconDelegate extends EndIconDelegate {
    CustomEndIconDelegate(@NonNull TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    void initialize() {
        this.textInputLayout.setEndIconOnClickListener(null);
        this.textInputLayout.setEndIconOnLongClickListener(null);
    }
}
