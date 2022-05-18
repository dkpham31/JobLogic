package com.example.joblogic.domain.usecases

data class JobLogicUseCase(
    // declare use cases
    val getCallList: GetCallList,
    val getBuyList: GetBuyList,
    val getSellList: GetSellList,
)