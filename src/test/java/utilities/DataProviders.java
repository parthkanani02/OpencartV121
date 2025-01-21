package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {
		
		String path = ".\\testData\\Opencart_Login_DDT.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcell = xlutil.getCellCount("Sheet1", 1);
		System.out.println("rows: "+totalrows+"and col: "+ totalcell);
		
		String logindata[][] = new String[totalrows][totalcell];
	
		for(int r=1;r<=totalrows;r++)
		{
			for(int c=0;c<totalcell;c++)
			{
				logindata[r-1][c] = xlutil.getCellData("Sheet1", r, c);
						
			}
		}
		return logindata;
		
	}
	
}
