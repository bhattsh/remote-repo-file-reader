package com.example.bitBucketFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;

public class BitBucketFileReader {
	String username = "username";
	String apppassword = "mypassword";
	String gitUrl = "urlOfFile";

	public static void main(String[] args) {
		BitBucketFileReader rdr = new BitBucketFileReader();
		rdr.readGit();
	}

	private void readGit() {
		ArrayList<String> lines = new ArrayList<>();

		byte[] encodedAuth = Base64.getEncoder().encode((username + ":" + apppassword).getBytes());

		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) new URL(gitUrl).openConnection();
			connection.setRequestProperty("Authorization", "Basic " + new String(encodedAuth));
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println("data found:=>" + line);
				lines.add(line);
			}
			System.out.println(line);
			connection.disconnect();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
