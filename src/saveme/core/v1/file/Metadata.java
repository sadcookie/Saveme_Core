package saveme.core.v1.file;

import java.util.Date;

public class Metadata {

	protected final String name;
	protected final String path;
	protected final Date createdDate;
	
	// Getter
	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public Date getCreatedDate() {
		return createdDate;
	}
    /**
     * Metadata for a file or folder.
     *
     * @param name  The last component of the path (including extension). This
     *     never contains a slash. Must not be <em>null</em>.
     * @param path  The full path of the file contents. Doesn't contain the last component of the path.
     * This starts with the slash. Must not be <em>null</em>.
     * @param createDate  
     * @throws IllegalArgumentException  If any argument does not meet its preconditions.
     *     preconditions.
     */
	public Metadata(String name, String path, Date createdDate) {
		if (this.name == null)
			throw new IllegalArgumentException("ERROR: \'name\' is null. This must not be null");
		if (this.path == null)
			throw new IllegalArgumentException("ERROR: \'path\' is null. This must not be null");
		if (this.createdDate == null)
			throw new IllegalArgumentException("ERROR: \'createdDate\' is null. This must not be null");
		
		this.name = name;
		this.path = path;
		this.createdDate = createdDate;
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
					&& ((this.createdDate == other.createdDate) || (this.createdDate != null
							&& this.createdDate.equals(other.createdDate))
							);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode(){
		return java.util.Arrays.hashCode(new Object[] {this.name, this.path,this.createdDate});		
	}

}
