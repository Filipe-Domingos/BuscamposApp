package br.edu.iff.pooa20152.buscampos.domain;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by filipinho on 17/05/16.
 */
public class LinhaService extends GenericService {

    public LinhaService(String url, String id, String method, JSONObject params) {
        super(url, id, method, params,new Linha());
    }
    public LinhaService(){
        super();
    }

    public List<Linha> getAll(String url) {

        List<Linha> listaLinhas = super.getAll(url,new Linha());

        return listaLinhas;

    }

    public Linha doGet(String url,String id){

        return (Linha) super.doGet(url,id,new Linha());
    }

    public Linha doDelete(String url,String id){

        return (Linha) super.doDelete(url,id);
    }

    public Linha doPut(String url, JSONObject params){
        return (Linha) super.doPut(url,params,new Linha());
    }

    public Linha doPost(String url, JSONObject params){
        return (Linha) super.doPost(url,params,new Linha());
    }

    public Linha execute(){
        return (Linha) super.execute();
    }
}