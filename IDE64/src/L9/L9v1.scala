package L9

import scala.runtime.Nothing$

object L9v1 {
  def main(args: Array[String]) = {
    println("Cw1<-in code");
    class Container[A](var a:A){
      def getContent:A=a;
      def applyFunction(fun:A=>A)=a=fun(a);
    }//nie jestem pewien czy dobrze rozumiem polecenie - jeżeli nie to się poprawi
    println("Cw2");
    trait Maybe[A]{}
    class No[B] extends Maybe[Nothing]{
      def applyFunction():No[B]=No.this;//czy to ma znaczenie które to No?
      def getOrElse(e:B):B=e;
    }
    class Yes[A](val a:A) extends Maybe[A]{
      def applyFunction(a:A):Yes[A]=new Yes(a);
      def getOrElse(e:A):A=a;
    }

    val no = new No[String];
    val yes = new Yes[Int](10)

    println("no->"+no.isInstanceOf[Maybe[Int]])
    println("yes->"+yes.isInstanceOf[Maybe[Int]])

    println("Cw3");
    //3.	(0.2 pkt) Rozszerz rozwiązanie zadania 3 o funkcję applyFunction typu A=>R
    //to zadanie rekurencyjne? jeżeli tak to doceniam humor. rozszerzenie zadania 2.
    //rozwiązanie wyżej
    println("no->"+no.applyFunction())
    println("yes->"+yes.applyFunction(5).a)

    println("Cw4");
    //rozwiązanie wyżej
    println("no->"+no.getOrElse("None"))
    println("yes->"+yes.getOrElse(0))
  }

}
