package androidx.compose.material3;

import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchBar.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0087\u0002\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\"0&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\"0&2\u0006\u0010(\u001a\u00020)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\"0&2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020)2\u0015\b\u0002\u0010.\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u00020\u00102\b\b\u0002\u00108\u001a\u00020\u00102\b\b\u0002\u00109\u001a\u00020:2\u001c\u0010;\u001a\u0018\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\"0&¢\u0006\u0002\b0¢\u0006\u0002\b=H\u0007ø\u0001\u0000¢\u0006\u0004\b>\u0010?\u001a\u0091\u0002\u0010@\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\"0&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\"0&2\u0006\u0010(\u001a\u00020)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\"0&2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020)2\u0015\b\u0002\u0010.\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u00020\u00102\b\b\u0002\u00108\u001a\u00020\u00102\b\b\u0002\u0010A\u001a\u00020B2\b\b\u0002\u00109\u001a\u00020:2\u001c\u0010;\u001a\u0018\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\"0&¢\u0006\u0002\b0¢\u0006\u0002\b=H\u0007ø\u0001\u0000¢\u0006\u0004\bC\u0010D\u001aÆ\u0001\u0010E\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\"0&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\"0&2\u0006\u0010(\u001a\u00020)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\"0&2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020)2\u0015\b\u0002\u0010.\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u00101\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\"\u0018\u00010/¢\u0006\u0002\b02\b\b\u0002\u00105\u001a\u00020F2\b\b\u0002\u00109\u001a\u00020:H\u0003¢\u0006\u0002\u0010G\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000e\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u000f\u001a\u00020\u0010X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012\"\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0018\u001a\u00020\u0010X\u0082\u0004¢\u0006\n\n\u0002\u0010\u0013\u0012\u0004\b\u0019\u0010\u001a\"\u0010\u0010\u001b\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013\"\u0010\u0010\u001c\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013\"\u0016\u0010\u001d\u001a\u00020\u0010X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u001e\u0010\u0012\"\u0016\u0010\u001f\u001a\u00020\u0010X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b \u0010\u0012\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006H²\u0006\n\u0010I\u001a\u00020)X\u008a\u0084\u0002²\u0006\n\u0010J\u001a\u00020)X\u008a\u0084\u0002"}, d2 = {"AnimationDelayMillis", "", "AnimationEnterDurationMillis", "AnimationEnterEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "AnimationEnterFloatSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "AnimationEnterSizeSpec", "Landroidx/compose/ui/unit/IntSize;", "AnimationExitDurationMillis", "AnimationExitEasing", "AnimationExitFloatSpec", "AnimationExitSizeSpec", "DockedActiveTableMaxHeightScreenRatio", "DockedActiveTableMinHeight", "Landroidx/compose/ui/unit/Dp;", "getDockedActiveTableMinHeight", "()F", "F", "DockedEnterTransition", "Landroidx/compose/animation/EnterTransition;", "DockedExitTransition", "Landroidx/compose/animation/ExitTransition;", "SearchBarCornerRadius", "getSearchBarCornerRadius$annotations", "()V", "SearchBarIconOffsetX", "SearchBarMaxWidth", "SearchBarMinWidth", "getSearchBarMinWidth", "SearchBarVerticalPadding", "getSearchBarVerticalPadding", "DockedSearchBar", "", "query", "", "onQueryChange", "Lkotlin/Function1;", "onSearch", "active", "", "onActiveChange", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "placeholder", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "leadingIcon", "trailingIcon", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/SearchBarColors;", "tonalElevation", "shadowElevation", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "DockedSearchBar-eWTbjVg", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "SearchBar", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "SearchBar-WuY5d9Q", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "SearchBarInputField", "Landroidx/compose/material3/TextFieldColors;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "material3_release", "useFullScreenShape", "showResults"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SearchBar_androidKt {
    private static final int AnimationDelayMillis = 100;
    private static final int AnimationEnterDurationMillis = 600;
    private static final CubicBezierEasing AnimationEnterEasing;
    private static final FiniteAnimationSpec<Float> AnimationEnterFloatSpec;
    private static final FiniteAnimationSpec<IntSize> AnimationEnterSizeSpec;
    private static final int AnimationExitDurationMillis = 350;
    private static final CubicBezierEasing AnimationExitEasing;
    private static final FiniteAnimationSpec<Float> AnimationExitFloatSpec;
    private static final FiniteAnimationSpec<IntSize> AnimationExitSizeSpec;
    private static final float DockedActiveTableMaxHeightScreenRatio = 0.6666667f;
    private static final EnterTransition DockedEnterTransition;
    private static final ExitTransition DockedExitTransition;
    private static final float SearchBarCornerRadius = Dp.m5882constructorimpl(SearchBarDefaults.INSTANCE.m1837getInputFieldHeightD9Ej5fM() / 2);
    private static final float DockedActiveTableMinHeight = Dp.m5882constructorimpl(KeyBoardKey.KeyboardKeyOemAttn);
    private static final float SearchBarMinWidth = Dp.m5882constructorimpl(360);
    private static final float SearchBarMaxWidth = Dp.m5882constructorimpl(720);
    private static final float SearchBarVerticalPadding = Dp.m5882constructorimpl(8);
    private static final float SearchBarIconOffsetX = Dp.m5882constructorimpl(4);

    private static /* synthetic */ void getSearchBarCornerRadius$annotations() {
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x035a  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0376  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x049e  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x050c  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0514  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x054d  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x05ec  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0615  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0640  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0653  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x066d  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x068a  */
    /* JADX WARN: Removed duplicated region for block: B:331:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0127  */
    /* JADX INFO: renamed from: SearchBar-WuY5d9Q, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1843SearchBarWuY5d9Q(final java.lang.String r42, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r43, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r44, final boolean r45, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r46, androidx.compose.ui.Modifier r47, boolean r48, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r49, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r50, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, androidx.compose.ui.graphics.Shape r52, androidx.compose.material3.SearchBarColors r53, float r54, float r55, androidx.compose.foundation.layout.WindowInsets r56, androidx.compose.foundation.interaction.MutableInteractionSource r57, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r58, androidx.compose.runtime.Composer r59, final int r60, final int r61, final int r62) {
        /*
            Method dump skipped, instruction units count: 1713
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.m1843SearchBarWuY5d9Q(java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SearchBarColors, float, float, androidx.compose.foundation.layout.WindowInsets, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x043e  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0440  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0453  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:258:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0124  */
    /* JADX INFO: renamed from: DockedSearchBar-eWTbjVg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1842DockedSearchBareWTbjVg(final java.lang.String r45, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r46, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r47, final boolean r48, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r49, androidx.compose.ui.Modifier r50, boolean r51, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r52, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r53, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r54, androidx.compose.ui.graphics.Shape r55, androidx.compose.material3.SearchBarColors r56, float r57, float r58, androidx.compose.foundation.interaction.MutableInteractionSource r59, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r60, androidx.compose.runtime.Composer r61, final int r62, final int r63, final int r64) {
        /*
            Method dump skipped, instruction units count: 1200
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.m1842DockedSearchBareWTbjVg(java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SearchBarColors, float, float, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:106:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x043f  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0441  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0447  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x04f0  */
    /* JADX WARN: Removed duplicated region for block: B:218:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SearchBarInputField(final java.lang.String r82, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r83, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r84, final boolean r85, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r86, androidx.compose.ui.Modifier r87, boolean r88, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r89, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r90, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r91, androidx.compose.material3.TextFieldColors r92, androidx.compose.foundation.interaction.MutableInteractionSource r93, androidx.compose.runtime.Composer r94, final int r95, final int r96, final int r97) {
        /*
            Method dump skipped, instruction units count: 1299
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.SearchBarInputField(java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.material3.TextFieldColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }

    static {
        CubicBezierEasing easingEmphasizedDecelerateCubicBezier = MotionTokens.INSTANCE.getEasingEmphasizedDecelerateCubicBezier();
        AnimationEnterEasing = easingEmphasizedDecelerateCubicBezier;
        CubicBezierEasing cubicBezierEasing = new CubicBezierEasing(0.0f, 1.0f, 0.0f, 1.0f);
        AnimationExitEasing = cubicBezierEasing;
        TweenSpec tweenSpecTween = AnimationSpecKt.tween(600, 100, easingEmphasizedDecelerateCubicBezier);
        AnimationEnterFloatSpec = tweenSpecTween;
        TweenSpec tweenSpecTween2 = AnimationSpecKt.tween(AnimationExitDurationMillis, 100, cubicBezierEasing);
        AnimationExitFloatSpec = tweenSpecTween2;
        TweenSpec tweenSpecTween3 = AnimationSpecKt.tween(600, 100, easingEmphasizedDecelerateCubicBezier);
        AnimationEnterSizeSpec = tweenSpecTween3;
        TweenSpec tweenSpecTween4 = AnimationSpecKt.tween(AnimationExitDurationMillis, 100, cubicBezierEasing);
        AnimationExitSizeSpec = tweenSpecTween4;
        DockedEnterTransition = EnterExitTransitionKt.fadeIn$default(tweenSpecTween, 0.0f, 2, null).plus(EnterExitTransitionKt.expandVertically$default(tweenSpecTween3, null, false, null, 14, null));
        DockedExitTransition = EnterExitTransitionKt.fadeOut$default(tweenSpecTween2, 0.0f, 2, null).plus(EnterExitTransitionKt.shrinkVertically$default(tweenSpecTween4, null, false, null, 14, null));
    }

    public static final float getDockedActiveTableMinHeight() {
        return DockedActiveTableMinHeight;
    }

    public static final float getSearchBarMinWidth() {
        return SearchBarMinWidth;
    }

    public static final float getSearchBarVerticalPadding() {
        return SearchBarVerticalPadding;
    }

    private static final boolean SearchBar_WuY5d9Q$lambda$2(State<Boolean> state) {
        return state.getValue().booleanValue();
    }
}
