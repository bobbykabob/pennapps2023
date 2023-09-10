package com.pennhacks.ecolens.response;

public class TrashTypesView {
    private int recyclables;
    private int nonrecyclables;

    public TrashTypesView(int recyclables, int nonrecyclables) {
        this.recyclables = recyclables;
        this.nonrecyclables = nonrecyclables;
    }

    public TrashTypesView() {
    }

    public int getRecyclables() {
        return recyclables;
    }

    public void setRecyclables(int recyclables) {
        this.recyclables = recyclables;
    }

    public int getNonrecyclables() {
        return nonrecyclables;
    }

    public void setNonrecyclables(int nonrecyclables) {
        this.nonrecyclables = nonrecyclables;
    }

    @Override
    public String toString() {
        return "TrashTypesView{" +
                "recyclables=" + recyclables +
                ", nonrecyclables=" + nonrecyclables +
                '}';
    }
}
