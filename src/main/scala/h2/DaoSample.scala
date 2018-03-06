package h2

import java.sql.{Connection, DriverManager}

trait DaoSample[T] {

  val connection = Conn

  def insert(obj:T)

  def update[T](obj:T)

  def findAll[T](obj:T):Set[T]

  def delete(id:Long)

}

object Conn {

  def executeUpdate(sql:String, conn:Connection): Unit = {
    val stmt = conn.prepareStatement(sql)
    stmt.executeUpdate()
    stmt.close()
  }

  def connect():Connection = {
    Class.forName("org.h2.Driver")
    DriverManager.getConnection( "jdbc:h2:tcp://localhost/~/dbhmon", "sa", "" )
  }

  def closeConnection(conn:Connection): Unit = {
    if (conn != null && !conn.isClosed) conn.close()
  }

}