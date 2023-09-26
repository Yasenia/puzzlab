package io.github.yasenia.puzzle.cube.standard.rubiks;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class RubiksRotationTest {

    @ParameterizedTest
    @EnumSource(RubiksRotation.class)
    void should_inverse_direction_and_keep_orientation_when_inverse_rotation(RubiksRotation rotation) {
        // exercise
        var inverseRotation = rotation.inverse();
        // verify
        assertThat(inverseRotation.orientation(), is(rotation.orientation()));
        assertThat(inverseRotation.direction(), is(rotation.direction().inverse()));
    }
}