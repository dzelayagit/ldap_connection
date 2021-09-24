/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valley.ejb.users.validateldap.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dzelaya
 */

public interface InterfaceLdap {

    /**
     *
     * @param INITJDNI JDNI de conexion
     * @param LDAP_HOST Dirección del Servidor LDAP
     * @param DOMAIN Nombre del Dominio donde esta el Active Directory
     * @param OU_MAIN
     * @param OU_SECONDARY
     * @param user Usario Registrado en el Active Directory
     * @param pass Contreaseña del Active Directory
     * @param attributes Arraylist de Atributos que se desea extraer
     * @return HashMap <clave, valor>
     */
    public HashMap<String, String> LDAPAuthentication(String INITJDNI, String LDAP_HOST, String DOMAIN, String OU_MAIN, String OU_SECONDARY, String user, String pass,  ArrayList<String> attributes);

}
