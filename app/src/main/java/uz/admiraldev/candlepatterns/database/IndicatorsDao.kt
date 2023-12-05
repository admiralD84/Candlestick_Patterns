package uz.admiraldev.candlepatterns.database

import androidx.room.Dao
import androidx.room.Query
import uz.admiraldev.candlepatterns.models.TradingIndicator

@Dao
interface IndicatorsDao {

    @Query("SELECT * FROM trading_indicators")
    suspend fun getIndicators(): List<TradingIndicator>

}