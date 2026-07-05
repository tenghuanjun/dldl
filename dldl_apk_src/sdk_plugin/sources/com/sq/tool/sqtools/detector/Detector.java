package com.sq.tool.sqtools.detector;

import android.content.Context;
import android.os.Build;
import com.sq.tool.sqtools.detector.callback.DeviceCollectCallback;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sq.tool.sqtools.detector.inter.IPrivateAgreement;
import com.sq.tool.sqtools.detector.sp.SPConfigInfo;
import com.sq.tool.sqtools.net.DHttpClient;
import com.sq.tool.sqtools.utils.DebugUtils;
import com.sq.tool.sqtools.utils.DeviceUtils;
import com.sq.tool.sqtools.utils.EmulatorUtils;
import com.sq.tool.sqtools.utils.NetWorkUtils;
import com.sq.tool.sqtools.utils.RootUtils;
import com.sq.tool.sqtools.utils.VirtualUtils;
import com.sq.tool.sqtools.utils.XposedUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Detector {
    public static DHttpClient mDHttpClient;
    private static volatile Detector sInstance;
    private DeviceCollectCallback mCollectCallback;
    private Context mContext;
    private Map<String, String> mDeviceCollectMap;
    private IPrivateAgreement mIPrivateAgreement;
    private SPConfigInfo mSPConfigInfo;
    private Map<String, String> mSPConfigInfoMap;

    private Detector() {
    }

    public static Detector getInstance() {
        if (sInstance == null) {
            synchronized (Detector.class) {
                if (sInstance == null) {
                    sInstance = new Detector();
                }
            }
        }
        return sInstance;
    }

    public static Detector build() {
        return getInstance();
    }

    public Detector setCollectCallback(DeviceCollectCallback deviceCollectCallback) {
        this.mCollectCallback = deviceCollectCallback;
        return this;
    }

    public boolean isPrivateAgreement() {
        IPrivateAgreement iPrivateAgreement = this.mIPrivateAgreement;
        if (iPrivateAgreement == null) {
            return false;
        }
        return iPrivateAgreement.getPrivateAgreement();
    }

    public Detector setPrivateAgreement(IPrivateAgreement iPrivateAgreement) {
        this.mIPrivateAgreement = iPrivateAgreement;
        return this;
    }

    public Detector setHttpClient(DHttpClient dHttpClient) {
        mDHttpClient = dHttpClient;
        return this;
    }

    public Detector setSPConfigInfo(SPConfigInfo sPConfigInfo) {
        this.mSPConfigInfo = sPConfigInfo;
        return this;
    }

    public void init(Context context) {
        this.mContext = context;
    }

    public void doDeviceCollect() {
        if (isPrivateAgreement()) {
            if (this.mDeviceCollectMap == null) {
                this.mDeviceCollectMap = new HashMap();
            }
            if (this.mDeviceCollectMap.size() != 0) {
                this.mCollectCallback.onSuccess(this.mDeviceCollectMap);
                return;
            }
            SPConfigInfo sPConfigInfo = this.mSPConfigInfo;
            if (sPConfigInfo != null) {
                this.mSPConfigInfoMap = sPConfigInfo.getMap();
            }
            if (this.mSPConfigInfoMap == null) {
                this.mSPConfigInfoMap = new HashMap();
            }
            try {
                this.mDeviceCollectMap.put("os", DetectorHelper.getMapInfo("os", this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.os_desc, DetectorHelper.getMapInfo(SqTrackCommonKey.os_desc, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put("over", DetectorHelper.getMapInfo("over", this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put("os_version", DetectorHelper.getMapInfo("os_version", this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put("country_code", DetectorHelper.getMapInfo("country_code", this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put("cpu_core", DetectorHelper.getMapInfo("cpu_core", this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put("apk_name", DetectorHelper.getMapInfo("apk_name", this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put("game_name", DetectorHelper.getMapInfo("game_name", this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put("install_time", DetectorHelper.getMapInfo("install_time", this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put("last_update_time", DetectorHelper.getMapInfo("last_update_time", this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put("version", DetectorHelper.getMapInfo("version", this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put("version_name", DetectorHelper.getMapInfo("version_name", this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.boot_time, DetectorHelper.getMapInfo(SqTrackCommonKey.boot_time, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.screen_brightness, DetectorHelper.getMapInfo(SqTrackCommonKey.screen_brightness, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.cpu_count, DetectorHelper.getMapInfo(SqTrackCommonKey.cpu_count, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.is_first_launch, DetectorHelper.getMapInfo(SqTrackCommonKey.is_first_launch, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.input_method_list, DetectorHelper.getMapInfo(SqTrackCommonKey.input_method_list, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.ram_total, DetectorHelper.getMapInfo(SqTrackCommonKey.ram_total, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.display_metrics, DetectorHelper.getMapInfo(SqTrackCommonKey.display_metrics, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.sensor_list, DetectorHelper.getMapInfo(SqTrackCommonKey.sensor_list, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.enable_adb, DetectorHelper.getMapInfo(SqTrackCommonKey.enable_adb, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put("country", DetectorHelper.getMapInfo("country", this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.is_wifi_proxy, DetectorHelper.getMapInfo(SqTrackCommonKey.is_wifi_proxy, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put("phone_brand", DeviceUtils.getBrand());
                this.mDeviceCollectMap.put("phone_model", DeviceUtils.getModel());
                this.mDeviceCollectMap.put("cpu_abi", DeviceUtils.getCpuArch());
                this.mDeviceCollectMap.put(SqTrackCommonKey.tags, Build.TAGS);
                this.mDeviceCollectMap.put(SqTrackCommonKey.version_codes_base, String.valueOf(1));
                this.mDeviceCollectMap.put(SqTrackCommonKey.version_release, Build.VERSION.RELEASE);
                this.mDeviceCollectMap.put("device", Build.DEVICE);
                this.mDeviceCollectMap.put(SqTrackCommonKey.display, Build.DISPLAY);
                this.mDeviceCollectMap.put(SqTrackCommonKey.board, Build.BOARD);
                this.mDeviceCollectMap.put(SqTrackCommonKey.fingerprint, Build.FINGERPRINT);
                this.mDeviceCollectMap.put(SqTrackCommonKey.id, Build.ID);
                this.mDeviceCollectMap.put(SqTrackCommonKey.manufacturer, Build.MANUFACTURER);
                this.mDeviceCollectMap.put(SqTrackCommonKey.user, Build.USER);
                this.mDeviceCollectMap.put(SqTrackCommonKey.bootloader, Build.BOOTLOADER);
                this.mDeviceCollectMap.put("host", Build.HOST);
                this.mDeviceCollectMap.put(SqTrackCommonKey.hardware, Build.HARDWARE);
                this.mDeviceCollectMap.put(SqTrackCommonKey.incremental, Build.VERSION.INCREMENTAL);
                this.mDeviceCollectMap.put(SqTrackCommonKey.codename, Build.VERSION.CODENAME);
                this.mDeviceCollectMap.put(SqTrackCommonKey.sdk, String.valueOf(Build.VERSION.SDK_INT));
                this.mDeviceCollectMap.put(SqTrackCommonKey.battery_level, DetectorHelper.getMapInfo(SqTrackCommonKey.battery_level, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.battery_status, DetectorHelper.getMapInfo(SqTrackCommonKey.battery_status, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.isRoot, RootUtils.isSUExistFiles() + "");
                this.mDeviceCollectMap.put(SqTrackCommonKey.rootFile, RootUtils.getSuFiles());
                this.mDeviceCollectMap.put(SqTrackCommonKey.is_ro_debug_prop, RootUtils.getRoDebugProp());
                this.mDeviceCollectMap.put(SqTrackCommonKey.is_ro_secure_prop, RootUtils.getRoSecureProp());
                this.mDeviceCollectMap.put(SqTrackCommonKey.my_uid, String.valueOf(VirtualUtils.checkByUid(this.mContext)));
                this.mDeviceCollectMap.put(SqTrackCommonKey.private_file_path, VirtualUtils.checkByPrivateFilePath(this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.multi_by_maps, VirtualUtils.checkByMultiByMaps(this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.e_qemu, EmulatorUtils.getQemu());
                this.mDeviceCollectMap.put(SqTrackCommonKey.e_cpu_abi, EmulatorUtils.getEmulatorFromCpuAbi());
                this.mDeviceCollectMap.put(SqTrackCommonKey.e_hardware, EmulatorUtils.checkByHardware());
                this.mDeviceCollectMap.put(SqTrackCommonKey.e_flavor, EmulatorUtils.checkFlavor());
                this.mDeviceCollectMap.put(SqTrackCommonKey.e_model, EmulatorUtils.checkModel());
                this.mDeviceCollectMap.put(SqTrackCommonKey.e_manufacturer, EmulatorUtils.checkManufacturer());
                this.mDeviceCollectMap.put(SqTrackCommonKey.e_board, EmulatorUtils.checkBoard());
                this.mDeviceCollectMap.put(SqTrackCommonKey.e_platform, EmulatorUtils.checkPlatform());
                this.mDeviceCollectMap.put(SqTrackCommonKey.e_baseband, EmulatorUtils.checkFeaturesByBaseBand());
                this.mDeviceCollectMap.put(SqTrackCommonKey.e_sensor_number, String.valueOf(EmulatorUtils.getSensorNumber(this.mContext)));
                this.mDeviceCollectMap.put(SqTrackCommonKey.e_light_sensor, String.valueOf(EmulatorUtils.hasLightSensor(this.mContext)));
                this.mDeviceCollectMap.put(SqTrackCommonKey.tracer_pid, DebugUtils.checkTracerPid());
                this.mDeviceCollectMap.put(SqTrackCommonKey.xposed_bridge, String.valueOf(XposedUtils.checkXposedBridge()));
                this.mDeviceCollectMap.put(SqTrackCommonKey.xposed_helper, String.valueOf(XposedUtils.checkXposedHelpers()));
                this.mDeviceCollectMap.put("ssid", DetectorHelper.getMapInfo("ssid", this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.bssid, DetectorHelper.getMapInfo(SqTrackCommonKey.bssid, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.ipv6, NetWorkUtils.getIpv6());
                this.mDeviceCollectMap.put(SqTrackCommonKey.kernel_version, DeviceUtils.getKernelVersion());
                this.mDeviceCollectMap.put(SqTrackCommonKey.d_oaid, DetectorHelper.getMapInfo(SqTrackCommonKey.d_oaid, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.d_android_id, DetectorHelper.getMapInfo(SqTrackCommonKey.d_android_id, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.d_imei, DetectorHelper.getMapInfo(SqTrackCommonKey.d_imei, this.mSPConfigInfoMap, this.mContext));
                this.mDeviceCollectMap.put(SqTrackCommonKey.d_mac, DetectorHelper.getMapInfo(SqTrackCommonKey.d_mac, this.mSPConfigInfoMap, this.mContext));
            } catch (Exception e) {
                e.printStackTrace();
            }
            DevicesFingerprint.getFingerprintToken(this.mContext, this.mDeviceCollectMap);
            DeviceCollectCallback deviceCollectCallback = this.mCollectCallback;
            if (deviceCollectCallback != null) {
                deviceCollectCallback.onSuccess(this.mDeviceCollectMap);
            }
        }
    }

    private void doDemoDeviceCollect() {
        this.mDeviceCollectMap.put("oaid", DeviceUtils.getOaid(this.mContext));
        this.mDeviceCollectMap.put("android_id", DeviceUtils.getAndroidId(this.mContext));
        this.mDeviceCollectMap.put("imei", DeviceUtils.getIMEI(this.mContext));
        this.mDeviceCollectMap.put("mac", DeviceUtils.getMac(this.mContext));
    }

    private void doDynamicDeviceCollect() {
        try {
            this.mDeviceCollectMap.put("target_version", DeviceUtils.getTargetVersion(this.mContext));
            this.mDeviceCollectMap.put("sd_memory", String.valueOf(DeviceUtils.getTotalExternalMemorySize()));
            this.mDeviceCollectMap.put("ram", DeviceUtils.getTotalRam());
            this.mDeviceCollectMap.put("cpu_Ghz", DeviceUtils.getMaxCpuFreq());
            this.mDeviceCollectMap.put("cpu_hardware", DeviceUtils.getCpuHardware());
            this.mDeviceCollectMap.put("cpu_is_x86", DeviceUtils.getCpuName());
            this.mDeviceCollectMap.put("sim", DeviceUtils.getSIM(this.mContext));
            this.mDeviceCollectMap.put("isSimulator", String.valueOf(DeviceUtils.isEmulator(this.mContext)));
            this.mDeviceCollectMap.put("cpu_info", DeviceUtils.readCpuInfo());
            this.mDeviceCollectMap.put("oaid", DeviceUtils.getOaid(this.mContext));
            this.mDeviceCollectMap.put("android_id", DeviceUtils.getAndroidId(this.mContext));
            this.mDeviceCollectMap.put("network_type", DeviceUtils.getNetWorkType(this.mContext));
            this.mDeviceCollectMap.put("carrier", DeviceUtils.getCarrier(this.mContext));
            this.mDeviceCollectMap.put("ip", DeviceUtils.getIpAddress(this.mContext));
            this.mDeviceCollectMap.put("event_time", String.valueOf(System.currentTimeMillis()));
            if (this.mSPConfigInfoMap != null) {
                this.mDeviceCollectMap.put("dev", this.mSPConfigInfoMap.get("dev"));
                this.mDeviceCollectMap.put("gid", this.mSPConfigInfoMap.get("gid"));
                this.mDeviceCollectMap.put("refer", this.mSPConfigInfoMap.get("refer"));
                this.mDeviceCollectMap.put("cid", this.mSPConfigInfoMap.get("cid"));
                this.mDeviceCollectMap.put("pid", this.mSPConfigInfoMap.get("pid"));
                this.mDeviceCollectMap.put("sversion", this.mSPConfigInfoMap.get("sversion"));
                this.mDeviceCollectMap.put("gwversion", this.mSPConfigInfoMap.get("gwversion"));
                this.mDeviceCollectMap.put("original_sversion", this.mSPConfigInfoMap.get("original_sversion"));
                this.mDeviceCollectMap.put("plugin_version", this.mSPConfigInfoMap.get("plugin_version"));
                this.mDeviceCollectMap.put("request_liveid", this.mSPConfigInfoMap.get("request_liveid"));
                this.mDeviceCollectMap.put("request_id", this.mSPConfigInfoMap.get("request_id"));
                this.mDeviceCollectMap.put("scut", this.mSPConfigInfoMap.get("scut"));
                this.mDeviceCollectMap.put("uid", this.mSPConfigInfoMap.get("uid"));
                this.mDeviceCollectMap.put("uname", this.mSPConfigInfoMap.get("uname"));
                this.mDeviceCollectMap.put("role_id", this.mSPConfigInfoMap.get("role_id"));
                this.mDeviceCollectMap.put("role_name", this.mSPConfigInfoMap.get("role_name"));
                this.mDeviceCollectMap.put("role_level", this.mSPConfigInfoMap.get("role_level"));
                this.mDeviceCollectMap.put("vip_level", this.mSPConfigInfoMap.get("vip_level"));
                this.mDeviceCollectMap.put("server_id", this.mSPConfigInfoMap.get("server_id"));
                this.mDeviceCollectMap.put("server_name", this.mSPConfigInfoMap.get("server_name"));
            }
            this.mDeviceCollectMap.put("isProxy", String.valueOf(NetWorkUtils.isWifiProxy()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
