package nexus.form;

import java.util.ArrayList;

import cn.nukkit.form.element.*;
import cn.nukkit.form.window.FormWindowSimple;

public class CompassForm extends FormWindowSimple{
	public CompassForm(boolean op){
		super("나침반","",new ArrayList<ElementButton>(){{
				add(new ElementButton("내 정보"));
				add(new ElementButton("스폰"));
				add(new ElementButton("귓속말"));
				if(op){
					add(new ElementButton("경고"));
				}
			}
		});
	}
}