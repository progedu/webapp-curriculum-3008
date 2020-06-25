import java.util.Date
import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

class MyActor extends Actor {
  val log = Logging(context.system, this)
  def recive = {
    case message: String =>{
      log.info(message)
      Thread.sleep(1000)
    }
    case _ =>
  }
}

object TooMuchMessageApp extends App {
  val system = ActorSystem("TooMuchMessageApp")
  val myActor = system.actorOf(Props[MyActor],"myAcctor")
  while (true){
    myActor ! new Date().toString
  }
}
