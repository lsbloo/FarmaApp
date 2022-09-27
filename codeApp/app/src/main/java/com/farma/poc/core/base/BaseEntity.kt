package com.farma.poc.core.base

import androidx.room.Entity
import androidx.room.PrimaryKey


abstract class BaseEntity {}

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = 0,
    var name:String?= null): BaseEntity()