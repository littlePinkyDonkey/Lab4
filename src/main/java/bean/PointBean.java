package main.java.bean;

import main.java.model.Point;
import org.json.JSONArray;
import org.json.JSONObject;
import main.java.service.PointService;
import main.java.util.Converter;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Stateless
@Path("/history")
public class PointBean {
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public String addPoint(InputStream json) throws IOException {
        JSONObject jsonObject = new JSONObject(Converter.convert(json, StandardCharsets.UTF_8));
        JSONObject response = new JSONObject();

        Point point = new Point();
        point.setX(jsonObject.getDouble("x"));
        point.setY(jsonObject.getDouble("y"));
        point.setR(jsonObject.getDouble("r"));
        point.setOwner(jsonObject.getString("user"));
        point.setHit(point.checkHit());

        PointService service = new PointService();

        if (service.addPoint(point)) {
            response.put("response", "OK");
        } else {
            response.put("response", "unknown error");
        }

        return response.toString();
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
