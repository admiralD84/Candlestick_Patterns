package uz.admiraldev.candlepatterns.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trading_indicators")
data class TradingIndicator(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,
    val englishName: String,
    val description: String
)
