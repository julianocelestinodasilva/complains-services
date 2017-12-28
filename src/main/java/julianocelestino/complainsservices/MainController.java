package julianocelestino.complainsservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/complain")
public class MainController {

	@Autowired
	private ComplainRepository repository;
	
	@PostMapping
	public @ResponseBody Complain ingestComplain (String title, String description, String company) {
		Complain complain = new Complain(title, description, company);
		repository.save(complain);
		return complain;
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Complain> getAllComplains() {
		return repository.findAll();
	}
}
