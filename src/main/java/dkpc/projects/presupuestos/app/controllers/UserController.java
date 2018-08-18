package dkpc.projects.presupuestos.app.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dkpc.projects.presupuestos.app.model.User;
import dkpc.projects.presupuestos.app.service.UserService;
import dkpc.projects.presupuestos.app.util.RestResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    protected UserService userService;

    protected ObjectMapper mapper;

    // SAVE and UPDATE method
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public RestResponse saveOrUpdate(@RequestBody String userJson)
            throws JsonParseException, JsonMappingException, IOException {

        this.mapper = new ObjectMapper();

        User user = this.mapper.readValue(userJson, User.class);

        if (!this.validate(user)) {
            return new RestResponse(HttpStatus.NOT_ACCEPTABLE.value(),
                    "Por favor ingrese todos los campos obligatorios");
        }
        this.userService.save(user);

        return new RestResponse(HttpStatus.OK.value(), "Usuario guardado exitosamente");
    }

    // SELECT method
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<User> getUsers() {
        return this.userService.findAll();
    }

    // DELETE method
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public void deleteUser(@RequestBody String userJson) throws Exception {
        this.mapper = new ObjectMapper();

        User user = this.mapper.readValue(userJson, User.class);

        if(user.getId() == null) {
            throw new Exception("Id cannot be null");
        }
        this.userService.deleteUser(user.getId());

        //return this.userService.findAll();
    }

    private boolean validate(User user) {
        boolean isValid = true;

        if (StringUtils.trimToNull(user.getName()) == null) {
            isValid = false;
        }

        if (StringUtils.trimToNull(user.getLastname()) == null) {
            isValid = false;
        }

        if (StringUtils.trimToNull(user.getUsername()) == null) {
            isValid = false;
        }

        if (user.getPassword() == null) {
            isValid = false;
        }

        if (user.getEmail() == null) {
            isValid = false;
        }

        if (StringUtils.trimToNull(user.getRole()) == null) {
            isValid = false;
        }

        if (user.getStatus() == null) {
            isValid = false;
        }

        return isValid;

    }

}
