package uz.admiraldev.candlepatterns.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.admiraldev.candlepatterns.R
import uz.admiraldev.candlepatterns.models.TradingTerms

class TermsAdapter(private val termsList: List<TradingTerms>) :
    RecyclerView.Adapter<TermsAdapter.TermsViewHolder>() {
    private var onTermsClickListener: TermsAdapter.OnTermClickListener? = null
    fun setOnTermsClickListener(listener: TermsAdapter.OnTermClickListener) {
        onTermsClickListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TermsViewHolder {
        val contentItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_terms_and_indicators, parent, false)
        return TermsViewHolder(contentItemView)
    }

    override fun getItemCount(): Int {
        return termsList.size
    }

    override fun onBindViewHolder(holder: TermsViewHolder, position: Int) {
        val term = termsList[position]
        holder.name.text = term.term
        holder.enName.text = term.englishName
        holder.itemView.setOnClickListener {
            onTermsClickListener?.onTermClick(term)
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

    interface OnTermClickListener {
        fun onTermClick(term: TradingTerms)
    }
}