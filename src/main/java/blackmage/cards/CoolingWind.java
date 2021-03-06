package blackmage.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;

import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;
import blackmage.powers.IcePower;

public class CoolingWind extends AbstractCustomCardWithType {
	
	public static final String ID = "CoolingWind";
	private static final String NAME = "Cooling Wind";
	private static final String IMG = "img/cards/icons/blizzard.png";
	private static final String BG_IMG = BlackMageMod.ATTACK_BG[1];
	private static final String BG_IMG_P = BlackMageMod.ATTACK_BG_P[1];
	private static final String DESCRIPTION = "Deal !D! ice damage. NL If you have Fire NL draw !M! card next turn. NL Apply Ice.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.ATTACK;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.UNCOMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.ENEMY;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.ICE;

	private static final int COST = 1;
	private static final int ATK_DMG = 8;
	private static final int MAGIC = 1;
	
	public CoolingWind() {
		super(ID, NAME, IMG, BG_IMG, BG_IMG_P, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);
		
		this.baseDamage = ATK_DMG;
		this.magicNumber = MAGIC;
		this.baseMagicNumber = MAGIC;
	}
	
	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		AbstractCustomCardWithType opposite = new WarmingFlame();
		if (isUpgraded)
			opposite.upgrade();
		return opposite;
	}

	@Override
	public AbstractCard makeCopy() {
		return new CoolingWind();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(1);
			this.rawDescription = "Deal !D! ice damage. NL If you have Fire NL draw !M! cards next turn. NL Apply Ice.";
			initializeDescription();
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		
		if(p.hasPower("bm_fire_power")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new DrawCardNextTurnPower(p, magicNumber), magicNumber));
		}
		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IcePower(p, 1), 1));
	}

}
