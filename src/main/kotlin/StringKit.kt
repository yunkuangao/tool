/**
 * 比较两个对象是否是字符串并且相等
 */
fun <T> equalsStringAndT(string: String, t: T): Boolean {
    return t != null && string == t.toString()
}

/**
 * 比较两个不同类型的对象是否相等且不为null
 */
fun <E, T> equalsAnyAndAny(e: E, t: T): Boolean {
    return e != null && e == t
}

/**
 * 比较两个相同类型的对象是否相等且不为null
 */
fun <T> equalsTAndT(t1: T, t2: T): Boolean {
    return t1 != null && t1 == t2
}

/**
 * 对象是否为空，如果为字符串，检查是否长度为零
 */
fun <T> isEmpty(t: T): Boolean {
    if (t == null) return true
    return if (t is String) t.length == 0
    else false
}

// 将字符串置于给定长度的中间
fun center(s: String, size: Int): String {
    return center(s, size, ' ')
}

fun center(s: String, size: Int, pad: Char): String {
    if (size <= s.length) return s
    val sb = StringBuilder(size)
    for (i in 0 until (size - s.length) / 2) {
        sb.append(pad)
    }
    sb.append(s)
    while (sb.length < size) {
        sb.append(pad)
    }
    return sb.toString()
}