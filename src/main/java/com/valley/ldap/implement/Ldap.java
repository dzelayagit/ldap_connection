/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.valley.ldap.implement;

import com.valley.ldap.enums.KeyNames;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import com.valley.ejb.users.validateldap.interfaces.InterfaceLdap;
import javax.naming.Context;

/**
 *
 * @author dzelaya
 */
public class Ldap implements InterfaceLdap {

    @Override
    public HashMap<String, String> LDAPAuthentication(String INITJDNI, String LDAP_HOST, String DOMAIN, String OU_MAIN, String OU_SECONDARY, String user, String pass, ArrayList attributes) {

        HashMap<String, String> useMap = new HashMap<>();
        NamingEnumeration users = null;
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, INITJDNI);
        env.put(Context.PROVIDER_URL, LDAP_HOST);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, (DOMAIN + "\\" + user));
        env.put(Context.SECURITY_CREDENTIALS, pass);

        DirContext dirContext = null;

        try {

            dirContext = new InitialDirContext(env);

            String searchFilter = "(&(objectClass=person)(sAMAccountName=" + user + "))";

            String[] requiredAttributes = new String[attributes.size()];

            for (int i = 0; i < attributes.size(); i++) {
                requiredAttributes[i] = attributes.get(i).toString();
            }

            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            controls.setReturningAttributes(requiredAttributes);

            users = dirContext.search("ou=" + OU_SECONDARY + ",ou= " + OU_MAIN, searchFilter, controls);
         
            SearchResult searchResult = null;
            if (users != null) {
                while (users.hasMore()) {
                    searchResult = (SearchResult) users.next();
                    Attributes attr = searchResult.getAttributes();

                    
                    try {
                        if (attr.get(KeyNames.NAME.getKey()).toString().substring(0, attr.get(KeyNames.NAME.getKey()).toString().indexOf(":")).trim().equals(KeyNames.NAME.getKey())) {
                            useMap.put(KeyNames.NAME.getKey(), attr.get(KeyNames.NAME.getKey()).get(0).toString());
                        } else {
                            //  useMap.put(KeyNames.NAME.getKey(), "Unknown");
                        }
                    } catch (Exception e) {
                        // useMap.put(KeyNames.NAME.getKey(), "Unknown");
                    }

                    try {
                        if (attr.get(KeyNames.MAIL.getKey()).toString().substring(0, attr.get(KeyNames.MAIL.getKey()).toString().indexOf(":")).trim().equals(KeyNames.MAIL.getKey())) {
                            useMap.put(KeyNames.MAIL.getKey(), attr.get(KeyNames.MAIL.getKey()).get(0).toString());
                        } else {
                            //useMap.put(KeyNames.MAIL.getKey(), "Unknown");
                        }
                    } catch (Exception e) {
                        //  useMap.put(KeyNames.MAIL.getKey(), "Unknown");
                    }

                    try {
                        if (attr.get(KeyNames.GIVEN_NAME.getKey()).toString().substring(0, attr.get(KeyNames.GIVEN_NAME.getKey()).toString().indexOf(":")).trim().equals(KeyNames.GIVEN_NAME.getKey())) {
                            useMap.put(KeyNames.GIVEN_NAME.getKey(), attr.get(KeyNames.GIVEN_NAME.getKey()).get(0).toString());
                        } else {
                            //  useMap.put(KeyNames.GIVEN_NAME.getKey(), "Unknown");
                        }
                    } catch (Exception e) {
                        // useMap.put(KeyNames.GIVEN_NAME.getKey(), "Unknown");
                    }

                    try {

                        if (attr.get(KeyNames.C.getKey()).toString().substring(0, attr.get(KeyNames.C.getKey()).toString().indexOf(":")).trim().equals(KeyNames.C.getKey())) {
                            useMap.put(KeyNames.C.getKey(), attr.get(KeyNames.C.getKey()).get(0).toString());
                        } else {
                            //    useMap.put(KeyNames.C.getKey(), "Unknown");
                        }
                    } catch (Exception e) {
                        //   useMap.put(KeyNames.C.getKey(), "Unknown");
                    }

                    try {
                        if (attr.get(KeyNames.CN.getKey()).toString().substring(0, attr.get(KeyNames.CN.getKey()).toString().indexOf(":")).trim().equals(KeyNames.CN.getKey())) {
                            useMap.put(KeyNames.CN.getKey(), attr.get(KeyNames.CN.getKey()).get(0).toString());
                        } else {
                            //    useMap.put(KeyNames.CN.getKey(), "Unknown");
                        }
                    } catch (Exception e) {
                        // useMap.put(KeyNames.CN.getKey(), "Unknown");
                    }

                    try {
                        if (attr.get(KeyNames.CO.getKey()).toString().substring(0, attr.get(KeyNames.CO.getKey()).toString().indexOf(":")).trim().equals(KeyNames.CO.getKey())) {
                            useMap.put(KeyNames.CO.getKey(), attr.get(KeyNames.CO.getKey()).get(0).toString());
                        } else {
                            //      useMap.put(KeyNames.CO.getKey(), "Unknown");
                        }
                    } catch (Exception e) {
                        //    useMap.put(KeyNames.CO.getKey(), "Unknown");
                    }

                    try {

                        if (attr.get(KeyNames.COMPANY.getKey()).toString().substring(0, attr.get(KeyNames.COMPANY.getKey()).toString().indexOf(":")).trim().equals(KeyNames.COMPANY.getKey())) {
                            useMap.put(KeyNames.COMPANY.getKey(), attr.get(KeyNames.COMPANY.getKey()).get(0).toString());
                        } else {
                            //     useMap.put(KeyNames.COMPANY.getKey(), "Unknown");
                        }
                    } catch (Exception e) {
                        //   useMap.put(KeyNames.COMPANY.getKey(), "Unknown");
                    }

                    try {
                        if (attr.get(KeyNames.USER_ACCOUNT_CONTROL.getKey()).toString().substring(0, attr.get(KeyNames.USER_ACCOUNT_CONTROL.getKey()).toString().indexOf(":")).trim().equals(KeyNames.USER_ACCOUNT_CONTROL.getKey())) {
                            useMap.put(KeyNames.USER_ACCOUNT_CONTROL.getKey(), attr.get(KeyNames.USER_ACCOUNT_CONTROL.getKey()).get(0).toString());
                        } else {
                            //       useMap.put(KeyNames.USER_ACCOUNT_CONTROL.getKey(), "Unknown");
                        }
                    } catch (Exception e) {
                        //   useMap.put(KeyNames.USER_ACCOUNT_CONTROL.getKey(), "Unknown");
                    }

                    try {
                        if (attr.get(KeyNames.DEPARTMENT.getKey()).toString().substring(0, attr.get(KeyNames.DEPARTMENT.getKey()).toString().indexOf(":")).trim().equals(KeyNames.DEPARTMENT.getKey())) {
                            useMap.put(KeyNames.DEPARTMENT.getKey(), attr.get(KeyNames.DEPARTMENT.getKey()).get(0).toString());
                        } else {
                            //     useMap.put(KeyNames.DEPARTMENT.getKey(), "Unknown");
                        }
                    } catch (Exception e) {
                        //  useMap.put(KeyNames.DEPARTMENT.getKey(), "Unknown");
                    }

                    try {
                        if (attr.get(KeyNames.L.getKey()).toString().substring(0, attr.get(KeyNames.L.getKey()).toString().indexOf(":")).trim().equals(KeyNames.L.getKey())) {
                            useMap.put(KeyNames.L.getKey(), attr.get(KeyNames.L.getKey()).get(0).toString());
                        } else {
                            //        useMap.put(KeyNames.L.getKey(), "Unknown");
                        }
                    } catch (Exception e) {
                        //  useMap.put(KeyNames.L.getKey(), "Unknown");
                    }

                    try {
                        if (attr.get(KeyNames.SAM_ACCOUNT_NAME.getKey()).toString().substring(0, attr.get(KeyNames.SAM_ACCOUNT_NAME.getKey()).toString().indexOf(":")).trim().equals(KeyNames.SAM_ACCOUNT_NAME.getKey())) {
                            useMap.put(KeyNames.SAM_ACCOUNT_NAME.getKey(), attr.get(KeyNames.SAM_ACCOUNT_NAME.getKey()).get(0).toString());
                        } else {
                            //         useMap.put(KeyNames.SAM_ACCOUNT_NAME.getKey(), "Unknown");
                        }
                    } catch (Exception e) {
                        //    useMap.put(KeyNames.SAM_ACCOUNT_NAME.getKey(), "Unknown");
                    }

                    try {
                        if (attr.get(KeyNames.ST.getKey()).toString().substring(0, attr.get(KeyNames.ST.getKey()).toString().indexOf(":")).trim().equals(KeyNames.ST.getKey())) {
                            useMap.put(KeyNames.ST.getKey(), attr.get(KeyNames.ST.getKey()).get(0).toString());
                        } else {
                            //     useMap.put(KeyNames.ST.getKey(), "Unknown");
                        }
                    } catch (Exception e) {
                        //  useMap.put(KeyNames.ST.getKey(), "Unknown");
                    }

                    try {
                        if (attr.get(KeyNames.TELEPHONE_NUMBER.getKey()).toString().substring(0, attr.get(KeyNames.TELEPHONE_NUMBER.getKey()).toString().indexOf(":")).trim().equals(KeyNames.TELEPHONE_NUMBER.getKey())) {
                            useMap.put(KeyNames.TELEPHONE_NUMBER.getKey(), attr.get(KeyNames.TELEPHONE_NUMBER.getKey()).get(0).toString());
                        } else {
                            //        useMap.put(KeyNames.TELEPHONE_NUMBER.getKey(), "Unknown");
                        }
                    } catch (Exception e) {
                        //   useMap.put(KeyNames.TELEPHONE_NUMBER.getKey(), "Unknown");
                    }

                    try {
                        if (attr.get(KeyNames.DESCRIPTION.getKey()).toString().substring(0, attr.get(KeyNames.DESCRIPTION.getKey()).toString().indexOf(":")).trim().equals(KeyNames.DESCRIPTION.getKey())) {
                            useMap.put(KeyNames.DESCRIPTION.getKey(), attr.get(KeyNames.DESCRIPTION.getKey()).get(0).toString());
                        } else {
                            //       useMap.put(KeyNames.DESCRIPTION.getKey(), "Unknown");
                        }
                    } catch (Exception e) {

                        //    useMap.put(KeyNames.DESCRIPTION.getKey(), "Unknown");
                    }
                    try {
                        if (attr.get(KeyNames.SN.getKey()).toString().substring(0, attr.get(KeyNames.SN.getKey()).toString().indexOf(":")).trim().equals(KeyNames.SN.getKey())) {
                            useMap.put(KeyNames.SN.getKey(), attr.get(KeyNames.SN.getKey()).get(0).toString());
                        } else {
                            //   useMap.put(KeyNames.SN.getKey(), "Unknown");
                        }
                    } catch (Exception e) {
                        //    useMap.put(KeyNames.SN.getKey(), "Unknown");
                    }

                }

                //  useMap.put(KeyNames.IP_ADDRESS.getKey(), (String) this.gest.deviceInfo().get(DeviceInfo.IP_ADDRESS.getKey()));
                //  useMap.put(KeyNames.DEVICE_NAME.getKey(), (String) this.gest.deviceInfo().get(DeviceInfo.DEVICE_NAME.getKey()));
            } else {

                for (Object att : attributes) {
                    useMap.put(att.toString(), "Unknown");
                }
                useMap.clear();

            }

        } catch (NamingException e) {

            for (Object att : attributes) {
                useMap.put(att.toString(), "Unknown");
            }

        }

        //useMap.clear();
        return useMap;

    }

}
