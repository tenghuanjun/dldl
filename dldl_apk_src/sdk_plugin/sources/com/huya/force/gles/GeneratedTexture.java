package com.huya.force.gles;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GeneratedTexture {
    private static final int BLACK = 0;
    private static final int BLUE = 16711680;
    private static final int BYTES_PER_PIXEL = 4;
    private static final int CYAN = 16776960;
    private static final int FORMAT = 6408;
    private static final int GREEN = 65280;
    private static final int HALF = Integer.MIN_VALUE;
    private static final int LOW = 1073741824;
    private static final int MAGENTA = 16711935;
    private static final int OPAQUE = -16777216;
    private static final int RED = 255;
    private static final int TEX_SIZE = 64;
    private static final int TRANSP = 0;
    private static final int WHITE = 16777215;
    private static final int YELLOW = 65535;
    private static final int[] GRID = {-16776961, -16711681, -16711936, -65281, -1, 1073742079, 1073807104, -16711681, -65281, 65280, -2147483393, -16777216, InputDeviceCompat.SOURCE_ANY, -65281, InputDeviceCompat.SOURCE_ANY, SupportMenu.CATEGORY_MASK};
    private static final ByteBuffer sCoarseImageData = generateCoarseData();
    private static final ByteBuffer sFineImageData = generateFineData();

    public enum Image {
        COARSE,
        FINE
    }

    /* JADX INFO: renamed from: com.huya.force.gles.GeneratedTexture$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$huya$force$gles$GeneratedTexture$Image;

        static {
            int[] iArr = new int[Image.values().length];
            $SwitchMap$com$huya$force$gles$GeneratedTexture$Image = iArr;
            try {
                iArr[Image.COARSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$huya$force$gles$GeneratedTexture$Image[Image.FINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static int createTestTexture(Image image) {
        ByteBuffer byteBuffer;
        int i = AnonymousClass1.$SwitchMap$com$huya$force$gles$GeneratedTexture$Image[image.ordinal()];
        if (i == 1) {
            byteBuffer = sCoarseImageData;
        } else if (i == 2) {
            byteBuffer = sFineImageData;
        } else {
            throw new RuntimeException("unknown image");
        }
        return GlUtil.createImageTexture(byteBuffer, 64, 64, FORMAT);
    }

    private static ByteBuffer generateCoarseData() {
        byte[] bArr = new byte[16384];
        for (int i = 0; i < 16384; i += 4) {
            int i2 = i / 4;
            int i3 = GRID[(((i2 / 64) / 16) * 4) + ((i2 % 64) / 16)];
            if (i == 0 || i == 16380) {
                i3 = -1;
            }
            int i4 = i3 & 255;
            int i5 = (i3 >> 8) & 255;
            int i6 = (i3 >> 16) & 255;
            int i7 = (i3 >> 24) & 255;
            float f = i7 / 255.0f;
            bArr[i] = (byte) (i4 * f);
            bArr[i + 1] = (byte) (i5 * f);
            bArr[i + 2] = (byte) (i6 * f);
            bArr[i + 3] = (byte) i7;
        }
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(16384);
        byteBufferAllocateDirect.put(bArr);
        byteBufferAllocateDirect.position(0);
        return byteBufferAllocateDirect;
    }

    private static ByteBuffer generateFineData() {
        byte[] bArr = new byte[16384];
        checkerPattern(bArr, 0, 0, 32, 32, -16776961, SupportMenu.CATEGORY_MASK, 1);
        checkerPattern(bArr, 32, 32, 64, 64, -16776961, -16711936, 2);
        checkerPattern(bArr, 0, 32, 32, 64, SupportMenu.CATEGORY_MASK, -16711936, 4);
        checkerPattern(bArr, 32, 0, 64, 32, -1, -16777216, 8);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(16384);
        byteBufferAllocateDirect.put(bArr);
        byteBufferAllocateDirect.position(0);
        return byteBufferAllocateDirect;
    }

    private static void checkerPattern(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        for (int i8 = i2; i8 < i4; i8++) {
            int i9 = i8 * 64 * 4;
            for (int i10 = i; i10 < i3; i10++) {
                int i11 = (i10 * 4) + i9;
                int i12 = ((i8 & i7) ^ (i10 & i7)) == 0 ? i5 : i6;
                int i13 = i12 & 255;
                int i14 = (i12 >> 8) & 255;
                int i15 = (i12 >> 16) & 255;
                int i16 = (i12 >> 24) & 255;
                float f = i16 / 255.0f;
                bArr[i11] = (byte) (i13 * f);
                bArr[i11 + 1] = (byte) (i14 * f);
                bArr[i11 + 2] = (byte) (i15 * f);
                bArr[i11 + 3] = (byte) i16;
            }
        }
    }
}
