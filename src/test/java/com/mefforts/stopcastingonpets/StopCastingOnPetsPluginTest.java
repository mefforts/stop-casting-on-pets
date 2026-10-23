package com.mefforts.stopcastingonpets;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class StopCastingOnPetsPluginTest
{
    public static void main(String[] args) throws Exception
    {
        ExternalPluginManager.loadBuiltin(StopCastingOnPetsPlugin.class);
        RuneLite.main(args);
    }
}
