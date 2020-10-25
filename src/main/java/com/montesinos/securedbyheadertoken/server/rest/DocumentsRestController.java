package com.montesinos.securedbyheadertoken.server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.montesinos.securedbyheadertoken.server.domain.Document;

@RestController
@RequestMapping("/documents")
public class DocumentsRestController {

	@Value("${apikey.user.name}")
	private String apiKeyUserName;
	
	@Value("${apikey.user.value}")
	private String apiKeyUserValue;
	
	@Value("${apikey.name}")
	private String apiKeyName;
	
	@Value("${apikey.value}")
	private String apiKeyValue;
	
	@Value("${extranet.api.url}")
	private String extranetApiUrl;	
	
	@PostMapping("/download")
	public List<Document> downloadDocuments(@RequestBody List<Document> documents){
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();        
        headers.add(this.apiKeyUserName, this.apiKeyUserValue);
        headers.add(this.apiKeyName, this.apiKeyValue);

        HttpEntity<List<Document>> entity = new HttpEntity<>(documents, headers);
        
		return restTemplate.postForObject(this.extranetApiUrl + "/documents/download", entity, List.class);		
	}
}
