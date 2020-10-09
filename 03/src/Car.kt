class Car(private val name: String,
          private val maxFuel: Int, private val maxSpeed: Int) {

    private var fuel = 0
    private var currentSpeed = 0


    fun refuel(newFuel : Int){
        fuel += newFuel
    }

    fun drive(){
        if(fuel == 0){
            println("i need to drink")
            return
        }

        for(speed in 0..maxSpeed){
            setSpeed(speed)
        }

        stop()
    }

    fun stop() {
        for(speed in maxSpeed downTo 0){
            setSpeed(speed)
        }

        println("finished : $name")
    }

    fun getFuel() : Int = fuel


    private fun setSpeed(speed: Int) {
        currentSpeed = speed
    }

    fun getSpeed(){

    }



}