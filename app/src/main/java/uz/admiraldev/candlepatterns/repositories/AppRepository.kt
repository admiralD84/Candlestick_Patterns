package uz.admiraldev.candlepatterns.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uz.admiraldev.candlepatterns.models.Patterns
import uz.admiraldev.candlepatterns.models.TradingIndicator
import uz.admiraldev.candlepatterns.models.TradingTerms

class AppRepository {

    private val database: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference
    }

    private val tradingIndicatorsLiveData = MutableLiveData<List<TradingIndicator>>()
    private val tradingTermsLiveData = MutableLiveData<List<TradingTerms>>()
    private val patternsLiveData = MutableLiveData<List<Patterns>>()

    fun getTradingIndicatorsLiveData(): LiveData<List<TradingIndicator>> {
        return tradingIndicatorsLiveData
    }

    fun getTradingTermsLiveData(): LiveData<List<TradingTerms>> {
        return tradingTermsLiveData
    }

    fun getPatternsLiveData(): LiveData<List<Patterns>> {
        return patternsLiveData
    }

    /*  suspend fun loadDataFromFirebase() {
        withContext(Dispatchers.IO) {
            loadDataOfType(
                "indicators",
                TradingIndicator::class.java,
                tradingIndicatorsLiveData
            ) { }

            loadDataOfType(
                "terms",
                TradingTerms::class.java,
                tradingTermsLiveData
            ) { }

            loadDataOfType(
                "patterns",
                Patterns::class.java,
                patternsLiveData
            ) { }
        }
    }

    private inline fun <reified T> loadDataOfType(
        nodeName: String,
        clazz: Class<T>,
        liveData: MutableLiveData<List<T>>,
        crossinline onComplete: (List<T>) -> Unit
    ) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataList = mutableListOf<T>()
                for (dataSnapshot in snapshot.children) {
                    val data = dataSnapshot.getValue(clazz)
                    data?.let { dataList.add(it) }
                }
                liveData.postValue(dataList)
                onComplete.invoke(dataList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("myTags", "Firebase data loading error " + error.message)
            }
        }
        database.child(nodeName).addValueEventListener(listener)
    }
}
*/

//==================================================================================================

    suspend fun loadDataFromFirebase() {
        withContext(Dispatchers.IO) {
            // Indicatorlarni Firebasedan yuklab olish
            database.child("indicators").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val tradingIndicatorsList = mutableListOf<TradingIndicator>()
                    for (indicatorSnapshot in snapshot.children) {
                        val tradingIndicator =
                            indicatorSnapshot.getValue(TradingIndicator::class.java)
                        tradingIndicator?.let { tradingIndicatorsList.add(it) }
                    }
                    tradingIndicatorsLiveData.postValue(tradingIndicatorsList)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("myTags", "Firebase indicator list loading error " + error.message)
                }
            })

            // Terminlarni Firebasedan yuklab olish
            database.child("terms").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val tradingTermsList = mutableListOf<TradingTerms>()
                    for (termSnapshot in snapshot.children) {
                        val tradingTerm = termSnapshot.getValue(TradingTerms::class.java)
                        tradingTerm?.let { tradingTermsList.add(it) }
                    }
                    tradingTermsLiveData.postValue(tradingTermsList)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("myTags", "Firebase terms list loading error " + error.message)
                }
            })

            // Patternlarni Firebasedan yuklab olish
            database.child("patterns").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val patternsList = mutableListOf<Patterns>()
                    for (patternSnapshot in snapshot.children) {
                        val pattern = patternSnapshot.getValue(Patterns::class.java)
                        pattern?.let { patternsList.add(it) }
                    }
                    patternsLiveData.postValue(patternsList)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("myTags", "Firebase patterns list loading error " + error.message)
                }
            })
        }
    }
}
/*
 private suspend fun loadDataFromCache() {
     withContext(Dispatchers.IO) {
         // Indikatorlarni keshdan yuklash
         val cachedIndicators = loadDataFromCache("indicators", TradingIndicator::class.java)
         tradingIndicatorsLiveData.postValue(cachedIndicators)

         // Terminlarni keshdan yuklash
         val cachedTerms = loadDataFromCache("terms", TradingTerms::class.java)
         tradingTermsLiveData.postValue(cachedTerms)

         // Patternlarni keshdan yuklash
         val cachedPatterns = loadDataFromCache("patterns", Patterns::class.java)
         patternsLiveData.postValue(cachedPatterns)
     }
 }

 private fun <T> saveDataToCache(key: String, data: List<T>) {
     val sharedPreferences = context.getSharedPreferences("MyCache", Context.MODE_PRIVATE)
     val editor = sharedPreferences.edit()
     editor.putString(key, Gson().toJson(data))
     editor.apply()
 }

 private fun <T> loadDataFromCache(key: String, clazz: Class<T>): List<T> {
     val sharedPreferences = context.getSharedPreferences("MyCache", Context.MODE_PRIVATE)
     val json = sharedPreferences.getString(key, "")
     return if (json.isNullOrEmpty()) {
         emptyList()
     } else {
         try {
             Gson().fromJson(json, object : TypeToken<List<T>>() {}.type)
         } catch (e: Exception) {
             Log.e("DeserializeError", "Error deserializing data for key: $key", e)
             emptyList()
         }
     }
 }

 private fun isOnline(): Boolean {
     val connectivityManager =
         context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
     val network = connectivityManager.activeNetwork ?: return false
     val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
     return when {
         activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
         activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
         else -> false
     }
 }*/

