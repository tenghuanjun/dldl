package com.donkingliang.consecutivescroller;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes3.dex */
public class OverScrollInterpolator implements Interpolator {
    public static int INTERPOLATOR_DECELERATE = 1;
    public static int INTERPOLATOR_VISCOUS_FLUID = 0;
    private static final float VISCOUS_FLUID_NORMALIZE;
    private static final float VISCOUS_FLUID_OFFSET;
    private static final float VISCOUS_FLUID_SCALE = 8.0f;
    private int type;

    public OverScrollInterpolator(int type) {
        this.type = type;
    }

    static {
        float fViscousFluid = 1.0f / viscousFluid(1.0f);
        VISCOUS_FLUID_NORMALIZE = fViscousFluid;
        VISCOUS_FLUID_OFFSET = 1.0f - (fViscousFluid * viscousFluid(1.0f));
    }

    private static float viscousFluid(float x) {
        float f = x * 8.0f;
        if (f < 1.0f) {
            return f - (1.0f - ((float) Math.exp(-f)));
        }
        return 0.36787945f + ((1.0f - ((float) Math.exp(1.0f - f))) * 0.63212055f);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float input) {
        if (this.type == INTERPOLATOR_DECELERATE) {
            float f = 1.0f - input;
            return 1.0f - (f * f);
        }
        float fViscousFluid = VISCOUS_FLUID_NORMALIZE * viscousFluid(input);
        return fViscousFluid > 0.0f ? fViscousFluid + VISCOUS_FLUID_OFFSET : fViscousFluid;
    }
}
