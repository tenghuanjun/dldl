package com.cy.yyjia.zhe28.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: AddressResult.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/AddressResult;", "", "address", "Lcom/cy/yyjia/zhe28/domain/AddressResult$DataBean;", "(Lcom/cy/yyjia/zhe28/domain/AddressResult$DataBean;)V", "getAddress", "()Lcom/cy/yyjia/zhe28/domain/AddressResult$DataBean;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "DataBean", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class AddressResult {
    public static final int $stable = 8;
    private final DataBean address;

    public static /* synthetic */ AddressResult copy$default(AddressResult addressResult, DataBean dataBean, int i, Object obj) {
        if ((i & 1) != 0) {
            dataBean = addressResult.address;
        }
        return addressResult.copy(dataBean);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final DataBean getAddress() {
        return this.address;
    }

    public final AddressResult copy(DataBean address) {
        Intrinsics.checkNotNullParameter(address, "address");
        return new AddressResult(address);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AddressResult) && Intrinsics.areEqual(this.address, ((AddressResult) other).address);
    }

    public int hashCode() {
        return this.address.hashCode();
    }

    public String toString() {
        return "AddressResult(address=" + this.address + ")";
    }

    public AddressResult(DataBean address) {
        Intrinsics.checkNotNullParameter(address, "address");
        this.address = address;
    }

    public final DataBean getAddress() {
        return this.address;
    }

    /* JADX INFO: compiled from: AddressResult.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b,\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B}\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000eJ\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010+\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010,\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0086\u0001\u00101\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u00102J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u000106HÖ\u0003J\b\u00107\u001a\u00020\u0003H\u0007J\t\u00108\u001a\u00020\u0007HÖ\u0001J\t\u00109\u001a\u00020\u0003HÖ\u0001R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0010\"\u0004\b\u001f\u0010\u0012R \u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0010\"\u0004\b!\u0010\u0012R \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0010\"\u0004\b#\u0010\u0012R \u0010\f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0010\"\u0004\b%\u0010\u0012R\u0015\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b&\u0010\u0018¨\u0006:"}, d2 = {"Lcom/cy/yyjia/zhe28/domain/AddressResult$DataBean;", "Landroidx/databinding/BaseObservable;", "address", "", "city", "country", "dateline", "", "id", "isdefault", "name", "province", "telephone", "uid", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "getCity", "setCity", "getCountry", "setCountry", "getDateline", "()Ljava/lang/Integer;", "setDateline", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getId", "setId", "getIsdefault", "setIsdefault", "getName", "setName", "getProvince", "setProvince", "getTelephone", "setTelephone", "getUid", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/cy/yyjia/zhe28/domain/AddressResult$DataBean;", "equals", "", "other", "", "getArea", "hashCode", "toString", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class DataBean extends BaseObservable {
        public static final int $stable = 8;

        @Bindable
        private String address;

        @Bindable
        private String city;

        @Bindable
        private String country;
        private Integer dateline;
        private Integer id;
        private String isdefault;

        @Bindable
        private String name;

        @Bindable
        private String province;

        @Bindable
        private String telephone;
        private final Integer uid;

        public DataBean() {
            this(null, null, null, null, null, null, null, null, null, null, 1023, null);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAddress() {
            return this.address;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Integer getUid() {
            return this.uid;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCity() {
            return this.city;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCountry() {
            return this.country;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getDateline() {
            return this.dateline;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getIsdefault() {
            return this.isdefault;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getProvince() {
            return this.province;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getTelephone() {
            return this.telephone;
        }

        public final DataBean copy(String address, String city, String country, Integer dateline, Integer id, String isdefault, String name, String province, String telephone, Integer uid) {
            return new DataBean(address, city, country, dateline, id, isdefault, name, province, telephone, uid);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DataBean)) {
                return false;
            }
            DataBean dataBean = (DataBean) other;
            return Intrinsics.areEqual(this.address, dataBean.address) && Intrinsics.areEqual(this.city, dataBean.city) && Intrinsics.areEqual(this.country, dataBean.country) && Intrinsics.areEqual(this.dateline, dataBean.dateline) && Intrinsics.areEqual(this.id, dataBean.id) && Intrinsics.areEqual(this.isdefault, dataBean.isdefault) && Intrinsics.areEqual(this.name, dataBean.name) && Intrinsics.areEqual(this.province, dataBean.province) && Intrinsics.areEqual(this.telephone, dataBean.telephone) && Intrinsics.areEqual(this.uid, dataBean.uid);
        }

        public int hashCode() {
            String str = this.address;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.city;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.country;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Integer num = this.dateline;
            int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.id;
            int iHashCode5 = (iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str4 = this.isdefault;
            int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.name;
            int iHashCode7 = (iHashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.province;
            int iHashCode8 = (iHashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.telephone;
            int iHashCode9 = (iHashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31;
            Integer num3 = this.uid;
            return iHashCode9 + (num3 != null ? num3.hashCode() : 0);
        }

        public String toString() {
            return "DataBean(address=" + this.address + ", city=" + this.city + ", country=" + this.country + ", dateline=" + this.dateline + ", id=" + this.id + ", isdefault=" + this.isdefault + ", name=" + this.name + ", province=" + this.province + ", telephone=" + this.telephone + ", uid=" + this.uid + ")";
        }

        public /* synthetic */ DataBean(String str, String str2, String str3, Integer num, Integer num2, String str4, String str5, String str6, String str7, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? 0 : num, (i & 16) != 0 ? 0 : num2, (i & 32) != 0 ? "" : str4, (i & 64) != 0 ? "" : str5, (i & 128) != 0 ? "" : str6, (i & 256) == 0 ? str7 : "", (i & 512) != 0 ? 0 : num3);
        }

        public final String getAddress() {
            return this.address;
        }

        public final void setAddress(String str) {
            this.address = str;
        }

        public final String getCity() {
            return this.city;
        }

        public final void setCity(String str) {
            this.city = str;
        }

        public final String getCountry() {
            return this.country;
        }

        public final void setCountry(String str) {
            this.country = str;
        }

        public final Integer getDateline() {
            return this.dateline;
        }

        public final void setDateline(Integer num) {
            this.dateline = num;
        }

        public final Integer getId() {
            return this.id;
        }

        public final void setId(Integer num) {
            this.id = num;
        }

        public final String getIsdefault() {
            return this.isdefault;
        }

        public final void setIsdefault(String str) {
            this.isdefault = str;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            this.name = str;
        }

        public final String getProvince() {
            return this.province;
        }

        public final void setProvince(String str) {
            this.province = str;
        }

        public final String getTelephone() {
            return this.telephone;
        }

        public final void setTelephone(String str) {
            this.telephone = str;
        }

        public final Integer getUid() {
            return this.uid;
        }

        public DataBean(String str, String str2, String str3, Integer num, Integer num2, String str4, String str5, String str6, String str7, Integer num3) {
            this.address = str;
            this.city = str2;
            this.country = str3;
            this.dateline = num;
            this.id = num2;
            this.isdefault = str4;
            this.name = str5;
            this.province = str6;
            this.telephone = str7;
            this.uid = num3;
        }

        @Bindable
        public final String getArea() {
            String str = this.province + StringUtils.SPACE + this.city + StringUtils.SPACE + this.country;
            return str.length() == 2 ? "" : str;
        }
    }
}
