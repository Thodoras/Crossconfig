package Analyzers.SystemAnalyzers.Puppet.SmellDetectors;

import java.util.List;

import Models.Puppet.PuppetFile;
import Parsers.PuppetParsers.PuppetFileExtractor;

abstract public class SmellDetector {
	
	private List<PuppetFile> puppetFiles;
	private String rootPathProgramStructure;
	private PuppetFile currentPuppetFile;
	
	public SmellDetector(String rootPathProgramStructure) {
		this.rootPathProgramStructure = rootPathProgramStructure;
		PuppetFileExtractor extractor = new PuppetFileExtractor(rootPathProgramStructure);
		puppetFiles = extractor.findPuppetFiles();
	}
	
	public String getRootPathProgramStructure() {
		return rootPathProgramStructure;
	}
	
	public List<PuppetFile> getPuppetFiles() {
		return puppetFiles;
	}
	
	
	public PuppetFile getCurrentPuppetFile() {
		return currentPuppetFile;
	}

	public void setCurrentPuppetFile(PuppetFile currentPuppetFile) {
		this.currentPuppetFile = currentPuppetFile;
	}

	abstract public void detectSmells();
}
