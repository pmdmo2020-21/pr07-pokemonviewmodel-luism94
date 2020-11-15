package es.iessaladillo.pedrojoya.intents.data.local.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pokemon(val id: Long, val name : String, val combatPower: Int, val image: Int,): Parcelable