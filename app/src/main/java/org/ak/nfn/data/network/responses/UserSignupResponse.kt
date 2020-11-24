package org.ak.nfn.data.network.responses

/*
    "email": "test@yopmal.com",
    "gender": "1",
    "citizenship_number": "qwert@123",
    "current_address": "Gorkha",
    "permanent_address": "Kathmandu",
    "username": "test",
    "date_joined": "2020-11-21T13:28:03.797463Z",
    "pk": 5
 */
data class UserSignUpResponse(
    val email: String? = null,
    val first_name: String? = null,
    val last_name: String? = null,
    val gender: String? = null,
    val citizenship_number: String? = null,
    val current_address: String? = null,
    val permanent_address: String? = null,
    val username: String? = null,
    val date_joined: String? = null,
    val pk: Int? = null
)