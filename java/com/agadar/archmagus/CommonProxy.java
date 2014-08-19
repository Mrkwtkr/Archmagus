package com.agadar.archmagus;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class CommonProxy 
{
	public void registerRenderers() { }
	
	public EntityPlayer getPlayerFromMessageContext(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}
}
