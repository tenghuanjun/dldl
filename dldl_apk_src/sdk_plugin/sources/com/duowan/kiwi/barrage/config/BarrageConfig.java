package com.duowan.kiwi.barrage.config;

import com.duowan.ark.bind.DependencyProperty;
import com.duowan.ark.util.Config;
import com.duowan.ark.util.DensityUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BarrageConfig {
    public static float ACCLERATE = 0.0f;
    public static int ACC_DENOMINATOR = 0;
    public static int ACTIVATE_ACC_SIZE = 0;
    private static final String ANTI_BLOCK_ENABLE = "antiBlockBarrageEnable";
    private static final String ANTI_BLOCK_HAS_TIP = "antiBlcokHasTip";
    private static final String ANTI_BLOCK_NEVER_USE = "antiBlockBarrageNeverUse";
    private static final String ANTI_BLOCK_TOAST_TIP_COUNT = "antiBlockBarrageToastTipCount";
    private static final String BARRAGE_ENABLE_BOLD = "barrage_enable_bold";
    private static final String BARRAGE_ENABLE_SHADOW = "barrage_enable_shadow";
    private static final int BARRAGE_SIZE_1 = 15;
    private static final int BARRAGE_SIZE_2 = 16;
    private static final int BARRAGE_SIZE_3 = 17;
    private static final int BARRAGE_SIZE_4 = 18;
    private static final int BARRAGE_SIZE_5 = 19;
    public static final int BARRAGE_SIZE_SECTION_1 = 0;
    public static final int BARRAGE_SIZE_SECTION_2 = 1;
    public static final int BARRAGE_SIZE_SECTION_3 = 2;
    public static final int BARRAGE_SIZE_SECTION_4 = 3;
    public static final int BARRAGE_SIZE_SECTION_5 = 4;
    private static final String Barrage_Alpha = "barrage_alpha";
    private static final String Barrage_Model = "barrage_model";
    private static final String Barrage_Size = "barrage_size";
    public static final int Barrage_Size_Big = 3;
    public static final int Barrage_Size_Middle = 2;
    private static final String Barrage_Size_Section = "barrage_size_section";
    public static final int Barrage_Size_Small = 1;
    private static final String Barrage_Size_Type = "barrage_size_type";
    public static float CLOSE_DOUBLE_SPEED = 0.0f;
    public static final String Channel_Config = "ChannelConfig";
    public static final int Chinese = 1;
    public static final float DEFAULT_ALPHA = 0.75f;
    public static int DOUBLE_WHEN_SHELL_CACHE_SIZES = 0;
    public static final int DefaultColor = -657931;
    public static final int English = 0;
    public static final int FlashCount = 10;
    public static final String KEY_ACCLERATE = "ACCLERATE";
    public static final String KEY_ACC_DENOMINATOR = "ACC_DENOMINATOR";
    public static final String KEY_ACTIVATE_ACC_SIZE = "ACTIVATE_ACC_SIZE";
    public static final String KEY_CLOSE_DOUBLE_SPEED = "CLOSE_DOUBLE_SPEED";
    public static final String KEY_OPEN_DOUBLE_SPEED = "OPEN_DOUBLE_SPEED";
    public static final String KEY_OPEN_RANDOM_SPEED_SIZE = "OPEN_RANDOM_SPEED_SIZE";
    public static final int LANDSCAPE_LINE_COUNT = 5;
    public static final int LANDSCAPE_LINE_SPACE = 1;
    private static final String LAST_REPORT_ANTI_BLOCK_STATUS_TIME = "lastReportAntiBlockStatusTime";
    public static final int ModelClose = 0;
    public static final int ModelLuxury = 1;
    public static final int ModelSimplify = 2;
    public static float OPEN_DOUBLE_SPEED = 0.0f;
    public static int OPEN_RANDOM_SPEED_SIZE = 0;
    public static final int PORTRAIT_LINE_SPACE = 1;
    public static final int PORTRAIT_LUXURY_LINE_COUNT = 10;
    public static final int PORTRAIT_SIMPLIFY_LINE_COUNT = 3;
    public static int SINGLE_WHEN_SHELL_CACHE_SIZES = 0;
    public static final String TAG = "[Barrage]";
    public static final String TAG_CACHE = "[Barrage]cache";
    public static final String TAG_QUEUE = "[Barrage]queue";
    public static final String TAG_RENDER = "[Barrage]render";
    public static final String TAG_VIEW = "[Barrage]view";
    public static final int TYPE_FLASH = 256;
    public static final int TYPE_HORIZONTAL = 1;
    public static final int TYPE_NULL = 0;
    public static final int TYPE_VERTICAL = 16;
    public static final int VERTICAL_DEFAULT_DURATION = 6000;
    public static final int VerticalLineCount = 5;
    private static final String Video_Barrage_Model = "video_barrage_model";
    private static boolean sAntiBlockNowStatus;
    public static final int FLOATING_TOP_MARGIN = DensityUtil.dip2px(BarrageContext.gContext, 8.0f);
    public static final int LANDSCAPE_TOP_MARGIN = DensityUtil.dip2px(BarrageContext.gContext, 24.0f);
    public static final int PORTRAIT_TOP_MARGIN = DensityUtil.dip2px(BarrageContext.gContext, 8.0f);
    public static final int PORTRAIT_BOTTOM_MARGIN = DensityUtil.dip2px(BarrageContext.gContext, 0.0f);
    public static int LANDSCAPE_SIZE_BIG = DensityUtil.dip2px(BarrageContext.gContext, 18.0f);
    public static int LANDSCAPE_SIZE_MEDIUM = DensityUtil.dip2px(BarrageContext.gContext, 17.0f);
    public static int LANDSCAPE_SIZE_SMALL = DensityUtil.dip2px(BarrageContext.gContext, 15.0f);
    public static int DEFAULT_BARRAGE_SIZE = -1;
    public static int sBaseLandscapeSize = LANDSCAPE_SIZE_BIG;
    public static final int PORTRAIT_SIZE_BIG = DensityUtil.dip2px(BarrageContext.gContext, 17.0f);
    public static final int PORTRAIT_SIZE_MEDIUM = DensityUtil.dip2px(BarrageContext.gContext, 16.0f);
    public static final int PORTRAIT_SIZE_SMALL = DensityUtil.dip2px(BarrageContext.gContext, 15.0f);
    public static final int LANDSCAPE_SIZE_BIG_OLD = DensityUtil.sp2px(BarrageContext.gContext, 18.0f);
    public static final int LANDSCAPE_SIZE_MEDIUM_OLD = DensityUtil.sp2px(BarrageContext.gContext, 16.0f);
    public static final int LANDSCAPE_SIZE_SMALL_OLD = DensityUtil.sp2px(BarrageContext.gContext, 14.0f);
    private static int sDeviceIsLargeScreenStandard = 480;
    public static boolean sEnableBold = true;
    public static boolean sEnableBitmapPool = true;
    public static boolean sEnableShadow = true;
    public static boolean sEnbaleStroke = false;
    public static boolean sEndableAssistCache = true;
    public static int ShadowRadius = DensityUtil.sp2px(BarrageContext.gContext, 0.5f);
    public static float ShadowDX = 2.5f;
    public static float ShadowDY = 2.0f;
    public static int ShadowColor = -822083584;
    public static final int LANDSCAPE_SPACE_X = DensityUtil.dip2px(BarrageContext.gContext, 10.0f);
    public static final int PORTRAIT_SPACE_X = DensityUtil.dip2px(BarrageContext.gContext, 20.0f);
    public static final int SpaceY = DensityUtil.dip2px(BarrageContext.gContext, 5.0f);
    public static final int LANDSCAPE_LINE_SPACE_INNER = DensityUtil.dip2px(BarrageContext.gContext, 2.0f);
    public static final int COLUMN_SPACE = DensityUtil.dip2px(BarrageContext.gContext, 5.0f);
    public static int EMPTY_STRATEGY_LINE_NUMBER = 7;
    public static int DEFAULT_DURATION = 8500;
    public static DependencyProperty<Boolean> sVideoBarrageStatusDp = new DependencyProperty<>(false);
    public static int sCurrentVideoBarrageStatus = readVideoBarrageModel();
    public static int sBorderColor = -24064;
    private static boolean mBarrageRefreshPrint = false;
    private static boolean sIsFixedQueue = true;
    private static int sFixedLine = 1;

    public static int modelToType(int i) {
        if (i != 0) {
            return i != 2 ? 273 : 1;
        }
        return 0;
    }

    public static void configDeviceIsLargeScreenStandard(int i) {
        sDeviceIsLargeScreenStandard = i;
    }

    public static int getDeviceIsLargeScreenStandard() {
        return sDeviceIsLargeScreenStandard;
    }

    public static int getSizeBySpeed(float f) {
        float f2 = DEFAULT_DURATION / 1000.0f;
        return (int) (((((f2 - f) / f2) * ACC_DENOMINATOR) / ACCLERATE) + ACTIVATE_ACC_SIZE);
    }

    public static float getBarrageAlpha() {
        return readBarrageAlpha(0.75f);
    }

    public static int getBarrageSize() {
        return DensityUtil.dip2px(BarrageContext.gContext, getBarrageSizeDpBySection(getBarrageSizeSection(), true));
    }

    public static int getVerticalBarrageSize() {
        return DensityUtil.dip2px(BarrageContext.gContext, getBarrageSizeDpBySection(getBarrageSizeSection(), false));
    }

    public static int getBarrageSizeBySection(int i, boolean z) {
        return DensityUtil.dip2px(BarrageContext.gContext, getBarrageSizeDpBySection(i, z));
    }

    public static int covertBarrageSizeFromDp(int i) {
        return DensityUtil.dip2px(BarrageContext.gContext, i);
    }

    public static int getBarrageSizeDpBySection(int i, boolean z) {
        int i2 = 17;
        if (i == 0) {
            i2 = 15;
        } else if (i == 1) {
            i2 = 16;
        } else if (i != 2) {
            if (i == 3) {
                i2 = 18;
            } else if (i == 4) {
                i2 = 19;
            }
        }
        if (!z) {
            return i2 - 1;
        }
        if (BarrageContext.gContext.getResources().getDisplayMetrics().densityDpi < sDeviceIsLargeScreenStandard) {
            return i2;
        }
        BarrageLog.info(TAG, "getBarrageSizeDpBySection: device has large screen");
        return i2 + 1;
    }

    public static int getVerticalBarrageSizeByType(int i) {
        if (i == 1) {
            return PORTRAIT_SIZE_SMALL;
        }
        if (i != 3) {
            return PORTRAIT_SIZE_MEDIUM;
        }
        return PORTRAIT_SIZE_BIG;
    }

    public static int getBarrageSizeByType(int i) {
        if (i == 1) {
            return LANDSCAPE_SIZE_SMALL;
        }
        if (i != 3) {
            return LANDSCAPE_SIZE_MEDIUM;
        }
        return LANDSCAPE_SIZE_BIG;
    }

    public static int getBarrageSizeType() {
        return readBarrageSizeType();
    }

    public static int getBarrageModel() {
        return readBarrageModel();
    }

    public static void saveVideoBarrageModel(int i) {
        sCurrentVideoBarrageStatus = i;
        Config.getInstance(BarrageContext.gContext, Channel_Config).setIntSync(Video_Barrage_Model, i);
        sVideoBarrageStatusDp.set(Boolean.valueOf(!r2.get().booleanValue()));
    }

    public static int getVideoBarrageModel() {
        return sCurrentVideoBarrageStatus;
    }

    public static int readBarrageModel() {
        return Config.getInstance(BarrageContext.gContext, Channel_Config).getInt(Barrage_Model, 1);
    }

    public static void saveBarrageModel(int i) {
        Config.getInstance(BarrageContext.gContext, Channel_Config).setInt(Barrage_Model, i);
    }

    public static int readVideoBarrageModel() {
        return Config.getInstance(BarrageContext.gContext, Channel_Config).getInt(Video_Barrage_Model, 1);
    }

    public static void saveBarrageAlpha(float f) {
        Config.getInstance(BarrageContext.gContext, Channel_Config).setFloat(Barrage_Alpha, f);
    }

    public static float readBarrageAlpha(float f) {
        return Config.getInstance(BarrageContext.gContext, Channel_Config).getFloat(Barrage_Alpha, f);
    }

    public static void saveBarrageSizeType(int i) {
        Config.getInstance(BarrageContext.gContext, Channel_Config).setInt(Barrage_Size_Type, i);
    }

    public static void saveBarrageSizeSection(int i) {
        Config.getInstance(BarrageContext.gContext, Channel_Config).setInt(Barrage_Size_Section, i);
    }

    public static int getBarrageSizeSection() {
        int i = Config.getInstance(BarrageContext.gContext, Channel_Config).getInt(Barrage_Size_Section, -1);
        if (i < 0) {
            int i2 = Config.getInstance(BarrageContext.gContext, Channel_Config).getInt(Barrage_Size_Type, 2);
            i = i2 != 1 ? i2 != 2 ? 3 : 2 : 0;
            saveBarrageSizeSection(i);
        }
        return i;
    }

    public static int readBarrageSizeType() {
        int i = Config.getInstance(BarrageContext.gContext, Channel_Config).getInt(Barrage_Size, 0);
        if (i != 0) {
            BarrageLog.info(TAG, "enter getInstance().mSetting.getInt(Barrage_Old_Version, 0) != 0");
            if (i == LANDSCAPE_SIZE_BIG_OLD) {
                saveBarrageSizeType(3);
                BarrageLog.info(TAG, "saveBarrageSizeType(BarrageConfig.LANDSCAPE_SIZE_BIG);");
            } else if (i == LANDSCAPE_SIZE_MEDIUM_OLD) {
                saveBarrageSizeType(2);
                BarrageLog.info(TAG, "saveBarrageSizeType(BarrageConfig.LANDSCAPE_SIZE_Middle);");
            } else if (i == LANDSCAPE_SIZE_SMALL_OLD) {
                saveBarrageSizeType(1);
                BarrageLog.info(TAG, "saveBarrageSize(BarrageConfig.LANDSCAPE_SIZE_SMALL);");
            }
        }
        return Config.getInstance(BarrageContext.gContext, Channel_Config).getInt(Barrage_Size_Type, 2);
    }

    public static boolean isAntiBlockHasTip() {
        return Config.getInstance(BarrageContext.gContext, Channel_Config).getBoolean(ANTI_BLOCK_HAS_TIP, false);
    }

    public static void setAntiBlockHasTip() {
        Config.getInstance(BarrageContext.gContext, Channel_Config).setBoolean(ANTI_BLOCK_HAS_TIP, true);
    }

    public static void setAntiBlockEnable(boolean z) {
        Config.getInstance(BarrageContext.gContext, Channel_Config).setBoolean(ANTI_BLOCK_ENABLE, z);
    }

    public static boolean isAntiBlockEnable() {
        return Config.getInstance(BarrageContext.gContext, Channel_Config).getBoolean(ANTI_BLOCK_ENABLE, false);
    }

    public static void setAntiBlockNowStatus(boolean z) {
        sAntiBlockNowStatus = z;
    }

    public static boolean getAntiBlockNowStatus() {
        return sAntiBlockNowStatus;
    }

    public static boolean isAntiBlockNeverUse() {
        return Config.getInstance(BarrageContext.gContext, Channel_Config).getBoolean(ANTI_BLOCK_NEVER_USE, true);
    }

    public static void setAntiBlcokHasUsed() {
        Config.getInstance(BarrageContext.gContext, Channel_Config).setBoolean(ANTI_BLOCK_NEVER_USE, false);
    }

    public static int getAntiBlockToastTipCount() {
        return Config.getInstance(BarrageContext.gContext, Channel_Config).getInt(ANTI_BLOCK_TOAST_TIP_COUNT, 0);
    }

    public static void setAntiBlockToastTipCount(int i) {
        Config.getInstance(BarrageContext.gContext, Channel_Config).setInt(ANTI_BLOCK_TOAST_TIP_COUNT, i);
    }

    public static long getLastReportAntiBlockStatusTime() {
        return Config.getInstance(BarrageContext.gContext, Channel_Config).getLong(LAST_REPORT_ANTI_BLOCK_STATUS_TIME, 0L);
    }

    public static void setLastReportAntiBlockStatusTime(long j) {
        Config.getInstance(BarrageContext.gContext, Channel_Config).setLong(LAST_REPORT_ANTI_BLOCK_STATUS_TIME, j);
    }

    public static float read(String str, float f) {
        return Config.getInstance(BarrageContext.gContext, Channel_Config).getFloat(str, f);
    }

    public static void save(String str, float f) {
        Config.getInstance(BarrageContext.gContext, Channel_Config).setFloat(str, f);
    }

    public static int read(String str, int i) {
        return Config.getInstance(BarrageContext.gContext, Channel_Config).getInt(str, i);
    }

    public static void save(String str, int i) {
        Config.getInstance(BarrageContext.gContext, Channel_Config).setInt(str, i);
    }

    public static boolean isBarrageRefreshPrint() {
        return mBarrageRefreshPrint;
    }

    public static void setBarrageRefreshPrint(boolean z) {
        mBarrageRefreshPrint = z;
    }

    public static void setFixedQueue(boolean z) {
        sIsFixedQueue = z;
    }

    public static boolean isFixedQueue() {
        return sIsFixedQueue;
    }

    public static void setFixedLine(int i) {
        sFixedLine = i;
    }

    public static int getFixedLine() {
        return sFixedLine;
    }

    static {
        ACTIVATE_ACC_SIZE = 1;
        ACC_DENOMINATOR = 75;
        OPEN_RANDOM_SPEED_SIZE = 35;
        ACCLERATE = 0.35f;
        OPEN_DOUBLE_SPEED = 5.7f;
        CLOSE_DOUBLE_SPEED = 8.0f;
        DOUBLE_WHEN_SHELL_CACHE_SIZES = 71;
        SINGLE_WHEN_SHELL_CACHE_SIZES = 13;
        sAntiBlockNowStatus = false;
        sAntiBlockNowStatus = isAntiBlockEnable();
        ACTIVATE_ACC_SIZE = read(KEY_ACTIVATE_ACC_SIZE, ACTIVATE_ACC_SIZE);
        ACC_DENOMINATOR = read(KEY_ACC_DENOMINATOR, ACC_DENOMINATOR);
        OPEN_RANDOM_SPEED_SIZE = read(KEY_OPEN_RANDOM_SPEED_SIZE, OPEN_RANDOM_SPEED_SIZE);
        ACCLERATE = read(KEY_ACCLERATE, ACCLERATE);
        OPEN_DOUBLE_SPEED = read(KEY_OPEN_DOUBLE_SPEED, OPEN_DOUBLE_SPEED);
        CLOSE_DOUBLE_SPEED = read(KEY_CLOSE_DOUBLE_SPEED, CLOSE_DOUBLE_SPEED);
        DOUBLE_WHEN_SHELL_CACHE_SIZES = getSizeBySpeed(OPEN_DOUBLE_SPEED);
        SINGLE_WHEN_SHELL_CACHE_SIZES = getSizeBySpeed(CLOSE_DOUBLE_SPEED);
    }
}
