fun main(args: Array<String>){
   val list = listOf("","","")
   val map = mapOf(1 to "Monday", 2 to "Tuesday", 3 to "Wednesday")
   val set = setOf("one", "two", "three")

}


//sorting

fun sort(list: List<String>){
    list.sortedBy {
        //sort by first letter
        it.first()
    }

    //Use comparator

    //compara la lunghezza
    val comparator = Comparator { first: String, second : String -> first.length - second.length }
    list.sortedWith(comparator)
}

//Task Recap

//use map created in the beginning of lesson and create set of Int from it
//remove these items which are odd and print the number which is divisible by 4
fun recap(map : Map<Int,String>){
   val transfmap = map.map{
        it.key
    }.filter{ it % 2 == 0 }.find{ it % 4 == 0}

    println(transfmap)

    val items = map.keys.toMutableList()
    items.removeIf { it % 4 != 0 }
    println(items)

}

