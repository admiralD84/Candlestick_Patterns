package uz.admiraldev.candlepatterns.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "images",
    foreignKeys = [ForeignKey(
        entity = Patterns::class,
        parentColumns = ["id"],
        childColumns = ["patternId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class PatternImages(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val patternId: Long,
    val imageURL: String
)
