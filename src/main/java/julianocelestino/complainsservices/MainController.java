package julianocelestino.complainsservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/demo")
public class MainController {

	@Autowired
	private ComplainRepository repository;
	
	@GetMapping(path="/add")
	public @ResponseBody String addNewComplain () {

		Complain complain = new Complain();
		complain.setTitle("Exemplo Chumbado");
		complain.setDescription("Exemplo Chumbado");
		complain.setDescription("Exemplo Chumbado");
		complain.setLocale("Exemplo Chumbado");
		complain.setCompany("Exemplo Chumbado");

		repository.save(complain);

		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Complain> getAllComplains() {
		return repository.findAll();
	}
}
