import java.sql.*

object DatabaseService {
    const val url = "jdbc:postgresql://100.101.102.103:5432/capri_int"
    private const val username = "xxx"
    private const val password = "xxx"

    fun getSkillShortNameById(id: Int): String {
        return DatabaseService.getSingleValueQuery("SELECT short_name from capri_backend.skill where id = $id")
    }

    fun getSingleValueQuery(query: String): String {
        val conn: Connection = openConnection()
        val stmt: Statement = conn.createStatement()
        val resSet: ResultSet = stmt.executeQuery(query)
        return if (resSet.next()) {
            resSet.getString(1)
        } else throw AssertionError("SQL response is empty")
    }

    private fun openConnection(): Connection {
        return try {
            DriverManager.getConnection(url, username, password)
        } catch (e: SQLException) {
            println("Connection Failed")
            throw SQLException()
        }
    }

}