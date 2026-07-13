package com.shuyu.gsyvideoplayer.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.KotlinVersion;
import kotlin.UByte;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class AnimatedGifEncoder {
    protected int colorDepth;
    protected byte[] colorTab;
    protected int height;
    protected Bitmap image;
    protected byte[] indexedPixels;
    protected OutputStream out;
    protected byte[] pixels;
    protected int transIndex;
    protected int width;
    protected int x = 0;
    protected int y = 0;
    protected int transparent = -1;
    protected int repeat = -1;
    protected int delay = 0;
    protected boolean started = false;
    protected boolean[] usedEntry = new boolean[256];
    protected int palSize = 7;
    protected int dispose = -1;
    protected boolean closeStream = false;
    protected boolean firstFrame = true;
    protected boolean sizeSet = false;
    protected int sample = 10;

    public void setDelay(int i) {
        this.delay = i / 10;
    }

    public void setDispose(int i) {
        if (i >= 0) {
            this.dispose = i;
        }
    }

    public void setRepeat(int i) {
        if (i >= 0) {
            this.repeat = i;
        }
    }

    public void setTransparent(int i) {
        this.transparent = i;
    }

    public boolean addFrame(Bitmap bitmap) {
        if (bitmap == null || !this.started) {
            return false;
        }
        try {
            if (!this.sizeSet) {
                setSize(bitmap.getWidth(), bitmap.getHeight());
            }
            this.image = bitmap;
            getImagePixels();
            analyzePixels();
            if (this.firstFrame) {
                writeLSD();
                writePalette();
                if (this.repeat >= 0) {
                    writeNetscapeExt();
                }
            }
            writeGraphicCtrlExt();
            writeImageDesc();
            if (!this.firstFrame) {
                writePalette();
            }
            writePixels();
            this.firstFrame = false;
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public boolean finish() {
        boolean z;
        if (!this.started) {
            return false;
        }
        this.started = false;
        try {
            this.out.write(59);
            this.out.flush();
            if (this.closeStream) {
                this.out.close();
            }
            z = true;
        } catch (IOException unused) {
            z = false;
        }
        this.transIndex = 0;
        this.out = null;
        this.image = null;
        this.pixels = null;
        this.indexedPixels = null;
        this.colorTab = null;
        this.closeStream = false;
        this.firstFrame = true;
        return z;
    }

    public void setFrameRate(float f) {
        if (f != 0.0f) {
            this.delay = (int) (100.0f / f);
        }
    }

    public void setQuality(int i) {
        if (i < 1) {
            i = 1;
        }
        this.sample = i;
    }

    public void setSize(int i, int i2) {
        this.width = i;
        this.height = i2;
        if (i < 1) {
            this.width = 320;
        }
        if (i2 < 1) {
            this.height = KeyBoardKey.KeyboardKeyOemAttn;
        }
        this.sizeSet = true;
    }

    public void setPosition(int i, int i2) {
        this.x = i;
        this.y = i2;
    }

    public boolean start(OutputStream outputStream) {
        boolean z = false;
        if (outputStream == null) {
            return false;
        }
        this.closeStream = false;
        this.out = outputStream;
        try {
            writeString("GIF89a");
            z = true;
        } catch (IOException unused) {
        }
        this.started = z;
        return z;
    }

    protected void analyzePixels() {
        byte[] bArr = this.pixels;
        int length = bArr.length;
        int i = length / 3;
        this.indexedPixels = new byte[i];
        NeuQuant neuQuant = new NeuQuant(bArr, length, this.sample);
        this.colorTab = neuQuant.process();
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.colorTab;
            if (i2 >= bArr2.length) {
                break;
            }
            byte b = bArr2[i2];
            int i3 = i2 + 2;
            bArr2[i2] = bArr2[i3];
            bArr2[i3] = b;
            this.usedEntry[i2 / 3] = false;
            i2 += 3;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            byte[] bArr3 = this.pixels;
            int i6 = bArr3[i4] & UByte.MAX_VALUE;
            int i7 = i4 + 2;
            int i8 = bArr3[i4 + 1] & UByte.MAX_VALUE;
            i4 += 3;
            int map = neuQuant.map(i6, i8, bArr3[i7] & UByte.MAX_VALUE);
            this.usedEntry[map] = true;
            this.indexedPixels[i5] = (byte) map;
        }
        this.pixels = null;
        this.colorDepth = 8;
        this.palSize = 7;
        int i9 = this.transparent;
        if (i9 != -1) {
            this.transIndex = findClosest(i9);
        }
    }

    protected int findClosest(int i) {
        byte[] bArr = this.colorTab;
        if (bArr == null) {
            return -1;
        }
        int i2 = (i >> 16) & KotlinVersion.MAX_COMPONENT_VALUE;
        int i3 = (i >> 8) & KotlinVersion.MAX_COMPONENT_VALUE;
        int i4 = i & KotlinVersion.MAX_COMPONENT_VALUE;
        int length = bArr.length;
        int i5 = 0;
        int i6 = 16777216;
        for (int i7 = 0; i7 < length; i7 += 3) {
            byte[] bArr2 = this.colorTab;
            int i8 = i2 - (bArr2[i7] & UByte.MAX_VALUE);
            int i9 = i7 + 2;
            int i10 = i3 - (bArr2[i7 + 1] & UByte.MAX_VALUE);
            int i11 = i4 - (bArr2[i9] & UByte.MAX_VALUE);
            int i12 = (i8 * i8) + (i10 * i10) + (i11 * i11);
            int i13 = i9 / 3;
            if (this.usedEntry[i13] && i12 < i6) {
                i6 = i12;
                i5 = i13;
            }
        }
        return i5;
    }

    protected void getImagePixels() {
        int width = this.image.getWidth();
        int height = this.image.getHeight();
        int i = this.width;
        if (width != i || height != this.height) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, this.height, Bitmap.Config.RGB_565);
            new Canvas(bitmapCreateBitmap).drawBitmap(this.image, 0.0f, 0.0f, new Paint());
            this.image = bitmapCreateBitmap;
        }
        int[] imageData = getImageData(this.image);
        this.pixels = new byte[imageData.length * 3];
        for (int i2 = 0; i2 < imageData.length; i2++) {
            int i3 = imageData[i2];
            int i4 = i2 * 3;
            byte[] bArr = this.pixels;
            bArr[i4] = (byte) (i3 & KotlinVersion.MAX_COMPONENT_VALUE);
            bArr[i4 + 1] = (byte) ((i3 >> 8) & KotlinVersion.MAX_COMPONENT_VALUE);
            bArr[i4 + 2] = (byte) ((i3 >> 16) & KotlinVersion.MAX_COMPONENT_VALUE);
        }
    }

    protected int[] getImageData(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        return iArr;
    }

    protected void writeGraphicCtrlExt() throws IOException {
        int i;
        int i2;
        this.out.write(33);
        this.out.write(KeyBoardKey.KeyboardKeyErEof);
        this.out.write(4);
        if (this.transparent == -1) {
            i = 0;
            i2 = 0;
        } else {
            i = 1;
            i2 = 2;
        }
        int i3 = this.dispose;
        if (i3 >= 0) {
            i2 = i3 & 7;
        }
        this.out.write(i | (i2 << 2));
        writeShort(this.delay);
        this.out.write(this.transIndex);
        this.out.write(0);
    }

    protected void writeImageDesc() throws IOException {
        this.out.write(44);
        writeShort(this.x);
        writeShort(this.y);
        writeShort(this.width);
        writeShort(this.height);
        if (this.firstFrame) {
            this.out.write(0);
        } else {
            this.out.write(this.palSize | 128);
        }
    }

    protected void writeLSD() throws IOException {
        writeShort(this.width);
        writeShort(this.height);
        this.out.write(this.palSize | KeyBoardKey.KeyboardKeyOemAttn);
        this.out.write(0);
        this.out.write(0);
    }

    protected void writeNetscapeExt() throws IOException {
        this.out.write(33);
        this.out.write(KotlinVersion.MAX_COMPONENT_VALUE);
        this.out.write(11);
        writeString("NETSCAPE2.0");
        this.out.write(3);
        this.out.write(1);
        writeShort(this.repeat);
        this.out.write(0);
    }

    protected void writePalette() throws IOException {
        OutputStream outputStream = this.out;
        byte[] bArr = this.colorTab;
        outputStream.write(bArr, 0, bArr.length);
        int length = 768 - this.colorTab.length;
        for (int i = 0; i < length; i++) {
            this.out.write(0);
        }
    }

    protected void writePixels() throws IOException {
        new LZWEncoder(this.width, this.height, this.indexedPixels, this.colorDepth).encode(this.out);
    }

    protected void writeShort(int i) throws IOException {
        this.out.write(i & KotlinVersion.MAX_COMPONENT_VALUE);
        this.out.write((i >> 8) & KotlinVersion.MAX_COMPONENT_VALUE);
    }

    protected void writeString(String str) throws IOException {
        for (int i = 0; i < str.length(); i++) {
            this.out.write((byte) str.charAt(i));
        }
    }
}
