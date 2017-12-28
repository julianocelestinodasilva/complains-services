package julianocelestino.complainsservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping(path="/complain")
public class MainController {

	@Autowired
	private ComplainRepository repository;
	
	@PostMapping
	public ResponseEntity<?> ingestComplain (@RequestBody Complain complain ) {
		repository.save(complain);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.buildAndExpand(complain.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Complain> getAllComplains() {
		return repository.findAll();
	}
}