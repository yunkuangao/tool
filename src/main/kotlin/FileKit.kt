import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

/**
 * 写入文件
 */
fun writeFile(
    path: String,
    data: ByteArray,
) {
    existDirectory(path.substring(0, path.lastIndexOf(sep)))
    val file = File(path)
    file.writeBytes(data)
}

/**
 * 创建一个目录
 */
fun createDirectory(path: String) {
    Files.createDirectory(Paths.get(path))
}

/**
 * 目录是否存在，若不存在则创建
 */
fun existDirectory(path: String) {
    if (!File(path).exists()) {
        createDirectory(path)
    }
}


/**
 * 文件有效性验证
 */
fun validFileName(name: String): String = name.map {
    if ("~!@#$%^&*，。；:‘’\\{【】[]}|/".contains(it)) ' '
    else it
}.joinToString("")