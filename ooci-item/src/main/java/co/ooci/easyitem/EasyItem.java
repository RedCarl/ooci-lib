package co.ooci.easyitem;

import co.ooci.easyitem.manager.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EasyItem {

    public EasyItem(JavaPlugin plugin){
        plugin.getServer().getPluginManager().registerEvents(new ItemManager(),plugin);
    }

}
