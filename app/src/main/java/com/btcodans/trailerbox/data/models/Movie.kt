package com.btcodans.trailerbox.data.models
import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Movie(
    val id: Int,
    val title: String?,
    val poster_path: String?,
    val overview: String?,
    val vote_average: String?
) : Parcelable
