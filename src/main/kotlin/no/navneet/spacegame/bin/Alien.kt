package no.navneet.spacegame.bin

import no.navneet.spacegame.util.Size

class Alien(val pixelRows: List<String>) {
    fun getHead(): String? {
        return if (pixelRows.isNotEmpty()) pixelRows[0] else null
    }

    fun getSize(): Size {
        return Size(pixelRows[0].length, pixelRows.size)
    }
}