import java.util.Date

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

class MyActor extends Actor {
  val log = Logging(context.system, this)

  def receive = {
    case message: String => {
      log.info(message)
      Thread.sleep(1000)
    }
    case _ =>
  }
}

object TooMuchMessageApp extends App {

  val system = ActorSystem("actorStudy")

  val myActor = system.actorOf(Props[MyActor], "myActor")

  while (true) {
    myActor ! new Date().toString
  }
}