package no.navneet.spacegame.resources

import no.navneet.spacegame.elements.Alien
import no.navneet.spacegame.elements.Space
import no.navneet.spacegame.match.AlienFinder
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
class SapceGameResource() {
    @POST
    @Path("/alien")
    @Valid
    fun checkAlienExistInSpace(
        @PathParam("alienPixel") alienPixel: String,
        @PathParam("spacePixel") spacePixel: String,
    ): Response {
        val space = Space(spacePixel.split("\n"))
        val alien = Alien(alienPixel.split("\n"))
        val result = AlienFinder.findAlien(space, alien)
        return Response.ok(result).build()
    }
}
