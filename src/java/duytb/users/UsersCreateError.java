/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duytb.users;

import java.io.Serializable;

/**
 *
 * @author baodu
 */
// 1 java bean 
public class UsersCreateError implements Serializable{
    private String usernameLengErr;
    private String passLengErr;
    private String fullnameLengErr;
    private String confirmPassNotMatched;
    private String usernameIsExisted;

    public UsersCreateError() {
    }

    
    /**
     * @return the usernameLengErr
     */
    public String getUsernameLengErr() {
        return usernameLengErr;
    }

    /**
     * @param usernameLengErr the usernameLengErr to set
     */
    public void setUsernameLengErr(String usernameLengErr) {
        this.usernameLengErr = usernameLengErr;
    }

    /**
     * @return the passLengErr
     */
    public String getPassLengErr() {
        return passLengErr;
    }

    /**
     * @param passLengErr the passLengErr to set
     */
    public void setPassLengErr(String passLengErr) {
        this.passLengErr = passLengErr;
    }

    /**
     * @return the fullnameLengErr
     */
    public String getFullnameLengErr() {
        return fullnameLengErr;
    }

    /**
     * @param fullnameLengErr the fullnameLengErr to set
     */
    public void setFullnameLengErr(String fullnameLengErr) {
        this.fullnameLengErr = fullnameLengErr;
    }

    /**
     * @return the confirmPassNotMatched
     */
    public String getConfirmPassNotMatched() {
        return confirmPassNotMatched;
    }

    /**
     * @param confirmPassNotMatched the confirmPassNotMatched to set
     */
    public void setConfirmPassNotMatched(String confirmPassNotMatched) {
        this.confirmPassNotMatched = confirmPassNotMatched;
    }

    /**
     * @return the usernameIsExisted
     */
    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    /**
     * @param usernameIsExisted the usernameIsExisted to set
     */
    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
    
}
