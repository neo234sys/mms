package com.sbmtech.mms.util;

import java.io.StringWriter;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.constant.NotificationConstants;
import com.sbmtech.mms.dto.NotifEmailDTO;

@Component
@Service

@PropertySource("classpath:notification-constants.properties")
public class NotificationUtil {

	@Autowired
	private VelocityEngine velocityEngine;

//	ResourceBundle resource = null;
//	
//	
//
//	private String getNotifProperty(String key) {
//		String val = "";
//		if (key != null && !key.isEmpty()) {
//			if (resource == null) {
//				resource = ResourceBundle.getBundle(NotificationConstants.NOTIF_CONSTANTS);
//			}
//			val = (String) resource.getString(key);
//		}
//		return val;
//	}
	
//	 public String generateEmailFromTemplate(String templateName, Map<String, Object> variables) {
//	        Template template = velocityEngine.getTemplate("email_templates/" + templateName);
//
//	        VelocityContext context = new VelocityContext();
//	        for (Map.Entry<String, Object> entry : variables.entrySet()) {
//	            context.put(entry.getKey(), entry.getValue());
//	        }
//
//	        StringWriter writer = new StringWriter();
//	        template.merge(context, writer);
//
//	        return writer.toString();
//	    }

//	public String getServiceNotifEmailSubject() {
//
//		return getNotifProperty(NotificationConstants.NOTIF_OTP_SUBJECT_KEY);
//	}
	
	
	
//	public String getTenantAcctCreationSubject() {
//
//		return getNotifProperty(NotificationConstants.NOTIF_TENANT_ACCT_SUBJECT_KEY);
//	}
	
	public String prepareOTPEmail(NotifEmailDTO dto){
		
		StringWriter mergedContent = new StringWriter();
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("dataHolderDTO", dto);
		
		
		//velocityEngine.mergeTemplate(getNotifProperty(NotificationConstants.NOTIF_OTP_TEMPLATE_KEY), "UTF-8", velocityContext, mergedContent);
		
		 Template template = velocityEngine.getTemplate(NotificationConstants.TEMPLATE_PATH + NotificationConstants.NOTIF_OTP_TEMPLATE_KEY);
		 template.merge(velocityContext, mergedContent);
		
		return mergedContent.toString();
	}
	
	public String prepareAcctActivationEmail(NotifEmailDTO dto){
		
		StringWriter mergedContent = new StringWriter();
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("dataHolderDTO", dto);
		
		
		//velocityEngine.mergeTemplate(getNotifProperty(NotificationConstants.NOTIF_ACCT_ACTIVE_TEMPLATE_KEY), "UTF-8", velocityContext, mergedContent);
		//return mergedContent.toString();
		
		 Template template = velocityEngine.getTemplate(NotificationConstants.TEMPLATE_PATH  + NotificationConstants.NOTIF_ACCT_ACTIVE_TEMPLATE_KEY);
		 template.merge(velocityContext, mergedContent);
		 return mergedContent.toString();
	     
		
	}
	
	public String prepareTenantAcctCreationEmail(NotifEmailDTO dto){
		
		StringWriter mergedContent = new StringWriter();
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("dataHolderDTO", dto);
		
		
		//velocityEngine.mergeTemplate(getNotifProperty(NotificationConstants.NOTIF_TENANT_ACCT_ACTIVE_TEMPLATE_KEY), "UTF-8", velocityContext, mergedContent);
		Template template = velocityEngine.getTemplate(NotificationConstants.TEMPLATE_PATH + NotificationConstants.NOTIF_TENANT_ACCT_ACTIVE_TEMPLATE_KEY);
		 template.merge(velocityContext, mergedContent);
		
		return mergedContent.toString();
	}
	
	public String prepareTenantAcctCreationEmailExistingUser(NotifEmailDTO dto){
		
		StringWriter mergedContent = new StringWriter();
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("dataHolderDTO", dto);
		
		
		//velocityEngine.mergeTemplate("email_templates/" +NotificationConstants.NOTIF_TENANT_ACCT_ACTIVE_TEMPLATE_KEY_EXT_USER);
		Template template = velocityEngine.getTemplate(NotificationConstants.TEMPLATE_PATH  + NotificationConstants.NOTIF_TENANT_ACCT_ACTIVE_TEMPLATE_KEY_EXT_USER);
		 template.merge(velocityContext, mergedContent);
		
		return mergedContent.toString();
	}
	
	public String prepareReserveUnitEmail(NotifEmailDTO dto){
		
		StringWriter mergedContent = new StringWriter();
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("dataHolderDTO", dto);
		
		
		//velocityEngine.mergeTemplate(getNotifProperty(NotificationConstants.NOTIF_UNIT_RESERVE_TEMPLATE_KEY), "UTF-8", velocityContext, mergedContent);
		Template template = velocityEngine.getTemplate(NotificationConstants.TEMPLATE_PATH  + NotificationConstants.NOTIF_TENANT_ACCT_ACTIVE_TEMPLATE_KEY_EXT_USER);
		 template.merge(velocityContext, mergedContent);
		
		return mergedContent.toString();
	}
	
//	public String getReserveUnitNotifEmailSubject() {
//
//		return getNotifProperty(NotificationConstants.NOTIF_UNIT_RESERVE_SUBJECT_KEY);
//	}
	
	
}