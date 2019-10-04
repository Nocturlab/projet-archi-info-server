package fr.nocturlab.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@Slf4j
public class PageHandler {

	@Autowired
	Environment env;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getMain(Map<String, Object> model) {
		return "main";
	}

	@RequestMapping(value="error-{statusCode}.html", method=RequestMethod.GET)
	public String getError(Map<String, Object> model, @PathVariable int statusCode) {
		model.put("statusCode", statusCode);
		return "error";
	}
}