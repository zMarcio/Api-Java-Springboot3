package br.com.webService.MongoDB.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.webService.MongoDB.demo.entity.Post;
import br.com.webService.MongoDB.demo.repository.PostRepository;
import br.com.webService.MongoDB.demo.service.exception.ObjectNotFoundException;

@Service
public class PostService {
	@Autowired
	private PostRepository repo;
	
	public List<Post> findByTitle(String txt){
		return repo.searchTitle(txt);
	}
	
	public List<Post> fullSearch(String txt, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(txt, minDate, maxDate);
	}
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
		
}
