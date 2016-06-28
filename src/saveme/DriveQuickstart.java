import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxUser;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

@SuppressWarnings("unused")
public class DriveQuickstart {

	private static String CLIENT_ID = "66190915557-pq6jqpls1so56em1tkcdsr0dv6vb9crj.apps.googleusercontent.com";
	private static String CLIENT_SECRET = "N-h_qYjmKv2PXglmaVPLL0ch";
//asdfasdf
	private static String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	/** Application name. */
	private static final String APPLICATION_NAME = "Drive API Java Quickstart";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"),
			".credentials/drive-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/** Global instance of the scopes required by this quickstart. */
	private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE);

	private static String path_1 = "c:\\client_secret_lee10241993.json";
	private static String path_2 = "c:\\client_secret_tjddnjs1024.json";

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static Credential authorize(String user, String path) throws IOException {

		// String file_path = "C:/client_secret.json";
		// String file_path = "C:\\/client_secret.json";
		// String file_path = "../resources/client_secret.json";
		// String file_path = "/src/main/java/client_secret.json";

		InputStream in = new FileInputStream(path);
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				CLIENT_ID, CLIENT_SECRET, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType("online")
						.build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize(user);
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;

		/*
		 * // Build flow and trigger user authorization request.
		 * GoogleAuthorizationCodeFlow flow = new
		 * GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
		 * CLIENT_ID, CLIENT_SECRET,
		 * SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).setAccessType(
		 * "offline") .build(); Credential credential = new
		 * AuthorizationCodeInstalledApp(flow, new
		 * LocalServerReceiver()).authorize("user"); String url =
		 * flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI) .build();
		 * System.out .println(
		 * "Please open the following URL in your browser then type the authorization code:"
		 * ); System.out.println("  " + url); BufferedReader br = new
		 * BufferedReader(new InputStreamReader(System.in)); String code =
		 * br.readLine(); System.out.println("Credentials saved to " +
		 * DATA_STORE_DIR.getAbsolutePath()); return credential;
		 */
	}

	/**
	 * Build and return an authorized Drive client service.
	 * 
	 * @return an authorized Drive client service
	 * @throws IOException
	 */
	public static Drive getDriveService(String user, String path) throws IOException {
		Credential credential = authorize(user, path);
		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	}

	private static InputStream downloadFile(Drive service, File file) {
		if (file.getDownloadUrl() != null && file.getDownloadUrl().length() > 0) {
			try {
				HttpResponse resp = service.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadUrl()))
						.execute();
				return resp.getContent();
			} catch (IOException e) {
				// An error occurred.
				e.printStackTrace();
				return null;
			}
		} else {
			// The file doesn't have any content stored on Drive.
			return null;
		}
	}

	private static Hashtable<String, Integer> FileTable = new Hashtable<String, Integer>();

	private static long count = 0;

	private static byte[] bytes = new byte[1024];

	// ������ ���� �ִ� �޼ҵ�
	private static void writeInputStreamToFile(String filename, InputStream in) throws IOException {
		OutputStream out = null;
		try {
			out = new FileOutputStream(filename + ".txt");

			int read = 0;

			while ((read = in.read(bytes)) != 1) {
				out.write(bytes, 0, read);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
		}
	}
	
	private static java.io.File changFile(InputStream in){
		return null;
		
	}

	private static FileContent a = null;
	private static FileContent b = null;

	private static FileContent mediaContent_1 = null;
	private static FileContent mediaContent_2 = null;

	private static int nchunk = 0;

	private static void divideFile(java.io.File file, ArrayList<Drive> service) throws FileNotFoundException {
		int service_num = service.size();
		FileInputStream inputfile = new FileInputStream(file);
		int read = 0;
		String newFileName;
		int filesize = (int) file.length();
		int split_size = (filesize / service_num)+1;
		byte[] ChunkSize;
		try {
			while (filesize > 0) {
				if (filesize < split_size) {
					split_size = filesize;
				}
				ChunkSize = new byte[split_size];
				read = inputfile.read(ChunkSize, 0, split_size);
				filesize -= read;
				nchunk++;
				newFileName = file.getName() + "_number" + Integer.toString(nchunk);
				FileOutputStream filepart = new FileOutputStream(newFileName);
				filepart.write(ChunkSize);
				filepart.flush();
				filepart.close();
				// ��� ��Ƽ� ���� �߰� ��Ű��
				uploadFile(file, service.get(nchunk - 1));
				System.out.println("Application Name == " + service.get(nchunk - 1).getApplicationName());
				ChunkSize = null;
				filepart = null;
			}
			FileTable.put(file.getName(), nchunk);
			nchunk = 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void uploadFile(java.io.File file, Drive service) throws FileNotFoundException {
		try {
			File body1 = new File();
			body1.setTitle(file.getName() + "_number" + Integer.toString(nchunk));
			java.io.File file1 = new java.io.File(file + "_number" + Integer.toString(nchunk));
			mediaContent_1 = new FileContent("text/plain", file1);
			service.files().insert(body1, mediaContent_1).execute();
			System.out.println("Insert File :" + body1.getTitle());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*
	private static void MergeFile(java.io.File file1, java.io.File file2) throws IOException {
		// List<String> Files_Name = null;
		// java.io.File file=new java.io.File(Files_Name.get(0));
		byte[] length;
		int read = 0;
		byte[] length1;
		int read1 = 0;
		FileOutputStream out1;
		FileOutputStream out2;
		try {
			FileInputStream in1 = new FileInputStream(file1);
			FileInputStream in2 = new FileInputStream(file2);
			out1 = new FileOutputStream(file1.getName() + ".txt", true);
			length = new byte[(int) file1.length()];
			read = in1.read(length, 0, (int) file1.length());
			length1 = new byte[(int) file2.length()];
			read1 = in1.read(length1, 0, (int) file2.length());
			out1.write(length);
			out1.write(length1);
			out1.flush();
			out1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
*/
	private static void DownNMergeFile(ArrayList<Drive> ServiceList, String FileName) throws IOException {
		int size = 0;
		int count=1;
		InputStream fi;
		OutputStream fo;
		java.io.File jf;
		size = ServiceList.size();
		try{
		for (int i = 0; i < ServiceList.size(); i++) {
			FileList result = ServiceList.get(i).files().list().setMaxResults(10).execute();
			List<File> files = result.getItems();
			for (int j = 0; j < files.size(); j++) {
				File file = files.get(j);
				String s = file.getTitle();
				if(s.contains(FileName)){
					fi=downloadFile(ServiceList.get(i), files.get(j));
					writeInputStreamToFile(Integer.toString(count),fi);
					jf=new java.io.File(Integer.toString(count)+".txt");
					filelist.add(jf);
					break;
					}
			}
			count++;
		}
		MergeFile(filelist,FileName);
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}

	private static void MergeFile(ArrayList<java.io.File> FileList, String FileName) throws IOException {
		int FileSize = FileList.size();
		java.io.File file;
		FileOutputStream fo;
		FileInputStream io;
		int byteread = 0;
		byte[] chunkbyte;
		int bytelength = 0;
		String Filepath;
		try {
			if (FileSize > 0) {
				fo = new FileOutputStream(FileName + ".txt", true);
				for (int i = 0; i < FileSize; i++) {
					file = FileList.get(i);
					io = new FileInputStream(file);
					chunkbyte = new byte[(int) file.length()];
					byteread = io.read(chunkbyte, 0, (int) file.length());
					fo.write(chunkbyte);
					fo.flush();
				}
				fo.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File doesn't exist");
			e.printStackTrace();
		}
	}
	private static List<Metadata> entries=null;
	public static void Dropbox(String ACCESS_TOKEN) throws DbxException{

        // Create Dropbox client
        DbxRequestConfig config = new DbxRequestConfig("lee10241993@naver.com", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        // Get current account info
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());

        // Get files and folder metadata from Dropbox root directory
       entries = client.files().listFolderBuilder("").withRecursive(true).start().getEntries();
        
        for (Metadata metadata : entries) {
            System.out.println(metadata.getName());
        }
	}
	/*private static void listFolder(BoxFolder folder, int depth) {
        for (BoxItem.Info itemInfo : folder) {
            String indent = "";
            for (int i = 0; i < depth; i++) {
                indent += "    ";
            }

            System.out.println(indent + itemInfo.getName());
            if (itemInfo instanceof BoxFolder.Info) {
                BoxFolder childFolder = (BoxFolder) itemInfo.getResource();
                if (depth < MAX_DEPTH) {
                    listFolder(childFolder, depth + 1);
                }
            }
        }
    }*/
	

    private static final String BOX_DEVELOPER_TOKEN = "Y5cizTvNNBYynDffQOO8eERMwK1nbONO";
    private static final int MAX_DEPTH = 1;
	
	public static BoxFolder Box(){
		
        Logger.getLogger("com.box.sdk").setLevel(Level.OFF);

        BoxAPIConnection api = new BoxAPIConnection(BOX_DEVELOPER_TOKEN);

        BoxUser.Info userInfo = BoxUser.getCurrentUser(api).getInfo();
        System.out.format("Welcome, %s <%s>!\n\n", userInfo.getName(), userInfo.getLogin());

        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        
        return rootFolder;
	}

	private static ArrayList<java.io.File> filelist = new ArrayList<java.io.File>();
	private static String user1 = "lee10241993";
	private static String user2 = "tjddnjs1024";
	private static final String Dropbox_ACCESS_TOKEN = "o64Fe_vW9IAAAAAAAAAAFScZl4a9t76D4BSnPE-p41e7hFtfHj6emzSb8fTKUC7m";

	public static void main(String[] args) throws IOException, DbxException {
		Drive service_1 = getDriveService("lee10241993", "c:\\client_secret_lee10241993.json");
		//Drive service_3 = getDriveService("tjddnjs1993", "c:\\client_secret_tjddnjs1993.json");
		//ArrayList<Drive> ServiceList = new ArrayList<Drive>();
		//ServiceList.add(service_1);
		//ServiceList.add(service_3);

		String filePath = "C:\\Users\\sung\\Desktop\\GDA\\�Ĺ� ���� ���� ��ġ_������.pptx";

		// Build a new authorized API client service.
		// Print the names and IDs for up to 10 files.
		FileList result = service_1.files().list().setMaxResults(10).execute();
		List<File> files = result.getItems();
		if (files == null || files.size() == 0) {
			System.out.println("No files found.");
		} else {
			System.out.println("Files:");
			for (File file : files) {
				System.out.printf("%s (%s)\n", file.getTitle(), file.getId());
			}
		}

		//File file = new File();
		// file
		// =service_1.files().get("0B0BEfjXa6SxPZW0p5Z1NDclFHWVE").execute();

		// ���� ���� �ϴ°�
		// InputStream input = downloadFile(service_1, file);

		// writeInputStreamToFile(file.getTitle(),input);

		//java.io.File fileContent = new java.io.File(filePath);
		//File body = new File();
		//body.setTitle(fileContent.getName());
		//divideFile(fileContent,ServiceList);
		//DownNMergeFile(ServiceList,"�Ĺ� ���� ���� ��ġ_������");
		Dropbox(Dropbox_ACCESS_TOKEN);
		MergeDriveUI merge = new MergeDriveUI();
		merge.getGoogleDriveFile(files);
		merge.getDropboxFile(entries);
		merge.getBoxFile(Box(), 0);
		merge.setView();
		
	}
}