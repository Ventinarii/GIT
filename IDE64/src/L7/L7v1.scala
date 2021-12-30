package L7

import scala.annotation.tailrec

object L7v1 {
  def main(args: Array[String]) = {
    var dniTyg = List("Poniedziałek","Wtorek","Środa","Czwartek","Piątek","Sobota","Niedziela");

    println("Cw1");
    println(Cw1a(dniTyg));
    println(Cw1b(dniTyg));
    println(Cw1c(dniTyg));

    println("Cw2");
    println(Cw2a(dniTyg));
    println(Cw2b(dniTyg));

    println("Cw3");
    println(Cw3(dniTyg));

    println("Cw4");
    println(Cw4a(dniTyg));
    println(Cw4b(dniTyg));
    println(Cw4c(dniTyg));

    println("Cw5");
    var map = Map("Tv"->9000.0,"Ak47"->19000.0,"World Peace"->1.0,"GenericValue"->100.0);
    println(map);
    var mapUpdated = map.map{a=>(a._1,a._2*0.9)};
    println(mapUpdated);

    println("Cw6");
    val t = (3,"abc",5.5);
    println(Cw6(t));

    println("Cw7");
    println(map.get("Tv"));
    println(map.get("dunno"));

    println("Cw8");
    val ints = List(0,10,-5,-4,-3,-2,-1,0,1,2,3,4,5,-10,0);
    println(ints);
    println(Cw8(ints));

    println("Cw9");
    println(Cw9(ints));

    println("Cw10");
    val doubles = List(-10.0,-5.0,-1.0,0.0,1.0,12.0,20.0);
    println(doubles);
    println(Cw10(doubles));
  }

  def Cw1a(dni:List[String]): String = {
    var suma = "";
    suma = suma + dni.head;
    for(e <- dni.tail){
      suma = suma + "," + e;
    }
    return suma
  }
  def Cw1b(dni:List[String]): String = {
    var suma = "";
    suma = suma + dni.head;
    for(e <- dni.tail if e.startsWith("P")){
      suma = suma + "," + e;
    }
    return suma
  }
  def Cw1c(dni:List[String]): String = {
    var suma = "";
    suma = suma + dni.head;
    var tail = dni.tail
    while(tail.size!=0){
      suma = suma + "," + tail.head;
      tail=tail.tail;
    }
    return suma;
  }

  def Cw2a(dni:List[String]): String = {
    if(1==dni.length) return dni.head;
    return dni.head + "," + Cw2a(dni.tail);
  }
  def Cw2b(dni:List[String]): String = {
    if(1==dni.length) return dni.head;
    return Cw2b(dni.tail) + "," + dni.head;
  }

  def Cw3(dni:List[String]): String = {
    @tailrec
    def iter(days:List[String],result:String):String=
      if(days.size==0) result;
      else iter(days.tail,result+","+days.head);
    return iter(dni.tail,dni.head);
  }

  def Cw4a(dni:List[String]): String = {
    return dni.tail.foldLeft(dni.head)(_+","+_);
  }
  def Cw4b(dni:List[String]): String = {
    return dni.foldRight(""){(a,b)=>
      if(b.isEmpty)a
      else a+","+b}
  }
  def Cw4c(dni:List[String]): String = {
    return dni.foldLeft(""){(a,b)=>
      if(!b.startsWith("P"))a
      else
        if(a.isEmpty)b
        else a+","+b}
  }

  def Cw6[A,B,C](t:Tuple3[A,B,C]): String = {
    return t._1+","+t._2+","+t._3;
  }

  def Cw8(v:List[Int]): List[Int] = {
    if(v.tail.isEmpty){
      if(v.head==0)
        return List();
      else
        return List(v.head);
    }else{
      if(v.head==0)
        return Cw8(v.tail);
      else
        return v.head::Cw8(v.tail);
    }
  }

  def Cw9(v:List[Int]): List[Int] = {
    v map (_+1);
  }

  def Cw10(v:List[Double]): List[Double] = {
    v filter(e=>(-5.0)<=e&&e<=12.0) map (_.abs)
  }
}