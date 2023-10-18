/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Plan;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author zouag
 */
public class PlanService {

    private ConnectionRequest req;
    private boolean resultOK;
    private static PlanService instance = null;
    private ArrayList<Plan> plans;

    private PlanService() {
        req = new ConnectionRequest();
    }

    public static PlanService getInstance() {
        if (instance == null) {
            instance = new PlanService();
        }
        return instance;
    }

    public ArrayList<Plan> parsePlan(String jsonText) {
        try {
            plans = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> planListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) planListJson.get("root");
            for (Map<String, Object> obj : list) {
                Plan p = new Plan();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                p.setNom(obj.get("nom").toString());
                p.setDescription(obj.get("description").toString());
                float nbParticipant = Float.parseFloat(obj.get("nbParticipant").toString());
                p.setNbParticipant((int) nbParticipant);
                float nombre_de_seances = Float.parseFloat(obj.get("nombre_de_seances").toString());
                p.setNombre_de_seances((int) nombre_de_seances);

                plans.add(p);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return plans;
    }

    public ArrayList<Plan> getAllPlans(int coachId) {
        ArrayList<Plan> listPlan = new ArrayList<>();
        String url = Statics.BASE_URL + "/json/plans?id="+String.valueOf(coachId);
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                plans = parsePlan(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return plans;
    }
    
       public void deletePlan(int id) {
    Dialog d = new Dialog();
    if (d.show("Delete Plan", "Do you really want to remove this Plan?", "Yes", "No")) {
        req.setUrl(Statics.BASE_URL + "/deletePlanJSON?id=" + id);
        NetworkManager.getInstance().addToQueueAndWait(req);
        d.dispose();
    }
}
       
       public boolean editPlan(Plan p) {
    String url = Statics.BASE_URL + "/updatePlanJSON?id=" + p.getId() +
            "&nom=" + p.getNom() +
            "&description=" + p.getDescription() +
            "&nbParticipant=" + p.getNbParticipant() +
            "&nombreDeSeances=" + p.getNombre_de_seances();
    req.setUrl(url);
    NetworkManager.getInstance().addToQueueAndWait(req);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });
    return resultOK;
}
       
       public boolean addPlan(Plan p) {
    String url = Statics.BASE_URL + "/addPlanJSON/new?nom=" + p.getNom() +
            "&description=" + p.getDescription() +
            "&nbParticipant=" + p.getNbParticipant() +
            "&nombreDeSeances=" + p.getNombre_de_seances()+
            "&coachId=" + p.getCoachId();
    req.setUrl(url);
    NetworkManager.getInstance().addToQueueAndWait(req);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });
    return resultOK;
}



}
