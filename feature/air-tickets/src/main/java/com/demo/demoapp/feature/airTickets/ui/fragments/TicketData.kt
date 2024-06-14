package com.demo.demoapp.feature.airTickets.ui.fragments

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TicketData(val from : String, val to : String) : Parcelable
