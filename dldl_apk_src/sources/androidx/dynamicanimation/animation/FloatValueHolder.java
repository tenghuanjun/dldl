package androidx.dynamicanimation.animation;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
public final class FloatValueHolder {
    private float mValue = 0.0f;

    public FloatValueHolder() {
    }

    public FloatValueHolder(float f) {
        setValue(f);
    }

    public void setValue(float f) {
        this.mValue = f;
    }

    public float getValue() {
        return this.mValue;
    }
}
