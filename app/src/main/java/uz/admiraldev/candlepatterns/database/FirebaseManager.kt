package uz.admiraldev.candlepatterns.database

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import uz.admiraldev.candlepatterns.models.Patterns
import uz.admiraldev.candlepatterns.models.TradingIndicator
import uz.admiraldev.candlepatterns.models.TradingTerms

class FirebaseManager {
    private val database: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference
    }

    fun readAllTradingTerms(callback: (List<TradingTerms>) -> Unit) {
        val tradingTermsRef = database.child("terms")

        tradingTermsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tradingTermsList = mutableListOf<TradingTerms>()

                for (termSnapshot in snapshot.children) {
                    val tradingTerm = termSnapshot.getValue(TradingTerms::class.java)
                    tradingTerm?.let { tradingTermsList.add(it) }
                }

                callback(tradingTermsList)
            }

            override fun onCancelled(error: DatabaseError) {
                /*TODO*/
                callback(emptyList())
            }
        })
    }

    fun readAllTradingIndicators(callback: (List<TradingIndicator>) -> Unit) {
        val tradingIndicatorRef = database.child("indicators")

        tradingIndicatorRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tradingIndicatorsList = mutableListOf<TradingIndicator>()

                for (indicatorSnapshot in snapshot.children) {
                    val tradingIndicator = indicatorSnapshot.getValue(TradingIndicator::class.java)
                    tradingIndicator?.let { tradingIndicatorsList.add(it) }
                }

                callback(tradingIndicatorsList)
            }

            override fun onCancelled(error: DatabaseError) {
                /*TODO*/
                callback(emptyList())
            }
        })
    }

    fun readAllPatterns(callback: (List<Patterns>) -> Unit) {
        val patternsRef = database.child("patterns")

        patternsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val patternsList = mutableListOf<Patterns>()

                for (patternSnapshot in snapshot.children) {
                    val pattern = patternSnapshot.getValue(Patterns::class.java)
                    pattern?.let { patternsList.add(it) }
                }

                callback(patternsList)
            }

            override fun onCancelled(error: DatabaseError) {
                /*TODO*/
                callback(emptyList())
            }
        })
    }

    fun readTradingIndicator(id: String, callback: (TradingIndicator?) -> Unit) {
        val tradingIndicatorRef = database.child("trading_indicators").child(id)

        tradingIndicatorRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tradingIndicator = snapshot.getValue(TradingIndicator::class.java)
                callback(tradingIndicator)
            }

            override fun onCancelled(error: DatabaseError) {/*TODO*/
                callback(null)
            }
        })
    }

    fun readPattern(id: String, callback: (Patterns?) -> Unit) {
        val patternRef = database.child("patterns").child(id)

        patternRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val pattern = snapshot.getValue(Patterns::class.java)
                callback(pattern)
            }

            override fun onCancelled(error: DatabaseError) {/*TODO*/
                callback(null)
            }
        })
    }

    fun readTradingTerm(id: String, callback: (TradingTerms?) -> Unit) {
        val tradingTermRef = database.child("trading_glossary").child(id)

        tradingTermRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val tradingTerm = snapshot.getValue(TradingTerms::class.java)
                callback(tradingTerm)
            }

            override fun onCancelled(error: DatabaseError) {/*TODO*/
                callback(null)
            }
        })
    }
}