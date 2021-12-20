package io.github.copecone.pat.util

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import java.math.BigInteger

class TypeSupportTest {

    @Test
    fun checkHexToByteArray() {
        val hexString = "d5edb2cf5552981adc42c03d87e459952cdd621a"
        val byteArray = hexString.hexToByteArray()
        val recoveredString = BigInteger(1, byteArray).toString(16) // 검증된 코드
        assertEquals(hexString, recoveredString)
    }

}