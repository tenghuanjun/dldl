package com.sqwan.liveshow.huya.skin.attr;

import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public abstract class AbstraceSkinAttr implements SkinViewInterface {
    public static final int INVALID_ID = 0;
    protected static final String SYSTEM_ID_PREFIX = "1";
    protected AttributeSet attrs;
    protected int resourceId = 0;
    protected String resourceName = "";
    protected View view;

    public abstract void applySkinWithValid();

    protected abstract Class<? extends View> getSkinViewClass();

    AbstraceSkinAttr(View view, AttributeSet attributeSet) {
        this.view = view;
        this.attrs = attributeSet;
    }

    int checkResourceId(int i) {
        if (Integer.toHexString(i).startsWith("1")) {
            return 0;
        }
        return i;
    }

    int getAttributeValue(AttributeSet attributeSet, int i) {
        if (attributeSet == null) {
            return 0;
        }
        int attributeCount = attributeSet.getAttributeCount();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            if (attributeSet.getAttributeNameResource(i2) == i) {
                String attributeValue = attributeSet.getAttributeValue(i2);
                if (isReferenceTypes(attributeValue)) {
                    return stringConvertInteger(attributeValue);
                }
            }
        }
        TypedArray typedArrayObtainStyledAttributes = null;
        try {
            typedArrayObtainStyledAttributes = this.view.getContext().getTheme().obtainStyledAttributes(attributeSet, new int[]{i}, attributeSet.getAttributeResourceValue(null, "style", 0), 0);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
            i = resourceId != 0 ? resourceId : 0;
            typedArrayObtainStyledAttributes.recycle();
        } catch (Exception e) {
            if (typedArrayObtainStyledAttributes != null) {
                typedArrayObtainStyledAttributes.recycle();
            }
            e.printStackTrace();
        }
        return i;
    }

    int getAttributeValueWithName(AttributeSet attributeSet, String str) {
        int attributeCount = attributeSet.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if (str.equals(attributeSet.getAttributeName(i))) {
                String attributeValue = attributeSet.getAttributeValue(i);
                if (isReferenceTypes(attributeValue)) {
                    return stringConvertInteger(attributeValue);
                }
            }
        }
        return -1;
    }

    private boolean isReferenceTypes(String str) {
        return !TextUtils.isEmpty(str) && (str.startsWith("?") || str.startsWith("@"));
    }

    private int stringConvertInteger(String str) {
        try {
            return Integer.parseInt(str.substring(1, str.length()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override // com.sqwan.liveshow.huya.skin.attr.SkinViewInterface
    public void applySkin() {
        if (this.resourceId == 0 || !getSkinViewClass().isAssignableFrom(this.view.getClass())) {
            return;
        }
        applySkinWithValid();
    }
}
