package Models.Puppet.Resources;

import java.util.regex.Pattern;

import Models.Puppet.PuppetRegexes;

public class ResourceClass extends Resource {
	
	private static final Pattern PATTERN_CLASS = Pattern.compile(PuppetRegexes.CLASS_REGEX);
	
	public Pattern getPattern() {
		return PATTERN_CLASS;
	}
}