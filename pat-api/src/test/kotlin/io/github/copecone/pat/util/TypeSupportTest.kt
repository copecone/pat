package io.github.copecone.pat.util

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class TypeSupportTest {

    @Test
    fun checkToByteArray() {
        val hexString = "d5edb2cf5552981adc42c03d87e459952cdd621a"
        val byteArrayAnswer = byteArrayOf(-43, -19, -78, -49, 85, 82, -104, 26, -36, 66, -64, 61, -121, -28, 89, -107, 44, -35, 98, 26)
        val byteArray = hexString.toByteArray()

        assertContentEquals(byteArrayAnswer, byteArray)
    }

    @Test
    fun checkToHexString() {
        val hexString = "d5edb2cf5552981adc42c03d87e459952cdd621a"
        val byteArray = byteArrayOf(-43, -19, -78, -49, 85, 82, -104, 26, -36, 66, -64, 61, -121, -28, 89, -107, 44, -35, 98, 26)
        val hexStringRecover = byteArray.toHexString()

        assertEquals(hexString, hexStringRecover)
    }

}