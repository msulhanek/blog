package sk.posam.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.posam.blog.dto.UserDto;
import sk.posam.blog.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username){
        return new ResponseEntity(userService.getUser(username), HttpStatus.OK);
    }

    @PutMapping("{username}")
    public ResponseEntity editUser(@PathVariable String username, @RequestBody UserDto userDto){
        return new ResponseEntity(userService.editUser(username,userDto), HttpStatus.OK);
    }

}
