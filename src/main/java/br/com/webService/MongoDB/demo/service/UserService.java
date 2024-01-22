package br.com.webService.MongoDB.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.webService.MongoDB.demo.DTO.UserDTO;
import br.com.webService.MongoDB.demo.entity.User;
import br.com.webService.MongoDB.demo.repository.UserRepository;
import br.com.webService.MongoDB.demo.service.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAllUsers(){
		return repo.findAll();
	}
	
	public User findByIdUser(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insertUser(User user) {
		return repo.insert(user);
	}
	
	public void  deleteUser(String id) {
		findByIdUser(id);
		repo.deleteById(id);
	}
	
	public User updateUser(User user) {
		User newUser = findByIdUser(user.getId());
		updateUserMethod(newUser,user);
		return repo.save(newUser);
	}

	private void updateUserMethod(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());	
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(),objDTO.getName(),objDTO.getEmail());
	}
	
}
