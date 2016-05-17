package saveme.core.v1.file;

import java.util.Date;

public class Metadata {

	protected final String name;
	protected final String path;
	protected final String parentFolder;
	protected final Date createdDate;
	protected final Date lastModifiedDate;
	
	// Getter
	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public String getParentFolder() {
		return parentFolder;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	/**
	 * Metadata for a file or folder
	 * 
	 * @throws IllegalArgumentException
	 *             If any argument does not meet its preconditions.
	 */
	public Metadata(String name, String path, String parentFolder, Date createdDate, Date lastModifiedDate) {
		if (this.name == null)
			throw new IllegalArgumentException("ERROR: \'name\' is null. Requires a value");
		if (this.path == null)
			throw new IllegalArgumentException("ERROR: \'path\' is null. Requires a value");
		if (this.parentFolder == null)
			throw new IllegalArgumentException("ERROR: \'parentFolder\' is null. Requires a value");
		if (this.createdDate == null)
			throw new IllegalArgumentException("ERROR: \'createdDate\' is null. Requires a value");
		if (this.lastModifiedDate == null)
			throw new IllegalArgumentException("ERROR: \'lastModifiedDate\' is null. Requires a value");

		this.name = name;
		this.path = path;
		this.parentFolder = parentFolder;
		this.createdDate = createdDate;
		this.lastModifiedDate = lastModifiedDate;
	}
	
	
	/**
	 * Compare Between Metadatas or any inherited Object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj.getClass().equals(this.getClass())) {
			Metadata other = (Metadata) obj;
			// Make sure not to override this function.
			return ((this.name == other.name) || (this.name.equals(other.name)))
					&& ((this.path == other.path) || (this.path.equals(other.path)))
					&& ((this.parentFolder == other.parentFolder) || (this.parentFolder.equals(other.parentFolder)))
					&& ((this.createdDate == other.createdDate) || (this.createdDate != null
							&& this.createdDate.equals(other.createdDate))
							&& ((this.lastModifiedDate == other.lastModifiedDate) || (this.lastModifiedDate != null
									&& this.lastModifiedDate.equals(other.lastModifiedDate))));

		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode(){
		return java.util.Arrays.hashCode(new Object[] {this.name, this.parentFolder, this.path,this.createdDate, this.lastModifiedDate});		
	}

}
