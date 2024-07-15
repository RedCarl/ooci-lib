package co.ooci.npc;

import co.ooci.npc.manager.CitizensManager;
import org.bukkit.plugin.java.JavaPlugin;

public class OociNPC {
    public OociNPC(JavaPlugin plugin){
        plugin.getServer().getPluginManager().registerEvents(new CitizensManager(),plugin);
        CitizensManager.getInstance().tick(plugin);
    }
}
