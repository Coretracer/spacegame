package no.navneet.spacegame.elements

class Alien(val pixelRows: List<String>) {
    fun getHead(): String? {
        return if (pixelRows.isNotEmpty()) pixelRows[0] else null
    }

    fun getSize(): Size {
        return Size(pixelRows[0].length, getWidth())
    }

    private fun getWidth(): Int {
        return if (pixelRows[0].isEmpty()) 0 else pixelRows.size
    }
}