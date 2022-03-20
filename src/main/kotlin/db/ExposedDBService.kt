package db

import io.qameta.allure.Step
import java.sql.*

object ExposedDBService {
    const val url = "jdbc:postgresql://100.101.102.103:5432/capri_int"
    private const val username = "xxx"
    private const val password = "xxx"
    const val dbName = "capri_backend.contingent"

    object Cont : Table(dbName) {
        // NOT ALL FIELDS DESCRIBED HERE BECAUSE I DO NOT KNOW THE WAY TO DESCRIBE array column like Double[]
        val id = integer("id")
        val shortName = varchar("short_name", 50)
        val longName = varchar("long_name", 100)
        val description = varchar("description", 2048)
        val kadisArea = varchar("standard_kadisarea", 2048)
        val detailedExamination = bool("detailed_check_on_request")
        val maximizeKapa = bool("maximize_contiguous_capacity")
        val limitValueCapacityQuotient = double("threshold_capacity_quotient")
        val expandAvailability = integer("extend_availability")
        val assessTimeWindow = bool("rate_time_slot")
        val threshold = double("threshold_value")
        val weightSkill = integer("skill_booking_order")
        val weightGeography = integer("geography")
        val weightUtilization = integer("balanced_utilization")
        val remainingWeightAvailability = integer("maximize_remaining_availability")
        val totalAvailabilityPolynomialDegree = integer("polynomial_degree").nullable()
    }

    fun isExists(): Boolean {
        DatabaseService.connect()
        val param1 = "1"
        val queryString = "SELECT * from $dbName" +
                " where polynomial_degree " + (if (param1 == "") "IS NULL" else "= '${param1}'") +
        return transaction {
            addLogger(StdOutSqlLogger)
            queryString.execAndMap { it.next() }.isNotEmpty()
        }
    }

    fun isExists(shortName: String): Boolean {
        DatabaseService.connect()
        return transaction {
            addLogger(StdOutSqlLogger)
            val query = Cont.select { Cont.shortName eq shortName }
            assertThat(
                    "There are more than one contingent with first name = \"$shortName\"",
                    query.count(),
                    lessThan(2)
            )
            return@transaction query.singleOrNull() != null
        }
    }

    fun getId(shortName: String): Int {
        DatabaseService.connect()
        return transaction {
            addLogger(StdOutSqlLogger)
            val query = Cont.select { Cont.shortName eq shortName }
            assertThat(
                    "There are more than one contingent with first name = \"$shortName\"",
                    query.count(),
                    lessThan(2)
            )
            return@transaction query.single()[Cont.id]
        }
    }

    fun getNumberOfRecords(): Long {
        DatabaseService.connect()
        return transaction {
            addLogger(StdOutSqlLogger)
            return@transaction Cont.selectAll().count()
        }
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