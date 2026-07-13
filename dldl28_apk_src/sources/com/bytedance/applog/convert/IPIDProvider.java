package com.bytedance.applog.convert;

import android.content.Context;
import android.text.TextUtils;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IPIDProvider implements IIdProvider {
    @Override // com.bytedance.applog.convert.IIdProvider
    public void getIdAndSetIntoJson(JSONObject jSONObject, Context context) throws JSONException {
        String clientTun;
        String clientAnpi = null;
        try {
            clientTun = getClientTun();
        } catch (SocketException e) {
            e.printStackTrace();
            clientTun = null;
        }
        if (!TextUtils.isEmpty(clientTun)) {
            jSONObject.put(BusinessConstant.KEY_CLIENT_TUN, clientTun);
        }
        try {
            clientAnpi = getClientAnpi();
        } catch (SocketException e2) {
            e2.printStackTrace();
        } catch (UnknownHostException e3) {
            e3.printStackTrace();
        }
        if (TextUtils.isEmpty(clientAnpi)) {
            return;
        }
        jSONObject.put(BusinessConstant.KEY_CLIENT_ANPI, clientAnpi);
    }

    public String getClientTun() throws SocketException {
        String lowerCase;
        NetworkInterface byName = NetworkInterface.getByName("dummy0");
        if (byName == null) {
            lowerCase = "empty";
        } else {
            lowerCase = getLocalIpv6Address(byName.getInetAddresses()).toLowerCase(Locale.getDefault());
        }
        return lowerCase.startsWith("fe80") ? lowerCase : "";
    }

    public String getClientAnpi() throws SocketException, UnknownHostException {
        NetworkInterface byName = NetworkInterface.getByName("wlan0");
        if (byName != null) {
            String lowerCase = getLocalIpv6Address(byName.getInetAddresses()).toLowerCase(Locale.getDefault());
            byte[] hardwareAddress = byName.getHardwareAddress();
            if (hardwareAddress != null && hardwareAddress.length == 6 && lowerCase.startsWith("fe80")) {
                byte[] address = Inet6Address.getByName(lowerCase).getAddress();
                if (address[10] == hardwareAddress[2] && address[13] == hardwareAddress[3] && address[14] == hardwareAddress[4] && address[15] == hardwareAddress[5]) {
                    return lowerCase;
                }
            }
        }
        return "";
    }

    public static String getLocalIpv6Address(Enumeration<InetAddress> enumeration) {
        String hostAddress = "empty";
        if (enumeration == null) {
            return "empty";
        }
        while (enumeration.hasMoreElements()) {
            InetAddress inetAddressNextElement = enumeration.nextElement();
            if ((inetAddressNextElement instanceof Inet6Address) && inetAddressNextElement.isLinkLocalAddress()) {
                hostAddress = inetAddressNextElement.getHostAddress();
            }
        }
        return hostAddress;
    }
}
