
/**
 * DataServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.paopao.hzgzf.modules.gzf.webservice;

    /**
     *  DataServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class DataServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public DataServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public DataServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for addRoom method
            * override this method for handling normal response from addRoom operation
            */
           public void receiveResultaddRoom(
                    org.tempuri.AddRoomResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addRoom operation
           */
            public void receiveErroraddRoom(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for test method
            * override this method for handling normal response from test operation
            */
           public void receiveResulttest(
                    org.tempuri.TestResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from test operation
           */
            public void receiveErrortest(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateRenter method
            * override this method for handling normal response from updateRenter operation
            */
           public void receiveResultupdateRenter(
                    org.tempuri.UpdateRenterResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateRenter operation
           */
            public void receiveErrorupdateRenter(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateLastPayTime method
            * override this method for handling normal response from updateLastPayTime operation
            */
           public void receiveResultupdateLastPayTime(
                    org.tempuri.UpdateLastPayTimeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateLastPayTime operation
           */
            public void receiveErrorupdateLastPayTime(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for assignRoom method
            * override this method for handling normal response from assignRoom operation
            */
           public void receiveResultassignRoom(
                    org.tempuri.AssignRoomResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from assignRoom operation
           */
            public void receiveErrorassignRoom(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addRenter method
            * override this method for handling normal response from addRenter operation
            */
           public void receiveResultaddRenter(
                    org.tempuri.AddRenterResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addRenter operation
           */
            public void receiveErroraddRenter(Exception e) {
            }
                


    }
    