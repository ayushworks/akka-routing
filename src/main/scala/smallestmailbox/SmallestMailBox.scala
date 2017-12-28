package smallestmailbox

import akka.actor.{Actor, ActorSystem, Props}
import akka.event.Logging
import akka.routing.SmallestMailboxPool

class SmallestMailBoxActor extends Actor {

  val logger = Logging(context.system, this)

  def receive = {
    case msg: String => logger.info(s"I ${self.path.name} Received string message $msg")
    case x => logger.info(s"Other than string received $x")
  }

}

object SmallestMailBox extends App {

  val actorSystem = ActorSystem("akka-routing")

  val router = actorSystem.actorOf(SmallestMailboxPool(5).props(Props[SmallestMailBoxActor]))

  for( i <- 1 to 15) {
    router ! s"Hello $i"
  }

}
