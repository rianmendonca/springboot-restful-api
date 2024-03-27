package com.springboot.restful.api.resources;

import com.springboot.restful.api.domain.Post;
import com.springboot.restful.api.domain.User;
import com.springboot.restful.api.dto.UserDTO;
import com.springboot.restful.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> usersList = userService.findAll();
        List<UserDTO> usersListDto = usersList.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersListDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> finById(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO userDTO) {
        User user = userService.fromDTO(userDTO);
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO userDTO, @PathVariable String id) {
        User user = userService.fromDTO(userDTO);
        user.setId(id);
        user = userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> finPosts(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user.getPosts());
    }

}
