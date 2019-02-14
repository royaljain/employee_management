package tfulabs.android.employeemanagement.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The type Employee sign in out.
 */
public class EmployeeSignInOut {
    @SerializedName("identificationSuccesful")
    @Expose
    private String identificationSuccesful;
    @SerializedName("employeeName")
    @Expose
    private String employeeName;

    /**
     * Gets identification succesful.
     *
     * @return the identification succesful
     */
    public String getIdentificationSuccesful() {
        return identificationSuccesful;
    }

    /**
     * Sets identification succesful.
     *
     * @param identificationSuccesful the identification succesful
     */
    public void setIdentificationSuccesful(String identificationSuccesful) {
        this.identificationSuccesful = identificationSuccesful;
    }

    /**
     * Gets employee name.
     *
     * @return the employee name
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Sets employee name.
     *
     * @param employeeName the employee name
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

}
