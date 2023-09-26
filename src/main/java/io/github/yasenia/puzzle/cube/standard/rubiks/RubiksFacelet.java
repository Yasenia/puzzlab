package io.github.yasenia.puzzle.cube.standard.rubiks;

import io.github.yasenia.puzzle.cube.standard.Facelet;
import io.github.yasenia.puzzle.cube.standard.geometric.accessor.SpatialAccessor;
import io.github.yasenia.puzzle.cube.standard.geometric.orientation.SpatialOrientation;

public record RubiksFacelet(SpatialOrientation originalOrientation) implements Facelet {

    public static final SpatialAccessor<RubiksFacelet> RUBIKS_FACELETS = SpatialAccessor.SPATIAL_ORIENTATIONS.map(RubiksFacelet::new);
}
