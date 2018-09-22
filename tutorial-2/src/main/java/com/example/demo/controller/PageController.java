package com.example.demo.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	@RequestMapping("/viral") /*ini untuk alamat laman*/
	public String index() {
		return "viral"; /*ini untuk memanggil file html yang akan di render*/
	}
	
	@RequestMapping("/challenge")
	public String challenge(@RequestParam(value = "name", required = false, defaultValue = "kiki") String name, Model model) {
		model.addAttribute("name",name);
		return "challenge";
	}
	
	@RequestMapping(value = {"/challenge","challenge/{name}"})
	public String challengePath(@PathVariable Optional<String> name, Model model) {
		if (name.isPresent()) {
			model.addAttribute("name", name.get());
		}
		else {
			model.addAttribute("name", "KB");
		}
		return "challenge";
	}
	
	@RequestMapping("/generator")
	public String generator(@RequestParam(value= "a", defaultValue="0") int a, 
							@RequestParam(value="b", defaultValue="0") int b, Model model, String word) {
		model.addAttribute("a", a);
		model.addAttribute("b", b);
		
		if (a==0) {
			a=1;
		}
		
		if (a==0 && b==0) {
			
			word = "hm";
		}
		else {
			word = "h";
			while (a > 0) {
				word += "m";
				a--;
			}
			 String word2 = word;
			while (b > 1) {
				word += " " + word2;
				b--;
			}
		}
		model.addAttribute("word", word);
		return "generator";
	}
}
