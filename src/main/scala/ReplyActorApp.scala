import akka.actor.{Actor, ActorSystem, Props}

class ReplyActor1 extends Actor {
  def receive = {
    case reply: String => {
      println(reply)
      sender ! "from actor1"
    }
  }
}

class ReplyActor2 extends Actor {
  def receive = {
    case reply: String => {
      println(reply)
      sender ! "from actor2"
    }
  }
}

object ReplyActorApp extends App {
  val system = ActorSystem("replyActorApp")

  val actor1 = system.actorOf(Props[ReplyActor1], "replyActor1")
  val actor2 = system.actorOf(Props[ReplyActor2], "replyActor2")

  actor2.tell("from actor1", actor1)
}
