package saveme.core.v1.file;

import java.util.Date;
import java.util.List;
import saveme.core.v1.Property.PropertyGroup;
import saveme.core.v1.util.LangUtil;

public class FileMetadata extends Metadata {

	protected final String Id;
	protected final Date createdDate;
	protected final Date clientModifiedDate; // this may not be finialised. This
	protected final Date serverModifiedDate;
	protected final String revision;
	protected final long size;
	protected final List<PropertyGroup> propertyGroup;

	/**
	 * 
	 * File Metadata
	 * 
	 * @param name
	 *            The last component of the path (including extension). This
	 *            never contains a slash. Must not be <em>null</em>.
	 * @param path
	 *            The full path of the file contents. Doesn't contain the last
	 *            component of the path. This starts with the slash. Must not be
	 *            <em>null</em>.
	 * @param Id
	 *            Owner information. Must not be
	 *            <em>null</em>.
	 * @param createdDate
	 *            The date of file created on server. Must not be
	 *            <em>null</em>.
	 * @param clientModifiedDate
	 *            The last date of user recently modified. If content of the
	 *            file updated, the date will be updated. Otherwise, it is
	 *            identical to serverModifed date. Must not be
	 *            <em>null</em>.
	 * @param serverModifiedDate
	 *            Latest modification date updated by user. This Must not be
	 *            <em>null</em>.
	 * @param revision
	 *            The latest revision. Revision may update if the client upload
	 *            the file to Saveme Server. Must not be
	 *            <em>null</em>.
	 * @param size
	 *            File size in Bytes Must not be
	 *            <em>null</em>.
	 * @param propertyGroup   Must not be
	 *            <em>null</em>.
	 * @throws IllegalArgumentException
	 *             if any argument is null,
	 */
	public FileMetadata(String name, String path, String Id, Date createdDate, Date clientModifiedDate, Date serverModifiedDate,
			String revision, long size, List<PropertyGroup> propertyGroup) {
		super(name, path);
		if (Id == null)
			throw new IllegalArgumentException("ERROR: \'Id\' is null. This must not be null");
		if (createdDate == null)
			throw new IllegalArgumentException("ERROR: \'createdDate\' is null. This must not be null");
		if (clientModifiedDate == null)
			throw new IllegalArgumentException("ERROR: \'clientModified\' is null. This must not be null");
		if (serverModifiedDate == null)
			throw new IllegalArgumentException("ERROR: \'serverModified\' is null. This must not be null");
		if (revision == null)
			throw new IllegalArgumentException("ERROR: \'revision\' is null. This must not be null");
		if (propertyGroup == null)
			throw new IllegalArgumentException("ERROR: \'propertyGroup\' is null. This must not be null");
		else
			for (PropertyGroup p : propertyGroup)
				if (p == null)
					throw new IllegalArgumentException("ERROR: \'propertyGroup\' is null. This must not be null");
		this.Id = Id;
		this.createdDate = LangUtil.truncateMilliseconds(createdDate);
		this.clientModifiedDate = LangUtil.truncateMilliseconds(clientModifiedDate);
		this.serverModifiedDate = LangUtil.truncateMilliseconds(serverModifiedDate);
		this.revision = revision;
		this.size = size;
		this.propertyGroup = propertyGroup;

	}

	/**
	 * 
	 * File Metadata
	 * 
	 * @param name
	 *            The last component of the path (including extension). This
	 *            never contains a slash. Must not be <em>null</em>.
	 * @param path
	 *            The full path of the file contents. Doesn't contain the last
	 *            component of the path. This starts with the slash. Must not be
	 *            <em>null</em>.
	 */
	public FileMetadata(String name, String path) {
		this(name, path, null, null, null, null, null, -1, null);
	}

	/**
	 * Check the file is newly updated.
	 * 
	 * @return modified State
	 */
	public boolean isClientModified() {
		if (this.clientModifiedDate == this.serverModifiedDate)
			return true;
		return false;
	}
	
}
