package com.example.german.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.german.data.entities.UserRole

@Dao
interface UserRoleDao {

    @Insert
    suspend fun insert(role: UserRole)

    @Insert
    suspend fun insertAll(roles: List<UserRole>)

    @Query("SELECT * FROM common_userrole")
    suspend fun getAll(): List<UserRole>

    @Query("SELECT * FROM common_userrole WHERE id = :id")
    suspend fun getById(id: Int): UserRole?

    @Update
    suspend fun update(role: UserRole)
}
