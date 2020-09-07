import kotlinx.coroutines.*

fun main() {
    println("Start")
    //sequencialExecution()
    //coroutineExecution()
    playingRunBlocking()
    println("End")
}


//Runblocking
//execute new coroutine, blocking current thread until it finished execution
//
/*fun main() = runBlocking {
    println("Start")
    launch{ task1() }
    launch{ task2() }
    println("End")
}*/

/*
//best use with runblocking
fun main() = runBlocking {
    println("Start")
    launch{ task1New() }
    launch{ task2New() }
    println("End")
*/

//Using yield
/*fun main() = runBlocking {
    println("Start")
    //launch{ task1New() }
    //launch{ task2New() }

    //yield give priority to other thread,in this case give priority to this coroutine instead of runBlocking
    launch{
        println("Hello")
        delay(5000)
    }

    yield()
    println("End")

}*/

/*fun main() = runBlocking {
    println("Start")
    val data = async{ task1Async() } //Async fetch data
    println(data.await()) //await fun waiting for the coroutine finish, once it finishes it gets value and return
    launch{ task2() }
    println("End")
}*/


fun sequencialExecution(){
    task1()
    task2()
}

//without other scope
fun coroutineExecution(){
    GlobalScope.launch {
        task1()
    }
    GlobalScope.launch {
        task2()
    }

    //Thread.sleep(3000) for see print of tasks because work is in parallel and coroutine are in another thread
}

//Playing with runBlocking
fun playingRunBlocking(){
    //RunBlocking create new coroutine with own scope
    //And in coroutineExecution we use coroutine with Global Scope
    //We have one box with one coroutine and another box with two coroutine
    //This box cannot communicate each other!
    //NOT WORKING
    /*runBlocking{
        coroutineExecution()
    }*/
    //WORKING
    runBlocking{
        launch{ task1() }
        launch{ task2() }
    }
}

fun task1(){
    for(i in 1..200){
        Thread.sleep(10)
    }
    println("Task 1 finished")
}

fun task2(){
    for(i in 1..100){
        Thread.sleep(10)
    }
    println("Task 2 finished")
}

//For utilize at the best runBlocking (use suspending function with delay not thread sleep)
suspend fun task1New(){
    for(i in 1..200){
        delay(10)
    }
    println("Task 1 finished")
}

suspend fun task2New(){
    for(i in 1..100){
        delay(10)
    }
    println("Task 2 finished")
}

//Async use when we want a result from a coroutine
suspend fun task1Async() : String{
    for(i in 1..200){
        delay(10)
    }
    println("Task 1 finished")
    return "Task 1 finished"
}