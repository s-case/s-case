package nlp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * The sentence endpoint used to annotate sentences.
 * 
 * @author themis
 */
@Path("/sentence")
public class Sentence {

	/**
	 * Receives a request containing a sentence, and returns the annotations of this sentence.
	 * 
	 * @param request a request in JSON format.
	 * @return a {@link javax.ws.rs.core.Response Response} object with HTTP code 200 and a JSON-formatted body.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response annotateSentence(JSONObject request) {
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		dateFormat.setTimeZone(timeZone);
		String currentTimeISO8601 = dateFormat.format(new Date());

		if (!request.has("sentence"))
			throw new WebApplicationException(Response.status(422).entity("Please include a \"sentence\" JSON key")
					.type("text/plain").build());
		else if (!request.has("annotation_format"))
			throw new WebApplicationException(Response.status(422)
					.entity("Please include an \"annotation_format\" JSON key").type("text/plain").build());

		JSONObject jsonResponse = null;
		try {
			if (!(request.get("annotation_format").equals("ann") || request.get("annotation_format").equals("ttl"))) {
				throw new WebApplicationException(Response.status(422)
						.entity("\"annotation_format\" must be either \"ann\" or \"ttl\"").type("text/plain").build());
			}
			jsonResponse = new JSONObject();
			jsonResponse.put("created_at", currentTimeISO8601);
			jsonResponse.put("sentence", request.get("sentence"));
			jsonResponse.put("annotations", "Annotation of " + request.get("sentence"));
			jsonResponse.put("annotation_format", request.get("annotation_format"));
		} catch (JSONException e) {
			throw new WebApplicationException(Response.status(422).entity(e.getMessage()).type("text/plain").build());
		}

		return Response.status(200).entity(jsonResponse).type("application/json").build();
	}

}
