package com.example.data.mapper

import com.example.data.model.bank.BankDto
import com.example.data.model.bank.BankListDto
import com.example.domain.model.Bank
import com.example.domain.model.BankList

fun BankDto.toEntity() = Bank(
    code = code,
    divisionCode = divisionCode,
    companyID = companyID,
    category = category,
    type = type,
    name = name,
    divisionName = divisionName,
    tel = tel,
    fax = fax,
    email = email,
    sido = sido,
    gugun = gugun,
    address = address,
    longitude = longitude,
    latitude = latitude,
    startTime = startTime,
    endTime = endTime,
    memo = memo,
    use = use,
    modifyDate = modifyDate,
    registrationDate = registrationDate,
    distance = distance
)

fun BankListDto.toEntity() = BankList(
    data = items.map {
        it.toEntity()
    }
)