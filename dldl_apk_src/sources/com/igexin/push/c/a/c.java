package com.igexin.push.c.a;

import com.igexin.b.a.b.a.a.h;
import com.igexin.b.a.b.d;
import com.igexin.b.a.b.g;
import com.igexin.push.c.c.e;
import java.io.IOException;
import java.util.Arrays;
import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class c extends d {
    public static final String a = "com.igexin.push.c.a.c";
    public static int b = -1;
    private byte[] g;

    private c(String str) {
        super(str, (byte) 0);
    }

    private static byte a(h hVar) throws IOException {
        return (byte) b(hVar, 1);
    }

    public static d a() {
        c cVar = new c("socketProtocol");
        new a("command", cVar);
        return cVar;
    }

    private static e a(com.igexin.push.c.c.a aVar) {
        e eVar = new e();
        eVar.b = e.a;
        eVar.a(aVar.c);
        eVar.f = aVar.b > 0 ? 1 : 0;
        eVar.d = 7;
        eVar.c = 11;
        eVar.g = aVar.d;
        eVar.c += com.igexin.push.f.h.c().length;
        if (aVar.a <= 0) {
            if (eVar.i == 0) {
                eVar.p = 0;
            }
            com.igexin.b.a.b.e.c();
            return eVar;
        }
        eVar.q = com.igexin.push.f.h.e();
        eVar.r = (int) (System.currentTimeMillis() / 1000);
        eVar.o = com.igexin.push.f.h.a(aVar, eVar.q, eVar.r);
        eVar.p = eVar.o.length;
        eVar.c += eVar.p;
        com.igexin.b.a.b.e.c();
        return eVar;
    }

    private static Object a(h hVar, e eVar) throws Exception {
        StringBuilder sb;
        String str;
        if (eVar.i == 48) {
            return null;
        }
        byte b2 = (byte) b(hVar, 1);
        if (b2 > 0) {
            a(hVar, b2);
        }
        eVar.g = (byte) b(hVar, 1);
        eVar.p = (byte) b(hVar, 1);
        if (eVar.p > 0) {
            eVar.o = a(hVar, eVar.p);
        }
        if (eVar.f == 0) {
            com.igexin.push.c.c.a aVar = new com.igexin.push.c.c.a();
            aVar.f = eVar.d;
            aVar.b = (byte) 0;
            return aVar;
        }
        byte[] bArrA = a(hVar, 11);
        int iC = g.c(bArrA, 0);
        if (iC <= b) {
            b = -1;
            throw new Exception("server packetId can't be less than previous");
        }
        b = iC;
        int iC2 = g.c(bArrA, 4);
        short sA = g.a(bArrA, 8);
        int i = bArrA[10] & UByte.MAX_VALUE;
        com.igexin.push.c.c.a aVar2 = new com.igexin.push.c.c.a();
        aVar2.a = sA;
        aVar2.b = (byte) i;
        aVar2.f = eVar.d;
        aVar2.g = eVar.i;
        if (sA <= 0) {
            if (aVar2.a < 0) {
                sb = new StringBuilder();
                sb.append(a);
                str = "|data len < 0, error";
                sb.append(str);
                com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
                return null;
            }
            return aVar2;
        }
        byte[] bArrA2 = a(hVar, sA);
        if (eVar.i == 16) {
            bArrA2 = com.igexin.push.f.h.d(bArrA2, com.igexin.push.f.h.b(g.b(iC2)));
        } else if (eVar.i == 32) {
            if (i != 26) {
                return null;
            }
            bArrA2 = com.igexin.push.f.h.e(bArrA2, g.b(iC2));
        } else if (eVar.i != 0) {
            if (eVar.i == 48) {
            }
            return null;
        }
        if (eVar.h == -128) {
            bArrA2 = g.b(bArrA2);
        } else if (eVar.h != 0) {
            return null;
        }
        aVar2.a(bArrA2);
        if (!Arrays.equals(eVar.o, com.igexin.push.f.h.a(aVar2, iC, iC2))) {
            sb = new StringBuilder();
            sb.append(a);
            str = "|decode signature error!!!!";
            sb.append(str);
            com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
            return null;
        }
        return aVar2;
    }

    private static byte[] a(h hVar, int i) throws IOException {
        byte[] bArr = new byte[i];
        hVar.a(bArr);
        return bArr;
    }

    private static int b(h hVar, int i) throws IOException {
        byte[] bArrA = a(hVar, i);
        if (i == 1) {
            return bArrA[0] & UByte.MAX_VALUE;
        }
        if (i == 2) {
            return g.a(bArrA, 0);
        }
        if (i == 4) {
            return g.c(bArrA, 0);
        }
        return 0;
    }

    private Object b(h hVar, e eVar) throws Exception {
        byte b2;
        if (eVar.i == 48 && (b2 = (byte) b(hVar, 1)) > 0) {
            this.g = a(hVar, b2);
        }
        if (eVar.f == 0) {
            com.igexin.push.c.c.a aVar = new com.igexin.push.c.c.a();
            aVar.f = eVar.d;
            aVar.b = (byte) 0;
            return aVar;
        }
        byte[] bArrA = a(hVar, 3);
        short sA = g.a(bArrA, 0);
        int i = bArrA[2] & UByte.MAX_VALUE;
        com.igexin.push.c.c.a aVar2 = new com.igexin.push.c.c.a();
        aVar2.a = sA;
        aVar2.b = (byte) i;
        aVar2.f = eVar.d;
        if (i != 26) {
            return null;
        }
        if (aVar2.a > 0) {
            byte[] bArrA2 = a(hVar, sA);
            if (eVar.i == 48) {
                byte[] bArr = this.g;
                bArrA2 = com.igexin.b.a.a.a.a(bArrA2, bArr == null ? com.igexin.b.a.b.e.a().f : com.igexin.b.b.a.a(bArr));
            }
            if (eVar.h == -128) {
                bArrA2 = g.b(bArrA2);
            } else if (eVar.h != 0) {
                return null;
            }
            aVar2.a(bArrA2);
        }
        return aVar2;
    }

    private static short b(h hVar) throws IOException {
        return (short) b(hVar, 2);
    }

    private static int c(h hVar) throws IOException {
        return b(hVar, 4);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0190  */
    @Override // com.igexin.b.a.b.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(java.lang.Object r12) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.c.a.c.a(java.lang.Object):java.lang.Object");
    }

    @Override // com.igexin.b.a.b.d
    public final Object b(Object obj) throws Exception {
        StringBuilder sb;
        String str;
        com.igexin.push.c.c.a aVar;
        byte b2;
        h hVar = obj instanceof h ? (h) obj : null;
        if (hVar == null) {
            sb = new StringBuilder();
            sb.append(a);
            str = "|syncIns is null";
        } else {
            byte[] bArrA = a(hVar, 8);
            if (g.c(bArrA, 0) != 1944742139) {
                return null;
            }
            e eVar = new e();
            eVar.c = bArrA[4] & UByte.MAX_VALUE;
            eVar.d = bArrA[5] & UByte.MAX_VALUE;
            eVar.a(bArrA[6]);
            eVar.f = bArrA[7] & UByte.MAX_VALUE;
            if (eVar.d == 7) {
                if (eVar.i == 48) {
                    return null;
                }
                byte b3 = (byte) b(hVar, 1);
                if (b3 > 0) {
                    a(hVar, b3);
                }
                eVar.g = (byte) b(hVar, 1);
                eVar.p = (byte) b(hVar, 1);
                if (eVar.p > 0) {
                    eVar.o = a(hVar, eVar.p);
                }
                if (eVar.f == 0) {
                    aVar = new com.igexin.push.c.c.a();
                    aVar.f = eVar.d;
                    aVar.b = (byte) 0;
                    return aVar;
                }
                byte[] bArrA2 = a(hVar, 11);
                int iC = g.c(bArrA2, 0);
                if (iC <= b) {
                    b = -1;
                    throw new Exception("server packetId can't be less than previous");
                }
                b = iC;
                int iC2 = g.c(bArrA2, 4);
                short sA = g.a(bArrA2, 8);
                int i = bArrA2[10] & UByte.MAX_VALUE;
                com.igexin.push.c.c.a aVar2 = new com.igexin.push.c.c.a();
                aVar2.a = sA;
                aVar2.b = (byte) i;
                aVar2.f = eVar.d;
                aVar2.g = eVar.i;
                if (sA <= 0) {
                    if (aVar2.a < 0) {
                        sb = new StringBuilder();
                        sb.append(a);
                        str = "|data len < 0, error";
                    }
                    return aVar2;
                }
                byte[] bArrA3 = a(hVar, sA);
                if (eVar.i == 16) {
                    bArrA3 = com.igexin.push.f.h.d(bArrA3, com.igexin.push.f.h.b(g.b(iC2)));
                } else if (eVar.i == 32) {
                    if (i != 26) {
                        return null;
                    }
                    bArrA3 = com.igexin.push.f.h.e(bArrA3, g.b(iC2));
                } else if (eVar.i != 0) {
                    if (eVar.i == 48) {
                    }
                    return null;
                }
                if (eVar.h == -128) {
                    bArrA3 = g.b(bArrA3);
                } else if (eVar.h != 0) {
                    return null;
                }
                aVar2.a(bArrA3);
                if (!Arrays.equals(eVar.o, com.igexin.push.f.h.a(aVar2, iC, iC2))) {
                    sb = new StringBuilder();
                    sb.append(a);
                    str = "|decode signature error!!!!";
                }
                return aVar2;
            }
            if (eVar.d == 1) {
                if (eVar.i == 48 && (b2 = (byte) b(hVar, 1)) > 0) {
                    this.g = a(hVar, b2);
                }
                if (eVar.f == 0) {
                    aVar = new com.igexin.push.c.c.a();
                    aVar.f = eVar.d;
                    aVar.b = (byte) 0;
                    return aVar;
                }
                byte[] bArrA4 = a(hVar, 3);
                short sA2 = g.a(bArrA4, 0);
                int i2 = bArrA4[2] & UByte.MAX_VALUE;
                com.igexin.push.c.c.a aVar3 = new com.igexin.push.c.c.a();
                aVar3.a = sA2;
                aVar3.b = (byte) i2;
                aVar3.f = eVar.d;
                if (i2 != 26) {
                    return null;
                }
                if (aVar3.a > 0) {
                    byte[] bArrA5 = a(hVar, sA2);
                    if (eVar.i == 48) {
                        byte[] bArr = this.g;
                        bArrA5 = com.igexin.b.a.a.a.a(bArrA5, bArr == null ? com.igexin.b.a.b.e.a().f : com.igexin.b.b.a.a(bArr));
                    }
                    if (eVar.h == -128) {
                        bArrA5 = g.b(bArrA5);
                    } else if (eVar.h != 0) {
                        return null;
                    }
                    aVar3.a(bArrA5);
                }
                return aVar3;
            }
            sb = new StringBuilder();
            sb.append(a);
            sb.append("|server socket resp version = ");
            sb.append(eVar.d);
            str = ", not support !!!";
        }
        sb.append(str);
        com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
        return null;
    }
}
