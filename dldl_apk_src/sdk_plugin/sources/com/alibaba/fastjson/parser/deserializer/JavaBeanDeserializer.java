package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONLexerBase;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class JavaBeanDeserializer implements ObjectDeserializer {
    private final Map<String, FieldDeserializer> alterNameFieldDeserializers;
    public final JavaBeanInfo beanInfo;
    protected final Class<?> clazz;
    private ConcurrentMap<String, Object> extraFieldDeserializers;
    private Map<String, FieldDeserializer> fieldDeserializerMap;
    private final FieldDeserializer[] fieldDeserializers;
    private transient long[] hashArray;
    private transient short[] hashArrayMapping;
    private transient long[] smartMatchHashArray;
    private transient short[] smartMatchHashArrayMapping;
    protected final FieldDeserializer[] sortedFieldDeserializers;

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public int getFastMatchToken() {
        return 12;
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls) {
        this(parserConfig, cls, cls);
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, Class<?> cls, Type type) {
        this(parserConfig, JavaBeanInfo.build(cls, type, parserConfig.propertyNamingStrategy, parserConfig.fieldBased, parserConfig.compatibleWithJavaBean, parserConfig.isJacksonCompatible()));
    }

    public JavaBeanDeserializer(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo) {
        this.clazz = javaBeanInfo.clazz;
        this.beanInfo = javaBeanInfo;
        this.sortedFieldDeserializers = new FieldDeserializer[javaBeanInfo.sortedFields.length];
        int length = javaBeanInfo.sortedFields.length;
        HashMap map = null;
        for (int i = 0; i < length; i++) {
            FieldInfo fieldInfo = javaBeanInfo.sortedFields[i];
            FieldDeserializer fieldDeserializerCreateFieldDeserializer = parserConfig.createFieldDeserializer(parserConfig, javaBeanInfo, fieldInfo);
            this.sortedFieldDeserializers[i] = fieldDeserializerCreateFieldDeserializer;
            if (length > 128) {
                if (this.fieldDeserializerMap == null) {
                    this.fieldDeserializerMap = new HashMap();
                }
                this.fieldDeserializerMap.put(fieldInfo.name, fieldDeserializerCreateFieldDeserializer);
            }
            for (String str : fieldInfo.alternateNames) {
                if (map == null) {
                    map = new HashMap();
                }
                map.put(str, fieldDeserializerCreateFieldDeserializer);
            }
        }
        this.alterNameFieldDeserializers = map;
        this.fieldDeserializers = new FieldDeserializer[javaBeanInfo.fields.length];
        int length2 = javaBeanInfo.fields.length;
        for (int i2 = 0; i2 < length2; i2++) {
            this.fieldDeserializers[i2] = getFieldDeserializer(javaBeanInfo.fields[i2].name);
        }
    }

    public FieldDeserializer getFieldDeserializer(String str) {
        return getFieldDeserializer(str, null);
    }

    public FieldDeserializer getFieldDeserializer(String str, int[] iArr) {
        FieldDeserializer fieldDeserializer;
        if (str == null) {
            return null;
        }
        Map<String, FieldDeserializer> map = this.fieldDeserializerMap;
        if (map != null && (fieldDeserializer = map.get(str)) != null) {
            return fieldDeserializer;
        }
        int i = 0;
        int length = this.sortedFieldDeserializers.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int iCompareTo = this.sortedFieldDeserializers[i2].fieldInfo.name.compareTo(str);
            if (iCompareTo < 0) {
                i = i2 + 1;
            } else {
                if (iCompareTo <= 0) {
                    if (isSetFlag(i2, iArr)) {
                        return null;
                    }
                    return this.sortedFieldDeserializers[i2];
                }
                length = i2 - 1;
            }
        }
        Map<String, FieldDeserializer> map2 = this.alterNameFieldDeserializers;
        if (map2 != null) {
            return map2.get(str);
        }
        return null;
    }

    public FieldDeserializer getFieldDeserializer(long j) {
        int i = 0;
        if (this.hashArray == null) {
            long[] jArr = new long[this.sortedFieldDeserializers.length];
            int i2 = 0;
            while (true) {
                FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                if (i2 >= fieldDeserializerArr.length) {
                    break;
                }
                jArr[i2] = TypeUtils.fnv1a_64(fieldDeserializerArr[i2].fieldInfo.name);
                i2++;
            }
            Arrays.sort(jArr);
            this.hashArray = jArr;
        }
        int iBinarySearch = Arrays.binarySearch(this.hashArray, j);
        if (iBinarySearch < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, (short) -1);
            while (true) {
                FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                if (i >= fieldDeserializerArr2.length) {
                    break;
                }
                int iBinarySearch2 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(fieldDeserializerArr2[i].fieldInfo.name));
                if (iBinarySearch2 >= 0) {
                    sArr[iBinarySearch2] = (short) i;
                }
                i++;
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[iBinarySearch];
        if (s != -1) {
            return this.sortedFieldDeserializers[s];
        }
        return null;
    }

    static boolean isSetFlag(int i, int[] iArr) {
        if (iArr == null) {
            return false;
        }
        int i2 = i / 32;
        int i3 = i % 32;
        if (i2 < iArr.length) {
            if (((1 << i3) & iArr[i2]) != 0) {
                return true;
            }
        }
        return false;
    }

    public Object createInstance(DefaultJSONParser defaultJSONParser, Type type) {
        Object objNewInstance;
        if ((type instanceof Class) && this.clazz.isInterface()) {
            return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{(Class) type}, new JSONObject());
        }
        Object obj = null;
        if (this.beanInfo.defaultConstructor == null && this.beanInfo.factoryMethod == null) {
            return null;
        }
        if (this.beanInfo.factoryMethod != null && this.beanInfo.defaultConstructorParameterSize > 0) {
            return null;
        }
        try {
            Constructor<?> constructor = this.beanInfo.defaultConstructor;
            if (this.beanInfo.defaultConstructorParameterSize != 0) {
                ParseContext context = defaultJSONParser.getContext();
                if (context == null || context.object == null) {
                    throw new JSONException("can't create non-static inner class instance.");
                }
                if (type instanceof Class) {
                    String name = ((Class) type).getName();
                    String strSubstring = name.substring(0, name.lastIndexOf(36));
                    Object obj2 = context.object;
                    String name2 = obj2.getClass().getName();
                    if (!name2.equals(strSubstring)) {
                        ParseContext parseContext = context.parent;
                        if (parseContext == null || parseContext.object == null || !("java.util.ArrayList".equals(name2) || "java.util.List".equals(name2) || "java.util.Collection".equals(name2) || "java.util.Map".equals(name2) || "java.util.HashMap".equals(name2))) {
                            obj = obj2;
                        } else if (parseContext.object.getClass().getName().equals(strSubstring)) {
                            obj = parseContext.object;
                        }
                        obj2 = obj;
                    }
                    if (obj2 == null || ((obj2 instanceof Collection) && ((Collection) obj2).isEmpty())) {
                        throw new JSONException("can't create non-static inner class instance.");
                    }
                    objNewInstance = constructor.newInstance(obj2);
                } else {
                    throw new JSONException("can't create non-static inner class instance.");
                }
            } else if (constructor != null) {
                objNewInstance = constructor.newInstance(new Object[0]);
            } else {
                objNewInstance = this.beanInfo.factoryMethod.invoke(null, new Object[0]);
            }
            if (defaultJSONParser != null && defaultJSONParser.lexer.isEnabled(Feature.InitStringFieldAsEmpty)) {
                for (FieldInfo fieldInfo : this.beanInfo.fields) {
                    if (fieldInfo.fieldClass == String.class) {
                        try {
                            fieldInfo.set(objNewInstance, "");
                        } catch (Exception e) {
                            throw new JSONException("create instance error, class " + this.clazz.getName(), e);
                        }
                    }
                }
            }
            return objNewInstance;
        } catch (JSONException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new JSONException("create instance error, class " + this.clazz.getName(), e3);
        }
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser, type, obj, 0);
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, int i) {
        return (T) deserialze(defaultJSONParser, type, obj, null, i, null);
    }

    public <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        Enum<?> enumScanEnum;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() != 14) {
            throw new JSONException("error");
        }
        String strScanTypeName = jSONLexer.scanTypeName(defaultJSONParser.symbolTable);
        if (strScanTypeName != null) {
            ObjectDeserializer seeAlso = getSeeAlso(defaultJSONParser.getConfig(), this.beanInfo, strScanTypeName);
            if (seeAlso == null) {
                seeAlso = defaultJSONParser.getConfig().getDeserializer(defaultJSONParser.getConfig().checkAutoType(strScanTypeName, TypeUtils.getClass(type), jSONLexer.getFeatures()));
            }
            if (seeAlso instanceof JavaBeanDeserializer) {
                return (T) ((JavaBeanDeserializer) seeAlso).deserialzeArrayMapping(defaultJSONParser, type, obj, obj2);
            }
        }
        T t = (T) createInstance(defaultJSONParser, type);
        int i = 0;
        int length = this.sortedFieldDeserializers.length;
        while (true) {
            if (i >= length) {
                break;
            }
            char c = i == length + (-1) ? AbstractJsonLexerKt.END_LIST : AbstractJsonLexerKt.COMMA;
            FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
            Class<?> cls = fieldDeserializer.fieldInfo.fieldClass;
            if (cls == Integer.TYPE) {
                fieldDeserializer.setValue((Object) t, jSONLexer.scanInt(c));
            } else if (cls == String.class) {
                fieldDeserializer.setValue((Object) t, jSONLexer.scanString(c));
            } else if (cls == Long.TYPE) {
                fieldDeserializer.setValue(t, jSONLexer.scanLong(c));
            } else if (cls.isEnum()) {
                char current = jSONLexer.getCurrent();
                if (current == '\"' || current == 'n') {
                    enumScanEnum = jSONLexer.scanEnum(cls, defaultJSONParser.getSymbolTable(), c);
                } else if (current >= '0' && current <= '9') {
                    enumScanEnum = ((EnumDeserializer) ((DefaultFieldDeserializer) fieldDeserializer).getFieldValueDeserilizer(defaultJSONParser.getConfig())).valueOf(jSONLexer.scanInt(c));
                } else {
                    enumScanEnum = scanEnum(jSONLexer, c);
                }
                fieldDeserializer.setValue(t, enumScanEnum);
            } else if (cls == Boolean.TYPE) {
                fieldDeserializer.setValue(t, jSONLexer.scanBoolean(c));
            } else if (cls == Float.TYPE) {
                fieldDeserializer.setValue(t, Float.valueOf(jSONLexer.scanFloat(c)));
            } else if (cls == Double.TYPE) {
                fieldDeserializer.setValue(t, Double.valueOf(jSONLexer.scanDouble(c)));
            } else if (cls == Date.class && jSONLexer.getCurrent() == '1') {
                fieldDeserializer.setValue(t, new Date(jSONLexer.scanLong(c)));
            } else if (cls == BigDecimal.class) {
                fieldDeserializer.setValue(t, jSONLexer.scanDecimal(c));
            } else {
                jSONLexer.nextToken(14);
                fieldDeserializer.setValue(t, defaultJSONParser.parseObject(fieldDeserializer.fieldInfo.fieldType, fieldDeserializer.fieldInfo.name));
                if (jSONLexer.token() == 15) {
                    break;
                }
                check(jSONLexer, c == ']' ? 15 : 16);
            }
            i++;
        }
        jSONLexer.nextToken(16);
        return t;
    }

    protected void check(JSONLexer jSONLexer, int i) {
        if (jSONLexer.token() != i) {
            throw new JSONException("syntax error");
        }
    }

    protected Enum<?> scanEnum(JSONLexer jSONLexer, char c) {
        throw new JSONException("illegal enum. " + jSONLexer.info());
    }

    /* JADX WARN: Code restructure failed: missing block: B:391:0x0514, code lost:
    
        r2 = getSeeAlso(r13, r33.beanInfo, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:392:0x051a, code lost:
    
        if (r2 != null) goto L395;
     */
    /* JADX WARN: Code restructure failed: missing block: B:393:0x051c, code lost:
    
        r15 = r13.checkAutoType(r1, com.alibaba.fastjson.util.TypeUtils.getClass(r35), r12.getFeatures());
        r2 = r34.getConfig().getDeserializer(r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:395:0x0531, code lost:
    
        r15 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:396:0x0532, code lost:
    
        r3 = (T) r2.deserialze(r34, r15, r36);
     */
    /* JADX WARN: Code restructure failed: missing block: B:397:0x0538, code lost:
    
        if ((r2 instanceof com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) == false) goto L401;
     */
    /* JADX WARN: Code restructure failed: missing block: B:398:0x053a, code lost:
    
        r2 = (com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer) r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:399:0x053c, code lost:
    
        if (r4 == null) goto L401;
     */
    /* JADX WARN: Code restructure failed: missing block: B:400:0x053e, code lost:
    
        r2.getFieldDeserializer(r4).setValue((java.lang.Object) r3, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:401:0x0545, code lost:
    
        if (r5 == null) goto L403;
     */
    /* JADX WARN: Code restructure failed: missing block: B:402:0x0547, code lost:
    
        r5.object = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:403:0x054b, code lost:
    
        r34.setContext(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:404:0x054e, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:409:0x055c, code lost:
    
        r15 = r6;
        r31 = r7;
        r1 = r19;
        r37 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:473:0x067c, code lost:
    
        r12.nextToken();
     */
    /* JADX WARN: Code restructure failed: missing block: B:486:0x06ab, code lost:
    
        r12.nextToken(16);
     */
    /* JADX WARN: Code restructure failed: missing block: B:676:0x097a, code lost:
    
        throw new com.alibaba.fastjson.JSONException("syntax error, unexpect token " + com.alibaba.fastjson.parser.JSONToken.name(r12.token()));
     */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0316 A[Catch: all -> 0x03fe, TryCatch #21 {all -> 0x03fe, blocks: (B:316:0x03d9, B:318:0x03e3, B:321:0x03f0, B:331:0x040b, B:333:0x0414, B:335:0x0420, B:336:0x0424, B:338:0x042c, B:340:0x0432, B:341:0x0436, B:342:0x0442, B:345:0x044b, B:347:0x044f, B:348:0x0452, B:350:0x0456, B:351:0x0459, B:352:0x0465, B:354:0x046d, B:355:0x0473, B:357:0x0479, B:359:0x047f, B:360:0x0485, B:361:0x048b, B:362:0x048f, B:365:0x0497, B:377:0x04c7, B:378:0x04e1, B:380:0x04e4, B:393:0x051c, B:398:0x053a, B:400:0x053e, B:142:0x01ee, B:149:0x01fc, B:152:0x0208, B:158:0x0217, B:160:0x021b, B:163:0x0224, B:168:0x022e, B:171:0x0237, B:176:0x0241, B:179:0x024a, B:182:0x0250, B:187:0x025a, B:192:0x0264, B:197:0x026e, B:199:0x0274, B:202:0x0282, B:204:0x028a, B:206:0x028e, B:209:0x029e, B:216:0x02af, B:219:0x02b9, B:224:0x02c4, B:227:0x02ce, B:232:0x02d9, B:235:0x02e3, B:238:0x02ea, B:242:0x02f6, B:244:0x02fe, B:249:0x030e, B:252:0x0316, B:248:0x0309, B:256:0x0321, B:258:0x032b, B:262:0x0337, B:265:0x033c, B:261:0x0332, B:268:0x0343, B:272:0x0355, B:275:0x035a, B:271:0x0350, B:278:0x0360, B:280:0x036a, B:284:0x0376, B:287:0x037b, B:283:0x0371, B:291:0x0383, B:293:0x038b, B:298:0x0398, B:301:0x039f, B:297:0x0393), top: B:729:0x03d9 }] */
    /* JADX WARN: Removed duplicated region for block: B:418:0x0592  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x059d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:431:0x05cd  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x05d8  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0639  */
    /* JADX WARN: Removed duplicated region for block: B:483:0x06a1  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x06a5 A[Catch: all -> 0x0987, TryCatch #2 {all -> 0x0987, blocks: (B:481:0x0699, B:484:0x06a5, B:486:0x06ab, B:469:0x0670, B:479:0x0691), top: B:698:0x0699 }] */
    /* JADX WARN: Removed duplicated region for block: B:624:0x086c A[Catch: Exception -> 0x08a0, all -> 0x092f, TRY_ENTER, TRY_LEAVE, TryCatch #12 {all -> 0x092f, blocks: (B:505:0x06d8, B:508:0x06e0, B:509:0x06e4, B:511:0x06e7, B:513:0x06ef, B:515:0x06ff, B:555:0x0784, B:516:0x0704, B:518:0x0708, B:519:0x070d, B:521:0x0711, B:522:0x0716, B:524:0x071a, B:525:0x071f, B:527:0x0723, B:528:0x0728, B:530:0x072c, B:531:0x0731, B:533:0x0735, B:536:0x073c, B:540:0x0748, B:542:0x074e, B:544:0x0755, B:546:0x075f, B:548:0x0767, B:550:0x076b, B:552:0x0775, B:554:0x0780, B:589:0x0800, B:591:0x0806, B:594:0x080d, B:596:0x0810, B:598:0x0814, B:600:0x081a, B:602:0x0821, B:608:0x0837, B:610:0x083d, B:648:0x0903, B:650:0x0908, B:657:0x0917, B:664:0x0927, B:665:0x092e, B:624:0x086c, B:639:0x08cf, B:641:0x08d5, B:645:0x08e2, B:646:0x0900, B:556:0x078c, B:558:0x0797, B:560:0x07a1, B:562:0x07a9, B:587:0x07f7, B:563:0x07af, B:565:0x07b3, B:566:0x07b9, B:568:0x07bd, B:569:0x07c3, B:571:0x07c7, B:572:0x07cc, B:574:0x07d0, B:575:0x07d5, B:577:0x07d9, B:578:0x07de, B:580:0x07e2, B:583:0x07e9), top: B:713:0x06d8, inners: #13, #20 }] */
    /* JADX WARN: Removed duplicated region for block: B:689:0x099c  */
    /* JADX WARN: Removed duplicated region for block: B:708:0x03cf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:716:0x0837 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected <T> T deserialze(com.alibaba.fastjson.parser.DefaultJSONParser r34, java.lang.reflect.Type r35, java.lang.Object r36, java.lang.Object r37, int r38, int[] r39) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 2471
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.deserialze(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.reflect.Type, java.lang.Object, java.lang.Object, int, int[]):java.lang.Object");
    }

    protected Enum scanEnum(JSONLexerBase jSONLexerBase, char[] cArr, ObjectDeserializer objectDeserializer) {
        EnumDeserializer enumDeserializer = objectDeserializer instanceof EnumDeserializer ? (EnumDeserializer) objectDeserializer : null;
        if (enumDeserializer == null) {
            jSONLexerBase.matchStat = -1;
            return null;
        }
        long jScanEnumSymbol = jSONLexerBase.scanEnumSymbol(cArr);
        if (jSONLexerBase.matchStat <= 0) {
            return null;
        }
        Enum enumByHashCode = enumDeserializer.getEnumByHashCode(jScanEnumSymbol);
        if (enumByHashCode == null) {
            if (jScanEnumSymbol == -3750763034362895579L) {
                return null;
            }
            if (jSONLexerBase.isEnabled(Feature.ErrorOnEnumNotMatch)) {
                throw new JSONException("not match enum value, " + enumDeserializer.enumClass);
            }
        }
        return enumByHashCode;
    }

    public boolean parseField(DefaultJSONParser defaultJSONParser, String str, Object obj, Type type, Map<String, Object> map) {
        return parseField(defaultJSONParser, str, obj, type, map, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0094 A[PHI: r2
  0x0094: PHI (r2v4 com.alibaba.fastjson.parser.deserializer.FieldDeserializer) = 
  (r2v3 com.alibaba.fastjson.parser.deserializer.FieldDeserializer)
  (r2v3 com.alibaba.fastjson.parser.deserializer.FieldDeserializer)
  (r2v24 com.alibaba.fastjson.parser.deserializer.FieldDeserializer)
  (r2v3 com.alibaba.fastjson.parser.deserializer.FieldDeserializer)
 binds: [B:10:0x0032, B:36:0x008b, B:39:0x0091, B:14:0x003f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r17v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean parseField(com.alibaba.fastjson.parser.DefaultJSONParser r22, java.lang.String r23, java.lang.Object r24, java.lang.reflect.Type r25, java.util.Map<java.lang.String, java.lang.Object> r26, int[] r27) {
        /*
            Method dump skipped, instruction units count: 518
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer.parseField(com.alibaba.fastjson.parser.DefaultJSONParser, java.lang.String, java.lang.Object, java.lang.reflect.Type, java.util.Map, int[]):boolean");
    }

    public FieldDeserializer smartMatch(String str) {
        return smartMatch(str, null);
    }

    public FieldDeserializer smartMatch(String str, int[] iArr) {
        boolean zStartsWith;
        if (str == null) {
            return null;
        }
        FieldDeserializer fieldDeserializer = getFieldDeserializer(str, iArr);
        if (fieldDeserializer == null) {
            long jFnv1a_64_lower = TypeUtils.fnv1a_64_lower(str);
            int i = 0;
            if (this.smartMatchHashArray == null) {
                long[] jArr = new long[this.sortedFieldDeserializers.length];
                int i2 = 0;
                while (true) {
                    FieldDeserializer[] fieldDeserializerArr = this.sortedFieldDeserializers;
                    if (i2 >= fieldDeserializerArr.length) {
                        break;
                    }
                    jArr[i2] = TypeUtils.fnv1a_64_lower(fieldDeserializerArr[i2].fieldInfo.name);
                    i2++;
                }
                Arrays.sort(jArr);
                this.smartMatchHashArray = jArr;
            }
            int iBinarySearch = Arrays.binarySearch(this.smartMatchHashArray, jFnv1a_64_lower);
            if (iBinarySearch < 0) {
                zStartsWith = str.startsWith("is");
                if (zStartsWith) {
                    iBinarySearch = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_lower(str.substring(2)));
                }
            } else {
                zStartsWith = false;
            }
            if (iBinarySearch >= 0) {
                if (this.smartMatchHashArrayMapping == null) {
                    short[] sArr = new short[this.smartMatchHashArray.length];
                    Arrays.fill(sArr, (short) -1);
                    while (true) {
                        FieldDeserializer[] fieldDeserializerArr2 = this.sortedFieldDeserializers;
                        if (i >= fieldDeserializerArr2.length) {
                            break;
                        }
                        int iBinarySearch2 = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv1a_64_lower(fieldDeserializerArr2[i].fieldInfo.name));
                        if (iBinarySearch2 >= 0) {
                            sArr[iBinarySearch2] = (short) i;
                        }
                        i++;
                    }
                    this.smartMatchHashArrayMapping = sArr;
                }
                short s = this.smartMatchHashArrayMapping[iBinarySearch];
                if (s != -1 && !isSetFlag(s, iArr)) {
                    fieldDeserializer = this.sortedFieldDeserializers[s];
                }
            }
            if (fieldDeserializer != null) {
                FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
                if ((fieldInfo.parserFeatures & Feature.DisableFieldSmartMatch.mask) != 0) {
                    return null;
                }
                Class<?> cls = fieldInfo.fieldClass;
                if (zStartsWith && cls != Boolean.TYPE && cls != Boolean.class) {
                    return null;
                }
            }
        }
        return fieldDeserializer;
    }

    private Object createFactoryInstance(ParserConfig parserConfig, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return this.beanInfo.factoryMethod.invoke(null, obj);
    }

    public Object createInstance(Map<String, Object> map, ParserConfig parserConfig) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Integer num;
        Object objCast;
        float f;
        double d;
        boolean z = true;
        if (this.beanInfo.creatorConstructor == null && this.beanInfo.factoryMethod == null) {
            Object objCreateInstance = createInstance((DefaultJSONParser) null, this.clazz);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                FieldDeserializer fieldDeserializerSmartMatch = smartMatch(key);
                if (fieldDeserializerSmartMatch != null) {
                    FieldInfo fieldInfo = fieldDeserializerSmartMatch.fieldInfo;
                    Field field = fieldDeserializerSmartMatch.fieldInfo.field;
                    Type type = fieldInfo.fieldType;
                    if (field != null) {
                        Class<?> type2 = field.getType();
                        if (type2 == Boolean.TYPE) {
                            if (value == Boolean.FALSE) {
                                field.setBoolean(objCreateInstance, false);
                            } else if (value == Boolean.TRUE) {
                                field.setBoolean(objCreateInstance, true);
                            }
                        } else if (type2 == Integer.TYPE) {
                            if (value instanceof Number) {
                                field.setInt(objCreateInstance, ((Number) value).intValue());
                            }
                        } else if (type2 == Long.TYPE) {
                            if (value instanceof Number) {
                                field.setLong(objCreateInstance, ((Number) value).longValue());
                            }
                        } else if (type2 == Float.TYPE) {
                            if (value instanceof Number) {
                                field.setFloat(objCreateInstance, ((Number) value).floatValue());
                            } else if (value instanceof String) {
                                String str = (String) value;
                                if (str.length() <= 10) {
                                    f = TypeUtils.parseFloat(str);
                                } else {
                                    f = Float.parseFloat(str);
                                }
                                field.setFloat(objCreateInstance, f);
                            }
                        } else if (type2 == Double.TYPE) {
                            if (value instanceof Number) {
                                field.setDouble(objCreateInstance, ((Number) value).doubleValue());
                            } else if (value instanceof String) {
                                String str2 = (String) value;
                                if (str2.length() <= 10) {
                                    d = TypeUtils.parseDouble(str2);
                                } else {
                                    d = Double.parseDouble(str2);
                                }
                                field.setDouble(objCreateInstance, d);
                            }
                        } else if (value != null && type == value.getClass()) {
                            field.set(objCreateInstance, value);
                        }
                    }
                    String str3 = fieldInfo.format;
                    if (str3 != null && type == Date.class) {
                        objCast = TypeUtils.castToDate(value, str3);
                    } else if (type instanceof ParameterizedType) {
                        objCast = TypeUtils.cast(value, (ParameterizedType) type, parserConfig);
                    } else {
                        objCast = TypeUtils.cast(value, type, parserConfig);
                    }
                    fieldDeserializerSmartMatch.setValue(objCreateInstance, objCast);
                }
            }
            if (this.beanInfo.buildMethod == null) {
                return objCreateInstance;
            }
            try {
                return this.beanInfo.buildMethod.invoke(objCreateInstance, new Object[0]);
            } catch (Exception e) {
                throw new JSONException("build object error", e);
            }
        }
        FieldInfo[] fieldInfoArr = this.beanInfo.fields;
        int length = fieldInfoArr.length;
        Object[] objArr = new Object[length];
        HashMap map2 = null;
        for (int i = 0; i < length; i++) {
            FieldInfo fieldInfo2 = fieldInfoArr[i];
            Object objValueOf = map.get(fieldInfo2.name);
            if (objValueOf == null) {
                Class<?> cls = fieldInfo2.fieldClass;
                if (cls == Integer.TYPE) {
                    objValueOf = 0;
                } else if (cls == Long.TYPE) {
                    objValueOf = 0L;
                } else if (cls == Short.TYPE) {
                    objValueOf = (short) 0;
                } else if (cls == Byte.TYPE) {
                    objValueOf = (byte) 0;
                } else if (cls == Float.TYPE) {
                    objValueOf = Float.valueOf(0.0f);
                } else if (cls == Double.TYPE) {
                    objValueOf = Double.valueOf(0.0d);
                } else if (cls == Character.TYPE) {
                    objValueOf = '0';
                } else if (cls == Boolean.TYPE) {
                    objValueOf = false;
                }
                if (map2 == null) {
                    map2 = new HashMap();
                }
                map2.put(fieldInfo2.name, Integer.valueOf(i));
            }
            objArr[i] = objValueOf;
        }
        if (map2 != null) {
            for (Map.Entry<String, Object> entry2 : map.entrySet()) {
                String key2 = entry2.getKey();
                Object value2 = entry2.getValue();
                FieldDeserializer fieldDeserializerSmartMatch2 = smartMatch(key2);
                if (fieldDeserializerSmartMatch2 != null && (num = (Integer) map2.get(fieldDeserializerSmartMatch2.fieldInfo.name)) != null) {
                    objArr[num.intValue()] = value2;
                }
            }
        }
        if (this.beanInfo.creatorConstructor != null) {
            if (this.beanInfo.f6kotlin) {
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    if (objArr[i2] != null || this.beanInfo.fields == null || i2 >= this.beanInfo.fields.length) {
                        i2++;
                    } else if (this.beanInfo.fields[i2].fieldClass != String.class) {
                        break;
                    }
                }
                z = false;
            } else {
                z = false;
            }
            if (z && this.beanInfo.kotlinDefaultConstructor != null) {
                try {
                    Object objNewInstance = this.beanInfo.kotlinDefaultConstructor.newInstance(new Object[0]);
                    for (int i3 = 0; i3 < length; i3++) {
                        Object obj = objArr[i3];
                        if (obj != null && this.beanInfo.fields != null && i3 < this.beanInfo.fields.length) {
                            this.beanInfo.fields[i3].set(objNewInstance, obj);
                        }
                    }
                    return objNewInstance;
                } catch (Exception e2) {
                    throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e2);
                }
            }
            try {
                return this.beanInfo.creatorConstructor.newInstance(objArr);
            } catch (Exception e3) {
                throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e3);
            }
        }
        if (this.beanInfo.factoryMethod == null) {
            return null;
        }
        try {
            return this.beanInfo.factoryMethod.invoke(null, objArr);
        } catch (Exception e4) {
            throw new JSONException("create factory method error, " + this.beanInfo.factoryMethod.toString(), e4);
        }
    }

    public Type getFieldType(int i) {
        return this.sortedFieldDeserializers[i].fieldInfo.fieldType;
    }

    protected Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i) {
        return parseRest(defaultJSONParser, type, obj, obj2, i, new int[0]);
    }

    protected Object parseRest(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, int i, int[] iArr) {
        return deserialze(defaultJSONParser, type, obj, obj2, i, iArr);
    }

    protected static JavaBeanDeserializer getSeeAlso(ParserConfig parserConfig, JavaBeanInfo javaBeanInfo, String str) {
        if (javaBeanInfo.jsonType == null) {
            return null;
        }
        for (Class<?> cls : javaBeanInfo.jsonType.seeAlso()) {
            ObjectDeserializer deserializer = parserConfig.getDeserializer(cls);
            if (deserializer instanceof JavaBeanDeserializer) {
                JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
                JavaBeanInfo javaBeanInfo2 = javaBeanDeserializer.beanInfo;
                if (javaBeanInfo2.typeName.equals(str)) {
                    return javaBeanDeserializer;
                }
                JavaBeanDeserializer seeAlso = getSeeAlso(parserConfig, javaBeanInfo2, str);
                if (seeAlso != null) {
                    return seeAlso;
                }
            }
        }
        return null;
    }

    protected static void parseArray(Collection collection, ObjectDeserializer objectDeserializer, DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        JSONLexerBase jSONLexerBase = (JSONLexerBase) defaultJSONParser.lexer;
        int i = jSONLexerBase.token();
        if (i == 8) {
            jSONLexerBase.nextToken(16);
            jSONLexerBase.token();
            return;
        }
        if (i != 14) {
            defaultJSONParser.throwException(i);
        }
        if (jSONLexerBase.getCurrent() == '[') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(14);
        } else {
            jSONLexerBase.nextToken(14);
        }
        if (jSONLexerBase.token() == 15) {
            jSONLexerBase.nextToken();
            return;
        }
        int i2 = 0;
        while (true) {
            collection.add(objectDeserializer.deserialze(defaultJSONParser, type, Integer.valueOf(i2)));
            i2++;
            if (jSONLexerBase.token() != 16) {
                break;
            }
            if (jSONLexerBase.getCurrent() == '[') {
                jSONLexerBase.next();
                jSONLexerBase.setToken(14);
            } else {
                jSONLexerBase.nextToken(14);
            }
        }
        int i3 = jSONLexerBase.token();
        if (i3 != 15) {
            defaultJSONParser.throwException(i3);
        }
        if (jSONLexerBase.getCurrent() == ',') {
            jSONLexerBase.next();
            jSONLexerBase.setToken(16);
        } else {
            jSONLexerBase.nextToken(16);
        }
    }
}
