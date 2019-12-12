import akka.actor.{ActorSystem,Props,Actor}
//お互いにメッセージを返信し合いその内容をコンソールに出力し続ける 2 つのアクターを実装
class FirstActor extends  Actor{
  private[this] val message = "I'm first!"
  def receive = {
    case _ => {
      println(message)
      Thread.sleep(100)
      sender ! message
    }
  }
}
class SecondActor extends  Actor{
  private[this] val message = "I'm second!"
  def receive = {
    case _ => {
      println(message)
      Thread.sleep(100)
      sender ! message
    }
  }
}

object ReplyActorApp extends App {
  val system = ActorSystem("talkActor")
  val first = system.actorOf(Props[FirstActor],"first")
  val second = system.actorOf(Props[SecondActor],"second")
  first.tell("start",second)
}
