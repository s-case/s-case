package nlp.nlpclient;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * An NLP web client. This class serves as an example of using the NLP Server.
 * 
 * @author themis
 */
public class NLPClient {

	/**
	 * Performs a POST request on the server.
	 * 
	 * @param address the address of the request.
	 * @param input the body of the request in JSON format
	 * @param credentials the username and the password to be added to the request header.
	 * @return a {@link org.codehaus.jettison.json.JSONObject JSONObject} containing the response body, if the request
	 *         is correct, else an exception is thrown.
	 * @throws JSONException when the request or the credentials are wrong.
	 */
	public static JSONObject performJsonPostRequest(String address, JSONObject input, String... credentials)
			throws JSONException {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);
		if (credentials.length == 2) {
			String username = credentials[0];
			String password = credentials[1];
			client.addFilter(new HTTPBasicAuthFilter(username, password));
		}

		WebResource webResource = client.resource(address);
		ClientResponse response = webResource.accept("application/json").type("application/json")
				.post(ClientResponse.class, input);

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + " Error: " + response.getEntity(String.class));
		}

		String stringoutput = response.getEntity(String.class);
		return new JSONObject(stringoutput);
	}

	/**
	 * Performs a POST request on the server.
	 * 
	 * @param address the address of the request.
	 * @param credentials the username and the password to be added to the request header.
	 * @return a {@link org.codehaus.jettison.json.JSONObject JSONObject} containing the response body, if the request
	 *         is correct, else an exception is thrown.
	 * @throws JSONException when the request or the credentials are wrong.
	 */
	public static JSONObject performJsonGetRequest(String address, String... credentials) throws JSONException {
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(clientConfig);

		WebResource webResource = client.resource(address);
		ClientResponse response = webResource.accept("application/json").type("application/json")
				.get(ClientResponse.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + " Error: " + response.getEntity(String.class));
		}

		String stringoutput = response.getEntity(String.class);
		return new JSONObject(stringoutput);
	}

	/**
	 * Performs request on the server.
	 * 
	 * @param args unused parameter.
	 * @throws JSONException when a JSON object is not defined correctly.
	 */
	public static void main(String[] args) throws JSONException {
		// Perform a GET request on the main endpoint ("/") of the server (no credentials required)
		String initAddress = "http://localhost:8010/nlpserver/";
		System.out.println("GET " + initAddress);
		JSONObject initOutput = performJsonGetRequest(initAddress);
		System.out.println(initOutput.toString(3).replaceAll("\\\\/", "/"));

		// Perform a POST request on the phrase endpoint ("/phrase") of the server
		String phraseAddress = "http://localhost:8010/nlpserver/phrase";
		JSONObject phraseInput = new JSONObject();
		phraseInput.put("phrase", "create bookmark");
		phraseInput.put("annotation_format", "ann");
		System.out.println("%nPOST " + phraseAddress);
		JSONObject phraseOutput = performJsonPostRequest(phraseAddress, phraseInput, "user", "pass");
		System.out.println(phraseOutput.toString(3).replaceAll("\\\\/", "/"));

		// Perform a POST request on the sentence endpoint ("/sentence") of the server
		String sentenceAddress = "http://localhost:8010/nlpserver/sentence";
		JSONObject sentenceInput = new JSONObject();
		sentenceInput.put("sentence", "The user must be able to create a bookmark.");
		sentenceInput.put("annotation_format", "ttl");
		System.out.println("%nPOST " + sentenceAddress);
		JSONObject sentenceOutput = performJsonPostRequest(sentenceAddress, sentenceInput, "user", "pass");
		System.out.println(sentenceOutput.toString(3).replaceAll("\\\\/", "/"));

		// Perform a POST request on the project endpoint ("/project") of the server
		String projectAddress = "http://localhost:8010/nlpserver/project";
		JSONObject projectInput = new JSONObject();
		projectInput.put("project_name", "Restmarks");
		String[] requirements = new String[] {
				"A user must be able to create a user account by providing a username and a password.",
				"A user must be able to login to his account by providing his username and password.",
				"A user that is logged in to his account must be able to update his password.",
				"A logged in user must be able to add a new bookmark to his account.",
				"A logged in user must be able to retrieve any bookmark from his account.",
				"A logged in user must be able to delete any bookmark from his account.",
				"A logged in user must be able to update any bookmark from his account.",
				"A logged in user must be able to mark his bookmarks as public or private.",
				"A logged in user must be able to add tags to his bookmarks.",
				"Any user must be able to retrieve the public bookmarks of any RESTMARKS's community user.",
				"Any user must be able to search by tag the public bookmarks of a specific RESTMARKS's user.",
				"Any user must be able to search by tag the public bookmarks of all RESTMARKS users.",
				"A logged in user, must be able to search by tag his private bookmarks as well." };
		JSONArray projectRequirements = new JSONArray();
		for (int i = 0; i < requirements.length; i++) {
			String requirement = requirements[i];
			projectRequirements.put(new JSONObject().put("id", "FR" + (i + 1)).put("text", requirement));
		}
		projectInput.put("project_requirements", projectRequirements);
		projectInput.put("annotation_format", "ttl");
		System.out.println("%nPOST " + projectAddress);
		JSONObject projectOutput = performJsonPostRequest(projectAddress, projectInput, "user", "pass");
		System.out.println(projectOutput.toString(3).replaceAll("\\\\/", "/"));

		// Perform the project endpoint ("/project") query again with ann annotation format
		projectInput.put("annotation_format", "ann");
		System.out.println("%nPOST " + projectAddress);
		projectOutput = performJsonPostRequest(projectAddress, projectInput, "user", "pass");
		System.out.println(projectOutput.toString(3).replaceAll("\\\\/", "/"));
	}

}
