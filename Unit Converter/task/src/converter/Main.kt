package converter

class Unit(val name: String, val nameMany: String, val value: Double, val type: String)

fun format(unit:Unit, number: Double): String{
    return "$number ${if (number == 1.0) unit.name else unit.nameMany}"
}

fun main() {
    val m = Unit("meter", "meters", 1.0, "length")
    val km = Unit("kilometer", "kilometers", 1000.0, "length")
    val cm = Unit("centimeter", "centimeters", 0.01, "length")
    val mm = Unit("milimeter", "milimeters", 0.001, "length")
    val mi = Unit("mile", "miles", 1609.35, "length")
    val yd = Unit("yard", "yards", 0.9144, "length")
    val ft = Unit("foot", "feet", 0.3048, "length")
    val inch = Unit("inch", "inches", 0.0254, "length")

    val g = Unit("gram", "grams", 1.0, "mass")
    val kg = Unit("kilogram", "kilograms", 1000.0, "mass")
    val mg = Unit("milligram", "milligrams", 0.001, "mass")
    val lb = Unit("pound", "pounds", 453.592, "mass")
    val oz = Unit("ounce", "ounces", 28.3495, "mass")

    val undefinedUnit = Unit("???", "???", 0.0, "???")

    val units: Map<String, Unit> = mapOf(
        "m" to m, "meter" to m, "meters" to m,
        "km" to km, "kilometer" to km, "kilometers" to km,
        "cm" to cm, "centimeter" to cm, "centimeters" to cm,
        "mm" to mm, "millimeter" to mm, "millimeters" to mm,
        "mi" to mi, "mile" to mi, "miles" to mi,
        "yd" to yd, "yard" to yd, "yards" to yd,
        "ft" to ft, "foot" to ft, "feet" to ft,
        "in" to inch, "inch" to inch, "inches" to inch,

        "g" to g, "gram" to g, "grams" to g,
        "kg" to kg, "kilogram" to kg, "kilograms" to kg,
        "mg" to mg, "milligram" to mg, "millirams" to mg,
        "lb" to lb, "pound" to lb, "pounds" to lb,
        "oz" to oz, "ounce" to oz, "ounces" to oz
    )

    while(true) {
        print("Enter what you want to convert (or exit): ")
        val line = readln()

        if (line == "exit") break

        val numberFrom = line.split(" ")[0].toDouble()
        val unitFrom = line.split(" ")[1].lowercase()
        val unitTo = line.split(" ")[3].lowercase()

        if(units.containsKey(unitFrom) && units.containsKey(unitTo)){
            if(units[unitFrom]?.type == units[unitTo]?.type){
                val numberTo = units[unitFrom]!!.value / units[unitTo]!!.value
                println("${format(units[unitFrom]!!, numberFrom)} is ${format(units[unitTo]!!, numberTo)}")
            }
        }else {
            val unitName1 = units.getOrDefault(unitFrom, undefinedUnit).nameMany
            val unitName2 = units.getOrDefault(unitTo, undefinedUnit).nameMany
            println("Conversion from $unitName1 to $unitName2 is impossible")
        }
    }
}
