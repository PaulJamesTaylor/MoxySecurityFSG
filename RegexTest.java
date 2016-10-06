

public class RegexTest {
	 
	String testString;
     		   
	public RegexTest(){};
	
			   
	public String getTestString() {
	    
		return testString;
	}
	
	
	public void setTestString(String value) {
			   
	    testString = value;
	}
	
		
	public boolean testRegex(String value) {
			   
			if(testString.replace(" ", "").equalsIgnoreCase(value.replace(" ", "")))
				return true;
			else
				return false;
			   
    }
		   
	
	public static void main(String args[]) {
			   
	    RegexTest regexTest = new RegexTest();
		
		regexTest.setTestString("Approve Orders ");
		
		System.out.println("The testing string is: " + regexTest.getTestString());
				
		String testString1 = new String("Approve Orders     ");
		String testString2 = new String("Test");
		
		boolean test1 = regexTest.testRegex(testString1);
		boolean test2 = regexTest.testRegex(testString2);
		
		System.out.println("Result of test1 is: " + test1);
		System.out.println("Result of test2 is: " + test2);
		
	}
	
}