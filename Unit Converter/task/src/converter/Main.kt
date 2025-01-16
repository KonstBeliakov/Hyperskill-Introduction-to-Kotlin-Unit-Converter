package converter


fun main() {
    val units: Map<Array<String>, Double> = mapOf(
        arrayOf("m", "meter", "meters") to 1.0,
        arrayOf("km", "kilometer", "kilometers") to 1000.0,
        arrayOf("cm", "centimeter", "centimeters") to 0.01,
        arrayOf("mm", "millimeter", "millimeters") to 0.001,
        arrayOf("mi", "mile", "miles") to 1609.35,
        arrayOf("yd", "yard", "yards") to 0.9144,
        arrayOf("ft", "foot", "feet") to 0.3048,
        arrayOf("in", "inch", "inches") to 0.0254
    )

    print("Enter a number and a measure: ")
    val line = readln()
    val number = line.split(" ")[0].toDouble()
    val entered_unit = line.split(" ")[1].lowercase()

    var unit_found = false

    for((unitArray, value) in units) {
        if (unitArray.contains(entered_unit.lowercase())) {
            println("""$number ${if (number == 1.0) unitArray[1] else unitArray[2]} is ${number * value} meters""")
            unit_found = true
            break
        }
    }

    if(!unit_found){
        println("Wrong input. Unknown unit $entered_unit")
    }
}
