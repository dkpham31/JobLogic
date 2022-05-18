package com.example.joblogic.domain.usecases

import arrow.core.Either
import com.example.joblogic.common.Failure
import com.example.joblogic.common.NoParams
import com.example.joblogic.common.UseCase
import com.example.joblogic.domain.entities.ItemToSell
import com.example.joblogic.domain.repositories.ProductRepository
import javax.inject.Inject

class GetSellList @Inject constructor(private val productRepository: ProductRepository) :
    UseCase<List<ItemToSell>, NoParams>() {

    override suspend fun invoke(params: NoParams): Either<Failure, List<ItemToSell>> {
        return productRepository.getSellList()
    }
}