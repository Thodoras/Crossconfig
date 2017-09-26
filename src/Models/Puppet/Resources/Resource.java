package Models.Puppet.Resources;

import java.util.regex.Pattern;

abstract public class Resource {
	
	private Integer pos;
	private Integer line;
	private Integer numOfLines;
	
	public Integer getPos() {
		return pos;
	}
	
	public Integer getLine() {
		return pos+1;
	}
	
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	
	public Integer getNumOfLines() {
		return numOfLines;
	}
	
	public void setNumOfLines(Integer numOfLines) {
		this.numOfLines = numOfLines;
	}
	
	abstract public Pattern getPattern();
}
