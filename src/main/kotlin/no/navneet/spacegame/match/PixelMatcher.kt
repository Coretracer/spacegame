package no.navneet.spacegame.match

import no.navneet.spacegame.elements.Alien
import no.navneet.spacegame.elements.Frame

object PixelMatcher {

    fun matchAlienInTheFrame(frame: Frame, alien: Alien): Boolean {
        if (!matchesSize(frame, alien)) return false

        alien.pixelRows.forEachIndexed { index, line ->
            if (frame.pixelRows[index] != line) {
                return false
            }
        }
        return true
    }

    private fun matchesSize(
        frame: Frame,
        alien: Alien
    ): Boolean {
        return frame.pixelRows.size == alien.pixelRows.size
    }
}