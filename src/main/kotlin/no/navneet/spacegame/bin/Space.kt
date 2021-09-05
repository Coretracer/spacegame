package no.navneet.spacegame.bin

import no.navneet.spacegame.util.Coordinate
import no.navneet.spacegame.util.Size

class Space(val pixelRows: List<String>) {

    fun extractFrame(coordinate: Coordinate, size: Size): Frame {
        return if (sizeAtThisCoordinateIsWithinSpace(coordinate, size)) {
            getFrame(coordinate, size)
        } else {
            Frame.emptyFrame()
        }
    }

    private fun getFrame(
        coordinate: Coordinate,
        size: Size
    ):Frame {
        val rowsOfFrame =  pixelRows.subList(coordinate.y, coordinate.y + size.height)
            .map { it.substring(coordinate.x, coordinate.x + size.width) }
        return Frame(rowsOfFrame)
    }


    private fun sizeAtThisCoordinateIsWithinSpace(
        coordinate: Coordinate,
        size: Size
    ) = (coordinate.y + size.height <= pixelRows.size
                && coordinate.x + size.width <= pixelRows[coordinate.y].length)
}