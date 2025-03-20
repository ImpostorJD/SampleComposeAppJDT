package com.jdt.routinuity.utils

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(private val context: Context) {

    companion object {
        private val FINISHED_WALKTHROUGH_KEY = booleanPreferencesKey("finished_walkthrough")
        private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
        private val PROJECT_ID_KEY = stringPreferencesKey("project_id")
        private val ATTRIBUTES_KEY = stringPreferencesKey("attributes")
        private val gson = Gson()
    }

    suspend fun saveBoolean(key: Preferences.Key<Boolean>, value: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }

    suspend fun saveString(key: Preferences.Key<String>, value: String) {
        context.dataStore.edit { prefs ->
            prefs[key] = value
        }
    }
    suspend fun saveAttributes(attributes: List<List<String>>) {
        val json = gson.toJson(attributes)
        context.dataStore.edit { prefs ->
            prefs[ATTRIBUTES_KEY] = json
        }
    }

    fun getBoolean(key: Preferences.Key<Boolean>): Flow<Boolean> {
        return context.dataStore.data.map { prefs ->
            prefs[key] ?: false
        }
    }

    fun getString(key: Preferences.Key<String>): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[key]
        }
    }

    fun getAttributes(): Flow<List<List<String>>> {
        return context.dataStore.data.map { prefs ->
            val json = prefs[ATTRIBUTES_KEY] ?: "[]"
            val type = object : TypeToken<List<List<String>>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        }
    }

    suspend fun setFinishedWalkthrough(value: Boolean) = saveBoolean(FINISHED_WALKTHROUGH_KEY, value)
    suspend fun setDarkMode(value: Boolean) = saveBoolean(DARK_MODE_KEY, value)
    suspend fun setProjectId(value: String) = saveString(PROJECT_ID_KEY, value)

    fun getFinishedWalkthrough(): Flow<Boolean> = getBoolean(FINISHED_WALKTHROUGH_KEY)
    fun getDarkMode(): Flow<Boolean> = getBoolean(DARK_MODE_KEY)
    fun getProjectId(): Flow<String?> = getString(PROJECT_ID_KEY)
}
