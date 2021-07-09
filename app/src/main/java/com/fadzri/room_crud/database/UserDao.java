package com.fadzri.room_crud.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid = :uid")
    User getUser(int uid);

    @Insert
    void insertAll(User... users);

    @Update
    void updateData(User... users);

    @Delete
    void delete(User user);
}
