package API_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import org.junit.Test;
import org.json.*;

public class apiTests {
	
	private static HttpURLConnection connection;
	
	// function necessary for a variety of requests, only parameter is url string
	public String getJsonRequest(String newUrl) {
		BufferedReader reader;
		String line;
		StringBuffer responseContent = new StringBuffer();
		String output = "";
		try {
			URL url = new URL(newUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			int status = connection.getResponseCode();
			if (status > 299) {
				fail("unable to connect.");
			}
			else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				reader.close();
			}
			output = responseContent.toString();
		}
		catch (MalformedURLException e) {
			fail(e.toString());
		}
		catch (IOException e) {
			fail(e.toString());
		}
		return output;
	}
	
	@Test
	public void validateStJohnCharity () {
		String criteria = "St John";
		String output = "";
		JSONArray jsonObject = new JSONArray(getJsonRequest("https://api.trademe.co.nz/v1/Charities.json"));
		for (int i = 0; i < jsonObject.length(); i++) {
			String str = jsonObject.getJSONObject(i).getString("Description");
			if (str.equals(criteria)) {
				output = str;
			}
		}
		assertEquals(criteria, output);
	}
	
}
