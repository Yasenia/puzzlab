package io.github.yasenia.puzzle.cube.standard;

import io.github.yasenia.puzzle.TwistyPuzzle;
import io.github.yasenia.puzzle.cube.standard.geometric.accessor.SpatialAccessor;

/**
 * Represents a Cube in the context of a twisty puzzle. It provides access to its faces and
 * defines its behavior and interactions in terms of rotations.
 *
 * @param <C>  the type of the cube
 * @param <R>  the type of the rotation applicable to the cube
 * @param <FL> the type of the facelet of the cube
 * @param <F>  the type of the face of the cube
 */
public interface Cube<
    C extends Cube<C, R, FL, F>,
    R extends CubeRotation<C, R>,
    FL extends Facelet,
    F extends Face<FL>
    > extends TwistyPuzzle<C, R> {

    /**
     * Provides access to the faces of the cube. Each face is represented by a {@link Face}
     * instance and contains information about its structure and properties.
     *
     * @return the faces of the cube as a SpatialAccessible
     */
    SpatialAccessor<F> faces();

    /**
     * Represents a specialized Cube where the order is odd. It provides additional
     * functionalities specific to cubes of odd order, such as access to its center facelets.
     *
     * @param <C>  the type of the cube
     * @param <R>  the type of the rotation applicable to the cube
     * @param <FL> the type of the facelet of the cube
     * @param <F>  the type of the face of the cube
     */
    interface Odd<
        C extends Odd<C, R, FL, F>,
        R extends CubeRotation<C, R>,
        FL extends Facelet,
        F extends Face.Odd<FL>
        > extends Cube<C, R, FL, F> {

        /**
         * Represents each small facet or piece on a face of the cube. A Facelet is the smallest
         * part of a cube's face and serves as the individual colored tile that is visible on the
         * surface of the cube.
         */
        default SpatialAccessor<FL> centerFacelets() {
            return this.faces().map(Face.Odd::centerFacelet);
        }
    }
}
