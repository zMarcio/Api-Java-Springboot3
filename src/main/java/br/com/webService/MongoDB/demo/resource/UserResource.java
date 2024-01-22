package br.com.webService.MongoDB.demo.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.webService.MongoDB.demo.DTO.UserDTO;
import br.com.webService.MongoDB.demo.entity.User;
import br.com.webService.MongoDB.demo.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	@Autowired
	private UserService service;

	@GetMapping
 	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAllUsers();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping("/{id}")
 	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findByIdUser(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

	@PostMapping
 	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User obj = service.fromDTO(objDto);
		obj = service.insertUser(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
 	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
 	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.updateUser(obj);
		return ResponseEntity.noContent().build();
	}
	
//	@GetMapping("/{id}/posts")
// 	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
//		User obj = service.findByIdUser(id);
//		return ResponseEntity.ok().body(obj.getPosts());
//	}
	
}
