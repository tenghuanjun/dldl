package layaair.game.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.igexin.sdk.PushConsts;
import layaair.game.browser.ConchJNI;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class NetworkReceiver extends BroadcastReceiver {
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1093)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1118)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    public static int a(android.content.Context r2) {
        /*
            java.lang.String r0 = "connectivity"
            java.lang.Object r2 = r2.getSystemService(r0)
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2
            android.net.NetworkInfo r2 = r2.getActiveNetworkInfo()
            r0 = 5
            if (r2 == 0) goto L2d
            boolean r1 = r2.isConnected()
            if (r1 == 0) goto L2d
            int r1 = r2.getType()
            switch(r1) {
                case 0: goto L1f;
                case 1: goto L1d;
                default: goto L1c;
            }
        L1c:
            goto L2e
        L1d:
            r0 = 1
            goto L2e
        L1f:
            int r2 = r2.getSubtype()
            switch(r2) {
                case 1: goto L2b;
                case 2: goto L2b;
                case 3: goto L29;
                case 4: goto L2b;
                case 5: goto L29;
                case 6: goto L29;
                case 7: goto L2b;
                case 8: goto L29;
                case 9: goto L29;
                case 10: goto L29;
                case 11: goto L2b;
                case 12: goto L29;
                case 13: goto L27;
                case 14: goto L29;
                case 15: goto L29;
                default: goto L26;
            }
        L26:
            goto L2e
        L27:
            r0 = 4
            goto L2e
        L29:
            r0 = 3
            goto L2e
        L2b:
            r0 = 2
            goto L2e
        L2d:
            r0 = 0
        L2e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: layaair.game.network.NetworkReceiver.a(android.content.Context):int");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(PushConsts.ACTION_BROADCAST_NETWORK_CHANGE)) {
            Log.i("0", ">>>>>>>>>>>>>The network has changed");
            int iA = a(context);
            Log.i("0", "connected type=" + iA);
            ConchJNI.networkChanged(iA);
        }
    }
}
