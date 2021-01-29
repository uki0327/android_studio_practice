package kr.ukinas.usingmariadb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.mariadb.jdbc.internal.util.constant.Version
import java.sql.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var conn: Connection? = null
        var stmt: Statement? = null
        var resultSet: ResultSet
        try {
            Class.forName("org.mariadb.jdbc.Driver")
            println("MariaDB Connector/J: " + Version.version + "\n")
            print("Connecting to DB...")
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://db정보",
                    "아이디", "비번")
            println("done.")
            stmt = conn.createStatement()
            resultSet = stmt.executeQuery("create table a (id int, name text)")
            println("\nList of Components:")
            while (resultSet.next()) {
                var name = resultSet.getString(1)
                println("name: " + name)
            }
        }
        catch (ex: SQLException) {
            ex.printStackTrace()
        }
        catch (ex: Exception) {
            ex.printStackTrace()
        }
        finally {
            try {
                if (stmt != null) {
                    conn?.close()
                }
            }
            catch (ex:SQLException) {}
            try {
                if (conn != null) {
                    conn.close()
                }
            }
            catch (ex: SQLException) {
                ex.printStackTrace()
            }
        }
    }
}

