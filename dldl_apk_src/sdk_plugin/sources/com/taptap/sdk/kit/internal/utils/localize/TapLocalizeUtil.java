package com.taptap.sdk.kit.internal.utils.localize;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqwan.common.constants.SqConstants;
import com.taptap.sdk.initializer.api.model.RegionType;
import com.taptap.sdk.kit.internal.TapTapKit;
import com.taptap.sdk.kit.internal.utils.DeviceUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TapLocalizeUtil.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\fH\u0002¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u001a\u0010\u0012\u001a\u00020\u00132\b\b\u0001\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0007J3\u0010\u0012\u001a\u00020\u00132\b\b\u0001\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0011H\u0002J\u0012\u0010\u001c\u001a\u00020\u00132\b\b\u0001\u0010\u0014\u001a\u00020\u0015H\u0007J+\u0010\u001c\u001a\u00020\u00132\b\b\u0001\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0017\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f\"\u00020\u0001H\u0007¢\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u00020\u0004H\u0007J\b\u0010\u001f\u001a\u00020\u0011H\u0002J\u0016\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0013J\u000e\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\nJ\u000e\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u0013J\u000e\u0010(\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020\nJ\u0012\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010*R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\u00020\u00068\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/taptap/sdk/kit/internal/utils/localize/TapLocalizeUtil;", "", "()V", "currentLanguage", "Lcom/taptap/sdk/kit/internal/utils/localize/TapLanguageInternal;", "isDomestic", "", "isDomestic$annotations", "languageChangeListeners", "", "Lcom/taptap/sdk/kit/internal/utils/localize/ITapLanguageChangeListener;", "collectTapLanguageChangeListeners", "", "()[Lcom/taptap/sdk/kit/internal/utils/localize/ITapLanguageChangeListener;", "dispatchLanguageChange", "", "getDefaultLocale", "Ljava/util/Locale;", "getLocalizedQuantityString", "", SqTrackCommonKey.id, "", "quantity", "formatArgs", "(II[Ljava/lang/Object;)Ljava/lang/String;", "getLocalizedResources", "Landroid/content/res/Resources;", SqConstants.LOCALE, "getLocalizedString", "(I[Ljava/lang/Object;)Ljava/lang/String;", "getPreferredLanguage", "getPreferredLocale", "initialize", "regionType", "Lcom/taptap/sdk/initializer/api/model/RegionType;", "preferredLanguage", "registerLanguageChangeListener", "listener", "setPreferredLanguage", "language", "unregisterLanguageChangeListener", "wrapperContext", "Landroid/content/Context;", "context", "tap-common_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class TapLocalizeUtil {
    public static final TapLocalizeUtil INSTANCE = new TapLocalizeUtil();
    private static boolean isDomestic = true;
    private static TapLanguageInternal currentLanguage = TapLanguageInternal.AUTO;
    private static final List<ITapLanguageChangeListener> languageChangeListeners = new ArrayList();

    /* JADX INFO: compiled from: TapLocalizeUtil.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TapLanguageInternal.values().length];
            try {
                iArr[TapLanguageInternal.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TapLanguageInternal.ZH_HANS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TapLanguageInternal.ZH_HANT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TapLanguageInternal.EN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[TapLanguageInternal.JA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[TapLanguageInternal.KO.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[TapLanguageInternal.TH.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[TapLanguageInternal.DE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[TapLanguageInternal.PT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[TapLanguageInternal.VI.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[TapLanguageInternal.ID.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[TapLanguageInternal.ES.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[TapLanguageInternal.FR.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[TapLanguageInternal.RU.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[TapLanguageInternal.TR.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    private static /* synthetic */ void isDomestic$annotations() {
    }

    private TapLocalizeUtil() {
    }

    public final void initialize(RegionType regionType, String preferredLanguage) {
        Intrinsics.checkNotNullParameter(regionType, "regionType");
        Intrinsics.checkNotNullParameter(preferredLanguage, "preferredLanguage");
        isDomestic = regionType == RegionType.CN;
        setPreferredLanguage(preferredLanguage);
    }

    @JvmStatic
    public static final TapLanguageInternal getPreferredLanguage() {
        if (currentLanguage == TapLanguageInternal.AUTO) {
            String curLanguageDisplayName = DeviceUtils.getCurLanguageDisplayName();
            Intrinsics.checkNotNullExpressionValue(curLanguageDisplayName, "curLanguageDisplayName");
            if (StringsKt.startsWith$default(curLanguageDisplayName, "zh", false, 2, (Object) null)) {
                Locale US = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US, "US");
                String lowerCase = curLanguageDisplayName.toLowerCase(US);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                String str = lowerCase;
                return StringsKt.contains$default((CharSequence) str, (CharSequence) "hant", false, 2, (Object) null) ? TapLanguageInternal.ZH_HANT : StringsKt.contains$default((CharSequence) str, (CharSequence) "hans", false, 2, (Object) null) ? TapLanguageInternal.ZH_HANS : !StringsKt.contains$default((CharSequence) str, (CharSequence) "cn", false, 2, (Object) null) ? TapLanguageInternal.ZH_HANT : TapLanguageInternal.ZH_HANS;
            }
            if (StringsKt.startsWith$default(curLanguageDisplayName, "en", false, 2, (Object) null)) {
                return TapLanguageInternal.EN;
            }
            if (StringsKt.startsWith$default(curLanguageDisplayName, "ja", false, 2, (Object) null)) {
                return TapLanguageInternal.JA;
            }
            if (StringsKt.startsWith$default(curLanguageDisplayName, "ko", false, 2, (Object) null)) {
                return TapLanguageInternal.KO;
            }
            if (StringsKt.startsWith$default(curLanguageDisplayName, "th", false, 2, (Object) null)) {
                return TapLanguageInternal.TH;
            }
            if (StringsKt.startsWith$default(curLanguageDisplayName, "de", false, 2, (Object) null)) {
                return TapLanguageInternal.DE;
            }
            if (StringsKt.startsWith$default(curLanguageDisplayName, "es", false, 2, (Object) null)) {
                return TapLanguageInternal.ES;
            }
            if (StringsKt.startsWith$default(curLanguageDisplayName, "fr", false, 2, (Object) null)) {
                return TapLanguageInternal.FR;
            }
            if (StringsKt.startsWith$default(curLanguageDisplayName, "pt", false, 2, (Object) null)) {
                return TapLanguageInternal.PT;
            }
            if (StringsKt.startsWith$default(curLanguageDisplayName, "ru", false, 2, (Object) null)) {
                return TapLanguageInternal.RU;
            }
            if (StringsKt.startsWith$default(curLanguageDisplayName, "tr", false, 2, (Object) null)) {
                return TapLanguageInternal.TR;
            }
            if (StringsKt.startsWith$default(curLanguageDisplayName, "vi", false, 2, (Object) null)) {
                return TapLanguageInternal.VI;
            }
            if (StringsKt.startsWith$default(curLanguageDisplayName, SqTrackCommonKey.id, false, 2, (Object) null) || StringsKt.startsWith$default(curLanguageDisplayName, "in", false, 2, (Object) null)) {
                return TapLanguageInternal.ID;
            }
            if (isDomestic) {
                return TapLanguageInternal.ZH_HANS;
            }
            return TapLanguageInternal.EN;
        }
        return currentLanguage;
    }

    @JvmStatic
    public static final String getLocalizedString(int id) throws Resources.NotFoundException {
        TapLocalizeUtil tapLocalizeUtil = INSTANCE;
        String string = tapLocalizeUtil.getLocalizedResources(tapLocalizeUtil.getPreferredLocale()).getString(id);
        Intrinsics.checkNotNullExpressionValue(string, "getLocalizedResources(ge…edLocale()).getString(id)");
        return string;
    }

    @JvmStatic
    public static final String getLocalizedString(int id, Object... formatArgs) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(formatArgs, "formatArgs");
        TapLocalizeUtil tapLocalizeUtil = INSTANCE;
        String string = tapLocalizeUtil.getLocalizedResources(tapLocalizeUtil.getPreferredLocale()).getString(id, Arrays.copyOf(formatArgs, formatArgs.length));
        Intrinsics.checkNotNullExpressionValue(string, "getLocalizedResources(ge…etString(id, *formatArgs)");
        return string;
    }

    @JvmStatic
    public static final String getLocalizedQuantityString(int id, int quantity) throws Resources.NotFoundException {
        TapLocalizeUtil tapLocalizeUtil = INSTANCE;
        String quantityString = tapLocalizeUtil.getLocalizedResources(tapLocalizeUtil.getPreferredLocale()).getQuantityString(id, quantity);
        Intrinsics.checkNotNullExpressionValue(quantityString, "getLocalizedResources(ge…ntityString(id, quantity)");
        return quantityString;
    }

    @JvmStatic
    public static final String getLocalizedQuantityString(int id, int quantity, Object... formatArgs) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(formatArgs, "formatArgs");
        TapLocalizeUtil tapLocalizeUtil = INSTANCE;
        String quantityString = tapLocalizeUtil.getLocalizedResources(tapLocalizeUtil.getPreferredLocale()).getQuantityString(id, quantity, Arrays.copyOf(formatArgs, formatArgs.length));
        Intrinsics.checkNotNullExpressionValue(quantityString, "getLocalizedResources(ge…d, quantity, *formatArgs)");
        return quantityString;
    }

    public final void registerLanguageChangeListener(ITapLanguageChangeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        synchronized (languageChangeListeners) {
            languageChangeListeners.add(listener);
        }
    }

    public final void unregisterLanguageChangeListener(ITapLanguageChangeListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        synchronized (languageChangeListeners) {
            languageChangeListeners.remove(listener);
        }
    }

    public final void setPreferredLanguage(String language) {
        TapLanguageInternal tapLanguageInternal;
        TapLanguageInternal tapLanguageInternal2;
        Intrinsics.checkNotNullParameter(language, "language");
        TapLanguageInternal[] tapLanguageInternalArrValues = TapLanguageInternal.values();
        int length = tapLanguageInternalArrValues.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                tapLanguageInternal = null;
                break;
            }
            tapLanguageInternal = tapLanguageInternalArrValues[i];
            if (Intrinsics.areEqual(tapLanguageInternal.getLanguage(), language)) {
                break;
            } else {
                i++;
            }
        }
        if (tapLanguageInternal == null) {
            if (isDomestic) {
                tapLanguageInternal2 = TapLanguageInternal.ZH_HANS;
            } else {
                tapLanguageInternal2 = TapLanguageInternal.EN;
            }
            tapLanguageInternal = tapLanguageInternal2;
        }
        if (tapLanguageInternal != currentLanguage) {
            currentLanguage = tapLanguageInternal;
            try {
                Result.Companion companion = Result.INSTANCE;
                INSTANCE.dispatchLanguageChange();
                Result.m52constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m52constructorimpl(ResultKt.createFailure(th));
            }
        }
    }

    public final Context wrapperContext(Context context) {
        if (context == null) {
            return null;
        }
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 24) {
            configuration.setLocales(new LocaleList(getPreferredLocale()));
        } else {
            configuration.setLocale(getPreferredLocale());
        }
        return context.createConfigurationContext(configuration);
    }

    private final void dispatchLanguageChange() {
        ITapLanguageChangeListener[] iTapLanguageChangeListenerArrCollectTapLanguageChangeListeners = collectTapLanguageChangeListeners();
        if (iTapLanguageChangeListenerArrCollectTapLanguageChangeListeners != null) {
            for (ITapLanguageChangeListener iTapLanguageChangeListener : iTapLanguageChangeListenerArrCollectTapLanguageChangeListeners) {
                iTapLanguageChangeListener.onLanguageChange(isDomestic, currentLanguage);
            }
        }
    }

    private final ITapLanguageChangeListener[] collectTapLanguageChangeListeners() {
        Object[] array;
        synchronized (languageChangeListeners) {
            array = languageChangeListeners.size() > 0 ? languageChangeListeners.toArray(new ITapLanguageChangeListener[0]) : null;
            Unit unit = Unit.INSTANCE;
        }
        return (ITapLanguageChangeListener[]) array;
    }

    private final Locale getPreferredLocale() {
        switch (WhenMappings.$EnumSwitchMapping$0[getPreferredLanguage().ordinal()]) {
            case 1:
                return getDefaultLocale();
            case 2:
                Locale SIMPLIFIED_CHINESE = Locale.SIMPLIFIED_CHINESE;
                Intrinsics.checkNotNullExpressionValue(SIMPLIFIED_CHINESE, "SIMPLIFIED_CHINESE");
                return SIMPLIFIED_CHINESE;
            case 3:
                Locale TRADITIONAL_CHINESE = Locale.TRADITIONAL_CHINESE;
                Intrinsics.checkNotNullExpressionValue(TRADITIONAL_CHINESE, "TRADITIONAL_CHINESE");
                return TRADITIONAL_CHINESE;
            case 4:
                Locale ENGLISH = Locale.ENGLISH;
                Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
                return ENGLISH;
            case 5:
                Locale JAPANESE = Locale.JAPANESE;
                Intrinsics.checkNotNullExpressionValue(JAPANESE, "JAPANESE");
                return JAPANESE;
            case 6:
                Locale KOREAN = Locale.KOREAN;
                Intrinsics.checkNotNullExpressionValue(KOREAN, "KOREAN");
                return KOREAN;
            case 7:
                return new Locale("th");
            case 8:
                Locale GERMAN = Locale.GERMAN;
                Intrinsics.checkNotNullExpressionValue(GERMAN, "GERMAN");
                return GERMAN;
            case 9:
                return new Locale("pt");
            case 10:
                return new Locale("vi");
            case 11:
                return new Locale(SqTrackCommonKey.id);
            case 12:
                return new Locale("es");
            case 13:
                Locale FRENCH = Locale.FRENCH;
                Intrinsics.checkNotNullExpressionValue(FRENCH, "FRENCH");
                return FRENCH;
            case 14:
                return new Locale("ru");
            case 15:
                return new Locale("tr");
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final Locale getDefaultLocale() {
        if (isDomestic) {
            Locale locale = Locale.SIMPLIFIED_CHINESE;
            Intrinsics.checkNotNullExpressionValue(locale, "{\n            Locale.SIMPLIFIED_CHINESE\n        }");
            return locale;
        }
        Locale locale2 = Locale.ENGLISH;
        Intrinsics.checkNotNullExpressionValue(locale2, "{\n            Locale.ENGLISH\n        }");
        return locale2;
    }

    private final Resources getLocalizedResources(Locale locale) {
        Configuration configuration = new Configuration(TapTapKit.INSTANCE.getContext().getResources().getConfiguration());
        configuration.setLocale(locale);
        Resources resources = TapTapKit.INSTANCE.getContext().createConfigurationContext(configuration).getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "TapTapKit.context.create…Context(config).resources");
        return resources;
    }
}
