package game.type;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TypeMeta {

	static HashMap<String,TypeMeta> cache = new HashMap<String,TypeMeta>();
	
	String description,name;
	TextureRegion icon,banner,cover;
	
	public TypeMeta(String name, String desc, TextureRegion... imgs)
	{
		this.name = name;
		description = desc;
		if(imgs.length>0)cover = imgs[0];
		if(imgs.length>1)banner = imgs[1];
		if(imgs.length>2)icon = imgs[2];
	}
	
	public String getDisplayName()
	{
		return name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public TextureRegion getIcon()
	{
		return icon;
	}
	
	public TextureRegion getBanner()
	{
		return banner;
	}
	
	public TextureRegion getCover()
	{
		return cover;
	}
	
	public static void setTypeMeta(Type type, TypeMeta metainfo)
	{
		cache.put(type.fullName(), metainfo);
	}
	
	public static TypeMeta getMeta(Type type)
	{
		return cache.get(type.fullName());
	}
}
