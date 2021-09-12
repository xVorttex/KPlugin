package eu.vortexgg.kplugin.util

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoCredential
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import java.sql.Connection
import java.sql.DriverManager
import java.util.concurrent.TimeUnit

sealed class Db {

    data class MongoDb(val host: String, val port: Int, val authEnabled: Boolean, val database: String, val username: String, val password: String): Db() {
        val client: MongoClient

        init {
            val builder = MongoClientSettings.builder()
            builder.applyConnectionString(ConnectionString("mongodb://$host:$port"))
            if(authEnabled)
                builder.credential(MongoCredential.createCredential(username, database, password.toCharArray()))
            client = MongoClients.create(builder.build())
        }

        fun createDatabase(name: String): MongoDatabase = client.getDatabase(name)

        fun disconnect() {
            client.close()
        }
    }

    data class MySQL(val host: String, val port: Int, val authEnabled: Boolean, val database: String, val username: String, val password: String): Db() {
        val connection: Connection = if(authEnabled) {
            DriverManager.getConnection("jdbc:mysql://$host:$port/$database?useSSL=false", username, password)
        } else {
            DriverManager.getConnection("jdbc:mysql://$host:$port/$database?useSSL=false")
        }

        fun createTable(tableName: String, structure: String) {
            connection.prepareStatement("CREATE TABLE IF NOT EXISTS $tableName($structure)").executeUpdate()
        }

        fun disconnect() {
            connection.close()
        }
    }
}
