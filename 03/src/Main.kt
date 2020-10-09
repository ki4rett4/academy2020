class Greeter(val name : String){

    internal fun greet(){
        println("Hello world $name")
    }

    /*
    //Main not extend Greeter so error
    protected fun greet(){
        println("Hello world $name")
    }
    * */

    /*
    //private not visibile in Main so error
    private fun greet(){
        println("Hello world $name")
    }
    * */

    fun greet1(){
        greet()
    }

}


data class CarData(private val name: String,
                   private val maxFuel: Int, private val maxSpeed: Int)

fun main(){
    val greeter = Greeter(name = "dsadsdsa")
    greeter.greet()

    val car1 = Car(name = "Beetle",maxFuel = 50, maxSpeed = 150)
    val car2 = Car(name = "Golf",maxFuel = 50, maxSpeed = 250)
    car1.refuel(50)
    car2.refuel(25)
    car1.drive()
    car2.drive()


    val car3 = CarData(name = "Beetle",maxFuel = 50, maxSpeed = 150)
    val car4 = car3.copy(name = "Golf")

    println(car1)
    println(car3)
    println(car4)


}