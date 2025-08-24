package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    //DataProvider 1:
    @DataProvider(name="LoginData")
    public String [][] getData() throws IOException{
        String path = ".\\testData\\OpenCart_LoginData.xlsx";// Pitching the path of an Excel that is located inside the folder name: testData, which is located within this project.

        Excel_Utility exlUtil = new Excel_Utility(path);// Creating an Object the Excel_Utility class, that is located inside the package name: utilities, which is located within this project.
        int totalRows = exlUtil.getRowCount("Sheet1");
        int totalColums = exlUtil.getCellCount("Sheet1", 1);

        String loginData [][] = new String [totalRows][totalColums];//Created for 2-tow Dimension array which can store LoginData.
        for(int i = 1; i <= totalRows; i++){// Reading the data from Excel, and storing it in 2-tow Dimension array.
            for(int j = 0; j < totalColums; j++ ){//  "i" represents the Rows and "j" represents Columns.
                loginData[i-1][j] = exlUtil.getCellData("Sheet1", i, j);
            }
        }
        return loginData;// Returning 2-tow Dimension array

    }

    //DataProvider 2
    //DataProvider 3
    //DataProvider 4
}
