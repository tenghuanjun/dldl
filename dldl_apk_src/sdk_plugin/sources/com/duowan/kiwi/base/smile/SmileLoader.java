package com.duowan.kiwi.base.smile;

import android.content.res.XmlResourceParser;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class SmileLoader implements SmileConst {
    SmileLoader() {
    }

    static Map<String, String> manualLoad() {
        HashMap map = new HashMap(46);
        map.put("/{dx", "0.png");
        map.put("/{sh", "1.png");
        map.put("/{tx", "2.png");
        map.put("/{dk", "3.png");
        map.put("/{hh", "4.png");
        map.put("/{66", "5.png");
        map.put("/{gd", "6.png");
        map.put("/{yw", "7.png");
        map.put("/{xh", "8.png");
        map.put("/{jx", "9.png");
        map.put("/{zan", "10.png");
        map.put("/{ka", "11.png");
        map.put("/{am", "12.png");
        map.put("/{kx", "13.png");
        map.put("/{88", "14.png");
        map.put("/{hx", "15.png");
        map.put("/{zs", "16.png");
        map.put("/{pu", "17.png");
        map.put("/{zc", "18.png");
        map.put("/{sq", "19.png");
        map.put("/{fe", "20.png");
        map.put("/{bz", "21.png");
        map.put("/{kw", "22.png");
        map.put("/{xu", "23.png");
        map.put("/{xk", "24.png");
        map.put("/{lh", "25.png");
        map.put("/{bk", "26.png");
        map.put("/{hq", "27.png");
        map.put("/{tp", "28.png");
        map.put("/{gl", "29.png");
        map.put("/{cl", "30.png");
        map.put("/{dg", "31.png");
        map.put("/{kun", "32.png");
        map.put("/{yb", "33.png");
        map.put("/{zt", "34.png");
        map.put("/{kl", "35.png");
        map.put("/{cc", "36.png");
        map.put("/{xd", "37.png");
        map.put("/{dao", "38.png");
        map.put("/{dhl", "51.png");
        map.put("/{hj", "52.png");
        map.put("/{ns", "54.png");
        map.put("/{kiss", "53.png");
        map.put("/{wg", "55.png");
        map.put("/{zj", "56.png");
        map.put("/{kun", "57.png");
        return map;
    }

    static Map<String, String> manualLoadString() {
        HashMap map = new HashMap(46);
        map.put("/{dx", "[大笑]");
        map.put("/{sh", "[送花]");
        map.put("/{tx", "[偷笑]");
        map.put("/{dk", "[大哭]");
        map.put("/{hh", "[嘿哈]");
        map.put("/{66", "[666]");
        map.put("/{gd", "[感动]");
        map.put("/{yw", "[疑问]");
        map.put("/{xh", "[喜欢]");
        map.put("/{jx", "[奸笑]");
        map.put("/{zan", "[赞]");
        map.put("/{ka", "[可爱]");
        map.put("/{am", "[傲慢]");
        map.put("/{kx", "[开心]");
        map.put("/{88", "[拜拜]");
        map.put("/{hx", "[害羞]");
        map.put("/{zs", "[衰]");
        map.put("/{pu", "[吐血]");
        map.put("/{zc", "[嘴馋]");
        map.put("/{sq", "[生气]");
        map.put("/{fe", "[扶额]");
        map.put("/{bz", "[闭嘴]");
        map.put("/{kw", "[枯萎]");
        map.put("/{xu", "[嘘]");
        map.put("/{xk", "[笑哭]");
        map.put("/{lh", "[流汗]");
        map.put("/{bk", "[不看]");
        map.put("/{hq", "[哈欠]");
        map.put("/{tp", "[调皮]");
        map.put("/{gl", "[鬼脸]");
        map.put("/{cl", "[戳脸]");
        map.put("/{dg", "[大哥]");
        map.put("/{kun", "[困]");
        map.put("/{yb", "[拥抱]");
        map.put("/{zt", "[猪头]");
        map.put("/{kl", "[骷髅]");
        map.put("/{cc", "[臭臭]");
        map.put("/{xd", "[心动]");
        map.put("/{dao", "[刀]");
        map.put("/{dhl", "[打呼]");
        map.put("/{hj", "[滑稽]");
        map.put("/{ns", "[难受]");
        map.put("/{kiss", "[亲亲]");
        map.put("/{wg", "[无辜]");
        map.put("/{zj", "[震惊]");
        map.put("/{kun", "[困]");
        return map;
    }

    private static boolean isSmileDataLocation(XmlResourceParser xmlResourceParser, int i) {
        return i == 2 && xmlResourceParser.getName().equals(SmileConst.FACE_ATTNAME);
    }
}
