package com.youme.imsdk;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class YIMMessageBodyFile implements IYIMMessageBodyBase {
    private String extParam;
    private String fileExtension;
    private String fileName;
    private int fileSize;
    private int fileType;
    private String localPath;

    public String getFileExtension() {
        return this.fileExtension;
    }

    public void setFileExtension(String str) {
        this.fileExtension = str;
    }

    public String getExtParam() {
        return this.extParam;
    }

    public void setExtParam(String str) {
        this.extParam = str;
    }

    public int getFileType() {
        return this.fileType;
    }

    public void setFileType(int i) {
        this.fileType = i;
    }

    public int getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(int i) {
        this.fileSize = i;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setLocalPath(String str) {
        this.localPath = str;
    }

    public String getLocalPath() {
        return this.localPath;
    }
}
