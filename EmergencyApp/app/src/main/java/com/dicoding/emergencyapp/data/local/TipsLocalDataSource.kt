package com.dicoding.emergencyapp.data.local

import android.content.Context
import com.dicoding.emergencyapp.R
import com.dicoding.emergencyapp.data.entity.TipsEntity

class TipsLocalDataSource(context: Context) {

    private var context: Context = context

    fun getTips(key: String):TipsEntity?{
        val data: Map<String,TipsEntity> = mapOf(
            "traffic" to TipsEntity(
                title = context.getString(R.string.title_traffic),
                description = context.getString(R.string.description_traffic),
                tips = context.resources.getStringArray(R.array.tips_traffic),
                video = R.raw.tips_berkendara
            ),
            "crime" to TipsEntity(
                title = context.getString(R.string.title_crime),
                description = context.getString(R.string.description_crime),
                tips = context.resources.getStringArray(R.array.tips_crime),
                video = R.raw.tips_kriminal
            ),
            "fire" to TipsEntity(
                title = context.getString(R.string.title_fire),
                description = context.getString(R.string.description_fire),
                tips = context.resources.getStringArray(R.array.tips_fire),
                video = R.raw.tips_kebakaran
            )
        )
        return data.get(key)
    }
}