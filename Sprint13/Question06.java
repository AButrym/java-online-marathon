/*
Create the method writeFile(String filename, String text) that write the text to file as sequence of bytes in binary format.
For example, the text fragment
    "Hello!"
should be represented in the file as a sequence of 7-bit bytes without spaces between them:
    "100100011001011101100110110011011110100001"
If less than 7 bits are required to represent the character then add to binary sequence leading zeros '0'.
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Question06 {
    public static void writeFile(String filename, String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(String.format("%7s", Integer.toBinaryString(c)));
        }
        String encodedText = sb.toString().replace(" ", "0");
        try {
            Files.writeString(Path.of(filename), encodedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String text = "Hello!";
        String filename = "g:\\TEMP\\temp01.txt";
        writeFile(filename, text);
    }
}
