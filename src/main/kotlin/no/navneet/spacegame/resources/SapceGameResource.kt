package no.navneet.spacegame.resources

import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/space")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class SapceGameResource {
    @POST
    @Path("/alien")
    @Valid
    fun checkAlienExistInSpace(@PathParam("shape") alienShape: String): Response {

        return Response.ok().build()
    }
}
