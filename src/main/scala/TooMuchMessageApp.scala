import java.util.Date

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

class TooMuchMessageActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case message: String => {
      Thread.sleep(1000)
      log.info(message)
    }
    case _ =>
  }
}

object TooMuchMessageApp extends App {
  val system = ActorSystem("TooMuchMessageApp")

  val myActor = system.actorOf(Props[TooMuchMessageActor], "tooMuchMessageActor")

  while(true) {
    myActor ! new Date().toString
  }

  Thread.currentThread().join()
}