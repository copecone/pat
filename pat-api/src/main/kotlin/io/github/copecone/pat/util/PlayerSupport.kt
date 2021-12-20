@file:Suppress("unused")
package io.github.copecone.pat.util

import io.github.monun.tap.util.isDamageable
import org.bukkit.entity.Player

val Player.isDamageable
    get() = run { !this.isInvulnerable && this.gameMode.isDamageable }

private val Player.resourcePacks: MutableMap<String, ByteArray>
    get() = run { this.resourcePacks }

/**
 * 리소스팩을 플레이어의 *서버측 리소스팩 리스트*에 등록
 *
 * 일시적인 리소스팩의 등록으로 편하게 등록된 리소스팩을 쓸 수 있도록 제작
 *
 * @param resourcePack 리소스팩의 URL
 * @param hash 리소스팩의 sha1 해쉬값
 */
fun Player.registerResourcePack(resourcePack: String, hash: String) {
    this.resourcePacks[resourcePack] = hash.hexToByteArray()
}

/**
 * 플레이어의 *서버측 리소스팩 리스트*에 등록된 리소스팩을 이름에 따라 바로 사용
 *
 * 플레이어의 리소스팩 리스트에 리소스팩을 등록하려면 [Player.registerResourcePack]을 사용
 *
 * @param resourcePack 리소스팩의 URL
 */

fun Player.useResourcePack(resourcePack: String) {
    this.resourcePacks[resourcePack]?.let { this.setResourcePack(resourcePack, it) }
}