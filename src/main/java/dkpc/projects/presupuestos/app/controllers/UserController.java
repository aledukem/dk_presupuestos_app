package dkpc.projects.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dkpc.projects.model.User;
import dkpc.projects.service.UserService;
import dkpc.projects.util.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    protected UserService userService;

    protected ObjectMapper mapper;

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

    private boolean validate(User user) {
        boolean isValid = true;

        if (user.getName() == null) {
            isValid = false;
        }

        if (user.getLastname() == null) {
            isValid = false;
        }

        if (user.getUsername() == null) {
            isValid = false;
        }

        return isValid;

    }

}
