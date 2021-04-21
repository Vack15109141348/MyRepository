package com.dhcc.util;

public class R {
    private String code;
    private String massgae;
    private String result;

    public R(String code, String massgae, String result) {
        this.code = code;
        this.massgae = massgae;
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public String getMassgae() {
        return massgae;
    }

    public String getResult() {
        return result;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMassgae(String massgae) {
        this.massgae = massgae;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
