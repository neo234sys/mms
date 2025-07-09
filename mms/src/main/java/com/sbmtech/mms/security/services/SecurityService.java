package com.sbmtech.mms.security.services;
import org.springframework.stereotype.Service;

@Service("securityService")
class SecurityService {
	
	
	//ROLE_ADMIN, ROLE_MGT_ADMIN, ROLE_MGT_MANAGER, ROLE_MGT_SUPERVISOR, ROLE_MGT_SECURITY, ROLE_MGT_ELECTRICIAN,
//	ROLE_MGT_PLUMBER, ROLE_TENANT,

    public String getAdmin(){
        return "ADMIN";
    }
    public String getMgtAdmin(){
        return "MGT_ADMIN";
    }
    public String getBSMgtAdmin(){
        return "BS_MGT_ADMIN";
    }
   
    
    
    
}