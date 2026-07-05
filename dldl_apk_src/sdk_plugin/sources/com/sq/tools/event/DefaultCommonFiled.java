package com.sq.tools.event;

import android.content.Context;
import com.sq.tools.SQIds;
import com.sq.tools.network.request.RequestParam;
import com.sq.tools.utils.DeviceUtils;
import com.sq.tools.utils.HardwareUtils;
import com.sq.tools.utils.SoftwareUtils;
import com.sqwan.common.constants.SqConstants;
import com.sqwan.common.track.SqTrackCommonKey;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DefaultCommonFiled extends EventRequest {

    @RequestParam("aaid")
    public String aaid;

    @RequestParam("adversion")
    public String adVersion;

    @RequestParam("android_id")
    public String androidId;

    @RequestParam("apk_name")
    public String apkName;

    @RequestParam("appid")
    public String appid;

    @RequestParam("phone_brand")
    public String brand;

    @RequestParam("cid")
    public String cid;

    @RequestParam("city")
    public String city;

    @RequestParam("country")
    public String country;

    @RequestParam("country_code")
    public String countryCode;

    @RequestParam("cpu_hardware")
    public String cpu;

    @RequestParam("cpu_is_x86")
    public String cpuArch;

    @RequestParam("cpu_core")
    public String cpuCore;

    @RequestParam("cpu_Ghz")
    public String cpuGhz;

    @RequestParam("densit")
    public String density;

    @RequestParam("dev")
    public String devId;

    @RequestParam(SqTrackCommonKey.last_os_update_ts)
    public String elapsedOsTime;

    @RequestParam("last_open_phone_ts")
    public String elapsedTime;

    @RequestParam("gid")
    public String gid;

    @RequestParam("gwversion")
    public String gwVersion;

    @RequestParam(SqConstants.IDFA)
    public String idfa;

    @RequestParam("imei")
    public String imei;

    @RequestParam("imsi")
    public String imsi;

    @RequestParam("ip")
    public String ip;

    @RequestParam("sd_memory")
    public String memory;

    @RequestParam("phone_model")
    public String model;

    @RequestParam("network_type")
    public String networkType;

    @RequestParam("oaid")
    public String oaid;

    @RequestParam("os")
    public String os;

    @RequestParam("os_version")
    public String osVersion;

    @RequestParam("pgid")
    public String pGid;

    @RequestParam("pid")
    public String pid;

    @RequestParam("province")
    public String province;

    @RequestParam("ram")
    public String ram;

    @RequestParam("refer")
    public String refer;

    @RequestParam("sversion")
    public String sVersion;

    @RequestParam("screen_height")
    public String screenHeight;

    @RequestParam("screen_width")
    public String screenWidth;

    @RequestParam("sim")
    public String simNum;

    @RequestParam("tgid")
    public String tGid;

    @RequestParam("targetVersion")
    public String targetVersion;

    @RequestParam("uuid")
    public String uuid;

    @RequestParam("vaid")
    public String vaid;

    @RequestParam("version_code")
    public String versionCode;

    @RequestParam("version_name")
    public String versionName;

    public DefaultCommonFiled(Context context) {
        super(context);
        this.os = "1";
        this.idfa = "";
        this.province = "";
        this.city = "";
    }

    @Override // com.sq.tools.event.EventRequest
    protected void fresh(Context context) throws Throwable {
        this.appid = com.sq.tool.sqtools.detector.common.SqTrackCommonKey.sdk;
        this.osVersion = SoftwareUtils.getOsVersion();
        this.country = SoftwareUtils.getCountry(context);
        this.countryCode = SoftwareUtils.getCountry(context);
        this.networkType = SoftwareUtils.getNetworkType(context);
        this.ip = SoftwareUtils.getIpAddress(context);
        this.brand = HardwareUtils.getBrand();
        this.model = HardwareUtils.getModel();
        this.density = String.valueOf(HardwareUtils.getDensity(context));
        this.screenHeight = Integer.toString(HardwareUtils.getScreenHeightPx(context));
        this.screenWidth = Integer.toString(HardwareUtils.getScreenWidthPx(context));
        this.ram = Long.toString(HardwareUtils.getTotalRam(context));
        this.memory = Long.toString(HardwareUtils.getExternalTotalMemory());
        this.cpu = HardwareUtils.getCpuModel();
        this.cpuGhz = HardwareUtils.getMaxCpuFreq();
        this.cpuCore = Integer.toString(HardwareUtils.getCpuCores());
        this.cpuArch = HardwareUtils.getCpuArch();
        this.imei = DeviceUtils.getIMEI(context);
        this.imsi = DeviceUtils.getIMSI(context);
        this.devId = DeviceUtils.getDevId(context);
        this.oaid = DeviceUtils.getOAID(context);
        this.vaid = DeviceUtils.getVAID(context);
        this.aaid = DeviceUtils.getAAID(context);
        this.uuid = DeviceUtils.getUUID(context);
        this.androidId = DeviceUtils.getAndroidId(context);
        this.simNum = Integer.toString(HardwareUtils.getCurrentSimNum(context));
        this.sVersion = SoftwareUtils.getSQSdkVersion();
        this.adVersion = SoftwareUtils.getADSdkVersion();
        this.gwVersion = SoftwareUtils.getGWVersion();
        this.elapsedTime = Long.toString(SoftwareUtils.getBootTime());
        this.elapsedOsTime = Long.toString(SoftwareUtils.getBuildTime());
        this.apkName = SoftwareUtils.getApkName(context);
        this.targetVersion = Integer.toString(SoftwareUtils.getTargetSdkVersion(context));
        this.versionCode = Integer.toString(SoftwareUtils.getVersionCode(context));
        this.versionName = SoftwareUtils.getVersionName(context);
        SQIds.ID.init(context);
        this.gid = SQIds.ID.gameId;
        this.refer = SQIds.ID.refer;
        this.pid = SQIds.ID.partner;
        this.cid = SQIds.ID.cid;
        this.pGid = SQIds.ID.pgid;
        this.tGid = SQIds.ID.tgid;
    }
}
