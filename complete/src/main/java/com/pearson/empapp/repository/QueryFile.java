package com.pearson.empapp.repository;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class QueryFile {

	QueryFile() {
		URL queryFileUrl = Thread.currentThread().getContextClassLoader().getResource("queries.properties");
		try {
			queries.load(queryFileUrl.openStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getQuery(String queryName) {
		return (String)queries.getProperty(queryName);
	}
	
	private Properties queries = new Properties();
	
}
