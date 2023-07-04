import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        // Create a SinglyLinkedList of PopularName objects to store the split data from the file.
        SinglyLinkedList<String> personLinkedList = new SinglyLinkedList<>();

        // Create two SinglyLinkedList objects to store the PopularName objects.
        SinglyLinkedList<PopularName> maleLinkedList = new SinglyLinkedList<>();
        SinglyLinkedList<PopularName> femaleLinkedList = new SinglyLinkedList<>();

        Scanner input = new Scanner(System.in);

        //I used the openFile method to open the file if it is found.
        openFile(personLinkedList);
        System.out.println("---------------------------------");
        System.out.println("Please wait while the data is being processed...");


        for (int i = 0; i < personLinkedList.size(); i++) {
            //if i mod 5 == 1 then this i is index of boy.
            if (i % 5 == 1) {
                maleLinkedList.add(new PopularName(
                        personLinkedList.get(i - 1), // Ranking 5n
                        personLinkedList.get(i), // Name boy  5n+1 1mod5 = 1 = i
                        personLinkedList.get(i + 1) // Number  5n+2
                ));

            }

            //If i mod 5 == 3 then this i is index of girl.
            if (i % 5 == 3) {
                femaleLinkedList.add(new PopularName(
                        personLinkedList.get(i - 3), // Ranking 5n
                        personLinkedList.get(i), // Name f 5n+3 3mod5 = 3 = i
                        personLinkedList.get(i + 1) // Number 5n+4
                        ));
            }
        }
        //Ask the user for a process.
        printProcessInfo();
        int choice = input.nextInt();

        while (choice != 4) {
            if (choice == 1) {
                System.out.println("---------------------------------");
                System.out.println("Enter a gender: ");
                String gender = input.next();
                System.out.println("---------------------------------");
                System.out.println("Enter a name: ");
                String name = input.next();
                System.out.println("---------------------------------");

                if (gender.equalsIgnoreCase("male")) {
                    //I used the getInfo method to get the information of the PopularName object.
                    getInfo(maleLinkedList, name);
                } else if (gender.equalsIgnoreCase("female")) {
                    //I used the getInfo method to get the information of the PopularName object..
                    getInfo(femaleLinkedList, name);
                } else {
                    System.out.println("You made a typo.");
                }
                printProcessInfo();
                choice = input.nextInt();
            } else if (choice == 2) {
                System.out.println("---------------------------------");
                System.out.println("Enter a gender: ");
                String gender = input.next();
                System.out.println("---------------------------------");
                if (gender.equalsIgnoreCase("male")) {
                    PopularName mostPopularName = getFirstName(maleLinkedList);
                    System.out.println("Most popular name in boy is " +
                            mostPopularName.getName() +
                            " with " + mostPopularName.getNumber() + " babies.");
                } else if (gender.equalsIgnoreCase("female")) {
                    PopularName mostPopularName = getFirstName(femaleLinkedList);
                    System.out.println("Most popular name in girl is " +
                            mostPopularName.getName() +
                            " with " + mostPopularName.getNumber() + " babies.");
                }
                else {
                    System.out.println("You made a typo.");
                }
                printProcessInfo();
                choice = input.nextInt();

            } else if (choice == 3) {
                System.out.println("---------------------------------");
                System.out.println("Enter a gender: ");
                String gender = input.next();
                System.out.println("---------------------------------");
                System.out.println("Enter a letter: ");
                System.out.println("---------------------------------");
                String letter = input.next();
                if (gender.equalsIgnoreCase("male")) {
                    listByFirstLetter(maleLinkedList, letter);
                } else if (gender.equalsIgnoreCase("female")) {
                    listByFirstLetter(femaleLinkedList, letter);
                }else {
                    System.out.println("You made a typo.");
                }
                printProcessInfo();
                choice = input.nextInt();
            } else if (choice == 4) {
                System.out.println("Goodbye.");
                break;
            }else {
                System.out.println("Please enter a valid process");
                printProcessInfo();
                choice = input.nextInt();
            }
        }
    }

    //Find the given PopularNamed object with given name and print its information.
    public static void getInfo(SinglyLinkedList<PopularName> list, String name) {
        PopularName babyObject = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equalsIgnoreCase(name)) {
                babyObject = list.get(i);
            }
        }
        if (babyObject == null) {
            System.out.println("This name doesn't exist in this file.");
        } else {
            System.out.printf(
                    "---------------------------------\n" +
                            name + ": \n" +
                            "Index in sorted list: " + list.indexOf(babyObject) + "\n" +
                            "Rank in popularity: " + babyObject.getRanking() + "\n" +
                            "Number of babies: " + babyObject.getNumber() + "\n" +
                            "Percentage of babies: " + "%.2f%% \n---------------------------------\n", getPercentage(list, name)
            );
        }
    }

    //Calculate the given name's percentage in the given list.
    public static float getPercentage(SinglyLinkedList<PopularName> list, String name) {

        float total = 0;
        float babyNumber = 0;
        for (int i = 0; i < list.size(); i++) {
            PopularName baby = list.get(i);
            //I used the Float.parseFloat method to convert the String to float.
            total += Float.parseFloat(baby.getNumber());
            if (name.equalsIgnoreCase(baby.getName())) {
                //I used the Float.parseFloat method to convert the String to float.
                babyNumber = Float.parseFloat(baby.getNumber());
            }
        }
        //I used the formula to calculate the percentage.
        return (babyNumber) * 100 / total;
    }


    //Find the PopularName object that has most babies in the given list and return it.
    public static PopularName getFirstName(SinglyLinkedList<PopularName> list) {
        PopularName babyObject = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if ((Integer.parseInt(list.get(i).getNumber()) > Integer.parseInt(babyObject.getNumber()))) {
                babyObject = list.get(i);
            }
        }
        return babyObject;
    }

    //List the names that start with the given letter in the given list.
    public static void listByFirstLetter(SinglyLinkedList<PopularName> list, String letter) {
        //Created a new SinglyLinkedList to store the names that start with the given letter.
        SinglyLinkedList<String> names = new SinglyLinkedList<>();
        System.out.println("---------------------------------");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().startsWith(letter.toUpperCase())) {
                names.add(list.get(i).getName() + " " + list.get(i).getNumber());
            }
        }
        //If list is empty
        if(names.size() == 0){
            System.out.println("There is no name starting with: " + letter);
        } else {
            System.out.println("Names starting with " + letter + ": ");
            System.out.println(names.toString());
        }
    }


    //Print the process information.
    public static void printProcessInfo() {
        System.out.println("---------------------------------");
        System.out.println("Choose an option.");
        System.out.println("""
                1 Name Statistics
                2 Most Popular Name for a gender
                3 List by first Letter of the Baby Name
                4 Exit""");
        System.out.println("---------------------------------");
    }


    //Read the file and store the data in the given list.
    public static void openFile(SinglyLinkedList<String> list) {
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
                    list.add(tokenizer.nextToken());
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

