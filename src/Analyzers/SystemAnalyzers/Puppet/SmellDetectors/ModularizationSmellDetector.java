package Analyzers.SystemAnalyzers.Puppet.SmellDetectors;

import Models.Puppet.PuppetFile;

public class ModularizationSmellDetector extends SmellDetector {
	
	public ModularizationSmellDetector(String rootPathProgramStructure) {
		super(rootPathProgramStructure);
	}
	
	@Override
	public void detectSmells() {
		for (PuppetFile puppetFile : getPuppetFiles()) {
			setCurrentPuppetFile(puppetFile);
			detectInsufficientModularization();
		}
	}
	
	public void detectInsufficientModularization() {
		if (isInsufficientModularizationForm1()) {
			System.out.println(
					"Insufficient Modularization in File: " + getCurrentPuppetFile().getFilePath() + "/" 
			                                                + getCurrentPuppetFile().getFileName()
			);
		}
	}
	
	private Boolean isInsufficientModularizationForm1() {
		return getCurrentPuppetFile().getClasses().size() > 1 ||
			   getCurrentPuppetFile().getDefines().size() > 1;
	}

}
