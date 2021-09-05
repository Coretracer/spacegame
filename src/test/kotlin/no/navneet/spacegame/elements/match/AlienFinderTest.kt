package no.navneet.spacegame.elements.match

import no.navneet.spacegame.elements.AlienFixture.sampleAlien1
import no.navneet.spacegame.elements.AlienFixture.sampleAlien2
import no.navneet.spacegame.elements.CoordinateFixture.coordinate39x33
import no.navneet.spacegame.elements.CoordinateFixture.coordinate70x25
import no.navneet.spacegame.elements.SpaceFixture.spaceWithAlien
import no.navneet.spacegame.elements.SpaceFixture.spaceWithoutAlien
import no.navneet.spacegame.match.AlienFinder
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AlienFinderTest {


    @Test
    fun findAlienTestWithNonEmptyResult() {
        assertThat(AlienFinder.findAlien(spaceWithAlien, sampleAlien1)).isNotEmpty
    }

    @Test
    fun findAlienTestWithEmptyResult() {
        assertThat(AlienFinder.findAlien(spaceWithoutAlien, sampleAlien1)).isEmpty()
    }

    @Test
    fun findAlienTestWithTwoResults() {
        val alienCoordinates = AlienFinder.findAlien(spaceWithAlien, sampleAlien2)
        assertThat(alienCoordinates.size).isEqualTo(2)
        assertThat(alienCoordinates[0]).isEqualTo(coordinate70x25)
        assertThat(alienCoordinates[1]).isEqualTo(coordinate39x33)
    }
    //TODO: tons of other edge case tests

}