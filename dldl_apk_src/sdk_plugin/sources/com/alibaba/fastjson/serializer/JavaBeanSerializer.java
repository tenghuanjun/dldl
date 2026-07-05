package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class JavaBeanSerializer extends SerializeFilterable implements ObjectSerializer {
    protected SerializeBeanInfo beanInfo;
    protected final FieldSerializer[] getters;
    private volatile transient long[] hashArray;
    private volatile transient short[] hashArrayMapping;
    protected final FieldSerializer[] sortedGetters;

    public JavaBeanSerializer(Class<?> cls) {
        this(cls, (Map<String, String>) null);
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, createAliasMap(strArr));
    }

    static Map<String, String> createAliasMap(String... strArr) {
        HashMap map = new HashMap();
        for (String str : strArr) {
            map.put(str, str);
        }
        return map;
    }

    public Class<?> getType() {
        return this.beanInfo.beanType;
    }

    public JavaBeanSerializer(Class<?> cls, Map<String, String> map) {
        this(TypeUtils.buildBeanInfo(cls, map, null));
    }

    public JavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) {
        boolean z;
        this.beanInfo = serializeBeanInfo;
        this.sortedGetters = new FieldSerializer[serializeBeanInfo.sortedFields.length];
        int i = 0;
        while (true) {
            FieldSerializer[] fieldSerializerArr = this.sortedGetters;
            if (i >= fieldSerializerArr.length) {
                break;
            }
            fieldSerializerArr[i] = new FieldSerializer(serializeBeanInfo.beanType, serializeBeanInfo.sortedFields[i]);
            i++;
        }
        if (serializeBeanInfo.fields == serializeBeanInfo.sortedFields) {
            this.getters = this.sortedGetters;
        } else {
            this.getters = new FieldSerializer[serializeBeanInfo.fields.length];
            int i2 = 0;
            while (true) {
                if (i2 >= this.getters.length) {
                    z = false;
                    break;
                }
                FieldSerializer fieldSerializer = getFieldSerializer(serializeBeanInfo.fields[i2].name);
                if (fieldSerializer == null) {
                    z = true;
                    break;
                } else {
                    this.getters[i2] = fieldSerializer;
                    i2++;
                }
            }
            if (z) {
                FieldSerializer[] fieldSerializerArr2 = this.sortedGetters;
                System.arraycopy(fieldSerializerArr2, 0, this.getters, 0, fieldSerializerArr2.length);
            }
        }
        if (serializeBeanInfo.jsonType != null) {
            for (Class<? extends SerializeFilter> cls : serializeBeanInfo.jsonType.serialzeFilters()) {
                try {
                    addFilter(cls.getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Exception unused) {
                }
            }
        }
        if (serializeBeanInfo.jsonType != null) {
            for (Class<? extends SerializeFilter> cls2 : serializeBeanInfo.jsonType.serialzeFilters()) {
                try {
                    addFilter(cls2.getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Exception unused2) {
                }
            }
        }
    }

    public void writeDirectNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArray(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArrayNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    public void writeNoneASM(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:344:0x0475, code lost:
    
        r31 = r6;
        r32 = r7;
        r22 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:345:0x047c, code lost:
    
        if (r21 == false) goto L347;
     */
    /* JADX WARN: Code restructure failed: missing block: B:346:0x047e, code lost:
    
        r4 = kotlinx.serialization.json.internal.AbstractJsonLexerKt.COMMA;
     */
    /* JADX WARN: Code restructure failed: missing block: B:347:0x0481, code lost:
    
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:348:0x0482, code lost:
    
        writeAfter(r34, r35, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:349:0x0488, code lost:
    
        if (r22.length <= 0) goto L353;
     */
    /* JADX WARN: Code restructure failed: missing block: B:351:0x0490, code lost:
    
        if (r14.isEnabled(com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat) == false) goto L353;
     */
    /* JADX WARN: Code restructure failed: missing block: B:352:0x0492, code lost:
    
        r34.decrementIdent();
        r34.println();
     */
    /* JADX WARN: Code restructure failed: missing block: B:353:0x0498, code lost:
    
        if (r39 != false) goto L355;
     */
    /* JADX WARN: Code restructure failed: missing block: B:354:0x049a, code lost:
    
        r14.append(r31);
     */
    /* JADX WARN: Code restructure failed: missing block: B:355:0x049f, code lost:
    
        r34.context = r32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:356:0x04a3, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:359:0x04a9, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x032b A[Catch: Exception -> 0x0447, all -> 0x04a4, TryCatch #2 {all -> 0x04a4, blocks: (B:110:0x0199, B:113:0x01a1, B:115:0x01ad, B:117:0x01bc, B:119:0x01c6, B:122:0x01d0, B:124:0x01db, B:126:0x01df, B:129:0x01e6, B:131:0x01e9, B:133:0x01ee, B:136:0x01f8, B:138:0x0203, B:140:0x0207, B:143:0x020e, B:145:0x0211, B:148:0x0219, B:150:0x0221, B:152:0x022c, B:154:0x0230, B:157:0x0237, B:159:0x023a, B:161:0x023f, B:162:0x0244, B:164:0x024c, B:166:0x0257, B:168:0x025b, B:171:0x0262, B:173:0x0265, B:175:0x026a, B:177:0x0271, B:179:0x0275, B:183:0x0283, B:185:0x0287, B:187:0x0290, B:189:0x029b, B:191:0x02a1, B:193:0x02a5, B:196:0x02b0, B:198:0x02b4, B:200:0x02b8, B:203:0x02c3, B:205:0x02c7, B:207:0x02cb, B:210:0x02d6, B:212:0x02da, B:214:0x02de, B:217:0x02ec, B:219:0x02f0, B:221:0x02f4, B:224:0x0301, B:226:0x0305, B:228:0x0309, B:231:0x0317, B:233:0x031b, B:235:0x031f, B:239:0x032b, B:241:0x032f, B:243:0x0333, B:246:0x033e, B:248:0x034b, B:253:0x0357, B:255:0x035d, B:307:0x0408, B:309:0x040c, B:311:0x0410, B:314:0x041a, B:316:0x0422, B:317:0x042a, B:319:0x0430, B:260:0x0368, B:261:0x036b, B:263:0x0371, B:265:0x037d, B:272:0x0392, B:277:0x039c, B:281:0x03ae, B:284:0x03b8, B:287:0x03c2, B:289:0x03cb, B:292:0x03d5, B:293:0x03d9, B:294:0x03dd, B:296:0x03e2, B:297:0x03e6, B:298:0x03ea, B:300:0x03ee, B:302:0x03f2, B:305:0x0401, B:306:0x0405, B:278:0x03a6, B:334:0x0455, B:348:0x0482, B:350:0x048a, B:352:0x0492, B:354:0x049a), top: B:398:0x0199 }] */
    /* JADX WARN: Removed duplicated region for block: B:249:0x034f  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x040c A[Catch: Exception -> 0x0447, all -> 0x04a4, TryCatch #2 {all -> 0x04a4, blocks: (B:110:0x0199, B:113:0x01a1, B:115:0x01ad, B:117:0x01bc, B:119:0x01c6, B:122:0x01d0, B:124:0x01db, B:126:0x01df, B:129:0x01e6, B:131:0x01e9, B:133:0x01ee, B:136:0x01f8, B:138:0x0203, B:140:0x0207, B:143:0x020e, B:145:0x0211, B:148:0x0219, B:150:0x0221, B:152:0x022c, B:154:0x0230, B:157:0x0237, B:159:0x023a, B:161:0x023f, B:162:0x0244, B:164:0x024c, B:166:0x0257, B:168:0x025b, B:171:0x0262, B:173:0x0265, B:175:0x026a, B:177:0x0271, B:179:0x0275, B:183:0x0283, B:185:0x0287, B:187:0x0290, B:189:0x029b, B:191:0x02a1, B:193:0x02a5, B:196:0x02b0, B:198:0x02b4, B:200:0x02b8, B:203:0x02c3, B:205:0x02c7, B:207:0x02cb, B:210:0x02d6, B:212:0x02da, B:214:0x02de, B:217:0x02ec, B:219:0x02f0, B:221:0x02f4, B:224:0x0301, B:226:0x0305, B:228:0x0309, B:231:0x0317, B:233:0x031b, B:235:0x031f, B:239:0x032b, B:241:0x032f, B:243:0x0333, B:246:0x033e, B:248:0x034b, B:253:0x0357, B:255:0x035d, B:307:0x0408, B:309:0x040c, B:311:0x0410, B:314:0x041a, B:316:0x0422, B:317:0x042a, B:319:0x0430, B:260:0x0368, B:261:0x036b, B:263:0x0371, B:265:0x037d, B:272:0x0392, B:277:0x039c, B:281:0x03ae, B:284:0x03b8, B:287:0x03c2, B:289:0x03cb, B:292:0x03d5, B:293:0x03d9, B:294:0x03dd, B:296:0x03e2, B:297:0x03e6, B:298:0x03ea, B:300:0x03ee, B:302:0x03f2, B:305:0x0401, B:306:0x0405, B:278:0x03a6, B:334:0x0455, B:348:0x0482, B:350:0x048a, B:352:0x0492, B:354:0x049a), top: B:398:0x0199 }] */
    /* JADX WARN: Removed duplicated region for block: B:325:0x043c  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x043f  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0444 A[PHI: r29 r31 r32
  0x0444: PHI (r29v1 int) = (r29v0 int), (r29v2 int) binds: [B:326:0x043d, B:101:0x015e] A[DONT_GENERATE, DONT_INLINE]
  0x0444: PHI (r31v2 char) = (r31v1 char), (r31v3 char) binds: [B:326:0x043d, B:101:0x015e] A[DONT_GENERATE, DONT_INLINE]
  0x0444: PHI (r32v4 com.alibaba.fastjson.serializer.SerialContext) = (r32v3 com.alibaba.fastjson.serializer.SerialContext), (r32v5 com.alibaba.fastjson.serializer.SerialContext) binds: [B:326:0x043d, B:101:0x015e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:372:0x04de A[Catch: all -> 0x055f, TRY_ENTER, TryCatch #12 {all -> 0x055f, blocks: (B:369:0x04bc, B:372:0x04de, B:380:0x052e, B:382:0x0534, B:383:0x054c, B:385:0x0550, B:389:0x0559, B:390:0x055e, B:374:0x04f3, B:376:0x04f7, B:378:0x04fd, B:379:0x0518), top: B:412:0x04bc }] */
    /* JADX WARN: Removed duplicated region for block: B:373:0x04f1  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x0534 A[Catch: all -> 0x055f, TryCatch #12 {all -> 0x055f, blocks: (B:369:0x04bc, B:372:0x04de, B:380:0x052e, B:382:0x0534, B:383:0x054c, B:385:0x0550, B:389:0x0559, B:390:0x055e, B:374:0x04f3, B:376:0x04f7, B:378:0x04fd, B:379:0x0518), top: B:412:0x04bc }] */
    /* JADX WARN: Removed duplicated region for block: B:385:0x0550 A[Catch: all -> 0x055f, TryCatch #12 {all -> 0x055f, blocks: (B:369:0x04bc, B:372:0x04de, B:380:0x052e, B:382:0x0534, B:383:0x054c, B:385:0x0550, B:389:0x0559, B:390:0x055e, B:374:0x04f3, B:376:0x04f7, B:378:0x04fd, B:379:0x0518), top: B:412:0x04bc }] */
    /* JADX WARN: Removed duplicated region for block: B:387:0x0556  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x0557  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x04bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0100  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void write(com.alibaba.fastjson.serializer.JSONSerializer r34, java.lang.Object r35, java.lang.Object r36, java.lang.reflect.Type r37, int r38, boolean r39) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1379
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.JavaBeanSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type, int, boolean):void");
    }

    protected void writeClassName(JSONSerializer jSONSerializer, String str, Object obj) {
        if (str == null) {
            str = jSONSerializer.config.typeKey;
        }
        jSONSerializer.out.writeFieldName(str, false);
        String name = this.beanInfo.typeName;
        if (name == null) {
            Class<?> superclass = obj.getClass();
            if (TypeUtils.isProxy(superclass)) {
                superclass = superclass.getSuperclass();
            }
            name = superclass.getName();
        }
        jSONSerializer.write(name);
    }

    public boolean writeReference(JSONSerializer jSONSerializer, Object obj, int i) {
        SerialContext serialContext = jSONSerializer.context;
        int i2 = SerializerFeature.DisableCircularReferenceDetect.mask;
        if (serialContext == null || (serialContext.features & i2) != 0 || (i & i2) != 0 || jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj)) {
            return false;
        }
        jSONSerializer.writeReference(obj);
        return true;
    }

    protected boolean isWriteAsArray(JSONSerializer jSONSerializer) {
        return isWriteAsArray(jSONSerializer, 0);
    }

    protected boolean isWriteAsArray(JSONSerializer jSONSerializer, int i) {
        int i2 = SerializerFeature.BeanToArray.mask;
        return ((this.beanInfo.features & i2) == 0 && !jSONSerializer.out.beanToArray && (i & i2) == 0) ? false : true;
    }

    public Object getFieldValue(Object obj, String str) {
        FieldSerializer fieldSerializer = getFieldSerializer(str);
        if (fieldSerializer == null) {
            throw new JSONException("field not found. " + str);
        }
        try {
            return fieldSerializer.getPropertyValue(obj);
        } catch (IllegalAccessException e) {
            throw new JSONException("getFieldValue error." + str, e);
        } catch (InvocationTargetException e2) {
            throw new JSONException("getFieldValue error." + str, e2);
        }
    }

    public Object getFieldValue(Object obj, String str, long j, boolean z) {
        FieldSerializer fieldSerializer = getFieldSerializer(j);
        if (fieldSerializer == null) {
            if (!z) {
                return null;
            }
            throw new JSONException("field not found. " + str);
        }
        try {
            return fieldSerializer.getPropertyValue(obj);
        } catch (IllegalAccessException e) {
            throw new JSONException("getFieldValue error." + str, e);
        } catch (InvocationTargetException e2) {
            throw new JSONException("getFieldValue error." + str, e2);
        }
    }

    public FieldSerializer getFieldSerializer(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        int length = this.sortedGetters.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int iCompareTo = this.sortedGetters[i2].fieldInfo.name.compareTo(str);
            if (iCompareTo < 0) {
                i = i2 + 1;
            } else {
                if (iCompareTo <= 0) {
                    return this.sortedGetters[i2];
                }
                length = i2 - 1;
            }
        }
        return null;
    }

    public FieldSerializer getFieldSerializer(long j) {
        PropertyNamingStrategy[] propertyNamingStrategyArrValues;
        int iBinarySearch;
        if (this.hashArray == null) {
            propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            long[] jArr = new long[this.sortedGetters.length * propertyNamingStrategyArrValues.length];
            int i = 0;
            int i2 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr = this.sortedGetters;
                if (i >= fieldSerializerArr.length) {
                    break;
                }
                String str = fieldSerializerArr[i].fieldInfo.name;
                jArr[i2] = TypeUtils.fnv1a_64(str);
                i2++;
                for (PropertyNamingStrategy propertyNamingStrategy : propertyNamingStrategyArrValues) {
                    String strTranslate = propertyNamingStrategy.translate(str);
                    if (!str.equals(strTranslate)) {
                        jArr[i2] = TypeUtils.fnv1a_64(strTranslate);
                        i2++;
                    }
                }
                i++;
            }
            Arrays.sort(jArr, 0, i2);
            this.hashArray = new long[i2];
            System.arraycopy(jArr, 0, this.hashArray, 0, i2);
        } else {
            propertyNamingStrategyArrValues = null;
        }
        int iBinarySearch2 = Arrays.binarySearch(this.hashArray, j);
        if (iBinarySearch2 < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            if (propertyNamingStrategyArrValues == null) {
                propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            }
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, (short) -1);
            int i3 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr2 = this.sortedGetters;
                if (i3 >= fieldSerializerArr2.length) {
                    break;
                }
                String str2 = fieldSerializerArr2[i3].fieldInfo.name;
                int iBinarySearch3 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(str2));
                if (iBinarySearch3 >= 0) {
                    sArr[iBinarySearch3] = (short) i3;
                }
                for (PropertyNamingStrategy propertyNamingStrategy2 : propertyNamingStrategyArrValues) {
                    String strTranslate2 = propertyNamingStrategy2.translate(str2);
                    if (!str2.equals(strTranslate2) && (iBinarySearch = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(strTranslate2))) >= 0) {
                        sArr[iBinarySearch] = (short) i3;
                    }
                }
                i3++;
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[iBinarySearch2];
        if (s != -1) {
            return this.sortedGetters[s];
        }
        return null;
    }

    public List<Object> getFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            arrayList.add(fieldSerializer.getPropertyValue(obj));
        }
        return arrayList;
    }

    public List<Object> getObjectFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            Class<?> cls = fieldSerializer.fieldInfo.fieldClass;
            if (!cls.isPrimitive() && !cls.getName().startsWith("java.lang.")) {
                arrayList.add(fieldSerializer.getPropertyValue(obj));
            }
        }
        return arrayList;
    }

    public int getSize(Object obj) throws Exception {
        int i = 0;
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            if (fieldSerializer.getPropertyValueDirect(obj) != null) {
                i++;
            }
        }
        return i;
    }

    public Set<String> getFieldNames(Object obj) throws Exception {
        HashSet hashSet = new HashSet();
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            if (fieldSerializer.getPropertyValueDirect(obj) != null) {
                hashSet.add(fieldSerializer.fieldInfo.name);
            }
        }
        return hashSet;
    }

    public Map<String, Object> getFieldValuesMap(Object obj) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            boolean zIsEnabled = SerializerFeature.isEnabled(fieldSerializer.features, SerializerFeature.SkipTransientField);
            FieldInfo fieldInfo = fieldSerializer.fieldInfo;
            if (!zIsEnabled || fieldInfo == null || !fieldInfo.fieldTransient) {
                if (fieldSerializer.fieldInfo.unwrapped) {
                    Object json = JSON.toJSON(fieldSerializer.getPropertyValue(obj));
                    if (json instanceof Map) {
                        linkedHashMap.putAll((Map) json);
                    } else {
                        linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
                    }
                } else {
                    linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
                }
            }
        }
        return linkedHashMap;
    }

    protected BeanContext getBeanContext(int i) {
        return this.sortedGetters[i].fieldContext;
    }

    protected Type getFieldType(int i) {
        return this.sortedGetters[i].fieldInfo.fieldType;
    }

    protected char writeBefore(JSONSerializer jSONSerializer, Object obj, char c) {
        if (jSONSerializer.beforeFilters != null) {
            Iterator<BeforeFilter> it = jSONSerializer.beforeFilters.iterator();
            while (it.hasNext()) {
                c = it.next().writeBefore(jSONSerializer, obj, c);
            }
        }
        if (this.beforeFilters != null) {
            Iterator<BeforeFilter> it2 = this.beforeFilters.iterator();
            while (it2.hasNext()) {
                c = it2.next().writeBefore(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    protected char writeAfter(JSONSerializer jSONSerializer, Object obj, char c) {
        if (jSONSerializer.afterFilters != null) {
            Iterator<AfterFilter> it = jSONSerializer.afterFilters.iterator();
            while (it.hasNext()) {
                c = it.next().writeAfter(jSONSerializer, obj, c);
            }
        }
        if (this.afterFilters != null) {
            Iterator<AfterFilter> it2 = this.afterFilters.iterator();
            while (it2.hasNext()) {
                c = it2.next().writeAfter(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    protected boolean applyLabel(JSONSerializer jSONSerializer, String str) {
        if (jSONSerializer.labelFilters != null) {
            Iterator<LabelFilter> it = jSONSerializer.labelFilters.iterator();
            while (it.hasNext()) {
                if (!it.next().apply(str)) {
                    return false;
                }
            }
        }
        if (this.labelFilters == null) {
            return true;
        }
        Iterator<LabelFilter> it2 = this.labelFilters.iterator();
        while (it2.hasNext()) {
            if (!it2.next().apply(str)) {
                return false;
            }
        }
        return true;
    }
}
