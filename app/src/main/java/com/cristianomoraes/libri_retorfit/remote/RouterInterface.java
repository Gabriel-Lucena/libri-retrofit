package com.cristianomoraes.libri_retorfit.remote;

import com.cristianomoraes.libri_retorfit.model.Livro;
import com.cristianomoraes.libri_retorfit.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RouterInterface {

    /*
     * Rota de usuário
     */

    @POST("/usuario/")
    Call<Usuario> addUsuario(@Body Usuario usuario);

    /*
     * Rotas de livros
     */

    @POST("/livro/")
    Call<Livro> addLivro(@Body Livro livro);

    @GET("/livro/")
    Call<List<Livro>> getLivro();

    @DELETE("/livro/{idLivro}")
    Call<Livro> deleteLivro(@Path("idLivro") int idLivro);

    @GET("/livro/id/{idLivro}")
    Call<List<Livro>> getLivroId(@Path("idLivro") int idLivro);

    @PUT("/livro/")
    Call<Livro> updateLivro(@Body Livro livro);

}
