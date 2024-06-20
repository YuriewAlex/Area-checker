package com.ifmo.input;

import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;

public class PointsCredentials {
    @NotEmpty
    private String login;

    @NotEmpty
    private String x;

    @NotEmpty
    private String y;

    @NotEmpty
    private String r;

    private String result;

    public String isResult() {
        return result;
    }

    public void setResult(@NotNull String x, @NotNull String y, @NotNull String r){
        Double x_form = Double.parseDouble(x.replace(",", "."));
        Double y_form = Double.parseDouble(y.replace(",", "."));
        Double r_form = Double.parseDouble(r.replace(",", "."));
        if(x_form <= 0 && y_form >= 0 && (y_form <= r_form/2 + x_form/2) && y_form <= r_form/2 && x_form >= -r_form ||
                x_form >= 0 && y_form >= 0 && (x_form*x_form + y_form*y_form <= (r_form)*(r_form)) ||
                x_form >= 0 && y_form <= 0 && x_form <= r_form/2 && -y_form <= r_form){
            this.result = "true";
        }else {
            this.result = "false";
        }
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
