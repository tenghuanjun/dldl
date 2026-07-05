package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import androidx.constraintlayout.solver.widgets.Helper;
import androidx.constraintlayout.solver.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.solver.widgets.analyzer.WidgetRun;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
public class HorizontalWidgetRun extends WidgetRun {
    private static int[] tempDimensions = new int[2];

    public HorizontalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.start.type = DependencyNode.Type.LEFT;
        this.end.type = DependencyNode.Type.RIGHT;
        this.orientation = 0;
    }

    public String toString() {
        return "HorizontalRun " + this.widget.getDebugName();
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void clear() {
        this.runGroup = null;
        this.start.clear();
        this.end.clear();
        this.dimension.clear();
        this.resolved = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void reset() {
        this.resolved = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.dimension.resolved = false;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    boolean supportsWrapComputation() {
        return this.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.widget.mMatchConstraintDefaultWidth == 0;
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    void apply() {
        ConstraintWidget parent;
        ConstraintWidget parent2;
        if (this.widget.measured) {
            this.dimension.resolve(this.widget.getWidth());
        }
        if (!this.dimension.resolved) {
            this.dimensionBehavior = this.widget.getHorizontalDimensionBehaviour();
            if (this.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (((parent2 = this.widget.getParent()) != null && parent2.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) || parent2.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)) {
                    int width = (parent2.getWidth() - this.widget.mLeft.getMargin()) - this.widget.mRight.getMargin();
                    addTarget(this.start, parent2.horizontalRun.start, this.widget.mLeft.getMargin());
                    addTarget(this.end, parent2.horizontalRun.end, -this.widget.mRight.getMargin());
                    this.dimension.resolve(width);
                    return;
                }
                if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.dimension.resolve(this.widget.getWidth());
                }
            }
        } else if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (((parent = this.widget.getParent()) != null && parent.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED) || parent.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)) {
            addTarget(this.start, parent.horizontalRun.start, this.widget.mLeft.getMargin());
            addTarget(this.end, parent.horizontalRun.end, -this.widget.mRight.getMargin());
            return;
        }
        if (this.dimension.resolved && this.widget.measured) {
            if (this.widget.mListAnchors[0].mTarget != null && this.widget.mListAnchors[1].mTarget != null) {
                if (this.widget.isInHorizontalChain()) {
                    this.start.margin = this.widget.mListAnchors[0].getMargin();
                    this.end.margin = -this.widget.mListAnchors[1].getMargin();
                    return;
                }
                DependencyNode target = getTarget(this.widget.mListAnchors[0]);
                if (target != null) {
                    addTarget(this.start, target, this.widget.mListAnchors[0].getMargin());
                }
                DependencyNode target2 = getTarget(this.widget.mListAnchors[1]);
                if (target2 != null) {
                    addTarget(this.end, target2, -this.widget.mListAnchors[1].getMargin());
                }
                this.start.delegateToWidgetRun = true;
                this.end.delegateToWidgetRun = true;
                return;
            }
            if (this.widget.mListAnchors[0].mTarget != null) {
                DependencyNode target3 = getTarget(this.widget.mListAnchors[0]);
                if (target3 != null) {
                    addTarget(this.start, target3, this.widget.mListAnchors[0].getMargin());
                    addTarget(this.end, this.start, this.dimension.value);
                    return;
                }
                return;
            }
            if (this.widget.mListAnchors[1].mTarget != null) {
                DependencyNode target4 = getTarget(this.widget.mListAnchors[1]);
                if (target4 != null) {
                    addTarget(this.end, target4, -this.widget.mListAnchors[1].getMargin());
                    addTarget(this.start, this.end, -this.dimension.value);
                    return;
                }
                return;
            }
            if ((this.widget instanceof Helper) || this.widget.getParent() == null || this.widget.getAnchor(ConstraintAnchor.Type.CENTER).mTarget != null) {
                return;
            }
            addTarget(this.start, this.widget.getParent().horizontalRun.start, this.widget.getX());
            addTarget(this.end, this.start, this.dimension.value);
            return;
        }
        if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            switch (this.widget.mMatchConstraintDefaultWidth) {
                case 2:
                    ConstraintWidget parent3 = this.widget.getParent();
                    if (parent3 != null) {
                        DimensionDependency dimensionDependency = parent3.verticalRun.dimension;
                        this.dimension.targets.add(dimensionDependency);
                        dimensionDependency.dependencies.add(this.dimension);
                        this.dimension.delegateToWidgetRun = true;
                        this.dimension.dependencies.add(this.start);
                        this.dimension.dependencies.add(this.end);
                    }
                    break;
                case 3:
                    if (this.widget.mMatchConstraintDefaultHeight == 3) {
                        this.start.updateDelegate = this;
                        this.end.updateDelegate = this;
                        this.widget.verticalRun.start.updateDelegate = this;
                        this.widget.verticalRun.end.updateDelegate = this;
                        this.dimension.updateDelegate = this;
                        if (this.widget.isInVerticalChain()) {
                            this.dimension.targets.add(this.widget.verticalRun.dimension);
                            this.widget.verticalRun.dimension.dependencies.add(this.dimension);
                            this.widget.verticalRun.dimension.updateDelegate = this;
                            this.dimension.targets.add(this.widget.verticalRun.start);
                            this.dimension.targets.add(this.widget.verticalRun.end);
                            this.widget.verticalRun.start.dependencies.add(this.dimension);
                            this.widget.verticalRun.end.dependencies.add(this.dimension);
                        } else if (this.widget.isInHorizontalChain()) {
                            this.widget.verticalRun.dimension.targets.add(this.dimension);
                            this.dimension.dependencies.add(this.widget.verticalRun.dimension);
                        } else {
                            this.widget.verticalRun.dimension.targets.add(this.dimension);
                        }
                    } else {
                        DimensionDependency dimensionDependency2 = this.widget.verticalRun.dimension;
                        this.dimension.targets.add(dimensionDependency2);
                        dimensionDependency2.dependencies.add(this.dimension);
                        this.widget.verticalRun.start.dependencies.add(this.dimension);
                        this.widget.verticalRun.end.dependencies.add(this.dimension);
                        this.dimension.delegateToWidgetRun = true;
                        this.dimension.dependencies.add(this.start);
                        this.dimension.dependencies.add(this.end);
                        this.start.targets.add(this.dimension);
                        this.end.targets.add(this.dimension);
                    }
                    break;
            }
        }
        if (this.widget.mListAnchors[0].mTarget != null && this.widget.mListAnchors[1].mTarget != null) {
            if (this.widget.isInHorizontalChain()) {
                this.start.margin = this.widget.mListAnchors[0].getMargin();
                this.end.margin = -this.widget.mListAnchors[1].getMargin();
                return;
            }
            DependencyNode target5 = getTarget(this.widget.mListAnchors[0]);
            DependencyNode target6 = getTarget(this.widget.mListAnchors[1]);
            target5.addDependency(this);
            target6.addDependency(this);
            this.mRunType = WidgetRun.RunType.CENTER;
            return;
        }
        if (this.widget.mListAnchors[0].mTarget != null) {
            DependencyNode target7 = getTarget(this.widget.mListAnchors[0]);
            if (target7 != null) {
                addTarget(this.start, target7, this.widget.mListAnchors[0].getMargin());
                addTarget(this.end, this.start, 1, this.dimension);
                return;
            }
            return;
        }
        if (this.widget.mListAnchors[1].mTarget != null) {
            DependencyNode target8 = getTarget(this.widget.mListAnchors[1]);
            if (target8 != null) {
                addTarget(this.end, target8, -this.widget.mListAnchors[1].getMargin());
                addTarget(this.start, this.end, -1, this.dimension);
                return;
            }
            return;
        }
        if ((this.widget instanceof Helper) || this.widget.getParent() == null) {
            return;
        }
        addTarget(this.start, this.widget.getParent().horizontalRun.start, this.widget.getX());
        addTarget(this.end, this.start, 1, this.dimension);
    }

    private void computeInsetRatio(int[] iArr, int i, int i2, int i3, int i4, float f, int i5) {
        int i6 = i2 - i;
        int i7 = i4 - i3;
        switch (i5) {
            case -1:
                int i8 = (int) ((i7 * f) + 0.5f);
                int i9 = (int) ((i6 / f) + 0.5f);
                if (i8 <= i6 && i7 <= i7) {
                    iArr[0] = i8;
                    iArr[1] = i7;
                    break;
                } else if (i6 <= i6 && i9 <= i7) {
                    iArr[0] = i6;
                    iArr[1] = i9;
                    break;
                }
                break;
            case 0:
                iArr[0] = (int) ((i7 * f) + 0.5f);
                iArr[1] = i7;
                break;
            case 1:
                iArr[0] = i6;
                iArr[1] = (int) ((i6 * f) + 0.5f);
                break;
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun, androidx.constraintlayout.solver.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        int dimensionRatio;
        switch (this.mRunType) {
            case START:
                updateRunStart(dependency);
                break;
            case END:
                updateRunEnd(dependency);
                break;
            case CENTER:
                updateRunCenter(dependency, this.widget.mLeft, this.widget.mRight, 0);
                return;
        }
        if (!this.dimension.resolved && this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            switch (this.widget.mMatchConstraintDefaultWidth) {
                case 2:
                    ConstraintWidget parent = this.widget.getParent();
                    if (parent != null && parent.horizontalRun.dimension.resolved) {
                        this.dimension.resolve((int) ((parent.horizontalRun.dimension.value * this.widget.mMatchConstraintPercentWidth) + 0.5f));
                    }
                    break;
                case 3:
                    if (this.widget.mMatchConstraintDefaultHeight == 0 || this.widget.mMatchConstraintDefaultHeight == 3) {
                        DependencyNode dependencyNode = this.widget.verticalRun.start;
                        DependencyNode dependencyNode2 = this.widget.verticalRun.end;
                        boolean z = this.widget.mLeft.mTarget != null;
                        boolean z2 = this.widget.mTop.mTarget != null;
                        boolean z3 = this.widget.mRight.mTarget != null;
                        boolean z4 = this.widget.mBottom.mTarget != null;
                        int dimensionRatioSide = this.widget.getDimensionRatioSide();
                        if (z && z2 && z3 && z4) {
                            float dimensionRatio2 = this.widget.getDimensionRatio();
                            if (dependencyNode.resolved && dependencyNode2.resolved) {
                                if (this.start.readyToSolve && this.end.readyToSolve) {
                                    computeInsetRatio(tempDimensions, this.start.targets.get(0).value + this.start.margin, this.end.targets.get(0).value - this.end.margin, dependencyNode.value + dependencyNode.margin, dependencyNode2.value - dependencyNode2.margin, dimensionRatio2, dimensionRatioSide);
                                    this.dimension.resolve(tempDimensions[0]);
                                    this.widget.verticalRun.dimension.resolve(tempDimensions[1]);
                                    return;
                                }
                                return;
                            }
                            if (this.start.resolved && this.end.resolved) {
                                if (!dependencyNode.readyToSolve || !dependencyNode2.readyToSolve) {
                                    return;
                                }
                                computeInsetRatio(tempDimensions, this.start.value + this.start.margin, this.end.value - this.end.margin, dependencyNode.targets.get(0).value + dependencyNode.margin, dependencyNode2.targets.get(0).value - dependencyNode2.margin, dimensionRatio2, dimensionRatioSide);
                                this.dimension.resolve(tempDimensions[0]);
                                this.widget.verticalRun.dimension.resolve(tempDimensions[1]);
                            }
                            if (!this.start.readyToSolve || !this.end.readyToSolve || !dependencyNode.readyToSolve || !dependencyNode2.readyToSolve) {
                                return;
                            }
                            computeInsetRatio(tempDimensions, this.start.targets.get(0).value + this.start.margin, this.end.targets.get(0).value - this.end.margin, dependencyNode.targets.get(0).value + dependencyNode.margin, dependencyNode2.targets.get(0).value - dependencyNode2.margin, dimensionRatio2, dimensionRatioSide);
                            this.dimension.resolve(tempDimensions[0]);
                            this.widget.verticalRun.dimension.resolve(tempDimensions[1]);
                            break;
                        } else if (z && z3) {
                            if (this.start.readyToSolve && this.end.readyToSolve) {
                                float dimensionRatio3 = this.widget.getDimensionRatio();
                                int i = this.start.targets.get(0).value + this.start.margin;
                                int i2 = this.end.targets.get(0).value - this.end.margin;
                                switch (dimensionRatioSide) {
                                    case -1:
                                    case 0:
                                        int limitedDimension = getLimitedDimension(i2 - i, 0);
                                        int i3 = (int) ((limitedDimension * dimensionRatio3) + 0.5f);
                                        int limitedDimension2 = getLimitedDimension(i3, 1);
                                        if (i3 != limitedDimension2) {
                                            limitedDimension = (int) ((limitedDimension2 / dimensionRatio3) + 0.5f);
                                        }
                                        this.dimension.resolve(limitedDimension);
                                        this.widget.verticalRun.dimension.resolve(limitedDimension2);
                                        break;
                                    case 1:
                                        int limitedDimension3 = getLimitedDimension(i2 - i, 0);
                                        int i4 = (int) ((limitedDimension3 / dimensionRatio3) + 0.5f);
                                        int limitedDimension4 = getLimitedDimension(i4, 1);
                                        if (i4 != limitedDimension4) {
                                            limitedDimension3 = (int) ((limitedDimension4 * dimensionRatio3) + 0.5f);
                                        }
                                        this.dimension.resolve(limitedDimension3);
                                        this.widget.verticalRun.dimension.resolve(limitedDimension4);
                                        break;
                                }
                            } else {
                                return;
                            }
                        } else if (z2 && z4) {
                            if (dependencyNode.readyToSolve && dependencyNode2.readyToSolve) {
                                float dimensionRatio4 = this.widget.getDimensionRatio();
                                int i5 = dependencyNode.targets.get(0).value + dependencyNode.margin;
                                int i6 = dependencyNode2.targets.get(0).value - dependencyNode2.margin;
                                switch (dimensionRatioSide) {
                                    case -1:
                                    case 1:
                                        int limitedDimension5 = getLimitedDimension(i6 - i5, 1);
                                        int i7 = (int) ((limitedDimension5 / dimensionRatio4) + 0.5f);
                                        int limitedDimension6 = getLimitedDimension(i7, 0);
                                        if (i7 != limitedDimension6) {
                                            limitedDimension5 = (int) ((limitedDimension6 * dimensionRatio4) + 0.5f);
                                        }
                                        this.dimension.resolve(limitedDimension6);
                                        this.widget.verticalRun.dimension.resolve(limitedDimension5);
                                        break;
                                    case 0:
                                        int limitedDimension7 = getLimitedDimension(i6 - i5, 1);
                                        int i8 = (int) ((limitedDimension7 * dimensionRatio4) + 0.5f);
                                        int limitedDimension8 = getLimitedDimension(i8, 0);
                                        if (i8 != limitedDimension8) {
                                            limitedDimension7 = (int) ((limitedDimension8 / dimensionRatio4) + 0.5f);
                                        }
                                        this.dimension.resolve(limitedDimension8);
                                        this.widget.verticalRun.dimension.resolve(limitedDimension7);
                                        break;
                                }
                            } else {
                                return;
                            }
                        }
                    } else {
                        switch (this.widget.getDimensionRatioSide()) {
                            case -1:
                                dimensionRatio = (int) ((this.widget.verticalRun.dimension.value * this.widget.getDimensionRatio()) + 0.5f);
                                break;
                            case 0:
                                dimensionRatio = (int) ((this.widget.verticalRun.dimension.value / this.widget.getDimensionRatio()) + 0.5f);
                                break;
                            case 1:
                                dimensionRatio = (int) ((this.widget.verticalRun.dimension.value * this.widget.getDimensionRatio()) + 0.5f);
                                break;
                            default:
                                dimensionRatio = 0;
                                break;
                        }
                        this.dimension.resolve(dimensionRatio);
                        break;
                    }
                    break;
            }
        }
        if (this.start.readyToSolve && this.end.readyToSolve) {
            if (this.start.resolved && this.end.resolved && this.dimension.resolved) {
                return;
            }
            if (!this.dimension.resolved && this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.widget.mMatchConstraintDefaultWidth == 0 && !this.widget.isInHorizontalChain()) {
                DependencyNode dependencyNode3 = this.start.targets.get(0);
                DependencyNode dependencyNode4 = this.end.targets.get(0);
                int i9 = dependencyNode3.value + this.start.margin;
                int i10 = dependencyNode4.value + this.end.margin;
                this.start.resolve(i9);
                this.end.resolve(i10);
                this.dimension.resolve(i10 - i9);
                return;
            }
            if (!this.dimension.resolved && this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && this.matchConstraintsType == 1 && this.start.targets.size() > 0 && this.end.targets.size() > 0) {
                int iMin = Math.min((this.end.targets.get(0).value + this.end.margin) - (this.start.targets.get(0).value + this.start.margin), this.dimension.wrapValue);
                int i11 = this.widget.mMatchConstraintMaxWidth;
                int iMax = Math.max(this.widget.mMatchConstraintMinWidth, iMin);
                if (i11 > 0) {
                    iMax = Math.min(i11, iMax);
                }
                this.dimension.resolve(iMax);
            }
            if (this.dimension.resolved) {
                DependencyNode dependencyNode5 = this.start.targets.get(0);
                DependencyNode dependencyNode6 = this.end.targets.get(0);
                int i12 = dependencyNode5.value + this.start.margin;
                int i13 = dependencyNode6.value + this.end.margin;
                float horizontalBiasPercent = this.widget.getHorizontalBiasPercent();
                if (dependencyNode5 == dependencyNode6) {
                    i12 = dependencyNode5.value;
                    i13 = dependencyNode6.value;
                    horizontalBiasPercent = 0.5f;
                }
                this.start.resolve((int) (i12 + 0.5f + (((i13 - i12) - this.dimension.value) * horizontalBiasPercent)));
                this.end.resolve(this.start.value + this.dimension.value);
            }
        }
    }

    @Override // androidx.constraintlayout.solver.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        if (this.start.resolved) {
            this.widget.setX(this.start.value);
        }
    }
}
