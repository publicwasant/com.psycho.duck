package com.psycho.utility.financial

import android.util.Log
import android.util.Log.WARN
import com.psycho.utility.Constants.FINANCIAL_UTILS_TAG
import com.psycho.utility.print.Cell
import java.lang.NumberFormatException
import java.text.DecimalFormat

class FinancialUtils {
    companion object {
        const val AMOUNT_NUM_MAX = 12
        const val AMOUNT_INT_MAX = 10
        const val AMOUNT_DEC_MAX = 2
        const val AMOUNT_INT_PATTERN = "#,###,###,###"
        const val AMOUNT_STANDARD_SOURCE = 0

        fun getAmount(source: Any? = null, fromSource: Int? = null): Amount {
            try {
                val tempSource: String = when (fromSource) {
                    AMOUNT_STANDARD_SOURCE -> {
                        if (source.toString().any { char -> !char.isDigit() })
                            throw NumberFormatException(String.format("Can't convert %s to %s " +
                                    "with AMOUNT_STANDARD_SOURCE %s " +
                                    "because the source given must contain a number only " +
                                    "but it continues, returning 0.",
                                Cell(source, wrap= Cell.WRAP_STRING),
                                Cell("Amount", wrap= Cell.WRAP_CLASS),
                                Cell(AMOUNT_STANDARD_SOURCE, wrap= Cell.WRAP_CONST)
                            ))

                        if (source.toString().length != AMOUNT_NUM_MAX)
                            throw NumberFormatException(String.format("Can't convert %s to %s " +
                                    "with AMOUNT_STANDARD_SOURCE %s " +
                                    "because it's size is not equal AMOUNT_NUM_MAX %s " +
                                    "but it continues, returning 0. ",
                                Cell(source, wrap= Cell.WRAP_STRING),
                                Cell("Amount", wrap= Cell.WRAP_CLASS),
                                Cell(AMOUNT_STANDARD_SOURCE, wrap= Cell.WRAP_CONST),
                                Cell(AMOUNT_NUM_MAX, wrap= Cell.WRAP_CONST)
                            ))

                        String.format("%s.%s",
                            source.toString().substring(0, AMOUNT_INT_MAX),
                            source.toString().substring(AMOUNT_INT_MAX, AMOUNT_NUM_MAX))
                    }
                    else -> when (source) {
                        is Int,
                        is Long -> String.format("%s.00", source.toString().toLong())
                        is Float,
                        is Double -> source.toString()
                        else -> source.toString()
                    }
                }

                var negative = ""
                var beginInt: String = tempSource
                    .substringBefore(".")
                    .filter { char ->
                        char.isDigit() || char == '-'
                    }.also { src ->
                        if (src.isNotEmpty())
                            negative = if (src[0] == '-') "-" else ""
                    }.filter { char ->
                        char.isDigit()
                    }

                if (beginInt.length > AMOUNT_INT_MAX)
                    throw NumberFormatException(String.format("Can't convert %s to %s " +
                            "because size is more than AMOUNT_INT_MAX %s " +
                            "but it continues, returning 0.",
                        Cell(tempSource, wrap= Cell.WRAP_STRING),
                        Cell("Amount", wrap= Cell.WRAP_CLASS),
                        Cell(AMOUNT_INT_MAX, wrap= Cell.WRAP_CONST)
                    ))

                beginInt = beginInt.padStart(AMOUNT_INT_MAX, '0')

                var beginDec = ""

                if (tempSource.any { char -> char == '.'})
                    beginDec = tempSource
                        .substringAfter(".")
                        .filter { char ->
                            char.isDigit()
                        }

                if (beginDec.length > AMOUNT_DEC_MAX)
                    beginDec = beginDec.substring(0, AMOUNT_DEC_MAX)

                beginDec = beginDec.padEnd(AMOUNT_DEC_MAX, '0')

                val integer: Long = if (negative.isEmpty()) beginInt.toLong() else -beginInt.toLong()

                val standard = String.format("%s%s", beginInt, beginDec)
                val decorated = String.format("%s.%s", DecimalFormat(AMOUNT_INT_PATTERN).format(integer.toDouble()), beginDec)
                val decimal = String.format("%d.%s", integer, beginDec).toDouble()

                return Amount(standard, decorated, decimal)
            } catch (e: NumberFormatException) {
                Log.println(WARN, FINANCIAL_UTILS_TAG, e.stackTraceToString())
            }

            return Amount("000000000000", "0.00", 0.0)
        }
    }
}