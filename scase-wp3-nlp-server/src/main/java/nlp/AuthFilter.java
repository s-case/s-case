package nlp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import javax.xml.bind.DatatypeConverter;

/**
 * Authentication filter used to authenticate any incoming requests to the server.
 * 
 * @author themis
 */
public class AuthFilter implements ContainerRequestFilter {

	/**
	 * Filters an incoming request and allows it to enter the server only if the user credentials are correct. The
	 * credentials are cross-checked to a text file.
	 * 
	 * @param containerRequest the request to be filtered.
	 * @return the {@code containerRequest} if the credentials are correct, else an exception is thrown.
	 * @throws WebApplicationException when the credentials are not correct (HTTP code 401 Unauthorized) or when the
	 *             credentials file is unreachable (HTTP Code 500 Internal Server Error).
	 */
	@Override
	public ContainerRequest filter(ContainerRequest containerRequest) throws WebApplicationException {
		// String method = containerRequest.getMethod();
		String path = containerRequest.getPath();

		if (path.equals("")) {
			return containerRequest;
		}
		else if (path.equals("phrase")) {
			// Get the authentification passed in HTTP headers parameters
			String auth = containerRequest.getHeaderValue("authorization");
			if (auth == null)
				throw new WebApplicationException(Response.status(401).entity("Please provide username and password!")
						.type("text/plain").build());

			// Decode the string into byte[]
			auth = auth.replaceFirst("[B|b]asic ", "");
			byte[] decodedBytes = DatatypeConverter.parseBase64Binary(auth);
			if (decodedBytes == null || decodedBytes.length == 0)
				throw new WebApplicationException(Response.status(401).entity("Wrong username and/or password!")
						.type("text/plain").build());

			// Convert the byte[] into a splitted array [login, password]
			String[] usernameAndPassword = new String(decodedBytes).split(":", 2);
			if (usernameAndPassword == null || usernameAndPassword.length != 2)
				throw new WebApplicationException(Response.status(401).entity("Wrong username and/or password!")
						.type("text/plain").build());
			String username = usernameAndPassword[0];
			String password = usernameAndPassword[1];

			// Read usernames and passwords from file
			HashMap<String, String> map = new HashMap<String, String>();
			try {
				// Change this path according to where the userdata path is set
				String userdatatextfile = "C:\\Users\\themis\\workspaceNLP\%nlpserver\\userdata.txt";
				Scanner scanner = new Scanner(new FileReader(userdatatextfile));
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					if (!line.startsWith("#")) {
						String[] columns = line.split(" ");
						map.put(columns[0], columns[1]);
					}
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				throw new WebApplicationException(Response.status(500)
						.entity("Internal Server Error! Try again later or contact the administrator..")
						.type("text/plain").build());
			}

			// Continue if the credentials are correct
			if (map.containsKey(username) && map.get(username).equals(password)) {
				return containerRequest;
			}
			else
				throw new WebApplicationException(Response.status(401).entity("Wrong username and/or password!")
						.type("text/plain").build());
		}
		else {
			return containerRequest;
		}
	}
}
