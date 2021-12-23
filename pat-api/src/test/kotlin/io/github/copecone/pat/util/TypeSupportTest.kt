package io.github.copecone.pat.util

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TypeSupportTest {

    @Test
    fun checkByteArrayAndHex() {
        val hexString = "d5edb2cf5552981adc42c03d87e459952cdd621a"
        val byteArrayAnswer = byteArrayOf(-43, -19, -78, -49, 85, 82, -104, 26, -36, 66, -64, 61, -121, -28, 89, -107, 44, -35, 98, 26)

        val byteArray = hexString.toByteArray()
        val hexStringRecover = byteArray.toHexString()

        assertEquals(byteArrayAnswer, byteArray)
        assertEquals(hexString, hexStringRecover)
    }

}