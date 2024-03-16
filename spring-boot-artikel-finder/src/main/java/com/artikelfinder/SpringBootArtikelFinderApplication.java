package com.artikelfinder;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// text varmı array boşmu der die das sonuç arrayi
@RestController
@SpringBootApplication
public class SpringBootArtikelFinderApplication {
	@CrossOrigin(origins = "*")
	@RequestMapping("/artikel-finder")
	public String home(@RequestBody Search src) throws IOException {
		System.out.println(src.word);
		FileSearch fileSearch = new FileSearch();
		String result = fileSearch.parseFile("src/main/resources/de-en.txt", src.word.toLowerCase()).get(0);

		if (!result.equals("NotFound")) {
			if (result.endsWith("f")) {
				// die fem
				result = "die " + fileSearch.toDisplayCase(src.word.toString());
			} else if (result.endsWith("m")) {
				// der mas
				result = "der " + fileSearch.toDisplayCase(src.word.toString());
			} else {
				// das neut
				result = "das " + fileSearch.toDisplayCase(src.word.toString());
			}
		}

		return result;
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringBootArtikelFinderApplication.class, args);
	}

}
