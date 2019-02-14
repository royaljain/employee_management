package tfulabs.android.employeemanagement.data.remote;


import tfulabs.android.employeemanagement.data.model.api.EmployeeSignInOut;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * The interface Api end point.
 *
 * @author vishal kumar
 */
public interface ApiEndPoint {

    /**
     * Employee sign in call.
     *
     * @param timestamp the timestamp
     * @param file      the file
     * @return the call
     */
    @Multipart
    @POST("/employee_sign_in")
    Call<EmployeeSignInOut> employeeSignIn(@Query("timestamp") String timestamp, @Part MultipartBody.Part file);

    /**
     * Employee sign out call.
     *
     * @param timestamp the timestamp
     * @param file      the file
     * @return the call
     */
    @Multipart
    @POST("/employee_sign_out")
    Call<EmployeeSignInOut> employeeSignOut(@Query("timestamp") String timestamp, @Part MultipartBody.Part file);

}
