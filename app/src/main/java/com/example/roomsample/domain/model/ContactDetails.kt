package com.example.roomsample.domain.model

import android.os.Parcelable
import androidx.room.Embedded
import java.util.Date
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactDetails(
    val sinceResiding: Date,
    @Embedded
    val address: Address,
    val email: String,
    val phoneNumber: String
) : Parcelable
