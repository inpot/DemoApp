package com.example.daniel.myapplication.common.db

import android.arch.persistence.room.*

/**
 * Created by daniel on 17-10-19.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM ${User.TABLE_NAME}")
    fun loadAllUser(): Array<User>;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

}