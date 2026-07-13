package androidx.compose.ui.text.input;

import com.bytedance.framwork.core.sdklib.DBHelper;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: KeyboardType.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\f\u0010\u0005J\u000f\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/text/input/KeyboardType;", "", DBHelper.COL_VALUE, "", "constructor-impl", "(I)I", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "Companion", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
public final class KeyboardType {
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int Text = m5580constructorimpl(1);
    private static final int Ascii = m5580constructorimpl(2);
    private static final int Number = m5580constructorimpl(3);
    private static final int Phone = m5580constructorimpl(4);
    private static final int Uri = m5580constructorimpl(5);
    private static final int Email = m5580constructorimpl(6);
    private static final int Password = m5580constructorimpl(7);
    private static final int NumberPassword = m5580constructorimpl(8);
    private static final int Decimal = m5580constructorimpl(9);

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ KeyboardType m5579boximpl(int i) {
        return new KeyboardType(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m5580constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m5581equalsimpl(int i, Object obj) {
        return (obj instanceof KeyboardType) && i == ((KeyboardType) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5582equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m5583hashCodeimpl(int i) {
        return i;
    }

    public boolean equals(Object obj) {
        return m5581equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m5583hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }

    private /* synthetic */ KeyboardType(int i) {
        this.value = i;
    }

    public String toString() {
        return m5584toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m5584toStringimpl(int i) {
        return m5582equalsimpl0(i, Text) ? "Text" : m5582equalsimpl0(i, Ascii) ? "Ascii" : m5582equalsimpl0(i, Number) ? "Number" : m5582equalsimpl0(i, Phone) ? "Phone" : m5582equalsimpl0(i, Uri) ? "Uri" : m5582equalsimpl0(i, Email) ? "Email" : m5582equalsimpl0(i, Password) ? "Password" : m5582equalsimpl0(i, NumberPassword) ? "NumberPassword" : m5582equalsimpl0(i, Decimal) ? "Decimal" : "Invalid";
    }

    /* JADX INFO: compiled from: KeyboardType.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R$\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\u0007R$\u0010\f\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\r\u0010\u0002\u001a\u0004\b\u000e\u0010\u0007R$\u0010\u000f\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0010\u0010\u0002\u001a\u0004\b\u0011\u0010\u0007R$\u0010\u0012\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0013\u0010\u0002\u001a\u0004\b\u0014\u0010\u0007R$\u0010\u0015\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0016\u0010\u0002\u001a\u0004\b\u0017\u0010\u0007R$\u0010\u0018\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0019\u0010\u0002\u001a\u0004\b\u001a\u0010\u0007R$\u0010\u001b\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u001c\u0010\u0002\u001a\u0004\b\u001d\u0010\u0007R$\u0010\u001e\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u001f\u0010\u0002\u001a\u0004\b \u0010\u0007\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006!"}, d2 = {"Landroidx/compose/ui/text/input/KeyboardType$Companion;", "", "()V", "Ascii", "Landroidx/compose/ui/text/input/KeyboardType;", "getAscii-PjHm6EE$annotations", "getAscii-PjHm6EE", "()I", "I", "Decimal", "getDecimal-PjHm6EE$annotations", "getDecimal-PjHm6EE", "Email", "getEmail-PjHm6EE$annotations", "getEmail-PjHm6EE", "Number", "getNumber-PjHm6EE$annotations", "getNumber-PjHm6EE", "NumberPassword", "getNumberPassword-PjHm6EE$annotations", "getNumberPassword-PjHm6EE", "Password", "getPassword-PjHm6EE$annotations", "getPassword-PjHm6EE", "Phone", "getPhone-PjHm6EE$annotations", "getPhone-PjHm6EE", "Text", "getText-PjHm6EE$annotations", "getText-PjHm6EE", "Uri", "getUri-PjHm6EE$annotations", "getUri-PjHm6EE", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getAscii-PjHm6EE$annotations, reason: not valid java name */
        public static /* synthetic */ void m5586getAsciiPjHm6EE$annotations() {
        }

        /* JADX INFO: renamed from: getDecimal-PjHm6EE$annotations, reason: not valid java name */
        public static /* synthetic */ void m5587getDecimalPjHm6EE$annotations() {
        }

        /* JADX INFO: renamed from: getEmail-PjHm6EE$annotations, reason: not valid java name */
        public static /* synthetic */ void m5588getEmailPjHm6EE$annotations() {
        }

        /* JADX INFO: renamed from: getNumber-PjHm6EE$annotations, reason: not valid java name */
        public static /* synthetic */ void m5589getNumberPjHm6EE$annotations() {
        }

        /* JADX INFO: renamed from: getNumberPassword-PjHm6EE$annotations, reason: not valid java name */
        public static /* synthetic */ void m5590getNumberPasswordPjHm6EE$annotations() {
        }

        /* JADX INFO: renamed from: getPassword-PjHm6EE$annotations, reason: not valid java name */
        public static /* synthetic */ void m5591getPasswordPjHm6EE$annotations() {
        }

        /* JADX INFO: renamed from: getPhone-PjHm6EE$annotations, reason: not valid java name */
        public static /* synthetic */ void m5592getPhonePjHm6EE$annotations() {
        }

        /* JADX INFO: renamed from: getText-PjHm6EE$annotations, reason: not valid java name */
        public static /* synthetic */ void m5593getTextPjHm6EE$annotations() {
        }

        /* JADX INFO: renamed from: getUri-PjHm6EE$annotations, reason: not valid java name */
        public static /* synthetic */ void m5594getUriPjHm6EE$annotations() {
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getText-PjHm6EE, reason: not valid java name */
        public final int m5602getTextPjHm6EE() {
            return KeyboardType.Text;
        }

        /* JADX INFO: renamed from: getAscii-PjHm6EE, reason: not valid java name */
        public final int m5595getAsciiPjHm6EE() {
            return KeyboardType.Ascii;
        }

        /* JADX INFO: renamed from: getNumber-PjHm6EE, reason: not valid java name */
        public final int m5598getNumberPjHm6EE() {
            return KeyboardType.Number;
        }

        /* JADX INFO: renamed from: getPhone-PjHm6EE, reason: not valid java name */
        public final int m5601getPhonePjHm6EE() {
            return KeyboardType.Phone;
        }

        /* JADX INFO: renamed from: getUri-PjHm6EE, reason: not valid java name */
        public final int m5603getUriPjHm6EE() {
            return KeyboardType.Uri;
        }

        /* JADX INFO: renamed from: getEmail-PjHm6EE, reason: not valid java name */
        public final int m5597getEmailPjHm6EE() {
            return KeyboardType.Email;
        }

        /* JADX INFO: renamed from: getPassword-PjHm6EE, reason: not valid java name */
        public final int m5600getPasswordPjHm6EE() {
            return KeyboardType.Password;
        }

        /* JADX INFO: renamed from: getNumberPassword-PjHm6EE, reason: not valid java name */
        public final int m5599getNumberPasswordPjHm6EE() {
            return KeyboardType.NumberPassword;
        }

        /* JADX INFO: renamed from: getDecimal-PjHm6EE, reason: not valid java name */
        public final int m5596getDecimalPjHm6EE() {
            return KeyboardType.Decimal;
        }
    }
}
