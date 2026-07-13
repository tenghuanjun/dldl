package com.volcengine.cloudphone.apiservice;

/* JADX INFO: loaded from: classes3.dex */
public class ProbeStats {
    private int downloadBandwidth;
    private int downloadJitter;
    private double downloadLossPercent;
    private int rtt;
    private int uploadBandwidth;
    private int uploadJitter;
    private double uploadLossPercent;

    public int getDownBandwidth() {
        return this.downloadBandwidth;
    }

    public int getDownloadJitter() {
        return this.downloadJitter;
    }

    public double getDownloadLossPercent() {
        return this.downloadLossPercent;
    }

    public int getRtt() {
        return this.rtt;
    }

    public int getUploadBandwidth() {
        return this.uploadBandwidth;
    }

    public int getUploadJitter() {
        return this.uploadJitter;
    }

    public double getUploadLossPercent() {
        return this.uploadLossPercent;
    }

    public void setDownBandwidth(int i) {
        this.downloadBandwidth = i;
    }

    public void setDownloadJitter(int i) {
        this.downloadJitter = i;
    }

    public void setDownloadLossPercent(double d) {
        this.downloadLossPercent = d;
    }

    public void setRtt(int i) {
        this.rtt = i;
    }

    public void setUploadBandwidth(int i) {
        this.uploadBandwidth = i;
    }

    public void setUploadJitter(int i) {
        this.uploadJitter = i;
    }

    public void setUploadLossPercent(double d) {
        this.uploadLossPercent = d;
    }
}
