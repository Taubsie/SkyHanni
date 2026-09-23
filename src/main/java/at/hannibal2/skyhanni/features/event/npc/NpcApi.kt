package at.hannibal2.skyhanni.features.event.npc

import at.hannibal2.skyhanni.api.event.HandleEvent
import at.hannibal2.skyhanni.events.DialogueResponseSentEvent
import at.hannibal2.skyhanni.events.minecraft.packet.PacketSentEvent
import at.hannibal2.skyhanni.skyhannimodule.SkyHanniModule
import at.hannibal2.skyhanni.utils.RegexUtils.matches
import at.hannibal2.skyhanni.utils.repopatterns.RepoPattern
import net.minecraft.client.Minecraft
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket
import net.minecraft.resources.Identifier
import java.util.Optional

@SkyHanniModule
object NpcApi {
    val patternGroup = RepoPattern.group("npc")

    /**
     * REGEX-TEST: skyblock:dialogue_response
     */
    val dialogueResponseIdPattern by patternGroup.pattern(
        "dialogue.response",
        "skyblock:dialogue_response"
    )

    @HandleEvent(onlyOnSkyblock = true)
    private fun onCustomClickPacketSent(event: PacketSentEvent) {
        val packet = event.packet as? ServerboundCustomClickActionPacket ?: return
        if (!dialogueResponseIdPattern.matches(packet.id().toString())) return

        DialogueResponseSentEvent(packet).post()
    }

    fun sendNpcResponse(npcId: String, responseKey: String) {
        val optionTag = CompoundTag()
        optionTag.putString("npcId", npcId)
        optionTag.putString("responseKey", responseKey)

        Minecraft.getInstance().player?.connection?.send(
            ServerboundCustomClickActionPacket(Identifier.parse(dialogueResponseIdPattern.toString()), Optional.of(optionTag))
        )
    }
}
