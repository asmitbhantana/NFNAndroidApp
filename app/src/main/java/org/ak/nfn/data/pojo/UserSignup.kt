package org.ak.nfn.data.pojo
data class UserSignup(
    //first page
    var email: String? = "",
    var password: String? = "",
    var username: String? = "",
    var phone_number: String? = "",

    //second page
    var first_name: String?="",
    var last_name: String?="",
    var gender: String? = "",
    var dob: String?= "",
    var citizenship_number: String? = "",
    var current_address: String? = "",
    var permanent_address: String? = ""
)