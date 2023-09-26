package io.github.yasenia.puzzle.cube.standard.rubiks.impl;

import io.github.yasenia.puzzle.cube.standard.geometric.accessor.PlanarAccessor;
import io.github.yasenia.puzzle.cube.standard.geometric.accessor.SpatialAccessor;
import io.github.yasenia.puzzle.cube.standard.geometric.orientation.SpatialOrientation;
import io.github.yasenia.puzzle.cube.standard.rubiks.RubiksCube;
import io.github.yasenia.puzzle.cube.standard.rubiks.RubiksFace;
import io.github.yasenia.puzzle.cube.standard.rubiks.RubiksFacelet;
import io.github.yasenia.puzzle.cube.standard.rubiks.RubiksRotation;

import java.util.Map;

public class RubiksCubeImpl implements RubiksCube {

    private final SpatialAccessor<Integer> codes;
    private final SpatialAccessor<RubiksFace> faces;

    public RubiksCubeImpl() {
        this(RubiksFacelet.RUBIKS_FACELETS.map(CodeManipulator::pureFaceCodeOf));
    }

    private RubiksCubeImpl(Map<SpatialOrientation, Integer> faceCodesMap) {
        this(SpatialAccessor.SPATIAL_ORIENTATIONS.map(faceCodesMap::get));
    }

    private RubiksCubeImpl(SpatialAccessor<Integer> codes) {
        this.codes = codes;
        this.faces = this.codes.map(CodeManipulator::faceOf);
    }

    @Override
    public RubiksCube rotate(RubiksRotation rotation) {
        var orientation = rotation.orientation();
        var direction = rotation.direction();

        // Rotate the center face of the cube in the specified direction and store the updated face code.
        var updatedCenterFaceCode = CodeManipulator.rotateFaceCode(this.codes.at(orientation), direction);

        // Project the spatial orientation to its adjacent orientations (e.g., if the orientation is FRONT,
        // the adjacent orientations are LEFT, RIGHT, TOP, and BOTTOM).
        var adjacentOrientations = orientation.project();

        // Map the adjacent orientations to their corresponding face codes in the current baseState of the cube.
        var adjacentFaceCodes = adjacentOrientations.map(this.codes::at);

        // For each adjacent orientation, calculate the updated face codes after the rotation.
        var updatedAdjacentFaceCodes = PlanarAccessor.PLANAR_ORIENTATIONS.map(adjacentOrientation -> {
            // Calculate the orientation before the rotation based on the direction of the rotation.
            var previousOrientation = adjacentOrientation.rotateBackward(direction);

            // Copy the side from the previous orientation to the adjacent orientation and return the updated face code.
            return CodeManipulator.copySide(
                adjacentFaceCodes.at(previousOrientation),
                orientation.determineAdjacentPlanarOrientation(previousOrientation),
                adjacentFaceCodes.at(adjacentOrientation),
                orientation.determineAdjacentPlanarOrientation(adjacentOrientation)
            );
        });

        // Construct a new RubiksCubeImpl object with the updated face codes for the center and adjacent faces,
        // and return it. The opposite face remains unchanged.
        return new RubiksCubeImpl(Map.of(
            orientation, updatedCenterFaceCode,
            adjacentOrientations.left(), updatedAdjacentFaceCodes.left(),
            adjacentOrientations.right(), updatedAdjacentFaceCodes.right(),
            adjacentOrientations.top(), updatedAdjacentFaceCodes.top(),
            adjacentOrientations.bottom(), updatedAdjacentFaceCodes.bottom(),
            orientation.opposite(), this.codes.at(orientation.opposite())
        ));
    }

    @Override
    public SpatialAccessor<RubiksFace> faces() {
        return this.faces;
    }
}
