package com.huya.berry.module.props.prop;

import com.duowan.HUYA.MobilePropsItem;
import com.duowan.auk.util.L;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PropStruct {
    private static final String TAG = "PropStruct";

    public static List<PropItem> parseMobileProps(List<MobilePropsItem> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<MobilePropsItem> it = list.iterator();
        while (it.hasNext()) {
            PropItem propItem = getPropItem(it.next());
            if (propItem.isValid()) {
                arrayList.add(propItem);
            } else {
                L.warn(TAG, "inValid prop id %d name %s", Integer.valueOf(propItem.getId()), propItem.getName());
            }
        }
        return arrayList;
    }

    public static List<PropItem> parseMobileProps(List<PropItem> list, List<MobilePropsItem> list2, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (MobilePropsItem mobilePropsItem : list2) {
            if (z && containsProps(list, mobilePropsItem)) {
                L.info(TAG, "parseMobileProps 礼物id %d name %s 已存在...", Integer.valueOf(mobilePropsItem.getIPropsId()), mobilePropsItem.getSPropsName());
            } else {
                PropItem propItem = getPropItem(mobilePropsItem);
                if (propItem.isValid()) {
                    arrayList.add(propItem);
                    L.info(TAG, "parseMobileProps 加入礼物id %d name %s ...", Integer.valueOf(propItem.getId()), propItem.getName());
                } else {
                    L.warn(TAG, "parseMobileProps inValid prop id %d name %s", Integer.valueOf(propItem.getId()), propItem.getName());
                }
            }
        }
        return arrayList;
    }

    private static boolean containsProps(List<PropItem> list, MobilePropsItem mobilePropsItem) {
        for (int i = 0; i < list.size(); i++) {
            PropItem propItem = list.get(i);
            if (propItem.getId() == mobilePropsItem.getIPropsId()) {
                return propItem.getName().equals(mobilePropsItem.getSPropsName());
            }
        }
        return false;
    }

    private static PropItem getPropItem(MobilePropsItem mobilePropsItem) {
        PropItem propItem = new PropItem();
        propItem.mId = mobilePropsItem.getIPropsId();
        propItem.mName = mobilePropsItem.getSPropsName();
        propItem.mResUrl = mobilePropsItem.getTAppIdentity().getSAndroid();
        return propItem;
    }
}
