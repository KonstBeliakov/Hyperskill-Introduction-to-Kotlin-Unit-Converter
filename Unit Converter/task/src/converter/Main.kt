package converter

enum class Unit(val nameOne: String, val nameMany: String, val value: Double, val type: String){
    M("meter", "meters", 1.0, "length"),
    KM("kilometer", "kilometers", 1000.0, "length"),
    CM("centimeter", "centimeters", 0.01, "length"),
    MM("milimeter", "milimeters", 0.001, "length"),
    MI("mile", "miles", 1609.35, "length"),
    YD("yard", "yards", 0.9144, "length"),
    FT("foot", "feet", 0.3048, "length"),
    INCH("inch", "inches", 0.0254, "length"),

    G("gram", "grams", 1.0, "mass"),
    KG("kilogram", "kilograms", 1000.0, "mass"),
    MG("milligram", "milligrams", 0.001, "mass"),
    LB("pound", "pounds", 453.592, "mass"),
    OZ("ounce", "ounces", 28.3495, "mass"),

    C("degree Celsius", "degrees Celsius", 1.0, "temperature"),
    F("degree Fahrenheit", "degrees Fahrenheit", 1.0, "temperature"),
    K("kelvin", "kelvins", 1.0, "temperature"),

    UNDEFINED("???", "???", 0.0, "???")
}

fun convertTemperature(unitFrom: Unit, numberFrom: Double, unitTo: Unit): Double{
    return when{
        unitFrom == Unit.C && unitTo == Unit.F -> (numberFrom - 32) * (5/9)
        unitFrom == Unit.F && unitTo == Unit.C -> numberFrom * (9/5) + 32
        unitFrom == Unit.K && unitTo == Unit.C -> numberFrom - 273.15
        unitFrom == Unit.C && unitTo == Unit.K -> numberFrom + 273.15
        unitFrom == Unit.F && unitTo == Unit.K -> (numberFrom + 459.67) * (5/9)
        else -> numberFrom * (9/5) - 459.67
    }
}

fun format(unit:Unit, number: Double): String{
    return "$number ${if (number == 1.0) unit.nameOne else unit.nameMany}"
}

fun main() {
    val units: Map<String, Unit> = mapOf(
        "m" to Unit.M, "meter" to Unit.M, "meters" to Unit.M,
        "km" to Unit.KM, "kilometer" to Unit.KM, "kilometers" to Unit.KM,
        "cm" to Unit.CM, "centimeter" to Unit.CM, "centimeters" to Unit.CM,
        "mm" to Unit.MM, "millimeter" to Unit.MM, "millimeters" to Unit.MM,
        "mi" to Unit.MI, "mile" to Unit.MI, "miles" to Unit.MI,
        "yd" to Unit.YD, "yard" to Unit.FT, "yards" to Unit.FT,
        "ft" to Unit.FT, "foot" to Unit.FT, "feet" to Unit.FT,
        "in" to Unit.INCH, "inch" to Unit.INCH, "inches" to Unit.INCH,

        "g" to Unit.G, "gram" to Unit.G, "grams" to Unit.G,
        "kg" to Unit.KG, "kilogram" to Unit.KG, "kilograms" to Unit.KG,
        "mg" to Unit.MG, "milligram" to Unit.MG, "millirams" to Unit.MG,
        "lb" to Unit.LB, "pound" to Unit.LB, "pounds" to Unit.LB,
        "oz" to Unit.OZ, "ounce" to Unit.OZ, "ounces" to Unit.OZ,

        "k" to Unit.K, "kelvin" to Unit.K, "kelvins" to Unit.K,
        "c" to Unit.C, "degree_Celsius" to Unit.C, "degrees_Celsius" to Unit.C,
        "dc" to Unit.C, "celsius" to Unit.C,
        "degree_Fahrenheit" to Unit.F, "degrees_Fahrenheit" to Unit.F, "fahrenheit" to Unit.F,
        "df" to Unit.F, "f" to Unit.F
    )

    while(true) {
        print("Enter what you want to convert (or exit): ")
        var line = readln()

        if (line == "exit") break

        line = line.replace("degree Celsius", "degree_Celsius")
        line = line.replace("degrees Celsius", "degrees_Celsius")

        line = line.replace("degree Fahrenheit", "degree_Fahrenheit")
        line = line.replace("degrees Fahrenheit", "degrees_Fahrenheit")

        val numberFrom = line.split(" ")[0].toDouble()
        val unitFrom = line.split(" ")[1].lowercase()
        val unitTo = line.split(" ")[3].lowercase()

        if(units.containsKey(unitFrom) && units.containsKey(unitTo)){
            if(units[unitFrom]?.type == units[unitTo]?.type){
                val type = units[unitFrom]?.type
                if(type == "temperature"){
                    val numberTo = convertTemperature(units[unitFrom]!!, numberFrom, units[unitTo]!!)
                    println("${format(units[unitFrom]!!, numberFrom)} is ${format(units[unitTo]!!, numberTo)}")
                }else {
                    if(numberFrom < 0){
                        if(type == "length") println("Length shouldn't be negative")
                        else println("Weight shouldn't be negative")
                    }
                    val numberTo = units[unitFrom]!!.value / units[unitTo]!!.value
                    println("${format(units[unitFrom]!!, numberFrom)} is ${format(units[unitTo]!!, numberTo)}")
                }
            }
        }else {
            val unitName1 = units.getOrDefault(unitFrom, Unit.UNDEFINED).nameMany
            val unitName2 = units.getOrDefault(unitTo, Unit.UNDEFINED).nameMany
            println("Conversion from $unitName1 to $unitName2 is impossible")
        }
    }
}
