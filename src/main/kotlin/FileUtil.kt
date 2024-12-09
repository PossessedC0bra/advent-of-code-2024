import java.io.InputStream

fun readResource(path: String): String = String(getResourceAsStream(path)!!.readAllBytes())

fun getResourceAsStream(path: String): InputStream? = object {}.javaClass.getResourceAsStream(path)