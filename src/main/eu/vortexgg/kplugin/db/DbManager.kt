package eu.vortexgg.kplugin.db

import com.mongodb.client.MongoDatabase
import eu.vortexgg.kplugin.config.Config
import eu.vortexgg.kplugin.util.Db

object DbManager {

    const val Sql_Database_Name = "kplugin_players"

    @JvmStatic lateinit var sql: Db.MySQL
    @JvmStatic lateinit var mongo: Db.MongoDb
    @JvmStatic lateinit var mongoDatabase: MongoDatabase

    @JvmStatic
    fun connect() {
        sql = Db.MySQL(Config.Sql_Host, Config.Sql_Port, Config.Sql_Auth_Enabled, Config.Sql_Database, Config.Sql_Username, Config.Sql_Password)
        sql.createTable(Sql_Database_Name, "UUID VARCHAR(42), NAME VARCHAR(16), PRIMARY KEY (UUID)")
        mongo = Db.MongoDb(Config.Mongo_Host, Config.Mongo_Port, Config.Mongo_Auth_Enabled, Config.Mongo_Database, Config.Mongo_Username, Config.Mongo_Password)
        mongoDatabase = mongo.createDatabase("kplugin")
    }

    @JvmStatic
    fun disconnect() {
        sql.disconnect()
        mongo.disconnect()
    }

}