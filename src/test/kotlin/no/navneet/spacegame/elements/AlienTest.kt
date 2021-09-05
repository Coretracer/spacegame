package no.navneet.spacegame.elements

import no.navneet.spacegame.elements.AlienFixture.alien1x1
import no.navneet.spacegame.elements.AlienFixture.alienTwoDimension
import no.navneet.spacegame.elements.AlienFixture.alienWithOneRow
import no.navneet.spacegame.elements.AlienFixture.emptyAlien
import no.navneet.spacegame.elements.PixelRowsFixture.arbitraryTwoDimensionPixel
import no.navneet.spacegame.elements.PixelRowsFixture.emptyPixel
import no.navneet.spacegame.elements.PixelRowsFixture.rowPixel
import no.navneet.spacegame.elements.PixelRowsFixture.singlePixel
import no.navneet.spacegame.elements.SizeFixture.size0x0
import no.navneet.spacegame.elements.SizeFixture.size1x1
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AlienTest {
    @Test
    fun testGetHead() {
        assertThat(emptyAlien.getHead()).isEqualTo(emptyPixel)
        assertThat(alien1x1.getHead()).isEqualTo(singlePixel)
        assertThat(alienWithOneRow.getHead()).isEqualTo(rowPixel)
        assertThat(alienTwoDimension.getHead()).isEqualTo(arbitraryTwoDimensionPixel[0])
    }

    @Test
    fun testGetSize() {
        assertThat(emptyAlien.getSize()).isEqualTo(size0x0)
        assertThat(alien1x1.getSize()).isEqualTo(size1x1)
        val sizeRow = alienWithOneRow.getSize()
        assertThat(sizeRow.height).isEqualTo(1)
        assertThat(sizeRow.width).isGreaterThan(1)

        val twoDimSize = alienTwoDimension.getSize()
        assertThat(twoDimSize.height).isGreaterThan(1)
        assertThat(twoDimSize.width).isGreaterThan(1)
    }
}