package lesson07_coroutine

import kotlinx.coroutines.*


//start
/*fun main() {
    runBlocking {
        val resultOne = "Result1"
        val resultTwo = "Result2"

        println("Starting in Thread ${Thread.currentThread().name}")
        println("RESULTS: $resultOne $resultTwo")
        println("Ending in Thread ${Thread.currentThread().name}")
    }
}*/

fun main() {
    //launch isn't blocking execution
    runBlocking {
        var resultOne = "Result1"
        var resultTwo = "Result2"

        //1 - launch
        /*launch { function1() }
        launch { function2() }*/

        //2 - withContext() - sequencial
        /*resultOne = withContext(Dispatchers.IO){function1()}
        resultTwo = withContext(Dispatchers.IO){function2()}*/

        //3 - replace withContext with launch
        /*launch(Dispatchers.IO){function1()}
        launch(Dispatchers.IO){function2()}
         */

        //4 - replace launch with async
        //coroutine launch sequentially
        /*val resultOneAsync = async(Dispatchers.IO){function1()}
        val resultTwoAsync = async(Dispatchers.IO){function2()}*/

        println("Starting in Thread ${Thread.currentThread().name}")

        //5 - Playing with coroutine jobs
        /*val job = launch {
            //add try-finally to see that job.join wait job finishing
            try {
                repeat(300){
                    iteration ->
                    println("Job is waiting... $iteration")
                    delay(50L)
                }
            }finally {
                println("Job Finishing")
            }
        }

        delay(2000L)
        job.cancel()
        //6 - with join
        job.join()*/
        //6 - or use only job.cancelAndJoin()

        //8 - withTimeoutOrNull
        val job = withTimeoutOrNull(2000L){
        //7 - withTimeout()
        // withTimeout(2000L){
        //add try-finally to see that job.join wait job finishing
            try {
                //repeat(10){ //not reach timeout
                repeat(300){
                    iteration ->
                    println("Job is waiting... $iteration")
                    delay(50L)
                }
            }finally {
                println("Job Finishing")
            }
        }

        //8 - withTimeoutOrNull
        if(job == null){
            println("job is null!!!")
        }else{
            println("job isn't null!!!")
        }
        // First print of function then results
        //println("RESULTS: ${resultOneAsync.await()} ${resultTwoAsync.await()}")  //async coroutine

        //launch will continue execution so first print results and at the end prints inside functions
        println("RESULTS: $resultOne $resultTwo")
        //we waiting for both result and then the execution of app continues.

        println("Ending in Thread ${Thread.currentThread().name}")
    }

}

suspend fun function1(): String {
    delay(1000)
    val result = "function1 ${Thread.currentThread().name}"
    println(result)
    return result
}

suspend fun function2(): String {
    delay(100)
    val result = "function2 ${Thread.currentThread().name}"
    println(result)
    return result
}