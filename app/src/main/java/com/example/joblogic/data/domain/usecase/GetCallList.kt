package com.example.joblogic.domain.usecases

import arrow.core.Either
import com.example.joblogic.common.Failure
import com.example.joblogic.common.NoParams
import com.example.joblogic.common.UseCase
import com.example.joblogic.domain.entities.Contact
import com.example.joblogic.domain.repositories.ContactRepository
import javax.inject.Inject

class GetCallList @Inject constructor(
    private val contactRepository: ContactRepository
) : UseCase<List<Contact>, NoParams>() {

    override suspend fun invoke(params: NoParams): Either<Failure, List<Contact>> {
        return contactRepository.getContactList()
    }
}