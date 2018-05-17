package cn.dlj.utils;

import java.util.HashMap;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

/**
 * 短信
 * @author 余狄龙
 * @date 2018年5月17日
 */
public class SmsUtils {

	/** 主帐号 **/
	public static String accountSid = "8a216da862b36cbc0162b898d7cf040e";
	/** 令牌 **/
	public static String authToken = "b2d86a6b0b5447a8894a522cba182cbc";
	/** 应用ID **/
	public static String appId = "8aaf070862fae1b501630b4bcc120840";
	/** 膜版ID **/
	public static String templateId = "246168";

	/**
	 * 发送短信
	 * 
	 * @param phone
	 * 			手机号码
	 * @param verificationCode
	 * 			验证码
	 * @return 
	 */
	public static void sendSms(String phone, String verificationCode) {
		HashMap<String, Object> result = null;
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		restAPI.init("app.cloopen.com", "8883");
		restAPI.setAccount(accountSid, authToken);
		restAPI.setAppId(appId);
		result = restAPI.sendTemplateSMS(phone, templateId, new String[] { verificationCode });
		System.out.println("SDKTestGetSubAccounts result=" + result);
		if ("000000".equals(result.get("statusCode"))) {
			//正常返回输出data包体信息（map）
			HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				System.out.println(key + " = " + object);
			}

		} else {
			//异常返回输出错误码和错误信息
			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
		}

	}
	
	
	public static void main(String[] args) {
		SmsUtils.sendSms("13416000672", "2222");
	}

}
