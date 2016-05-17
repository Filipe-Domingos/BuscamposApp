package br.edu.iff.pooa20152.buscampos.domain;

/**
 * Created by filipinho on 17/05/16.
 */
public class Ponto {
    private Integer id;
    private String rua;
    private String bairro;
    private String latitude;
    private String longitude;

    public Ponto(Integer id, String rua, String bairro, String latitude, String longitude) {
        this.id = id;
        this.rua = rua;
        this.bairro = bairro;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Ponto() {

    }

    public Integer getId() {
        return id;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Ponto{" +
                "id=" + id +
                '}';
    }
}
