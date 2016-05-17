package br.edu.iff.pooa20152.buscampos.domain;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by filipinho on 17/05/16.
 */
public class OnibusService extends GenericService {

    public OnibusService(String url, String id, String method, JSONObject params) {
        super(url, id, method, params,new Onibus());
    }
    public OnibusService(){
        super();
    }

    public List<Onibus> getAll(String url) {

        List<Onibus> listaOnibus = super.getAll(url,new Onibus());

        return listaOnibus;

    }

    public Onibus doGet(String url,String id){

        return (Onibus) super.doGet(url,id,new Onibus());
    }

    public Onibus doDelete(String url,String id){

        return (Onibus) super.doDelete(url,id);
    }

    public Onibus doPut(String url, JSONObject params){
        return (Onibus) super.doPut(url,params,new Onibus());
    }

    public Onibus doPost(String url, JSONObject params){
        return (Onibus) super.doPost(url,params,new Onibus());
    }

    public Onibus execute(){
        return (Onibus) super.execute();
    }
}