package Models.Puppet.Resources;

import java.util.regex.Pattern;

import Models.Puppet.PuppetRegexes;

public class ResourceDefine extends Resource {
	
	private static final Pattern PATTERN_DEFINE = Pattern.compile(PuppetRegexes.DEFINE_REGEX);
	
	public Pattern getPattern() {
		return PATTERN_DEFINE;
	}
}
