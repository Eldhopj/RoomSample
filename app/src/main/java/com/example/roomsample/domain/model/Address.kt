package com.example.roomsample.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Address(val residentName: String, val street: String) : Parcelable
