package nlp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * The main endpoint of the server. It allows a GET request that returns infomration about the server.
 * 
 * @author themis
 */
@Path("/")
public class Info {

	/**
	 * The GET handle for this endpoint. It returns information about the NLP server and a list of the possible
	 * endpoints.
	 * 
	 * @return a {@link javax.ws.rs.core.Response Response} object with HTTP code 200 and a JSON-formatted body.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response info() {
		JSONObject jsonResponse = null;
		try {
			jsonResponse = new JSONObject();
			jsonResponse.put("module", "NLP Server");
			jsonResponse.put("description", "NLP Server of the EU-funded project S-CASE. See http://www.scasefp7.eu/");
			JSONObject links = new JSONObject();
			links.put("phrase", "http://localhost:8010/nlpserver/phrase");
			links.put("sentence", "http://localhost:8010/nlpserver/sentence");
			links.put("project", "http://localhost:8010/nlpserver/project");
			jsonResponse.put("_links", links);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(jsonResponse).type("application/json").build();
	}

}
