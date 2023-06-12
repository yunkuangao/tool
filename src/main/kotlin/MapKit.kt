import java.util.*
import java.util.stream.Collectors

/**
 * 检查给定的map里面是否有给定的keys中有空值
 */
fun hasAnyEmpty(map: Map<String, Any>, vararg keys: String): Boolean {
    return Arrays.stream(keys).anyMatch { lt: String -> !map.containsKey(lt) }
}

/**
 * 如果所有参数都包含在映射中，将返回一个空字符串； 否则，不会返回任何参数
 */
fun getAnyEmpty(map: Map<String, Any>, vararg keys: String): String {
    return Arrays.stream(keys)
        .filter { lt: String -> !map.containsKey(lt) }
        .collect(Collectors.joining(","))
}


/**
 * 将map的类型转换成t的
 */
fun <T> changeMapToKT(changeMap: Map<out Any, Any>, t: T): Map<*, *> {
    return changeMap.entries.stream()
        .collect(
            Collectors.toMap(
                { (key): Map.Entry<Any, Any> -> key as T },
                { (_, value): Map.Entry<Any, Any> -> value as T })
        )
}

/**
 * 将map的类型装换成k/v的
 */
fun <K, V> changeMapToKT(changeMap: Map<out Any, Any>, t: K, v: V): Map<*, *> {
    return changeMap.entries.stream()
        .collect(
            Collectors.toMap(
                { (key): Map.Entry<Any, Any> -> key as K },
                { (_, value): Map.Entry<Any, Any> -> value as V })
        )
}