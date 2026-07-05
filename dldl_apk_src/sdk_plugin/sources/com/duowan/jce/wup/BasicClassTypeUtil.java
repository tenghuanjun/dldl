package com.duowan.jce.wup;

import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BasicClassTypeUtil {
    private static void addType(ArrayList<String> arrayList, String str) {
        int length = str.length();
        while (str.charAt(length - 1) == '>' && length - 1 != 0) {
        }
        arrayList.add(0, uni2JavaType(str.substring(0, length)));
    }

    public static ArrayList<String> getTypeList(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        int iIndexOf = str.indexOf(SimpleComparison.LESS_THAN_OPERATION);
        int i = 0;
        while (i < iIndexOf) {
            addType(arrayList, str.substring(i, iIndexOf));
            i = iIndexOf + 1;
            iIndexOf = str.indexOf(SimpleComparison.LESS_THAN_OPERATION, i);
            int iIndexOf2 = str.indexOf(",", i);
            if (iIndexOf == -1) {
                iIndexOf = iIndexOf2;
            }
            if (iIndexOf2 != -1 && iIndexOf2 < iIndexOf) {
                iIndexOf = iIndexOf2;
            }
        }
        addType(arrayList, str.substring(i, str.length()));
        return arrayList;
    }

    public static void main(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("char");
        arrayList.add("list<char>");
        arrayList.add("list<list<char>>");
        arrayList.add("map<short,string>");
        arrayList.add("map<double,map<float,list<bool>>>");
        arrayList.add("map<int64,list<Test.UserInfo>>");
        arrayList.add("map<short,Test.FriendInfo>");
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ArrayList<String> typeList = getTypeList((String) it.next());
            Iterator<String> it2 = typeList.iterator();
            while (it2.hasNext()) {
                System.out.println(it2.next());
            }
            Collections.reverse(typeList);
            System.out.println("-------------finished " + transTypeList(typeList));
        }
    }

    public static String transTypeList(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.set(i, java2UniType(arrayList.get(i)));
        }
        Collections.reverse(arrayList);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String str = arrayList.get(i2);
            if (str.equals("list")) {
                int i3 = i2 - 1;
                arrayList.set(i3, SimpleComparison.LESS_THAN_OPERATION + arrayList.get(i3));
                arrayList.set(0, arrayList.get(0) + SimpleComparison.GREATER_THAN_OPERATION);
            } else if (str.equals("map")) {
                int i4 = i2 - 1;
                arrayList.set(i4, SimpleComparison.LESS_THAN_OPERATION + arrayList.get(i4) + ",");
                StringBuilder sb = new StringBuilder();
                sb.append(arrayList.get(0));
                sb.append(SimpleComparison.GREATER_THAN_OPERATION);
                arrayList.set(0, sb.toString());
            } else if (str.equals("Array")) {
                int i5 = i2 - 1;
                arrayList.set(i5, SimpleComparison.LESS_THAN_OPERATION + arrayList.get(i5));
                arrayList.set(0, arrayList.get(0) + SimpleComparison.GREATER_THAN_OPERATION);
            }
        }
        Collections.reverse(arrayList);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x007b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object createClassByUni(java.lang.String r7) throws com.duowan.jce.wup.ObjectCreateException {
        /*
            java.util.ArrayList r7 = getTypeList(r7)
            java.util.Iterator r7 = r7.iterator()
            r0 = 0
            r1 = r0
            r2 = r1
        Lb:
            r3 = r2
        Lc:
            boolean r4 = r7.hasNext()
            if (r4 == 0) goto L7e
            java.lang.Object r1 = r7.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = createClassByName(r1)
            boolean r4 = r1 instanceof java.lang.String
            r5 = 0
            if (r4 == 0) goto L42
            r4 = r1
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r6 = "Array"
            boolean r6 = r6.equals(r4)
            if (r6 == 0) goto L36
            if (r2 == 0) goto L2f
            goto Lc
        L2f:
            java.lang.Class<java.lang.Byte> r1 = java.lang.Byte.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r5)
            goto Lc
        L36:
            java.lang.String r5 = "?"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L3f
            goto Lc
        L3f:
            if (r2 != 0) goto L7b
            goto L7a
        L42:
            boolean r4 = r1 instanceof java.util.List
            r6 = 1
            if (r4 == 0) goto L61
            if (r2 == 0) goto L57
            boolean r4 = r2 instanceof java.lang.Byte
            if (r4 == 0) goto L57
            java.lang.Class<java.lang.Byte> r1 = java.lang.Byte.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r6)
            java.lang.reflect.Array.set(r1, r5, r2)
            goto Lc
        L57:
            if (r2 == 0) goto L5f
            r4 = r1
            java.util.List r4 = (java.util.List) r4
            r4.add(r2)
        L5f:
            r2 = r0
            goto Lc
        L61:
            boolean r4 = r1 instanceof java.util.Map
            if (r4 == 0) goto L78
            if (r2 == 0) goto L69
            r4 = 1
            goto L6a
        L69:
            r4 = 0
        L6a:
            if (r3 == 0) goto L6d
            r5 = 1
        L6d:
            r4 = r4 & r5
            if (r4 == 0) goto L76
            r4 = r1
            java.util.Map r4 = (java.util.Map) r4
            r4.put(r2, r3)
        L76:
            r2 = r0
            goto Lb
        L78:
            if (r2 != 0) goto L7b
        L7a:
            goto L7c
        L7b:
            r3 = r2
        L7c:
            r2 = r1
            goto Lc
        L7e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.jce.wup.BasicClassTypeUtil.createClassByUni(java.lang.String):java.lang.Object");
    }

    public static Object createClassByName(String str) throws ObjectCreateException {
        if (str.equals("java.lang.Integer")) {
            return 0;
        }
        if (str.equals("java.lang.Boolean")) {
            return false;
        }
        if (str.equals("java.lang.Byte")) {
            return (byte) 0;
        }
        if (str.equals("java.lang.Double")) {
            return Double.valueOf(0.0d);
        }
        if (str.equals("java.lang.Float")) {
            return Float.valueOf(0.0f);
        }
        if (str.equals("java.lang.Long") || str.equals("java.lang.Short")) {
            return 0;
        }
        if (str.equals("java.lang.Character")) {
            throw new IllegalArgumentException("can not support java.lang.Character");
        }
        if (str.equals("java.lang.String")) {
            return "";
        }
        if (str.equals("java.util.List")) {
            return new ArrayList();
        }
        if (str.equals("java.util.Map")) {
            return new HashMap();
        }
        if (str.equals("Array")) {
            return "Array";
        }
        if (str.equals("?")) {
            return str;
        }
        try {
            return Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ObjectCreateException(e);
        }
    }

    public static String java2UniType(String str) {
        if (str.equals("java.lang.Integer") || str.equals("int")) {
            return "int32";
        }
        if (str.equals("java.lang.Boolean") || str.equals("boolean")) {
            return "bool";
        }
        if (str.equals("java.lang.Byte") || str.equals("byte")) {
            return "char";
        }
        String str2 = "double";
        if (!str.equals("java.lang.Double") && !str.equals("double")) {
            str2 = "float";
            if (!str.equals("java.lang.Float") && !str.equals("float")) {
                if (str.equals("java.lang.Long") || str.equals("long")) {
                    return "int64";
                }
                if (str.equals("java.lang.Short") || str.equals("short")) {
                    return "short";
                }
                if (str.equals("java.lang.Character")) {
                    throw new IllegalArgumentException("can not support java.lang.Character");
                }
                return str.equals("java.lang.String") ? "string" : str.equals("java.util.List") ? "list" : str.equals("java.util.Map") ? "map" : str;
            }
        }
        return str2;
    }

    public static String uni2JavaType(String str) {
        return str.equals("int32") ? "java.lang.Integer" : str.equals("bool") ? "java.lang.Boolean" : str.equals("char") ? "java.lang.Byte" : str.equals("double") ? "java.lang.Double" : str.equals("float") ? "java.lang.Float" : str.equals("int64") ? "java.lang.Long" : str.equals("short") ? "java.lang.Short" : str.equals("string") ? "java.lang.String" : str.equals("list") ? "java.util.List" : str.equals("map") ? "java.util.Map" : str;
    }

    public static boolean isBasicType(String str) {
        return str.equals("int") || str.equals("boolean") || str.equals("byte") || str.equals("double") || str.equals("float") || str.equals("long") || str.equals("short") || str.equals("char") || str.equals("Integer") || str.equals("Boolean") || str.equals("Byte") || str.equals("Double") || str.equals("Float") || str.equals("Long") || str.equals("Short") || str.equals("Char");
    }

    public static String getClassTransName(String str) {
        return str.equals("int") ? "Integer" : str.equals("boolean") ? "Boolean" : str.equals("byte") ? "Byte" : str.equals("double") ? "Double" : str.equals("float") ? "Float" : str.equals("long") ? "Long" : str.equals("short") ? "Short" : str.equals("char") ? "Character" : str;
    }

    public static String getVariableInit(String str, String str2) {
        if (str2.equals("int")) {
            return str2 + " " + str + "=0 ;\n";
        }
        if (str2.equals("boolean")) {
            return str2 + " " + str + "=false ;\n";
        }
        if (str2.equals("byte")) {
            return str2 + " " + str + " ;\n";
        }
        if (str2.equals("double")) {
            return str2 + " " + str + "=0 ;\n";
        }
        if (str2.equals("float")) {
            return str2 + " " + str + "=0 ;\n";
        }
        if (str2.equals("long")) {
            return str2 + " " + str + "=0 ;\n";
        }
        if (str2.equals("short")) {
            return str2 + " " + str + "=0 ;\n";
        }
        if (str2.equals("char")) {
            return str2 + " " + str + " ;\n";
        }
        return str2 + " " + str + " = null ;\n";
    }
}
