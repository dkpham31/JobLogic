package com.example.joblogic.common

sealed class Failure {
    data class ApiFailure(val code: Int) : Failure()
    object NetworkFailure : Failure()
    object OtherFailure : Failure()
}
