package com.cristianomoraes.libri_retorfit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Livro {

    @SerializedName("idLivro")
    @Expose
    private int idLivro;

    @SerializedName("idUsuario")
    @Expose
    private int idUsuario;

    @SerializedName("titulo")
    @Expose
    private String titulo;

    @SerializedName("descricao")
    @Expose
    private String descricao;

    @SerializedName("imagem")
    @Expose
    private String imagem;

    public Livro() {
    }

    public Livro(int idLivro, int idUsuario, String titulo, String descricao,
                 String imagem) {
        this.idLivro = idLivro;
        this.idUsuario = idUsuario;
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem = imagem;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }


}
