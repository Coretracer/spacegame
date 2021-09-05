package no.navneet.spacegame.match

import no.navneet.spacegame.bin.Alien
import no.navneet.spacegame.bin.Space
import no.navneet.spacegame.util.Coordinate

object AlienFinder {

    fun findAlien(space: Space, alien: Alien): List<Coordinate> {
        return space
            .pixelRows
            .mapIndexed { yAxis, line ->
                findCoordinateThatMatchesAlienHeadInThisYAxis(line, yAxis, space, alien)
            }.flatten()
    }

    private fun findCoordinateThatMatchesAlienHeadInThisYAxis(
        line: String,
        yAxis: Int,
        space: Space,
        alien: Alien
    ): List<Coordinate> {
        val partialMatchingCoordinates = alien
            .getHead()
            ?.let { findAlienHead(it, line) }
            ?.map { Coordinate(it.first, yAxis) }

        return partialMatchingCoordinates
            ?.filter { findAlienInThisSpaceCoordinate(it, space, alien) }
            ?: emptyList()
    }


    private fun findAlienInThisSpaceCoordinate(
        coordinatePOI: Coordinate,
        space: Space,
        alien: Alien
    ): Boolean {
        val frame = space.extractFrame(coordinatePOI, alien.getSize())

        return if (!frame.isEmpty()) {
            PixelMatcher.matchAlienInTheFrame(frame, alien)
        } else {
            false
        }

    }

    private fun findAlienHead(
        firstAlienLine: String,
        spaceFirstLine: String
    ): List<IntRange> {
        return firstAlienLine
            .toRegex()
            .findAll(spaceFirstLine)
            .filter { !it.range.isEmpty() }
            .map { it.range }
            .toList()
    }

}