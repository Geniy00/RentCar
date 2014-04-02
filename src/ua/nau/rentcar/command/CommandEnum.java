package ua.nau.rentcar.command;

import ua.nau.rentcar.util.UserTypeEnum;

public enum CommandEnum {
	WITHOUT_PARAMETER(UserTypeEnum.ANONYMOUS), 
	UNKNOWN_COMMAND(UserTypeEnum.ANONYMOUS), 
	
	SIGN_UP(UserTypeEnum.ANONYMOUS),
	LOGIN(UserTypeEnum.ANONYMOUS),
	LOGOUT(UserTypeEnum.ANONYMOUS),
	
	LOAD_CAR_LIST(UserTypeEnum.USER),
	ADD_CAR(UserTypeEnum.ADMIN),
	LOAD_CAR(UserTypeEnum.USER),
	EDIT_CAR(UserTypeEnum.ADMIN),
	
	LOAD_REQUEST(UserTypeEnum.USER),
	ADD_REQUEST(UserTypeEnum.USER),
	LOAD_REQUEST_LIST(UserTypeEnum.USER),
	EXAMINE_REQUEST(UserTypeEnum.ADMIN),
	
	LOAD_ORDER(UserTypeEnum.USER),
	ADD_ORDER(UserTypeEnum.ADMIN),
	EDIT_ORDER(UserTypeEnum.ADMIN),
	LOAD_ORDER_LIST(UserTypeEnum.USER);
	
	/**
	 * It describes which users can execute this command
	 */
	private UserTypeEnum userTypeEnum;
	
	private CommandEnum(UserTypeEnum userTypeEnum){
		this.userTypeEnum = userTypeEnum;
	}
	
	public UserTypeEnum getUserType(){
		return userTypeEnum;
	}
}
