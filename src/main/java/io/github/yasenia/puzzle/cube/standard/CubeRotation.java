package io.github.yasenia.puzzle.cube.standard;

import io.github.yasenia.puzzle.Rotation;

/**
 * Represents a Rotation that can be applied to a Cube, defining the way a cube can be manipulated.
 *
 * @param <C> the type of the cube to which this rotation can be applied
 * @param <R> the type of the rotation
 */
public interface CubeRotation<
    C extends Cube<C, R, ?, ?>,
    R extends CubeRotation<C, R>
    > extends Rotation<C, R> {
}
