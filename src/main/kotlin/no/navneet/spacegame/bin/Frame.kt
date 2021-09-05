package no.navneet.spacegame.bin

class Frame(val pixelRows: List<String>) {

    fun isEmpty():Boolean{
        return pixelRows.isEmpty()
    }

    companion object {
        fun emptyFrame(): Frame {
            return Frame(emptyList())
        }
    }
}