package julianocelestino.complainsservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@Controller
@RequestMapping(path="/complains")
public class MainController {

	@Autowired
	private ComplainRepository repository;
	
	@PostMapping
	public ResponseEntity<?> ingestComplain (@RequestBody Complain complain ) {
		if (!complain.valid()) {
			throw new IllegalArgumentException(Complain.MSG_INVALID);
		} else {
			repository.save(complain);
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest().path("/{id}")
					.buildAndExpand(complain.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
	}

	@GetMapping(path="/{id}")
	public @ResponseBody Complain getComplain(@PathVariable Long id) {
		return repository.findOne(id);
	}

	@GetMapping
	public @ResponseBody Iterable<Complain> getAllComplains() {
		return repository.findAll();
	}

	@ExceptionHandler(IllegalArgumentException.class)
	void handleBadRequests(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), Complain.MSG_INVALID);
	}
}
