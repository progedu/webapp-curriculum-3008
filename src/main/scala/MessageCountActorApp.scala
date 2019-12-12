import akka.actor.{ActorSystem,Props,Actor}

//自らにメッセージが送られた回数をコンソールに出力するアクターを実装し、 1 から 10000 まで出力したあと、ActorSystem を終了させる
//エラーが出ないように ActorSystem を終了させる

class countPrintActor extends Actor{
  def receive = {
    case n:Int => println(n)
  }
}

object MessageCountActorApp extends App {
  val system = ActorSystem("printCount")
  val countPrinter = system.actorOf(Props[countPrintActor])
  for(i <- 1 to 10000){countPrinter ! i} //この後メインスレッドに制御が返ってきていない？
  println("finished") //←出力されない
  Thread.currentThread().join()
}
