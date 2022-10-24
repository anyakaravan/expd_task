import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitTestExamples {

    @Test
    public void inputTest(String[] myArray){
        Assert.assertEquals(myArray.length, 6); // to make sure we have all needed fields (First name, Last name, Address, City, State, Age)
    }

    @Test
    public void firstNameTest(String[] myArray){
        Assert.assertFalse(myArray[0].isBlank()); // to make sure it isn't blank
        Assert.assertTrue(myArray[0].matches("[a-zA-Z]+" )); // to make sure it has alphabetical input only
    }

    @Test
    public void lastNameTest(String[] myArray){
        Assert.assertFalse(myArray[1].isBlank()); // to make sure it isn't blank
        Assert.assertTrue(myArray[1].matches("[a-zA-Z]+" )); // to make sure it has alphabetical input only
    }

    @Test
    public void addressTest(String[] myArray){
        Assert.assertTrue(myArray[2].length()>1); // to make sure it isn't blank
    }

    @Test
    public void cityTest(String[] myArray){
        Assert.assertFalse(myArray[3].isBlank());
        Assert.assertTrue(myArray[3].length()>1);
        Assert.assertTrue(myArray[3].matches("[a-zA-Z]+" ));
    }

    @Test
    public void stateTest(String[] myArray){
        Assert.assertEquals(myArray[4].length(), 2); // to make sure it only has 2 letters for state abbreviations
        Assert.assertTrue(myArray[4].matches("[a-zA-Z]+" ));
    }

    @Test
    public void ageTest(String[] myArray){
        Assert.assertTrue(myArray[5].length()>0 && myArray[5].length()<125); // to make sure it is a valid age number
        Assert.assertTrue(myArray[5].matches("[0-9]+")); // to make sure it has digits only
    }
}
