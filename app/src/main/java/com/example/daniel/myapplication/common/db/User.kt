
package com.example.daniel.myapplication.common.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by daniel on 17-10-19.
 */
@Entity(tableName = User.TABLE_NAME)
data class User constructor(val name: String,
                            val phone: String,
                            @PrimaryKey(autoGenerate = true) val id: Long,
                            @ColumnInfo(name = "addr") val address: String) {
    companion object {
        const val TABLE_NAME = "users"
    }
}