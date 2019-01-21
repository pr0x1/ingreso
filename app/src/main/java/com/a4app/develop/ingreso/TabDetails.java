package com.a4app.develop.ingreso;

public class TabDetails {
    private String tabName;
    private android.support.v4.app.Fragment fragment;

    public TabDetails(String tabName, android.support.v4.app.Fragment fragment) {
        this.tabName = tabName;
        this.fragment = fragment;

    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public android.support.v4.app.Fragment getFragment() {
        return fragment;
    }

    public void setFragment(android.support.v4.app.Fragment fragment) {
        this.fragment = fragment;
    }

}