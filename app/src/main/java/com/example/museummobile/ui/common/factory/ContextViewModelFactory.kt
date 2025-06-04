package com.example.museummobile.ui.common.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ContextViewModelFactory<T: ViewModel>(
    private val context: Context,
    private val creator: (Context) -> T
) : ViewModelProvider.Factory{
    override fun <V : ViewModel> create(modelClass: Class<V>): V {

        @Suppress("UNCHECKED_CAST")
        return creator(context) as V
    }
}