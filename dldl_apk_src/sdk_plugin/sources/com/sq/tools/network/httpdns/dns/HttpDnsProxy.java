package com.sq.tools.network.httpdns.dns;

import android.content.Context;
import com.sq.tools.network.httpdns.SqHttpDns;
import com.sq.tools.network.httpdns.callback.IDnsRequestListener;
import com.sq.tools.network.httpdns.data.DnsData;
import com.sq.tools.network.httpdns.data.IPData;
import com.sq.tools.network.httpdns.log.HttpDnsLog;
import com.sq.tools.network.httpdns.network.HttpDnsRequestManager;
import com.sq.tools.network.httpdns.util.HttpDnsUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpDnsProxy {
    public static String getIpByHost(final Context context, final String host, List<String> excludeIp, boolean ipv6, final IDnsRequestListener dnsRequestListener) {
        IPData iPDataSelectIpV4;
        boolean zIsGlobalIpV6Enable = SqHttpDns.getInstance().isGlobalIpV6Enable();
        HttpDnsLog.v("HttpDnsProxy getIpByHost " + host + ", ipv6=" + ipv6 + ", globalIpV6=" + zIsGlobalIpV6Enable);
        if (!isProtect(host)) {
            HttpDnsLog.w(host + "既不在保护域名列表也不在主域保护列表中, 无法解析");
            return "";
        }
        DnsData httpDnsDataByHost = HttpDnsCache.getHttpDnsDataByHost(context, host);
        if (!host.equals(httpDnsDataByHost.getHost())) {
            HttpDnsLog.w(host + "与DnsData缓存不匹配, 无法解析, 请求刷新 " + host + " 配置");
            SqHttpDns.getInstance().executeAsync(new Runnable() { // from class: com.sq.tools.network.httpdns.dns.-$$Lambda$HttpDnsProxy$b5r5yUJNIKyfh4V3yUC00KisalQ
                @Override // java.lang.Runnable
                public final void run() {
                    HttpDnsRequestManager.getInstance().requestDnsSingle(context, host, dnsRequestListener);
                }
            });
            return "";
        }
        if (ipv6 && zIsGlobalIpV6Enable) {
            boolean zIsSupportIpV6 = HttpDnsUtil.isSupportIpV6();
            if (zIsSupportIpV6 && !hasIpV6(excludeIp)) {
                iPDataSelectIpV4 = selectIpV6(httpDnsDataByHost, excludeIp);
                if (iPDataSelectIpV4 == null || !HttpDnsUtil.isIpAddress(iPDataSelectIpV4.getIp())) {
                    HttpDnsLog.v("IpV6域名配置无效, 降级到IpV4");
                    iPDataSelectIpV4 = selectIpV4(httpDnsDataByHost, excludeIp);
                }
            } else {
                if (!zIsSupportIpV6) {
                    HttpDnsLog.v("设备不支持IpV6, 使用IpV4");
                } else {
                    HttpDnsLog.v("排除列表中包含IpV6, 使用IpV4");
                }
                iPDataSelectIpV4 = selectIpV4(httpDnsDataByHost, excludeIp);
            }
        } else {
            iPDataSelectIpV4 = selectIpV4(httpDnsDataByHost, excludeIp);
        }
        if (iPDataSelectIpV4 == null) {
            HttpDnsLog.w("IpV4域名配置为空, 无法解析: " + host + ", 触发单域名请求");
            SqHttpDns.getInstance().executeAsync(new Runnable() { // from class: com.sq.tools.network.httpdns.dns.-$$Lambda$HttpDnsProxy$yK1PAImbhlpo06uWt7p7Q6BhUgs
                @Override // java.lang.Runnable
                public final void run() {
                    HttpDnsRequestManager.getInstance().requestDnsSingle(context, host, dnsRequestListener);
                }
            });
            return "";
        }
        if (httpDnsDataByHost.overTime()) {
            HttpDnsLog.v("过了有效期, 单域名请求获取: " + host);
            SqHttpDns.getInstance().executeAsync(new Runnable() { // from class: com.sq.tools.network.httpdns.dns.-$$Lambda$HttpDnsProxy$sozYA_3BXBjiPny2KTQ_-Vxd5_M
                @Override // java.lang.Runnable
                public final void run() {
                    HttpDnsRequestManager.getInstance().requestDnsSingle(context, host, dnsRequestListener);
                }
            });
        }
        String ip = iPDataSelectIpV4.getIp();
        if (!HttpDnsUtil.isIpAddress(ip)) {
            HttpDnsLog.w(host + " 域名解析结果(ip=" + ip + ")不合法, 忽略");
            return "";
        }
        HttpDnsLog.d(host + "随机选择的ip为 " + ip);
        return ip;
    }

    private static IPData selectIpV4(DnsData dnsData, List<String> excludeIp) {
        List<IPData> ips = dnsData.getIps();
        if (ips == null || ips.isEmpty()) {
            return null;
        }
        return selectIp(ips, excludeIp);
    }

    private static IPData selectIpV6(DnsData dnsData, List<String> excludeIp) {
        List<IPData> ipV6s = dnsData.getIpV6s();
        if (ipV6s == null || ipV6s.isEmpty()) {
            return null;
        }
        return selectIp(ipV6s, excludeIp);
    }

    private static IPData selectIp(List<IPData> ips, List<String> excludeIp) {
        List<IPData> listExcludeIp = excludeIp(ips, excludeIp);
        if (listExcludeIp == null || listExcludeIp.isEmpty()) {
            return null;
        }
        int weight = 0;
        if (listExcludeIp.size() > 1) {
            Iterator<IPData> it = listExcludeIp.iterator();
            int weight2 = 0;
            while (it.hasNext()) {
                weight2 += it.next().getWeight();
            }
            int iNextInt = new Random().nextInt(weight2);
            HttpDnsLog.v("随机数：weightSum=" + weight2 + " ips:" + listExcludeIp);
            for (IPData iPData : listExcludeIp) {
                if (weight <= iNextInt && iNextInt < iPData.getWeight() + weight) {
                    HttpDnsLog.v("随机数：" + iNextInt + " 落在区间[" + weight + "," + (weight + iPData.getWeight()) + "),命中,返回 " + iPData);
                    return iPData;
                }
                weight += iPData.getWeight();
            }
            return null;
        }
        return listExcludeIp.get(0);
    }

    private static List<IPData> excludeIp(List<IPData> data, List<String> excludeIp) {
        if (data == null || data.isEmpty() || excludeIp == null || excludeIp.isEmpty()) {
            return data;
        }
        ArrayList arrayList = new ArrayList();
        for (int size = data.size() - 1; size >= 0; size--) {
            IPData iPData = data.get(size);
            if (iPData != null) {
                if (excludeIp.contains(iPData.getIp())) {
                    HttpDnsLog.v("剔除" + iPData);
                } else {
                    arrayList.add(iPData);
                }
            }
        }
        return arrayList;
    }

    private static boolean hasIpV6(List<String> ips) {
        if (ips != null && !ips.isEmpty()) {
            Iterator<String> it = ips.iterator();
            while (it.hasNext()) {
                if (HttpDnsUtil.isIpV6Address(it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isProtectHosts(String host) {
        ArrayList<String> protectHosts = SqHttpDns.getInstance().getProtectHosts();
        if (protectHosts == null || protectHosts.isEmpty() || !protectHosts.contains(host)) {
            return false;
        }
        HttpDnsLog.v(host + "在保护域名列表中");
        return true;
    }

    public static boolean isProtectMainHosts(String host) {
        Matcher matcher = Pattern.compile("[\\w-]+\\.(com.cn|com.hk|net.cn|gov.cn|org.cn|com|net|org|gov|cc|biz|info|cn|co|tv|mobi|me|name|asia|hk|ac.cn|bj.cn|sh.cn|tj.cn|cq.cn|he.cn|sx.cn|nm.cn|ln.cn|jl.cn|hl.cn|js.cn|zj.cn|ah.cn|fj.cn|jx.cn|sd.cn|ha.cn|hb.cn|hn.cn|gd.cn|gx.cn|hi.cn|sc.cn|gz.cn|yn.cn|xz.cn|sn.cn|gs.cn|qh.cn|nx.cn|xj.cn|tw.cn|hk.cn|mo.cn|travel|tw|com.tw|la|sh|ac|io|ws|us|tm|vc|ag|bz|in|mn|sc|co|org.tw|jobs|tel|网络|公司|中国)\\b()*").matcher(host);
        String strGroup = matcher.find() ? matcher.group() : host;
        ArrayList<String> protectMainHosts = SqHttpDns.getInstance().getProtectMainHosts();
        if (protectMainHosts == null || protectMainHosts.isEmpty() || !protectMainHosts.contains(strGroup)) {
            return false;
        }
        HttpDnsLog.v(host + "的主域" + strGroup + "在主域名保护列表中");
        return true;
    }

    public static boolean isProtect(String host) {
        return isProtectHosts(host) || isProtectMainHosts(host);
    }
}
