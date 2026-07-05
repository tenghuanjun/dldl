package layaair.game.browser;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class ak implements TextWatcher, TextView.OnEditorActionListener {
    public b a;
    private CharSequence b = null;
    private int c = 0;
    private String d = "";

    public ak(b bVar) {
        this.a = null;
        this.a = bVar;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        if (this.a.a == null) {
            return;
        }
        this.c = this.a.a.getSelectionStart();
        this.a.a.getSelectionEnd();
        if (this.b.length() > 0) {
            if (this.a.b) {
                if (!editable.toString().equals(this.a.e)) {
                    this.a.a.setText(this.a.e);
                }
            } else if (this.a.c() != null && this.c > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.b.charAt(this.c - 1));
                if (!this.a.b(sb.toString())) {
                    int i = this.c;
                    editable.delete(i - 1, i);
                    this.a.a.setText(editable);
                }
                this.a.a.setSelection(this.c);
            }
        }
        if (this.a.b || this.d.compareTo(editable.toString()) == 0) {
            return;
        }
        editable.toString();
        ConchJNI.inputChange(0);
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.d = this.a.a.getText().toString();
        this.b = charSequence;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        Log.e("input", ">>>>onEditor" + i);
        b.b();
        if (i != 6) {
            return false;
        }
        ConchJNI.inputChange(i);
        return false;
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
