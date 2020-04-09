package com.neueda.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neueda.POJO.ShortURLPOJO;

/**
 * @author Prasanth Controller class handles restapi end points
 */

@Controller
public class ShortURLRestapiController {

	private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String loadIndex() {
		return "index";
	}

	private Map<String, ShortURLPOJO> shortenUrlList = new HashMap<>();

	/**
	 * @author Prasanth POST call to create a short URL. This will call the
	 *         getRandomChars() methods to generate a random string and set it with
	 *         the POJO class
	 * @return Short URL
	 */

	@RequestMapping(value = "/shortenurl", method = RequestMethod.POST)
	public ResponseEntity<Object> getShortenUrl(HttpServletRequest request, @RequestBody ShortURLPOJO shortenUrl)
			throws MalformedURLException {
		LOGGER.debug("+++++++++++++ Create Short URL API has been triggered ++++++++++");
		HttpServletRequest tmp = request;
		String hostname = tmp.getLocalName();
		int port = tmp.getLocalPort();
		String randomChar = getRandomChars();
		setShortUrl(randomChar, shortenUrl, hostname, port);
		return new ResponseEntity<Object>(shortenUrl, HttpStatus.OK);
	}

	/**
	 * @author Prasanth GET call get all the list of urls stored in shortenUrlList
	 *         hashmap
	 * @return A list of POJO objects which contains the short and full URLS
	 */

	@RequestMapping(value = "/getURLs", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllUrls(HttpServletResponse response) throws IOException {
		LOGGER.debug("------------------- Returning all urls stored in the hashmap ----------");
		return new ResponseEntity<Object>(shortenUrlList.values(), HttpStatus.OK);
	}

	@RequestMapping(value = "/s/{randomstring}", method = RequestMethod.GET)
	public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString)
			throws IOException {
		response.sendRedirect(shortenUrlList.get(randomString).getFull_url());
	}

	private void setShortUrl(String randomChar, ShortURLPOJO shortenUrl, String hostname, int port)
			throws MalformedURLException {
		String tmpURL = "http://" + hostname + ":" + Integer.toString(port) + "/s/" + randomChar;
		shortenUrl.setShort_url(tmpURL);
		LOGGER.debug("********** Short URL Got created ************" + tmpURL);
		shortenUrlList.put(randomChar, shortenUrl);
	}

	private String getRandomChars() {
		String randomStr = "";
		String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for (int i = 0; i < 5; i++)
			randomStr += possibleChars.charAt((int) Math.floor(Math.random() * possibleChars.length()));
		return randomStr;
	}

}
