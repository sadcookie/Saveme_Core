package saveme.core.v1.file;

import java.util.Date;
import java.util.List;
import saveme.core.v1.Property.PropertyGroup;
import saveme.core.v1.util.LangUtil;

public class FileMetadata extends Metadata {

	protected final String Id;
	protected final Date createdDate;
	protected final Date clientModified;
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
	 * @param createdDate 
	 * @param clientModified
	 * @param serverModified
	 * @param revision
	 * @param size
	 * @param propertyGroup
	 * @throws IllegalArgumentException
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
}
