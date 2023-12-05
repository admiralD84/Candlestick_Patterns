package uz.admiraldev.candlepatterns.database

import androidx.room.Dao
import androidx.room.Query
import uz.admiraldev.candlepatterns.models.TradingTerms

@Dao
interface TradingTermsDao {

    @Query("SELECT * FROM trading_glossary")
    suspend fun getAllTerms(): List<TradingTerms>
}