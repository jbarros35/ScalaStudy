package cake
import java.sql.{Connection, DriverManager}

object TestCheckCount {
  def main(args: Array[String]){
    val checkCountService = new DefaultCheckCountServiceComponent with CheckCountRepositoryJBDC {
      val connection: Connection = {
        Class.forName("org.h2.Driver")
        DriverManager.getConnection( "jdbc:h2:tcp://localhost/~/dbhmon", "sa", "" )
      }
    }
    checkCountService.checkCountService.findAll.map(x => println(s"checkcount: ${x.id} ${x.dbURL}"))
  }
}
