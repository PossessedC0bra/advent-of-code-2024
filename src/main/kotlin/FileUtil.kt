fun readResource(path: String) = String(getResourceAsStream(path)!!.readAllBytes())

fun getResourceAsStream(path: String) = object {}.javaClass.getResourceAsStream(path)