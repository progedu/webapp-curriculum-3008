

import akka.actor.{Actor, ActorSystem, Inbox, Props}
import akka.event.Logging

import scala.concurrent.duration.DurationInt

class MessageCounter extends Actor {
  val log = Logging(context.system, this)
  var count: Int = 0

  def receive = {
    case _ => {
      count += 1
      println(s"I have received $count messeages.")
      if (count >= 10000) {
        context.system.terminate()
      }
    }
  }
}

object MessageCountActorApp extends App {
  val system = ActorSystem("messageCountActorApp")

  val counter = system.actorOf(Props[MessageCounter])

  for (i <- 1 to 10000) counter ! "message"
}
