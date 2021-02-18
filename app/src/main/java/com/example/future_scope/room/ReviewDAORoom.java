package com.example.future_scope.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.future_scope.model.Review;

import java.util.List;

@Dao
public interface ReviewDAORoom {
    @Insert
    void insertar(Review review);

    @Delete
    void borrar(Review review);

    @Update
    void actualizar(Review review);

    @Query("SELECT * FROM review WHERE idReview = :id LIMIT 1")
    Review buscar(String id);

    @Query("SELECT * FROM review")
    List<Review> buscarTodos();
}