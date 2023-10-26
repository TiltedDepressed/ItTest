package com.example.ittestapplication.model

import com.google.gson.annotations.SerializedName

data class User(
@SerializedName("first_name")var firstName: String? = null,
@SerializedName("last_name")var lastName: String? = null,
@SerializedName("middle_name")var middleName: String? = null,
@SerializedName("login")var login: String? = null,
@SerializedName("password")var password: String? = null,
@SerializedName("role")var role: Int = 1,
@SerializedName("avatar")var avatar: String? = null
)
