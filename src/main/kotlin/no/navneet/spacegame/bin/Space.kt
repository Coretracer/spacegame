package no.navneet.spacegame.bin

import no.navneet.spacegame.util.Coordinate
import no.navneet.spacegame.util.Size

class Space(val pixelRows: List<String>) {

    fun extractFrame(coordinate: Coordinate, size: Size): List<String>? {
        return if (sizeAtThisCoordinateIsWithinSpace(coordinate, size)) {
            getFrame(coordinate, size)
        } else {
            null
        }
    }

    private fun getFrame(
        coordinate: Coordinate,
        size: Size
    ) = pixelRows.subList(coordinate.y, coordinate.y + size.height)
        .map { it.substring(coordinate.x, coordinate.x + size.width) }

    private fun sizeAtThisCoordinateIsWithinSpace(
        coordinate: Coordinate,
        size: Size
    ) = (coordinate.y + size.height <= pixelRows.size
                && coordinate.x + size.width <= pixelRows[coordinate.y].length)
}