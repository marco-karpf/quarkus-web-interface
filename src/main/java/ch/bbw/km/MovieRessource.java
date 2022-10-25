package ch.bbw.km;

import org.jboss.logging.annotations.Pos;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/movies")
public class MovieRessource {
    public static List<String> movies = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMovies() {
        return Response.ok(movies).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/size")
    public Response countMoviesSize() {
        return Response.ok(movies.size()).build();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response createMovie(String movie) {
        movies.add(movie);
        return Response.ok().build();
    }

    @PUT
    @Path("{movieToUpdate}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateMovie(@PathParam("movieToUpdate") String movieToUpdate, @QueryParam("movie") String movie) {
        movies = movies.stream().map( m -> {
            if (m.equals(movieToUpdate)) {
                return movie;
            }
            return m;
        }).collect(Collectors.toList());
    return Response.ok(movies).build();
    }

    @DELETE
    @Path("{movieToDelete}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMovie(@PathParam("movieToDelete") String movieToDelete) {
        movies = movies.stream().filter(m -> !m.equals(movieToDelete)).collect(Collectors.toList());
        return Response.ok(movies).build();
    }
}
