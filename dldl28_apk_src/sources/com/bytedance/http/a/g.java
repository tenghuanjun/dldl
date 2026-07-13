package com.bytedance.http.a;

import com.bytedance.http.Call;
import com.bytedance.http.HttpDispatcher;
import com.bytedance.http.HttpRequest;
import com.bytedance.http.HttpResponse;
import com.bytedance.http.Interceptor;
import com.bytedance.http.Scheduler;
import com.bytedance.http.WebSocket;
import com.bytedance.http.WebSocketListener;
import com.bytedance.http.b.h;
import com.bytedance.http.b.i;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CancellationException;
import okhttp3.internal.ws.WebSocketProtocol;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes2.dex */
public class g implements Interceptor {
    private final Queue c;
    private final Scheduler d;
    private h e;
    private BufferedInputStream f;
    private BufferedOutputStream g;
    private WebSocketListener h;
    private HttpRequest i;
    private WebSocket j;
    private int n;
    private String o;
    private volatile int k = 0;
    private volatile boolean l = false;
    private volatile boolean m = false;
    private final SecureRandom b = new SecureRandom();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f387a = new Object();

    class a extends com.bytedance.http.b.d {
        public a() {
            super("websocket");
        }

        /* JADX WARN: Code restructure failed: missing block: B:43:0x0167, code lost:
        
            return;
         */
        /* JADX WARN: Removed duplicated region for block: B:19:0x004a A[Catch: all -> 0x0168, LOOP:1: B:19:0x004a->B:38:0x015c, LOOP_START, PHI: r3
  0x004a: PHI (r3v1 int) = (r3v0 int), (r3v2 int) binds: [B:15:0x0041, B:38:0x015c] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {, blocks: (B:4:0x0009, B:6:0x0012, B:8:0x001b, B:10:0x0023, B:14:0x0031, B:16:0x0043, B:17:0x0048, B:19:0x004a, B:21:0x0056, B:27:0x0076, B:32:0x010a, B:34:0x0130, B:35:0x013f, B:37:0x0157, B:30:0x008e, B:31:0x00b1, B:24:0x006e, B:39:0x015f, B:40:0x0164, B:13:0x002e), top: B:47:0x0009, inners: #1, #2 }] */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0043 A[SYNTHETIC] */
        @Override // com.bytedance.http.b.d
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        protected final void a() {
            /*
                Method dump skipped, instruction units count: 363
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bytedance.http.a.g.a.a():void");
        }

        @Override // com.bytedance.http.b.d
        public final Call b() {
            return g.this.j;
        }
    }

    public g(HttpDispatcher httpDispatcher) {
        this.d = httpDispatcher.dispatcher();
        this.c = new ArrayBlockingQueue(httpDispatcher.payloadQueueSize());
    }

    private static void a(InputStream inputStream, String str) throws IOException {
        int i;
        LinkedList linkedList = new LinkedList();
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        boolean z = false;
        while (true) {
            int i3 = inputStream.read();
            if (i3 == -1) {
                throw new i(-19, "Unexpected end of stream");
            }
            char c = (char) i3;
            i = i2 + 1;
            if (c == '\r') {
                int i4 = inputStream.read();
                if (i4 == -1) {
                    throw new i(-19, "Unexpected end of stream");
                }
                i = i2 + 2;
                if (((char) i4) != '\n') {
                    throw new i(-19, "Invalid handshake format");
                }
                if (z) {
                    break;
                }
                i2 = i;
                z = true;
            } else if (c == '\n') {
                if (z) {
                    break;
                }
                i2 = i;
                z = true;
            } else {
                sb.append(c);
                i2 = i;
                z = false;
                if (i <= 16392) {
                    continue;
                }
            }
            linkedList.offer(sb.toString());
            sb.setLength(0);
            if (i2 > 16392) {
                i = i2;
                break;
            }
        }
        if (i > 16392) {
            throw new i(-16, "Entity too large");
        }
        String str2 = (String) linkedList.poll();
        if (str2 == null) {
            throw new i(-19, "There is no status line");
        }
        String[] strArrSplit = str2.split(StringUtils.SPACE);
        if (strArrSplit.length <= 1) {
            throw new i(-19, "Invalid status line format");
        }
        String str3 = strArrSplit[1];
        if (!str3.equals("101")) {
            throw new i(-19, "Invalid status code. Expected 101, received: " + str3);
        }
        HashMap map = new HashMap();
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            String[] strArrSplit2 = ((String) it.next()).split(":", 2);
            if (strArrSplit2.length != 2) {
                throw new i(-19, "Invalid headers format");
            }
            map.put(strArrSplit2[0].trim().toLowerCase(), strArrSplit2[1].trim());
        }
        String str4 = (String) map.get("upgrade");
        if (str4 == null) {
            throw new i(-19, "There is no header named Upgrade");
        }
        String lowerCase = str4.toLowerCase();
        if (!lowerCase.equals("websocket")) {
            throw new i(-19, "Invalid value for header Upgrade. Expected: websocket, received: " + lowerCase);
        }
        String str5 = (String) map.get("connection");
        if (str5 == null) {
            throw new i(-19, "There is no header named Connection");
        }
        String lowerCase2 = str5.toLowerCase();
        if (!lowerCase2.equals("upgrade")) {
            throw new i(-19, "Invalid value for header Connection. Expected: upgrade, received: " + lowerCase2);
        }
        String str6 = (String) map.get("sec-websocket-accept");
        if (str6 == null) {
            throw new i(-19, "There is no header named Sec-WebSocket-Accept");
        }
        String str7 = str + WebSocketProtocol.ACCEPT_MAGIC;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(str7.getBytes(StandardCharsets.US_ASCII));
            String strB = com.bytedance.http.b.g.b(messageDigest.digest());
            if (str6.equals(strB)) {
                return;
            }
            throw new i(-19, "Invalid value for header Sec-WebSocket-Accept. Expected: " + strB + ", received: " + str6);
        } catch (NoSuchAlgorithmException unused) {
            throw new i(-16, "Your platform does not support the SHA-1 algorithm");
        }
    }

    static /* synthetic */ boolean a(g gVar, boolean z) {
        gVar.l = false;
        return false;
    }

    private boolean a(com.bytedance.http.b.g gVar) {
        synchronized (this.f387a) {
            if (this.k == 3) {
                return false;
            }
            if (gVar.a() == 8) {
                this.k = 3;
            }
            boolean zOffer = this.c.offer(gVar);
            if (zOffer) {
                this.l = true;
                this.f387a.notify();
            } else {
                synchronized (this.f387a) {
                    if (this.h != null) {
                        this.h.onDiagnosis(this.j, new HttpResponse.Builder().code(-18).message("Payload queue is full").request(this.i).build());
                    }
                }
            }
            return zOffer;
        }
    }

    private static byte[] a(URI uri, Map map, String str) {
        StringBuilder sb = new StringBuilder();
        String rawPath = uri.getRawPath();
        String rawQuery = uri.getRawQuery();
        if (rawPath == null || rawPath.isEmpty()) {
            rawPath = "/";
        }
        if (rawQuery != null && !rawQuery.isEmpty()) {
            rawPath = rawPath + "?" + rawQuery;
        }
        sb.append("GET " + rawPath + " HTTP/1.1");
        sb.append("\r\n");
        sb.append("Host: " + (uri.getPort() == -1 ? uri.getHost() : uri.getHost() + ":" + uri.getPort()));
        sb.append("\r\nUpgrade: websocket\r\nConnection: Upgrade\r\n");
        sb.append("Sec-WebSocket-Key: " + str);
        sb.append("\r\nSec-WebSocket-Version: 13\r\n");
        for (Map.Entry entry : map.entrySet()) {
            sb.append(((String) entry.getKey()) + ": " + ((String) entry.getValue()));
            sb.append("\r\n");
        }
        sb.append("\r\n");
        String string = sb.toString();
        com.bytedance.http.b.c.a("handshake - " + string);
        return string.getBytes(StandardCharsets.US_ASCII);
    }

    private void e() throws IOException {
        int iA;
        LinkedList<byte[]> linkedList = new LinkedList();
        int i = -1;
        int i2 = -1;
        while (true) {
            int i3 = this.f.read();
            if (i3 == i) {
                synchronized (this.f387a) {
                    if (this.k != 3) {
                        throw new IOException("Unexpected end of stream");
                    }
                }
                return;
            }
            int i4 = (i3 << 24) >>> 31;
            int i5 = (i3 << 28) >>> 28;
            if (i4 == 0 && i2 == i) {
                i2 = i5;
            }
            int iA2 = (this.f.read() << 25) >>> 25;
            if (iA2 == 126) {
                byte[] bArr = new byte[2];
                for (int i6 = 0; i6 < 2; i6++) {
                    bArr[i6] = (byte) this.f.read();
                }
                iA2 = com.bytedance.http.b.g.a(new byte[]{0, 0, bArr[0], bArr[1]});
            } else if (iA2 == 127) {
                byte[] bArr2 = new byte[8];
                for (int i7 = 0; i7 < 8; i7++) {
                    bArr2[i7] = (byte) this.f.read();
                }
                iA2 = com.bytedance.http.b.g.a(new byte[]{bArr2[4], bArr2[5], bArr2[6], bArr2[7]});
            }
            byte[] bArr3 = new byte[iA2];
            for (int i8 = 0; i8 < iA2; i8++) {
                bArr3[i8] = (byte) this.f.read();
            }
            if (i4 == 1 && i5 == 0) {
                linkedList.add(bArr3);
                Iterator it = linkedList.iterator();
                int length = 0;
                while (it.hasNext()) {
                    length += ((byte[]) it.next()).length;
                }
                bArr3 = new byte[length];
                int length2 = 0;
                for (byte[] bArr4 : linkedList) {
                    System.arraycopy(bArr4, 0, bArr3, length2, bArr4.length);
                    length2 += bArr4.length;
                }
                linkedList.clear();
                i5 = i2;
                i2 = -1;
            } else {
                if (i4 == 0 && (i5 == 0 || i5 == 1 || i5 == 2)) {
                    linkedList.add(bArr3);
                }
                i = -1;
            }
            if (i5 == 1) {
                String str = new String(bArr3, Charset.forName("UTF-8"));
                synchronized (this.f387a) {
                    WebSocketListener webSocketListener = this.h;
                    if (webSocketListener != null) {
                        webSocketListener.onMessage(this.j, str);
                    }
                }
            } else if (i5 != 2) {
                switch (i5) {
                    case 8:
                        if (bArr3.length > 125) {
                            f();
                            throw new i(-17, "Close frame payload is too big");
                        }
                        if (bArr3.length > 1) {
                            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr3, 0, 2);
                            iA = com.bytedance.http.b.g.a(new byte[]{0, 0, bArrCopyOfRange[0], bArrCopyOfRange[1]});
                        } else {
                            iA = -1;
                        }
                        this.n = iA;
                        String str2 = bArr3.length > 2 ? new String(Arrays.copyOfRange(bArr3, 2, bArr3.length), StandardCharsets.UTF_8) : null;
                        this.o = str2;
                        int i9 = this.n;
                        synchronized (this.f387a) {
                            if (this.k == 2) {
                                this.k = 3;
                                WebSocketListener webSocketListener2 = this.h;
                                if (webSocketListener2 != null) {
                                    webSocketListener2.onClosing(this.j, i9, str2);
                                }
                            }
                        }
                        synchronized (this.f387a) {
                            if (this.k == 3) {
                                g();
                                return;
                            }
                            a(new com.bytedance.http.b.g(8, bArr3, true));
                        }
                        break;
                        break;
                    case 9:
                        if (bArr3.length > 125) {
                            throw new IllegalArgumentException("Control frame payload cannot be greater than 125 bytes");
                        }
                        a(new com.bytedance.http.b.g(10, bArr3, false));
                        break;
                        break;
                    case 10:
                        break;
                    default:
                        f();
                        throw new IOException("Unknown opcode: 0x" + Integer.toHexString(i5));
                }
            } else {
                synchronized (this.f387a) {
                    WebSocketListener webSocketListener3 = this.h;
                    if (webSocketListener3 != null) {
                        webSocketListener3.onMessage(this.j, bArr3);
                    }
                }
            }
            i = -1;
        }
    }

    private void f() {
        synchronized (this.f387a) {
            g();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.l = true;
        this.f387a.notify();
    }

    private void h() {
        if (this.m) {
            throw new CancellationException("Canceled");
        }
    }

    public final void a() {
        this.m = true;
        com.bytedance.http.b.g.a(this.f);
        com.bytedance.http.b.g.a(this.g);
        h hVar = this.e;
        if (hVar != null) {
            hVar.c();
        }
    }

    public final boolean a(String str) {
        if (this.k != 2) {
            return false;
        }
        return a(new com.bytedance.http.b.g(1, str.getBytes(StandardCharsets.UTF_8), false));
    }

    public final boolean a(byte[] bArr) {
        if (this.k != 2) {
            return false;
        }
        return a(new com.bytedance.http.b.g(2, bArr, false));
    }

    public final boolean b() {
        return this.m;
    }

    public final boolean c() {
        return this.k == 2;
    }

    public final int d() {
        return this.c.size();
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x015f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x013f A[PHI: r1
  0x013f: PHI (r1v18 boolean) = (r1v14 boolean), (r1v15 boolean), (r1v16 boolean), (r1v17 boolean), (r1v5 boolean) binds: [B:41:0x00ec, B:52:0x0106, B:73:0x013d, B:62:0x0123, B:18:0x00c4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0158  */
    @Override // com.bytedance.http.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.bytedance.http.HttpResponse intercept(com.bytedance.http.Interceptor.Chain r10) {
        /*
            Method dump skipped, instruction units count: 444
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.http.a.g.intercept(com.bytedance.http.Interceptor$Chain):com.bytedance.http.HttpResponse");
    }

    @Override // com.bytedance.http.Interceptor
    public String name() {
        return "ws_int";
    }
}
