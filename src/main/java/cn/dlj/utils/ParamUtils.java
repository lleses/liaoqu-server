package cn.dlj.utils;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * Web参数处理工具类
 * 
 */
public class ParamUtils {

	private ParamUtils() {
	}

	/**
	 * 获取Integer参数
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @return Integer/null
	 */
	public static Integer getInt(HttpServletRequest request, String paramName) {
		return paramInt(request, paramName, false);
	}

	/**
	 * 获取Integer参数并传递(Attribute)
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @return
	 */
	public static Integer paramInt(HttpServletRequest request, String paramName) {
		return paramInt(request, paramName, true);
	}

	/**
	 * 获取Integer参数
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param attr
	 *            是否需要传递
	 * @return
	 */
	public static Integer paramInt(HttpServletRequest request, String paramName, boolean attr) {
		String str = getStr(request, paramName);
		if (null != str && str.matches("[0-9-]{0,11}")) {
			try {
				int i = Integer.parseInt(str);
				if (attr) {
					request.setAttribute(paramName, i);// attribute参数
				}
				return i;
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	/**
	 * 获取参数字符串
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @return
	 */
	public static String getStr(HttpServletRequest request, String paramName) {
		return getStr(request, paramName, null);
	}

	/**
	 * 获取参数字符串
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param utf8
	 *            是否utf8编码字符串
	 * @return
	 */
	public static String getStr(HttpServletRequest request, String paramName, boolean utf8) {
		return getStr(request, paramName, utf8 ? "UTF-8" : null);
	}

	/**
	 * 获取参数字符串
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param charset
	 *            编码字符串
	 * @return
	 */
	public static String getStr(HttpServletRequest request, String paramName, String charset) {
		String str = request.getParameter(paramName);
		if (null == str) {
			Object o = request.getAttribute(paramName);
			if (null != o) {
				str = o.toString();
			}
		}
		if (null != str) {
			str = StringUtils.htmlRmv(str.trim());
			if (str.length() > 0) {
				if (null != charset) {
					try {
						str = new String(str.getBytes("ISO-8859-1"), charset);
					} catch (UnsupportedEncodingException e) {
					}
				}
				return str;
			}
		}
		return null;
	}

	/**
	 * 获取日期(yyyy-MM-dd)参数并传递(Attribute)
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @return
	 */
	public static Date paramDate(HttpServletRequest request, String paramName) {
		return paramDate(request, paramName, null, true);
	}

	/**
	 * 获取日期(yyyy-MM-dd)参数
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param attr
	 *            是否需要传递
	 * @return
	 */
	public static Date paramDate(HttpServletRequest request, String paramName, boolean attr) {
		return paramDate(request, paramName, null, attr);
	}

	/**
	 * 获取日期参数
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @param pattern
	 *            日期格式，缺省yyyy-MM-dd
	 * @param attr
	 *            是否需要传递
	 * @return
	 */
	public static Date paramDate(HttpServletRequest request, String paramName, String pattern, boolean attr) {
		String str = getStr(request, paramName);
		if (null != str) {
			try {
				Date date = null;
				if (null == pattern) {
					date = DateUtils.DF_DATE.parse(str);
				} else {
					date = new SimpleDateFormat(pattern).parse(str);
				}
				if (null != date && attr) {
					request.setAttribute(paramName, str);// attribute参数
				}
				return date;
			} catch (ParseException e) {
			}
		}
		return null;
	}
}
