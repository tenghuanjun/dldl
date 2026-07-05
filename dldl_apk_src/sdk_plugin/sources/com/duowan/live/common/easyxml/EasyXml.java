package com.duowan.live.common.easyxml;

import java.io.StringReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class EasyXml {
    public static <T> T parse(Class<? extends T> cls, String str) throws Exception {
        XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
        xmlPullParserFactoryNewInstance.setNamespaceAware(true);
        XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
        xmlPullParserNewPullParser.setInput(new StringReader(str));
        if (xmlPullParserNewPullParser.next() != 2) {
            throw new Exception("invalid xml");
        }
        String name = xmlPullParserNewPullParser.getName();
        EasyRoot easyRoot = (EasyRoot) getAnnotation(cls, EasyRoot.class);
        if (easyRoot == null || !name.equalsIgnoreCase(easyRoot.name())) {
            throw new Exception("not found EasyRoot annotation in " + cls.getName());
        }
        return (T) parseElement(cls, xmlPullParserNewPullParser);
    }

    private static <T> T parseElement(Class<? extends T> cls, XmlPullParser xmlPullParser) throws Exception {
        T tNewInstance = cls.newInstance();
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        for (Field field : cls.getFields()) {
            EasyAttribute easyAttribute = (EasyAttribute) getAnnotation(field, EasyAttribute.class);
            if (easyAttribute != null) {
                setFieldValue(tNewInstance, field, xmlPullParser.getAttributeValue(null, easyAttribute.name()));
            } else {
                EasyElement easyElement = (EasyElement) getAnnotation(field, EasyElement.class);
                if (easyElement != null) {
                    map.put(easyElement.name(), field);
                }
                EasyElementList easyElementList = (EasyElementList) getAnnotation(field, EasyElementList.class);
                if (easyElementList != null) {
                    map2.put(getElementListName(easyElementList), field);
                }
            }
        }
        xmlPullParser.next();
        while (stepToTag(xmlPullParser)) {
            String name = xmlPullParser.getName();
            Field field2 = (Field) map.remove(name);
            if (field2 != null) {
                field2.set(tNewInstance, parseElement(field2.getType(), xmlPullParser));
            } else {
                Field field3 = (Field) map2.remove(name);
                if (field3 != null) {
                    field3.set(tNewInstance, parseElementList((EasyElementList) getAnnotation(field3, EasyElementList.class), xmlPullParser));
                } else {
                    stepOutTag(xmlPullParser);
                }
            }
        }
        if (map.size() > 0 || map2.size() > 0) {
            throw new Exception("element field miss in xml");
        }
        stepOutTag(xmlPullParser);
        return tNewInstance;
    }

    private static String getElementListName(EasyElementList easyElementList) throws Exception {
        String strName;
        if (easyElementList.inline()) {
            EasyRoot easyRoot = (EasyRoot) getAnnotation(easyElementList.type(), EasyRoot.class);
            if (easyRoot == null) {
                throw new Exception("no EasyRoot annotation in atom of elementList");
            }
            strName = easyRoot.name();
        } else {
            strName = easyElementList.name();
        }
        if (strName.length() > 0) {
            return strName;
        }
        throw new Exception("empty name");
    }

    private static <T> List<T> parseElementList(EasyElementList easyElementList, XmlPullParser xmlPullParser) throws Exception {
        boolean zInline = easyElementList.inline();
        if (!zInline) {
            xmlPullParser.next();
        }
        ArrayList arrayList = new ArrayList();
        while (stepToTag(xmlPullParser) && (!zInline || xmlPullParser.getName().equalsIgnoreCase(getElementListName(easyElementList)))) {
            arrayList.add(parseElement(easyElementList.type(), xmlPullParser));
        }
        if (!zInline) {
            stepOutTag(xmlPullParser);
        }
        return arrayList;
    }

    private static boolean stepToTag(XmlPullParser xmlPullParser) throws Exception {
        int eventType = xmlPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                return true;
            }
            if (eventType == 3) {
                return false;
            }
            eventType = xmlPullParser.next();
            if (eventType == 1) {
                throw new Exception("unexpected end document");
            }
        }
        return false;
    }

    private static void stepOutTag(XmlPullParser xmlPullParser) throws Exception {
        if (xmlPullParser.getEventType() == 3) {
            xmlPullParser.next();
            return;
        }
        int next = xmlPullParser.next();
        int i = 1;
        while (next != 1) {
            if (next == 2) {
                i++;
            } else if (next == 3 && i - 1 == 0) {
                xmlPullParser.next();
                return;
            }
            next = xmlPullParser.next();
            if (next == 1) {
                throw new Exception("unexpected end document");
            }
        }
    }

    private static <T extends Annotation> T getAnnotation(AnnotatedElement annotatedElement, Class<? extends T> cls) {
        for (Annotation annotation : annotatedElement.getAnnotations()) {
            T t = (T) annotation;
            if (t.annotationType().equals(cls)) {
                return t;
            }
        }
        return null;
    }

    private static void setFieldValue(Object obj, Field field, String str) throws Exception {
        if (field.getType().equals(String.class)) {
            field.set(obj, str);
        } else {
            if (field.getType().equals(Integer.class)) {
                field.set(obj, Integer.valueOf(Integer.parseInt(str)));
                return;
            }
            throw new Exception("not imp yet");
        }
    }
}
