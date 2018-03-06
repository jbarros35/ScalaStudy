package h2

import java.math.BigInteger
import java.sql.Timestamp
import java.util.{Calendar, Date}

class CheckCountDAO[CheckCount] {
}

trait CheckCountComponent extends DaoSample[CheckCount]{

  def insert(checkCount:CheckCount) = {
    val sqlIns =
      """insert into dbhmon.check_count(db_url, table_name, cnt, start_dtm, end_dtm, err_msg, err_stacktrace)
                values (?, ?, ?, ?, ?, ?, ?)"""
    val stmtLogBegin = connection.connect().prepareStatement(sqlIns)
    stmtLogBegin.setString(1, checkCount.dbURL)
    stmtLogBegin.setString(2, checkCount.tableName)
    stmtLogBegin.setLong(3, checkCount.cnt)
    stmtLogBegin.setTimestamp(4, checkCount.startDtm)
    stmtLogBegin.setNull(5, java.sql.Types.TIMESTAMP)
    stmtLogBegin.setString(6, checkCount.errorMsg)
    stmtLogBegin.setString(7, checkCount.errStack)
    stmtLogBegin.executeUpdate()
    stmtLogBegin.close()
  }

  def update[CheckCount](checkCount:CheckCount) =  ???

  def findAll[CheckCount](checkCount:CheckCount):Set[CheckCount] = ???

  def delete(id:Long) = ???
}

case class CheckCount(id:Long, dbURL:String, tableName:String, cnt:Long, startDtm:Timestamp, errorMsg:String, errStack:String)

object Main {
  def main(args: Array[String]){
    val checkCount = new CheckCountDAO[CheckCount] with CheckCountComponent
    checkCount.insert(
      new CheckCount(1, "url", "table", 1000, new Timestamp(new Date().getTime), "error", "msg err")
    )
  }
}