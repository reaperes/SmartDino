package nhk.raon.smartdino;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.util.Log;

public class Record {
	private String strFileName;

	private static final String MATH_RECORD_TOKEN = "$math$";
	
	public static final int MATH_RECORD = 1;

	public Record(int dataNum) {
		strFileName = "data0" + String.valueOf(dataNum) + ".txt";

		// File file = getContext().getFileStreamPath(FILE_NAME);
		// if(file.exists()) ...
	}

	public void initRecord(Context context) {
		OutputStreamWriter osw = null;
		
		try {
			 osw = new OutputStreamWriter(context.openFileOutput(strFileName, context.MODE_PRIVATE));
			 osw.write(String.valueOf(SmartDino.Dino_Type) + "\n" + MATH_RECORD_TOKEN + "0000");
		} catch (Exception e) {
			 Log.e("NHK", "initialize record Error");
		} finally {
			try {
				 osw.close();
			} catch (Exception e) {
			}
		}
	}

	public int getRecord(Context context, int recordType, int point) {
		BufferedReader br;
		StringBuffer sb = new StringBuffer();
		String str;

		try {
			br = new BufferedReader(new InputStreamReader(context.openFileInput(strFileName)));
			while ((str = br.readLine()) != null) {
				sb.append(str + "\n");
			}
			br.close();
		} catch (Exception e) {
			Log.e("NHK", "read level ERROR");
			str = "Error";
			return 0;
		}
		
		String token = null;
		switch(recordType) {
		case MATH_RECORD:
			token = MATH_RECORD_TOKEN;
			break;
		}
		int baseIndex = sb.indexOf(token) + token.length()+1;
		
		return Integer.parseInt(String.valueOf((sb.charAt(baseIndex+point))));
	}

	public void setRecord(Context context, int recordType, int point, int data) {
		BufferedReader br;
		StringBuffer sb = new StringBuffer();
		String str;

		try {
			br = new BufferedReader(new InputStreamReader(context.openFileInput(strFileName)));
			while ((str = br.readLine()) != null) {
				sb.append(str + "\n");
			}
			br.close();
		} catch (Exception e) {
			Log.e("NHK", "read level ERROR");
			str = "Error";
			return ;
		}
		
		String token = null;
		switch(recordType) {
		case MATH_RECORD:
			token = MATH_RECORD_TOKEN;
			break;
		}
		int baseIndex = sb.indexOf(token) + token.length()+1;
		sb.replace(baseIndex+point, baseIndex+point, String.valueOf(data));
		
		OutputStreamWriter osw = null;
		try {
			 osw = new OutputStreamWriter(context.openFileOutput(strFileName, context.MODE_PRIVATE));
			 osw.write(new String(sb));
		} catch (Exception e) {
			 Log.e("NHK", "initialize record Error");
		} finally {
			try {
				 osw.close();
			} catch (Exception e) {
			}
		}		
		return ;
	}
}
