package com.FirstAssignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileAssignment {
	final static String sMainFolder = "C:\\Main";

	public static void main(String[] args) throws IOException {
				
		File f = new File(sMainFolder);
		if(!f.exists()) {
			f.mkdir();
		}
		
		System.out.println("Welcome to LockedMe.com");
		System.out.println("The Perfect Place to Lock Your Assets");
		System.out.println("Prototype Application created by Zeenath");
		System.out.println("*****************************************");
		while(true) {
			System.out.println("");
			System.out.println("Choose the operation you need to perform by selecting the appropriate number:");
			System.out.println("1. Display all files in the Main folder");
			System.out.println("2. Display Secondary Menu to Add/Delete/Search File");
			System.out.println("3. Exit the program");
			Scanner sc = new Scanner(System.in);
			try {
				int choose_1 = sc.nextInt();
						
				switch(choose_1) {
					case 1:{
						File[] fnames = getAllFiles(sMainFolder);
						
						System.out.println("The Main folder contents are:");
						for(int i=0;i<fnames.length;i++) {
							if(fnames[i].isFile()) {
								System.out.println("File: " +fnames[i]);
							}
							else if(fnames[i].isDirectory()) {
								System.out.println("Directory: " +fnames[i]);
							}
							
						}
						break;
					}
					case 2:{
						boolean bSecondMenu=true;
						while(bSecondMenu) {
							System.out.println("");
							System.out.println("Displaying File Menu");
							System.out.println("Choose the operation you need to perform by selecting the appropriate number:");
							
							System.out.println("1. Add new file/folder");
							System.out.println("2. Delete file/folder");
							System.out.println("3. Search file using text");
							System.out.println("4. Return to Previous Menu");
							System.out.println("5. Exit the program");
							Scanner sc1 = new Scanner(System.in);
							try {
								int choose_2=sc1.nextInt();
								
								switch(choose_2) {
									case 1:{
										Scanner sc2 = new Scanner(System.in);
										System.out.println("Choose 1 to add a Folder, choose 2 to add a File");
										int nFileAddOption = sc2.nextInt();
										if(nFileAddOption == 1) {
											System.out.println("Input the full folder path to be added");
											String sNewFolderName = sc2.next();
											File newdir = new File(sNewFolderName);
											if(newdir.getAbsolutePath().startsWith(sMainFolder)) {
												newdir.mkdirs();
												System.out.println("Folder created");
											}else {
												System.out.println("The new folder path is not under Main folder");
											}
											
										}
										else if(nFileAddOption == 2) {
											System.out.println("Input the full path of the file to be added");
											String sNewFileName = sc2.next();
											File newFile = new File(sNewFileName);
											
											File filePath = new File(newFile.getParent());
											
											if(filePath.getAbsolutePath().startsWith(sMainFolder)) {
												if(!filePath.exists()) {
													filePath.mkdirs();
												}
												newFile.createNewFile();
												System.out.println("File created");
											}else {
												System.out.println("The new folder path is not under Main folder");
											}											
										}
										else {
											System.out.println("The option you chose is not available.Please choose the correct option.");
										}
										break;
									}
									case 2:{
										System.out.println("Input the full path of the file/folder to be deleted");
										Scanner sc2 = new Scanner(System.in);
										String sNewFileName = sc2.next();
										
										File sDeleteFile = new File(sNewFileName);
										if(sNewFileName.startsWith(sMainFolder)) {
											if(sDeleteFile.exists()) {
												if(sDeleteFile.isDirectory()) {
													File[] contents = sDeleteFile.listFiles();
													for(int i=0;i<contents.length;i++) {
														contents[i].delete();
													}
												}
												sDeleteFile.delete();																								
												System.out.println("The File/Folder deleted");
											}else {
												System.out.println("The File/Folder do not exist");
											}
											
										}else {
											System.out.println("Please enter the correct file name. Either the file do not exist in main folder or the path is not correct");
										}
										break;
									}
									case 3:{
										System.out.println("Please enter the search string");
										Scanner sc2 = new Scanner(System.in);
										String sSearchText = sc2.next();
										boolean bMatch = false;
										
										File[] fnames = getAllFiles(sMainFolder);										
										for(int i=0;i<fnames.length;i++) {
											if(fnames[i].getName().startsWith(sSearchText)) {
												if(fnames[i].isFile()) {
													System.out.println(fnames[i]);
													bMatch=true;												}
												
											}											
										}
										if(!bMatch) {
											System.out.println("No matching files found !!!!");
										}
										break;
									}
									case 4:{
										bSecondMenu=false;
										break;
									}
									case 5:{
										System.out.println("The program exists. Thank you.");
										System.exit(0);
										break;
									}
									default:{
										System.out.println("The option you chose is not available.Please choose the correct option.");
										break;
									}
								}
							}catch(InputMismatchException e) {
								System.out.println("The option you chose is not available.Please choose the correct option.");
							}
						}
						break;
					}
					case 3:{
						System.out.println("The program exists. Thank you.");
						System.exit(0);
						break;
					}
					default:{
						System.out.println("The option you chose is not available.Please choose the correct option.");
						break;
					}
				
				}
			}catch(InputMismatchException e) {
				System.out.println("The option you chose is not available.Please choose the correct option.");
			}
		}
	}
	
	private static ArrayList<File> FileList=new ArrayList<File>();
	
	public static File[] getAllFiles(String directory) {
		FileList.clear();
		listOfFiles(directory);
		
		File[] dir_contents = new File[FileList.size()];
		
		for(int i=0;i<FileList.size();i++) {
			dir_contents[i]=(File) FileList.get(i);
		}
		
		return dir_contents;
	}
	
	public static void listOfFiles(String directory){
		File dir = new File(directory);
		File[] fname = dir.listFiles();
		
		for(int i=0;i<fname.length;i++) {
			if(fname[i].isFile()) {
				FileList.add(fname[i]);
			}else {
				FileList.add(fname[i]);
				listOfFiles(fname[i].getAbsolutePath());
			}
		}
	}
}

