package com.asia.scom.httpredirector;

import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HttpRedirector {
   @RequestMapping(value = "**")
   public ResponseEntity<String> getBaseUrl(@RequestHeader HttpHeaders headers, HttpServletRequest request)
         throws MalformedURLException {
      String url = getURLBase(request);

      System.out.println("REQUEST: " + url);
      RestTemplate restTemplate = new RestTemplate();
      headers.remove("Accept-Encoding");
      headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

      HttpEntity<String> entity = new HttpEntity<>(headers);

      ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

      String contents = result.getBody();
      contents = contents.replaceAll("\"subscriptionContentType\":\"METER\"", "\"subscriptionContentType\":\"FREE\"");
      HttpHeaders headersResponse = new HttpHeaders();
      for (Entry<String, List<String>> g : result.getHeaders().entrySet()) {
         if (!g.getKey().equals("Content-Length")) {
            headersResponse.put(g.getKey(), g.getValue());
         }
      }

      return new ResponseEntity<>(contents, headersResponse, HttpStatus.OK);

   }

   public String getURLBase(HttpServletRequest request) throws MalformedURLException {
      return request.getRequestURL().toString() + "?" + request.getQueryString();
   }

}