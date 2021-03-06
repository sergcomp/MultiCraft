package mobi.MultiCraft;

import android.app.NativeActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class MCNativeActivity extends NativeActivity {
	private int m_MessagReturnCode;
	private String m_MessageReturnValue;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		m_MessagReturnCode = -1;
		m_MessageReturnValue = "";
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 101) {
			if (resultCode == RESULT_OK) {
				String text = data.getStringExtra("text");
				m_MessagReturnCode = 0;
				m_MessageReturnValue = text;
			} else {
				m_MessagReturnCode = 1;
			}
		}
	}

	public void copyAssets() {
	}

	public void showDialog(String acceptButton, String hint, String current, int editType) {
		Intent intent = new Intent(this, MultiCraftTextEntry.class);
		Bundle params = new Bundle();
		params.putString("acceptButton", acceptButton);
		params.putString("hint", hint);
		params.putString("current", current);
		params.putInt("editType", editType);
		intent.putExtras(params);
		startActivityForResult(intent, 101);
		m_MessageReturnValue = "";
		m_MessagReturnCode = -1;
	}

	public static native void putMessageBoxResult(String text);

	public int getDialogState() {
		return m_MessagReturnCode;
	}

	public String getDialogValue() {
		m_MessagReturnCode = -1;
		return m_MessageReturnValue;
	}

	public float getDensity() {
		return getResources().getDisplayMetrics().density;
	}

	public int getDisplayWidth() {
		return getResources().getDisplayMetrics().widthPixels;
	}

	public int getDisplayHeight() {
		return getResources().getDisplayMetrics().heightPixels;
	}

	static {
		System.loadLibrary("openal");
		System.loadLibrary("ogg");
		System.loadLibrary("vorbis");
		System.loadLibrary("ssl");
		System.loadLibrary("crypto");
		System.loadLibrary("gmp");
		System.loadLibrary("iconv");
	}
}