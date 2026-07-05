package com.getui.gtc.dim;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class DimRequest implements Parcelable {
    public static final Parcelable.Creator<DimRequest> CREATOR = new Parcelable.Creator<DimRequest>() { // from class: com.getui.gtc.dim.DimRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DimRequest createFromParcel(Parcel parcel) {
            return new DimRequest(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DimRequest[] newArray(int i) {
            return new DimRequest[i];
        }
    };
    private AllowSysCall allowSysCall;
    private String key;
    private long ramCacheValidTime;
    private long storageCacheValidTime;
    private boolean useExpiredCacheForReserve;

    public static class Builder {
        private AllowSysCall allowSysCall;
        private String key;
        private long ramCacheValidTime;
        private long storageCacheValidTime;
        private boolean useExpiredCacheForReserve;

        public Builder() {
            this.ramCacheValidTime = 60000L;
            this.storageCacheValidTime = 60000L;
            this.allowSysCall = AllowSysCall.ALL_ALLOW;
            this.useExpiredCacheForReserve = false;
        }

        public Builder(DimRequest dimRequest) {
            this.ramCacheValidTime = 60000L;
            this.storageCacheValidTime = 60000L;
            this.allowSysCall = AllowSysCall.ALL_ALLOW;
            this.useExpiredCacheForReserve = false;
            this.key = dimRequest.key;
            this.ramCacheValidTime = dimRequest.ramCacheValidTime;
            this.storageCacheValidTime = dimRequest.storageCacheValidTime;
            this.allowSysCall = dimRequest.allowSysCall;
            this.useExpiredCacheForReserve = dimRequest.useExpiredCacheForReserve;
        }

        public Builder allowSysCall(AllowSysCall allowSysCall) {
            this.allowSysCall = allowSysCall;
            return this;
        }

        public Builder allowSysCall(boolean z) {
            this.allowSysCall = z ? AllowSysCall.ALL_ALLOW : AllowSysCall.NOT_ALLOW;
            return this;
        }

        public DimRequest build() {
            return new DimRequest(this);
        }

        public Builder key(String str) {
            this.key = str;
            return this;
        }

        public Builder ramCacheValidTime(long j) {
            this.ramCacheValidTime = j;
            return this;
        }

        public Builder storageCacheValidTime(long j) {
            this.storageCacheValidTime = j;
            return this;
        }

        public Builder useExpiredCacheForReserve(boolean z) {
            this.useExpiredCacheForReserve = z;
            return this;
        }
    }

    private DimRequest() {
    }

    protected DimRequest(Parcel parcel) {
        this.key = parcel.readString();
        this.ramCacheValidTime = parcel.readLong();
        this.storageCacheValidTime = parcel.readLong();
        this.allowSysCall = AllowSysCall.valueOf(parcel.readInt());
        this.useExpiredCacheForReserve = parcel.readByte() != 0;
    }

    private DimRequest(Builder builder) {
        this.key = builder.key;
        this.ramCacheValidTime = builder.ramCacheValidTime;
        this.storageCacheValidTime = builder.storageCacheValidTime;
        this.allowSysCall = builder.allowSysCall;
        this.useExpiredCacheForReserve = builder.useExpiredCacheForReserve;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AllowSysCall getAllowSysCall() {
        return this.allowSysCall;
    }

    public String getKey() {
        return this.key;
    }

    public long getRamCacheValidTime() {
        return this.ramCacheValidTime;
    }

    public long getStorageCacheValidTime() {
        return this.storageCacheValidTime;
    }

    public boolean isUseExpiredCacheForReserve() {
        return this.useExpiredCacheForReserve;
    }

    public void setAllowSysCall(AllowSysCall allowSysCall) {
        this.allowSysCall = allowSysCall;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setRamCacheValidTime(long j) {
        this.ramCacheValidTime = j;
    }

    public void setStorageCacheValidTime(long j) {
        this.storageCacheValidTime = j;
    }

    public void setUseExpiredCacheForReserve(boolean z) {
        this.useExpiredCacheForReserve = z;
    }

    public String toString() {
        return "DimRequest{key='" + this.key + "', ramCacheValidTime=" + this.ramCacheValidTime + ", storageCacheValidTime=" + this.storageCacheValidTime + ", allowSysCall=" + this.allowSysCall + ", useExpiredCacheForReserve=" + this.useExpiredCacheForReserve + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.key);
        parcel.writeLong(this.ramCacheValidTime);
        parcel.writeLong(this.storageCacheValidTime);
        parcel.writeInt(this.allowSysCall.getValue());
        parcel.writeByte(this.useExpiredCacheForReserve ? (byte) 1 : (byte) 0);
    }
}
