package io.github.yasenia.puzzle.cube.standard.rubiks.impl;

import io.github.yasenia.puzzle.cube.standard.geometric.orientation.PlanarOrientation;
import io.github.yasenia.puzzle.cube.standard.geometric.orientation.SpatialOrientation;
import io.github.yasenia.puzzle.cube.standard.geometric.rotation.RotateDirection;
import io.github.yasenia.puzzle.cube.standard.rubiks.RubiksFace;
import io.github.yasenia.puzzle.cube.standard.rubiks.RubiksFacelet;

final class CodeManipulator {

    private static final int CODE_DOWN = 0B000;
    private static final int CODE_UP = 0B001;
    private static final int CODE_FRONT = 0B010;
    private static final int CODE_BACK = 0B011;
    private static final int CODE_RIGHT = 0B100;
    private static final int CODE_LEFT = 0B101;

    private static final int FACE_OFFSET_LEFT_TOP = 0;
    private static final int FACE_OFFSET_TOP = 3;
    private static final int FACE_OFFSET_RIGHT_TOP = 6;
    private static final int FACE_OFFSET_LEFT = 9;
    private static final int FACE_OFFSET_CENTER = 12;
    private static final int FACE_OFFSET_RIGHT = 15;
    private static final int FACE_OFFSET_LEFT_BOTTOM = 18;
    private static final int FACE_OFFSET_BOTTOM = 21;
    private static final int FACE_OFFSET_RIGHT_BOTTOM = 24;

    private static final int SIDE_OFFSET_FIRST = 0;
    private static final int SIDE_OFFSET_SECOND = 3;
    private static final int SIDE_OFFSET_THIRD = 6;

    private CodeManipulator() {
    }

    public static RubiksFace faceOf(int faceCode) {
        return new RubiksFace(
            faceletOf(faceletCodeAt(faceCode, FACE_OFFSET_LEFT_TOP)),
            faceletOf(faceletCodeAt(faceCode, FACE_OFFSET_TOP)),
            faceletOf(faceletCodeAt(faceCode, FACE_OFFSET_RIGHT_TOP)),
            faceletOf(faceletCodeAt(faceCode, FACE_OFFSET_LEFT)),
            faceletOf(faceletCodeAt(faceCode, FACE_OFFSET_CENTER)),
            faceletOf(faceletCodeAt(faceCode, FACE_OFFSET_RIGHT)),
            faceletOf(faceletCodeAt(faceCode, FACE_OFFSET_LEFT_BOTTOM)),
            faceletOf(faceletCodeAt(faceCode, FACE_OFFSET_BOTTOM)),
            faceletOf(faceletCodeAt(faceCode, FACE_OFFSET_RIGHT_BOTTOM))
        );
    }

    public static int pureFaceCodeOf(RubiksFacelet facelet) {
        var faceletCode = faceletCodeOf(facelet);
        return faceCodeOf(faceletCode, faceletCode, faceletCode, faceletCode, faceletCode, faceletCode, faceletCode, faceletCode, faceletCode);
    }

    public static int copySide(
        int fromFaceCode,
        PlanarOrientation fromSideOrientation,
        int toFaceCode,
        PlanarOrientation toSideOrientation
    ) {
        var sourceSide = sideCodeAt(fromFaceCode, fromSideOrientation);
        var firstCode = faceletCodeAt(sourceSide, SIDE_OFFSET_FIRST);
        var secondCode = faceletCodeAt(sourceSide, SIDE_OFFSET_SECOND);
        var thirdCode = faceletCodeAt(sourceSide, SIDE_OFFSET_THIRD);
        return switch (toSideOrientation) {
            case LEFT -> faceCodeOf(
                thirdCode, faceletCodeAt(toFaceCode, FACE_OFFSET_TOP), faceletCodeAt(toFaceCode, FACE_OFFSET_RIGHT_TOP),
                secondCode, faceletCodeAt(toFaceCode, FACE_OFFSET_CENTER), faceletCodeAt(toFaceCode, FACE_OFFSET_RIGHT),
                firstCode, faceletCodeAt(toFaceCode, FACE_OFFSET_BOTTOM), faceletCodeAt(toFaceCode, FACE_OFFSET_RIGHT_BOTTOM)
            );
            case RIGHT -> faceCodeOf(
                faceletCodeAt(toFaceCode, FACE_OFFSET_LEFT_TOP), faceletCodeAt(toFaceCode, FACE_OFFSET_TOP), firstCode,
                faceletCodeAt(toFaceCode, FACE_OFFSET_LEFT), faceletCodeAt(toFaceCode, FACE_OFFSET_CENTER), secondCode,
                faceletCodeAt(toFaceCode, FACE_OFFSET_LEFT_BOTTOM), faceletCodeAt(toFaceCode, FACE_OFFSET_BOTTOM), thirdCode
            );
            case TOP -> faceCodeOf(
                firstCode, secondCode, thirdCode,
                faceletCodeAt(toFaceCode, FACE_OFFSET_LEFT), faceletCodeAt(toFaceCode, FACE_OFFSET_CENTER), faceletCodeAt(toFaceCode, FACE_OFFSET_RIGHT),
                faceletCodeAt(toFaceCode, FACE_OFFSET_LEFT_BOTTOM), faceletCodeAt(toFaceCode, FACE_OFFSET_BOTTOM), faceletCodeAt(toFaceCode, FACE_OFFSET_RIGHT_BOTTOM)
            );
            case BOTTOM -> faceCodeOf(
                faceletCodeAt(toFaceCode, FACE_OFFSET_LEFT_TOP), faceletCodeAt(toFaceCode, FACE_OFFSET_TOP), faceletCodeAt(toFaceCode, FACE_OFFSET_RIGHT_TOP),
                faceletCodeAt(toFaceCode, FACE_OFFSET_LEFT), faceletCodeAt(toFaceCode, FACE_OFFSET_CENTER), faceletCodeAt(toFaceCode, FACE_OFFSET_RIGHT),
                thirdCode, secondCode, firstCode
            );
        };
    }

    public static int rotateFaceCode(int faceCode, RotateDirection rotateDirection) {
        return switch (rotateDirection) {
            case CLOCKWISE -> faceCodeOf(
                faceletCodeAt(faceCode, FACE_OFFSET_LEFT_BOTTOM), faceletCodeAt(faceCode, FACE_OFFSET_LEFT), faceletCodeAt(faceCode, FACE_OFFSET_LEFT_TOP),
                faceletCodeAt(faceCode, FACE_OFFSET_BOTTOM), faceletCodeAt(faceCode, FACE_OFFSET_CENTER), faceletCodeAt(faceCode, FACE_OFFSET_TOP),
                faceletCodeAt(faceCode, FACE_OFFSET_RIGHT_BOTTOM), faceletCodeAt(faceCode, FACE_OFFSET_RIGHT), faceletCodeAt(faceCode, FACE_OFFSET_RIGHT_TOP)
            );
            case COUNTER_CLOCKWISE -> faceCodeOf(
                faceletCodeAt(faceCode, FACE_OFFSET_RIGHT_TOP), faceletCodeAt(faceCode, FACE_OFFSET_RIGHT), faceletCodeAt(faceCode, FACE_OFFSET_RIGHT_BOTTOM),
                faceletCodeAt(faceCode, FACE_OFFSET_TOP), faceletCodeAt(faceCode, FACE_OFFSET_CENTER), faceletCodeAt(faceCode, FACE_OFFSET_BOTTOM),
                faceletCodeAt(faceCode, FACE_OFFSET_LEFT_TOP), faceletCodeAt(faceCode, FACE_OFFSET_LEFT), faceletCodeAt(faceCode, FACE_OFFSET_LEFT_BOTTOM)
            );
            case DOUBLE -> faceCodeOf(
                faceletCodeAt(faceCode, FACE_OFFSET_RIGHT_BOTTOM), faceletCodeAt(faceCode, FACE_OFFSET_BOTTOM), faceletCodeAt(faceCode, FACE_OFFSET_LEFT_BOTTOM),
                faceletCodeAt(faceCode, FACE_OFFSET_RIGHT), faceletCodeAt(faceCode, FACE_OFFSET_CENTER), faceletCodeAt(faceCode, FACE_OFFSET_LEFT),
                faceletCodeAt(faceCode, FACE_OFFSET_RIGHT_TOP), faceletCodeAt(faceCode, FACE_OFFSET_TOP), faceletCodeAt(faceCode, FACE_OFFSET_LEFT_TOP)
            );
        };
    }

    private static int faceletCodeAt(int combinedCode, int offset) {
        return (combinedCode >> offset) & 0B111;
    }

    private static int faceletCodeOf(RubiksFacelet facelet) {
        return switch (facelet.originalOrientation()) {
            case SpatialOrientation.DOWN -> CODE_DOWN;
            case SpatialOrientation.UP -> CODE_UP;
            case SpatialOrientation.FRONT -> CODE_FRONT;
            case SpatialOrientation.BACK -> CODE_BACK;
            case SpatialOrientation.RIGHT -> CODE_RIGHT;
            case SpatialOrientation.LEFT -> CODE_LEFT;
        };
    }

    private static int faceCodeOf(
        int leftTopCode, int topCode, int rightTopCode,
        int leftCode, int centerCode, int rightCode,
        int leftBottomCode, int bottomCode, int rightBottomCode
    ) {
        return (leftTopCode << FACE_OFFSET_LEFT_TOP) | (topCode << FACE_OFFSET_TOP) | (rightTopCode << FACE_OFFSET_RIGHT_TOP) | (leftCode << FACE_OFFSET_LEFT) | (centerCode << FACE_OFFSET_CENTER)
            | (rightCode << FACE_OFFSET_RIGHT) | (leftBottomCode << FACE_OFFSET_LEFT_BOTTOM) | (bottomCode << FACE_OFFSET_BOTTOM) | (rightBottomCode << FACE_OFFSET_RIGHT_BOTTOM);
    }

    private static int sideCodeOf(int firstCode, int secondCode, int thirdCode) {
        return (firstCode << SIDE_OFFSET_FIRST) | (secondCode << SIDE_OFFSET_SECOND) | (thirdCode << SIDE_OFFSET_THIRD);
    }

    private static int sideCodeAt(int faceCode, PlanarOrientation sideOrientation) {
        return switch (sideOrientation) {
            case LEFT -> sideCodeOf(
                faceletCodeAt(faceCode, FACE_OFFSET_LEFT_BOTTOM),
                faceletCodeAt(faceCode, FACE_OFFSET_LEFT),
                faceletCodeAt(faceCode, FACE_OFFSET_LEFT_TOP)
            );
            case RIGHT -> sideCodeOf(
                faceletCodeAt(faceCode, FACE_OFFSET_RIGHT_TOP),
                faceletCodeAt(faceCode, FACE_OFFSET_RIGHT),
                faceletCodeAt(faceCode, FACE_OFFSET_RIGHT_BOTTOM)
            );
            case TOP -> sideCodeOf(
                faceletCodeAt(faceCode, FACE_OFFSET_LEFT_TOP),
                faceletCodeAt(faceCode, FACE_OFFSET_TOP),
                faceletCodeAt(faceCode, FACE_OFFSET_RIGHT_TOP)
            );
            case BOTTOM -> sideCodeOf(
                faceletCodeAt(faceCode, FACE_OFFSET_RIGHT_BOTTOM),
                faceletCodeAt(faceCode, FACE_OFFSET_BOTTOM),
                faceletCodeAt(faceCode, FACE_OFFSET_LEFT_BOTTOM)
            );
        };
    }

    private static RubiksFacelet faceletOf(int code) {
        return switch (code) {
            case CODE_DOWN -> RubiksFacelet.RUBIKS_FACELETS.down();
            case CODE_UP -> RubiksFacelet.RUBIKS_FACELETS.up();
            case CODE_FRONT -> RubiksFacelet.RUBIKS_FACELETS.front();
            case CODE_BACK -> RubiksFacelet.RUBIKS_FACELETS.back();
            case CODE_RIGHT -> RubiksFacelet.RUBIKS_FACELETS.right();
            case CODE_LEFT -> RubiksFacelet.RUBIKS_FACELETS.left();
            default -> throw new IllegalArgumentException("Invalid color code: " + code);
        };
    }
}
