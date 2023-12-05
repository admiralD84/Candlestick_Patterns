package uz.admiraldev.candlepatterns.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("trading_glossary")
data class TradingTerms(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val term: String,
    val englishName: String,
    val definition: String
)
