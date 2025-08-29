package com.api.crud.controllers;

import com.api.crud.model.UserModel;
import com.api.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ArrayList<UserModel> getUsers(){
        return this.userService.getUsers();
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user){
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getUserById(@PathVariable("id") Long id){
        return this.userService.getById(id);
    }

//    @PutMapping(path = "/{id}")
//    public UserModel updateUserById(@RequestBody UserModel request, @PathVariable("id") Long id){
//        return this.userService.updateById(request, id);
//    }

    @PutMapping(path = "{id}")
    public UserModel updateUserById(@RequestBody UserModel request ,@PathVariable Long id){
        return this.userService.updateById(request, id);
    }


    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.userService.deleteUser(id);

        if (ok){
            return "Cuenta con id " + id + " delete! ";
        }else{
            return "Error, tenemos un problema generando el delete a la cuenta con id  " + id;
        }

    }
}
