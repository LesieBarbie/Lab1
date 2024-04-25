package ExtraTasks.Task2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        User user = new User("Pushkina Lesia", 19);
        try {
            String serialized = SerializationUtils.serializeToString(user);
            System.out.println("Serialized form: " + serialized);

            User deserializedUser = (User) SerializationUtils.deserializeFromString(serialized);
            System.out.println("Deserialized User: " + deserializedUser);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
