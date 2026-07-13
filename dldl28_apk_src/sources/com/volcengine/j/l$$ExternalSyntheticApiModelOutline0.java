package com.volcengine.j;

import java.nio.file.DirectoryStream;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystemLoopException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class l$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ Class m() {
        return BasicFileAttributes.class;
    }

    public static /* bridge */ /* synthetic */ DirectoryStream m(Object obj) {
        return (DirectoryStream) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ FileSystemException m6702m(Object obj) {
        return (FileSystemException) obj;
    }

    public static /* synthetic */ FileSystemException m(String str) {
        return new FileSystemException(str);
    }

    public static /* synthetic */ FileSystemException m(String str, String str2, String str3) {
        return new FileSystemException(str, str2, str3);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ FileSystemLoopException m6703m(String str) {
        return new FileSystemLoopException(str);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ FileVisitResult m6706m(Object obj) {
        return (FileVisitResult) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ FileVisitor m6707m(Object obj) {
        return (FileVisitor) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ NoSuchFileException m6709m(String str, String str2, String str3) {
        return new NoSuchFileException(str, str2, str3);
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Path m6710m(Object obj) {
        return (Path) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ SecureDirectoryStream m6712m(Object obj) {
        return (SecureDirectoryStream) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ BasicFileAttributeView m6715m(Object obj) {
        return (BasicFileAttributeView) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ BasicFileAttributes m6716m(Object obj) {
        return (BasicFileAttributes) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Optional m6718m(Object obj) {
        return (Optional) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ CompletionStage m6719m(Object obj) {
        return (CompletionStage) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ BiConsumer m6720m(Object obj) {
        return (BiConsumer) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Stream m6724m(Object obj) {
        return (Stream) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ void m6725m() {
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m6728m(Object obj) {
        return obj instanceof SecureDirectoryStream;
    }

    public static /* bridge */ /* synthetic */ Class m$1() {
        return BasicFileAttributeView.class;
    }

    /* JADX INFO: renamed from: m$1, reason: collision with other method in class */
    public static /* synthetic */ void m6737m$1() {
    }

    /* JADX INFO: renamed from: m$2, reason: collision with other method in class */
    public static /* synthetic */ void m6740m$2() {
    }
}
