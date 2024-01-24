package com.psycho.database.utility.financial

import com.psycho.utility.print.Cell
import com.psycho.utility.financial.FinancialUtils.Companion.AMOUNT_STANDARD_SOURCE
import com.psycho.utility.financial.Amount
import com.psycho.utility.financial.FinancialUtils
import org.junit.Test

class AmountUnitTest {
    private data class Case(
        val source: Any?,
        val standard: String,
        val decorated: String,
        val decimal: Double)

    private fun assertCaseWithAmount(title: String, case: Case, result: Amount) {
        println(Cell(title, 63, wrap= Cell.WRAP_SPACE, align= Cell.ALIGN_CENTER, space= Cell.SPACE_DARK))
        println(String.format("%s %s %s %s %s %s",
            Cell("#", 1, align= Cell.ALIGN_CENTER, space='#'),
            Cell("TYPE", 10),
            Cell("ATTRIBUTE", 10),
            Cell("EXPECTED", 18, align= Cell.ALIGN_CENTER),
            Cell("RESULT", 18, align= Cell.ALIGN_CENTER),
            Cell("#", 1, align= Cell.ALIGN_CENTER, space='#')
        ))
        println(String.format("%s %s %s %s %s %s",
            Cell("#", 1, space='#'),
            Cell("Any?", 10, Cell.WRAP_TYPE),
            Cell("source", 10),
            Cell(case.source, 18, Cell.WRAP_STRING, align= Cell.ALIGN_CENTER,),
            Cell("NO", 18, align= Cell.ALIGN_CENTER,),
            Cell("#", 1, align= Cell.ALIGN_CENTER, space='#')
        ))
        println(String.format("%s %s %s %s %s %s",
            Cell("#", 1, space='#'),
            Cell("String", 10, Cell.WRAP_TYPE),
            Cell("standard", 10),
            Cell(case.standard, 18, Cell.WRAP_STRING, align= Cell.ALIGN_CENTER),
            Cell(result.standard, 18, Cell.WRAP_STRING, align= Cell.ALIGN_CENTER),
            Cell("#", 1, align= Cell.ALIGN_CENTER, space='#')
        ))
        println(String.format("%s %s %s %s %s %s",
            Cell("#", 1, space='#'),
            Cell("String", 10, Cell.WRAP_TYPE),
            Cell("decorated", 10),
            Cell(case.decorated, 18, Cell.WRAP_STRING, align= Cell.ALIGN_CENTER),
            Cell(result.decorated, 18, Cell.WRAP_STRING, align= Cell.ALIGN_CENTER),
            Cell("#", 1, align= Cell.ALIGN_CENTER, space='#')
        ))
        println(String.format("%s %s %s %s %s %s",
            Cell("#", 1, space='#'),
            Cell("Double", 10, Cell.WRAP_TYPE),
            Cell("decimal", 10),
            Cell(case.decimal, 18, align= Cell.ALIGN_CENTER),
            Cell(result.decimal, 18, align= Cell.ALIGN_CENTER),
            Cell("#", 1, align= Cell.ALIGN_CENTER, space='#')
        ))
        println(Cell("#", 63, align= Cell.ALIGN_CENTER, space= Cell.SPACE_DARK))
        println()
        assert(case.standard == result.standard)
        assert(case.decimal == result.decimal)
        assert(case.decorated == result.decorated)
    }

    @Test
    fun testConvertNullToAmount() {
        val case = Case(null, "000000000000", "0.00", 0.0)
        val amount = FinancialUtils.getAmount(case.source)
        assertCaseWithAmount("TEST CONVERT NULL TO AMOUNT", case, amount)
    }

    @Test
    fun testConvertEmptyToAmount() {
        val case = Case("", "000000000000", "0.00", 0.0)
        val amount = FinancialUtils.getAmount(case.source)
        assertCaseWithAmount("TEST CONVERT EMPTY TO AMOUNT", case, amount)
    }

    @Test
    fun testConvertBlankToAmount() {
        val case = Case("   ", "000000000000", "0.00", 0.0)
        val amount = FinancialUtils.getAmount(case.source)
        assertCaseWithAmount("TEST CONVERT BLANK TO AMOUNT", case, amount)
    }

    @Test
    fun testConvertStringToAmount1() {
        val case = Case("  4,599 .999 ฿ ", "000000459999", "4,599.99", 4599.99)
        val amount = FinancialUtils.getAmount(case.source)
        assertCaseWithAmount("TEST CONVERT STRING TO AMOUNT", case, amount)
    }

    @Test
    fun testConvertStringToAmount2() {
        val case = Case("-0.999฿", "000000000099", "0.99", 0.99)
        val amount = FinancialUtils.getAmount(case.source)
        assertCaseWithAmount("TEST CONVERT STRING TO AMOUNT", case, amount)
    }

    @Test
    fun testConvertStringToAmount3() {
        val case = Case("-999.0฿", "000000099900", "-999.00", -999.0)
        val amount = FinancialUtils.getAmount(case.source)
        assertCaseWithAmount("TEST CONVERT STRING TO AMOUNT", case, amount)
    }

    @Test
    fun testConvertStandardToAmount1() {
        val case = Case("000000459999", "000000459999", "4,599.99", 4599.99)
        val amount = FinancialUtils.getAmount(case.source, fromSource=AMOUNT_STANDARD_SOURCE)
        assertCaseWithAmount("TEST CONVERT STANDARD TO AMOUNT", case, amount)
    }

    @Test
    fun testConvertStandardToAmount2() {
        val case = Case("459999000000", "459999000000", "4,599,990,000.00", 4599990000.0)
        val amount = FinancialUtils.getAmount(case.source, fromSource=AMOUNT_STANDARD_SOURCE)
        assertCaseWithAmount("TEST CONVERT STANDARD TO AMOUNT", case, amount)
    }

    @Test
    fun testConvertStandardToAmount3() {
        val case = Case("7654", "000000000000", "0.00", 0.0)
        val amount = FinancialUtils.getAmount(case.source, fromSource=AMOUNT_STANDARD_SOURCE)
        assertCaseWithAmount("TEST CONVERT STANDARD TO AMOUNT", case, amount)
    }

    @Test
    fun testConvertStandardToAmount4() {
        val case = Case("000012007654", "000012007654", "120,076.54", 120076.54)
        val amount = FinancialUtils.getAmount(case.source, fromSource=AMOUNT_STANDARD_SOURCE)
        assertCaseWithAmount("TEST CONVERT STANDARD TO AMOUNT", case, amount)
    }

    @Test
    fun testConvertNumberToAmount1() {
        val case = Case(-999.999, "000000099999", "-999.99", -999.99)
        val amount = FinancialUtils.getAmount(case.source)
        assertCaseWithAmount("TEST CONVERT NUMBER TO AMOUNT", case, amount)
    }

    @Test
    fun testConvertNumberToAmount2() {
        val case = Case(-999, "000000099900", "-999.00", -999.0)
        val amount = FinancialUtils.getAmount(case.source)
        assertCaseWithAmount("TEST CONVERT NUMBER TO AMOUNT", case, amount)
    }

    @Test
    fun testConvertNumberToAmount3() {
        val case = Case(99999999L, "009999999900", "99,999,999.00", 99999999.0)
        val amount = FinancialUtils.getAmount(case.source)
        assertCaseWithAmount("TEST CONVERT NUMBER TO AMOUNT", case, amount)
    }
}