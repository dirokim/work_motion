package com.workmotion.app.errors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error/*")
public class ErrorController {

	@GetMapping("error404")
	public String Error404(Model model) {
		model.addAttribute("error", "404");
		return "errors/error";
	}

	@GetMapping("error400")
	public String Error400(Model model) {	
		model.addAttribute("error", "400");
		return "errors/error";
	}

	@GetMapping("error403")
	public String Error403(Model model) {
		model.addAttribute("error", "403");
		return "errors/error";
	}

	@GetMapping("error500")
	public String Error500(Model model) {		
		model.addAttribute("error", "500");
		return "errors/error";
	}
}
