package com.jdt.routinuity.utils

import android.content.Context
import android.util.Log
import io.appwrite.Client
import io.appwrite.services.Databases


class AppwriteService private constructor(private val context: Context) {

    private var client: Client? = null
    private var projectId: String? = null

    /**
     * Sets or updates the Project ID.
     * If a different Project ID is provided, the client is reinitialized.
     */
    suspend fun setProjectId(newProjectId: String) {

        if (projectId != newProjectId || client == null) {
            Log.d("appwrite", newProjectId)
            projectId = newProjectId
            client = Client(context)
                .setEndpoint("https://cloud.appwrite.io/v1")
                .setProject(newProjectId)
            
            val databases = Databases(client!!)
            try {
                // Try listing collections (validates project without requiring a collection)
                val collections = databases.listDocuments(
                    databaseId = "routinuitydb",
                    collectionId = "default",
                )
                Log.d("Appwrite", "Valid Project ID. Collections: ${collections.total}")
            } catch (e: Exception) {
                Log.e("Appwrite", "Invalid Project ID: ${e.message}")
                throw IllegalStateException("Invalid Project ID: ${e.message}")
            }
        }
    }

    /**
     * Returns the current Appwrite Client instance.
     * Throws an error if the client is not initialized.
     */
    fun getClient(): Client {
        return client ?: throw IllegalStateException("Appwrite Client is not initialized. Call setProjectId() first.")
    }

    /**
     * Returns the currently set Project ID.
     * Throws an error if the Project ID is not set.
     */
    fun getProjectId(): String {
        return projectId ?: throw IllegalStateException("Project ID is not set. Call setProjectId() first.")
    }

    companion object {
        @Volatile
        private var instance: AppwriteService? = null

        /**
         * Initializes the AppwriteService singleton **without requiring a Project ID initially**.
         */
        fun init(context: Context) {
            synchronized(this) {
                if (instance == null) {
                    instance = AppwriteService(context)
                }
            }
        }

        /**
         * Returns the singleton instance of AppwriteService.
         * Throws an error if init() was not called first.
         */
        fun getInstance(): AppwriteService {
            return instance ?: throw IllegalStateException("AppwriteService is not initialized. Call init(context) first.")
        }
    }
}
