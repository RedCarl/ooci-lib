package co.ooci.npc.abstracts;

import co.ooci.npc.manager.CitizensManager;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;

import java.util.List;


public abstract class AbstractNPC {

    protected void register() {
        CitizensManager.register(this);
    }

    public abstract List<Object> getTexts();

    public abstract Location getLocation();

    public abstract NPC getNPC();

    public void NPCLeftClick(NPCLeftClickEvent e){
    }

    public void NPCRightClick(NPCRightClickEvent e){
    }
}
