package utils;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Utils {

	public static JSONArray readDataFromJson(String filePath, String key) throws Exception {
		Object object;
		JSONArray jsonArray;
		object = new JSONParser().parse(new FileReader(filePath));
		JSONObject jsonObject = (JSONObject) object;
		jsonArray = (JSONArray) jsonObject.get(key);
		return jsonArray;
	}

	public static void main(String[] args) throws Exception {
		readDataFromJson("src/main/resources/data.json", "data");
	}

	public static HashMap<String, String> readDataFromPropertyFile(String filePath) {
		HashMap<String, String> propertyData = new HashMap<>();
		try {
			FileInputStream fi = new FileInputStream(filePath);
			Properties propFile = new Properties();
			propFile.load(fi);
			Set<Object> allKeys = propFile.keySet();
			for (Object key : allKeys) {
				String propVal = propFile.getProperty(key.toString());
				propertyData.put(key.toString(), propVal);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return propertyData;

	}
}