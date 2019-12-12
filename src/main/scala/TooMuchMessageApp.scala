import akka.actor.{ActorSystem,Actor,Props}
import akka.event.Logging
import java.util.Date

class overThousandSecondsActor extends Actor {
  val log = Logging(context.system,this)
  def receive = {
    case s:String => {
      log.info(s)
      Thread.sleep(1000)
    }
  }
}

object TooMuchMessageApp extends App {
  val system = ActorSystem("tooMuchMessageApp")
  val myActor = system.actorOf(Props[overThousandSecondsActor],"myActor")
  while(true){
    myActor ! new Date().toString
  }
}
