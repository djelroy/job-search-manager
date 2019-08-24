package com.djelroy.jsm.data;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.annotation.NonNull
import java.util.*

@Entity(tableName = "applications", primaryKeys = ["company", "title"])
class Application(@ColumnInfo(name = "company") val company: String ,
                  @ColumnInfo(name = "title") val title: String ,
                  @ColumnInfo(name = "description") val description: String/*,
                  @ColumnInfo(name = "submitted_date") val submittedDate: Date = Date()*/)