package db

import org.jetbrains.exposed.sql.Database

object DatabaseService {

    fun connect() : Database {
        return Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver", user = "root", password = "")
    }

    @Suppress("UNUSED_PARAMETER")
    fun <T : Any> String.execAndMap(transform: (ResultSet) -> T): List<T> {
        val result = arrayListOf<T>()
        val query = this
        transaction {
            exec(query) { rs ->
                while (rs.next()) {
                    result += transform(rs)
                }
            }
        }
        return result
    }

}

