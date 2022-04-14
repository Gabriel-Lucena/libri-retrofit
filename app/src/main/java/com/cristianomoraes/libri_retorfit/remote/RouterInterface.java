package com.cristianomoraes.libri_retorfit.remote;

import com.cristianomoraes.libri_retorfit.model.Livro;
import com.cristianomoraes.libri_retorfit.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RouterInterface {

    /*
     * Rota de usuário
     */

    @POST("/usuario/")
    Call<Usuario> addUsuario(@Body Usuario usuario);

    /*
     * Rota de livros
     */

    @POST("/livro/")
    Call<Livro> addLivro(@Body Livro livro);

    @GET("/livro/")
    Call<List<Livro>> getLivros();

}
