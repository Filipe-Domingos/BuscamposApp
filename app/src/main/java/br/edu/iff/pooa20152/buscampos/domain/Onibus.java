package br.edu.iff.pooa20152.buscampos.domain;

/**
 * Created by filipinho on 17/05/16.
 */
public class Onibus {

    private Integer id;
    private String empresa;
    private String placa;
    private String num_assento;

    public Onibus(Integer id, String empresa, String placa, String num_assento) {
        this.id = id;
        this.empresa = empresa;
        this.placa = placa;
        this.num_assento = num_assento;
    }

    public Onibus() {

    }

    public Integer getId() {
        return id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getPlaca() {
        return placa;
    }

    public String getNum_assento() {
        return num_assento;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setNum_assento(String num_assento) {
        this.num_assento = num_assento;
    }

    @Override
    public String toString() {
        return "Onibus{" +
                "id=" + id +
                '}';
    }
}
