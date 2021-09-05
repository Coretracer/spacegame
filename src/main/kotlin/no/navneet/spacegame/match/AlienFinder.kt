package no.navneet.spacegame.match

import no.navneet.spacegame.bin.Alien
import no.navneet.spacegame.bin.Space
import no.navneet.spacegame.util.Coordinate

object AlienFinder {



    fun findAlien(space: Space, alien: Alien): List<Coordinate> {

        val result = space
            .pixelRows
            .mapIndexed { yAxis, line ->
                findCoordinateThatMatchesAlienHeadInRow(line, yAxis, space, alien)
            }
        return result.flatten()
    }

    private fun findCoordinateThatMatchesAlienHeadInRow(
        line: String,
        yAxis: Int,
        space: Space,
        alien: Alien
    ): List<Coordinate> {
        val pixelsOfMatchingHead = alien
            .getHead()
            ?.let { findAlienHead(it, line) }

        return pixelsOfMatchingHead
            ?.map { Coordinate(it.first, yAxis) }
            ?.filter { findAlienInThisSpaceCoordinate(it, space, alien) }
            ?: emptyList()
    }


    private fun findAlienInThisSpaceCoordinate(
        coordinatePOI: Coordinate,
        space: Space,
        alien: Alien
    ): Boolean {
        val alienSize = alien.getSize()
        val frame = space.extractFrame(coordinatePOI, alienSize)
        return frame?.let {
            matchAlienInFrame(it, alien)
        } ?: false
    }


    fun matchAlienInFrame(frame: List<String>, alien: Alien): Boolean {
        if (!matchesSize(frame, alien)) return false

        alien.pixelRows.forEachIndexed { index, line ->
            if (frame[index] != line) {
                return false
            }
        }
        return true
    }

    private fun matchesSize(
        frame: List<String>,
        alien: Alien
    ): Boolean {
        return frame.size == alien.pixelRows.size
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