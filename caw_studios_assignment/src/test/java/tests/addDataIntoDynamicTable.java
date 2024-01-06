package tests;

import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Event;

import commonMethods.BaseTest;
import commonMethods.Events;
import pages.DynamicTable;

public class addDataIntoDynamicTable extends BaseTest{
	
	@Test
	public void addingDataIntoTableAndValidation() {
		
		System.out.println("clcking on table data button");
		DynamicTable dynamicTable = new DynamicTable();
		Events.clickOnElement(dynamicTable.tableDataButton());
		
		System.out.println("clearing exsting text in json text area");
		Events.clearingTextInInputField(dynamicTable.jsonTextAreaInputField());
		
		System.out.println("entering text in json text area");
		Events.enterTextInField(dynamicTable.jsonTextAreaInputField(),data.toString());
		
		System.out.println("clicking on refresh table button");
		Events.clickOnElement(dynamicTable.refreshTableButton());
		
		System.out.println("validating table data");
		dynamicTable.validatingTableData(data);
	}
}
