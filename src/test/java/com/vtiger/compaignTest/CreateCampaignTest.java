package com.vtiger.compaignTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.SeleniumWebDriverLibrary;
import com.sdet34l1.genericLibrary.WorkbookLibrary;
import com.vtiger.webElementsRepo.CampaignInformationPage;
import com.vtiger.webElementsRepo.CampaignPage;
import com.vtiger.webElementsRepo.CreateNewCampaignPage;

public class CreateCampaignTest extends BaseClass{
	
	String campaignname;
	CampaignPage campaign;
	CreateNewCampaignPage createNewCampaign;
	CampaignInformationPage campaignInformation;
	
	
	@Test(groups = {"sanity","baseclass"})
	public void createCampaignTest()
	{
		campaignname = WorkbookLibrary.getDataFromExcel("Campaign", 2, 1) + randomNumber;
		
		campaign = new CampaignPage(driver);
		createNewCampaign = new CreateNewCampaignPage(driver);
		campaignInformation = new CampaignInformationPage(driver);		
				
				
		home.clickCampaign(seleniumLibrary);
		campaign.createCampaignImg();
		createNewCampaign.enterCampaignNameAndSave(campaignname);
		
		seleniumLibrary.waitUntilElementVisible(campaignInformation.getCampaignNameInformation(seleniumLibrary));
		
		javalibrary.assertionThroughIfCondition(campaignInformation.getCampaignName(), campaignname, "campaign");
		
	}
}
