package cake

trait CheckCountServiceComponent {

  def checkCountService:CheckCountService

  trait CheckCountService {
    def findAll: List[CheckCount]
  }
}

trait DefaultCheckCountServiceComponent extends CheckCountServiceComponent {
  this:CheckCountRepository =>

  def checkCountService = new DefaultCheckCountService

  class DefaultCheckCountService extends CheckCountService {
    def findAll: List[CheckCount] = checkCountDAO.findall
  }
}