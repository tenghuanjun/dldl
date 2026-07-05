package com.huya.force.imagefilter.beauty;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import com.huya.force.gles.Drawable2d;
import com.huya.force.gpuimage.GPUImageFilter;
import com.huya.force.gpuimage.util.GLShaderProgram;
import com.huya.force.gpuimage.util.GLTexture;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GPUBeautyNaturalFilter extends GPUImageFilter {
    public static final String LookUpTableShader = "precision mediump float;\nvarying vec2 vTexCoord;\nuniform sampler2D uTexture0;\nuniform sampler2D uTextureTable;\nuniform float uIntensity;\nuniform float uWhiteness;                      \nvoid main ()\n{\n    mediump float temp;\n    mediump vec2 pos1;\n    mediump vec2 pos2;\n    mediump vec2 lookPos1;\n    mediump vec2 lookPos2;                    \n    lowp vec4 temp0;\n    temp0 = texture2D (uTexture0, vTexCoord);\n    temp = (temp0.z * 15.0);                   \n    pos1.y = floor((floor(temp) / 4.0));\n    pos1.x = (floor(temp) - (pos1.y * 4.0));                   \n    pos2.y = floor((ceil(temp) / 4.0));\n    pos2.x = (ceil(temp) - (pos2.y * 4.0));\n\tlowp vec3 whiteColor = temp0.xyz;\n\twhiteColor = log(whiteColor * (uWhiteness - 1.0) + 1.0) / log(uWhiteness);\n    lookPos1 = (((pos1 * vec2(0.25, 0.25)) + vec2(0.0078125, 0.0078125)) + (vec2(0.234375, 0.234375) * whiteColor.xy));\n    lookPos2 = (((pos2 * vec2(0.25, 0.25)) + vec2(0.0078125, 0.0078125)) + (vec2(0.234375, 0.234375) * whiteColor.xy));                  \n    lowp vec4 temp1;\n    temp1 = texture2D (uTextureTable, lookPos1);\n    lowp vec4 temp2;\n    temp2 = texture2D (uTextureTable, lookPos2);                   \n    gl_FragColor = vec4(mix(whiteColor.xyz, mix (temp1.xyz, temp2.xyz, fract(temp)), uIntensity), 1.0);\n}";
    public static final String MixShader = "precision mediump float;\nvarying vec2 vTexCoord;\nuniform sampler2D uTexture0;\nuniform sampler2D uTexture1;\nuniform float uOpacity;\nvoid main ()\n{\n  lowp vec4 temp1;\n  lowp vec4 temp2;\n  temp1 = texture2D (uTexture0, vTexCoord);\n  temp2 = texture2D (uTexture1, vTexCoord);\n  lowp vec4 temp3;\n  temp3.w = 1.0;\n  temp3.xyz = mix(temp2.xyz, mix(temp2.xyz, min(max (((temp2.xyz + (vec3(2.0, 2.0, 2.0) * temp1.xyz)) -\n\t\tvec3(1.0, 1.0, 1.0)), 0.0), 1.0), 0.30), uOpacity);\n  gl_FragColor = temp3;\n}";
    public static String NoChangeFrameShader = "precision mediump float;\nvarying vec2 vTexCoord;\nuniform sampler2D uTexture0;\n\nvoid main()\n{\n    vec4 color = texture2D(uTexture0, vTexCoord);\n    gl_FragColor = color;\n}";
    public static final String RgbToYuvShader = "precision mediump float;\nvarying vec2 vTexCoord;\nuniform sampler2D uTexture0;\nvoid main ()\n{\n  mediump vec4 rgb;\n  mediump vec3 yuv;\n  rgb = texture2D (uTexture0, vTexCoord);\n  yuv = (mat3(0.2126, -0.09991, 0.615, 0.7152, -0.33609, -0.55861, 0.0722, 0.436, -0.05639) * rgb.xyz);\n  yuv.yz = (yuv.yz + vec2(0.5, 0.5));\n  gl_FragColor = vec4(yuv,1.0);\n}";
    public static final String VertexShader = "attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTexCoord;\n\nvoid main()\n{\n    gl_Position = aPosition;\n    vTexCoord = aTextureCoord.xy;\n}";
    public static final String XBlurShader = "precision mediump float;\nuniform sampler2D uTexture0;\nvarying  vec2 vTexCoord;\nuniform float xSteps;\nvoid main ()\n{\n    int step = 3;\n    lowp float sum1;\n    lowp float sum2;\n    lowp vec4 temp1;\n    lowp vec4 temp2;\n    temp1 = texture2D (uTexture0, vTexCoord);\n sum2 = (temp1.x * temp1.x);\n    lowp float temp3 = temp1.x;\n    sum1 = temp3;\n    \n for(int i = 1; i < step; i++)\n {\n  lowp vec2 temp4;\n         temp4.y = 0.0;\n         temp4.x = (float(i) * xSteps);\n         temp1 = texture2D (uTexture0, (vTexCoord - temp4));\n         temp2 = texture2D (uTexture0, (vTexCoord + temp4));\n         sum1 = (sum1 + (temp1.x + temp2.x));\n         sum2 = (sum2 + ((temp1.x * temp1.x) + (temp2.x * temp2.x)));\n}\n    \nstep = 2 * step - 1;\n    temp1.z = 1.0;\n    temp1.x = (sum1 / float(step));\n    temp1.y = (sum2 / float(step));\n    temp1.w = temp3;\n    gl_FragColor = temp1;\n}";
    public static final String YBlurShader = "precision mediump float;\nvarying vec2 vTexCoord;\nuniform sampler2D uTexture0;\nuniform float ySteps;\nuniform int uStrength;\nvoid main ()\n{\n  float uEps = 0.002905;\n  lowp float temp0;\n  int step = 3;\n  lowp float temp1;\n  lowp float temp2;\n  lowp vec4 temp3;\n  temp3 = texture2D (uTexture0, vTexCoord);\n  temp1 = temp3.y;\n  temp2 = temp3.x;\n  \n  for(int i = 1; i < step; i++)\n  {\n    lowp vec2 temp4;\n    temp4.x = 0.0;\n    temp4.y = (float(i) * ySteps);\n    lowp vec4 temp5 = texture2D (uTexture0, (vTexCoord - temp4));\n    lowp vec4 temp6 = texture2D (uTexture0, (vTexCoord + temp4));\n    temp2 = (temp2 + (temp5.x + temp6.x));\n    temp1 = (temp1 + (temp5.y + temp6.y));\n  }\n  highp int temp7;\n  temp7 = (1 + ((step - 1) * 2));\n  lowp float temp8;\n  temp8 = (temp2 / float(temp7));\n  lowp float temp9;\n  temp9 = ((temp1 / float(temp7)) - (temp8 * temp8));\n  lowp float temp10;\n  temp10 = min (max ((temp9 / (temp9 + uEps)), 0.0), 1.0);\n  temp0 = ((temp3.w - ((temp10 * temp3.w) + (temp8 - (temp10 * temp8)))) + 0.5);\n  for (int i = 0; i < uStrength; i++)\n  {\n    lowp float temp11;\n    if ((temp0 < 0.5)) \n\t{\n\t\ttemp11 = ((2.0 * temp0) * temp0);\n    } \n\telse \n\t{\n\t\ttemp11 = (1.0 - ((2.0 * (1.0 - temp0)) * (1.0 - temp0)));\n    };\n    temp0 = temp11;\n  };\n  lowp float temp12 = (1.0 + (pow (temp3.w, 0.3) * 0.17));\n  temp0 = ((mix (temp3.w, mix (temp3.w, clamp (((temp3.w * temp12) + (temp0 * \n      (1.0 - temp12))), 0.0, 1.0), pow (temp3.w, 0.33)), pow (temp3.w, 0.39)) - temp3.w) + 0.5);\n  \n  gl_FragColor = vec4(temp0, temp0, temp0, 1.0);\n}";
    private Bitmap mBitmap;
    private IntBuffer mFramebuffer;
    private float mIntensity;
    private GLTexture mLookupTexture;
    private IntBuffer mOldFramebuffer;
    private float mOpacity;
    private GLShaderProgram mProgramLookUpTableShader;
    private GLShaderProgram mProgramMixShader;
    private GLShaderProgram mProgramRgbToYuvShader;
    private GLShaderProgram mProgramShader;
    private GLShaderProgram mProgramXBlurShader;
    private GLShaderProgram mProgramYBlurShader;
    private Drawable2d mRectDrawable;
    private int mStrength;
    private GLTexture mTempTexture0;
    private GLTexture mTempTexture1;
    private GLTexture mTempTexture2;
    private float xSteps;
    private float ySteps;

    public GPUBeautyNaturalFilter() {
        super(GPUImageFilter.NO_FILTER_VERTEX_SHADER, GPUImageFilter.NO_FILTER_FRAGMENT_SHADER);
        this.mProgramShader = null;
        this.mProgramRgbToYuvShader = null;
        this.mProgramXBlurShader = null;
        this.mProgramYBlurShader = null;
        this.mProgramMixShader = null;
        this.mProgramLookUpTableShader = null;
        this.mTempTexture0 = null;
        this.mTempTexture1 = null;
        this.mTempTexture2 = null;
        this.mLookupTexture = null;
        this.mFramebuffer = null;
        this.mOldFramebuffer = null;
        this.mRectDrawable = new Drawable2d(Drawable2d.Prefab.FULL_RECTANGLE);
        this.xSteps = 0.007453514f;
        this.ySteps = 0.004192601f;
        this.mIntensity = 0.7f;
        this.mOpacity = 0.7f;
        this.mStrength = 5;
    }

    public void setBitmap(Bitmap bitmap) {
        if (bitmap == null || !bitmap.isRecycled()) {
            this.mBitmap = bitmap;
        }
    }

    public void setIntensity(float f) {
        if (f <= 0.1f) {
            f = 0.1f;
        }
        this.mIntensity = f;
    }

    public float getIntensity() {
        return this.mIntensity;
    }

    public void setOpacity(float f) {
        this.mOpacity = f;
        if (f >= 0.0f && f <= 0.2f) {
            this.mStrength = 1;
            return;
        }
        float f2 = this.mOpacity;
        if (f2 > 0.2f && f2 <= 0.4f) {
            this.mStrength = 2;
            return;
        }
        float f3 = this.mOpacity;
        if (f3 > 0.4f && f3 <= 0.6f) {
            this.mStrength = 3;
            return;
        }
        float f4 = this.mOpacity;
        if (f4 > 0.6f && f4 <= 0.8f) {
            this.mStrength = 4;
        } else {
            this.mStrength = 5;
        }
    }

    public float getOpacity() {
        return this.mOpacity;
    }

    @Override // com.huya.force.gpuimage.GPUImageFilter
    public void onOutputSizeChanged(int i, int i2) {
        this.mOutputWidth = i;
        this.mOutputHeight = i2;
        this.xSteps = (1.0f / this.mOutputWidth) * 4.0f;
        this.ySteps = (1.0f / this.mOutputHeight) * 4.0f;
    }

    @Override // com.huya.force.gpuimage.GPUImageFilter
    public void onInit() {
        super.onInit();
        IntBuffer intBufferAllocate = IntBuffer.allocate(1);
        this.mFramebuffer = intBufferAllocate;
        GLES20.glGenFramebuffers(1, intBufferAllocate);
        GLShaderProgram gLShaderProgram = new GLShaderProgram();
        this.mProgramShader = gLShaderProgram;
        gLShaderProgram.setProgram("attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTexCoord;\n\nvoid main()\n{\n    gl_Position = aPosition;\n    vTexCoord = aTextureCoord.xy;\n}", NoChangeFrameShader);
        GLShaderProgram gLShaderProgram2 = new GLShaderProgram();
        this.mProgramRgbToYuvShader = gLShaderProgram2;
        gLShaderProgram2.setProgram("attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTexCoord;\n\nvoid main()\n{\n    gl_Position = aPosition;\n    vTexCoord = aTextureCoord.xy;\n}", RgbToYuvShader);
        GLShaderProgram gLShaderProgram3 = new GLShaderProgram();
        this.mProgramXBlurShader = gLShaderProgram3;
        gLShaderProgram3.setProgram("attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTexCoord;\n\nvoid main()\n{\n    gl_Position = aPosition;\n    vTexCoord = aTextureCoord.xy;\n}", "precision mediump float;\nuniform sampler2D uTexture0;\nvarying  vec2 vTexCoord;\nuniform float xSteps;\nvoid main ()\n{\n    int step = 3;\n    lowp float sum1;\n    lowp float sum2;\n    lowp vec4 temp1;\n    lowp vec4 temp2;\n    temp1 = texture2D (uTexture0, vTexCoord);\n sum2 = (temp1.x * temp1.x);\n    lowp float temp3 = temp1.x;\n    sum1 = temp3;\n    \n for(int i = 1; i < step; i++)\n {\n  lowp vec2 temp4;\n         temp4.y = 0.0;\n         temp4.x = (float(i) * xSteps);\n         temp1 = texture2D (uTexture0, (vTexCoord - temp4));\n         temp2 = texture2D (uTexture0, (vTexCoord + temp4));\n         sum1 = (sum1 + (temp1.x + temp2.x));\n         sum2 = (sum2 + ((temp1.x * temp1.x) + (temp2.x * temp2.x)));\n}\n    \nstep = 2 * step - 1;\n    temp1.z = 1.0;\n    temp1.x = (sum1 / float(step));\n    temp1.y = (sum2 / float(step));\n    temp1.w = temp3;\n    gl_FragColor = temp1;\n}");
        GLShaderProgram gLShaderProgram4 = new GLShaderProgram();
        this.mProgramYBlurShader = gLShaderProgram4;
        gLShaderProgram4.setProgram("attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTexCoord;\n\nvoid main()\n{\n    gl_Position = aPosition;\n    vTexCoord = aTextureCoord.xy;\n}", "precision mediump float;\nvarying vec2 vTexCoord;\nuniform sampler2D uTexture0;\nuniform float ySteps;\nuniform int uStrength;\nvoid main ()\n{\n  float uEps = 0.002905;\n  lowp float temp0;\n  int step = 3;\n  lowp float temp1;\n  lowp float temp2;\n  lowp vec4 temp3;\n  temp3 = texture2D (uTexture0, vTexCoord);\n  temp1 = temp3.y;\n  temp2 = temp3.x;\n  \n  for(int i = 1; i < step; i++)\n  {\n    lowp vec2 temp4;\n    temp4.x = 0.0;\n    temp4.y = (float(i) * ySteps);\n    lowp vec4 temp5 = texture2D (uTexture0, (vTexCoord - temp4));\n    lowp vec4 temp6 = texture2D (uTexture0, (vTexCoord + temp4));\n    temp2 = (temp2 + (temp5.x + temp6.x));\n    temp1 = (temp1 + (temp5.y + temp6.y));\n  }\n  highp int temp7;\n  temp7 = (1 + ((step - 1) * 2));\n  lowp float temp8;\n  temp8 = (temp2 / float(temp7));\n  lowp float temp9;\n  temp9 = ((temp1 / float(temp7)) - (temp8 * temp8));\n  lowp float temp10;\n  temp10 = min (max ((temp9 / (temp9 + uEps)), 0.0), 1.0);\n  temp0 = ((temp3.w - ((temp10 * temp3.w) + (temp8 - (temp10 * temp8)))) + 0.5);\n  for (int i = 0; i < uStrength; i++)\n  {\n    lowp float temp11;\n    if ((temp0 < 0.5)) \n\t{\n\t\ttemp11 = ((2.0 * temp0) * temp0);\n    } \n\telse \n\t{\n\t\ttemp11 = (1.0 - ((2.0 * (1.0 - temp0)) * (1.0 - temp0)));\n    };\n    temp0 = temp11;\n  };\n  lowp float temp12 = (1.0 + (pow (temp3.w, 0.3) * 0.17));\n  temp0 = ((mix (temp3.w, mix (temp3.w, clamp (((temp3.w * temp12) + (temp0 * \n      (1.0 - temp12))), 0.0, 1.0), pow (temp3.w, 0.33)), pow (temp3.w, 0.39)) - temp3.w) + 0.5);\n  \n  gl_FragColor = vec4(temp0, temp0, temp0, 1.0);\n}");
        GLShaderProgram gLShaderProgram5 = new GLShaderProgram();
        this.mProgramMixShader = gLShaderProgram5;
        gLShaderProgram5.setProgram("attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTexCoord;\n\nvoid main()\n{\n    gl_Position = aPosition;\n    vTexCoord = aTextureCoord.xy;\n}", "precision mediump float;\nvarying vec2 vTexCoord;\nuniform sampler2D uTexture0;\nuniform sampler2D uTexture1;\nuniform float uOpacity;\nvoid main ()\n{\n  lowp vec4 temp1;\n  lowp vec4 temp2;\n  temp1 = texture2D (uTexture0, vTexCoord);\n  temp2 = texture2D (uTexture1, vTexCoord);\n  lowp vec4 temp3;\n  temp3.w = 1.0;\n  temp3.xyz = mix(temp2.xyz, mix(temp2.xyz, min(max (((temp2.xyz + (vec3(2.0, 2.0, 2.0) * temp1.xyz)) -\n\t\tvec3(1.0, 1.0, 1.0)), 0.0), 1.0), 0.30), uOpacity);\n  gl_FragColor = temp3;\n}");
        GLShaderProgram gLShaderProgram6 = new GLShaderProgram();
        this.mProgramLookUpTableShader = gLShaderProgram6;
        gLShaderProgram6.setProgram("attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTexCoord;\n\nvoid main()\n{\n    gl_Position = aPosition;\n    vTexCoord = aTextureCoord.xy;\n}", LookUpTableShader);
        this.mOldFramebuffer = IntBuffer.allocate(1);
    }

    @Override // com.huya.force.gpuimage.GPUImageFilter
    public void onDestroy() {
        super.onDestroy();
        GLES20.glDeleteFramebuffers(1, this.mFramebuffer);
        GLTexture gLTexture = this.mTempTexture0;
        if (gLTexture != null) {
            gLTexture.destory();
        }
        GLTexture gLTexture2 = this.mLookupTexture;
        if (gLTexture2 != null) {
            gLTexture2.destory();
        }
        GLTexture gLTexture3 = this.mTempTexture1;
        if (gLTexture3 != null) {
            gLTexture3.destory();
        }
        GLTexture gLTexture4 = this.mTempTexture2;
        if (gLTexture4 != null) {
            gLTexture4.destory();
        }
        GLShaderProgram gLShaderProgram = this.mProgramShader;
        if (gLShaderProgram != null) {
            gLShaderProgram.destory();
        }
        GLShaderProgram gLShaderProgram2 = this.mProgramRgbToYuvShader;
        if (gLShaderProgram2 != null) {
            gLShaderProgram2.destory();
        }
        GLShaderProgram gLShaderProgram3 = this.mProgramXBlurShader;
        if (gLShaderProgram3 != null) {
            gLShaderProgram3.destory();
        }
        GLShaderProgram gLShaderProgram4 = this.mProgramYBlurShader;
        if (gLShaderProgram4 != null) {
            gLShaderProgram4.destory();
        }
        GLShaderProgram gLShaderProgram5 = this.mProgramMixShader;
        if (gLShaderProgram5 != null) {
            gLShaderProgram5.destory();
        }
        GLShaderProgram gLShaderProgram6 = this.mProgramLookUpTableShader;
        if (gLShaderProgram6 != null) {
            gLShaderProgram6.destory();
        }
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            if (!bitmap.isRecycled()) {
                this.mBitmap.recycle();
            }
            this.mBitmap = null;
        }
    }

    @Override // com.huya.force.gpuimage.GPUImageFilter
    public void onInitExt(boolean z) {
        this.mTextureTarget = 3553;
        if (z) {
            this.mTextureTarget = 36197;
            NoChangeFrameShader = "#extension GL_OES_EGL_image_external : require\n" + NoChangeFrameShader.replace("uniform sampler2D uTexture0;", "uniform samplerExternalOES uTexture0;");
        }
        this.mIsInitialized = true;
    }

    @Override // com.huya.force.gpuimage.GPUImageFilter
    public void onDraw(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        runPendingOnDrawTasks();
        if (isInitialized()) {
            if (this.mTempTexture0 == null) {
                this.mTempTexture0 = new GLTexture(3553);
                this.mTempTexture1 = new GLTexture(3553);
                this.mTempTexture2 = new GLTexture(3553);
                this.mLookupTexture = new GLTexture(3553);
            }
            if (this.mTempTexture0.getWidth() != this.mOutputWidth || this.mTempTexture0.getHeight() != this.mOutputHeight) {
                this.mTempTexture0.create(this.mOutputWidth, this.mOutputHeight, 6408);
                this.mTempTexture1.create(this.mOutputWidth, this.mOutputHeight, 6408);
                this.mTempTexture2.create(this.mOutputWidth, this.mOutputHeight, 6408);
                this.mLookupTexture.create(this.mBitmap, 6408);
                Bitmap bitmap = this.mBitmap;
                if (bitmap != null && !bitmap.isRecycled()) {
                    this.mBitmap.recycle();
                    this.mBitmap = null;
                }
            }
            GLES20.glGetIntegerv(36006, this.mOldFramebuffer);
            this.mTempTexture0.bindFBO(this.mFramebuffer.get(0));
            this.mProgramShader.useProgram();
            this.mProgramShader.setUniformTexture("uTexture0", 0, i, this.mTextureTarget);
            drawQuad(this.mProgramShader, floatBuffer, floatBuffer2);
            this.mTempTexture1.bindFBO(this.mFramebuffer.get(0));
            this.mProgramRgbToYuvShader.useProgram();
            this.mProgramRgbToYuvShader.setUniformTexture("uTexture0", 0, this.mTempTexture0.getTextureId(), this.mTextureTarget);
            drawQuad(this.mProgramRgbToYuvShader, floatBuffer, floatBuffer2);
            this.mTempTexture2.bindFBO(this.mFramebuffer.get(0));
            this.mProgramXBlurShader.useProgram();
            this.mProgramXBlurShader.setUniformTexture("uTexture0", 0, this.mTempTexture1.getTextureId(), 3553);
            this.mProgramXBlurShader.setUniform1f("xSteps", this.xSteps);
            drawQuad(this.mProgramXBlurShader, this.mRectDrawable.getVertexArray(), this.mRectDrawable.getTexCoordArray());
            this.mTempTexture1.bindFBO(this.mFramebuffer.get(0));
            this.mProgramYBlurShader.useProgram();
            this.mProgramYBlurShader.setUniformTexture("uTexture0", 0, this.mTempTexture2.getTextureId(), 3553);
            this.mProgramYBlurShader.setUniform1f("ySteps", this.ySteps);
            this.mProgramYBlurShader.setUniform1i("uStrength", this.mStrength);
            drawQuad(this.mProgramYBlurShader, this.mRectDrawable.getVertexArray(), this.mRectDrawable.getTexCoordArray());
            this.mTempTexture2.bindFBO(this.mFramebuffer.get(0));
            this.mProgramMixShader.useProgram();
            this.mProgramMixShader.setUniformTexture("uTexture0", 0, this.mTempTexture1.getTextureId(), 3553);
            this.mProgramMixShader.setUniformTexture("uTexture1", 1, this.mTempTexture0.getTextureId(), 3553);
            this.mProgramMixShader.setUniform1f("uOpacity", this.mOpacity);
            drawQuad(this.mProgramMixShader, this.mRectDrawable.getVertexArray(), this.mRectDrawable.getTexCoordArray());
            GLES20.glBindFramebuffer(36160, this.mOldFramebuffer.get(0));
            this.mProgramLookUpTableShader.useProgram();
            this.mProgramLookUpTableShader.setUniformTexture("uTexture0", 0, this.mTempTexture2.getTextureId(), 3553);
            this.mProgramLookUpTableShader.setUniformTexture("uTextureTable", 1, this.mLookupTexture.getTextureId(), 3553);
            this.mProgramLookUpTableShader.setUniform1f("uIntensity", this.mIntensity);
            this.mProgramLookUpTableShader.setUniform1f("uWhiteness", (this.mIntensity * 0.25f) + 1.05f);
            drawQuad(this.mProgramLookUpTableShader, this.mRectDrawable.getVertexArray(), this.mRectDrawable.getTexCoordArray());
            GLES20.glBindTexture(this.mTextureTarget, 0);
        }
    }

    public void drawQuad(GLShaderProgram gLShaderProgram, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        floatBuffer.position(0);
        gLShaderProgram.setVertexAttribPointer("aPosition", 2, 5126, false, 0, floatBuffer);
        floatBuffer2.position(0);
        gLShaderProgram.setVertexAttribPointer("aTextureCoord", 2, 5126, false, 0, floatBuffer2);
        GLES20.glDrawArrays(5, 0, 4);
    }
}
