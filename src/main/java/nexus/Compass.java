package nexus;

import cn.nukkit.Player;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.form.window.*;
import cn.nukkit.form.response.*;
import cn.nukkit.event.player.PlayerFormRespondedEvent;

import nexus.form.CompassForm;
import nexus.Warn;

import me.onebone.economyapi.EconomyAPI;

public class Compass extends PluginBase implements Listener{
	@Override
	public void onEnable(){
		this.getServer().getPluginManager().registerEvents(this,this);
	}
	@EventHandler
	public void onTouch(PlayerInteractEvent ev){
		if(ev.getItem().getId()==345){
			boolean op=(ev.getPlayer().isOp())? true:false;
			ev.getPlayer().showFormWindow(new CompassForm(op));
		}
	}
	@EventHandler
	public void onResponse(PlayerFormRespondedEvent ev){
		if(ev.getWindow() instanceof CompassForm){
			if(ev.getResponse() instanceof FormResponseSimple){
				FormResponseSimple result=(FormResponseSimple) ev.getResponse();
				Player player=ev.getPlayer();
				String name=player.getName();
				double money=EconomyAPI.getInstance().myMoney(player);
				int warn_count=Warn.getInstance().getNumber(player);
				switch(result.getClickedButton().getText()){
					case "스폰":
						player.teleport(getServer().getDefaultLevel().getSafeSpawn());
						break;
					case "귓속말":
						this.getServer().dispatchCommand(player,"whisper");
						break;
					case "경고":
						this.getServer().dispatchCommand(player,"warn");
						break;
					case "내 정보":
						player.showFormWindow(new FormWindowSimple("내 정보","나의 이름 : "+name+
						"\n내 돈 : "+money+"\n누적된 경고 수 : "+warn_count));
						break;
					default:
						break;
				}
			}
		}
	}
}