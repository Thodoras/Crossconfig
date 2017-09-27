package Analyzers.SystemAnalyzers.Puppet;

import java.util.List;

import Analyzers.SystemAnalyzers.SystemConfigurationAnalyzer;
import Analyzers.SystemAnalyzers.Puppet.SmellDetectors.AbstractionSmellDetector;
import Analyzers.SystemAnalyzers.Puppet.SmellDetectors.ModularizationSmellDetector;
import Models.Puppet.PuppetFile;
import Parsers.PuppetParsers.PuppetFileExtractor;

public class PuppetAnalyzer extends SystemConfigurationAnalyzer {
	
	private String rootPathProgramStructure;
	private AbstractionSmellDetector abstractionSmellDetector;
	private ModularizationSmellDetector modularizationSmellDetector;
	
	public PuppetAnalyzer(String rootPathProgramStructure) {
		this.rootPathProgramStructure = rootPathProgramStructure;
		abstractionSmellDetector = new AbstractionSmellDetector(rootPathProgramStructure);
		modularizationSmellDetector = new ModularizationSmellDetector(rootPathProgramStructure);
	}

	@Override
	public void detectConfigurationCodeSmells() {
		abstractionSmellDetector.detectSmells();
		modularizationSmellDetector.detectSmells();
	}

}
