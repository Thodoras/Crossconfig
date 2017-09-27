package Analyzers.SystemAnalyzers.Puppet.SmellDetectors;

import java.util.List;

import Analyzers.SystemAnalyzers.Puppet.PuppetSmellConstants;
import Models.Puppet.PuppetFile;
import Models.Puppet.Resources.Resource;
import Models.Puppet.Resources.ResourceName;

public class AbstractionSmellDetector extends SmellDetector {

	public AbstractionSmellDetector(String rootPathProgramStructure) {
		super(rootPathProgramStructure);
	}

	@Override
	public void detectSmells() {
		for (PuppetFile puppetFile : getPuppetFiles()) {
			setCurrentPuppetFile(puppetFile);
			detectUnnecessaryAbstraction();
		}
	}
	
	public void detectUnnecessaryAbstraction() {
		detectUnnecessaryAbstraction(ResourceName.CLASS);
		detectUnnecessaryAbstraction(ResourceName.DEFINE);
	}
	
	private void detectUnnecessaryAbstraction(ResourceName resourceName) {
		for (Resource resource : getResourceListByName(resourceName)) {
			if (hasUnnecessaryAbstraction(resource)) {
				System.out.println(
						"Unnecessary '" + resourceName.name().toLowerCase() + "' abstraction detected in " +
 						"File: " + getCurrentPuppetFile().getFilePath() + "/" + getCurrentPuppetFile().getFileName() +
						" line: " + resource.getLine()
				);
			}
		}
	}
	
	private List<Resource> getResourceListByName(ResourceName resourceName) {
		return getCurrentPuppetFile().getResourceListByName(resourceName);
	}
	
	private Boolean hasUnnecessaryAbstraction(Resource resource) {
		return resource.getNumOfLines() <= PuppetSmellConstants.EMPTY_ABSTRACTION_LINES_THRESHOLD;
	}

}
