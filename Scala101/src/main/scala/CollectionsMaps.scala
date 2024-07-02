object CollectionsMaps extends App {
  
  val testSeq:Seq[String] = Seq("a","b","c")
  val letter = testSeq(0)
  
  val testMap: Map[Int,String] = Map(10->"dog",11->"cat",12->"leopard") // dictionary (python) - key value pairs

  // maps NOT Map which is a dictionary - this one iterates through every item in a collection and performs a function on it
  // for example instead of for loops you use maps and flatmaps

  val testSeqMaps = Seq(1,2,3)

  val doubledSeq = testSeqMaps.map{
    num => num * 2 // num is a placeholder name can call it whatever you want
  }

  // filter: iterates through every item in a collection and removes it if a condition is not met

  val testSeqFilter= Seq(1,2,3,4,5)
  val filteredSeq = testSeqFilter.filter{
    num => num > 3
  } // this would return you a new sequence made up of only 4 and 5

    // exists: iterates through every item in a collection and return true if a condition is met atleast once

    val testSeqExists = Seq(1,3,5)
    val outcode = testSeqExists.exists{
      num => num > 3
    } // this will return true if atleast 1 item in this sequence matches the condition otherwise will return false if none of the items match the condition

    








}
