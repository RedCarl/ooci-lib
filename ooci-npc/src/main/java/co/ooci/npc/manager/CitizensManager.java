package co.ooci.npc.manager;

import co.ooci.npc.abstracts.AbstractNPC;
import co.ooci.npc.entity.MHolograms;
import co.ooci.npc.entity.MNPCs;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import lombok.Getter;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: carl0
 * @DATE: 2022/8/3 17:41
 */
public class CitizensManager implements Listener {

    @Getter
    private static final CitizensManager instance = new CitizensManager();

    private static final Map<String, AbstractNPC> registerNPC = new HashMap<>();
    private static final Map<String, NPC> npcs = new HashMap<>();

    public static void register(AbstractNPC abstractNPC) {
        NPC npc = spawnerNPC(abstractNPC);
        registerNPC.put(npc.getName(), abstractNPC);
        npcs.put(npc.getName(),npc);
    }

    public AbstractNPC getAbstractNPC(NPC npc) {
        return registerNPC.get(npc.getName());
    }

    private static NPC spawnerNPC(AbstractNPC abstractNPC){
        return MNPCs.createNPC(
                abstractNPC.getLocation(),
                abstractNPC.getTexts(),
                abstractNPC.getNPC()
        );
    }

    public void tick(JavaPlugin plugin){
        new BukkitRunnable() {
            @Override
            public void run() {
                if (npcs.isEmpty()){
                    return;
                }

                npcs.forEach((id,npc)->{
                    AbstractNPC abstractNPC = registerNPC.get(id);

                    Hologram hologram = DHAPI.getHologram(npc.getName());
                    if (hologram!=null) {
                        MHolograms.setHologramLines(hologram,abstractNPC.getTexts());
                    }
                });
            }
        }.runTaskTimer(plugin, 20,20);
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void NPCLeftClick(NPCLeftClickEvent e){
        AbstractNPC npc = getAbstractNPC(e.getNPC());

        npc.NPCLeftClick(e);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void NPCRightClick(NPCRightClickEvent e){
        AbstractNPC npc = getAbstractNPC(e.getNPC());

        npc.NPCRightClick(e);
    }
}