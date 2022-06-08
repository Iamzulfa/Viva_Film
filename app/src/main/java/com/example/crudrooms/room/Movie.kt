package com.example.crudrooms.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie (
    @PrimaryKey(autoGenerate = true)

    val id: Int,
    val title: String,
    val desc: String
        )
