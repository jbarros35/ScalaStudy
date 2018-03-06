package h2

import java.sql.{Connection, DriverManager, Timestamp}
import java.util.Calendar

object TestH2 {

  private def createDbStructure(conn: Connection): Unit = {

    val sql = """
      create schema if not exists dbhmon;

      set schema dbhmon;

      create table if not exists check_count (
        id int auto_increment primary key,
        db_url varchar(255) not null,
        table_name varchar(255) not null,
        cnt bigint,
        start_dtm timestamp not null,
        end_dtm timestamp,
        err_msg varchar,
        err_stacktrace varchar);"""
    val stmt = conn.createStatement()
    try {
      stmt.execute(sql)
    }
    finally {
      stmt.close
    }
  }

  private def insert(connSys:Connection) = {
    val sqlIns =
      """insert into dbhmon.check_count(db_url, table_name, cnt, start_dtm, end_dtm, err_msg, err_stacktrace)
                values (?, ?, ?, ?, ?, ?, ?)"""
    val stmtLogBegin = connSys.prepareStatement(sqlIns)
    val connData = connSys.getMetaData
    stmtLogBegin.setString(1, connData.getURL)
    stmtLogBegin.setString(2, "testTable")
    stmtLogBegin.setNull(3, java.sql.Types.BIGINT)
    stmtLogBegin.setTimestamp(4, new Timestamp(Calendar.getInstance().getTime().getTime()))
    stmtLogBegin.setNull(5, java.sql.Types.TIMESTAMP)
    stmtLogBegin.setNull(6, java.sql.Types.VARCHAR)
    stmtLogBegin.setNull(7, java.sql.Types.VARCHAR)
    stmtLogBegin.executeUpdate()
    stmtLogBegin.close()
  }

  def main(args: Array[String]): Unit = {
    Class.forName("org.h2.Driver")
    val conn: Connection = DriverManager.getConnection( "jdbc:h2:tcp://localhost/~/dbhmon", "sa", "" )
    try {
      createDbStructure(conn)
      insert(conn)
      println( "ok")
    }
    catch {
      case t: Throwable => {
        t.printStackTrace()
      }
    }
    finally {
      conn.close()
    }
  }
}
