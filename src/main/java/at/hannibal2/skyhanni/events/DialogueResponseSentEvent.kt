package at.hannibal2.skyhanni.events

import at.hannibal2.skyhanni.api.event.SkyHanniEvent
import at.hannibal2.skyhanni.skyhannimodule.PrimaryFunction
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket

/**
 * Fired when the player clicks on any chat component with a custom click action dedicated to skyblock dialogue responses.
 * This is handled by [at.hannibal2.skyhanni.api.NpcApi] by filtering the outgoing packets.
 *
 * @param packet The actual packet that is sent to the server.
 */
@PrimaryFunction("onDialogueResponseSent")
class DialogueResponseSentEvent(val packet: ServerboundCustomClickActionPacket) : SkyHanniEvent()
