
/**
 * DataService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.paopao.hzgzf.modules.gzf.webservice;

/*
 *  DataService java interface
 */

public interface DataService {

    /**
      * Auto generated method signature
      * 
                * @param addRoom0
            
         * @throws com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRoom_FaultExceptionFault_FaultMessage : 
     */

    public org.tempuri.AddRoomResponse addRoom(

            org.tempuri.AddRoom addRoom0) throws java.rmi.RemoteException

    , com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRoom_FaultExceptionFault_FaultMessage;

    /**
       * Auto generated method signature for Asynchronous Invocations
       * 
           * @param addRoom0
       
     */
    public void startaddRoom(

            org.tempuri.AddRoom addRoom0,

            final com.paopao.hzgzf.modules.gzf.webservice.DataServiceCallbackHandler callback)

    throws java.rmi.RemoteException;

    /**
      * Auto generated method signature
      * 
                * @param test2
            
     */

    public org.tempuri.TestResponse test(

            org.tempuri.Test test2) throws java.rmi.RemoteException;

    /**
       * Auto generated method signature for Asynchronous Invocations
       * 
           * @param test2
       
     */
    public void starttest(

            org.tempuri.Test test2,

            final com.paopao.hzgzf.modules.gzf.webservice.DataServiceCallbackHandler callback)

    throws java.rmi.RemoteException;

    /**
      * Auto generated method signature
      * 
                * @param updateRenter4
            
         * @throws com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateRenter_FaultExceptionFault_FaultMessage : 
     */

    public org.tempuri.UpdateRenterResponse updateRenter(

            org.tempuri.UpdateRenter updateRenter4)
                                           throws java.rmi.RemoteException

                                           ,
                                           com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateRenter_FaultExceptionFault_FaultMessage;

    /**
       * Auto generated method signature for Asynchronous Invocations
       * 
           * @param updateRenter4
       
     */
    public void startupdateRenter(

            org.tempuri.UpdateRenter updateRenter4,

            final com.paopao.hzgzf.modules.gzf.webservice.DataServiceCallbackHandler callback)

    throws java.rmi.RemoteException;

    /**
      * Auto generated method signature
      * 
                * @param updateLastPayTime6
            
         * @throws com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateLastPayTime_FaultExceptionFault_FaultMessage : 
     */

    public org.tempuri.UpdateLastPayTimeResponse updateLastPayTime(

            org.tempuri.UpdateLastPayTime updateLastPayTime6)
                                                     throws java.rmi.RemoteException

                                                     ,
                                                     com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateLastPayTime_FaultExceptionFault_FaultMessage;

    /**
       * Auto generated method signature for Asynchronous Invocations
       * 
           * @param updateLastPayTime6
       
     */
    public void startupdateLastPayTime(

            org.tempuri.UpdateLastPayTime updateLastPayTime6,

            final com.paopao.hzgzf.modules.gzf.webservice.DataServiceCallbackHandler callback)

    throws java.rmi.RemoteException;

    /**
      * Auto generated method signature
      * 
                * @param assignRoom8
            
         * @throws com.paopao.hzgzf.modules.gzf.webservice.IDataService_AssignRoom_FaultExceptionFault_FaultMessage : 
     */

    public org.tempuri.AssignRoomResponse assignRoom(

            org.tempuri.AssignRoom assignRoom8)
                                       throws java.rmi.RemoteException

                                       ,
                                       com.paopao.hzgzf.modules.gzf.webservice.IDataService_AssignRoom_FaultExceptionFault_FaultMessage;

    /**
       * Auto generated method signature for Asynchronous Invocations
       * 
           * @param assignRoom8
       
     */
    public void startassignRoom(

            org.tempuri.AssignRoom assignRoom8,

            final com.paopao.hzgzf.modules.gzf.webservice.DataServiceCallbackHandler callback)

    throws java.rmi.RemoteException;

    /**
      * Auto generated method signature
      * 
                * @param addRenter10
            
         * @throws com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRenter_FaultExceptionFault_FaultMessage : 
     */

    public org.tempuri.AddRenterResponse addRenter(

            org.tempuri.AddRenter addRenter10)
                                      throws java.rmi.RemoteException

                                      ,
                                      com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRenter_FaultExceptionFault_FaultMessage;

    /**
       * Auto generated method signature for Asynchronous Invocations
       * 
           * @param addRenter10
       
     */
    public void startaddRenter(

            org.tempuri.AddRenter addRenter10,

            final com.paopao.hzgzf.modules.gzf.webservice.DataServiceCallbackHandler callback)

    throws java.rmi.RemoteException;

    //
}
