package uz.admiraldev.candlepatterns.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.admiraldev.candlepatterns.R
import uz.admiraldev.candlepatterns.models.TradingIndicator

class IndicatorsAdapter(private val indicatorList: List<TradingIndicator>) :
    RecyclerView.Adapter<IndicatorsAdapter.TermsViewHolder>() {
    private var onIndicatorClickListener: OnIndicatorClickListener? = null

    fun setOnIndicatorClickListener(listener: OnIndicatorClickListener) {
        onIndicatorClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermsViewHolder {
        val contentItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_terms_and_indicators, parent, false)
        return TermsViewHolder(contentItemView)
    }

    override fun getItemCount(): Int {
        return indicatorList.size
    }

    override fun onBindViewHolder(holder: TermsViewHolder, position: Int) {
        val indicator = indicatorList[position]
        holder.name.text = indicator.name
        holder.enName.text = indicator.englishName
        holder.itemView.setOnClickListener {
            onIndicatorClickListener?.onIndicatorClick(indicator)
        }
    }


    class TermsViewHolder(termsView: View) : RecyclerView.ViewHolder(termsView) {
        var name: TextView
        var enName: TextView

        init {
            name = termsView.findViewById(R.id.tv_item_name)
            enName = termsView.findViewById(R.id.tv_item_en_name)
        }
    }

    interface OnIndicatorClickListener {
        fun onIndicatorClick(indicator: TradingIndicator)
    }

}