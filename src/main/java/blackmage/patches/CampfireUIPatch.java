package blackmage.patches;

import java.util.ArrayList;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.rooms.CampfireUI;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;

import basemod.ReflectionHacks;
import blackmage.ui.ExchangeOption;

@SpirePatch(cls="com.megacrit.cardcrawl.rooms.CampfireUI", method="initializeButtons")
public class CampfireUIPatch {
	public static void Postfix(Object meObj) {
		CampfireUI campfire = (CampfireUI)meObj;
		try {
			@SuppressWarnings("unchecked")
			ArrayList<AbstractCampfireOption> campfireButtons = (ArrayList<AbstractCampfireOption>) ReflectionHacks.getPrivate(campfire, CampfireUI.class, "buttons");
			
			ExchangeOption button = new ExchangeOption(true);
			
			campfireButtons.add(button);
			campfireButtons.get(campfireButtons.size() - 1).setPosition(950 * Settings.scale, 180 * Settings.scale);
		
		} catch (SecurityException | IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
