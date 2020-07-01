import java.util.Date

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.{Logging, LoggingAdapter}

class LongDurationActor extends Actor {
  val log: LoggingAdapter = Logging(context.system, this)

  override def receive: Receive = {
    case message: String =>
      log.info(message)
      Thread.sleep(1000)
    case _ =>
  }
}

object TooMuchMessageApp extends App {

  val system = ActorSystem("tooMuchMessageApp")

  val longDurationActor =
    system.actorOf(Props[LongDurationActor], "longDurationActor")

  while (true) {
    longDurationActor ! new Date().toString
  }

}
