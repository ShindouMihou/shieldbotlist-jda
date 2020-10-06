package tk.paradoxium.sbh.entities;

public class ErrorHandler {

    /*
     * Will be used in the future when the API starts sending actual error messages instead of 404s.
     */

    private String error;
    private String success;

    public String getError() {
        return error;
    }

    public String getSuccess() {
        return success;
    }
}
