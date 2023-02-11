package com.example.DiningReview.controller;

import com.example.DiningReview.model.DiningReview;
import com.example.DiningReview.model.User;
import com.example.DiningReview.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController{

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping()
    public User createNewUser(@RequestBody User user){
        if(this.userRepository.existsByUsername(user.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return this.userRepository.save(user);
    }


    @GetMapping()
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable("username") String username){
        Optional<User> userToFind = Optional.ofNullable(this.userRepository.findByUsername(username));
        if(!userToFind.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } return new ResponseEntity<>(userToFind.get(), HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public User updateUser(@PathVariable("username") String name, @RequestBody User user){
        Optional<User> userToUpdateOptional = Optional.ofNullable(this.userRepository.findByUsername(name));
        if(userToUpdateOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        User userToUpdate = userToUpdateOptional.get();
        userToUpdate.setName(user.getName());
        userToUpdate.setCity(user.getCity());
        userToUpdate.setState(user.getState());
        userToUpdate.setZipcode(user.getZipcode());
        userToUpdate.setEggAllergies(user.getEggAllergies());
        userToUpdate.setPeanutAllergies(user.getPeanutAllergies());
        userToUpdate.setDairyAllergies(user.getDairyAllergies());
        return this.userRepository.save(userToUpdate);
    }


}
