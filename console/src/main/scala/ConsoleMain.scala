import akka.actor.ActorSystem
import victorops.example.Console

object ConsoleMain {
  def main(args: Array[String]): Unit = {

    val system = ActorSystem("ConsoleActorSystem")
    val console = new Console(system)

    ammonite.Main(
      predef =
        """
          |println("Loading the VictorOps Example Console!")
          |import console.imports._
        """.stripMargin
    ).run(
      "console" -> console
    )
  }
}
