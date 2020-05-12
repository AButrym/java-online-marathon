import java.io.IOException;

/*
Write a method to read from a file a character-oriented data and return these data as String object.
For example, next fragment:
...
after reading will be transform to the next String object:
"This is text fragment\nthat contains some text data\nand can be read!"
*/

class Question05 {
    public static String readFile(String filename) {
        try {
            return java.nio.file.Files.readString(java.nio.file.Path.of(filename));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
    }
}
