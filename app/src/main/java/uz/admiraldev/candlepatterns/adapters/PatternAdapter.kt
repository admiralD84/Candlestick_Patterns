package uz.admiraldev.candlepatterns.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.admiraldev.candlepatterns.R
import uz.admiraldev.candlepatterns.models.Patterns

class PatternAdapter(
    private val itemClickListener: PatternAdapter.OnPatternClickListener,
    private val patternsList: List<Patterns>,
    private val context: Context
) :
    RecyclerView.Adapter<PatternAdapter.MyViewHolder>() {
 /*   private var onPatternClickListener: PatternAdapter.OnPatternClickListener? = null
    fun setOnPatternsClickListener(listener: PatternAdapter.OnPatternClickListener) {
        onPatternClickListener = listener
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val contentItemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_patterns, parent, false)
        return MyViewHolder(contentItemView)
    }

    override fun getItemCount(): Int {
        return patternsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pattern = patternsList[position]
        holder.patternName.text = pattern.patternName
        holder.patternEnName.text = pattern.patternEnglishName
        Glide.with(context)
            .load(pattern.patternTitleImage)
            .placeholder(R.drawable.bull_patterns)
            .into(holder.patternImage)

        holder.itemView.setOnClickListener {
            itemClickListener.onPatternClick(pattern)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var patternName: TextView
        var patternEnName: TextView
        var patternImage: ImageView

        init {
            patternImage = itemView.findViewById(R.id.iv_pattern_image)
            patternEnName = itemView.findViewById(R.id.tv_pattern_en_name)
            patternName = itemView.findViewById(R.id.tv_pattern_name)
        }
    }

    interface OnPatternClickListener {
        fun onPatternClick(pattern: Patterns)
    }
}