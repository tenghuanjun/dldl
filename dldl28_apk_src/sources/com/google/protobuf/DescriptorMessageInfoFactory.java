package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.StructuralMessageInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
final class DescriptorMessageInfoFactory implements MessageInfoFactory {
    private static final String GET_DEFAULT_INSTANCE_METHOD_NAME = "getDefaultInstance";
    private static final DescriptorMessageInfoFactory instance = new DescriptorMessageInfoFactory();
    private static final Set<String> specialFieldNames = new HashSet(Arrays.asList("cached_size", "serialized_size", "class"));
    private static IsInitializedCheckAnalyzer isInitializedCheckAnalyzer = new IsInitializedCheckAnalyzer();

    private DescriptorMessageInfoFactory() {
    }

    public static DescriptorMessageInfoFactory getInstance() {
        return instance;
    }

    @Override // com.google.protobuf.MessageInfoFactory
    public boolean isSupported(Class<?> cls) {
        return GeneratedMessageV3.class.isAssignableFrom(cls);
    }

    @Override // com.google.protobuf.MessageInfoFactory
    public MessageInfo messageInfoFor(Class<?> cls) {
        if (!GeneratedMessageV3.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: " + cls.getName());
        }
        return convert(cls, descriptorForType(cls));
    }

    private static Message getDefaultInstance(Class<?> cls) {
        try {
            return (Message) cls.getDeclaredMethod(GET_DEFAULT_INSTANCE_METHOD_NAME, null).invoke(null, null);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to get default instance for message class " + cls.getName(), e);
        }
    }

    private static Descriptors.Descriptor descriptorForType(Class<?> cls) {
        return getDefaultInstance(cls).getDescriptorForType();
    }

    private static MessageInfo convert(Class<?> cls, Descriptors.Descriptor descriptor) {
        int i = AnonymousClass3.$SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax[descriptor.getFile().getSyntax().ordinal()];
        if (i == 1) {
            return convertProto2(cls, descriptor);
        }
        if (i == 2) {
            return convertProto3(cls, descriptor);
        }
        throw new IllegalArgumentException("Unsupported syntax: " + descriptor.getFile().getSyntax());
    }

    static class IsInitializedCheckAnalyzer {
        private final Map<Descriptors.Descriptor, Boolean> resultCache = new ConcurrentHashMap();
        private int index = 0;
        private final Stack<Node> stack = new Stack<>();
        private final Map<Descriptors.Descriptor, Node> nodeCache = new HashMap();

        IsInitializedCheckAnalyzer() {
        }

        public boolean needsIsInitializedCheck(Descriptors.Descriptor descriptor) {
            Boolean bool = this.resultCache.get(descriptor);
            if (bool != null) {
                return bool.booleanValue();
            }
            synchronized (this) {
                Boolean bool2 = this.resultCache.get(descriptor);
                if (bool2 != null) {
                    return bool2.booleanValue();
                }
                return dfs(descriptor).component.needsIsInitializedCheck;
            }
        }

        private static class Node {
            StronglyConnectedComponent component = null;
            final Descriptors.Descriptor descriptor;
            final int index;
            int lowLink;

            Node(Descriptors.Descriptor descriptor, int i) {
                this.descriptor = descriptor;
                this.index = i;
                this.lowLink = i;
            }
        }

        private static class StronglyConnectedComponent {
            final List<Descriptors.Descriptor> messages;
            boolean needsIsInitializedCheck;

            private StronglyConnectedComponent() {
                this.messages = new ArrayList();
                this.needsIsInitializedCheck = false;
            }
        }

        private Node dfs(Descriptors.Descriptor descriptor) {
            Node nodePop;
            int i = this.index;
            this.index = i + 1;
            Node node = new Node(descriptor, i);
            this.stack.push(node);
            this.nodeCache.put(descriptor, node);
            for (Descriptors.FieldDescriptor fieldDescriptor : descriptor.getFields()) {
                if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    Node node2 = this.nodeCache.get(fieldDescriptor.getMessageType());
                    if (node2 == null) {
                        node.lowLink = Math.min(node.lowLink, dfs(fieldDescriptor.getMessageType()).lowLink);
                    } else if (node2.component == null) {
                        node.lowLink = Math.min(node.lowLink, node2.lowLink);
                    }
                }
            }
            if (node.index == node.lowLink) {
                StronglyConnectedComponent stronglyConnectedComponent = new StronglyConnectedComponent();
                do {
                    nodePop = this.stack.pop();
                    nodePop.component = stronglyConnectedComponent;
                    stronglyConnectedComponent.messages.add(nodePop.descriptor);
                } while (nodePop != node);
                analyze(stronglyConnectedComponent);
            }
            return node;
        }

        private void analyze(StronglyConnectedComponent stronglyConnectedComponent) {
            boolean z;
            Iterator<Descriptors.Descriptor> it = stronglyConnectedComponent.messages.iterator();
            loop0: while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Descriptors.Descriptor next = it.next();
                z = true;
                if (next.isExtendable()) {
                    break;
                }
                for (Descriptors.FieldDescriptor fieldDescriptor : next.getFields()) {
                    if (fieldDescriptor.isRequired()) {
                        break loop0;
                    }
                    if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                        Node node = this.nodeCache.get(fieldDescriptor.getMessageType());
                        if (node.component != stronglyConnectedComponent && node.component.needsIsInitializedCheck) {
                            break loop0;
                        }
                    }
                }
            }
            stronglyConnectedComponent.needsIsInitializedCheck = z;
            Iterator<Descriptors.Descriptor> it2 = stronglyConnectedComponent.messages.iterator();
            while (it2.hasNext()) {
                this.resultCache.put(it2.next(), Boolean.valueOf(stronglyConnectedComponent.needsIsInitializedCheck));
            }
        }
    }

    private static boolean needsIsInitializedCheck(Descriptors.Descriptor descriptor) {
        return isInitializedCheckAnalyzer.needsIsInitializedCheck(descriptor);
    }

    private static StructuralMessageInfo convertProto2(Class<?> cls, Descriptors.Descriptor descriptor) {
        List<Descriptors.FieldDescriptor> fields = descriptor.getFields();
        StructuralMessageInfo.Builder builderNewBuilder = StructuralMessageInfo.newBuilder(fields.size());
        builderNewBuilder.withDefaultInstance(getDefaultInstance(cls));
        builderNewBuilder.withSyntax(ProtoSyntax.PROTO2);
        builderNewBuilder.withMessageSetWireFormat(descriptor.getOptions().getMessageSetWireFormat());
        AnonymousClass1 anonymousClass1 = null;
        OneofState oneofState = new OneofState();
        java.lang.reflect.Field fieldBitField = null;
        int i = 0;
        int i2 = 0;
        int i3 = 1;
        while (i < fields.size()) {
            final Descriptors.FieldDescriptor fieldDescriptor = fields.get(i);
            boolean javaStringCheckUtf8 = fieldDescriptor.getFile().getOptions().getJavaStringCheckUtf8();
            Internal.EnumVerifier enumVerifier = fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM ? new Internal.EnumVerifier() { // from class: com.google.protobuf.DescriptorMessageInfoFactory.1
                @Override // com.google.protobuf.Internal.EnumVerifier
                public boolean isInRange(int i4) {
                    return fieldDescriptor.getEnumType().findValueByNumber(i4) != null;
                }
            } : anonymousClass1;
            if (fieldDescriptor.getContainingOneof() != null) {
                builderNewBuilder.withField(buildOneofMember(cls, fieldDescriptor, oneofState, javaStringCheckUtf8, enumVerifier));
            } else {
                java.lang.reflect.Field field = field(cls, fieldDescriptor);
                int number = fieldDescriptor.getNumber();
                FieldType fieldType = getFieldType(fieldDescriptor);
                if (fieldDescriptor.isMapField()) {
                    final Descriptors.FieldDescriptor fieldDescriptorFindFieldByNumber = fieldDescriptor.getMessageType().findFieldByNumber(2);
                    if (fieldDescriptorFindFieldByNumber.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM) {
                        enumVerifier = new Internal.EnumVerifier() { // from class: com.google.protobuf.DescriptorMessageInfoFactory.2
                            @Override // com.google.protobuf.Internal.EnumVerifier
                            public boolean isInRange(int i4) {
                                return fieldDescriptorFindFieldByNumber.getEnumType().findValueByNumber(i4) != null;
                            }
                        };
                    }
                    builderNewBuilder.withField(FieldInfo.forMapField(field, number, SchemaUtil.getMapDefaultEntry(cls, fieldDescriptor.getName()), enumVerifier));
                } else if (!fieldDescriptor.isRepeated()) {
                    if (fieldBitField == null) {
                        fieldBitField = bitField(cls, i2);
                    }
                    if (fieldDescriptor.isRequired()) {
                        builderNewBuilder.withField(FieldInfo.forProto2RequiredField(field, number, fieldType, fieldBitField, i3, javaStringCheckUtf8, enumVerifier));
                    } else {
                        builderNewBuilder.withField(FieldInfo.forProto2OptionalField(field, number, fieldType, fieldBitField, i3, javaStringCheckUtf8, enumVerifier));
                    }
                } else if (enumVerifier != null) {
                    if (fieldDescriptor.isPacked()) {
                        builderNewBuilder.withField(FieldInfo.forPackedFieldWithEnumVerifier(field, number, fieldType, enumVerifier, cachedSizeField(cls, fieldDescriptor)));
                    } else {
                        builderNewBuilder.withField(FieldInfo.forFieldWithEnumVerifier(field, number, fieldType, enumVerifier));
                    }
                } else if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    builderNewBuilder.withField(FieldInfo.forRepeatedMessageField(field, number, fieldType, getTypeForRepeatedMessageField(cls, fieldDescriptor)));
                } else if (fieldDescriptor.isPacked()) {
                    builderNewBuilder.withField(FieldInfo.forPackedField(field, number, fieldType, cachedSizeField(cls, fieldDescriptor)));
                } else {
                    builderNewBuilder.withField(FieldInfo.forField(field, number, fieldType, javaStringCheckUtf8));
                }
                i++;
                anonymousClass1 = null;
            }
            i3 <<= 1;
            if (i3 == 0) {
                i2++;
                fieldBitField = null;
                i3 = 1;
            }
            i++;
            anonymousClass1 = null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i4 = 0; i4 < fields.size(); i4++) {
            Descriptors.FieldDescriptor fieldDescriptor2 = fields.get(i4);
            if (fieldDescriptor2.isRequired() || (fieldDescriptor2.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE && needsIsInitializedCheck(fieldDescriptor2.getMessageType()))) {
                arrayList.add(Integer.valueOf(fieldDescriptor2.getNumber()));
            }
        }
        int[] iArr = new int[arrayList.size()];
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            iArr[i5] = ((Integer) arrayList.get(i5)).intValue();
        }
        builderNewBuilder.withCheckInitialized(iArr);
        return builderNewBuilder.build();
    }

    private static StructuralMessageInfo convertProto3(Class<?> cls, Descriptors.Descriptor descriptor) {
        List<Descriptors.FieldDescriptor> fields = descriptor.getFields();
        StructuralMessageInfo.Builder builderNewBuilder = StructuralMessageInfo.newBuilder(fields.size());
        builderNewBuilder.withDefaultInstance(getDefaultInstance(cls));
        builderNewBuilder.withSyntax(ProtoSyntax.PROTO3);
        OneofState oneofState = new OneofState();
        for (int i = 0; i < fields.size(); i++) {
            Descriptors.FieldDescriptor fieldDescriptor = fields.get(i);
            if (fieldDescriptor.getContainingOneof() != null) {
                builderNewBuilder.withField(buildOneofMember(cls, fieldDescriptor, oneofState, true, null));
            } else if (fieldDescriptor.isMapField()) {
                builderNewBuilder.withField(FieldInfo.forMapField(field(cls, fieldDescriptor), fieldDescriptor.getNumber(), SchemaUtil.getMapDefaultEntry(cls, fieldDescriptor.getName()), null));
            } else if (fieldDescriptor.isRepeated() && fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                builderNewBuilder.withField(FieldInfo.forRepeatedMessageField(field(cls, fieldDescriptor), fieldDescriptor.getNumber(), getFieldType(fieldDescriptor), getTypeForRepeatedMessageField(cls, fieldDescriptor)));
            } else if (fieldDescriptor.isPacked()) {
                builderNewBuilder.withField(FieldInfo.forPackedField(field(cls, fieldDescriptor), fieldDescriptor.getNumber(), getFieldType(fieldDescriptor), cachedSizeField(cls, fieldDescriptor)));
            } else {
                builderNewBuilder.withField(FieldInfo.forField(field(cls, fieldDescriptor), fieldDescriptor.getNumber(), getFieldType(fieldDescriptor), true));
            }
        }
        return builderNewBuilder.build();
    }

    private static FieldInfo buildOneofMember(Class<?> cls, Descriptors.FieldDescriptor fieldDescriptor, OneofState oneofState, boolean z, Internal.EnumVerifier enumVerifier) {
        OneofInfo oneof = oneofState.getOneof(cls, fieldDescriptor.getContainingOneof());
        FieldType fieldType = getFieldType(fieldDescriptor);
        return FieldInfo.forOneofMemberField(fieldDescriptor.getNumber(), fieldType, oneof, getOneofStoredType(cls, fieldDescriptor, fieldType), z, enumVerifier);
    }

    private static Class<?> getOneofStoredType(Class<?> cls, Descriptors.FieldDescriptor fieldDescriptor, FieldType fieldType) {
        switch (AnonymousClass3.$SwitchMap$com$google$protobuf$JavaType[fieldType.getJavaType().ordinal()]) {
            case 1:
                return Boolean.class;
            case 2:
                return ByteString.class;
            case 3:
                return Double.class;
            case 4:
                return Float.class;
            case 5:
            case 6:
                return Integer.class;
            case 7:
                return Long.class;
            case 8:
                return String.class;
            case 9:
                return getOneofStoredTypeForMessage(cls, fieldDescriptor);
            default:
                throw new IllegalArgumentException("Invalid type for oneof: " + fieldType);
        }
    }

    /* JADX INFO: renamed from: com.google.protobuf.DescriptorMessageInfoFactory$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$JavaType;

        static {
            int[] iArr = new int[Descriptors.FieldDescriptor.Type.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = iArr;
            try {
                iArr[Descriptors.FieldDescriptor.Type.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FLOAT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.GROUP.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.INT32.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.INT64.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED32.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT32.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT64.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.STRING.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT32.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT64.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            int[] iArr2 = new int[JavaType.values().length];
            $SwitchMap$com$google$protobuf$JavaType = iArr2;
            try {
                iArr2[JavaType.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.BYTE_STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.FLOAT.ordinal()] = 4;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.ENUM.ordinal()] = 5;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.INT.ordinal()] = 6;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.LONG.ordinal()] = 7;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.STRING.ordinal()] = 8;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$google$protobuf$JavaType[JavaType.MESSAGE.ordinal()] = 9;
            } catch (NoSuchFieldError unused27) {
            }
            int[] iArr3 = new int[Descriptors.FileDescriptor.Syntax.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax = iArr3;
            try {
                iArr3[Descriptors.FileDescriptor.Syntax.PROTO2.ordinal()] = 1;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FileDescriptor$Syntax[Descriptors.FileDescriptor.Syntax.PROTO3.ordinal()] = 2;
            } catch (NoSuchFieldError unused29) {
            }
        }
    }

    private static FieldType getFieldType(Descriptors.FieldDescriptor fieldDescriptor) {
        switch (AnonymousClass3.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[fieldDescriptor.getType().ordinal()]) {
            case 1:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.BOOL_LIST_PACKED : FieldType.BOOL_LIST;
                }
                return FieldType.BOOL;
            case 2:
                return fieldDescriptor.isRepeated() ? FieldType.BYTES_LIST : FieldType.BYTES;
            case 3:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.DOUBLE_LIST_PACKED : FieldType.DOUBLE_LIST;
                }
                return FieldType.DOUBLE;
            case 4:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.ENUM_LIST_PACKED : FieldType.ENUM_LIST;
                }
                return FieldType.ENUM;
            case 5:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.FIXED32_LIST_PACKED : FieldType.FIXED32_LIST;
                }
                return FieldType.FIXED32;
            case 6:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.FIXED64_LIST_PACKED : FieldType.FIXED64_LIST;
                }
                return FieldType.FIXED64;
            case 7:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.FLOAT_LIST_PACKED : FieldType.FLOAT_LIST;
                }
                return FieldType.FLOAT;
            case 8:
                return fieldDescriptor.isRepeated() ? FieldType.GROUP_LIST : FieldType.GROUP;
            case 9:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.INT32_LIST_PACKED : FieldType.INT32_LIST;
                }
                return FieldType.INT32;
            case 10:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.INT64_LIST_PACKED : FieldType.INT64_LIST;
                }
                return FieldType.INT64;
            case 11:
                if (fieldDescriptor.isMapField()) {
                    return FieldType.MAP;
                }
                return fieldDescriptor.isRepeated() ? FieldType.MESSAGE_LIST : FieldType.MESSAGE;
            case 12:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.SFIXED32_LIST_PACKED : FieldType.SFIXED32_LIST;
                }
                return FieldType.SFIXED32;
            case 13:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.SFIXED64_LIST_PACKED : FieldType.SFIXED64_LIST;
                }
                return FieldType.SFIXED64;
            case 14:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.SINT32_LIST_PACKED : FieldType.SINT32_LIST;
                }
                return FieldType.SINT32;
            case 15:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.SINT64_LIST_PACKED : FieldType.SINT64_LIST;
                }
                return FieldType.SINT64;
            case 16:
                return fieldDescriptor.isRepeated() ? FieldType.STRING_LIST : FieldType.STRING;
            case 17:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.UINT32_LIST_PACKED : FieldType.UINT32_LIST;
                }
                return FieldType.UINT32;
            case 18:
                if (fieldDescriptor.isRepeated()) {
                    return fieldDescriptor.isPacked() ? FieldType.UINT64_LIST_PACKED : FieldType.UINT64_LIST;
                }
                return FieldType.UINT64;
            default:
                throw new IllegalArgumentException("Unsupported field type: " + fieldDescriptor.getType());
        }
    }

    private static java.lang.reflect.Field bitField(Class<?> cls, int i) {
        return field(cls, "bitField" + i + "_");
    }

    private static java.lang.reflect.Field field(Class<?> cls, Descriptors.FieldDescriptor fieldDescriptor) {
        return field(cls, getFieldName(fieldDescriptor));
    }

    private static java.lang.reflect.Field cachedSizeField(Class<?> cls, Descriptors.FieldDescriptor fieldDescriptor) {
        return field(cls, getCachedSizeFieldName(fieldDescriptor));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static java.lang.reflect.Field field(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Exception unused) {
            throw new IllegalArgumentException("Unable to find field " + str + " in message class " + cls.getName());
        }
    }

    static String getFieldName(Descriptors.FieldDescriptor fieldDescriptor) {
        String name;
        if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
            name = fieldDescriptor.getMessageType().getName();
        } else {
            name = fieldDescriptor.getName();
        }
        return snakeCaseToCamelCase(name) + (specialFieldNames.contains(name) ? "__" : "_");
    }

    private static String getCachedSizeFieldName(Descriptors.FieldDescriptor fieldDescriptor) {
        return snakeCaseToCamelCase(fieldDescriptor.getName()) + "MemoizedSerializedSize";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String snakeCaseToCamelCase(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 1);
        boolean z = false;
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '_') {
                z = true;
            } else if (Character.isDigit(cCharAt)) {
                sb.append(cCharAt);
                z = true;
            } else if (z) {
                sb.append(Character.toUpperCase(cCharAt));
                z = false;
            } else if (i == 0) {
                sb.append(Character.toLowerCase(cCharAt));
            } else {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    private static Class<?> getOneofStoredTypeForMessage(Class<?> cls, Descriptors.FieldDescriptor fieldDescriptor) {
        try {
            return cls.getDeclaredMethod(getterForField(fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP ? fieldDescriptor.getMessageType().getName() : fieldDescriptor.getName()), null).getReturnType();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Class<?> getTypeForRepeatedMessageField(Class<?> cls, Descriptors.FieldDescriptor fieldDescriptor) {
        try {
            return cls.getDeclaredMethod(getterForField(fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP ? fieldDescriptor.getMessageType().getName() : fieldDescriptor.getName()), Integer.TYPE).getReturnType();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getterForField(String str) {
        String strSnakeCaseToCamelCase = snakeCaseToCamelCase(str);
        return "get" + Character.toUpperCase(strSnakeCaseToCamelCase.charAt(0)) + strSnakeCaseToCamelCase.substring(1, strSnakeCaseToCamelCase.length());
    }

    private static final class OneofState {
        private OneofInfo[] oneofs;

        private OneofState() {
            this.oneofs = new OneofInfo[2];
        }

        OneofInfo getOneof(Class<?> cls, Descriptors.OneofDescriptor oneofDescriptor) {
            int index = oneofDescriptor.getIndex();
            OneofInfo[] oneofInfoArr = this.oneofs;
            if (index >= oneofInfoArr.length) {
                this.oneofs = (OneofInfo[]) Arrays.copyOf(oneofInfoArr, index * 2);
            }
            OneofInfo oneofInfo = this.oneofs[index];
            if (oneofInfo != null) {
                return oneofInfo;
            }
            OneofInfo oneofInfoNewInfo = newInfo(cls, oneofDescriptor);
            this.oneofs[index] = oneofInfoNewInfo;
            return oneofInfoNewInfo;
        }

        private static OneofInfo newInfo(Class<?> cls, Descriptors.OneofDescriptor oneofDescriptor) {
            String strSnakeCaseToCamelCase = DescriptorMessageInfoFactory.snakeCaseToCamelCase(oneofDescriptor.getName());
            return new OneofInfo(oneofDescriptor.getIndex(), DescriptorMessageInfoFactory.field(cls, strSnakeCaseToCamelCase + "Case_"), DescriptorMessageInfoFactory.field(cls, strSnakeCaseToCamelCase + "_"));
        }
    }
}
