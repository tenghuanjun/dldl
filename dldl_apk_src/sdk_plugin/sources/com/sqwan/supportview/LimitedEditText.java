package com.sqwan.supportview;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class LimitedEditText extends EditText {
    private int maxCharacters;
    private TextWatcher textWatcher;
    private TextWatcherAdapter textWatcherEx;

    public LimitedEditText(Context context) {
        this(context, null);
    }

    public LimitedEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setNumberFilter(int i, TextWatcherAdapter textWatcherAdapter) {
        setFilters(new InputFilter[]{new ExceedInputFilter(i).setListener(new ExceedInputFilter.OnTextExceedListener() { // from class: com.sqwan.supportview.LimitedEditText.1
            @Override // com.sqwan.supportview.LimitedEditText.ExceedInputFilter.OnTextExceedListener
            public void onTextExceed() {
                if (LimitedEditText.this.textWatcherEx != null) {
                    LimitedEditText.this.textWatcherEx.maxCharactersCallback();
                }
            }
        })});
        addTextChangedListenerEx(textWatcherAdapter);
        addTextChangedListener(textWatcherAdapter);
    }

    private void addTextChangedListenerEx(TextWatcherAdapter textWatcherAdapter) {
        this.textWatcherEx = textWatcherAdapter;
    }

    public static class TextWatcherAdapter implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void emptyChange(Boolean bool) {
        }

        public void maxCharactersCallback() {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            emptyChange(Boolean.valueOf(TextUtils.isEmpty(editable)));
        }
    }

    public class TextWatcherEx implements TextWatcher {
        private LimitedEditText limitedEditText;
        private String text = null;
        private int beforeCursorPosition = 0;

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public TextWatcherEx(LimitedEditText limitedEditText) {
            this.limitedEditText = limitedEditText;
        }

        public TextWatcherEx() {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.text = charSequence.toString();
            this.beforeCursorPosition = i;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            this.limitedEditText.removeTextChangedListener(this);
            if (this.limitedEditText.getLineCount() > this.limitedEditText.getMaxLines()) {
                this.limitedEditText.setText(this.text);
                this.limitedEditText.setSelection(this.beforeCursorPosition);
            }
            if (editable.toString().length() > this.limitedEditText.maxCharacters) {
                this.limitedEditText.setText(this.text);
                this.limitedEditText.setSelection(this.beforeCursorPosition);
                if (LimitedEditText.this.textWatcherEx != null) {
                    LimitedEditText.this.textWatcherEx.maxCharactersCallback();
                }
            }
            this.limitedEditText.addTextChangedListener(this);
            if (LimitedEditText.this.textWatcherEx != null) {
                LimitedEditText.this.textWatcherEx.afterTextChanged(editable);
            }
        }
    }

    public static class ExceedInputFilter extends InputFilter.LengthFilter {
        private OnTextExceedListener listener;
        private int max;

        public interface OnTextExceedListener {
            void onTextExceed();
        }

        public ExceedInputFilter setListener(OnTextExceedListener onTextExceedListener) {
            this.listener = onTextExceedListener;
            return this;
        }

        public ExceedInputFilter(int i) {
            super(i);
            this.max = i;
        }

        @Override // android.text.InputFilter.LengthFilter, android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            boolean z;
            OnTextExceedListener onTextExceedListener;
            OnTextExceedListener onTextExceedListener2;
            if (this.max - (spanned.length() - (i4 - i3)) > 0 || (onTextExceedListener2 = this.listener) == null) {
                z = false;
            } else {
                z = true;
                onTextExceedListener2.onTextExceed();
            }
            CharSequence charSequenceFilter = super.filter(charSequence, i, i2, spanned, i3, i4);
            if (charSequenceFilter != null) {
                String string = charSequenceFilter.toString();
                if (string.length() < i2 - i && !string.contains("'") && !z && (onTextExceedListener = this.listener) != null) {
                    onTextExceedListener.onTextExceed();
                }
            }
            return charSequenceFilter;
        }
    }
}
