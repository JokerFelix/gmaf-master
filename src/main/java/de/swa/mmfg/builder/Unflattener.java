package de.swa.mmfg.builder;

import de.swa.mmfg.MMFG;

/** interface to be used for unflattening (i.e. importing) MMFGs based on a String **/
public interface Unflattener {
	/** returns the MMFG contained in the string **/
	public MMFG unflatten(String s);
}
