package uz.admiraldev.candlepatterns.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.admiraldev.candlepatterns.MyApplication
import uz.admiraldev.candlepatterns.repositories.AppRepository

class AppViewModelFactory(
    private val application: MyApplication,
    private val repository: AppRepository
) : ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AppViewModel(application, repository) as T
    }
}