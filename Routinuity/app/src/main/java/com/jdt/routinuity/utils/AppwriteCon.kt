package com.jdt.routinuity.utils

import android.content.Context
import android.util.Log
import com.jdt.routinuity.R
import io.appwrite.Client
import io.appwrite.services.Databases
import java.io.InputStream
import java.io.BufferedReader
import java.io.InputStreamReader

class AppwriteCon private constructor(context: Context) {

    private val client: Client
    private val databases: Databases

    init {
        val inputStream: InputStream = context.resources.openRawResource(R.raw.env)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val content = reader.use { it.readText() }

        Log.d("AppwriteCon", "Content of .env file: $content")

        val lines = content.split("\n")
        for (line in lines) {
            val parts = line.split("=", limit = 2)
            if (parts.size == 2) {
                val key = parts[0].trim()
                val value = parts[1].trim()

                System.setProperty(key, value)

                Log.d("AppwriteCon", "Loaded variable: $key = $value")
            }
        }

        val endpoint = System.getProperty("APPWRITE_ENDPOINT")
        val projectId = System.getProperty("APPWRITE_PROJECT_ID")

        client = Client(context)
            .setEndpoint(endpoint ?: "")
            .setProject(projectId ?: "")
            .setSelfSigned(true)

        databases = Databases(client)
    }

    fun getClient(): Client = client

    fun getDatabases(): Databases = databases

    companion object {
        @Volatile
        private var instance: AppwriteCon? = null

        fun getInstance(context: Context): AppwriteCon {
            return instance ?: synchronized(this) {
                instance ?: AppwriteCon(context).also { instance = it }
            }
        }
    }
}
