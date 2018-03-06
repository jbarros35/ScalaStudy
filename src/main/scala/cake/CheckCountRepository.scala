package cake

import java.sql.{Connection, Timestamp}

/**
  * A model for any kind of repository
  */
trait CheckCountRepository {

  def checkCountDAO:CheckCountDAO

  trait CheckCountDAO {
    def findall: List[CheckCount]
  }
}

/**
  * Implements how to interact with table using SQL query and retrieve data.
  * This is use pure JDBC routines
  */
trait CheckCountRepositoryJBDC extends CheckCountRepository {

  val connection:Connection

  def checkCountDAO = new CheckCountDAOImpl(connection)

  class CheckCountDAOImpl(val connection: Connection) extends CheckCountDAO {
    def findall: List[CheckCount] = {
      val result = connection.createStatement().executeQuery(
        """
          SELECT * FROM DBHMON.CHECK_COUNT
        """)
      new Iterator[CheckCount] {
        def hasNext = result.next()
        def next = new CheckCount(result.getLong("id"),
          result.getString("db_url"),
          result.getString("table_name"),
          result.getLong("cnt"),
          result.getTimestamp("start_dtm"),
          result.getTimestamp("end_dtm"),
          result.getString("err_msg"),
          result.getString("err_stacktrace")
        )
      }.toList
    }
  }
}

/**
  * Our case class for this sample.
  * @param id
  * @param dbURL
  * @param tableName
  * @param cnt
  * @param startDtm
  * @param endDtm
  * @param errorMsg
  * @param errStack
  */
case class CheckCount(id:Long, dbURL:String, tableName:String, cnt:Long, startDtm:Timestamp, endDtm:Timestamp, errorMsg:String, errStack:String)
