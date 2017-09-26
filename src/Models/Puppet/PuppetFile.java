package Models.Puppet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import Models.Puppet.Resources.Resource;
import Models.Puppet.Resources.ResourceFactory;
import Models.Puppet.Resources.ResourceName;

public class PuppetFile {
	
	private static final String RIGHT_BRACKET = "}";
	private static final String LEFT_BRACKET = "{";
	
	private File file;
	private List<String> fileLines;
	private Integer numFileLines;
	private List<Resource> classes;
	private List<Resource> defines;
	
	public PuppetFile(File file) {
		this.file = file;
	}
	
	public String getFileName() {
		return file.getName();
	}
	
	public String getFilePath() {
		return file.getPath();
	}

	public File getFile() {
		return file;
	}
	
	public List<String> getLinesOfFile() {
		if (fileLines == null) {
			populateLinesOfFile();
		}
		return fileLines;
	}
	
	private void populateLinesOfFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			fileLines = new ArrayList<String>();
			String line;
		    while ((line = br.readLine()) != null) {
		       fileLines.add(line);
		    }
		    numFileLines = fileLines.size();
		    br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Integer getNumOfLines() {
		if (fileLines == null) {
			getLinesOfFile();
		}
		return numFileLines;
	}
	
	public List<Resource> getResourceListByName(ResourceName resourceName) {
		if (resourceName == ResourceName.CLASS) {
			return getClasses();
		}
		if (resourceName == ResourceName.DEFINE) {
			return getDefines();
		}
		return null;
	}
	
	public List<Resource> getClasses() { 
		if (classes == null) {
			classes = new ArrayList();
			populateResourceList(classes, ResourceName.CLASS);
		}
		return classes;
	}
	
	public List<Resource> getDefines() { 
		if (defines == null) {
			defines = new ArrayList();
			populateResourceList(defines, ResourceName.DEFINE);
		}
		return defines;
	}

	
	private void populateResourceList(List<Resource> resources, ResourceName resourceName) {
		Pattern pattern = ResourceFactory.getResource(resourceName).getPattern();
		Integer pos = 0;
		for (String line : getLinesOfFile()) {
			if (pattern.matcher(line).matches()) {
				Resource resource = ResourceFactory.getResource(resourceName);
				resource.setPos(pos);
				resource.setNumOfLines(getLinesOfAResource(resource.getPos()));
				resources.add(resource);
			}
			pos++;
		}
	}
	
	public Integer getLinesOfAResource(Integer posOfResource) {
		Integer balanceOfBrackets = 0;
		Integer numberOfResourceLines = 0;
		for (String line : subFile(posOfResource, getNumOfLines())) {
			if (!isCommentOrEmpty(line)) {
				numberOfResourceLines++;
				balanceOfBrackets += findNumOfCharsInLine(line, LEFT_BRACKET) - findNumOfCharsInLine(line, RIGHT_BRACKET);
				if (balanceOfBrackets <= 0) {
					break;
				}
			}
		}
		return numberOfResourceLines;
	}
	
	private List<String> subFile(Integer startPos, Integer endPos) {
		return getLinesOfFile().subList(startPos, endPos);
	}
	
	private Integer findNumOfCharsInLine(String line, String character) {
		Integer indexOfFirstChar = line.indexOf(character); 
		if (indexOfFirstChar < 0) {
			return 0;
		}
		return findNumOfCharsInLine(line.substring(indexOfFirstChar + 1), character) + 1;
	}
	
	private Boolean isCommentOrEmpty(String line) {
		return line.matches("((\\s*)#.*)|^$");
	}
}