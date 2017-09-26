package Analyzers.SystemAnalyzers.Puppet;

import java.util.List;

import Analyzers.SystemAnalyzers.SystemConfigurationAnalyzer;
import Analyzers.SystemAnalyzers.Puppet.SmellDetectors.AbstractionSmellDetector;
import Models.Puppet.PuppetFile;
import Parsers.PuppetParsers.PuppetFileExtractor;

public class PuppetAnalyzer extends SystemConfigurationAnalyzer {
	
	private String rootPathProgramStructure;
	private AbstractionSmellDetector abstractionSmellDetector;
	
	public PuppetAnalyzer(String rootPathProgramStructure) {
		this.rootPathProgramStructure = rootPathProgramStructure;
		abstractionSmellDetector = new AbstractionSmellDetector(rootPathProgramStructure);
	}

	@Override
	public void detectConfigurationCodeSmells() {
		abstractionSmellDetector.detectSmells();
	}

}
