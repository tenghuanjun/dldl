package com.cy.yyjia.zhe28.domain;

import com.bytedance.framwork.core.sdklib.MonitorCommonConstants;
import com.google.gson.annotations.SerializedName;
import com.mobile.auth.gatewayauth.Constant;
import com.tencent.open.SocialConstants;
import com.volcengine.common.contant.CommonConstants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: YunIndexBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001:\u0003\u0017\u0018\u0019B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001a"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/YunIndexBean;", "", "index", "Lcom/cy/yyjia/zhe28/domain/YunIndexBean$Default;", "deviceList", "", "Lcom/cy/yyjia/zhe28/domain/YunIndexBean$Device;", "(Lcom/cy/yyjia/zhe28/domain/YunIndexBean$Default;Ljava/util/List;)V", "getDeviceList", "()Ljava/util/List;", "getIndex", "()Lcom/cy/yyjia/zhe28/domain/YunIndexBean$Default;", "component1", "component2", "copy", "equals", "", "other", "getSelectedDevice", "hashCode", "", "toString", "", "Block", "Default", "Device", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class YunIndexBean {
    public static final int $stable = 8;
    private final List<Device> deviceList;

    @SerializedName(MonitorCommonConstants.DEFAULT_AID)
    private final Default index;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ YunIndexBean copy$default(YunIndexBean yunIndexBean, Default r1, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            r1 = yunIndexBean.index;
        }
        if ((i & 2) != 0) {
            list = yunIndexBean.deviceList;
        }
        return yunIndexBean.copy(r1, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Default getIndex() {
        return this.index;
    }

    public final List<Device> component2() {
        return this.deviceList;
    }

    public final YunIndexBean copy(Default index, List<Device> deviceList) {
        Intrinsics.checkNotNullParameter(index, "index");
        Intrinsics.checkNotNullParameter(deviceList, "deviceList");
        return new YunIndexBean(index, deviceList);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof YunIndexBean)) {
            return false;
        }
        YunIndexBean yunIndexBean = (YunIndexBean) other;
        return Intrinsics.areEqual(this.index, yunIndexBean.index) && Intrinsics.areEqual(this.deviceList, yunIndexBean.deviceList);
    }

    public int hashCode() {
        return (this.index.hashCode() * 31) + this.deviceList.hashCode();
    }

    public String toString() {
        return "YunIndexBean(index=" + this.index + ", deviceList=" + this.deviceList + ")";
    }

    public YunIndexBean(Default index, List<Device> deviceList) {
        Intrinsics.checkNotNullParameter(index, "index");
        Intrinsics.checkNotNullParameter(deviceList, "deviceList");
        this.index = index;
        this.deviceList = deviceList;
    }

    public final Default getIndex() {
        return this.index;
    }

    public final List<Device> getDeviceList() {
        return this.deviceList;
    }

    public final Device getSelectedDevice() {
        for (Device device : this.deviceList) {
            if (device.getSelected()) {
                return device;
            }
        }
        return null;
    }

    /* JADX INFO: compiled from: YunIndexBean.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J-\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/YunIndexBean$Default;", "", "list", "", "Lcom/cy/yyjia/zhe28/domain/YunIndexBean$Block;", "title", "", "title1", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getList", "()Ljava/util/List;", "getTitle", "()Ljava/lang/String;", "getTitle1", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Default {
        public static final int $stable = 8;
        private final List<Block> list;
        private final String title;
        private final String title1;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Default copy$default(Default r0, List list, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = r0.list;
            }
            if ((i & 2) != 0) {
                str = r0.title;
            }
            if ((i & 4) != 0) {
                str2 = r0.title1;
            }
            return r0.copy(list, str, str2);
        }

        public final List<Block> component1() {
            return this.list;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTitle1() {
            return this.title1;
        }

        public final Default copy(List<Block> list, String title, String title1) {
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(title1, "title1");
            return new Default(list, title, title1);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Default)) {
                return false;
            }
            Default r5 = (Default) other;
            return Intrinsics.areEqual(this.list, r5.list) && Intrinsics.areEqual(this.title, r5.title) && Intrinsics.areEqual(this.title1, r5.title1);
        }

        public int hashCode() {
            return (((this.list.hashCode() * 31) + this.title.hashCode()) * 31) + this.title1.hashCode();
        }

        public String toString() {
            return "Default(list=" + this.list + ", title=" + this.title + ", title1=" + this.title1 + ")";
        }

        public Default(List<Block> list, String title, String title1) {
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(title1, "title1");
            this.list = list;
            this.title = title;
            this.title1 = title1;
        }

        public final List<Block> getList() {
            return this.list;
        }

        public final String getTitle() {
            return this.title;
        }

        public final String getTitle1() {
            return this.title1;
        }
    }

    /* JADX INFO: compiled from: YunIndexBean.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b(\b\u0087\b\u0018\u00002\u00020\u0001BY\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0010J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u000eHÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0007HÆ\u0003J\t\u0010)\u001a\u00020\u0007HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u000eHÆ\u0003Jm\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eHÆ\u0001J\u0013\u00100\u001a\u00020\u000e2\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u00102\u001a\u00020\u0003J\u0006\u00103\u001a\u00020\u0003J\t\u00104\u001a\u00020\u0007HÖ\u0001J\t\u00105\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u000f\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0016\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0016\"\u0004\b \u0010\u001eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0012\"\u0004\b#\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018¨\u00066"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/YunIndexBean$Device;", "", "customGameId", "", "endTime", "", CommonConstants.key_gameId, "", "id", "name", "screen", "pic", Constant.START_TIME, "selected", "", "checked", "(Ljava/lang/String;JIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;JZZ)V", "getChecked", "()Z", "setChecked", "(Z)V", "getCustomGameId", "()Ljava/lang/String;", "getEndTime", "()J", "getGameId", "()I", "getId", "getName", "setName", "(Ljava/lang/String;)V", "getPic", "setPic", "getScreen", "getSelected", "setSelected", "getStartTime", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "getEndTimeStr", "getEndTimeStr2", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Device {
        public static final int $stable = 8;
        private boolean checked;
        private final String customGameId;
        private final long endTime;
        private final int gameId;
        private final int id;
        private String name;
        private String pic;
        private final String screen;
        private boolean selected;
        private final long startTime;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getCustomGameId() {
            return this.customGameId;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final boolean getChecked() {
            return this.checked;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getEndTime() {
            return this.endTime;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getGameId() {
            return this.gameId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getScreen() {
            return this.screen;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getPic() {
            return this.pic;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final long getStartTime() {
            return this.startTime;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final boolean getSelected() {
            return this.selected;
        }

        public final Device copy(String customGameId, long endTime, int gameId, int id, String name, String screen, String pic, long startTime, boolean selected, boolean checked) {
            Intrinsics.checkNotNullParameter(customGameId, "customGameId");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(screen, "screen");
            Intrinsics.checkNotNullParameter(pic, "pic");
            return new Device(customGameId, endTime, gameId, id, name, screen, pic, startTime, selected, checked);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Device)) {
                return false;
            }
            Device device = (Device) other;
            return Intrinsics.areEqual(this.customGameId, device.customGameId) && this.endTime == device.endTime && this.gameId == device.gameId && this.id == device.id && Intrinsics.areEqual(this.name, device.name) && Intrinsics.areEqual(this.screen, device.screen) && Intrinsics.areEqual(this.pic, device.pic) && this.startTime == device.startTime && this.selected == device.selected && this.checked == device.checked;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v13, types: [int] */
        /* JADX WARN: Type inference failed for: r1v15 */
        /* JADX WARN: Type inference failed for: r1v16 */
        /* JADX WARN: Type inference failed for: r2v0 */
        /* JADX WARN: Type inference failed for: r2v1, types: [int] */
        /* JADX WARN: Type inference failed for: r2v2 */
        public int hashCode() {
            int iHashCode = ((((((((((((((this.customGameId.hashCode() * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.endTime)) * 31) + this.gameId) * 31) + this.id) * 31) + this.name.hashCode()) * 31) + this.screen.hashCode()) * 31) + this.pic.hashCode()) * 31) + GMTitleBean$$ExternalSyntheticBackport0.m(this.startTime)) * 31;
            boolean z = this.selected;
            ?? r1 = z;
            if (z) {
                r1 = 1;
            }
            int i = (iHashCode + r1) * 31;
            boolean z2 = this.checked;
            return i + (z2 ? 1 : z2);
        }

        public String toString() {
            return "Device(customGameId=" + this.customGameId + ", endTime=" + this.endTime + ", gameId=" + this.gameId + ", id=" + this.id + ", name=" + this.name + ", screen=" + this.screen + ", pic=" + this.pic + ", startTime=" + this.startTime + ", selected=" + this.selected + ", checked=" + this.checked + ")";
        }

        public Device(String customGameId, long j, int i, int i2, String name, String screen, String pic, long j2, boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(customGameId, "customGameId");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(screen, "screen");
            Intrinsics.checkNotNullParameter(pic, "pic");
            this.customGameId = customGameId;
            this.endTime = j;
            this.gameId = i;
            this.id = i2;
            this.name = name;
            this.screen = screen;
            this.pic = pic;
            this.startTime = j2;
            this.selected = z;
            this.checked = z2;
        }

        public /* synthetic */ Device(String str, long j, int i, int i2, String str2, String str3, String str4, long j2, boolean z, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, j, i, i2, str2, str3, str4, j2, (i3 & 256) != 0 ? false : z, (i3 & 512) != 0 ? false : z2);
        }

        public final String getCustomGameId() {
            return this.customGameId;
        }

        public final long getEndTime() {
            return this.endTime;
        }

        public final int getGameId() {
            return this.gameId;
        }

        public final int getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.name = str;
        }

        public final String getScreen() {
            return this.screen;
        }

        public final String getPic() {
            return this.pic;
        }

        public final void setPic(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.pic = str;
        }

        public final long getStartTime() {
            return this.startTime;
        }

        public final boolean getSelected() {
            return this.selected;
        }

        public final void setSelected(boolean z) {
            this.selected = z;
        }

        public final boolean getChecked() {
            return this.checked;
        }

        public final void setChecked(boolean z) {
            this.checked = z;
        }

        public final String getEndTimeStr() {
            return "到期时间：" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(Long.parseLong(this.endTime + "000")));
        }

        public final String getEndTimeStr2() {
            String str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(Long.parseLong(this.endTime + "000")));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            return str;
        }
    }

    /* JADX INFO: compiled from: YunIndexBean.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/YunIndexBean$Block;", "", SocialConstants.PARAM_APP_DESC, "", "icon", "title", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "getIcon", "getTitle", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class Block {
        public static final int $stable = 0;
        private final String desc;
        private final String icon;
        private final String title;

        public static /* synthetic */ Block copy$default(Block block, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = block.desc;
            }
            if ((i & 2) != 0) {
                str2 = block.icon;
            }
            if ((i & 4) != 0) {
                str3 = block.title;
            }
            return block.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDesc() {
            return this.desc;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getIcon() {
            return this.icon;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        public final Block copy(String desc, String icon, String title) {
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(title, "title");
            return new Block(desc, icon, title);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Block)) {
                return false;
            }
            Block block = (Block) other;
            return Intrinsics.areEqual(this.desc, block.desc) && Intrinsics.areEqual(this.icon, block.icon) && Intrinsics.areEqual(this.title, block.title);
        }

        public int hashCode() {
            return (((this.desc.hashCode() * 31) + this.icon.hashCode()) * 31) + this.title.hashCode();
        }

        public String toString() {
            return "Block(desc=" + this.desc + ", icon=" + this.icon + ", title=" + this.title + ")";
        }

        public Block(String desc, String icon, String title) {
            Intrinsics.checkNotNullParameter(desc, "desc");
            Intrinsics.checkNotNullParameter(icon, "icon");
            Intrinsics.checkNotNullParameter(title, "title");
            this.desc = desc;
            this.icon = icon;
            this.title = title;
        }

        public final String getDesc() {
            return this.desc;
        }

        public final String getIcon() {
            return this.icon;
        }

        public final String getTitle() {
            return this.title;
        }
    }
}
