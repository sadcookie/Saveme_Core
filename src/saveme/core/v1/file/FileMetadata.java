package saveme.core.v1.file;

import java.util.Date;
import java.util.List;
import saveme.core.v1.Property.PropertyGroup;

public class FileMetadata extends Metadata{
	
	protected final String Id;
	protected final Date clientModified;
	protected final Date serverModified;
	protected final String revision;
	protected final long size;
	protected final List<PropertyGroup> propertyGroup;
	
	/**
	 * @param name
	 * @param path
	 * @param createdDate
	 * @param Id
	 * @param clientModified
	 * @param serverModified
	 * @param revision
	 * @param size
	 * @param propertyGroup
	 * @throws IllegalArgumentException 
	 */
	public FileMetadata(String name, String path, Date createdDate, String Id, Date clientModified, Date serverModified, String revision, long size, List<PropertyGroup> propertyGroup ) {
		super(name, path, createdDate);
		this.Id = Id;
		if(Id ==null)
			throw new IllegalArgumentException("ERROR: \'Id\' is null. This must not be null");
		this.clientModified = clientModified;
		if(clientModified ==null)
			throw new IllegalArgumentException("ERROR: \'clientModified\' is null. This must not be null");
		this.serverModified = serverModified;
		if(serverModified ==null)
			throw new IllegalArgumentException("ERROR: \'serverModified\' is null. This must not be null");
		this.revision = revision;
		this.size = size;
		this.propertyGroup = propertyGroup; 
		
	}
}
