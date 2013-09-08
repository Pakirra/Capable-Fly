package capablefly;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import capablefly.DisableFly;
import capablefly.Principal;

public class checkoneusertask extends BukkitRunnable {

private final Principal plugin;
	private Player p;
	public checkoneusertask(Principal plugin,Player player){
		this.plugin = plugin;
		this.p = player;
	}
	
	@Override
	public void run() {
		
		int minutesCanFly = plugin.getConfig().getInt("options.MinutesToAllowFly");
		int minutesCantFly = plugin.getConfig().getInt("options.MinutesToDenyFly") + minutesCanFly;
		final Calendar cal = new GregorianCalendar();
		final int minOfYear = cal.get(Calendar.MINUTE) + ((cal.get(Calendar.HOUR_OF_DAY) + (cal.get(Calendar.DAY_OF_YEAR) * 24))*60);

           
    		if( (minOfYear -(plugin.getConfig().getInt("users.minuteOfYear." + p.getDisplayName()))) < minutesCanFly){
    			plugin.getConfig().set("users.canfly." + p.getDisplayName() , true);	
    		}else if( (minOfYear -(plugin.getConfig().getInt("users.minuteOfYear." + p.getDisplayName()))) >= minutesCantFly){
    			plugin.getConfig().set("users.canfly." + p.getDisplayName() , true);	
    			plugin.getConfig().set("users.settimefly." + p.getDisplayName() , true);
    		}
    		else 
    		{
    			plugin.getConfig().set("users.canfly." + p.getDisplayName() , false);
    			plugin.getConfig().set("users.isfling." + p.getDisplayName() , false);
    			if(p.getAllowFlight() == true)
    			{
    			p.sendMessage( ChatColor.RED + plugin.getConfig().getString("translate.onCmdcflyhelp") + " 30 S."  );
        		BukkitTask checktask = new DisableFly( p ).runTaskLater(plugin, 20*30);
    			}
    			
    		}
    		
    		
        

    	plugin.saveConfig();
    
		
		// TODO Auto-generated method stub
		
	}

}
