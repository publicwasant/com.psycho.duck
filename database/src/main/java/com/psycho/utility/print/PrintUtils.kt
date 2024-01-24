package com.psycho.utility.print

class Cell(
    val text: Any?,
    val n: Int? = null,
    val wrap: String = WRAP_NONE,
    val align: Int = ALIGN_START,
    val space: Char = SPACE_BLANK
) {

    companion object {
        const val WRAP_NONE = "%s"
        const val WRAP_SPACE = " %s "
        const val WRAP_CONST = "(%s)"
        const val WRAP_STRING = "\"%s\""
        const val WRAP_TYPE = "<%s>"
        const val WRAP_CLASS = "%s::class"

        const val SPACE_BLANK = ' '
        const val SPACE_DARK = '#'
        const val SPACE_POINT = '.'

        const val ALIGN_START = 0
        const val ALIGN_CENTER = 1
        const val ALIGN_END = 2
    }

    override fun toString(): String {
        var textRaw = String.format(wrap, text.toString())
        val nRaw = this.n?: textRaw.length
        if (textRaw.length > nRaw)
            textRaw = textRaw.substring(0, nRaw)
        return when (align) {
            ALIGN_CENTER -> {
                val nBegin = nRaw/2 + textRaw.length/2
                textRaw.padStart(nBegin, space).padEnd(nRaw, space)
            }
            ALIGN_END -> textRaw.padStart(nRaw, space)
            ALIGN_START -> textRaw.padEnd(nRaw, space)
            else -> textRaw.padEnd(nRaw, space)
        }
    }
}

class PrintUtils {
    companion object {

    }
}