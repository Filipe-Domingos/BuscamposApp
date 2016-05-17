package br.edu.iff.pooa20152.buscampos.domain;


import org.json.JSONObject;

import java.util.List;

/**
 * Created by filipinho on 17/05/16.
 */
public class PontoService extends GenericService {

    public PontoService(String url, String id, String method, JSONObject params) {
        super(url, id, method, params,new Ponto());
    }
    public PontoService(){
        super();
    }

    public List<Ponto> getAll(String url) {

        List<Ponto> listaPontos = super.getAll(url,new Ponto());

        return listaPontos;

    }

    public Ponto doGet(String url,String id){

        return (Ponto) super.doGet(url,id,new Ponto());
    }

    public Ponto doDelete(String url,String id){

        return (Ponto) super.doDelete(url,id);
    }

    public Ponto doPut(String url, JSONObject params){
        return (Ponto) super.doPut(url,params,new Ponto());
    }

    public Ponto doPost(String url, JSONObject params){
        return (Ponto) super.doPost(url,params,new Ponto());
    }

    public Ponto execute(){
        return (Ponto) super.execute();
    }
}