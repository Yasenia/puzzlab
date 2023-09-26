package io.github.yasenia.puzzle.cube.standard.rubiks;

import io.github.yasenia.puzzle.cube.standard.Face;
import io.github.yasenia.puzzle.cube.standard.geometric.accessor.PlanarAccessor;
import io.github.yasenia.support.tupple.Triple;

import java.util.Objects;
import java.util.function.Supplier;

import static io.github.yasenia.support.functional.Memoization.memoize;

public class RubiksFace implements Face.Odd<RubiksFacelet> {

    public final RubiksFacelet leftTop;
    public final RubiksFacelet top;
    public final RubiksFacelet rightTop;
    public final RubiksFacelet left;
    public final RubiksFacelet center;
    public final RubiksFacelet right;
    public final RubiksFacelet leftBottom;
    public final RubiksFacelet bottom;
    public final RubiksFacelet rightBottom;
    private final Supplier<Triple<Triple<RubiksFacelet>>> rows;
    private final Supplier<Triple<Triple<RubiksFacelet>>> columns;
    private final Supplier<PlanarAccessor<Triple<RubiksFacelet>>> sides;

    public RubiksFace(RubiksFacelet leftTop, RubiksFacelet top, RubiksFacelet rightTop,
                      RubiksFacelet left, RubiksFacelet center, RubiksFacelet right,
                      RubiksFacelet leftBottom, RubiksFacelet bottom, RubiksFacelet rightBottom) {
        this.leftTop = leftTop;
        this.top = top;
        this.rightTop = rightTop;
        this.left = left;
        this.center = center;
        this.right = right;
        this.leftBottom = leftBottom;
        this.bottom = bottom;
        this.rightBottom = rightBottom;
        this.columns = memoize(() -> Triple.of(
            Triple.of(leftTop, left, leftBottom),
            Triple.of(top, center, bottom),
            Triple.of(rightTop, right, rightBottom)
        ));
        this.rows = memoize(() -> Triple.of(
            Triple.of(leftTop, top, rightTop),
            Triple.of(left, center, right),
            Triple.of(leftBottom, bottom, rightBottom)
        ));
        this.sides = memoize(() -> new PlanarAccessor<>(
            new Triple<>(leftBottom, left, leftTop),
            new Triple<>(rightTop, right, rightBottom),
            new Triple<>(leftTop, top, rightTop),
            new Triple<>(rightBottom, bottom, leftBottom)
        ));
    }

    @Override
    public RubiksFacelet centerFacelet() {
        return this.center;
    }

    @Override
    public RubiksFacelet[] facelets() {
        return new RubiksFacelet[] {
            leftTop,
            top,
            rightTop,
            left,
            center,
            right,
            leftBottom,
            bottom,
            rightBottom
        };
    }

    public boolean isSolved() {
        return Objects.equals(center, left)
            && Objects.equals(center, right)
            && Objects.equals(center, top)
            && Objects.equals(center, bottom)
            && Objects.equals(center, leftTop)
            && Objects.equals(center, rightTop)
            && Objects.equals(center, leftBottom)
            && Objects.equals(center, rightBottom);
    }

    public PlanarAccessor<Triple<RubiksFacelet>> sides() {
        return sides.get();
    }

    public Triple<Triple<RubiksFacelet>> rows() {
        return rows.get();
    }

    public Triple<Triple<RubiksFacelet>> columns() {
        return columns.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        return (o instanceof RubiksFace that)
            && Objects.equals(leftTop, that.leftTop) && Objects.equals(top, that.top) && Objects.equals(rightTop, that.rightTop)
            && Objects.equals(left, that.left) && Objects.equals(center, that.center) && Objects.equals(right, that.right)
            && Objects.equals(leftBottom, that.leftBottom) && Objects.equals(bottom, that.bottom) && Objects.equals(rightBottom, that.rightBottom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftTop, top, rightTop, left, center, right, leftBottom, bottom, rightBottom);
    }
}
