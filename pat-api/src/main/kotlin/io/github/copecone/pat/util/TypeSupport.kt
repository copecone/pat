package io.github.copecone.pat.util

import java.util.*

/**
 * [String]으로 저장된 헥스값을 [ByteArray]로 바꾸는 함수
 */
@Suppress("UnclearPrecedenceOfBinaryExpression", "USELESS_ELVIS")
fun String.hexToByteArray(): ByteArray =
    HexFormat.of().parseHex(this)