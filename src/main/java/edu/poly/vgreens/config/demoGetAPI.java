package edu.poly.vgreens.config;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;

public class demoGetAPI {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://www.dongabank.com.vn/exchange/export");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			if (conn.getResponseCode() == 200) {
				Scanner scan = new Scanner(url.openStream());
				while (scan.hasNext()) {
					String temp = scan.nextLine();
					 temp = temp.replace("(", "");
					 temp = temp.replace(")", "");
					temp.trim();
					JSONParser parser = new JSONParser();
					JSONObject json = (JSONObject) parser.parse(temp);
					System.out.println("Temp: "+ json);
				}
			}
		} catch (Exception e) {
			System.out.println("Ex"+ e.getMessage());
		}
		
		
	}
}
