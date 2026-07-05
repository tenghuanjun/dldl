package com.huya.mtp.data.parser;

import com.huya.mtp.data.exception.ParseException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class Parser<Source, Target> {
    public abstract Target decode(Source source) throws ParseException;

    public abstract Source encode(Target target) throws ParseException;

    public <C> Parser<Source, C> transit(final Parser<Target, C> parser) {
        return new Parser<Source, C>() { // from class: com.huya.mtp.data.parser.Parser.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.huya.mtp.data.parser.Parser
            public C decode(Source source) throws ParseException {
                return (C) parser.decode(Parser.this.decode(source));
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.huya.mtp.data.parser.Parser
            public Source encode(C c) throws ParseException {
                return (Source) Parser.this.encode(parser.encode(c));
            }
        };
    }

    public <C> Parser<Source, C> align(final Parser<C, Target> parser) {
        return new Parser<Source, C>() { // from class: com.huya.mtp.data.parser.Parser.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.huya.mtp.data.parser.Parser
            public C decode(Source source) throws ParseException {
                return (C) parser.encode(Parser.this.decode(source));
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.huya.mtp.data.parser.Parser
            public Source encode(C c) throws ParseException {
                return (Source) Parser.this.encode(parser.decode(c));
            }
        };
    }

    public <C> Parser<C, Source> alignReverse(final Parser<Target, C> parser) {
        return new Parser<C, Source>() { // from class: com.huya.mtp.data.parser.Parser.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.huya.mtp.data.parser.Parser
            public Source decode(C c) throws ParseException {
                return (Source) Parser.this.encode(parser.encode(c));
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.huya.mtp.data.parser.Parser
            public C encode(Source source) throws ParseException {
                return (C) parser.decode(Parser.this.decode(source));
            }
        };
    }

    public <C> Parser<C, Source> transitReverse(final Parser<C, Target> parser) {
        return new Parser<C, Source>() { // from class: com.huya.mtp.data.parser.Parser.4
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.huya.mtp.data.parser.Parser
            public Source decode(C c) throws ParseException {
                return (Source) Parser.this.encode(parser.decode(c));
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.huya.mtp.data.parser.Parser
            public C encode(Source source) throws ParseException {
                return (C) parser.encode(Parser.this.decode(source));
            }
        };
    }

    public Parser<Target, Source> reverse() {
        return new Parser<Target, Source>() { // from class: com.huya.mtp.data.parser.Parser.5
            @Override // com.huya.mtp.data.parser.Parser
            public Source decode(Target target) throws ParseException {
                return (Source) Parser.this.encode(target);
            }

            @Override // com.huya.mtp.data.parser.Parser
            public Target encode(Source source) throws ParseException {
                return (Target) Parser.this.decode(source);
            }
        };
    }
}
