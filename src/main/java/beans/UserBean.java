package beans;

import model.User;
import org.json.JSONObject;
import service.UserService;
import util.Converter;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Stateless
@Path("/auth")
public class UserBean {

    @POST
    @Path("/signUp")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String signUp(InputStream json) throws IOException {
        JSONObject jsonObject = new JSONObject(Converter.convert(json, StandardCharsets.UTF_8));
        JSONObject response = new JSONObject();
        response.put("authStatus","already exist");

        User user = new User();
        user.setLogin(jsonObject.getString("username"));
        user.setPassword(jsonObject.getString("password"));

        UserService service = new UserService();
        if (service.addUser(user))
            response.put("authStatus","successful");

        return response.toString();
    }

    @POST
    @Path("/signIn")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String signIn(InputStream json) throws IOException {
        JSONObject jsonObject = new JSONObject(Converter.convert(json,StandardCharsets.UTF_8));
        JSONObject response = new JSONObject();

        User currentUser = new User();
        currentUser.setLogin(jsonObject.getString("username"));
        currentUser.setPassword(jsonObject.getString("password"));

        UserService service = new UserService();
        User user = service.getUserByLogin(currentUser.getLogin());

        if (user == null) {
            response.put("authStatus","sign-in-failed");
            response.put("message","no such user");
        }else {
            if (service.checkAccess(user,currentUser.getPassword())) {
                response.put("authStatus", "ok");
                response.put("message", "logged in");
            }else {
                response.put("authStatus", "sign-in-failed");
                response.put("message", "invalid password");
            }
        }

        return response.toString();
    }
}
