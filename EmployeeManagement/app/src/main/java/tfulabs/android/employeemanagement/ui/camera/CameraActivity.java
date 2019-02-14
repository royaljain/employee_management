package tfulabs.android.employeemanagement.ui.camera;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.util.Calendar;

import tfulabs.android.employeemanagement.R;

/**
 * The type Camera activity.
 *
 * @author vishal kumar
 */
public class CameraActivity extends AppCompatActivity {

    private static String TAG = CameraActivity.class.getSimpleName();
    private static final int REQUEST_WRITE_PERMISSION = 1;

    /**
     * This is the output file for our picture.
     */
    private File mFile;

    /**
     * Gets file.
     *
     * @return the file
     */
    public File getmFile() {
        return mFile;
    }

    /**
     * Sets file.
     *
     * @param mFile the m file
     */
    public void setmFile(File mFile) {
        this.mFile = mFile;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //Hide the system navigation bar
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        requestPermissions(new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_WRITE_PERMISSION);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == REQUEST_WRITE_PERMISSION){
                String fileName = Calendar.getInstance().getTime() + ".jpg";
                String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
                String pathDir = baseDir + "/Android/data/tfulabs.android.employeemanagement/";
                File pathDirFolder = new File(pathDir);
                Log.d(TAG, "The file!" + pathDirFolder.getAbsolutePath());
                if (pathDirFolder.exists()) {
                    mFile = new File(pathDir + File.separator + fileName);
                    if (mFile.exists()) {
                        Log.d(TAG, "The file " + mFile.getName() + " exists!");
                        String s = "Back button pressed.";
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, CameraFragment.newInstance())
                                .addToBackStack(s)
                                .commit();
                    } else {
                        Log.d(TAG, "The file no longer exists!");
                    }
                } else {
                    if (pathDirFolder.mkdirs()) {
                        mFile = new File(pathDir + File.separator + fileName);
                        if (mFile.exists()) {
                            Log.d(TAG, "The file " + mFile.getName() + " exists!");
                            String s = "Back button pressed.";
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container, CameraFragment.newInstance())
                                    .addToBackStack(s)
                                    .commit();
                        } else {
                            Log.d(TAG, "The file no longer exists!");
                        }
                    } else {
                        Log.e(TAG, "onActivityCreated: FailedToCreate Directory");
                    }
                }

                String s = "Back button pressed.";
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, CameraFragment.newInstance())
                        .addToBackStack(s)
                        .commit();
        }
        else
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed(){

    }

}
