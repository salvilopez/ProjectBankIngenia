package com.ingenia.projectbank.controller;

import com.ingenia.projectbank.model.Account;
import com.ingenia.projectbank.model.BankCard;
import com.ingenia.projectbank.model.User;
import com.ingenia.projectbank.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://bank-ingenia-front.vercel.app", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    /**
     * method return all users
     * @return List<User>
     */
    @GetMapping("/users")
    @ApiOperation(value = "encuentra todos los usuarios ")
    public List<User> findAllUsers() {
        log.debug("Rest request all users");
        return userService.findAllUsers();
    }
    /**
     * method return One account for ID
     *
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    @ApiOperation(value = "encuentra un usuario por su id")
    public ResponseEntity<User> findOneUserById(@ApiParam("Clave primaria del usuario") @PathVariable Long id) {
        log.debug("Rest request a account with id: "+id);
        Optional<User> userOpt = userService.findOneUserById(id);
        if (userOpt.isPresent())
            return ResponseEntity.ok().body(userOpt.get());

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * method return One account for ID
     *
     * @param email
     * @return
     */
    @GetMapping("/user-mail/{email}")
    @ApiOperation(value = "encuentra un usuario por su email")
    public ResponseEntity<User> findOneUserByEmail(@ApiParam("Clave email del usuario") @PathVariable String email) {
        log.debug("Rest request a account with email: "+email);
        if(email!=null){
            User userOpt = userService.findUserByEmail(email);
            if (userOpt != null)
                return ResponseEntity.ok().body(userOpt);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    /**
     * create user
     * @param user
     * @return ResponseEntity
     * @throws URISyntaxException
     */
    @PostMapping("/user")
    @ApiOperation(value = "crea un usuario")
    public ResponseEntity<User> createUser(@ApiParam("Objeto User para Crearlo")@RequestBody User user) throws URISyntaxException{
        log.debug("Create User");
        User resultado=null;
        if (user.getId()!=null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        resultado=userService.createUser(user);

        if(resultado==null)return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return  ResponseEntity.created(new URI("/api/user/"+resultado.getId())).body(resultado);
    }

    /**
     * Modificar User
     * @param user
     * @return
     */
    @PutMapping(value = "/user")
    @ApiOperation(value = "modificar usuario")
    public ResponseEntity<User> modifyUser(@ApiParam("Objeto User a modificar")@RequestBody User user) throws URISyntaxException{
        log.debug("Modify user");
        User resultado=null;
        if (user.getId()==null) {
            log.warn("Updating user without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        resultado = userService.updateUser(user);
        if(resultado==null)return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       return ResponseEntity.ok().body(resultado);

    }
    /**
     *method Delete One  BankCard
     * @param id
     * @return no content
     */
    @DeleteMapping(value = "/user/{id}")
    @ApiOperation(value = "Borra un usuario por id")
    public ResponseEntity<Void> deleteOne(@ApiParam("Clave primaria del usuario")@PathVariable("id") Long id) {
        log.debug("Delete user");
        if(id!=null){
            userService.deleteOneUserById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    /**
     * method Delete all User
     * @return no content
     */
    @ApiOperation(value = "Borra todos los usuarios")
    @DeleteMapping(value = "/users")
    public ResponseEntity<Void> deleteAll() {
        log.debug("DeleteAll");
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
