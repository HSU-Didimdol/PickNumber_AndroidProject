package com.example.picknumber_androidproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "BankTable", indices = [Index(value = ["company_id"], unique = true)])
data class Bank(
    @PrimaryKey val code: String,
    @PrimaryKey val divisionCode: String,
    @ColumnInfo(name = "company_id") val companyID: Int,
    val category: Int = 10000,
    val type: String,
    val name: String,
    val divisionName: String,
    val tel: String,
    val fax: String,
    val email: String,
    val sido: String,
    val gugun: String,
    val address: String,
    val longitude: String,
    val latitude: String,
    val startTime: String,
    val endTime: String,
    val memo: String,
    val use: String = "Y",
    val modifyDate: Timestamp,
    val registrationDate: Timestamp
)
