import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class representing a user in the online shopping system.
 */
class User {
    private String username;
    private String password;
    private static ArrayList<String> userList = new ArrayList<>();


    /**
     * Constructor to initialize a User object with a username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void addUserToHistory () {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream("users.txt", true));

            writer.println("");

            // Save username and password
            writer.println(username + "/" + password);

            userList.add(username);

            System.out.println("\nUser saved successfully");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Something went wrong!\n");
        }
    }

    public static ArrayList<String> loadPastUsersList() {
        try {
            File file = new File("users.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String[] userInfo = line.split("/");

                if (userInfo.length == 2) {
                    String username = userInfo[0];
                    userList.add(username);
                }
            }
        }
        catch (IOException e) {
            System.out.println("\nSomething went wrong.");
        }
        return userList;
    }
}
