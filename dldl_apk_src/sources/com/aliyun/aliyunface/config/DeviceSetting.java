package com.aliyun.aliyunface.config;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class DeviceSetting {
    boolean displayAuto = true;
    int displayAngle = 90;
    boolean cameraAuto = true;
    int cameraID = 1;
    boolean algorithmAuto = true;
    int algorithmAngle = 270;
    boolean widthAuto = true;
    int width = 640;
    int zoom = 0;
    int maxApiLevel = 100;
    int minApiLevel = 0;
    boolean isp = false;
    boolean slir = false;

    public boolean isDisplayAuto() {
        return this.displayAuto;
    }

    public void setDisplayAuto(boolean z) {
        this.displayAuto = z;
    }

    public int getDisplayAngle() {
        return this.displayAngle;
    }

    public void setDisplayAngle(int i) {
        this.displayAngle = i;
    }

    public boolean isCameraAuto() {
        return this.cameraAuto;
    }

    public void setCameraAuto(boolean z) {
        this.cameraAuto = z;
    }

    public int getCameraID() {
        return this.cameraID;
    }

    public void setCameraID(int i) {
        this.cameraID = i;
    }

    public int getAlgorithmAngle() {
        return this.algorithmAngle;
    }

    public void setAlgorithmAngle(int i) {
        this.algorithmAngle = i;
    }

    public boolean isAlgorithmAuto() {
        return this.algorithmAuto;
    }

    public void setAlgorithmAuto(boolean z) {
        this.algorithmAuto = z;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public boolean isWidthAuto() {
        return this.widthAuto;
    }

    public void setWidthAuto(boolean z) {
        this.widthAuto = z;
    }

    public int getZoom() {
        return this.zoom;
    }

    public void setZoom(int i) {
        this.zoom = i;
    }

    public int getMaxApiLevel() {
        return this.maxApiLevel;
    }

    public void setMaxApiLevel(int i) {
        this.maxApiLevel = i;
    }

    public int getMinApiLevel() {
        return this.minApiLevel;
    }

    public void setMinApiLevel(int i) {
        this.minApiLevel = i;
    }

    public boolean isIsp() {
        return this.isp;
    }

    public void setIsp(boolean z) {
        this.isp = z;
    }

    public boolean isSlir() {
        return this.slir;
    }

    public void setSlir(boolean z) {
        this.slir = z;
    }

    public String toString() {
        return "DeviceSetting{displayAuto=" + this.displayAuto + ", displayAngle=" + this.displayAngle + ", cameraAuto=" + this.cameraAuto + ", cameraID=" + this.cameraID + ", algorithmAuto=" + this.algorithmAuto + ", algorithmAngle=" + this.algorithmAngle + ", widthAuto=" + this.widthAuto + ", width=" + this.width + ", zoom=" + this.zoom + ", maxApiLevel=" + this.maxApiLevel + ", minApiLevel=" + this.minApiLevel + ", isp=" + this.isp + ", slir=" + this.slir + '}';
    }
}
