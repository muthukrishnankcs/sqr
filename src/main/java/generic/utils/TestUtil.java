package generic.utils;

public class TestUtil {


	//return the test data from a test in a 2 dim array
	public static Object[][] getData(Xls_Reader xls , String testCaseName) {
		
		if(! xls.isSheetExist(testCaseName)){
			
			xls = null;
			return new Object[1][0];
		}
		
		
		int rows = xls.getRowCount(testCaseName);
		int cols = xls.getColumnCount(testCaseName);
		/*System.out.println(rows);
		System.out.println(cols); */

		Object[][] data = new Object[rows-1][cols];
		for(int rowNum = 2 ; rowNum<=rows ; rowNum++){
			
			for(int colNum = 0 ; colNum<cols ; colNum++){
				/*System.out.println(rowNum + "    row and col    "+colNum);
				System.out.println(xls.getCellData(testCaseName, colNum, rowNum));*/
				data[rowNum-2][colNum] = xls.getCellData(testCaseName, colNum, rowNum);
			}
		}
		xls = null;  // Release Memory
		return data;
	}
	
}
	
