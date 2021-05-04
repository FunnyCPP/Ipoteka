package com.codart.ipoteka.data.entities

data class UserDataResponse(
    var data: UserData,
    var error: List<Any>,
    var success: Int
)

data class UserData(
    var calendar_pay: List<CalendarPay>,
    var credit_summ: String,
    var curs: String,
    var customer_email: String,
    var customer_id: String,
    var customer_name: String,
    var customer_telephone: String,
    var requisite: List<Requisite>,
    var total_peny: String,
    var total_procent: String,
    var type_credit_id: String,
    var type_credit_name: String
)

data class CalendarPay(
    var amount: String,
    var date_pay: String,
    var peni: String,
    var sort_order: String,
    var status_id: String,
    var status_name: String
)

data class Requisite(
    var fio: String,
    var name: String,
    var text: String
)