package com.agadar.archmagus;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/** The message that is send when a player's maximum mana has changed. */
public class MaxManaMessage implements IMessage 
{
	private int maxMana;
	
	public MaxManaMessage() { }
	
	public MaxManaMessage(int maxMana)
	{
		this.maxMana = maxMana;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.maxMana = ByteBufUtils.readVarInt(buf, 1);		
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		ByteBufUtils.writeVarInt(buf, this.maxMana, 1);	
	}
	
	/** The handler for this message. */
	public static class Handler implements IMessageHandler<MaxManaMessage, IMessage> 
	{      
		@Override
		public IMessage onMessage(MaxManaMessage message, MessageContext ctx) 
		{
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			ManaProperties prop = ManaProperties.get(player);
			prop.setMaxMana(message.maxMana);				
			return null;
		}
    }
}
