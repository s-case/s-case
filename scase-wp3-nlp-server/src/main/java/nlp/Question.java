package nlp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONArray;

import uk.ac.ed.inf.srl.StaticPipeline;
/**
 * The question endpoint used to extract query terms from a sentence.
 * 
 * @author themis
 * @author richard
 */
@Path("/question")
public class Question {

	/**
	 * Receives a request containing a question, and returns the query terms in it.
	 * 
	 * @param request a question in JSON format.
	 * @return a {@link javax.ws.rs.core.Response Response} object with HTTP code 200 and a JSON-formatted body.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response annotateQuestion(JSONObject request) {
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		dateFormat.setTimeZone(timeZone);
		String currentTimeISO8601 = dateFormat.format(new Date());

		if (!request.has("question"))
			throw new WebApplicationException(Response.status(422).entity("Please include a \"question\" JSON key")
					.type("text/plain").build());

		JSONObject jsonResponse = null;
		try {
			StaticPipeline.clear();
//			String[] strings = {"some", "terms"};
			Object query_terms = 
			    StaticPipeline.findQueryTerms(request.get("question").toString());
//			    new JSONArray(Arrays.asList(strings));

			jsonResponse = new JSONObject();
			jsonResponse.put("created_at", currentTimeISO8601);
			jsonResponse.put("question", request.get("question"));
			jsonResponse.put("query_terms", query_terms);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebApplicationException(Response.status(422).entity(e.getMessage()).type("text/plain").build());
		}

		return Response.status(200).entity(jsonResponse).type("application/json").build();
	}

}
