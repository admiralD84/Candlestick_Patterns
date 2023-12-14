package uz.admiraldev.candlepatterns.viewmodels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.admiraldev.candlepatterns.MyApplication
import uz.admiraldev.candlepatterns.repositories.AppRepository

class AppViewModel(application: MyApplication, private val repository: AppRepository) :
    AndroidViewModel(application) {

    // Indicatorlar uchun LiveData
    val tradingIndicatorsLiveData = repository.getTradingIndicatorsLiveData()

    // Terminlar uchun LiveData
    val tradingTermsLiveData = repository.getTradingTermsLiveData()

    // Patternlar uchun LiveData
    val patternsLiveData = repository.getPatternsLiveData()

    private val _selectedDescription = MutableLiveData<String>()
    val selectedItemDescription: LiveData<String>
        get() = _selectedDescription

    fun saveDescription(description: String) {
        _selectedDescription.value = description
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadDataFromFirebase()
        }
    }
}