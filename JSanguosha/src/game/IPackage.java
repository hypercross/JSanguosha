package game;

import game.api.IPlayable;
import game.type.Type;

public interface IPackage {

	IPlayable getDefinitionCard(Type td);
}
