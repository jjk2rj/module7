package data;

import java.io.*;
import java.util.*;

import business.User;

public class UserIO {

	public static boolean add(User user, String filepath) {
		try {
			File file = new File(filepath);
			System.out.println(filepath);
			PrintWriter out = new PrintWriter(new FileWriter(file, true));
			out.println(user.getName() + "|" + user.getEmail() + "|"
					+ user.getCoursesString()+ "|" + user.getEmploymentStatus() + "|" + user.getExtrasString()); 

			out.close();
			return true;
		} catch (IOException e) {
			System.out.println(e);
			return false;
		}
	}

}