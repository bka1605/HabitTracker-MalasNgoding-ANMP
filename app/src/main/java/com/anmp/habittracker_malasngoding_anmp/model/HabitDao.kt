package com.anmp.habittracker_malasngoding_anmp.model


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabit(habit: HabitModel)

    @Query("SELECT * FROM habit ORDER BY id ASC")
    fun getAllHabits(): List<HabitModel>

    @Update
    fun updateHabit(habit: HabitModel)

    @Query("SELECT * FROM habit WHERE id = :id")
    fun getHabit(id: Long): HabitModel?

    @Query("DELETE FROM habit")
    fun deleteAll()
    @Query("SELECT * FROM habit WHERE id=:id")
    fun selectHabit(id:Long): HabitModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg habit:HabitModel)

    @Update
    fun updateHabit(habit: HabitModel)
}