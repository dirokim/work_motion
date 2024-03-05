package com.workmotion.app.api;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/*")
public class ApiTossController {

	@GetMapping("toss")
	public String apiToss(Model model) throws Exception{
		model.addAttribute("page", "/api/toss");
		return "index";
	}
}
