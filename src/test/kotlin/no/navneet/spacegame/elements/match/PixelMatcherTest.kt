package no.navneet.spacegame.elements.match


import no.navneet.spacegame.elements.AlienFixture.alien1x1
import no.navneet.spacegame.elements.AlienFixture.alienTwoDimension
import no.navneet.spacegame.elements.AlienFixture.alienWithOneRow
import no.navneet.spacegame.elements.AlienFixture.emptyAlien
import no.navneet.spacegame.elements.FrameFixture.emptyFrame
import no.navneet.spacegame.elements.FrameFixture.frame1x1
import no.navneet.spacegame.elements.FrameFixture.frameTwoDimension
import no.navneet.spacegame.elements.FrameFixture.frameWithOneRow
import no.navneet.spacegame.match.PixelMatcher.matchAlienInTheFrame
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PixelMatcherTest {
    @Test
    fun testMatchAlienWithFrame(){
         assertThat(matchAlienInTheFrame(emptyFrame,emptyAlien)).isTrue
         assertThat(matchAlienInTheFrame(frame1x1,alien1x1)).isTrue
         assertThat(matchAlienInTheFrame(frameWithOneRow,alienWithOneRow)).isTrue
         assertThat(matchAlienInTheFrame(frameTwoDimension,alienTwoDimension)).isTrue
    }

    @Test
    fun testDoesNotMatchAlienWithFrame(){
        assertThat(matchAlienInTheFrame(emptyFrame,alien1x1)).isFalse
        assertThat(matchAlienInTheFrame(frameWithOneRow,alienTwoDimension)).isFalse
    }

}