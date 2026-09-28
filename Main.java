package org.rugom.fileencdec;

import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Main {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the string");
		String str = scanner.nextLine();
//		String key = "rugom";
		int key = 15;
		int choice;
		
		File objFile = createFile();
		writeToFile(objFile, str);

		do {
			System.out.println("Enter the choice:");
			System.out.println("1.Encrypt\n2.Decrypt\n0.Exit");
			choice = scanner.nextInt();
			switch(choice) {
				case 0:
					System.out.println("Exiting the program...");
					scanner.close();
					return;
				case 1:
					encFile(objFile, key);
					break;
				case 2:
					decFile(objFile, key);
					break;
				default:
					System.out.println("Invalid choice!");
			}		
		}while(choice != 0);
		
		
		scanner.close();
		
	}

	private static File createFile() {
		File objFile =new File("D:\\cdacbatch2\\encryptdec\\info.txt");
		
		if(objFile.exists()) {
			System.out.println("File Already Exists!!!");
			return objFile;
		}else {
			System.out.println("Creating a new file");
			try {
				objFile.getParentFile().mkdirs();
				objFile.createNewFile();
				System.out.println("File Created Successfully!!!");
				return objFile;
			} catch (IOException e) {
				e.printStackTrace();
				return objFile;
			}
		}
	}
	
	private static void writeToFile(File file, String content) {

       try (FileOutputStream fileStream = new FileOutputStream(file);
             DataOutputStream dataStream = new DataOutputStream(fileStream)) {

            dataStream.writeUTF(content);
            System.out.println("Content Saved Successfully!!!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	 private static String readFromFile(File file) {

	        
	        try (FileInputStream fileStream = new FileInputStream(file);
	             DataInputStream dataStream = new DataInputStream(fileStream)) {

	            return dataStream.readUTF();

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }


	 private static void encFile(File inputFile, int key) {

	        // Step 1: Read original string from info.txt
	        String original = readFromFile(inputFile);
	        if (original == null) {
	            System.out.println("Could not read file for encryption!");
	            return;
	        }

	        // Step 2: Add 15 to each character's ASCII value
	        StringBuilder encrypted = new StringBuilder();
	        for (int i = 0; i < original.length(); i++) {
	            char encChar = (char) (original.charAt(i) + key);
	            encrypted.append(encChar);
	        }

	        // Step 3: Save encrypted string to enc_info.txt
	        File encFile = new File("D:\\cdacbatch2\\encryptdec\\enc_info.txt");
	        writeToFile(encFile, encrypted.toString());
	        System.out.println("Encrypted text : " + encrypted.toString());
	        System.out.println("Encrypted and saved to: enc_info.txt");
	    }

	    private static void decFile(File inputFile, int key) {

	        // Step 1: Read encrypted string from enc_info.txt
	        File encFile = new File("D:\\cdacbatch2\\encryptdec\\enc_info.txt");
	        if (!encFile.exists()) {
	            System.out.println("Encrypted file not found! Please encrypt first.");
	            return;
	        }

	        String encrypted = readFromFile(encFile);
	        if (encrypted == null) {
	            System.out.println("Could not read encrypted file!");
	            return;
	        }

	        // Step 2: Subtract 15 from each character's ASCII value
	        StringBuilder decrypted = new StringBuilder();
	        for (int i = 0; i < encrypted.length(); i++) {
	            char decChar = (char) (encrypted.charAt(i) - key);  // ✅ ASCII - 15
	            decrypted.append(decChar);
	        }

	        // Step 3: Save decrypted string to dec_info.txt
	        File decFile = new File("D:\\cdacbatch2\\encryptdec\\dec_info.txt");
	        writeToFile(decFile, decrypted.toString());
	        System.out.println("Decrypted text : " + decrypted.toString());
	        System.out.println("Decrypted and saved to: dec_info.txt");
	    }
	}
