import akka.actor.{Actor, ActorSystem, Props}

class MessageActorA extends Actor {
  override def receive: Receive = {
    case "messageA" => {
      println("actorA")
      sender ! "messageB"
    }
  }
}

class MessageActorB extends Actor {
  override def receive: Receive = {
    case "messageB" => {
      println("actorB")
      sender ! "messageA"
    }
  }
}

object ReplyActorApp extends App {
  val system = ActorSystem("replyActor")
  val actorA = system.actorOf(Props[MessageActorA])
  val actorB = system.actorOf(Props[MessageActorB])

  actorA.tell("messageA", actorB)
}
