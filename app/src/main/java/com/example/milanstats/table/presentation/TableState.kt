package com.example.milanstats.table.presentation

import com.example.milanstats.table.domain.model.TableInformation

data class TableState(
    val isLoading: Boolean = false,
    val tableInformation: TableInformation? = null,
)
