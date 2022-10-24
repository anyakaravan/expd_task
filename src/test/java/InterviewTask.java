import java.lang.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class InterviewTask {

    public static void main(String args[]) throws Exception {

        // Pulling the information from input.txt file to String[][] myArray

        Scanner sc = new Scanner(new BufferedReader(new FileReader("src/test/resources/input.txt")));

        int rows = 10;
        int columns = 6;

        String [][] myArray = new String[rows][columns];

        while(sc.hasNextLine()) {
            for (int i=0; i<myArray.length; i++) {
                String[] person = sc.nextLine().split("\",\"");
                for (int j=0; j<person.length; j++) {
                    myArray[i][j] = removeQuotes(person[j]); // *removeQuotes method implementation - line 80
                }
            }
        }

        // Now I want to see how many occupants in each household, so I am creating a Set (to avoid duplicates) and adding information to it  from String[][] myArray based on the indexes correlating to address, city and state.
        // * Please find the implementation of genericAddress method on line 70

        Set<String> allHouseholds = new HashSet<>(); // all addresses
        for (String[] each : myArray){
            allHouseholds.add(genericAddress(each[2] + " " + each[3] + " " + each[4]));
        }

        // Now I should be able to create new Map with the String for key and List<String[]> for value, where full information of people with the same address will be stored.

        Map<String, List<String[]>> answer = new HashMap<>();

        for (String eachAddress : allHouseholds) {
            List<String[]> temp = new ArrayList<>(); // creating temporary List to store adults' info in it later on
            int count = 0; // to count how many people in every household
            for (int j = 0; j < myArray.length; j++) { // start looping through each of our people
                if(eachAddress.equals(genericAddress(myArray[j][2] + " " + myArray[j][3] + " " + myArray[j][4]))){  // index 2 is street address, 3 is for city and 4 is for state. We converting it to the generic address and compare it to each address from our set
                    count++;
                    if (Integer.parseInt(myArray[j][5] ) >= 18) { // also checking if that person is an adult
                        temp.add(myArray[j]); // we add only adults to our temp List because we don't need minors due to the requirements
                    }
                }
            }
            answer.put("Address: " + eachAddress.toUpperCase() + ". Number of occupants: " + count, temp);
        }

        // method to print our final result
        for (Map.Entry<String, List<String[]>> each : answer.entrySet()) {
            System.out.println(each.getKey());
            printSortedHousehold(each.getValue());
        }

    }

    public static String genericAddress (String text){
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if(text.charAt(i)!=',' && text.charAt(i)!='.'){
                result += ""+text.charAt(i);
            }
        }
        return result.toLowerCase().trim();
    }

    public static String removeQuotes (String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != '"') {
                result += "" + text.charAt(i);
            }
        }
        return result;
    }

    public static void printSortedHousehold (List<String[]> list){
        String[] fullNames = new String[list.size()];
        int i = 0;
        for (String[] each: list) {
            fullNames[i++] = each[1] + " " + each[0]; // getting last name and first name to String[] fullNames
        }
        Arrays.sort(fullNames); // this method sorts my new created String[] fullNames by last name

        for (int j = 0; j < fullNames.length; j++){ // this
            String [] temp = fullNames[j].split(" ");
            String lastName = temp[0]; // separated last name
            String firstName = temp[1]; // separated first name
            for (int k = 0; k < list.size(); k++) { // this loop helps me find person with matching appropriate names
                if(list.get(k)[1].equals(lastName) && list.get(k)[0].equals(firstName)){
                    System.out.println(Arrays.toString(list.get(k)));
                }
            }
        }

    }

}

