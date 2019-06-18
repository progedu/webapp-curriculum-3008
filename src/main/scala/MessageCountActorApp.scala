import akka.actor.{Actor, ActorSystem, Props}

case object Count

class Printer extends Actor {
  var counter: Int = 0

  def receive = {
    case Count => {
      counter = counter + 1
      println(s"Count: $counter")}
  }
}

object MessageCountActorApp extends App {
  val system = ActorSystem("messageCount")

  val messageCountActor = system.actorOf(Props[Printer], "messageCountActor")

  for (i <- 1 to 10000) {
    messageCountActor ! Count
  }

  Thread.currentThread().join()
}
