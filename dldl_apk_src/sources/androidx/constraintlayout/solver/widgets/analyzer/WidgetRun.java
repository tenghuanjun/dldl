package androidx.constraintlayout.solver.widgets.analyzer;

import androidx.constraintlayout.solver.widgets.ConstraintAnchor;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
public abstract class WidgetRun implements Dependency {
    protected ConstraintWidget.DimensionBehaviour dimensionBehavior;
    public int matchConstraintsType;
    RunGroup runGroup;
    ConstraintWidget widget;
    DimensionDependency dimension = new DimensionDependency(this);
    public int orientation = 0;
    boolean resolved = false;
    public DependencyNode start = new DependencyNode(this);
    public DependencyNode end = new DependencyNode(this);
    protected RunType mRunType = RunType.NONE;

    enum RunType {
        NONE,
        START,
        END,
        CENTER
    }

    abstract void apply();

    abstract void applyToWidget();

    abstract void clear();

    abstract void reset();

    abstract boolean supportsWrapComputation();

    @Override // androidx.constraintlayout.solver.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
    }

    protected void updateRunEnd(Dependency dependency) {
    }

    protected void updateRunStart(Dependency dependency) {
    }

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.widget = constraintWidget;
    }

    public boolean isDimensionResolved() {
        return this.dimension.resolved;
    }

    public boolean isCenterConnection() {
        int size = this.start.targets.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            if (this.start.targets.get(i2).run != this) {
                i++;
            }
        }
        int size2 = this.end.targets.size();
        for (int i3 = 0; i3 < size2; i3++) {
            if (this.end.targets.get(i3).run != this) {
                i++;
            }
        }
        return i >= 2;
    }

    public long wrapSize(int i) {
        if (!this.dimension.resolved) {
            return 0L;
        }
        long j = this.dimension.value;
        if (isCenterConnection()) {
            return j + ((long) (this.start.margin - this.end.margin));
        }
        if (i == 0) {
            return j + ((long) this.start.margin);
        }
        return j - ((long) this.end.margin);
    }

    protected final DependencyNode getTarget(ConstraintAnchor constraintAnchor) {
        if (constraintAnchor.mTarget == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor.mTarget.mOwner;
        switch (constraintAnchor.mTarget.mType) {
            case LEFT:
                return constraintWidget.horizontalRun.start;
            case RIGHT:
                return constraintWidget.horizontalRun.end;
            case TOP:
                return constraintWidget.verticalRun.start;
            case BASELINE:
                return constraintWidget.verticalRun.baseline;
            case BOTTOM:
                return constraintWidget.verticalRun.end;
            default:
                return null;
        }
    }

    protected void updateRunCenter(Dependency dependency, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        float verticalBiasPercent;
        DependencyNode target = getTarget(constraintAnchor);
        DependencyNode target2 = getTarget(constraintAnchor2);
        if (target.resolved && target2.resolved) {
            int margin = target.value + constraintAnchor.getMargin();
            int margin2 = target2.value - constraintAnchor2.getMargin();
            int i2 = margin2 - margin;
            if (!this.dimension.resolved && this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                resolveDimension(i, i2);
            }
            if (this.dimension.resolved) {
                if (this.dimension.value == i2) {
                    this.start.resolve(margin);
                    this.end.resolve(margin2);
                    return;
                }
                if (i == 0) {
                    verticalBiasPercent = this.widget.getHorizontalBiasPercent();
                } else {
                    verticalBiasPercent = this.widget.getVerticalBiasPercent();
                }
                if (target == target2) {
                    margin = target.value;
                    margin2 = target2.value;
                    verticalBiasPercent = 0.5f;
                }
                this.start.resolve((int) (margin + 0.5f + (((margin2 - margin) - this.dimension.value) * verticalBiasPercent)));
                this.end.resolve(this.start.value + this.dimension.value);
            }
        }
    }

    private void resolveDimension(int i, int i2) {
        int i3;
        switch (this.matchConstraintsType) {
            case 0:
                this.dimension.resolve(getLimitedDimension(i2, i));
                break;
            case 1:
                this.dimension.resolve(Math.min(getLimitedDimension(this.dimension.wrapValue, i), i2));
                break;
            case 2:
                ConstraintWidget parent = this.widget.getParent();
                if (parent != null) {
                    if ((i == 0 ? parent.horizontalRun : parent.verticalRun).dimension.resolved) {
                        this.dimension.resolve(getLimitedDimension((int) ((r5.dimension.value * (i == 0 ? this.widget.mMatchConstraintPercentWidth : this.widget.mMatchConstraintPercentHeight)) + 0.5f), i));
                    }
                }
                break;
            case 3:
                if (this.widget.horizontalRun.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.widget.horizontalRun.matchConstraintsType != 3 || this.widget.verticalRun.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.widget.verticalRun.matchConstraintsType != 3) {
                    if ((i == 0 ? this.widget.verticalRun : this.widget.horizontalRun).dimension.resolved) {
                        float dimensionRatio = this.widget.getDimensionRatio();
                        if (i == 1) {
                            i3 = (int) ((r5.dimension.value / dimensionRatio) + 0.5f);
                        } else {
                            i3 = (int) ((dimensionRatio * r5.dimension.value) + 0.5f);
                        }
                        this.dimension.resolve(i3);
                    }
                }
                break;
        }
    }

    protected final int getLimitedDimension(int i, int i2) {
        if (i2 == 0) {
            int i3 = this.widget.mMatchConstraintMaxWidth;
            int iMax = Math.max(this.widget.mMatchConstraintMinWidth, i);
            if (i3 > 0) {
                iMax = Math.min(i3, i);
            }
            return iMax != i ? iMax : i;
        }
        int i4 = this.widget.mMatchConstraintMaxHeight;
        int iMin = i4 > 0 ? Math.min(i4, i) : Math.max(this.widget.mMatchConstraintMinHeight, i);
        return iMin != i ? iMin : i;
    }

    protected final DependencyNode getTarget(ConstraintAnchor constraintAnchor, int i) {
        if (constraintAnchor.mTarget == null) {
            return null;
        }
        ConstraintWidget constraintWidget = constraintAnchor.mTarget.mOwner;
        WidgetRun widgetRun = i == 0 ? constraintWidget.horizontalRun : constraintWidget.verticalRun;
        int i2 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type[constraintAnchor.mTarget.mType.ordinal()];
        if (i2 != 5) {
            switch (i2) {
                case 1:
                case 3:
                    return widgetRun.start;
                case 2:
                    break;
                default:
                    return null;
            }
        }
        return widgetRun.end;
    }

    protected final void addTarget(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i) {
        dependencyNode.targets.add(dependencyNode2);
        dependencyNode.margin = i;
        dependencyNode2.dependencies.add(dependencyNode);
    }

    protected final void addTarget(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i, DimensionDependency dimensionDependency) {
        dependencyNode.targets.add(dependencyNode2);
        dependencyNode.targets.add(this.dimension);
        dependencyNode.marginFactor = i;
        dependencyNode.marginDependency = dimensionDependency;
        dependencyNode2.dependencies.add(dependencyNode);
        dimensionDependency.dependencies.add(dependencyNode);
    }

    public long getWrapDimension() {
        if (this.dimension.resolved) {
            return this.dimension.value;
        }
        return 0L;
    }

    public boolean isResolved() {
        return this.resolved;
    }
}
