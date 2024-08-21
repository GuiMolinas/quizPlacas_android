package com.guimolinas.quiztransito;

public class ItemPlaca {

    String nome;
    int imgs;

    public ItemPlaca(String nome, int imgs) {
        this.nome = nome;
        this.imgs = imgs;
    }

    public String getNome() {
        return nome;
    }

    public int getImgs() {
        return imgs;
    }
}
