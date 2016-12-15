package com.app.gitevent.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class RequestProgressDialog extends ProgressDialog
{
	public int reqCode = 0;

	public RequestProgressDialog(Context context, String message, int requesCode)
	{
		super(context);
		reqCode = requesCode;
		setMessage(message);
		setCancelable(true);
		setCanceledOnTouchOutside(false);
	}
	
	public RequestProgressDialog(Context context, String message, int requesCode, int theme)
	{
		super(context, theme);
		reqCode = requesCode;
		setMessage(message);
		setCancelable(true);
		setCanceledOnTouchOutside(false);
	}
}
