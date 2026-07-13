package com.volcengine.cloudphone.base;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class VeAudioFrame {
    public VeAudioChannel channel;
    public ByteBuffer dataBuffer;
    public int dataSize;
    public VeAudioFrameType frameType;
    public VeAudioSampleRate sampleRate;
    public long timestamp_us;

    public enum VeAudioChannel {
        AUDIO_CHANNEL_AUTO(-1),
        AUDIO_CHANNEL_MONO(1),
        AUDIO_CHANNEL_STEREO(2);

        public final int value;

        VeAudioChannel(int i) {
            this.value = i;
        }

        public static VeAudioChannel fromValue(int i) {
            for (VeAudioChannel veAudioChannel : values()) {
                if (veAudioChannel.value == i) {
                    return veAudioChannel;
                }
            }
            return null;
        }
    }

    public enum VeAudioFrameType {
        FRAME_TYPE_PCM16(0);

        final int value;

        VeAudioFrameType(int i) {
            this.value = i;
        }

        public static VeAudioFrameType fromValue(int i) {
            for (VeAudioFrameType veAudioFrameType : values()) {
                if (veAudioFrameType.value == i) {
                    return veAudioFrameType;
                }
            }
            return null;
        }
    }

    public enum VeAudioSampleRate {
        AUDIO_SAMPLE_RATE_AUTO(-1),
        AUDIO_SAMPLE_RATE_8000(8000),
        AUDIO_SAMPLE_RATE_16000(16000),
        AUDIO_SAMPLE_RATE_32000(32000),
        AUDIO_SAMPLE_RATE_44100(44100),
        AUDIO_SAMPLE_RATE_48000(48000);

        public final int value;

        VeAudioSampleRate(int i) {
            this.value = i;
        }

        public static VeAudioSampleRate fromValue(int i) {
            for (VeAudioSampleRate veAudioSampleRate : values()) {
                if (veAudioSampleRate.value == i) {
                    return veAudioSampleRate;
                }
            }
            return null;
        }
    }

    public VeAudioFrame() {
    }

    public VeAudioFrame(long j, VeAudioSampleRate veAudioSampleRate, VeAudioChannel veAudioChannel, ByteBuffer byteBuffer, int i, VeAudioFrameType veAudioFrameType) {
        this.timestamp_us = j;
        this.sampleRate = veAudioSampleRate;
        this.channel = veAudioChannel;
        this.dataBuffer = byteBuffer;
        this.dataSize = i;
        this.frameType = veAudioFrameType;
    }

    public String toString() {
        return "VeAudioFrame{timestamp_us=" + this.timestamp_us + ", sampleRate=" + this.sampleRate + ", channel=" + this.channel + ", dataSize=" + this.dataSize + ", frameType=" + this.frameType + '}';
    }
}
