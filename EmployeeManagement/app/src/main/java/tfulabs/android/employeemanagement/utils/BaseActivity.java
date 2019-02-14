package tfulabs.android.employeemanagement.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import tfulabs.android.employeemanagement.R;

/**
 * The type Base activity.
 *
 * @author vishal kumar
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * The M progress dialog.
     */
    @VisibleForTesting
    public ProgressDialog mProgressDialog;

    /**
     * Show progress dialog.
     */
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.progress_msg_loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    /**
     * Hide progress dialog.
     */
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * Hide keyboard.
     *
     * @param view the view
     */
    public void hideKeyboard(View view) {
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Show toast.
     *
     * @param context the context
     * @param message the message
     */
    public void showToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.setBackground(getDrawable(R.drawable.toast));
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(getResources().getColor(R.color.colorWhite));
        text.setTextSize(12);
        toast.show();
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

}