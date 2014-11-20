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

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * The project endpoint used to annotate projects.
 * 
 * @author themis
 */
@Path("/project")
public class Project {

	/**
	 * Receives a request containing the requirements of a project, and returns the annotations of this project.
	 * 
	 * @param request a request in JSON format.
	 * @return a {@link javax.ws.rs.core.Response Response} object with HTTP code 200 and a JSON-formatted body.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response annotateProject(JSONObject request) {
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		dateFormat.setTimeZone(timeZone);
		String currentTimeISO8601 = dateFormat.format(new Date());

		if (!request.has("project_name"))
			throw new WebApplicationException(Response.status(422).entity("Please include a \"project_name\" JSON key")
					.type("text/plain").build());
		if (!request.has("project_requirements"))
			throw new WebApplicationException(Response.status(422)
					.entity("Please include a \"project_requirements\" JSON key").type("text/plain").build());
		else if (!request.has("annotation_format"))
			throw new WebApplicationException(Response.status(422)
					.entity("Please include an \"annotation_format\" JSON key").type("text/plain").build());

		JSONObject jsonResponse = null;
		try {
			if (!(request.get("annotation_format").equals("ann") || request.get("annotation_format").equals("ttl"))) {
				throw new WebApplicationException(Response.status(422)
						.entity("\"annotation_format\" must be either \"ann\" or \"ttl\"").type("text/plain").build());
			}
			if (request.get("annotation_format").equals("ann")) {
				jsonResponse = new JSONObject();
				jsonResponse.put("created_at", currentTimeISO8601);
				jsonResponse.put("project_name", request.get("project_name"));
				jsonResponse.put("project_requirements", request.get("project_requirements"));
				jsonResponse.put("annotations", "Annotation of requirements");
				jsonResponse.put("annotation_format", request.get("annotation_format"));
			}
			else if (request.get("annotation_format").equals("ttl")) {
				jsonResponse = new JSONObject();
				jsonResponse.put("created_at", currentTimeISO8601);
				jsonResponse.put("project_name", request.get("project_name"));
				jsonResponse.put("project_requirements", request.get("project_requirements"));
				JSONArray annotations = new JSONArray();
				JSONArray requirements = request.getJSONArray("project_requirements");
				for (int i = 0; i < requirements.length(); i++) {
					JSONObject requirement = requirements.getJSONObject(i);
					JSONObject annotatedRequirement = new JSONObject();
					annotatedRequirement.put("id", requirement.get("id"));
					annotatedRequirement.put("annotation", "Annotation of " + requirement.get("text"));
					annotations.put(annotatedRequirement);
				}
				jsonResponse.put("annotations", annotations);
				jsonResponse.put("annotation_format", request.get("annotation_format"));
			}
		} catch (JSONException e) {
			throw new WebApplicationException(Response.status(422).entity(e.getMessage()).type("text/plain").build());
		}

		return Response.status(200).entity(jsonResponse).type("application/json").build();
	}

}
