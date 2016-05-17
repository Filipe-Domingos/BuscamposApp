package br.edu.iff.pooa20152.buscampos.domain;

/**
 * Created by filipinho on 17/05/16.
 */
public class Linha {

    private Integer id;
    private String nome;
    private String ponto;

    public Linha(Integer id, String nome, String ponto) {
        this.id = id;
        this.nome = nome;
        this.ponto = ponto;
    }

    public Linha() {

    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPonto() {
        return ponto;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPonto(String ponto) {
        this.ponto = ponto;
    }

    @Override
    public String toString() {
        return "Linha{" +
                "id=" + id +
                '}';
    }
}
