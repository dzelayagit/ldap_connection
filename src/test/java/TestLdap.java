/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.valley.ldap.enums.KeyNames;

import com.valley.ldap.implement.Ldap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dzelaya
 */
public class TestLdap {

    private Ldap ejbLdap = new Ldap();

    private String JDNI = "com.sun.jndi.ldap.LdapCtxFactory";
    ;
    private String LDAP_HOST = "ldap://192.168.0.9:389/DC=SOFIA,DC=NET";
    private String DOMIMIO = "SOFIA";
    private String OU_MAIN = "MZ";
    private String OU_SECONDARY = "Usuarios";
    ;
    private String user;
    private String pass;
    private String cn;
    private String userExpect;
    private HashMap<String, String> userCompare;
    private ArrayList<String> attributes = new ArrayList<String>();

    public TestLdap() {

        attributes.add(KeyNames.NAME.getKey());
        attributes.add(KeyNames.MAIL.getKey());
        attributes.add(KeyNames.SAM_ACCOUNT_NAME.getKey());
        attributes.add(KeyNames.GIVEN_NAME.getKey());
        attributes.add(KeyNames.CN.getKey());
        attributes.add(KeyNames.TELEPHONE_NUMBER.getKey());

    }

    @Test
    public void testNombreRicardo() {

        this.user = "rzelaya";
        this.pass = "Ieaa22$01";

        this.userExpect = "Ricardo Zelaya";
        this.userCompare = this.ejbLdap.LDAPAuthentication(JDNI, LDAP_HOST, DOMIMIO, OU_MAIN, OU_SECONDARY, user, pass, attributes);

        if (this.userCompare.size() > 0) {

            Iterator<Map.Entry<String, String>> entries = userCompare.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();

            }
        } else {
            System.out.println("El usuario no esta registrado");
        }

        assertEquals(userExpect, userCompare.get(KeyNames.CN.getKey()).toString());
    }

    @Test
    public void testNombreLiliana() {

        this.user = "lzelaya";
        this.pass = "Ieaa22$01";
        this.userExpect = "Liliana Zelaya";
        this.userCompare = this.ejbLdap.LDAPAuthentication(JDNI, LDAP_HOST, DOMIMIO, OU_MAIN, OU_SECONDARY, user, pass, attributes);
        if (this.userCompare.size() > 0) {

            Iterator<Map.Entry<String, String>> entries = userCompare.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
            }
        } else {
            System.out.println("El usuario no esta registrado");
        }

        assertEquals(userExpect, userCompare.get(KeyNames.CN.getKey()).toString());
    }

}
