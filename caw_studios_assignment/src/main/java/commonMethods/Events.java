package commonMethods;

import org.openqa.selenium.WebElement;

public class Events {

	public static void clickOnElement(WebElement element) {
		element.click();
	}

	public static void enterTextInField(WebElement element, String inputText) {
		element.sendKeys(inputText);
	}

	public static void clearingTextInInputField(WebElement element) {
		element.clear();
	}
}