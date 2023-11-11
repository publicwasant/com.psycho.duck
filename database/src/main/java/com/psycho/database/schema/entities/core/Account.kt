package com.psycho.database.schema.entities.core

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.psycho.database.schema.entities.BaseEntity
import java.io.Serializable

@Entity(tableName="account")
class Account: BaseEntity(), Serializable {
    @PrimaryKey(autoGenerate=true)
    override var id: Int = 0

    var title: String = ""
    var bankName: String = ""
    var holderName: String = ""
    var phoneNumber: String = "0000000000"
    var accountNumber: String = "0000000000"
    var promptPayQrCode: String? = null

    override fun from(baseEntity: BaseEntity) {
        if (baseEntity is Account) {
            id = baseEntity.id
            title = baseEntity.title
            bankName = baseEntity.bankName
            holderName = baseEntity.holderName
            phoneNumber = baseEntity.phoneNumber
            accountNumber = baseEntity.accountNumber
            promptPayQrCode = baseEntity.promptPayQrCode
        }
    }

    override fun copy(): Account {
        val newAccount = Account()
        newAccount.id = id
        newAccount.title = title
        newAccount.bankName = bankName
        newAccount.holderName = holderName
        newAccount.phoneNumber = phoneNumber
        newAccount.accountNumber = accountNumber
        newAccount.promptPayQrCode = promptPayQrCode
        return newAccount
    }
}