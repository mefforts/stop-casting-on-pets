package com.mefforts.stopcastingonpets;

import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;
import net.runelite.api.NPC;
import net.runelite.api.NPCComposition;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetUtil;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@PluginDescriptor(name = "Stop Casting On Pets")
public class StopCastingOnPetsPlugin extends Plugin
{
    @Inject
    private Client client;

    @Subscribe
    public void onMenuEntryAdded(MenuEntryAdded event)
    {
        MenuEntry entry = event.getMenuEntry();
        MenuAction action = entry.getType();
        if ((action != MenuAction.WIDGET_TARGET_ON_NPC || !"Cast".equals(entry.getOption()))
            && action != MenuAction.EXAMINE_NPC)
        {
            return;
        }

        if (!client.isWidgetSelected())
        {
            return;
        }

        Widget widget = client.getSelectedWidget();
        if (widget == null || WidgetUtil.componentToInterface(widget.getId()) != InterfaceID.MAGIC_SPELLBOOK)
        {
            return;
        }

        NPC npc = entry.getNpc();
        if (npc == null && client.getTopLevelWorldView() != null)
        {
            npc = client.getTopLevelWorldView().npcs().byIndex(entry.getIdentifier());
        }

        NPCComposition composition = npc != null ? npc.getComposition() : null;
        if (composition != null && composition.isFollower())
        {
            client.getMenu().removeMenuEntry(entry);
        }
    }
}
