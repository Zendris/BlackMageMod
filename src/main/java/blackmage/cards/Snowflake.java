package blackmage.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackmage.BlackMageMod;
import blackmage.patches.EnumPatch;
import blackmage.powers.IcePower;

public class Snowflake extends AbstractCustomCardWithType {
	
	public static final String ID = "Snowflake";
	private static final String NAME = "Snowflake";
	private static final String IMG = "img/cards/icons/snowflake.png";
	private static final String BG_IMG = BlackMageMod.SKILL_BG[1];
	private static final String BG_IMG_P = BlackMageMod.SKILL_BG_P[1];
	private static final String DESCRIPTION = "Apply !M! Ice.";
	
	private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
	private static final AbstractCard.CardColor COLOR = EnumPatch.BLACK_MAGE;
	private static final AbstractCard.CardRarity RARITY = AbstractCard.CardRarity.COMMON;
	private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
	private static final AbstractCustomCardWithType.CardColorType COLOR_TYPE = AbstractCustomCardWithType.CardColorType.ICE;

	private static final int COST = 0;
	private static final int MAGIC = 1;
	
	public Snowflake() {
		super(ID, NAME, IMG, BG_IMG, BG_IMG_P, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET, 1, COLOR_TYPE);
		
		this.magicNumber = MAGIC;
		this.baseMagicNumber = MAGIC;
	}

	@Override
	public AbstractCustomCardWithType getOpposite(boolean isUpgraded) {
		AbstractCustomCardWithType opposite = new Ember();
		if (isUpgraded)
			opposite.upgrade();
		return opposite;
	}

	@Override
	public AbstractCard makeCopy() {
		return new Snowflake();
	}

	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(1);
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster arg1) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IcePower(p, this.magicNumber), this.magicNumber));
	}

}
