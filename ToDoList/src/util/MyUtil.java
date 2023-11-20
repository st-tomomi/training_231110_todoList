package util;

public class MyUtil {

	//nullのときデフォルト値を代入する
	public static String getValueOrDefault(String value, String defaultValue) {
		return (value != null) ? value : defaultValue;
	}
	//nullのときデフォルト値を代入する
	public static int getValueOrDefault(int value, int defaultValue) {
        return (value != 0) ? value : defaultValue;
    }

	//

}
