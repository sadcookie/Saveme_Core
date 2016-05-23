package saveme.core.v1.file;

import saveme.core.v1.util.LangUtil;

public class Metadata {

	protected final String name;
	protected final String path;

	// Getter
	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	/**
	 * Metadata for a file or folder.
	 *
	 * @param name
	 *            The last component of the path (including extension). This
	 *            never contains a slash. Must not be <em>null</em>.
	 * @param path
	 *            The full path of the file contents. Doesn't contain the last
	 *            component of the path. This starts with the slash. Must not be
	 *            <em>null</em>.
	 * @param createdDate
	 * @throws IllegalArgumentException
	 *             If any argument does not meet its preconditions.
	 *             preconditions.
	 */
	public Metadata(String name, String path) {
		if (this.name == null)
			throw new IllegalArgumentException("ERROR: \'name\' is null. This must not be null");
		if (this.path == null)
			throw new IllegalArgumentException("ERROR: \'path\' is null. This must not be null");

		this.name = name;
		this.path = path;
	}

	/**
	 * Compare two Metadatas or any inherited Objects.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj.getClass().equals(this.getClass())) {
			Metadata other = (Metadata) obj;
			// Make sure not to override this function.
			return ((this.name == other.name) || (this.name.equals(other.name)))
					&& ((this.path == other.path) || (this.path.equals(other.path)));
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return java.util.Arrays.hashCode(new Object[] { this.name, this.path});
	}

}
