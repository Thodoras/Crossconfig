package Parsers.PuppetParsers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Models.Puppet.PuppetFile;

public class PuppetFileExtractor {
	
	private String rootPathProgramStructure;
	private List<PuppetFile> puppetFiles;
	
	public PuppetFileExtractor(String rootPathProgramStructure) {
		this.rootPathProgramStructure = rootPathProgramStructure;
		this.puppetFiles = new ArrayList<PuppetFile>();
	}
	
	public String getRootPathProgramStructure() {
		return rootPathProgramStructure;
	}
	
	public List<PuppetFile> findPuppetFiles() {
		return findPuppetFilesByDirectory(this.rootPathProgramStructure);
		
	}

	private List<PuppetFile> findPuppetFilesByDirectory(String currentPath) {
		File[] files = new File(currentPath).listFiles();
		try {
			for (File file: files) {
				String fileName = file.getName();
				if (file.isDirectory()) {
					findPuppetFilesByDirectory(currentPath + "/" + fileName);
				}
				else {
					if (isPuppetFile(fileName)) {
						puppetFiles.add(new PuppetFile(file));
					}
				}
			}
		}
		catch (NullPointerException e) {
			throw e;
		}
		return this.puppetFiles;
	}
	
	private Boolean isPuppetFile(String fileName) {
		return getFileExtension(fileName).equals("pp");
	}
	
	private String getFileExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		return fileName.substring(dotIndex + 1);
	}
	
}
