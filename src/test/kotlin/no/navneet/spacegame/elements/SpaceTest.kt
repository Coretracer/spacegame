package no.navneet.spacegame.elements

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SpaceTest {

    @Test
    fun extractEmptyFrameTest() {
        val frame = SpaceFixture.space1x1.extractFrame(CoordinateFixture.coordinate1x1, SizeFixture.size1x1)
        assertThat(frame.isEmpty()).isTrue

    }
    @Test
    fun extractNonEmptyFrameTest() {
        val frame = SpaceFixture.spaceWithEmptyAlien.extractFrame(CoordinateFixture.coordinate1x1, SizeFixture.size1x1)
        assertThat(frame.isEmpty()).isFalse()
    }
    //TODO: test other edge cases

}