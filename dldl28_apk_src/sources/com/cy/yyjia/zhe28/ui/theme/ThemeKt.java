package com.cy.yyjia.zhe28.ui.theme;

import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.graphics.Color;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Theme.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0010\u0006\u001a\u00020\u00072\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u000b\u001a \u0010\f\u001a\u00020\u00072\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u000b\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003¨\u0006\r"}, d2 = {"colorScheme", "Landroidx/compose/material3/ColorScheme;", "getColorScheme", "()Landroidx/compose/material3/ColorScheme;", "transparentColorScheme", "getTransparentColorScheme", "TransparentTheme", "", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "Zhe28Theme", "app_zhe28Release"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class ThemeKt {
    private static final ColorScheme colorScheme = ColorSchemeKt.m1443lightColorSchemeCXl9yA$default(androidx.compose.ui.graphics.ColorKt.Color(4294469944L), 0, 0, 0, 0, androidx.compose.ui.graphics.ColorKt.Color(4294082880L), 0, 0, 0, ColorKt.getPink40(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -546, 15, null);
    private static final ColorScheme transparentColorScheme = ColorSchemeKt.m1443lightColorSchemeCXl9yA$default(androidx.compose.ui.graphics.ColorKt.Color(4294469944L), 0, 0, 0, 0, androidx.compose.ui.graphics.ColorKt.Color(4294082880L), 0, 0, 0, ColorKt.getPink40(), 0, 0, 0, Color.INSTANCE.m3529getTransparent0d7_KjU(), 0, Color.INSTANCE.m3529getTransparent0d7_KjU(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -41506, 15, null);

    public static final ColorScheme getColorScheme() {
        return colorScheme;
    }

    public static final ColorScheme getTransparentColorScheme() {
        return transparentColorScheme;
    }

    public static final void Zhe28Theme(final Function2<? super Composer, ? super Integer, Unit> content, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1533689478);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Zhe28Theme)");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(content) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1533689478, i2, -1, "com.cy.yyjia.zhe28.ui.theme.Zhe28Theme (Theme.kt:40)");
            }
            MaterialThemeKt.MaterialTheme(colorScheme, null, TypeKt.getTypography(), content, composerStartRestartGroup, ((i2 << 9) & 7168) | 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.theme.ThemeKt.Zhe28Theme.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i3) {
                ThemeKt.Zhe28Theme(content, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            }
        });
    }

    public static final void TransparentTheme(final Function2<? super Composer, ? super Integer, Unit> content, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1955797873);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(TransparentTheme)");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(content) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 11) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1955797873, i2, -1, "com.cy.yyjia.zhe28.ui.theme.TransparentTheme (Theme.kt:50)");
            }
            MaterialThemeKt.MaterialTheme(transparentColorScheme, null, TypeKt.getTypography(), content, composerStartRestartGroup, ((i2 << 9) & 7168) | 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.theme.ThemeKt.TransparentTheme.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i3) {
                ThemeKt.TransparentTheme(content, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            }
        });
    }
}
