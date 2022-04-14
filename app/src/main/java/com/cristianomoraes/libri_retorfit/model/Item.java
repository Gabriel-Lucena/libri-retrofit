package com.cristianomoraes.libri_retorfit.model;

public class Item {

    /**
     * 0 - Livro
     * 1 - HQ
     * 2 - Mangá
     */

    private int type;

    /*
     * Recebe objetos de livro, hq, mangá ou qualquer objeto
     * de qualuer outra classe de tipo que venha a existir
     */

    private Object object;

    public Item() {

    }

    public Item(int type, Object object) {
        this.type = type;
        this.object = object;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
