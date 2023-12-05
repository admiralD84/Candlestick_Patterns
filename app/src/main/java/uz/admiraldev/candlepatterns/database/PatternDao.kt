package uz.admiraldev.candlepatterns.database

import androidx.room.Dao
import androidx.room.Query
import uz.admiraldev.candlepatterns.models.PatternImages
import uz.admiraldev.candlepatterns.models.Patterns

@Dao
interface PatternDao {

    @Query("SELECT * FROM patterns")
    suspend fun getAllPatterns(): List<Patterns>

    @Query("SELECT * FROM images WHERE patternId = :patternId")
    suspend fun getImagesForPattern(patternId: Long): List<PatternImages>
}
