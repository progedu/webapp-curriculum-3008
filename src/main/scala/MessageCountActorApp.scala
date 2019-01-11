import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging

class CountActor extends Actor {
  val log = Logging(context.system, this)

  var count = 0

  def receive = {
    case _ => {
      count += 1
      log.info(count.toString)
    }
  }
}

object MessageCountActorApp extends App {
  val system = ActorSystem("messageCountActorApp")

  val countActor = system.actorOf(Props[CountActor], "countActor")

  for (i <- 1 to 10000) {
    countActor ! "message"
  }

  Thread.sleep(1000)

  system.terminate()

}
