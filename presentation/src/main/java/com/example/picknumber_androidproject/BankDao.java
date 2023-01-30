package com.example.picknumber_androidproject;

import androidx.room.Query;

import java.util.List;

interface BankDao {
    @Query("SELECT * FROM ")
    List<Bank> getAllBank();
}
