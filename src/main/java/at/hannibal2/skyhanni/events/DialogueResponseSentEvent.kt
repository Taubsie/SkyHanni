package at.hannibal2.skyhanni.events

import at.hannibal2.skyhanni.api.event.SkyHanniEvent
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket

class DialogueResponseSentEvent(val packet: ServerboundCustomClickActionPacket) : SkyHanniEvent()
