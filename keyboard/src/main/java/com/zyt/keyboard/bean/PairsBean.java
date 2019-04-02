package com.zyt.keyboard.bean;

import java.util.List;

public class PairsBean {

    public int randomIndex = 0;
    public String icon;
    public List<String> sendtext;


    public PairsBean(List<String> sendtext, String icon) {
        this.icon = icon;
        this.sendtext = sendtext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PairsBean)) return false;

        PairsBean pairsBean = (PairsBean) o;

        return icon != null ? icon.equals(pairsBean.icon) : pairsBean.icon == null;
    }

    @Override
    public int hashCode() {
        return icon != null ? icon.hashCode() : 0;
    }
}
