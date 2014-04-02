package ua.nau.rentcar.util;

/**
 * This class describes all users' groups <br>
 * Permissions must be sorted from minimum permission users to maximum permissions users
 * ANONYMOUS, USER, ADMIN
 * {@code 
 * UserTypeEnum admin = UserTypeEnum.ADMIN;
 * UserTypeEnum user = UserTypeEnum.USER;
 * admin > user;}
 * @author Geniy
 *
 */
public enum UserTypeEnum {
	ANONYMOUS, USER, ADMIN;
	
}
