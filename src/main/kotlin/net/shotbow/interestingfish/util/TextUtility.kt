package net.shotbow.interestingfish.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer

object TextUtility {
    private val legacySerializer = LegacyComponentSerializer.builder()
        .hexColors()
        .useUnusualXRepeatedCharacterHexFormat()
        .build()
    private val ampersandSerializer = LegacyComponentSerializer.builder()
        .character('&')
        .hexColors()
        .useUnusualXRepeatedCharacterHexFormat()
        .build()

    fun String.withColors(player: String? = null): String {
        // the player name is inserted as legacy text without parsing tags
        val component = if (player == null) {
            MiniMessage.miniMessage().deserialize(this)
        } else {
            MiniMessage.miniMessage().deserialize(
                "$this<player>",
                Placeholder.component("player", legacySerializer.deserialize(player))
            )
        }
        return legacySerializer.serialize(component)
    }

    // checks for both & and § codes
    fun String.hasLegacyCodes(): Boolean {
        val text = replace('§', '&')
        val component = ampersandSerializer.deserialize(text)
        return component.content() != text || !component.style().isEmpty || component.children().isNotEmpty()
    }

    // returns null if there's nothing to convert or minimessage tags are mixed in
    fun String.legacyToMiniMessage(): String? {
        if (!hasLegacyCodes()) return null
        val component = ampersandSerializer.deserialize(replace('§', '&'))
        val plainText = component.plainText()
        if (MiniMessage.miniMessage().stripTags(plainText) != plainText) return null
        return MiniMessage.miniMessage().serialize(component)
    }

    private fun Component.plainText(): String =
        (if (this is TextComponent) content() else "") + children().joinToString("") { it.plainText() }
}
