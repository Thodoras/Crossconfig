import java.util.List;

import Analyzers.SystemAnalyzers.Puppet.PuppetAnalyzer;
import Models.Puppet.PuppetFile;
import Models.Puppet.Resources.ResourceName;
import Parsers.PuppetParsers.PuppetFileExtractor;

public class CrossconfigMain {
	public static void main(String[] args) {
		PuppetAnalyzer puppetAnalyzer = new PuppetAnalyzer("/home/thodoras/Documents/PhD/CROSSMINER/Crossminer-Tests");
		puppetAnalyzer.detectConfigurationCodeSmells();
	}
}
