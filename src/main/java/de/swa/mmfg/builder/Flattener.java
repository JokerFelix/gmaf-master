package de.swa.mmfg.builder;

import de.swa.mmfg.MMFG;

/** interface to represent flatteners for exporting MMFGs into various formats **/
public interface Flattener {
	/** this method is called, when a MMFG should be exported **/
	public String flatten(MMFG fv);
	/** returns the file extension for this flattening process **/
	public String getFileExtension();
	/** writes some header (if required) **/
	public String startFile();
	/** writes some footer (if required) **/
	public String endFile();
}
