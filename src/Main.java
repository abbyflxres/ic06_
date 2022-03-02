import java.io.*;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
	// write your code here

            Scanner keyboard = new Scanner(System.in);
            String name, breed;
            int age, count=0;

            Dog[] dogPound = new Dog[10];

            //read from binary file Dogs.dat
                File binaryFile = new File("Dogs.dat");
                //check to see if file exists && non zero size
        System.out.println("Previously saved Dogs from binary file:");
        if (binaryFile.exists() && binaryFile.length() > 1L)
        {
            try {
                ObjectInputStream fileReader= new ObjectInputStream(new FileInputStream(binaryFile));
                //read the entire array into dogPound
                //readObject returns Object data type
                // Dog[] = Object
                dogPound = (Dog[]) fileReader.readObject();
                //loop through array && print out all objects
                //use while loop bc we dont know how big the array is/ will be
                while(dogPound[count] != null)
                    System.out.println(dogPound[count++]);
                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
            //binaryFile = dogs.dat


        }
        else
            System.out.println("[None, please enter new dog data]");



        do {
            System.out.print("Please enter dog's name (or \"quit\" to exit): ");
            name = keyboard.nextLine();
            if (name.equalsIgnoreCase("quit"))
                break;
            System.out.print("Please enter dog's breed: ");
            breed = keyboard.nextLine();
            System.out.print("Please enter dog's age: ");
            age = keyboard.nextInt();

            //todo: create a new dog object, add it to array, && increment count
            dogPound[count++] = new Dog(name, breed, age);
            //count++ is a post fix operator

            //get rid of dangling \n
            keyboard.nextLine();

        } while(true);

        //after user enters "quit", write the dogPound array to binary file
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            fileWriter.writeObject(dogPound);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    }

