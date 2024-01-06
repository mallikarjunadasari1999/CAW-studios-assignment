package pages;

import java.util.List;

import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import org.json.simple.JSONObject;

import commonMethods.BaseTest;

public class DynamicTable extends BaseTest {

	public WebElement tableDataButton() {
		return driver.findElement(By.xpath("//*[text()='Table Data']"));
	}

	public WebElement jsonTextAreaInputField() {
		return driver.findElement(By.id("jsondata"));
	}

	public WebElement refreshTableButton() {
		return driver.findElement(By.id("refreshtable"));
	}

	public void validatingTableData(JSONArray data) {
		SoftAssert softAssert = new SoftAssert();
		String[][] tableData = takingTableDataIntoArray();
		for (int i = 0; i < data.size(); i++) {
			JSONObject jsonObj = (JSONObject) data.get(i);
			int j = 0;
			for (Object key : jsonObj.keySet()) {
				softAssert.assertEquals(jsonObj.get(key).toString(), tableData[i][j], key + " data is not matched");
				System.out.println("Expected " + key + " = " + jsonObj.get(key) + " and " + " actual " + key + " = "
						+ tableData[i][j]);
				j++;
			}
		}
		softAssert.assertAll();
	}

	public String[][] takingTableDataIntoArray() {
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='dynamictable']/tr/td/.."));
		int rowCount = rows.size();
		String[][] data = new String[rowCount][];
		for (int i = 0; i < rowCount; i++) {
			List<WebElement> columns = driver
					.findElements(By.xpath("(//table[@id='dynamictable']/tr/td/parent::tr)[" + (i + 1) + "]/td"));
			int columneCount = columns.size();
			data[i] = new String[columneCount];
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = driver
						.findElement(By.xpath(
								"(//table[@id='dynamictable']/tr/td/parent::tr)[" + (i + 1) + "]/td[" + (j + 1) + "]"))
						.getText();
			}
		}
		return data;
	}
}