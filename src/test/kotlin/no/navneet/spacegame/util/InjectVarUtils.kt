package no.navneet.spacegame.util

import java.lang.reflect.Field

object InjectVarUtils {

    @Suppress("UNCHECKED_CAST")
    fun injectEnvironmentVariable(key: String, value: String) {
        try {
            val processEnvironment = Class.forName("java.lang.ProcessEnvironment")
            val unmodifiableMapField = getAccessibleField(processEnvironment, "theUnmodifiableEnvironment")
            val unmodifiableMap = unmodifiableMapField[null]
            injectIntoUnmodifiableMap(key, value, unmodifiableMap)
            try {
                val mapField = getAccessibleField(processEnvironment, "theCaseInsensitiveEnvironment")
                val map = mapField[null] as MutableMap<String, String>
                map[key] = value
            } catch (e: NoSuchFieldException) {
            }
            val mapField = getAccessibleField(processEnvironment, "theEnvironment")
            val map = mapField[null] as MutableMap<String, String>
            map[key] = value
        } catch (e: Exception) {
            throw RuntimeException("Failed to set environment variable in tests")
        }
    }

    private fun getAccessibleField(clazz: Class<*>, fieldName: String): Field {
        val field = clazz.getDeclaredField(fieldName)
        field.isAccessible = true
        return field
    }

    @Suppress("UNCHECKED_CAST")
    private fun injectIntoUnmodifiableMap(key: String, value: String, map: Any) {
        val unmodifiableMap = Class.forName("java.util.Collections\$UnmodifiableMap")
        val field = getAccessibleField(unmodifiableMap, "m")
        val obj = field[map]
        (obj as MutableMap<String?, String?>)[key] = value
    }
}