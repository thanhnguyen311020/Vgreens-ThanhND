package edu.poly.vgreens.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vgreens")
public class FavoriteController {
	
	@GetMapping("favorite")
	public String Favorite() {
		return "site/favorite/favorite";
	}

}
