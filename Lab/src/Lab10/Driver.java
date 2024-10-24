package Lab10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
    static LHash<Martyr> LMartyr = new LHash<>(9974);
    static QHash<Martyr> QMartyr = new QHash<>(9974);
    static DHash<Martyr> DMartyr = new DHash<>(9974);
    static int count = 0;

    public static void main(String[] args) {
//        HashReadFromFile();
        LMartyr.avgCollision();
        QMartyr.avgCollision();
        DMartyr.avgCollision();
        System.out.println("Total records processed: " + count);
    }

    public static void HashReadFromFile() {
        File file = new File("C:\\Users\\coolnet\\Desktop\\COMP242 - DATA STRUCTURES\\Lab 0\\src\\data.csv");
        if (file.exists()) {
            try (Scanner scan = new Scanner(file)) {
                if (scan.hasNextLine()) {
                    scan.nextLine(); // Skip the header
                }

                while (scan.hasNextLine()) {
                    String line = scan.nextLine().trim();
                    String[] splitLine = line.split(",");

                    if (splitLine.length == 6) { // Ensure correct number of columns
                        String name = splitLine[0];
                        String dateDeath = splitLine[1];
                        int age = splitLine[2].isEmpty() ? -1 : Integer.parseInt(splitLine[2].trim());
                        String location = splitLine[3];
                        String district = splitLine[4];
                        String gender = splitLine[5];

                        Martyr InsertMartyr = new Martyr(name, dateDeath, age, location, district, gender);
                        count++;
                        LMartyr.add(InsertMartyr);
                        QMartyr.add(InsertMartyr);
                        DMartyr.add(InsertMartyr);
                    } else {
                        System.err.println("Invalid record: " + line);
                    }
                }
            } catch (FileNotFoundException e) {
            	System.out.println("File not found: " + file.getPath());
                e.printStackTrace();
            } catch (NumberFormatException e) {
            	System.out.println("Error parsing number: " + e.getMessage());
            } catch (Exception e) {
            	System.out.println("Error processing file: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
        	System.out.println("File does not exist: " + file.getPath());
        }
    }
}
