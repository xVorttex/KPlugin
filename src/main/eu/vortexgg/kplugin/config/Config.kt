package eu.vortexgg.kplugin.config

import eu.vortexgg.kplugin.KPlugin

object Config {
    @JvmStatic private val Config = KPlugin.instance.config
    @JvmStatic val Mongo_Host = Config.getString("MONGO.HOST")
    @JvmStatic val Mongo_Port = Config.getInt("MONGO.PORT")
    @JvmStatic val Mongo_Auth_Enabled = Config.getBoolean("MONGO.AUTH_ENABLED")
    @JvmStatic val Mongo_Database = Config.getString("MONGO.DATABASE")
    @JvmStatic val Mongo_Username = Config.getString("MONGO.USERNAME")
    @JvmStatic val Mongo_Password = Config.getString("MONGO.PASSWORD")

    @JvmStatic val Sql_Host = Config.getString("SQL.HOST")
    @JvmStatic val Sql_Port = Config.getInt("SQL.PORT")
    @JvmStatic val Sql_Auth_Enabled = Config.getBoolean("SQL.AUTH_ENABLED")
    @JvmStatic val Sql_Database = Config.getString("SQL.DATABASE")
    @JvmStatic val Sql_Username = Config.getString("SQL.USERNAME")
    @JvmStatic val Sql_Password = Config.getString("SQL.PASSWORD")
}