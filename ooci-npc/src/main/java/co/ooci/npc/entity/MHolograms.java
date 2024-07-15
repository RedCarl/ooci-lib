package co.ooci.npc.entity;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import eu.decentsoftware.holograms.api.holograms.HologramLine;
import eu.decentsoftware.holograms.api.holograms.HologramPage;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @Author: carl0
 * @DATE: 2022/11/18 15:35
 */
public interface MHolograms {

    static Hologram createHologram(String name, Location location, List<Object> texts){
        Hologram hologram = DHAPI.createHologram(name,location);

        for (Object o:texts){
            if (o instanceof ItemStack){
                DHAPI.addHologramLine(hologram, (ItemStack) o);
            }else {
                DHAPI.addHologramLine(hologram, (String) o);
            }
        }

        return hologram;
    }



    static Hologram setHologramLines(Hologram hologram, List<Object> texts){

        HologramPage page = hologram.getPages().getFirst();

        for (int i = 0; i < page.getLines().size(); i++) {
            HologramLine line = page.getLine(i);
            if (texts.get(i) instanceof ItemStack itemStack){
                DHAPI.setHologramLine(line, itemStack);
            }else {
                DHAPI.setHologramLine(line, (String) texts.get(i));
            }
        }

        return hologram;
    }

}