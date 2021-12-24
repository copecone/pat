package io.github.copecone.pat.util

import java.math.BigInteger
import java.util.*

/**
 * [String]으로 저장된 헥스값을 [ByteArray]로 바꾸는 함수
 */
@Suppress("UnclearPrecedenceOfBinaryExpression", "USELESS_ELVIS")
fun String.toByteArray(): ByteArray =
    HexFormat.of().parseHex(this)

/**
 * [ByteArray]을 [String] 헥스로 바꾸는 함수
 */
fun ByteArray.toHexString(): String =
    BigInteger(1, this).toString(16)