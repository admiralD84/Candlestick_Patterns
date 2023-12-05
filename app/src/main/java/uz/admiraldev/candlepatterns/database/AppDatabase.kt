package uz.admiraldev.candlepatterns.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.admiraldev.candlepatterns.models.PatternImages
import uz.admiraldev.candlepatterns.models.Patterns
import uz.admiraldev.candlepatterns.models.TradingTerms

@Database(
    entities = [Patterns::class, PatternImages::class, TradingTerms::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun patternDao(): PatternDao

    abstract fun tradingTermsDao(): TradingTermsDao

    abstract fun indicatorsDao(): IndicatorsDao
}