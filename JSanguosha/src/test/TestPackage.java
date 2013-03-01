package test;

import game.type.Type;

public class TestPackage {
	public static Type CARD_INPLAY = Type.fromParent("Inplay", Type.ENTITY_CARD);
	
	public static Type CARD_BASIC = Type.fromParent("Basic", CARD_INPLAY);
	public static Type CARD_SHA = Type.fromParent("Sha", CARD_BASIC);
	public static Type CARD_SHAN = Type.fromParent("Shan", CARD_BASIC);
	
}
