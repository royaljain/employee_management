package tfulabs.android.employeemanagement.data.remote;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import tfulabs.android.employeemanagement.data.model.api.EmployeeSignInOut;
import tfulabs.android.employeemanagement.utils.Constants;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * The type Utility.
 *
 * @author vishal kumar
 */
public class Utility {

    /**
     * Employee sign in employee sign in out.
     *
     * @param timeStamp the time stamp
     * @param filePath  the file path
     * @return the employee sign in out
     */
    public static EmployeeSignInOut employeeSignIn(String timeStamp, final String filePath) {
        File file = new File(filePath);
        ApiEndPoint apiEndPoint = ApiHelper.getClient(Constants.BASE_URL).create(ApiEndPoint.class);
        RequestBody requestBodyFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part partFile = MultipartBody.Part.createFormData("file", file.getName(), requestBodyFile);
        Call<EmployeeSignInOut> call = apiEndPoint.employeeSignIn(timeStamp, partFile);
        retrofit2.Response<EmployeeSignInOut> response;
        try {
            response = call.execute();
            Log.i("EMPLOYEE_SIGN_IN", "Response: " + response.isSuccessful()+timeStamp);
            return response.body();
        } catch (IOException e) {
            Log.e("EMPLOYEE_SIGN_IN", "Uploading failed" + e.getMessage()+ timeStamp);
            return null;
        }
    }

    /**
     * Employee sign out employee sign in out.
     *
     * @param timeStamp the time stamp
     * @param filePath  the file path
     * @return the employee sign in out
     */
    public static EmployeeSignInOut employeeSignOut(String timeStamp, final String filePath) {
        File file = new File(filePath);
        ApiEndPoint apiEndPoint = ApiHelper.getClient(Constants.BASE_URL).create(ApiEndPoint.class);
        RequestBody requestBodyFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part partFile = MultipartBody.Part.createFormData("file", file.getName(), requestBodyFile);
        Call<EmployeeSignInOut> call = apiEndPoint.employeeSignOut(timeStamp, partFile);
        retrofit2.Response<EmployeeSignInOut> response;
        try {
            response = call.execute();
            Log.i("EMPLOYEE_SIGN_OUT", "Response: " + response.isSuccessful());
            return response.body();
        } catch (IOException e) {
            Log.e("EMPLOYEE_SIGN_OUT", "Uploading failed" + e.getMessage());
            return null;
        }
    }
}
