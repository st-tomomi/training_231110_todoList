package util;

public class MyUtil {

	//nullのときデフォルト値を代入する
	public static String getValueOrDefault(String value, String defaultValue) {
		return (value != null) ? value : defaultValue;
	}
	//チェックボックスがONのとき1を設定する
	public static int getChackBoxStatus(String value) {
		return (value == null || value.equals("0")) ? 0 : 1;
	}

}
