package com.jdt.routinuity.connection

import io.github.cdimascio.dotenv.Dotenv
import android.content.Context
import io.appwrite.Client
import io.appwrite.services.Databases

object AppwriteCon {

    private var client: Client? = null
    private var databases: Databases? = null

    val dotenv = Dotenv.load()
    val endpoint = dotenv["APPWRITE_ENDPOINT"]
    val projectId = dotenv["APPWRITE_PROJECT_ID"]
    // Initialize Appwrite client and services

    fun getDatabases(context: Context): Databases {
        // Initialize client only once
        if (client == null) {
            val client = Client(context)
                .setEndpoint(endpoint ?: "")
                .setProject(projectId ?: "")
        }

        // Initialize Databases service only once
        if (databases == null) {
            databases = Databases(client!!)
        }

        return databases!!
    }
}
