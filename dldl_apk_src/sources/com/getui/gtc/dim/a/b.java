package com.getui.gtc.dim.a;

import android.content.ContentValues;
import android.util.Base64;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.crypt.CryptTools;
import com.getui.gtc.base.crypt.SecureCryptTools;
import com.getui.gtc.base.db.AbstractTable;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b extends AbstractTable {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bb  */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.getui.gtc.dim.b.e] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.getui.gtc.dim.b.e a(java.lang.String r11) throws java.lang.Throwable {
        /*
            r10 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r10.getReadableDatabase()     // Catch: java.lang.Throwable -> Lac java.lang.Throwable -> Laf
            java.lang.String r2 = "d"
            java.lang.String r3 = "t"
            java.lang.String r4 = "b"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch: java.lang.Throwable -> Lac java.lang.Throwable -> Laf
            java.lang.String r4 = "a=?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> Lac java.lang.Throwable -> Laf
            java.lang.String r6 = java.lang.String.valueOf(r11)     // Catch: java.lang.Throwable -> Lac java.lang.Throwable -> Laf
            r9 = 0
            r5[r9] = r6     // Catch: java.lang.Throwable -> Lac java.lang.Throwable -> Laf
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> Lac java.lang.Throwable -> Laf
            if (r1 == 0) goto La6
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            if (r2 == 0) goto La6
            java.lang.String r2 = "b"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            java.lang.String r3 = "t"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            long r3 = r1.getLong(r3)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            r1.close()     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            if (r5 == 0) goto L4e
            com.getui.gtc.dim.b.e r11 = new com.getui.gtc.dim.b.e     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            r11.<init>(r0, r3)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            r0 = r11
            goto La6
        L4e:
            java.lang.String r5 = "-1"
            boolean r5 = r2.equals(r5)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            if (r5 == 0) goto L8d
            java.io.File r5 = new java.io.File     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            android.content.Context r6 = com.getui.gtc.base.GtcProvider.context()     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            java.io.File r6 = r6.getCacheDir()     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            java.lang.String r7 = "MD5"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            r8.<init>()     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            r8.append(r11)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            java.lang.String r11 = ".db"
            r8.append(r11)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            java.lang.String r11 = r8.toString()     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            byte[] r11 = r11.getBytes()     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            java.lang.String r11 = com.getui.gtc.base.crypt.CryptTools.digestToHexString(r7, r11)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            r5.<init>(r6, r11)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            boolean r11 = r5.exists()     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            if (r11 == 0) goto L8d
            java.lang.String r2 = new java.lang.String     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            byte[] r11 = com.getui.gtc.dim.d.b.a(r5)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            r2.<init>(r11)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
        L8d:
            byte[] r11 = android.util.Base64.decode(r2, r9)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            com.getui.gtc.base.crypt.SecureCryptTools r2 = com.getui.gtc.base.crypt.SecureCryptTools.getInstance()     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            byte[] r11 = r2.decrypt(r11)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            java.lang.Object r11 = com.getui.gtc.dim.d.b.a(r11)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            com.getui.gtc.dim.b.e r2 = new com.getui.gtc.dim.b.e     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            r2.<init>(r11, r3)     // Catch: java.lang.Throwable -> La4 java.lang.Throwable -> Lb8
            r0 = r2
            goto La6
        La4:
            r11 = move-exception
            goto Lb1
        La6:
            if (r1 == 0) goto Lb7
        La8:
            r1.close()
            goto Lb7
        Lac:
            r11 = move-exception
            r1 = r0
            goto Lb9
        Laf:
            r11 = move-exception
            r1 = r0
        Lb1:
            com.getui.gtc.dim.d.a.a(r11)     // Catch: java.lang.Throwable -> Lb8
            if (r1 == 0) goto Lb7
            goto La8
        Lb7:
            return r0
        Lb8:
            r11 = move-exception
        Lb9:
            if (r1 == 0) goto Lbe
            r1.close()
        Lbe:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.a.b.a(java.lang.String):com.getui.gtc.dim.b.e");
    }

    public final boolean a(String str, Object obj) {
        String strEncodeToString;
        try {
            try {
                strEncodeToString = Base64.encodeToString(SecureCryptTools.getInstance().encrypt(com.getui.gtc.dim.d.b.a(obj)), 0);
                if (strEncodeToString.length() > 2087152) {
                    com.getui.gtc.dim.d.b.a(strEncodeToString.getBytes(), new File(GtcProvider.context().getCacheDir(), CryptTools.digestToHexString("MD5", (str + ".db").getBytes())));
                    strEncodeToString = "-1";
                }
            } catch (Throwable unused) {
                strEncodeToString = "";
                com.getui.gtc.dim.d.a.b("dim storage save failed: ".concat(String.valueOf(obj)));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("a", str);
            contentValues.put("t", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("b", strEncodeToString);
            com.getui.gtc.dim.d.a.a(str + " update dim storage cache = " + strEncodeToString);
            return replace(null, contentValues) != -1;
        } catch (Throwable th) {
            com.getui.gtc.dim.d.a.a(th);
            return false;
        }
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String createSql() {
        return "CREATE TABLE IF NOT EXISTS d (a TEXT PRIMARY KEY, t TEXT, b TEXT)";
    }

    @Override // com.getui.gtc.base.db.AbstractTable
    public String getTableName() {
        return "d";
    }
}
