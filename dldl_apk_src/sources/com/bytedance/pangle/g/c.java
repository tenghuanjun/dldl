package com.bytedance.pangle.g;

import android.util.ArrayMap;
import android.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c {

    static class a extends Exception {
    }

    static C0019c a(RandomAccessFile randomAccessFile, m mVar) {
        ArrayMap arrayMap = new ArrayMap();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            try {
                ByteBuffer byteBufferA = f.a(mVar.a);
                int i = 0;
                C0019c c0019cA = null;
                while (byteBufferA.hasRemaining()) {
                    try {
                        c0019cA = a(f.a(byteBufferA), arrayMap, certificateFactory);
                        i++;
                    } catch (a unused) {
                    } catch (IOException e) {
                        e = e;
                        throw new SecurityException("Failed to parse/verify signer #" + i + " block", e);
                    } catch (SecurityException e2) {
                        e = e2;
                        throw new SecurityException("Failed to parse/verify signer #" + i + " block", e);
                    } catch (BufferUnderflowException e3) {
                        e = e3;
                        throw new SecurityException("Failed to parse/verify signer #" + i + " block", e);
                    }
                }
                if (i <= 0 || c0019cA == null) {
                    throw new SecurityException("No signers found");
                }
                if (i != 1) {
                    throw new SecurityException("APK Signature Scheme V3 only supports one signer: multiple signers found.");
                }
                if (arrayMap.isEmpty()) {
                    throw new SecurityException("No content digests found");
                }
                f.a(arrayMap, randomAccessFile, mVar);
                if (arrayMap.containsKey(3)) {
                    c0019cA.c = f.a((byte[]) arrayMap.get(3), randomAccessFile.length(), mVar);
                }
                return c0019cA;
            } catch (IOException e4) {
                throw new SecurityException("Failed to read list of signers", e4);
            }
        } catch (CertificateException e5) {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e5);
        }
    }

    private static C0019c a(ByteBuffer byteBuffer, Map<Integer, byte[]> map, CertificateFactory certificateFactory) throws IOException {
        ByteBuffer byteBufferA = f.a(byteBuffer);
        int i = byteBuffer.getInt();
        int i2 = byteBuffer.getInt();
        ByteBuffer byteBufferA2 = f.a(byteBuffer);
        byte[] bArrB = f.b(byteBuffer);
        ArrayList arrayList = new ArrayList();
        int i3 = -1;
        int i4 = 0;
        byte[] bArrB2 = null;
        while (true) {
            int i5 = 8;
            boolean z = true;
            if (!byteBufferA2.hasRemaining()) {
                if (i3 == -1) {
                    if (i4 == 0) {
                        throw new SecurityException("No signatures found");
                    }
                    throw new SecurityException("No supported signatures found");
                }
                String strC = f.c(i3);
                Pair<String, ? extends AlgorithmParameterSpec> pairD = f.d(i3);
                String str = (String) pairD.first;
                AlgorithmParameterSpec algorithmParameterSpec = (AlgorithmParameterSpec) pairD.second;
                try {
                    PublicKey publicKeyGeneratePublic = KeyFactory.getInstance(strC).generatePublic(new X509EncodedKeySpec(bArrB));
                    Signature signature = Signature.getInstance(str);
                    signature.initVerify(publicKeyGeneratePublic);
                    if (algorithmParameterSpec != null) {
                        signature.setParameter(algorithmParameterSpec);
                    }
                    signature.update(byteBufferA);
                    if (!signature.verify(bArrB2)) {
                        throw new SecurityException(str + " signature did not verify");
                    }
                    byteBufferA.clear();
                    ByteBuffer byteBufferA3 = f.a(byteBufferA);
                    ArrayList arrayList2 = new ArrayList();
                    byte[] bArrB3 = null;
                    int i6 = 0;
                    while (byteBufferA3.hasRemaining()) {
                        i6++;
                        try {
                            ByteBuffer byteBufferA4 = f.a(byteBufferA3);
                            if (byteBufferA4.remaining() < i5) {
                                throw new IOException("Record too short");
                            }
                            int i7 = byteBufferA4.getInt();
                            arrayList2.add(Integer.valueOf(i7));
                            if (i7 == i3) {
                                bArrB3 = f.b(byteBufferA4);
                            }
                            i5 = 8;
                        } catch (IOException | BufferUnderflowException e) {
                            throw new IOException("Failed to parse digest record #".concat(String.valueOf(i6)), e);
                        }
                    }
                    if (!arrayList.equals(arrayList2)) {
                        throw new SecurityException("Signature algorithms don't match between digests and signatures records");
                    }
                    int iA = f.a(i3);
                    byte[] bArrPut = map.put(Integer.valueOf(iA), bArrB3);
                    if (bArrPut != null && !MessageDigest.isEqual(bArrPut, bArrB3)) {
                        throw new SecurityException(f.b(iA) + " contents digest does not match the digest specified by a preceding signer");
                    }
                    ByteBuffer byteBufferA5 = f.a(byteBufferA);
                    ArrayList arrayList3 = new ArrayList();
                    int i8 = 0;
                    while (byteBufferA5.hasRemaining()) {
                        i8++;
                        byte[] bArrB4 = f.b(byteBufferA5);
                        try {
                            arrayList3.add(new p((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(bArrB4)), bArrB4));
                        } catch (CertificateException e2) {
                            throw new SecurityException("Failed to decode certificate #".concat(String.valueOf(i8)), e2);
                        }
                    }
                    if (arrayList3.isEmpty()) {
                        throw new SecurityException("No certificates listed");
                    }
                    if (!Arrays.equals(bArrB, ((X509Certificate) arrayList3.get(0)).getPublicKey().getEncoded())) {
                        throw new SecurityException("Public key mismatch between certificate and signature record");
                    }
                    if (byteBufferA.getInt() != i) {
                        throw new SecurityException("minSdkVersion mismatch between signed and unsigned in v3 signer block.");
                    }
                    if (byteBufferA.getInt() != i2) {
                        throw new SecurityException("maxSdkVersion mismatch between signed and unsigned in v3 signer block.");
                    }
                    return a(f.a(byteBufferA), arrayList3, certificateFactory);
                } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e3) {
                    throw new SecurityException("Failed to verify " + str + " signature", e3);
                }
            }
            i4++;
            try {
                ByteBuffer byteBufferA6 = f.a(byteBufferA2);
                if (byteBufferA6.remaining() < 8) {
                    throw new SecurityException("Signature record too short");
                }
                int i9 = byteBufferA6.getInt();
                arrayList.add(Integer.valueOf(i9));
                if (i9 != 513 && i9 != 514 && i9 != 769 && i9 != 1057 && i9 != 1059 && i9 != 1061) {
                    switch (i9) {
                        case 257:
                        case 258:
                        case 259:
                        case 260:
                            break;
                        default:
                            z = false;
                            break;
                    }
                }
                if (z && (i3 == -1 || f.a(i9, i3) > 0)) {
                    bArrB2 = f.b(byteBufferA6);
                    i3 = i9;
                }
            } catch (IOException | BufferUnderflowException e4) {
                throw new SecurityException("Failed to parse signature record #".concat(String.valueOf(i4)), e4);
            }
        }
    }

    private static C0019c a(ByteBuffer byteBuffer, List<X509Certificate> list, CertificateFactory certificateFactory) throws IOException {
        X509Certificate[] x509CertificateArr = (X509Certificate[]) list.toArray(new X509Certificate[list.size()]);
        b bVarA = null;
        while (byteBuffer.hasRemaining()) {
            ByteBuffer byteBufferA = f.a(byteBuffer);
            if (byteBufferA.remaining() < 4) {
                throw new IOException("Remaining buffer too short to contain additional attribute ID. Remaining: " + byteBufferA.remaining());
            }
            if (byteBufferA.getInt() == 1000370060) {
                if (bVarA != null) {
                    throw new SecurityException("Encountered multiple Proof-of-rotation records when verifying APK Signature Scheme v3 signature");
                }
                bVarA = a(byteBufferA, certificateFactory);
                try {
                    if (bVarA.a.size() > 0 && !Arrays.equals(bVarA.a.get(bVarA.a.size() - 1).getEncoded(), x509CertificateArr[0].getEncoded())) {
                        throw new SecurityException("Terminal certificate in Proof-of-rotation record does not match APK signing certificate");
                    }
                } catch (CertificateEncodingException e) {
                    throw new SecurityException("Failed to encode certificate when comparing Proof-of-rotation record and signing certificate", e);
                }
            }
        }
        return new C0019c(x509CertificateArr, bVarA);
    }

    private static b a(ByteBuffer byteBuffer, CertificateFactory certificateFactory) throws IOException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        try {
            byteBuffer.getInt();
            HashSet hashSet = new HashSet();
            int i2 = -1;
            p pVar = null;
            while (byteBuffer.hasRemaining()) {
                i++;
                ByteBuffer byteBufferA = f.a(byteBuffer);
                ByteBuffer byteBufferA2 = f.a(byteBufferA);
                int i3 = byteBufferA.getInt();
                int i4 = byteBufferA.getInt();
                byte[] bArrB = f.b(byteBufferA);
                if (pVar != null) {
                    Pair<String, ? extends AlgorithmParameterSpec> pairD = f.d(i2);
                    PublicKey publicKey = pVar.getPublicKey();
                    Signature signature = Signature.getInstance((String) pairD.first);
                    signature.initVerify(publicKey);
                    if (pairD.second != null) {
                        signature.setParameter((AlgorithmParameterSpec) pairD.second);
                    }
                    signature.update(byteBufferA2);
                    if (!signature.verify(bArrB)) {
                        throw new SecurityException("Unable to verify signature of certificate #" + i + " using " + ((String) pairD.first) + " when verifying Proof-of-rotation record");
                    }
                }
                byteBufferA2.rewind();
                byte[] bArrB2 = f.b(byteBufferA2);
                int i5 = byteBufferA2.getInt();
                if (pVar != null && i2 != i5) {
                    throw new SecurityException("Signing algorithm ID mismatch for certificate #" + i + " when verifying Proof-of-rotation record");
                }
                pVar = new p((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(bArrB2)), bArrB2);
                if (hashSet.contains(pVar)) {
                    throw new SecurityException("Encountered duplicate entries in Proof-of-rotation record at certificate #" + i + ".  All signing certificates should be unique");
                }
                hashSet.add(pVar);
                arrayList.add(pVar);
                arrayList2.add(Integer.valueOf(i3));
                i2 = i4;
            }
            return new b(arrayList, arrayList2);
        } catch (IOException e) {
            e = e;
            throw new IOException("Failed to parse Proof-of-rotation record", e);
        } catch (BufferUnderflowException e2) {
            e = e2;
            throw new IOException("Failed to parse Proof-of-rotation record", e);
        } catch (InvalidAlgorithmParameterException e3) {
            e = e3;
            throw new SecurityException("Failed to verify signature over signed data for certificate #0 when verifying Proof-of-rotation record", e);
        } catch (InvalidKeyException e4) {
            e = e4;
            throw new SecurityException("Failed to verify signature over signed data for certificate #0 when verifying Proof-of-rotation record", e);
        } catch (NoSuchAlgorithmException e5) {
            e = e5;
            throw new SecurityException("Failed to verify signature over signed data for certificate #0 when verifying Proof-of-rotation record", e);
        } catch (SignatureException e6) {
            e = e6;
            throw new SecurityException("Failed to verify signature over signed data for certificate #0 when verifying Proof-of-rotation record", e);
        } catch (CertificateException e7) {
            throw new SecurityException("Failed to decode certificate #0 when verifying Proof-of-rotation record", e7);
        }
    }

    public static class b {
        public final List<X509Certificate> a;
        public final List<Integer> b;

        public b(List<X509Certificate> list, List<Integer> list2) {
            this.a = list;
            this.b = list2;
        }
    }

    /* JADX INFO: renamed from: com.bytedance.pangle.g.c$c, reason: collision with other inner class name */
    public static class C0019c {
        public final X509Certificate[] a;
        public final b b;
        public byte[] c;

        public C0019c(X509Certificate[] x509CertificateArr, b bVar) {
            this.a = x509CertificateArr;
            this.b = bVar;
        }
    }
}
