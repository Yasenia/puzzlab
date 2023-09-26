package io.github.yasenia.puzzle.cube.standard;

/**
 * Represents a Face of a Cube, containing information about its structure and properties based on the order of the cube.
 *
 * @param <FL> the type of the facelet of the face
 */
public interface Face<FL extends Facelet> {

    FL[] facelets();

    /**
     * Represents a specialized Face of a Cube where the order is odd.
     * This interface extends the Face interface and is designed to represent faces of cubes that have a distinct center facelet due to their odd order.
     *
     * @param <FL> the type of the facelet of the face
     */
    interface Odd<FL extends Facelet> extends Face<FL> {

        /**
         * Provides access to the distinct center facelet of the face. This facelet is unique to faces of cubes of odd order.
         *
         * @return the center facelet of the face
         */
        FL centerFacelet();
    }
}
