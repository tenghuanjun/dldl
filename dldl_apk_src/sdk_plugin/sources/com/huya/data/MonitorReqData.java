package com.huya.data;

import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MonitorReqData {
    public String sMetricName = "";
    public ArrayList<DimensionWrapper> vDimension = new ArrayList<>();
    public ArrayList<FieldWrapper> vField = new ArrayList<>();
    public ArrayList<DimensionWrapper> vExLog = new ArrayList<>();
    public long iTS = 0;

    public static class DimensionWrapper {
        public String sName;
        public String sValue;

        public DimensionWrapper(String str, String str2) {
            this.sName = "";
            this.sValue = "";
            this.sName = str;
            this.sValue = str2;
        }
    }

    public static class FieldWrapper {
        public double fValue;
        public String sName;

        public FieldWrapper(String str, double d) {
            this.sName = "";
            this.fValue = 0.0d;
            this.sName = str;
            this.fValue = d;
        }
    }
}
