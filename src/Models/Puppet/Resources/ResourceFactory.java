package Models.Puppet.Resources;

public class ResourceFactory {
	
	public static Resource getResource(ResourceName resourceName) {
		if (resourceName == ResourceName.CLASS) {
			return new ResourceClass();
		}
		if (resourceName == ResourceName.DEFINE) {
			return new ResourceDefine();
		}
		return null;
	}
	
}
