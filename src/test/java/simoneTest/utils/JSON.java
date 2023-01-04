package allainTest.utils;

import org.json.simple.JSONObject;

public class JSON {

    public String passarChaves(String nome, String cpf, String email, Float valor, Integer parcelas, Boolean seguro) {
        JSONObject jsonObject = createJSON();
        jsonObject.put("nome", nome);
        jsonObject.put("cpf", cpf);
        jsonObject.put("email", email);
        jsonObject.put("valor", valor);
        jsonObject.put("parcelas", parcelas);
        jsonObject.put("seguro", seguro);
        return jsonObject.toJSONString();
    }

    public String passarChavesSeguro(String nome, String cpf, String email, Float valor, Integer parcelas, String seguro) {
        JSONObject jsonObject = createJSON();
        jsonObject.put("nome", nome);
        jsonObject.put("cpf", cpf);
        jsonObject.put("email", email);
        jsonObject.put("valor", valor);
        jsonObject.put("parcelas", parcelas);
        jsonObject.put("seguro", seguro);
        return jsonObject.toJSONString();
    }


    private JSONObject createJSON() {
        JSONObject jsonObj = new JSONObject();
        return jsonObj;
    }
}
