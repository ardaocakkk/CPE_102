import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        // Create an ArrayList of PopularName objects to store the split data from the file.
        ArrayList<String> personArrayList = new ArrayList<>();
        
        ArrayList<PopularName> maleList = new ArrayList<>();
        ArrayList<PopularName> femaleList = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        //I used the openFile method to open the file if it is found.
        openFile(personArrayList);
        
        //I told how this works in the report.
        for (int i = 0; i < personArrayList.size(); i++) {
            //if i mod 5 == 1 then this i is index of boy.
            if (i % 5 == 1) {
                maleList.add(new PopularName(
                        personArrayList.get(i - 1), // Ranking 5n
                        personArrayList.get(i), // Name boy  5n+1 1mod5 = 1 = i
                        personArrayList.get(i + 1) // Number  5n+2
                ));
            }
            
            //If i mod 5 == 3 then this i is index of girl.
            if (i % 5 == 3) {
                femaleList.add(new PopularName(
                        personArrayList.get(i - 3), // Ranking 5n
                        personArrayList.get(i), // Name f 5n+3 3mod5 = 3 = i
                        personArrayList.get((i + 1) // Number 5n+4
                )));
            }
        }

        //I used the sortAlphabetically method to sort the arrays alphabetically.
        sortAlphabetically(maleList);
        sortAlphabetically(femaleList);

        //Ask user if they want to search for a name.
        System.out.println("---------------------------------");
        System.out.println("Do you want to search for a name and see its statistics (y/n)?");
        System.out.println("---------------------------------");
        String choice = input.next();

        while(choice.equals("y")) {
            System.out.println("---------------------------------");
            System.out.println("Enter a gender: ");
            String gender = input.next();
            System.out.println("---------------------------------");
            System.out.println("Enter a name: ");
            String name = input.next();
            System.out.println("---------------------------------");
            
            if (gender.equalsIgnoreCase("male")) {
            //I used the getInfo method to get the information of the PopularName object.
                getInfo(maleList, name);
            } else if (gender.equalsIgnoreCase("female")) {
                //I used the getInfo method to get the information of the PopularName object..
                getInfo(femaleList, name);
            }else {
                System.out.println("You made a typo.");
            }
            System.out.println("Do you want to search for a name and see its statistics (y/n)?");
            choice = input.next();
        }
        if(choice.equals("n")) {
            System.out.println("Goodbye! But i really wanted to show you how this program works.");
        }
    }


    public static void getInfo(ArrayList<PopularName> list, String name) {
        PopularName babyObject = null;
        for (PopularName baby : list) {
            if (baby.getName().equalsIgnoreCase(name)) {
                babyObject = baby;
            }
        }
        if(babyObject == null) {
            System.out.println("This name doesn't exist in this file.");
        }else {
            System.out.printf(
                    "---------------------------------\n" +
                            name + ": \n" +
                            "Index in sorted list: " + list.indexOf(babyObject) + "\n" +
                            "Rank in popularity: " + babyObject.getRanking() + "\n" +
                            "Number of babies: " + babyObject.getNumber() + "\n" +
                            "Percentage of babies: " + "%.2f%% \n---------------------------------\n", getPercentage(list,name)
            );
        }
    }

    public static float getPercentage(ArrayList<PopularName> list, String name) {
        
        float total = 0;
        float babyNumber = 0;
        for(PopularName baby : list) {
            //I used the Float.parseFloat method to convert the String to float.
            total += Float.parseFloat(baby.getNumber());
            if(name.equalsIgnoreCase(baby.getName())) {
                //I used the Float.parseFloat method to convert the String to float.
                babyNumber  = Float.parseFloat(baby.getNumber());
            }
        }
        //I used the formula to calculate the percentage.
        return (babyNumber)*100/total;
    }

    
    public static void sortAlphabetically(ArrayList<PopularName> list) {
        //Bubblesort algorithm.
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size()-1; j++) {
                System.out.println(list.get(j).getName() + " " + list.get(j+1).getName());
                if (list.get(j).getName().compareTo(list.get(j+1).getName()) > 0) {
                    PopularName temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
            }
        }
    }

    public static void openFile(ArrayList<String> personArrayList) {
        try {
            // Ask the user to enter the file name.
            Scanner input = new Scanner(System.in);
            System.out.println("---------------------------------");
            System.out.println("Please enter a File name: ");
            System.out.println("---------------------------------");
            String fileName = input.next();
            // Read the file and store the data in the ArrayList.
            Scanner inputPrinter = new Scanner(new File(fileName));
            System.out.println("File founded.");
            while (inputPrinter.hasNext()) {
                // Read the data line by line.
                String person = inputPrinter.nextLine();
                // Split the data by comma and store it in the ArrayList.
                StringTokenizer tokenizer = new StringTokenizer(person, ",");
                // Add the data to the ArrayList.
                while (tokenizer.hasMoreTokens()) {
                    personArrayList.add(tokenizer.nextToken());
                }
            }
            // Close the file.
            inputPrinter.close();
        // Catch the exception if the file is not found.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Please enter a valid file name.");
            System.exit(0); // Exit the program.
        }
        
    }
}

