package saveme.core.v1.file;

import java.util.Date;
import java.util.List;
import saveme.core.v1.Property.PropertyGroup;
import saveme.core.v1.util.LangUtil;

public class FileMetadata extends Metadata {

	protected final String Id;
	protected final Date createdDate;
	protected final Date clientModified; // this may not be finialised. This 
	protected final Date serverModified;
	protected final String revision;
	protected final long size;
	protected final List<PropertyGroup> propertyGroup;

	/**
	 * @param name
	 *            The last component of the path (including extension). This
	 *            never contains a slash. Must not be <em>null</em>.
	 * @param path
	 *            The full path of the file contents. Doesn't contain the last
	 *            component of the path. This starts with the slash. Must not be
	 *            <em>null</em>.
	 * @param Id  Owner information.
	 * @param createdDate The date of file created on server. 
=	 * @param clientModified  The last date of user recently modified. If content of the file updated, the date will be updated. Otherwise, it is identical to serverModifed date. 
	 * @param serverModified  Latest modification date updated by user. This  
	 * @param revision  The latest revision. Revision may update if the client upload the file to Saveme Server.
	 * @param size  File size in Bytes
	 * @param propertyGroup
	 * @throws IllegalArgumentException  if the arguments are null, 
	 */
	public FileMetadata(String name, String path, String Id,Date createdDate, Date clientModified, Date serverModified,
			String revision, long size, List<PropertyGroup> propertyGroup) {
		super(name, path);
		if (Id == null)
			throw new IllegalArgumentException("ERROR: \'Id\' is null. This must not be null");
		if (createdDate == null)
			throw new IllegalArgumentException("ERROR: \'createdDate\' is null. This must not be null");
		if (clientModified == null)
			throw new IllegalArgumentException("ERROR: \'clientModified\' is null. This must not be null");
		if (serverModified == null)
			throw new IllegalArgumentException("ERROR: \'serverModified\' is null. This must not be null");
		if (revision == null)
			throw new IllegalArgumentException("ERROR: \'revision\' is null. This must not be null");
		if (propertyGroup == null)
			for (PropertyGroup p : propertyGroup)
				if (p == null)
					throw new IllegalArgumentException("ERROR: \'propertyGroup\' is null. This must not be null");
		this.Id = Id;
		this.createdDate = LangUtil.truncateMilliseconds(createdDate);
		this.clientModified = LangUtil.truncateMilliseconds(clientModified);
		this.serverModified = LangUtil.truncateMilliseconds(serverModified);
		this.revision = revision;
		this.size = size;
		this.propertyGroup = propertyGroup;

	}

	public FileMetadata(String name, String path, Date createdDate) {
		this(name, path, null, null, null, null, null, -1, null);
	}
	
	public boolean clientModified(){
		if(this.clientModified== this.serverModified)		
			return true;
		return false;
	}
}
