package julianocelestino.complainsservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping(path="/complains")
public class MainController {

	@Autowired
	private ComplainRepository repository;
	
	@PostMapping
	public ResponseEntity<?> ingestComplain (@RequestBody Complain complain ) {
		// TODO Valid complain (not null)
		repository.save(complain);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(complain.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping
	public @ResponseBody Iterable<Complain> getAllComplains() {
		return repository.findAll();
	}
}
