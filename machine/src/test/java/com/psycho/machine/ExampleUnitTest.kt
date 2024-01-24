package com.psycho.machine

import com.psycho.database.schema.entities.core.Account
import com.psycho.database.schema.entities.core.Net
import com.psycho.database.schema.entities.core.NetWorth
import com.psycho.machine.actions.implement.param_collectors.ParamCollectors
import com.psycho.machine.actions.implement.param_collectors.ParamData
import com.psycho.utility.core.NetStatus
import com.psycho.utility.core.NetType
import com.psycho.utility.core.NetWorthStatus
import com.psycho.utility.financial.Amount
import org.junit.Test

import org.junit.Assert.*
import java.util.Date

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun paramCollectorsUnit() {
        val paramCollectors = ParamCollectors()

        paramCollectors.set("[{\"key\":\"param1\", \"value\": \"98.56\"}]")
        paramCollectors.set(ParamCollectors(ParamData("param2", "hello")))
        paramCollectors.set(ParamData("param3", true))

        val netWorth = NetWorth()
        netWorth.netWorthStatus = NetWorthStatus.RISKY

        val account = Account()
        account.title = "My KBank Account"
        account.bankName = "KBank"
        account.holderName = "วสันต์ พรดี"
        account.phoneNumber = "0968841508"
        account.accountNumber = "1440700156"

        val net = Net(netWorth, account)
        net.title = "เก็บตังค์ซื้อคอมฯ"
        net.description = "งบประมาณ ~45,000 บาท"
        net.netGoalsAmount = Amount.from(45990.89)
        net.netType = NetType.ASSET
        net.netStatus = NetStatus.ACTIVE

        paramCollectors.set("param4", net)

        val importParameters = ParamCollectors()
        val importParametersToJson: String = paramCollectors.toString()

        importParameters.set(importParametersToJson)

        val param1: Any? = importParameters.get("param1")
        val param1ToDouble: Double? = importParameters.get("param1", Double::class.java)
        val param1ToDouble2: Double = importParameters.get("param1").toString().toDouble()
        println("*".repeat(100))
        println("param1:                                        %s".format(param1))
        println("param1: (Double)                               %s".format(param1ToDouble))
        println("param1: (Double)                               %s".format(param1ToDouble2))

        val param2: Any? = importParameters.get("param2")
        println("*".repeat(100))
        println("param2:                                        %s".format(param2))

        val param3: Any? = importParameters.get("param3")
        val param3ToBoolean: Boolean? = importParameters.get("param3", Boolean::class.java)
        val param3ToBoolean2: Boolean = importParameters.get("param3").toString().toBoolean()
        println("*".repeat(100))
        println("param3:                                        %s".format(param3))
        println("param3: (Boolean)                              %s".format(param3ToBoolean))
        println("param3: (Boolean)                              %s".format(param3ToBoolean2))

        val param4: Any? = importParameters.get("param4")
        val param4ToString1: String? = importParameters.get("param4", String::class.java)
        val param4ToString2: String = importParameters.get("param4").toString()
        val param4ToNet: Net? = importParameters.get("param4", Net::class.java)
        println("*".repeat(100))
        println("param4:                                        %s".format(param4))
        println("param4: (String1)                              %s".format(param4ToString1))
        println("param4: (String2)                              %s".format(param4ToString2))
        println("param4: (Net)                                  %s".format(param4ToNet))
        println("param4: (Net.netWorth.id)                      %s".format(param4ToNet?.netWorth?.netWorthStatus?.id))
        println("param4: (Net.netWorth.string)                  %s".format(param4ToNet?.netWorth?.netWorthStatus?.string))
        println("param4: (Net.account.title)                    %s".format(param4ToNet?.account?.title))
        println("param4: (Net.account.bankName)                 %s".format(param4ToNet?.account?.bankName))
        println("param4: (Net.account.holderName)               %s".format(param4ToNet?.account?.holderName))
        println("param4: (Net.account.phoneNumber)              %s".format(param4ToNet?.account?.phoneNumber))
        println("param4: (Net.account.accountNumber)            %s".format(param4ToNet?.account?.accountNumber))
        println("param4: (Net.title)                            %s".format(param4ToNet?.title))
        println("param4: (Net.description)                      %s".format(param4ToNet?.description))
        println("param4: (Net.netGoalsAmount)                   %s".format(param4ToNet?.netGoalsAmount))
        println("param4: (Net.netGoalsAmount -> Double)         %s".format(param4ToNet?.netGoalsAmount?.toDouble()))
        println("param4: (Net.netGoalsAmount -> Standard)       %s".format(param4ToNet?.netGoalsAmount?.toStandard()))
        println("param4: (Net.netGoalsAmount -> String)         %s".format(param4ToNet?.netGoalsAmount?.toString()))
        println("param4: (Net.netType.id)                       %s".format(param4ToNet?.netType?.id))
        println("param4: (Net.netType.string)                   %s".format(param4ToNet?.netType?.string))
        println("param4: (Net.netStatus.id)                     %s".format(param4ToNet?.netStatus?.id))
        println("param4: (Net.netStatus.string)                 %s".format(param4ToNet?.netStatus?.string))
    }
}