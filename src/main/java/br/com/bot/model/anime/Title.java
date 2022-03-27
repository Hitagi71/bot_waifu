package br.com.bot.model.anime;

import com.google.gson.annotations.SerializedName;

public class Title {
    private String romanji;
    private String english;

    @SerializedName(value = "native")
    private String nativo;

    public String getRomanji() {
        return romanji;
    }

    public void setRomanji(String romanji) {
        this.romanji = romanji;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getNativo() {
        return nativo;
    }

    public void setNativo(String nativo) {
        this.nativo = nativo;
    }
}
