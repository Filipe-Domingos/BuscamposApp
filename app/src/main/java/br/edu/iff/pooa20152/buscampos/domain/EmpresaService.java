package br.edu.iff.pooa20152.buscampos.domain;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by filipinho on 09/05/16.
 */
public class EmpresaService extends GenericService {

    public EmpresaService(String url, String id, String method, JSONObject params) {
        super(url, id, method, params,new Empresa());
    }
    public EmpresaService(){
        super();
    }

    public List<Empresa> getAll(String url) {

        List<Empresa> listaEmpresas = super.getAll(url,new Empresa());

        return listaEmpresas;

    }

    public Empresa doGet(String url,String id){

        return (Empresa) super.doGet(url,id,new Empresa());
    }

    public Empresa doDelete(String url,String id){

        return (Empresa) super.doDelete(url,id);
    }

    public Empresa doPut(String url, JSONObject params){
        return (Empresa) super.doPut(url,params,new Empresa());
    }

    public Empresa doPost(String url, JSONObject params){
        return (Empresa) super.doPost(url,params,new Empresa());
    }

    public Empresa execute(){
        return (Empresa) super.execute();
    }
}