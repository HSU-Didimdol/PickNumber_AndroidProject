package com.example.picknumber_androidproject.view.main

import androidx.lifecycle.*
import com.example.domain.model.Bank
import com.example.domain.model.BankResult
import com.example.domain.usecase.GetBankListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBankListUseCase: GetBankListUseCase
) : ViewModel() {


}


