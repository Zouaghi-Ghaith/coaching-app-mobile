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
import com.mycompany.entities.Coach;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zouag
 */
public class CoachService {

    private ConnectionRequest req;
    private boolean resultOK;
    private static CoachService instance = null;
    private ArrayList<Coach> coaches;

    private CoachService() {
        req = new ConnectionRequest();
    }

    public static CoachService getInstance() {
        if (instance == null) {
            instance = new CoachService();
        }
        return instance;
    }

    public ArrayList<Coach> parseCoach(String jsonText) {
        try {
            coaches = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String, Object> coachListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) coachListJson.get("root");
            for (Map<String, Object> obj : list) {
                Coach c = new Coach();
                float id = Float.parseFloat(obj.get("id").toString());
                float age = Float.parseFloat(obj.get("Age").toString());
                c.setId((int) id);
                c.setNom(obj.get("Nom").toString());
                c.setPrenom(obj.get("Prenom").toString());
                c.setEmail(obj.get("email").toString());
                c.setAge((int) age);
                c.setSpecialite(obj.get("specialite").toString());

                coaches.add(c);
            }

        } catch (IOException ex) {
           // ex.printStackTrace();
        }

        return coaches;
    }

    public ArrayList<Coach> getAllCoaches() {
        ArrayList<Coach> listCoach = new ArrayList<>();
        String url = Statics.BASE_URL + "/Allcoach";
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                coaches = parseCoach(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return coaches;
    }
    
    public boolean addCoach(Coach c) {
    String url = Statics.BASE_URL + "/addCoachJSON/new?Nom=" + c.getNom() + "&Prenom=" + c.getPrenom() + "&email=" + c.getEmail() + "&Age=" + c.getAge() + "&specialite=" + c.getSpecialite();
    req.setUrl(url);
    //NetworkManager.getInstance().addToQueueAndWait(req);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
    
    public boolean editCoach(Coach c) {
        String url = Statics.BASE_URL + "/updateCoachJSON?id=" + c.getId() +
                "&Nom=" + c.getNom() +
                "&Prenom=" + c.getPrenom() +
                "&email=" + c.getEmail() +
                "&Age=" + c.getAge() +
                "&specialite=" + c.getSpecialite();
        req.setUrl(url);
        NetworkManager.getInstance().addToQueueAndWait(req);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
      //  NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public void deleteCoach(int id) {
    Dialog d = new Dialog();
    if (d.show("Delete Coach", "Do you really want to remove this Coach?", "Yes", "No")) {
        req.setUrl(Statics.BASE_URL + "/deleteCoachJSON?id=" + id);
        NetworkManager.getInstance().addToQueueAndWait(req);
        d.dispose();
    }
}





}
