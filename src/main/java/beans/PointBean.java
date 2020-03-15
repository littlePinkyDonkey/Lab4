package beans;

import model.Point;
import org.json.JSONArray;
import service.PointService;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Stateless
@Path("/history")
public class PointBean {
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public String addPoint(InputStream json) throws IOException {
        return "";
    }

    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHistory(@PathParam("username") String username){
        JSONArray history = new JSONArray();
        PointService service = new PointService();

        List<Point> points = service.getPointsByOwner(username);
        points.forEach(point -> history.put(point.toJSON()));

        return points.toString();
    }
}
