package com.donkingliang.imageselector.entry;

import com.donkingliang.imageselector.utils.StringUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class Folder {
    private ArrayList<Image> images;
    private String name;
    private boolean useCamera;

    public Folder(String str) {
        this.name = str;
    }

    public Folder(String str, ArrayList<Image> arrayList) {
        this.name = str;
        this.images = arrayList;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public ArrayList<Image> getImages() {
        return this.images;
    }

    public void setImages(ArrayList<Image> arrayList) {
        this.images = arrayList;
    }

    public boolean isUseCamera() {
        return this.useCamera;
    }

    public void setUseCamera(boolean z) {
        this.useCamera = z;
    }

    public void addImage(Image image) {
        if (image == null || !StringUtils.isNotEmptyString(image.getPath())) {
            return;
        }
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(image);
    }

    public String toString() {
        return "Folder{name='" + this.name + "', images=" + this.images + '}';
    }
}
