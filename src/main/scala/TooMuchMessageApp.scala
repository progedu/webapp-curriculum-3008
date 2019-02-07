import java.util.Date

import akka.actor.{Actor, ActorSystem, Props}

import scala.concurrent.duration._

class SlowProcessActor extends Actor {

  def receive = {
    case str: String =>
      Thread.sleep(1000)
      println(str)
  }
}

object TooMuchMessageApp extends App {
  val system = ActorSystem("TooMuchMessageApp")

  val actor = system.actorOf(Props[SlowProcessActor], "slowActor")

  while(true) {
    actor ! new Date().toString
  }

}
