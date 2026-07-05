package com.sqwan.common.annotation;

import android.text.TextUtils;
import com.sq.tools.network.httpdns.SqHttpDns;
import com.sq.tools.utils.SoftwareUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ProtectUrlManager {
    public static ArrayList<String> defaultProtectHosts;
    private static ProtectUrlManager instance;
    private ArrayList<String> protectHosts = new ArrayList<>();
    private ArrayList<String> protectMainHosts = new ArrayList<>();

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        defaultProtectHosts = arrayList;
        arrayList.add("bugless.shan-yu-tech.com");
        defaultProtectHosts.add("track.37.com.cn");
    }

    private ProtectUrlManager() {
    }

    public static ProtectUrlManager getInstance() {
        if (instance == null) {
            synchronized (ProtectUrlManager.class) {
                if (instance == null) {
                    instance = new ProtectUrlManager();
                }
            }
        }
        return instance;
    }

    public void setProtectUrls(ArrayList<String> arrayList) {
        HashSet hashSet = new HashSet();
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        this.protectHosts.clear();
        this.protectHosts.addAll(defaultProtectHosts);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String host = SoftwareUtils.getHost(it.next());
            if (!TextUtils.isEmpty(host)) {
                hashSet.add(host);
            }
        }
        this.protectHosts.addAll(hashSet);
        SqHttpDns.getInstance().setProtectHosts(this.protectHosts);
    }

    public ArrayList<String> getProtectHosts() {
        return this.protectHosts;
    }

    public ArrayList<String> getProtectMainHosts() {
        return this.protectMainHosts;
    }

    public void addProtectHost(String str) {
        ArrayList<String> arrayList = this.protectHosts;
        if (arrayList == null || arrayList.contains(str)) {
            return;
        }
        this.protectMainHosts.add(str);
        SqHttpDns.getInstance().addProtectHost(str);
    }

    public void addProtectHosts(ArrayList<String> arrayList) {
        if (this.protectHosts != null) {
            for (String str : arrayList) {
                if (!this.protectHosts.contains(str)) {
                    this.protectMainHosts.add(str);
                    SqHttpDns.getInstance().addProtectHost(str);
                }
            }
        }
    }

    public void addProtectMainHost(String str) {
        ArrayList<String> arrayList = this.protectMainHosts;
        if (arrayList == null || arrayList.contains(str)) {
            return;
        }
        this.protectMainHosts.add(str);
        SqHttpDns.getInstance().addProtectMainHost(str);
    }
}
