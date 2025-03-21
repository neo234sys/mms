package com.sbmtech.mms.util;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.Element;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.w3c.dom.NodeList;

import com.sbmtech.mms.constant.CommonConstants;
import com.sbmtech.mms.exception.BusinessException;
import com.sbmtech.mms.models.Role;
import com.sbmtech.mms.models.RoleEnum;
import com.sbmtech.mms.models.User;
import com.sbmtech.mms.repository.UserRepository;
import com.sbmtech.mms.security.services.UserDetailsImpl;

public class CommonUtil {

	public static ZoneId ZONE_DUBAI = ZoneId.of("Asia/Dubai");

	public static String getStringValue(String fieldName) {
		String result = "";
		if (fieldName != null && !fieldName.equalsIgnoreCase("-1")) {
			return fieldName;
		}
		return result;
	}

	public static LocalDateTime getLocalDateTimefromString(String paramVal, String datePattern) {

		if (paramVal != null && !paramVal.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.ENGLISH);
			try {
				if (paramVal != null && paramVal.trim().length() > 0 && !(paramVal.equalsIgnoreCase("null"))) {

					Date date = sdf.parse(paramVal);
					return date.toInstant().atZone(ZONE_DUBAI).toLocalDateTime();
				} else {
					return null;
				}
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	public static LocalDate getLocalDatefromString(String paramVal, String datePattern) {

		if (paramVal != null && !paramVal.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.ENGLISH);
			try {
				if (paramVal != null && paramVal.trim().length() > 0 && !(paramVal.equalsIgnoreCase("null"))) {

					Date date = sdf.parse(paramVal);
					return date.toInstant().atZone(ZONE_DUBAI).toLocalDate();
				} else {
					return null;
				}
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	public static Date getDatefromLocalDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZONE_DUBAI).toInstant());
	}
//	public static LocalDate getLocalDatefromDate(Date date,String datePattern) {
//		if(StringUtils.isBlank(datePattern)) {
//			datePattern=CommonConstants.DATE_ddMMyyyy;
//		}
//		SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.ENGLISH);
//		Date dateOb=sdf.parse(date);
//		return  Date.from(localDate.atStartOfDay(ZONE_DUBAI).toInstant());
//	}

	public static String getFormattedDate(Object dateToFormat) {
		String strDate = null;
		try {
			if (dateToFormat != null) {
				SimpleDateFormat format2 = new SimpleDateFormat(CommonConstants.DATE_yyyy_MM_dd, Locale.ENGLISH);
				SimpleDateFormat format1 = new SimpleDateFormat(CommonConstants.DATE_ddMMyyyy, Locale.ENGLISH);
				strDate = format1.format(format2.parse(dateToFormat.toString()));
			}
		} catch (Exception e) {
		}
		return strDate;
	}

	public static String getFormattedDateByPattern(Object dateToFormat, String fromPattern, String toPattern) {
		String strDate = null;
		try {
			if (dateToFormat != null) {
				SimpleDateFormat format1 = new SimpleDateFormat(toPattern, Locale.ENGLISH);
				SimpleDateFormat format2 = new SimpleDateFormat(fromPattern, Locale.ENGLISH);
				strDate = format1.format(format2.parse(dateToFormat.toString()));
			}
		} catch (Exception e) {
		}
		return strDate;
	}

	public static String getFormattedDatePattern(Object dateToFormat, String pattern) {
		String strDate = null;
		try {
			if (dateToFormat != null) {
				if (pattern.equalsIgnoreCase(CommonConstants.DATE_yyyy_MM_dd)) {
					SimpleDateFormat format2 = new SimpleDateFormat(CommonConstants.DATE_yyyy_MM_dd, Locale.ENGLISH);
					SimpleDateFormat format1 = new SimpleDateFormat(CommonConstants.DATE_ddMMyyyy, Locale.ENGLISH);
					strDate = format1.format(format2.parse(dateToFormat.toString()));
				}
				if (pattern.equalsIgnoreCase(CommonConstants.DATE_ddMMyyyy)) {
					SimpleDateFormat format1 = new SimpleDateFormat(CommonConstants.DATE_ddMMyyyy, Locale.ENGLISH);
					SimpleDateFormat format2 = new SimpleDateFormat(CommonConstants.DATE_yyyy_MM_dd, Locale.ENGLISH);
					strDate = format1.format(format2.parse(dateToFormat.toString()));
				}
				if (pattern.equalsIgnoreCase(CommonConstants.DATE_ddMMyyyy_HH_MM_SS)) {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
					strDate = sdf.format(dateToFormat);
				}
				if (pattern.equalsIgnoreCase(CommonConstants.DATEyyyyMMdd)) {
					SimpleDateFormat sdf = new SimpleDateFormat(CommonConstants.DATEyyyyMMdd);
					strDate = sdf.format(dateToFormat);
				}
				if (pattern.equalsIgnoreCase(CommonConstants.DATE_yyyyMMddHHmmss)) {
					SimpleDateFormat sdf = new SimpleDateFormat(CommonConstants.DATE_yyyyMMddHHmmss);
					strDate = sdf.format(dateToFormat);
				}
				if (pattern.equalsIgnoreCase(CommonConstants.DATE_ddMMyyyy + " " + CommonConstants.DATE_HHMMSS)) {
					SimpleDateFormat sdf = new SimpleDateFormat(
							CommonConstants.DATE_ddMMyyyy + " " + CommonConstants.DATE_HHMMSS);
					strDate = sdf.format(dateToFormat);
				}
				if (pattern.equalsIgnoreCase(CommonConstants.DATE_MMddyyyy_HH_MM)) {
					SimpleDateFormat sdf = new SimpleDateFormat(CommonConstants.DATE_MMddyyyy_HH_MM);
					strDate = sdf.format(dateToFormat);
				}
				if (pattern.equalsIgnoreCase(CommonConstants.DATE_ddMMyyyy_HH_MM_SS_SSS)) {
					SimpleDateFormat sdf = new SimpleDateFormat(CommonConstants.DATE_ddMMyyyy_HH_MM_SS_SSS);
					strDate = sdf.format(dateToFormat);
				}
				if (pattern.equalsIgnoreCase(CommonConstants.DATE_ddMMyyyyHHmmss)) {
					SimpleDateFormat sdf = new SimpleDateFormat(CommonConstants.DATE_ddMMyyyyHHmmss);
					strDate = sdf.format(dateToFormat);
				}
				if (pattern.equalsIgnoreCase(CommonConstants.DATE_dd_MMM_yyyy)) {
					SimpleDateFormat format2 = new SimpleDateFormat(CommonConstants.DATE_dd_MMM_yyyy, Locale.ENGLISH);
					SimpleDateFormat format1 = new SimpleDateFormat(CommonConstants.DATE_ddMMyyyy, Locale.ENGLISH);
					strDate = format1.format(format2.parse(dateToFormat.toString()));
				}
			}
		} catch (Exception e) {
		}
		return strDate;
	}

	public static String getSystemDateFromFormat(String format) {
		SimpleDateFormat formatter = null;
		long sysdate = System.currentTimeMillis();
		Date sys_date = new Date(sysdate);
		String system_date = "";
		if (format != null) {
			formatter = new SimpleDateFormat(format);
			system_date = formatter.format(sys_date);
		} else {
			return "";
		}
		return system_date;
	}

	public static Date getCurrentDate(int daysToAdd) {
		Calendar calNow = Calendar.getInstance();
		calNow.add(Calendar.DAY_OF_MONTH, daysToAdd);
		return calNow.getTime();
	}
	
	public static LocalDate getCurrentLocalDate() {
		LocalDate currentDate = LocalDate.now(ZONE_DUBAI);
		return currentDate;
	}
	
	

	public static Integer getIntegerFromString(String text) {
		Integer intText = null;
		if (text != null) {
			intText = Integer.parseInt(text);
		}
		return intText;
	}

	public static Date getDatefromString(String paramVal, String datePattern) {
		if (paramVal != null && !paramVal.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.ENGLISH);
			try {
				if (paramVal != null && paramVal.trim().length() > 0 && !(paramVal.equalsIgnoreCase("null"))) {
					return sdf.parse(paramVal);
				} else {
					return null;
				}
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	public static String formatSysDate(String dateStr) throws ParseException {
		if (dateStr != null && !dateStr.isEmpty()) {
			SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
			Date date = (Date) formatter.parse(dateStr);
			return getStringDatefromDate(date);
		} else {
			return dateStr;
		}
	}

	public static String getStringDatefromDate(Date date) {
		String datePattern = "dd/MM/yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.ENGLISH);
		try {
			if (date != null && !(date.toString().equalsIgnoreCase("null"))) {
				return sdf.format(date);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static String getStringTimestampfromDate(Date date) {
		String datePattern = "dd/MM/yyyy HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern, Locale.ENGLISH);
		try {
			if (date != null && !(date.toString().equalsIgnoreCase("null"))) {
				return sdf.format(date);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static String getIntValuesFromBoolean(String booleanValue) {
		if ((booleanValue != null) && (booleanValue.trim().length() > 0) && (booleanValue.equalsIgnoreCase("false"))) {
			return "0";
		} else {
			return "1";
		}
	}

	public static int compareDates(String endDate, String startDate) throws ParseException {
		try {
			int result = 0;
			SimpleDateFormat formatter = new SimpleDateFormat(CommonConstants.DATE_ddMMyyyy);
			Date date1 = formatter.parse(endDate);
			Date date2 = formatter.parse(startDate);
			result = date1.compareTo(date2);
			if (result < 0)
				return 1;
		} catch (Exception e) {
		}
		return 0;
	}

	private static Calendar getCalendarInstanceWithoutTime() {
		Calendar calNow = Calendar.getInstance();
		calNow.set(Calendar.HOUR, 0);
		calNow.set(Calendar.HOUR_OF_DAY, 0);
		calNow.set(Calendar.MINUTE, 0);
		calNow.set(Calendar.SECOND, 0);
		calNow.set(Calendar.MILLISECOND, 0);
		return calNow;
	}

	public static boolean isExpiredWithTime(Date dateTime) {
		boolean isExpired = false;
		if (dateTime != null) {
			Calendar calGiven = Calendar.getInstance();
			Calendar calNow = Calendar.getInstance();
			calGiven.setTime(dateTime);
			calGiven.add(Calendar.MINUTE, 15);
			if (calGiven.before(calNow)) {
				isExpired = true;
			}
		} else {
			isExpired = true;
		}
		return isExpired;
	}

	public static boolean isExpired(String date, String datePattern) throws ParseException {
		boolean isExpired = false;
		if (date != null && !date.isEmpty() && !date.equalsIgnoreCase("null")) {
			Calendar calNow = getCalendarInstanceWithoutTime();
			Calendar calGiven = getCalendarInstanceWithoutTime();
			SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
			calGiven.setTime(formatter.parse(date));
			if (calGiven.before(calNow)) {
				isExpired = true;
			}
		} else {
			isExpired = true;
		}
		return isExpired;
	}

	public static Long getLongValofObject(Object objVal) {
		if (objVal != null) {
			try {
				return Long.parseLong(objVal.toString());
			} catch (NumberFormatException e) {
				return 0L;
			}
		} else {
			return 0L;
		}
	}

	public static Double getDoubleValofObject(Object objVal) {
		if (objVal != null) {
			try {
				return Double.parseDouble(objVal.toString());
			} catch (NumberFormatException e) {
				return 0.0;
			}
		} else {
			return 0.0;
		}
	}

	public static String getStringValofObject(Object objVal) {
		if (objVal != null) {
			return objVal.toString();
		} else {
			return "";
		}
	}

	public static BigInteger getBigIntValofObject(Object objVal) {
		if (objVal != null) {
			try {
				return BigInteger.valueOf(CommonUtil.getLongValofObject(objVal));
			} catch (NumberFormatException e) {
				return BigInteger.valueOf(0);
			}
		} else {
			return BigInteger.valueOf(0);
		}
	}

	public static Integer getIntValofObject(Object objVal) {
		if (objVal != null) {
			try {
				return Integer.parseInt(objVal.toString());
			} catch (NumberFormatException e) {
				return 0;
			}
		} else {
			return 0;
		}
	}

	public static boolean getBooleanValofObject(Object objVal) {
		if (objVal != null) {
			try {
				return (Integer.parseInt(objVal.toString()) == 1 ? true : false);
			} catch (NumberFormatException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public static Float getFloatValofObject(Object objVal) {
		if (objVal != null) {
			try {
				return Float.parseFloat(objVal.toString());
			} catch (NumberFormatException e) {
				return 0.0F;
			}
		} else {
			return 0.0F;
		}
	}

	public static boolean checkDateWithGivenDays(String startDateStr, String endDateStr, int days)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(CommonConstants.DATE_ddMMyyyy);
		Date endDate = formatter.parse(endDateStr);
		Calendar currentDate = getCalendarInstanceWithoutTime();
		if (startDateStr != null && !startDateStr.isEmpty()) {
			Date startDate = formatter.parse(startDateStr);
			currentDate.setTimeInMillis(startDate.getTime());
		}
		currentDate.add(Calendar.DATE, days);
		return currentDate.getTime().before(endDate) || currentDate.getTime().equals(endDate);
	}

	public static String getCurrentDate() {
		String s = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			s = dateFormat.format(cal.getTime());
		} catch (Exception e) {
			e.getStackTrace();
		}
		return s;
	}

	public static String getFormattedAppDate(String date) {
		String formattedDate = "";
		try {
			if (date != null && !date.isEmpty()) {
				String[] splitedDate = date.split("\\s+");
				if (splitedDate != null) {
					formattedDate = splitedDate[0];
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return formattedDate;
	}

	public static String getDefaultIfNull(String paramVal, String defaultVal) {
		if ((paramVal != null) && (paramVal.trim().length() > 0) && !(paramVal.equalsIgnoreCase("null"))) {
			return paramVal;
		} else {
			return defaultVal;
		}
	}

	public static String getSuccessOrFailureMessageWithId(int result) {
		if (result == CommonConstants.SUCCESS_CODE) {
			return CommonConstants.SUCCESS_DESC;
		} else if (result == CommonConstants.PENDING_CODE) {
			return CommonConstants.PENDING_DESC;
		} else {
			return CommonConstants.FAILURE_DESC;
		}
	}

	public static String maskEmail(String email) {
		return email.replaceAll("(^[^@]{3}|(?!^)\\G)[^@]", "$1*");
	}

	public static byte[] getFileFromPath(String fullPath) {
		Path path = Paths.get(fullPath);
		byte[] data = null;
		try {
			data = Files.readAllBytes(path);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return data;
	}

	public static Map<String, Object> createResponse(int responseCode, String responseMessage, Object data) {
		Map<String, Object> response = new HashMap<>();
		response.put("responseCode", responseCode);
		response.put("responseMessage", responseMessage);
		if (data != null) {
			response.put("data", data);
		}
		return response;
	}

	public static boolean validateBasicAuth(String authHeader, String username, String password) {
		if (authHeader == null || !authHeader.startsWith("Basic ")) {
			return false;
		}
		String base64Credentials = authHeader.substring("Basic ".length()).trim();
		byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64Credentials);
		String credentials = new String(decodedBytes);
		final String[] values = credentials.split(":", 2);
		return values.length == 2 && values[0].equals(username) && values[1].equals(password);
	}

	public static String generateRandomPwd() {
		String characters = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
		String pwd = RandomStringUtils.random(10, characters);
		return pwd;
	}

	public static ResponseEntity<Map<String, Object>> buildErrorResponse(String errorMessage, boolean isOTPVerified,
			Integer integer) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("responseCode", CommonConstants.FAILURE_CODE);
		errorResponse.put("responseDesc", CommonConstants.FAILURE_DESC);
		errorResponse.put("message", errorMessage);
		errorResponse.put("isOTPVerified", isOTPVerified);
		errorResponse.put("subscriberId", integer);
		return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
	}
	
	
	public static String validateAttachment(byte[] attachment) throws Exception{
			
		String contentType = new Tika().detect(attachment);

		if(!Arrays.asList(CommonConstants.ATTACHMENT_IMAGE_TYPES).contains(contentType.toLowerCase())) {
			throw new BusinessException("Invalid File Type, Only jpg, png, pdf Allowed",null);
		}
		
		long size = attachment.length;
		size = size/1024;
		if(size> CommonConstants.MAX_IMAGE_SIZE*1024) {
			throw new BusinessException("Max 5 MB Allowed",null);
		}
		
		return contentType;
	}
	
	public static String getContentTypeOfAttachment(byte[] byteVal){
		String contType=null;
		
		contType=new Tika().detect(byteVal);
		
		return contType;
	} 

}