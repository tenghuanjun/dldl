package com.sqwan.msdk.api;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.sq.diagnostic.assistant.other.IBusinessParameters;
import com.sqwan.common.track.SqTrackUtil;
import com.sqwan.common.util.DeviceUtils;
import com.sqwan.common.util.NetWorkUtils;
import com.sqwan.common.util.VersionUtil;
import com.sqwan.msdk.SQwanCore;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class BusinessParameters implements IBusinessParameters {
    private final Context mContext;

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getCity() {
        return "";
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getGwVersion() {
        return "4.6.7";
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public /* synthetic */ String getPhoneBrand() {
        return Build.BRAND;
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public /* synthetic */ String getPhoneModel() {
        return Build.MODEL;
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getProvince() {
        return "";
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public /* synthetic */ String getSystemName() {
        return IMUrl.OS;
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public /* synthetic */ String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    public BusinessParameters(Context context) {
        if (context instanceof Application) {
            this.mContext = context;
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            this.mContext = applicationContext;
        } else {
            this.mContext = context;
        }
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getGid() {
        SQAppConfig appConfig = SQwanCore.getInstance().getAppConfig();
        return appConfig != null ? appConfig.getGameid() : "";
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getPid() {
        SQAppConfig appConfig = SQwanCore.getInstance().getAppConfig();
        return appConfig != null ? appConfig.getPartner() : "";
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getReferer() {
        SQAppConfig appConfig = SQwanCore.getInstance().getAppConfig();
        return appConfig != null ? appConfig.getRefer() : "";
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getDeviceId() {
        return DeviceUtils.getDev(this.mContext);
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getUserId() {
        return SqTrackUtil.getUserid(this.mContext);
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getUserName() {
        return SqTrackUtil.getUsername(this.mContext);
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getSdkVersion() {
        return VersionUtil.getSdkVersion();
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getGameName() {
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(getPackageName(), 0));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getPackageName() {
        return this.mContext.getPackageName();
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getVersionName() {
        try {
            return this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getVersionCode() {
        try {
            return String.valueOf(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getCountry() {
        return DeviceUtils.getCountry();
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getNetworkType() {
        return NetWorkUtils.getNetworkType(this.mContext);
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getIpAddress() {
        return NetWorkUtils.getIpAddress(this.mContext);
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getCarrier() {
        return DeviceUtils.getCarrier(this.mContext);
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getServerId() {
        return MultiSDKUtils.getServerid(this.mContext);
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getServerName() {
        return MultiSDKUtils.getServerName(this.mContext);
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getRoleId() {
        return MultiSDKUtils.getRoleid(this.mContext);
    }

    @Override // com.sq.diagnostic.assistant.other.IBusinessParameters
    public String getRoleName() {
        return MultiSDKUtils.getRolename(this.mContext);
    }
}
