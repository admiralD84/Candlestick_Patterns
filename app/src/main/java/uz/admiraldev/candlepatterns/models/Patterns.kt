package uz.admiraldev.candlepatterns.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "patterns")
data class Patterns(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val patternName: String,
    val patternEnglishName: String,
    val patternType: String,
    val description: String,
    val patternTitleImage: String,
    val category: String
)