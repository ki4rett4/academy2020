import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

fun main(){
    println("Start")
    //1 - suspend function
    //tasksSuspend().forEach { println(it) }
    //4 - flow
    runBlocking {

        //5 - prove that not blocking
        /*launch {
            for(i in 1..5){
                delay(100)
                println("I'm not blocked $i")
            }
        }

        taskFlow().collect { println(it) } //values print one by one*/

        //6 - call two times
        /*val task = taskFlow()
        task.collect { println(it) }
        task.collect { println("second time $it") }*/

        //7 - cancellation
        /*val task = taskFlow()
        task.collect { println(it) }
        withTimeoutOrNull(250){
            task.collect { println("second time $it") }
        }*/

        //8 - flow builder
        /*taskFlowBuilder().collect { println(it) }
        taskFlowBuilder2().collect { println(it) }*/

        //9 - intermediate builder op
        // call suspend task
        (1..3).asFlow().map {
            task1()
        }.collect { println(it) }

        //filter
        (1..3).asFlow().onEach { delay(100) }.filter { number -> number %2 == 1 }.collect { println(it) }

        (1..3).asFlow().onEach { delay(100) }.transform {
            emit(it)
            emit("my extra msg $it")
        }.collect { println(it) }

        //10 - add buffer
        val time = measureTimeMillis {
            simple()
                .buffer() // buffer emissions, don't wait
                .collect { value ->
                    delay(300) // pretend we are processing it for 300 ms
                    println(value)
                }
        }
        println("Collected in $time ms")


    }
    println("End")
}

//1 - suspend function
suspend fun task1():String{
    delay(2000)
    return "task1 finished"

}

suspend fun task2():String{
    delay(1000)
    return "task2 finished"
}

fun tasksSuspend() : List<String>{
    val list = mutableListOf<String>()
    runBlocking {
        //2 - async
        /*val data = async {
            task1()
        }
        list.add(data.await())
        val data2 = async {
            task2()
        }
        list.add(data2.await())*/

        //3 - launch
        launch {
            list.add(task1())
            list.add(task2())
        }
    }

    return list
}

//4 - flow
fun taskFlow(): Flow<String> =
    flow{
        for(i in 1..5){
            delay(100)
            emit("task $i finished")
        }
    }

//8 - flow builders
fun taskFlowBuilder() : Flow<Int>{
    return flowOf(1,2,3).onEach {
        delay(100)
    }
}

fun taskFlowBuilder2() : Flow<Int>{
    return (1..3).asFlow().onEach {
        delay(100)
    }
}

//10 - add buffer
fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100) // pretend we are asynchronously waiting 100 ms
        emit(i) // emit next value
    }
}