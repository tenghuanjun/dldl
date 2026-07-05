package com.plugin.core.resources;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import java.io.InputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class MixResources extends ResourcesWrapper {
    private String mPluginPkgName;
    private Resources mPluginResources;

    public MixResources(Resources resources, Context context, String str) {
        super(resources);
        PluginResources pluginResources = new PluginResources(context, str);
        this.mPluginResources = pluginResources.get();
        this.mPluginPkgName = pluginResources.getPkgName();
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public CharSequence getText(int i) throws Resources.NotFoundException {
        try {
            return super.getText(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getText(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public String getString(int i) throws Resources.NotFoundException {
        try {
            return super.getString(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getString(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public String getString(int i, Object... objArr) throws Resources.NotFoundException {
        try {
            return super.getString(i, objArr);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getString(i, objArr);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public float getDimension(int i) throws Resources.NotFoundException {
        try {
            return super.getDimension(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getDimension(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public int getDimensionPixelOffset(int i) throws Resources.NotFoundException {
        try {
            return super.getDimensionPixelOffset(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getDimensionPixelOffset(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public int getDimensionPixelSize(int i) throws Resources.NotFoundException {
        try {
            return super.getDimensionPixelSize(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getDimensionPixelSize(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public Drawable getDrawable(int i) throws Resources.NotFoundException {
        try {
            return super.getDrawable(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getDrawable(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public Drawable getDrawable(int i, Resources.Theme theme) throws Resources.NotFoundException {
        try {
            return super.getDrawable(i, theme);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getDrawable(i, theme);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public Drawable getDrawableForDensity(int i, int i2) throws Resources.NotFoundException {
        try {
            return super.getDrawableForDensity(i, i2);
        } catch (Resources.NotFoundException unused) {
            if (Build.VERSION.SDK_INT >= 15) {
                return this.mPluginResources.getDrawableForDensity(i, i2);
            }
            return null;
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public Drawable getDrawableForDensity(int i, int i2, Resources.Theme theme) {
        try {
            return super.getDrawableForDensity(i, i2, theme);
        } catch (Exception unused) {
            return this.mPluginResources.getDrawableForDensity(i, i2, theme);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public int getColor(int i) throws Resources.NotFoundException {
        try {
            return super.getColor(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getColor(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public int getColor(int i, Resources.Theme theme) throws Resources.NotFoundException {
        try {
            return super.getColor(i, theme);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getColor(i, theme);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public ColorStateList getColorStateList(int i) throws Resources.NotFoundException {
        try {
            return super.getColorStateList(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getColorStateList(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public ColorStateList getColorStateList(int i, Resources.Theme theme) throws Resources.NotFoundException {
        try {
            return super.getColorStateList(i, theme);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getColorStateList(i, theme);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public boolean getBoolean(int i) throws Resources.NotFoundException {
        try {
            return super.getBoolean(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getBoolean(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public XmlResourceParser getLayout(int i) throws Resources.NotFoundException {
        try {
            return super.getLayout(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getLayout(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public String getResourceName(int i) throws Resources.NotFoundException {
        try {
            return super.getResourceName(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getResourceName(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public int getInteger(int i) throws Resources.NotFoundException {
        try {
            return super.getInteger(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getInteger(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public CharSequence getText(int i, CharSequence charSequence) {
        try {
            return super.getText(i, charSequence);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getText(i, charSequence);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public InputStream openRawResource(int i) throws Resources.NotFoundException {
        try {
            return super.openRawResource(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.openRawResource(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public XmlResourceParser getXml(int i) throws Resources.NotFoundException {
        try {
            return super.getXml(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getXml(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public Typeface getFont(int i) throws Resources.NotFoundException {
        try {
            return super.getFont(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getFont(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public void getValue(int i, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        try {
            super.getValue(i, typedValue, z);
        } catch (Resources.NotFoundException unused) {
            this.mPluginResources.getValue(i, typedValue, z);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public Movie getMovie(int i) throws Resources.NotFoundException {
        try {
            return super.getMovie(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getMovie(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public XmlResourceParser getAnimation(int i) throws Resources.NotFoundException {
        try {
            return super.getAnimation(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.getAnimation(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public InputStream openRawResource(int i, TypedValue typedValue) throws Resources.NotFoundException {
        try {
            return super.openRawResource(i, typedValue);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.openRawResource(i, typedValue);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public AssetFileDescriptor openRawResourceFd(int i) throws Resources.NotFoundException {
        try {
            return super.openRawResourceFd(i);
        } catch (Resources.NotFoundException unused) {
            return this.mPluginResources.openRawResourceFd(i);
        }
    }

    @Override // com.plugin.core.resources.ResourcesWrapper, android.content.res.Resources
    public int getIdentifier(String str, String str2, String str3) {
        int identifier = super.getIdentifier(str, str2, str3);
        return identifier <= 0 ? this.mPluginResources.getIdentifier(str, str2, this.mPluginPkgName) : identifier;
    }

    public int getIdentifierFromPlugin(String str, String str2) {
        return this.mPluginResources.getIdentifier(str, str2, this.mPluginPkgName);
    }
}
