package org.json.simple.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
public class JSONParser {
    public static final int S_END = 6;
    public static final int S_INIT = 0;
    public static final int S_IN_ARRAY = 3;
    public static final int S_IN_ERROR = -1;
    public static final int S_IN_FINISHED_VALUE = 1;
    public static final int S_IN_OBJECT = 2;
    public static final int S_IN_PAIR_VALUE = 5;
    public static final int S_PASSED_PAIR_KEY = 4;
    private LinkedList handlerStatusStack;
    private Yylex lexer = new Yylex((Reader) null);
    private Yytoken token = null;
    private int status = 0;

    private List createArrayContainer(ContainerFactory containerFactory) {
        List listCreatArrayContainer;
        return (containerFactory == null || (listCreatArrayContainer = containerFactory.creatArrayContainer()) == null) ? new JSONArray() : listCreatArrayContainer;
    }

    private Map createObjectContainer(ContainerFactory containerFactory) {
        Map mapCreateObjectContainer;
        return (containerFactory == null || (mapCreateObjectContainer = containerFactory.createObjectContainer()) == null) ? new JSONObject() : mapCreateObjectContainer;
    }

    private void nextToken() throws ParseException, IOException {
        this.token = this.lexer.yylex();
        if (this.token == null) {
            this.token = new Yytoken(-1, null);
        }
    }

    private int peekStatus(LinkedList linkedList) {
        if (linkedList.size() == 0) {
            return -1;
        }
        return ((Integer) linkedList.getFirst()).intValue();
    }

    public int getPosition() {
        return this.lexer.getPosition();
    }

    public Object parse(Reader reader) throws ParseException, IOException {
        return parse(reader, (ContainerFactory) null);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:47:0x0156. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0027 A[Catch: IOException -> 0x01c1, TryCatch #0 {IOException -> 0x01c1, blocks: (B:3:0x000d, B:4:0x0016, B:55:0x019d, B:57:0x01a1, B:62:0x01b5, B:63:0x01c0, B:6:0x001b, B:10:0x0024, B:11:0x0027, B:12:0x002b, B:13:0x004d, B:14:0x0052, B:15:0x006c, B:16:0x0070, B:17:0x0093, B:18:0x0097, B:20:0x009b, B:22:0x00a1, B:23:0x00ac, B:24:0x00b0, B:25:0x00ce, B:26:0x00ec, B:27:0x00fb, B:33:0x0108, B:35:0x010e, B:36:0x011a, B:38:0x0122, B:39:0x0139, B:41:0x013f, B:43:0x0144, B:44:0x014f, B:45:0x0150, B:47:0x0156, B:49:0x015b, B:50:0x016b, B:51:0x016f, B:52:0x0180, B:53:0x0191, B:54:0x019c), top: B:66:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01a1 A[Catch: IOException -> 0x01c1, TRY_LEAVE, TryCatch #0 {IOException -> 0x01c1, blocks: (B:3:0x000d, B:4:0x0016, B:55:0x019d, B:57:0x01a1, B:62:0x01b5, B:63:0x01c0, B:6:0x001b, B:10:0x0024, B:11:0x0027, B:12:0x002b, B:13:0x004d, B:14:0x0052, B:15:0x006c, B:16:0x0070, B:17:0x0093, B:18:0x0097, B:20:0x009b, B:22:0x00a1, B:23:0x00ac, B:24:0x00b0, B:25:0x00ce, B:26:0x00ec, B:27:0x00fb, B:33:0x0108, B:35:0x010e, B:36:0x011a, B:38:0x0122, B:39:0x0139, B:41:0x013f, B:43:0x0144, B:44:0x014f, B:45:0x0150, B:47:0x0156, B:49:0x015b, B:50:0x016b, B:51:0x016f, B:52:0x0180, B:53:0x0191, B:54:0x019c), top: B:66:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01b5 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object parse(java.io.Reader r8, org.json.simple.parser.ContainerFactory r9) throws org.json.simple.parser.ParseException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 500
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.simple.parser.JSONParser.parse(java.io.Reader, org.json.simple.parser.ContainerFactory):java.lang.Object");
    }

    public Object parse(String str) throws ParseException {
        return parse(str, (ContainerFactory) null);
    }

    public Object parse(String str, ContainerFactory containerFactory) throws ParseException {
        try {
            return parse(new StringReader(str), containerFactory);
        } catch (IOException e) {
            throw new ParseException(-1, 2, e);
        }
    }

    public void parse(Reader reader, ContentHandler contentHandler) throws ParseException, IOException {
        parse(reader, contentHandler, false);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01d3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01bd A[Catch: Error -> 0x01df, RuntimeException -> 0x01e3, ParseException -> 0x01e7, IOException -> 0x01eb, TRY_LEAVE, TryCatch #2 {IOException -> 0x01eb, Error -> 0x01df, RuntimeException -> 0x01e3, ParseException -> 0x01e7, blocks: (B:10:0x001d, B:11:0x0024, B:91:0x01b9, B:93:0x01bd, B:98:0x01d3, B:99:0x01de, B:14:0x002a, B:17:0x003a, B:20:0x0045, B:21:0x0048, B:22:0x004c, B:25:0x006a, B:28:0x007e, B:31:0x0085, B:34:0x00a3, B:35:0x00aa, B:37:0x00ae, B:39:0x00b4, B:41:0x00c0, B:40:0x00be, B:44:0x00c7, B:47:0x00da, B:50:0x00ed, B:53:0x00f8, B:58:0x0107, B:60:0x010d, B:62:0x0119, B:61:0x0117, B:65:0x0120, B:67:0x0128, B:70:0x0142, B:72:0x014b, B:74:0x0151, B:75:0x015e, B:76:0x015f, B:78:0x016b, B:80:0x0170, B:83:0x0183, B:86:0x019a, B:89:0x01ad, B:90:0x01b8), top: B:112:0x001d }] */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:217)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:68)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.addCases(SwitchRegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:71)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:104)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void parse(java.io.Reader r7, org.json.simple.parser.ContentHandler r8, boolean r9) throws org.json.simple.parser.ParseException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 548
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.simple.parser.JSONParser.parse(java.io.Reader, org.json.simple.parser.ContentHandler, boolean):void");
    }

    public void parse(String str, ContentHandler contentHandler) throws ParseException {
        parse(str, contentHandler, false);
    }

    public void parse(String str, ContentHandler contentHandler, boolean z) throws ParseException {
        try {
            parse(new StringReader(str), contentHandler, z);
        } catch (IOException e) {
            throw new ParseException(-1, 2, e);
        }
    }

    public void reset() {
        this.token = null;
        this.status = 0;
        this.handlerStatusStack = null;
    }

    public void reset(Reader reader) {
        this.lexer.yyreset(reader);
        reset();
    }
}
