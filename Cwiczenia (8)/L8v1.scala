package L8

object L8v1 {
  def main(args: Array[String]) = {
    var dniTyg = List("Poniedziałek","Wtorek","Środa","Czwartek","Piątek","Sobota","Niedziela");

    println("Cw1");
    dniTyg.foreach(e=>print(e+"->"+Cw1(e)+","));
    print("łubudubu"+"->"+Cw1("łubudubu"));
    println();

    println("Cw2");
    class KontoBankowe(val money:Double = 0){
      def wplata()={}
      def wyplata()={}
    }
    val k21 = new KontoBankowe();
    val k22 = new KontoBankowe(100);
    println(k21.money+" vs "+k22.money)

    println("Cw3");
    val osC31 = new Osoba("Adam","Adamczuk");
    val osC32 = new Osoba("Brand","Branderson");
    val osC33 = new Osoba("Celia","Ceceliuk");
    val osC34 = new Osoba("Dimitr","Duma");
    val osC35 = new Osoba("Ewa","Duma");
    List(osC31,osC32,osC33,osC34,osC35)
      .foreach(e=>println(e.imie+" "+e.nazwisko+"->"+Cw3(e)))

    println("Cw4");
    println("3,v+v->",Cw4(3,Cw4mini1))
    println("3,v*v",Cw4(3,Cw4mini2))

    println("Cw5");
    case class Osoba5(val _imie:String, val _nazwisko:String, val _podatek:Double){

    }
    trait Student{
      def podatek:Double = 0.0;
    }
    trait Pracownik{
      var _pensja : Double
      def pensja = _pensja;
      def pensja_=(value:Double):Unit=_pensja=value

      def podatek = _pensja*0.2;
    }
    trait Nauczyciel extends Pracownik {
      override def podatek = _pensja*0.1;
    }

    val student = new Osoba5("A","B",-10) with Student;
    println(student+"->"+student.podatek)
    //===================================================================================================================
    val pracownik = new Osoba5("B","C",-10) with Pracownik {
      override var _pensja: Double = 100//jeżeli tego explicite nie sprecyzować to kompilator daje błąd.
    };
    println(pracownik+"->"+pracownik.podatek)

    val nauczyciel = new Osoba5("A","B",-10) with Nauczyciel {
      override var _pensja: Double = 100//jeżeli tego explicite nie sprecyzować to kompilator daje błąd.
    };
    println(nauczyciel+"-mix Student-Pracownik->"+nauczyciel.podatek)

    val mutant1 = new Osoba5("B","C",-10) with Student with Pracownik {
      override var _pensja: Double = 100;
      override def podatek:Double=super[Pracownik].podatek;//jeżeli tego explicite nie sprecyzować to kompilator daje błąd.
    }
    println(mutant1+"-mix Pracownik-Student->"+mutant1.podatek)

    val mutant2 = new Osoba5("B","C",-10) with Pracownik with Student {
      override var _pensja: Double = 100;
      override def podatek:Double=super[Student].podatek;//jeżeli tego explicite nie sprecyzować to kompilator daje błąd.
    };
    println(mutant2+"->"+mutant2.podatek)
    //może to kwestia wersji scala - tak czy owak override implicite nie działa więc jest explicite
    //===================================================================================================================
  }

  def Cw1(day:String): String = {
    var work = List("Poniedziałek","Wtorek","Środa","Czwartek","Piątek");
    var free = List("Sobota","Niedziela");
    var repWork="Praca";
    var repFree="Weekend";
    var repUkn="Nie ma takiego dnia";

    day match{
      case e if work.contains(e) => repWork;
      case e if free.contains(e) => repFree;
      case _=> repUkn;
    }
  }

  class Osoba(val imie:String, val nazwisko:String){

  }
  def Cw3(person:Osoba):String={
    person match {
      case e if e.nazwisko.equals("Duma") => "Dumnie witamy, "+e.imie;
      case e if List("Brand","Celia").contains(e.imie) => "Witamy dom "+e.nazwisko;
      case _ => "Cze, sup?"
    }
  }

  def Cw4mini1(v:Int):Int={
    v+v
  }
  def Cw4mini2(v:Int):Int={
    v*v
  }
  def Cw4(v:Int,fun:Int=>Int):Int={
    fun(fun(fun(v)))
  }


}
