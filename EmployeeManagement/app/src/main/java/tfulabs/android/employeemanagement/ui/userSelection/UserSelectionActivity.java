package tfulabs.android.employeemanagement.ui.userSelection;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import tfulabs.android.employeemanagement.R;
import tfulabs.android.employeemanagement.ui.camera.CameraActivity;
import tfulabs.android.employeemanagement.utils.Global;

public class UserSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    Button customer;
    Button employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);
        initUi();
    }

    private void initUi() {
        employee = findViewById(R.id.btn_employee);
        employee.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        Global.userType  = "EMPLOYEE";
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        // create a dialog with AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(getString(R.string.description_sure_exit));

        String positiveText = getString(R.string.label_exit);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("myTag", "positive button clicked");
                        dialog.dismiss();
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Log.d("myTag", "negative button clicked");
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }
}
