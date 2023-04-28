package com.example.taskmanagementapp.database.converter

import androidx.room.TypeConverter
import com.example.taskmanagementapp.constant.EventInfo
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type
import java.util.*

class ListEventConverter(
) {
    @TypeConverter
    fun stringToListServer(data: String?): List<EventInfo?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object :
            TypeToken<List<EventInfo?>?>() {}.type
        return Gson().fromJson<List<EventInfo?>>(data, listType)
    }

    @TypeConverter
    fun listServerToString(someObjects: List<EventInfo?>?): String? {
        return Gson().toJson(someObjects)
    }
}