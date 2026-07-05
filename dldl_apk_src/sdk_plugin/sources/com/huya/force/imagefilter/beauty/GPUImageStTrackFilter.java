package com.huya.force.imagefilter.beauty;

import android.opengl.GLES20;
import android.util.Log;
import com.huya.force.gles.Drawable2d;
import com.huya.force.gpuimage.OpenGlUtils;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class GPUImageStTrackFilter {
    private static final String FRAGMENT_SHADER = " precision highp float;\n varying highp vec2 vTexCoord;\n \n uniform sampler2D inputImageTexture;\n \n \n uniform lowp vec2 location0;\n uniform lowp vec2 location1;\n uniform lowp vec2 location2;\n uniform lowp vec2 location3;\n uniform lowp vec2 location4;\n uniform lowp vec2 location5;\n uniform lowp vec2 location6;\n uniform lowp vec2 location7;\n uniform lowp vec2 location8;\n uniform lowp vec2 location9;\n \n uniform lowp vec2 quad_origin;\n uniform lowp vec2 quad_size;\n \n uniform lowp float x_a;\n uniform lowp float y_a;\n \n uniform lowp float foreheadSize;\n uniform lowp float faceSize;\n uniform lowp float chinSize;\n uniform lowp float eyeSize;\n uniform lowp float faceRang;\n uniform lowp float chinRang;\n uniform lowp float boneRang;\n uniform lowp float faceRadius;\n uniform lowp float chinRadius;\n uniform lowp float boneRadius;\n uniform lowp float eyeLength;\n uniform lowp float eyeRadius;\n uniform lowp float boneStrength;\n uniform lowp float faceStrength;\n uniform lowp float chinStrength;\n uniform lowp float smallfaceSize;\n uniform lowp float cutfaceSize;\n uniform lowp float middlechinRang;\n uniform lowp float middlechinRadius;\n uniform lowp float middlechinStrength;\n \n#define p_faceleft location0\n#define p_chin location1\n#define p_faceright location2\n#define p_nose location3\n#define p_eyea location4\n#define p_eyeb location5\n#define p_chinleft location6\n#define p_chinright location7\n#define p_boneleft location8\n#define p_boneright location9\n \nvec2 faceStretch(vec2 textureCoord, vec2 originPosition, vec2 targetPosition, float radius)\n{\n    float dis = distance(textureCoord, originPosition); \n    if (dis >= radius) {\n        return vec2(0, 0); \n    } \n    vec2 direction = targetPosition - originPosition;\n    float lengthA = length(direction);\n    float lengthB = min(lengthA, radius);\n    direction *= lengthB / lengthA;\n    float infect = dis / radius;\n    infect = clamp(1.0 - infect, 0.0, 1.0);\n    \n    return direction * infect;\n}\n \n void main()\n{\n    gl_FragColor =  texture2D(inputImageTexture,vTexCoord).rgba;\n    \n    vec2 newCoord = vec2(vTexCoord.x*x_a,vTexCoord.y*y_a);\n    //gl_FragColor = texture2D(inputImageTexture, newCoord).rgba;\n    if(location3.x>0.03 && location3.y>0.03)\n    {\n        vec2 eyea = vec2(p_eyea.x * x_a, p_eyea.y * y_a);\n        vec2 eyeb = vec2(p_eyeb.x * x_a, p_eyeb.y * y_a);\n        \n        vec2 faceleft = vec2(p_faceleft.x * x_a, p_faceleft.y * y_a);\n        vec2 faceright = vec2(p_faceright.x * x_a, p_faceright.y * y_a);\n        \n        vec2 chinleft = vec2(p_chinleft.x * x_a, p_chinleft.y * y_a);\n        vec2 chinright = vec2(p_chinright.x * x_a, p_chinright.y * y_a);\n        \n        vec2 boneleft = vec2(p_boneleft.x * x_a, p_boneleft.y * y_a);\n        vec2 boneright = vec2(p_boneright.x * x_a, p_boneright.y * y_a);\n        \n        vec2 nose = vec2(p_nose.x * x_a, p_nose.y * y_a);\n        vec2 chin = vec2(p_chin.x * x_a, p_chin.y * y_a);\n        \n        vec2 boneCenter = nose + (chin - nose) * boneRang;\n        vec2 middlechinCenter = nose + (chin - nose) * middlechinRang;\n        vec2 faceCenter = nose + (chin - nose) * faceRang;\n        vec2 chinCenter = nose + (chin - nose) * chinRang;\n        \n        float face_width = distance(eyea, eyeb);\n        \n        if (eyeSize > 0.0) {\n            float weight = 0.0;\n            float eyeRadius = face_width * foreheadSize*eyeRadius;\n            float dis_eye1 = distance(newCoord, eyea);\n            \n            if(dis_eye1 <= eyeRadius)\n            {\n                weight = (1.0-eyeLength)+eyeLength*pow(dis_eye1/ eyeRadius, eyeSize);\n                newCoord = eyea + (newCoord - eyea) * weight;\n            }\n            \n            float dis_eye2 = distance(newCoord, eyeb);\n            if(dis_eye2 <= eyeRadius)\n            {\n                weight = (1.0-eyeLength)+eyeLength*pow(dis_eye2 / eyeRadius, eyeSize);\n                newCoord = eyeb + (newCoord - eyeb) * weight;\n            }\n        }\n        \n        float radius = face_width * 2.0*faceRadius;\n        vec2 leftF = faceleft;\n        vec2 targetleftF = faceCenter + (leftF - faceCenter)*pow(faceSize,1.0+faceStrength);\n        vec2 leftFplus = vec2(0.0);\n        leftFplus = faceStretch(newCoord, leftF, targetleftF, radius);\n        newCoord = newCoord - leftFplus;\n        \n        vec2 rightF = faceright;\n        vec2 targetrightF = faceCenter + (rightF - faceCenter) *pow(faceSize,1.0+faceStrength);\n        vec2 rightFplus = vec2(0.0);\n        rightFplus = faceStretch(newCoord, rightF, targetrightF, radius);\n        newCoord = newCoord - rightFplus;\n        \n        radius = face_width * 2.0*middlechinRadius;\n        vec2 chinC = chin;\n        vec2 targetleftW = middlechinCenter + (chinC - middlechinCenter) *pow(chinSize,1.0+middlechinStrength);\n        vec2 chinCplus = vec2(0.0);\n        chinCplus = faceStretch(newCoord, chinC, targetleftW, radius);\n        newCoord = newCoord - chinCplus;\n        \n        radius = face_width * 2.0;\n        vec2 chinS = chin;\n        vec2 targetleftS = nose + (chinS - nose) *pow(smallfaceSize,1.0);\n        vec2 chinSplus = vec2(0.0);\n        chinSplus = faceStretch(newCoord, chinS, targetleftS, radius);\n        newCoord = newCoord - chinSplus;\n        \n        radius = face_width * 2.0*chinRadius;\n        vec2 leftC = chinleft;\n        vec2 targetleftC = chinCenter + (leftC - chinCenter) *pow(faceSize,1.0+chinStrength);\n        vec2 leftCplus = vec2(0.0);\n        leftCplus = faceStretch(newCoord, leftC, targetleftC, radius);\n        newCoord = newCoord - leftCplus;\n        \n        vec2 rightC = chinright;\n        vec2 targetrightC = chinCenter + (rightC - chinCenter) *pow(faceSize,1.0+chinStrength);\n        vec2 rightCplus = vec2(0.0);\n        rightCplus = faceStretch(newCoord, rightC, targetrightC, radius);\n        newCoord = newCoord - rightCplus;\n        \n        radius = face_width * 2.0*boneRadius;\n        vec2 leftB = boneleft;\n        vec2 targetleftB = boneCenter + (leftB - boneCenter) *pow(cutfaceSize,1.0+boneStrength);\n        vec2 leftBplus = vec2(0.0);\n        leftBplus = faceStretch(newCoord, leftB, targetleftB, radius);\n        newCoord = newCoord - leftBplus;\n        \n        vec2 rightB = boneright;\n        vec2 targetrightB = boneCenter + (rightB - boneCenter) *pow(cutfaceSize,1.0+boneStrength);\n        vec2 rightBplus = vec2(0.0);\n        rightBplus = faceStretch(newCoord, rightB, targetrightB, radius);\n        newCoord = newCoord - rightBplus;\n        \n        newCoord = vec2(newCoord.x / x_a, newCoord.y / y_a);\n        gl_FragColor = texture2D(inputImageTexture, newCoord).rgba;\n    }\n}";
    private static final String TAG = "GPUImageStTrackFilter";
    private static final String TEXTURE_SHADER = " attribute vec4 position;\n attribute vec4 textureCoord;\n \n varying vec2  vTexCoord;\n void main()\n {\n     vec4 newPosition = vec4(position.x , position.y, position.zw);\n     gl_Position = newPosition;\n     vTexCoord = textureCoord.xy;\n }";
    private int mGLAttribPosition;
    private int mGLAttribTextureCoordinate;
    private int mGLProgId;
    private int mGLUniformBoneRadius;
    private int mGLUniformBoneRang;
    private int mGLUniformBoneStrength;
    private int mGLUniformChinRadius;
    private int mGLUniformChinRang;
    private int mGLUniformChinSize;
    private int mGLUniformChinStrength;
    private int mGLUniformCutfaceSize;
    private int mGLUniformEyeLength;
    private int mGLUniformEyeRadius;
    private int mGLUniformEyeSize;
    private int mGLUniformFaceRadius;
    private int mGLUniformFaceRang;
    private int mGLUniformFaceSize;
    private int mGLUniformFaceStrength;
    private int mGLUniformForeheadSize;
    private int mGLUniformLocation0;
    private int mGLUniformLocation1;
    private int mGLUniformLocation2;
    private int mGLUniformLocation3;
    private int mGLUniformLocation4;
    private int mGLUniformLocation5;
    private int mGLUniformLocation6;
    private int mGLUniformLocation7;
    private int mGLUniformLocation8;
    private int mGLUniformLocation9;
    private int mGLUniformMiddleChinRadius;
    private int mGLUniformMiddleChinRang;
    private int mGLUniformMiddleChinStrength;
    private int mGLUniformQuadOrigin;
    private int mGLUniformQuadSize;
    private int mGLUniformSmallfaceSize;
    private int mGLUniformTexture;
    private int mGLUniformXA;
    private int mGLUniformYA;
    private boolean mIsInit;
    private final Drawable2d mRectDrawable = new Drawable2d(Drawable2d.Prefab.FULL_RECTANGLE);
    private Config mConfig = new Config();
    private float FaceInstenty = 0.94f;
    private float EyeInstenty = 0.0f;
    private final LinkedList<Runnable> mRunOnDraw = new LinkedList<>();

    public static class Config {
        public float faceRang = 0.3f;
        public float chinRang = 0.57f;
        public float boneRang = 0.0f;
        public float faceRadius = 0.6f;
        public float chinRadius = 0.6f;
        public float boneRadius = 0.5f;
        public int leftFace = 10;
        public int rightFace = 22;
        public int leftChin = 13;
        public int rightChin = 19;
        public int leftBone = 5;
        public int rightBone = 27;
        public int nose = 46;
        public float eyeLength = 1.0f;
        public float eyeRadius = 1.0f;
        public float boneStrength = 0.0f;
        public float faceStrength = 0.0f;
        public float chinStrength = 0.0f;
        public float middlechinStrength = 0.0f;
        public float middlechinRadius = 0.3f;
        public float middlechinRang = 0.7f;
    }

    public void setConfig(Config config) {
        this.mConfig = config;
    }

    public float getFaceRang() {
        return this.mConfig.faceRang;
    }

    public float getChinRang() {
        return this.mConfig.chinRang;
    }

    public float getBoneRang() {
        return this.mConfig.boneRang;
    }

    public float getFaceRadius() {
        return this.mConfig.faceRadius;
    }

    public float getChinRadius() {
        return this.mConfig.chinRadius;
    }

    public float getBoneRadius() {
        return this.mConfig.boneRadius;
    }

    public int getLeftFace() {
        return this.mConfig.leftFace;
    }

    public int getRightFace() {
        return this.mConfig.rightFace;
    }

    public int getLeftChin() {
        return this.mConfig.leftChin;
    }

    public int getRightChin() {
        return this.mConfig.rightChin;
    }

    public int getLeftBone() {
        return this.mConfig.leftBone;
    }

    public int getRightBone() {
        return this.mConfig.rightBone;
    }

    public int getNose() {
        return this.mConfig.nose;
    }

    public float getEyeLength() {
        return this.mConfig.eyeLength;
    }

    public float getEyeRadius() {
        return this.mConfig.eyeRadius;
    }

    public float getFaceInstenty() {
        return this.FaceInstenty;
    }

    public float getEyeInstenty() {
        return this.EyeInstenty;
    }

    public Map<String, String> getParams() {
        HashMap map = new HashMap();
        map.put("param1", String.valueOf(getFaceRang()));
        map.put("param2", String.valueOf(getChinRang()));
        map.put("param3", String.valueOf(getLeftFace()));
        map.put("param4", String.valueOf(getRightFace()));
        map.put("param5", String.valueOf(getLeftChin()));
        map.put("param6", String.valueOf(getRightChin()));
        map.put("param7", String.valueOf(getEyeInstenty()));
        map.put("param8", String.valueOf(getFaceInstenty()));
        map.put("param9", String.valueOf(getBoneRang()));
        map.put("param10", String.valueOf(getFaceRadius()));
        map.put("param11", String.valueOf(getChinRadius()));
        map.put("param12", String.valueOf(getBoneRadius()));
        map.put("param13", String.valueOf(getLeftBone()));
        map.put("param14", String.valueOf(getRightBone()));
        return map;
    }

    public void setParams(Map<String, String> map) {
        setFaceRang(Float.valueOf(map.get("param1")).floatValue());
        setChinRang(Float.valueOf(map.get("param2")).floatValue());
        this.mConfig.leftFace = Integer.valueOf(map.get("param3")).intValue();
        this.mConfig.rightFace = Integer.valueOf(map.get("param4")).intValue();
        this.mConfig.leftChin = Integer.valueOf(map.get("param5")).intValue();
        this.mConfig.rightChin = Integer.valueOf(map.get("param6")).intValue();
        setEyeSize(Float.valueOf(map.get("param7")).floatValue());
        float fFloatValue = Float.valueOf(map.get("param8")).floatValue();
        setChinSize(fFloatValue);
        setFaceSize(fFloatValue);
        float fFloatValue2 = Float.valueOf(map.get("param9")).floatValue();
        if (fFloatValue2 <= 0.1f) {
            fFloatValue2 = 0.1f;
        }
        setBoneRang(fFloatValue2);
        setFaceRadius(Float.valueOf(map.get("param10")).floatValue());
        setChinRadius(Float.valueOf(map.get("param11")).floatValue());
        setBoneRadius(Float.valueOf(map.get("param12")).floatValue());
        this.mConfig.leftBone = Integer.valueOf(map.get("param13")).intValue();
        this.mConfig.rightBone = Integer.valueOf(map.get("param14")).intValue();
    }

    public final void init() {
        int iLoadProgram = OpenGlUtils.loadProgram(TEXTURE_SHADER, FRAGMENT_SHADER);
        this.mGLProgId = iLoadProgram;
        this.mGLAttribPosition = GLES20.glGetAttribLocation(iLoadProgram, "position");
        this.mGLAttribTextureCoordinate = GLES20.glGetAttribLocation(this.mGLProgId, "textureCoord");
        this.mGLUniformTexture = GLES20.glGetUniformLocation(this.mGLProgId, "inputImageTexture");
        this.mGLUniformXA = GLES20.glGetUniformLocation(this.mGLProgId, "x_a");
        this.mGLUniformYA = GLES20.glGetUniformLocation(this.mGLProgId, "y_a");
        this.mGLUniformForeheadSize = GLES20.glGetUniformLocation(this.mGLProgId, "foreheadSize");
        this.mGLUniformLocation0 = GLES20.glGetUniformLocation(this.mGLProgId, "location0");
        this.mGLUniformLocation1 = GLES20.glGetUniformLocation(this.mGLProgId, "location1");
        this.mGLUniformLocation2 = GLES20.glGetUniformLocation(this.mGLProgId, "location2");
        this.mGLUniformLocation3 = GLES20.glGetUniformLocation(this.mGLProgId, "location3");
        this.mGLUniformLocation4 = GLES20.glGetUniformLocation(this.mGLProgId, "location4");
        this.mGLUniformLocation5 = GLES20.glGetUniformLocation(this.mGLProgId, "location5");
        this.mGLUniformLocation6 = GLES20.glGetUniformLocation(this.mGLProgId, "location6");
        this.mGLUniformLocation7 = GLES20.glGetUniformLocation(this.mGLProgId, "location7");
        this.mGLUniformLocation8 = GLES20.glGetUniformLocation(this.mGLProgId, "location8");
        this.mGLUniformLocation9 = GLES20.glGetUniformLocation(this.mGLProgId, "location9");
        this.mGLUniformFaceSize = GLES20.glGetUniformLocation(this.mGLProgId, "faceSize");
        this.mGLUniformChinSize = GLES20.glGetUniformLocation(this.mGLProgId, "chinSize");
        this.mGLUniformEyeSize = GLES20.glGetUniformLocation(this.mGLProgId, "eyeSize");
        this.mGLUniformSmallfaceSize = GLES20.glGetUniformLocation(this.mGLProgId, "smallfaceSize");
        this.mGLUniformCutfaceSize = GLES20.glGetUniformLocation(this.mGLProgId, "cutfaceSize");
        this.mGLUniformFaceRang = GLES20.glGetUniformLocation(this.mGLProgId, "faceRang");
        this.mGLUniformChinRang = GLES20.glGetUniformLocation(this.mGLProgId, "chinRang");
        this.mGLUniformBoneRang = GLES20.glGetUniformLocation(this.mGLProgId, "boneRang");
        this.mGLUniformFaceRadius = GLES20.glGetUniformLocation(this.mGLProgId, "faceRadius");
        this.mGLUniformChinRadius = GLES20.glGetUniformLocation(this.mGLProgId, "chinRadius");
        this.mGLUniformBoneRadius = GLES20.glGetUniformLocation(this.mGLProgId, "boneRadius");
        this.mGLUniformEyeLength = GLES20.glGetUniformLocation(this.mGLProgId, "eyeLength");
        this.mGLUniformEyeRadius = GLES20.glGetUniformLocation(this.mGLProgId, "eyeRadius");
        this.mGLUniformBoneStrength = GLES20.glGetUniformLocation(this.mGLProgId, "boneStrength");
        this.mGLUniformFaceStrength = GLES20.glGetUniformLocation(this.mGLProgId, "faceStrength");
        this.mGLUniformChinStrength = GLES20.glGetUniformLocation(this.mGLProgId, "chinStrength");
        this.mGLUniformMiddleChinRadius = GLES20.glGetUniformLocation(this.mGLProgId, "middlechinRadius");
        this.mGLUniformMiddleChinStrength = GLES20.glGetUniformLocation(this.mGLProgId, "middlechinStrength");
        this.mGLUniformMiddleChinRang = GLES20.glGetUniformLocation(this.mGLProgId, "middlechinRang");
        this.mGLUniformQuadOrigin = GLES20.glGetUniformLocation(this.mGLProgId, "quad_origin");
        this.mGLUniformQuadSize = GLES20.glGetUniformLocation(this.mGLProgId, "quad_size");
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.1
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformXA, 0.72f);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformYA, 1.28f);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformForeheadSize, 0.72f);
                GLES20.glUniform2f(GPUImageStTrackFilter.this.mGLUniformLocation3, 0.0f, 0.0f);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformFaceSize, 0.99f);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformChinSize, 0.99f);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformSmallfaceSize, 0.99f);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformCutfaceSize, 0.99f);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformFaceRang, GPUImageStTrackFilter.this.mConfig.faceRang);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformChinRang, GPUImageStTrackFilter.this.mConfig.chinRang);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformBoneRang, GPUImageStTrackFilter.this.mConfig.boneRang);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformFaceRadius, GPUImageStTrackFilter.this.mConfig.faceRadius);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformChinRadius, GPUImageStTrackFilter.this.mConfig.chinRadius);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformBoneRadius, GPUImageStTrackFilter.this.mConfig.boneRadius);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformEyeLength, GPUImageStTrackFilter.this.mConfig.eyeLength);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformEyeRadius, GPUImageStTrackFilter.this.mConfig.eyeRadius);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformBoneStrength, GPUImageStTrackFilter.this.mConfig.boneStrength);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformFaceStrength, GPUImageStTrackFilter.this.mConfig.faceStrength);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformChinStrength, GPUImageStTrackFilter.this.mConfig.chinStrength);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformMiddleChinRadius, GPUImageStTrackFilter.this.mConfig.middlechinRadius);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformMiddleChinRang, GPUImageStTrackFilter.this.mConfig.middlechinRang);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformMiddleChinStrength, GPUImageStTrackFilter.this.mConfig.middlechinStrength);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformEyeSize, 0.0f);
                GLES20.glUniform2f(GPUImageStTrackFilter.this.mGLUniformQuadOrigin, 0.0f, 0.0f);
                GLES20.glUniform2f(GPUImageStTrackFilter.this.mGLUniformQuadSize, 0.0f, 0.0f);
            }
        });
        this.mIsInit = true;
    }

    public final void destroy() {
        this.mIsInit = false;
        GLES20.glDeleteProgram(this.mGLProgId);
    }

    public void onDraw(int i) {
        GLES20.glUseProgram(this.mGLProgId);
        runPendingOnDrawTasks();
        if (this.mIsInit) {
            GLES20.glVertexAttribPointer(this.mGLAttribPosition, 2, 5126, false, 0, (Buffer) this.mRectDrawable.getVertexArray());
            GLES20.glEnableVertexAttribArray(this.mGLAttribPosition);
            GLES20.glVertexAttribPointer(this.mGLAttribTextureCoordinate, 2, 5126, false, 0, (Buffer) this.mRectDrawable.getTexCoordArray());
            GLES20.glEnableVertexAttribArray(this.mGLAttribTextureCoordinate);
            if (i != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, i);
                GLES20.glUniform1i(this.mGLUniformTexture, 0);
            }
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.mGLAttribPosition);
            GLES20.glDisableVertexAttribArray(this.mGLAttribTextureCoordinate);
            GLES20.glBindTexture(3553, 0);
        }
    }

    protected void runPendingOnDrawTasks() {
        while (!this.mRunOnDraw.isEmpty()) {
            this.mRunOnDraw.removeFirst().run();
        }
    }

    public boolean isInitialized() {
        return this.mIsInit;
    }

    public int getProgram() {
        return this.mGLProgId;
    }

    public void setFaceKeyPoints(final float[] fArr) {
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.2
            @Override // java.lang.Runnable
            public void run() {
                float[] fArr2 = fArr;
                if (fArr2 == null || fArr2.length != 318) {
                    GLES20.glUniform2f(GPUImageStTrackFilter.this.mGLUniformLocation0, 0.0f, 0.0f);
                    GLES20.glUniform2f(GPUImageStTrackFilter.this.mGLUniformLocation1, 0.0f, 0.0f);
                    GLES20.glUniform2f(GPUImageStTrackFilter.this.mGLUniformLocation2, 0.0f, 0.0f);
                    GLES20.glUniform2f(GPUImageStTrackFilter.this.mGLUniformLocation3, 0.0f, 0.0f);
                    GLES20.glUniform2f(GPUImageStTrackFilter.this.mGLUniformLocation4, 0.0f, 0.0f);
                    GLES20.glUniform2f(GPUImageStTrackFilter.this.mGLUniformLocation5, 0.0f, 0.0f);
                    GLES20.glUniform2f(GPUImageStTrackFilter.this.mGLUniformLocation6, 0.0f, 0.0f);
                    GLES20.glUniform2f(GPUImageStTrackFilter.this.mGLUniformLocation7, 0.0f, 0.0f);
                    GLES20.glUniform2f(GPUImageStTrackFilter.this.mGLUniformLocation8, 0.0f, 0.0f);
                    GLES20.glUniform2f(GPUImageStTrackFilter.this.mGLUniformLocation9, 0.0f, 0.0f);
                    return;
                }
                int i = GPUImageStTrackFilter.this.mConfig.leftFace;
                int i2 = GPUImageStTrackFilter.this.mGLUniformLocation0;
                float[] fArr3 = fArr;
                int i3 = i * 3;
                GLES20.glUniform2f(i2, fArr3[i3], fArr3[i3 + 1]);
                int i4 = GPUImageStTrackFilter.this.mGLUniformLocation1;
                float[] fArr4 = fArr;
                GLES20.glUniform2f(i4, fArr4[48], fArr4[49]);
                int i5 = GPUImageStTrackFilter.this.mConfig.rightFace;
                int i6 = GPUImageStTrackFilter.this.mGLUniformLocation2;
                float[] fArr5 = fArr;
                int i7 = i5 * 3;
                GLES20.glUniform2f(i6, fArr5[i7], fArr5[i7 + 1]);
                int i8 = GPUImageStTrackFilter.this.mConfig.nose;
                int i9 = GPUImageStTrackFilter.this.mGLUniformLocation3;
                float[] fArr6 = fArr;
                int i10 = i8 * 3;
                GLES20.glUniform2f(i9, fArr6[i10], fArr6[i10 + 1]);
                int i11 = GPUImageStTrackFilter.this.mGLUniformLocation4;
                float[] fArr7 = fArr;
                GLES20.glUniform2f(i11, (fArr7[159] + fArr7[168]) / 2.0f, (fArr7[160] + fArr7[169]) / 2.0f);
                int i12 = GPUImageStTrackFilter.this.mGLUniformLocation5;
                float[] fArr8 = fArr;
                GLES20.glUniform2f(i12, (fArr8[177] + fArr8[186]) / 2.0f, (fArr8[178] + fArr8[187]) / 2.0f);
                int i13 = GPUImageStTrackFilter.this.mConfig.leftChin;
                int i14 = GPUImageStTrackFilter.this.mGLUniformLocation6;
                float[] fArr9 = fArr;
                int i15 = i13 * 3;
                GLES20.glUniform2f(i14, fArr9[i15], fArr9[i15 + 1]);
                int i16 = GPUImageStTrackFilter.this.mConfig.rightChin;
                int i17 = GPUImageStTrackFilter.this.mGLUniformLocation7;
                float[] fArr10 = fArr;
                int i18 = i16 * 3;
                GLES20.glUniform2f(i17, fArr10[i18], fArr10[i18 + 1]);
                int i19 = GPUImageStTrackFilter.this.mConfig.leftBone;
                int i20 = GPUImageStTrackFilter.this.mGLUniformLocation8;
                float[] fArr11 = fArr;
                int i21 = i19 * 3;
                GLES20.glUniform2f(i20, fArr11[i21], fArr11[i21 + 1]);
                int i22 = GPUImageStTrackFilter.this.mConfig.rightBone;
                int i23 = GPUImageStTrackFilter.this.mGLUniformLocation9;
                float[] fArr12 = fArr;
                int i24 = i22 * 3;
                GLES20.glUniform2f(i23, fArr12[i24], fArr12[i24 + 1]);
            }
        });
    }

    public void setFaceSize(final float f) {
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.3
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformFaceSize, f);
            }
        });
    }

    public void setChinSize(final float f) {
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.4
            @Override // java.lang.Runnable
            public void run() {
                Log.i(GPUImageStTrackFilter.TAG, "star show beauty face setChinSize value=" + f);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformChinSize, (f * 0.2f) + 0.999f);
            }
        });
    }

    public void setEyeSize(final float f) {
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.5
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformEyeSize, f);
            }
        });
    }

    public void setSmallfaceSize(final float f) {
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.6
            @Override // java.lang.Runnable
            public void run() {
                Log.i(GPUImageStTrackFilter.TAG, "star show beauty face setSmallfaceSize value=" + f);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformSmallfaceSize, 0.999f - (f * 0.1f));
            }
        });
    }

    public void setCutfaceSize(final float f) {
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.7
            @Override // java.lang.Runnable
            public void run() {
                Log.i(GPUImageStTrackFilter.TAG, "star show beauty face setCutfaceSize value=" + f);
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformCutfaceSize, 0.999f - (f * 0.1f));
            }
        });
    }

    public void setFaceRang(final float f) {
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.8
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformFaceRang, f);
            }
        });
    }

    public void setChinRang(final float f) {
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.9
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformChinRang, f);
            }
        });
    }

    public void setBoneRang(final float f) {
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.10
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformBoneRang, f);
            }
        });
    }

    public void setFaceRadius(final float f) {
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.11
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformFaceRadius, f);
            }
        });
    }

    public void setChinRadius(final float f) {
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.12
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformChinRadius, f);
            }
        });
    }

    public void setBoneRadius(final float f) {
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.13
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformBoneRadius, f);
            }
        });
    }

    public void setEyeLength(final float f) {
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.14
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformEyeLength, f);
            }
        });
    }

    public void setEyeRadius(final float f) {
        runOnDraw(new Runnable() { // from class: com.huya.force.imagefilter.beauty.GPUImageStTrackFilter.15
            @Override // java.lang.Runnable
            public void run() {
                GLES20.glUniform1f(GPUImageStTrackFilter.this.mGLUniformEyeRadius, f);
            }
        });
    }

    protected void runOnDraw(Runnable runnable) {
        this.mRunOnDraw.addLast(runnable);
    }
}
