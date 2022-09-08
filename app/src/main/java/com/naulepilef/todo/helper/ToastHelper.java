package com.naulepilef.todo.helper;

import android.content.Context;
import android.widget.Toast;

import com.naulepilef.todo.R;

public class ToastHelper {
    public static void EmptyField(Context context){
        Toast.makeText(
            context,
            context.getString(R.string.toast_input_description_empty),
            Toast.LENGTH_LONG
        ).show();
    }

    public static void CreateSuccess(Context context){
        Toast.makeText(
                context,
                context.getString(R.string.toast_create_successfully),
                Toast.LENGTH_LONG
        ).show();
    }

    public static void CreateFail(Context context){
        Toast.makeText(
                context,
                context.getString(R.string.toast_create_fail),
                Toast.LENGTH_LONG
        ).show();
    }

    public static void UpgradeSuccess(Context context){
        Toast.makeText(
                context,
                context.getString(R.string.toast_upgrade_successfully),
                Toast.LENGTH_LONG
        ).show();
    }

    public static void UpgradeFail(Context context){
        Toast.makeText(
                context,
                context.getString(R.string.toast_upgrade_fail),
                Toast.LENGTH_LONG
        ).show();
    }

    public static void DeleteSuccess(Context context){
        Toast.makeText(
                context,
                context.getString(R.string.toast_delete_successfully),
                Toast.LENGTH_LONG
        ).show();
    }

    public static void DeleteFail(Context context){
        Toast.makeText(
                context,
                context.getString(R.string.toast_delete_fail),
                Toast.LENGTH_LONG
        ).show();
    }
}
