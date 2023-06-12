import java.io.File

/**
 * 返回用户音乐目录
 */
val musicDirectory = getDirectory(UserDirectory("music", listOf("music", "音乐", "Music")))

/**
 * 返回用户图片目录
 */
val pictureDirectory = getDirectory(UserDirectory("picture", listOf("picture", "图片", "Pictures")))

/**
 * 返回目录
 */
fun getDirectory(directory: UserDirectory): String = File(System.getProperty("user.home"))
    .listFiles()
    ?.filter { directory.directory.contains(it.name) }
    ?.get(0)
    ?.absolutePath ?: (System.getProperty("user.dir") + sep + directory.default)

/**
 * @property default 默认目录
 * @property directors 目录列表
 */
data class UserDirectory(val default: String, val directory: List<String>)

/**
 * 列出文件清单,以一个数组形式返回，
 * @param filePath 磁盘文件路径
 * @param fileArr  此参数需要传一个 MutableList<>()进入方法体,在方法体创建一个对象数组，子目录的文件存放不了进数组进行返回
 */
fun cacheFileList(filePath: String, fileArr: MutableList<String> = mutableListOf()): List<String> {

    // 保证文件夹存在，若不存在则创建
    existDirectory(filePath)

    File(filePath).listFiles()?.forEach { lt ->
        if (lt.isDirectory) {
            cacheFileList(lt.path, fileArr)
        } else {
            fileArr.add(lt.name)
        }
    }

    return fileArr
}