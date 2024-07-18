package co.ooci.npc.entity;

import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public interface MNPCs {

    static NPC createNPC(Location location, List<Object> texts, NPC npc) {

        npc.data().setPersistent(NPC.Metadata.NAMEPLATE_VISIBLE, false);

        npc.spawn(location);

        // holograms
        if (texts==null){
            texts = new ArrayList<>();
        }

        if (!texts.isEmpty()) {
            location.setY(location.getBlockY()+2.1+(texts.size()*0.25));
            texts.forEach(i->{
                if (i instanceof ItemStack){
                    location.setY(location.getY()+0.37);
                }
            });
            MHolograms.createHologram(npc.getName(),location,texts);
        }

        return npc;

    }

}
