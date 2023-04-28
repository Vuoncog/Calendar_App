package com.example.taskmanagementapp.database.converter

import androidx.room.TypeConverter
import com.example.taskmanagementapp.constant.TaskType
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type
import java.util.*

class ListToDoTaskConverter(
) {
//    @TypeConverter
//    fun toListToDoTaskJson(meaning: List<TaskType>) : String {
//
//        return jsonParser.toJson(
//            meaning,
//            object : TypeToken<ArrayList<TaskType>>(){}.type
//        ) ?: "[]"
//    }
//
//    @TypeConverter
//    fun fromListToDoTaskJson(json: String): List<TaskType>{
//        return jsonParser.fromJson<ArrayList<TaskType>>(
//            json,
//            object: TypeToken<ArrayList<TaskType>>(){}.type
//        ) ?: emptyList()
//    }

    @TypeConverter
    fun stringToListServer(data: String?): List<TaskType?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object :
            TypeToken<List<TaskType?>?>() {}.type
        return Gson().fromJson<List<TaskType?>>(data, listType)
    }

    @TypeConverter
    fun listServerToString(someObjects: List<TaskType?>?): String? {
        return Gson().toJson(someObjects)
    }
}