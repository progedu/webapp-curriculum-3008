import akka.actor.{Actor, ActorSystem, Props}

class MessageCountActor extends Actor {
  var count = 0

  override def receive: Receive = {
    case _ =>
      count += 1
      println(count)
  }
}

object MessageCountActorApp extends App {

  val system = ActorSystem("messageCountActorApp")

  val messageCountActor =
    system.actorOf(Props[MessageCountActor], "messageCountActor")

  for (i <- 1 to 1000) {
    messageCountActor ! "call"
  }

  Thread.currentThread().join()

}
