package eu.vortexgg.kplugin.user

import com.eatthepath.uuid.FastUUID
import eu.vortexgg.kplugin.db.DbManager
import eu.vortexgg.kplugin.db.DbManager.Sql_Database_Name
import java.util.*

class KPlayer(var uuid: UUID, var name: String) {

    fun load() {
        val identifier = FastUUID.toString(uuid)
        val rs = DbManager.sql.connection.prepareStatement("SELECT * FROM $Sql_Database_Name WHERE UUID='$identifier'").executeQuery()
        if(rs.next()) {

        } else {
            DbManager.sql.connection.prepareStatement("INSERT INTO $Sql_Database_Name(UUID, NAME) VALUE($identifier, $name)").executeUpdate()
        }
    }

    fun save() {
        DbManager.sql.connection.prepareStatement("UPDATE $Sql_Database_Name SET NAME='$name' WHERE UUID='${FastUUID.toString(uuid)}'").executeUpdate()
    }

    fun register() {
        players[uuid] = this
    }

    fun unregister() {
        players.remove(uuid)
    }

    companion object  {
        @JvmStatic
        val players: MutableMap<UUID, KPlayer> = mutableMapOf()
    }
}