/**
 * DataServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */
package com.paopao.hzgzf.modules.gzf.webservice;

import com.paopao.hzgzf.common.config.Global;

/*
*  DataServiceStub java implementation
*/

public class DataServiceStub extends org.apache.axis2.client.Stub implements DataService {
    protected org.apache.axis2.description.AxisOperation[] _operations;

    //hashmaps to keep the fault mapping
    private java.util.HashMap                              faultExceptionNameMap      = new java.util.HashMap();
    private java.util.HashMap                              faultExceptionClassNameMap = new java.util.HashMap();
    private java.util.HashMap                              faultMessageMap            = new java.util.HashMap();

    private static int                                     counter                    = 0;

    private static synchronized String getUniqueSuffix() {
        // reset the counter if it is greater than 99999
        if (counter > 99999) {
            counter = 0;
        }
        counter = counter + 1;
        return Long.toString(System.currentTimeMillis()) + "_" + counter;
    }

    private void populateAxisService() throws org.apache.axis2.AxisFault {

        //creating the Service with a unique name
        _service = new org.apache.axis2.description.AxisService("DataService" + getUniqueSuffix());
        addAnonymousOperations();

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[6];

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName("http://tempuri.org/", "addRoom"));
        _service.addOperation(__operation);

        _operations[0] = __operation;

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName("http://tempuri.org/", "test"));
        _service.addOperation(__operation);

        _operations[1] = __operation;

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName("http://tempuri.org/", "updateRenter"));
        _service.addOperation(__operation);

        _operations[2] = __operation;

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName("http://tempuri.org/",
            "updateLastPayTime"));
        _service.addOperation(__operation);

        _operations[3] = __operation;

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName("http://tempuri.org/", "assignRoom"));
        _service.addOperation(__operation);

        _operations[4] = __operation;

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName("http://tempuri.org/", "addRenter"));
        _service.addOperation(__operation);

        _operations[5] = __operation;

    }

    //populates the faults
    private void populateFaults() {

        faultExceptionNameMap
            .put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName(
                "http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                "AddRoom"),
                "com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRoom_FaultExceptionFault_FaultMessage");
        faultExceptionClassNameMap
            .put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName(
                "http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                "AddRoom"),
                "com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRoom_FaultExceptionFault_FaultMessage");
        faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName(
            "http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
            "AddRoom"), "org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE");

        faultExceptionNameMap
            .put(
                new org.apache.axis2.client.FaultMapKey(
                    new javax.xml.namespace.QName(
                        "http://schemas.datacontract.org/2004/07/System.ServiceModel",
                        "FaultException"), "UpdateRenter"),
                "com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateRenter_FaultExceptionFault_FaultMessage");
        faultExceptionClassNameMap
            .put(
                new org.apache.axis2.client.FaultMapKey(
                    new javax.xml.namespace.QName(
                        "http://schemas.datacontract.org/2004/07/System.ServiceModel",
                        "FaultException"), "UpdateRenter"),
                "com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateRenter_FaultExceptionFault_FaultMessage");
        faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName(
            "http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
            "UpdateRenter"),
            "org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE");

        faultExceptionNameMap
            .put(
                new org.apache.axis2.client.FaultMapKey(
                    new javax.xml.namespace.QName(
                        "http://schemas.datacontract.org/2004/07/System.ServiceModel",
                        "FaultException"), "UpdateLastPayTime"),
                "com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateLastPayTime_FaultExceptionFault_FaultMessage");
        faultExceptionClassNameMap
            .put(
                new org.apache.axis2.client.FaultMapKey(
                    new javax.xml.namespace.QName(
                        "http://schemas.datacontract.org/2004/07/System.ServiceModel",
                        "FaultException"), "UpdateLastPayTime"),
                "com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateLastPayTime_FaultExceptionFault_FaultMessage");
        faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName(
            "http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
            "UpdateLastPayTime"),
            "org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE");

        faultExceptionNameMap
            .put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName(
                "http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                "AssignRoom"),
                "com.paopao.hzgzf.modules.gzf.webservice.IDataService_AssignRoom_FaultExceptionFault_FaultMessage");
        faultExceptionClassNameMap
            .put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName(
                "http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                "AssignRoom"),
                "com.paopao.hzgzf.modules.gzf.webservice.IDataService_AssignRoom_FaultExceptionFault_FaultMessage");
        faultMessageMap
            .put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName(
                "http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                "AssignRoom"),
                "org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE");

        faultExceptionNameMap
            .put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName(
                "http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                "AddRenter"),
                "com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRenter_FaultExceptionFault_FaultMessage");
        faultExceptionClassNameMap
            .put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName(
                "http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
                "AddRenter"),
                "com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRenter_FaultExceptionFault_FaultMessage");
        faultMessageMap.put(new org.apache.axis2.client.FaultMapKey(new javax.xml.namespace.QName(
            "http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException"),
            "AddRenter"), "org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE");

    }

    /**
      *Constructor that takes in a configContext
      */

    public DataServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
                           String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(configurationContext, targetEndpoint, false);
    }

    /**
      * Constructor that takes in a configContext  and useseperate listner
      */
    public DataServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext,
                           String targetEndpoint, boolean useSeparateListener)
                                                                                        throws org.apache.axis2.AxisFault {
        //To populate AxisService
        populateAxisService();
        populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext, _service);

        _serviceClient.getOptions().setTo(
            new org.apache.axis2.addressing.EndpointReference(targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

    }

    /**
     * Default Constructor
     */
    public DataServiceStub(org.apache.axis2.context.ConfigurationContext configurationContext)
                                                                                              throws org.apache.axis2.AxisFault {

        this(configurationContext, "http://" + Global.getConfig("webservice.url")
                                   + "/DataService.svc");

    }

    /**
     * Default Constructor
     */
    public DataServiceStub() throws org.apache.axis2.AxisFault {

        this("http://" + Global.getConfig("webservice.url") + "/DataService.svc");

    }

    /**
     * Constructor taking the target endpoint
     */
    public DataServiceStub(String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(null, targetEndpoint);
    }

    /**
     * Auto generated method signature
     * 
     * @see com.paopao.hzgzf.modules.gzf.webservice.DataService#addRoom
     * @param addRoom12
    
     * @throws com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRoom_FaultExceptionFault_FaultMessage : 
     */

    public org.tempuri.AddRoomResponse addRoom(

    org.tempuri.AddRoom addRoom12)

    throws java.rmi.RemoteException

    , com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRoom_FaultExceptionFault_FaultMessage {
        org.apache.axis2.context.MessageContext _messageContext = null;
        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient
                .createClient(_operations[0].getName());
            _operationClient.getOptions().setAction("http://tempuri.org/IDataService/AddRoom");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(_operationClient,
                org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                "&");

            // create a message context
            _messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                addRoom12, optimizeContent(new javax.xml.namespace.QName("http://tempuri.org/",
                    "addRoom")), new javax.xml.namespace.QName("http://tempuri.org/", "addRoom"));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            //execute the operation client
            _operationClient.execute(true);

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
                .getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

            Object object = fromOM(_returnEnv.getBody().getFirstElement(),
                org.tempuri.AddRoomResponse.class, getEnvelopeNamespaces(_returnEnv));

            return (org.tempuri.AddRoomResponse) object;

        } catch (org.apache.axis2.AxisFault f) {

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(
                    faultElt.getQName(), "AddRoom"))) {
                    //make the fault by reflection
                    try {
                        String exceptionClassName = (String) faultExceptionClassNameMap
                            .get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
                                "AddRoom"));
                        Class exceptionClass = Class
                            .forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass
                            .getConstructor(String.class);
                        Exception ex = (Exception) constructor.newInstance(f
                            .getMessage());
                        //message class
                        String messageClassName = (String) faultMessageMap
                            .get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
                                "AddRoom"));
                        Class messageClass = Class.forName(messageClassName);
                        Object messageObject = fromOM(faultElt, messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                            new Class[] { messageClass });
                        m.invoke(ex, new Object[] { messageObject });

                        if (ex instanceof com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRoom_FaultExceptionFault_FaultMessage) {
                            throw (com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRoom_FaultExceptionFault_FaultMessage) ex;
                        }

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (ClassCastException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        } finally {
            if (_messageContext.getTransportOut() != null) {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
    }

    /**
    * Auto generated method signature for Asynchronous Invocations
    * 
    * @see com.paopao.hzgzf.modules.gzf.webservice.DataService#startaddRoom
        * @param addRoom12
    
    */
    public void startaddRoom(

    org.tempuri.AddRoom addRoom12,

    final com.paopao.hzgzf.modules.gzf.webservice.DataServiceCallbackHandler callback)

    throws java.rmi.RemoteException {

        org.apache.axis2.client.OperationClient _operationClient = _serviceClient
            .createClient(_operations[0].getName());
        _operationClient.getOptions().setAction("http://tempuri.org/IDataService/AddRoom");
        _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

        addPropertyToOperationClient(_operationClient,
            org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

        // create SOAP envelope with that payload
        org.apache.axiom.soap.SOAPEnvelope env = null;
        final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

        //Style is Doc.

        env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), addRoom12,
            optimizeContent(new javax.xml.namespace.QName("http://tempuri.org/", "addRoom")),
            new javax.xml.namespace.QName("http://tempuri.org/", "addRoom"));

        // adding SOAP soap_headers
        _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);

        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                try {
                    org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

                    Object object = fromOM(resultEnv.getBody().getFirstElement(),
                        org.tempuri.AddRoomResponse.class, getEnvelopeNamespaces(resultEnv));
                    callback.receiveResultaddRoom((org.tempuri.AddRoomResponse) object);

                } catch (org.apache.axis2.AxisFault e) {
                    callback.receiveErroraddRoom(e);
                }
            }

            public void onError(Exception error) {
                if (error instanceof org.apache.axis2.AxisFault) {
                    org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
                    org.apache.axiom.om.OMElement faultElt = f.getDetail();
                    if (faultElt != null) {
                        if (faultExceptionNameMap
                            .containsKey(new org.apache.axis2.client.FaultMapKey(faultElt
                                .getQName(), "AddRoom"))) {
                            //make the fault by reflection
                            try {
                                String exceptionClassName = (String) faultExceptionClassNameMap
                                    .get(new org.apache.axis2.client.FaultMapKey(faultElt
                                        .getQName(), "AddRoom"));
                                Class exceptionClass = Class
                                    .forName(exceptionClassName);
                                java.lang.reflect.Constructor constructor = exceptionClass
                                    .getConstructor(String.class);
                                Exception ex = (Exception) constructor
                                    .newInstance(f.getMessage());
                                //message class
                                String messageClassName = (String) faultMessageMap
                                    .get(new org.apache.axis2.client.FaultMapKey(faultElt
                                        .getQName(), "AddRoom"));
                                Class messageClass = Class
                                    .forName(messageClassName);
                                Object messageObject = fromOM(faultElt, messageClass,
                                    null);
                                java.lang.reflect.Method m = exceptionClass.getMethod(
                                    "setFaultMessage", new Class[] { messageClass });
                                m.invoke(ex, new Object[] { messageObject });

                                if (ex instanceof com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRoom_FaultExceptionFault_FaultMessage) {
                                    callback
                                        .receiveErroraddRoom((com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRoom_FaultExceptionFault_FaultMessage) ex);
                                    return;
                                }

                                callback.receiveErroraddRoom(new java.rmi.RemoteException(ex
                                    .getMessage(), ex));
                            } catch (ClassCastException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErroraddRoom(f);
                            } catch (ClassNotFoundException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErroraddRoom(f);
                            } catch (NoSuchMethodException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErroraddRoom(f);
                            } catch (java.lang.reflect.InvocationTargetException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErroraddRoom(f);
                            } catch (IllegalAccessException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErroraddRoom(f);
                            } catch (InstantiationException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErroraddRoom(f);
                            } catch (org.apache.axis2.AxisFault e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErroraddRoom(f);
                            }
                        } else {
                            callback.receiveErroraddRoom(f);
                        }
                    } else {
                        callback.receiveErroraddRoom(f);
                    }
                } else {
                    callback.receiveErroraddRoom(error);
                }
            }

            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
                    .getInboundFaultFromMessageContext(faultContext);
                onError(fault);
            }

            public void onComplete() {
                try {
                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                } catch (org.apache.axis2.AxisFault axisFault) {
                    callback.receiveErroraddRoom(axisFault);
                }
            }
        });

        org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if (_operations[0].getMessageReceiver() == null
            && _operationClient.getOptions().isUseSeparateListener()) {
            _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
            _operations[0].setMessageReceiver(_callbackReceiver);
        }

        //execute the operation client
        _operationClient.execute(false);

    }

    /**
     * Auto generated method signature
     * 
     * @see com.paopao.hzgzf.modules.gzf.webservice.DataService#test
     * @param test14
    
     */

    public org.tempuri.TestResponse test(

    org.tempuri.Test test14)

    throws java.rmi.RemoteException

    {
        org.apache.axis2.context.MessageContext _messageContext = null;
        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient
                .createClient(_operations[1].getName());
            _operationClient.getOptions().setAction("http://tempuri.org/IDataService/Test");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(_operationClient,
                org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                "&");

            // create a message context
            _messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), test14,
                optimizeContent(new javax.xml.namespace.QName("http://tempuri.org/", "test")),
                new javax.xml.namespace.QName("http://tempuri.org/", "test"));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            //execute the operation client
            _operationClient.execute(true);

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
                .getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

            Object object = fromOM(_returnEnv.getBody().getFirstElement(),
                org.tempuri.TestResponse.class, getEnvelopeNamespaces(_returnEnv));

            return (org.tempuri.TestResponse) object;

        } catch (org.apache.axis2.AxisFault f) {

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(
                    faultElt.getQName(), "Test"))) {
                    //make the fault by reflection
                    try {
                        String exceptionClassName = (String) faultExceptionClassNameMap
                            .get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
                                "Test"));
                        Class exceptionClass = Class
                            .forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass
                            .getConstructor(String.class);
                        Exception ex = (Exception) constructor.newInstance(f
                            .getMessage());
                        //message class
                        String messageClassName = (String) faultMessageMap
                            .get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
                                "Test"));
                        Class messageClass = Class.forName(messageClassName);
                        Object messageObject = fromOM(faultElt, messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                            new Class[] { messageClass });
                        m.invoke(ex, new Object[] { messageObject });

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (ClassCastException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        } finally {
            if (_messageContext.getTransportOut() != null) {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
    }

    /**
    * Auto generated method signature for Asynchronous Invocations
    * 
    * @see com.paopao.hzgzf.modules.gzf.webservice.DataService#starttest
        * @param test14
    
    */
    public void starttest(

    org.tempuri.Test test14,

    final com.paopao.hzgzf.modules.gzf.webservice.DataServiceCallbackHandler callback)

    throws java.rmi.RemoteException {

        org.apache.axis2.client.OperationClient _operationClient = _serviceClient
            .createClient(_operations[1].getName());
        _operationClient.getOptions().setAction("http://tempuri.org/IDataService/Test");
        _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

        addPropertyToOperationClient(_operationClient,
            org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

        // create SOAP envelope with that payload
        org.apache.axiom.soap.SOAPEnvelope env = null;
        final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

        //Style is Doc.

        env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()), test14,
            optimizeContent(new javax.xml.namespace.QName("http://tempuri.org/", "test")),
            new javax.xml.namespace.QName("http://tempuri.org/", "test"));

        // adding SOAP soap_headers
        _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);

        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                try {
                    org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

                    Object object = fromOM(resultEnv.getBody().getFirstElement(),
                        org.tempuri.TestResponse.class, getEnvelopeNamespaces(resultEnv));
                    callback.receiveResulttest((org.tempuri.TestResponse) object);

                } catch (org.apache.axis2.AxisFault e) {
                    callback.receiveErrortest(e);
                }
            }

            public void onError(Exception error) {
                if (error instanceof org.apache.axis2.AxisFault) {
                    org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
                    org.apache.axiom.om.OMElement faultElt = f.getDetail();
                    if (faultElt != null) {
                        if (faultExceptionNameMap
                            .containsKey(new org.apache.axis2.client.FaultMapKey(faultElt
                                .getQName(), "Test"))) {
                            //make the fault by reflection
                            try {
                                String exceptionClassName = (String) faultExceptionClassNameMap
                                    .get(new org.apache.axis2.client.FaultMapKey(faultElt
                                        .getQName(), "Test"));
                                Class exceptionClass = Class
                                    .forName(exceptionClassName);
                                java.lang.reflect.Constructor constructor = exceptionClass
                                    .getConstructor(String.class);
                                Exception ex = (Exception) constructor
                                    .newInstance(f.getMessage());
                                //message class
                                String messageClassName = (String) faultMessageMap
                                    .get(new org.apache.axis2.client.FaultMapKey(faultElt
                                        .getQName(), "Test"));
                                Class messageClass = Class
                                    .forName(messageClassName);
                                Object messageObject = fromOM(faultElt, messageClass,
                                    null);
                                java.lang.reflect.Method m = exceptionClass.getMethod(
                                    "setFaultMessage", new Class[] { messageClass });
                                m.invoke(ex, new Object[] { messageObject });

                                callback.receiveErrortest(new java.rmi.RemoteException(ex
                                    .getMessage(), ex));
                            } catch (ClassCastException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrortest(f);
                            } catch (ClassNotFoundException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrortest(f);
                            } catch (NoSuchMethodException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrortest(f);
                            } catch (java.lang.reflect.InvocationTargetException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrortest(f);
                            } catch (IllegalAccessException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrortest(f);
                            } catch (InstantiationException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrortest(f);
                            } catch (org.apache.axis2.AxisFault e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrortest(f);
                            }
                        } else {
                            callback.receiveErrortest(f);
                        }
                    } else {
                        callback.receiveErrortest(f);
                    }
                } else {
                    callback.receiveErrortest(error);
                }
            }

            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
                    .getInboundFaultFromMessageContext(faultContext);
                onError(fault);
            }

            public void onComplete() {
                try {
                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                } catch (org.apache.axis2.AxisFault axisFault) {
                    callback.receiveErrortest(axisFault);
                }
            }
        });

        org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if (_operations[1].getMessageReceiver() == null
            && _operationClient.getOptions().isUseSeparateListener()) {
            _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
            _operations[1].setMessageReceiver(_callbackReceiver);
        }

        //execute the operation client
        _operationClient.execute(false);

    }

    /**
     * Auto generated method signature
     * 
     * @see com.paopao.hzgzf.modules.gzf.webservice.DataService#updateRenter
     * @param updateRenter16
    
     * @throws com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateRenter_FaultExceptionFault_FaultMessage : 
     */

    public org.tempuri.UpdateRenterResponse updateRenter(

    org.tempuri.UpdateRenter updateRenter16)

                                            throws java.rmi.RemoteException

                                            ,
                                            com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateRenter_FaultExceptionFault_FaultMessage {
        org.apache.axis2.context.MessageContext _messageContext = null;
        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient
                .createClient(_operations[2].getName());
            _operationClient.getOptions().setAction("http://tempuri.org/IDataService/UpdateRenter");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(_operationClient,
                org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                "&");

            // create a message context
            _messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                updateRenter16, optimizeContent(new javax.xml.namespace.QName(
                    "http://tempuri.org/", "updateRenter")), new javax.xml.namespace.QName(
                    "http://tempuri.org/", "updateRenter"));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            //execute the operation client
            _operationClient.execute(true);

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
                .getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

            Object object = fromOM(_returnEnv.getBody().getFirstElement(),
                org.tempuri.UpdateRenterResponse.class, getEnvelopeNamespaces(_returnEnv));

            return (org.tempuri.UpdateRenterResponse) object;

        } catch (org.apache.axis2.AxisFault f) {

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(
                    faultElt.getQName(), "UpdateRenter"))) {
                    //make the fault by reflection
                    try {
                        String exceptionClassName = (String) faultExceptionClassNameMap
                            .get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
                                "UpdateRenter"));
                        Class exceptionClass = Class
                            .forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass
                            .getConstructor(String.class);
                        Exception ex = (Exception) constructor.newInstance(f
                            .getMessage());
                        //message class
                        String messageClassName = (String) faultMessageMap
                            .get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
                                "UpdateRenter"));
                        Class messageClass = Class.forName(messageClassName);
                        Object messageObject = fromOM(faultElt, messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                            new Class[] { messageClass });
                        m.invoke(ex, new Object[] { messageObject });

                        if (ex instanceof com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateRenter_FaultExceptionFault_FaultMessage) {
                            throw (com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateRenter_FaultExceptionFault_FaultMessage) ex;
                        }

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (ClassCastException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        } finally {
            if (_messageContext.getTransportOut() != null) {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
    }

    /**
    * Auto generated method signature for Asynchronous Invocations
    * 
    * @see com.paopao.hzgzf.modules.gzf.webservice.DataService#startupdateRenter
        * @param updateRenter16
    
    */
    public void startupdateRenter(

    org.tempuri.UpdateRenter updateRenter16,

    final com.paopao.hzgzf.modules.gzf.webservice.DataServiceCallbackHandler callback)

    throws java.rmi.RemoteException {

        org.apache.axis2.client.OperationClient _operationClient = _serviceClient
            .createClient(_operations[2].getName());
        _operationClient.getOptions().setAction("http://tempuri.org/IDataService/UpdateRenter");
        _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

        addPropertyToOperationClient(_operationClient,
            org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

        // create SOAP envelope with that payload
        org.apache.axiom.soap.SOAPEnvelope env = null;
        final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

        //Style is Doc.

        env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
            updateRenter16, optimizeContent(new javax.xml.namespace.QName("http://tempuri.org/",
                "updateRenter")), new javax.xml.namespace.QName("http://tempuri.org/",
                "updateRenter"));

        // adding SOAP soap_headers
        _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);

        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                try {
                    org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

                    Object object = fromOM(resultEnv.getBody().getFirstElement(),
                        org.tempuri.UpdateRenterResponse.class, getEnvelopeNamespaces(resultEnv));
                    callback.receiveResultupdateRenter((org.tempuri.UpdateRenterResponse) object);

                } catch (org.apache.axis2.AxisFault e) {
                    callback.receiveErrorupdateRenter(e);
                }
            }

            public void onError(Exception error) {
                if (error instanceof org.apache.axis2.AxisFault) {
                    org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
                    org.apache.axiom.om.OMElement faultElt = f.getDetail();
                    if (faultElt != null) {
                        if (faultExceptionNameMap
                            .containsKey(new org.apache.axis2.client.FaultMapKey(faultElt
                                .getQName(), "UpdateRenter"))) {
                            //make the fault by reflection
                            try {
                                String exceptionClassName = (String) faultExceptionClassNameMap
                                    .get(new org.apache.axis2.client.FaultMapKey(faultElt
                                        .getQName(), "UpdateRenter"));
                                Class exceptionClass = Class
                                    .forName(exceptionClassName);
                                java.lang.reflect.Constructor constructor = exceptionClass
                                    .getConstructor(String.class);
                                Exception ex = (Exception) constructor
                                    .newInstance(f.getMessage());
                                //message class
                                String messageClassName = (String) faultMessageMap
                                    .get(new org.apache.axis2.client.FaultMapKey(faultElt
                                        .getQName(), "UpdateRenter"));
                                Class messageClass = Class
                                    .forName(messageClassName);
                                Object messageObject = fromOM(faultElt, messageClass,
                                    null);
                                java.lang.reflect.Method m = exceptionClass.getMethod(
                                    "setFaultMessage", new Class[] { messageClass });
                                m.invoke(ex, new Object[] { messageObject });

                                if (ex instanceof com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateRenter_FaultExceptionFault_FaultMessage) {
                                    callback
                                        .receiveErrorupdateRenter((com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateRenter_FaultExceptionFault_FaultMessage) ex);
                                    return;
                                }

                                callback.receiveErrorupdateRenter(new java.rmi.RemoteException(ex
                                    .getMessage(), ex));
                            } catch (ClassCastException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorupdateRenter(f);
                            } catch (ClassNotFoundException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorupdateRenter(f);
                            } catch (NoSuchMethodException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorupdateRenter(f);
                            } catch (java.lang.reflect.InvocationTargetException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorupdateRenter(f);
                            } catch (IllegalAccessException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorupdateRenter(f);
                            } catch (InstantiationException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorupdateRenter(f);
                            } catch (org.apache.axis2.AxisFault e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorupdateRenter(f);
                            }
                        } else {
                            callback.receiveErrorupdateRenter(f);
                        }
                    } else {
                        callback.receiveErrorupdateRenter(f);
                    }
                } else {
                    callback.receiveErrorupdateRenter(error);
                }
            }

            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
                    .getInboundFaultFromMessageContext(faultContext);
                onError(fault);
            }

            public void onComplete() {
                try {
                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                } catch (org.apache.axis2.AxisFault axisFault) {
                    callback.receiveErrorupdateRenter(axisFault);
                }
            }
        });

        org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if (_operations[2].getMessageReceiver() == null
            && _operationClient.getOptions().isUseSeparateListener()) {
            _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
            _operations[2].setMessageReceiver(_callbackReceiver);
        }

        //execute the operation client
        _operationClient.execute(false);

    }

    /**
     * Auto generated method signature
     * 
     * @see com.paopao.hzgzf.modules.gzf.webservice.DataService#updateLastPayTime
     * @param updateLastPayTime18
    
     * @throws com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateLastPayTime_FaultExceptionFault_FaultMessage : 
     */

    public org.tempuri.UpdateLastPayTimeResponse updateLastPayTime(

    org.tempuri.UpdateLastPayTime updateLastPayTime18)

                                                      throws java.rmi.RemoteException

                                                      ,
                                                      com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateLastPayTime_FaultExceptionFault_FaultMessage {
        org.apache.axis2.context.MessageContext _messageContext = null;
        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient
                .createClient(_operations[3].getName());
            _operationClient.getOptions().setAction(
                "http://tempuri.org/IDataService/UpdateLastPayTime");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(_operationClient,
                org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                "&");

            // create a message context
            _messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                updateLastPayTime18, optimizeContent(new javax.xml.namespace.QName(
                    "http://tempuri.org/", "updateLastPayTime")), new javax.xml.namespace.QName(
                    "http://tempuri.org/", "updateLastPayTime"));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            //execute the operation client
            _operationClient.execute(true);

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
                .getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

            Object object = fromOM(_returnEnv.getBody().getFirstElement(),
                org.tempuri.UpdateLastPayTimeResponse.class, getEnvelopeNamespaces(_returnEnv));

            return (org.tempuri.UpdateLastPayTimeResponse) object;

        } catch (org.apache.axis2.AxisFault f) {

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(
                    faultElt.getQName(), "UpdateLastPayTime"))) {
                    //make the fault by reflection
                    try {
                        String exceptionClassName = (String) faultExceptionClassNameMap
                            .get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
                                "UpdateLastPayTime"));
                        Class exceptionClass = Class
                            .forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass
                            .getConstructor(String.class);
                        Exception ex = (Exception) constructor.newInstance(f
                            .getMessage());
                        //message class
                        String messageClassName = (String) faultMessageMap
                            .get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
                                "UpdateLastPayTime"));
                        Class messageClass = Class.forName(messageClassName);
                        Object messageObject = fromOM(faultElt, messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                            new Class[] { messageClass });
                        m.invoke(ex, new Object[] { messageObject });

                        if (ex instanceof com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateLastPayTime_FaultExceptionFault_FaultMessage) {
                            throw (com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateLastPayTime_FaultExceptionFault_FaultMessage) ex;
                        }

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (ClassCastException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        } finally {
            if (_messageContext.getTransportOut() != null) {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
    }

    /**
    * Auto generated method signature for Asynchronous Invocations
    * 
    * @see com.paopao.hzgzf.modules.gzf.webservice.DataService#startupdateLastPayTime
        * @param updateLastPayTime18
    
    */
    public void startupdateLastPayTime(

    org.tempuri.UpdateLastPayTime updateLastPayTime18,

    final com.paopao.hzgzf.modules.gzf.webservice.DataServiceCallbackHandler callback)

    throws java.rmi.RemoteException {

        org.apache.axis2.client.OperationClient _operationClient = _serviceClient
            .createClient(_operations[3].getName());
        _operationClient.getOptions()
            .setAction("http://tempuri.org/IDataService/UpdateLastPayTime");
        _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

        addPropertyToOperationClient(_operationClient,
            org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

        // create SOAP envelope with that payload
        org.apache.axiom.soap.SOAPEnvelope env = null;
        final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

        //Style is Doc.

        env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
            updateLastPayTime18, optimizeContent(new javax.xml.namespace.QName(
                "http://tempuri.org/", "updateLastPayTime")), new javax.xml.namespace.QName(
                "http://tempuri.org/", "updateLastPayTime"));

        // adding SOAP soap_headers
        _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);

        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                try {
                    org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

                    Object object = fromOM(resultEnv.getBody().getFirstElement(),
                        org.tempuri.UpdateLastPayTimeResponse.class,
                        getEnvelopeNamespaces(resultEnv));
                    callback
                        .receiveResultupdateLastPayTime((org.tempuri.UpdateLastPayTimeResponse) object);

                } catch (org.apache.axis2.AxisFault e) {
                    callback.receiveErrorupdateLastPayTime(e);
                }
            }

            public void onError(Exception error) {
                if (error instanceof org.apache.axis2.AxisFault) {
                    org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
                    org.apache.axiom.om.OMElement faultElt = f.getDetail();
                    if (faultElt != null) {
                        if (faultExceptionNameMap
                            .containsKey(new org.apache.axis2.client.FaultMapKey(faultElt
                                .getQName(), "UpdateLastPayTime"))) {
                            //make the fault by reflection
                            try {
                                String exceptionClassName = (String) faultExceptionClassNameMap
                                    .get(new org.apache.axis2.client.FaultMapKey(faultElt
                                        .getQName(), "UpdateLastPayTime"));
                                Class exceptionClass = Class
                                    .forName(exceptionClassName);
                                java.lang.reflect.Constructor constructor = exceptionClass
                                    .getConstructor(String.class);
                                Exception ex = (Exception) constructor
                                    .newInstance(f.getMessage());
                                //message class
                                String messageClassName = (String) faultMessageMap
                                    .get(new org.apache.axis2.client.FaultMapKey(faultElt
                                        .getQName(), "UpdateLastPayTime"));
                                Class messageClass = Class
                                    .forName(messageClassName);
                                Object messageObject = fromOM(faultElt, messageClass,
                                    null);
                                java.lang.reflect.Method m = exceptionClass.getMethod(
                                    "setFaultMessage", new Class[] { messageClass });
                                m.invoke(ex, new Object[] { messageObject });

                                if (ex instanceof com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateLastPayTime_FaultExceptionFault_FaultMessage) {
                                    callback
                                        .receiveErrorupdateLastPayTime((com.paopao.hzgzf.modules.gzf.webservice.IDataService_UpdateLastPayTime_FaultExceptionFault_FaultMessage) ex);
                                    return;
                                }

                                callback
                                    .receiveErrorupdateLastPayTime(new java.rmi.RemoteException(ex
                                        .getMessage(), ex));
                            } catch (ClassCastException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorupdateLastPayTime(f);
                            } catch (ClassNotFoundException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorupdateLastPayTime(f);
                            } catch (NoSuchMethodException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorupdateLastPayTime(f);
                            } catch (java.lang.reflect.InvocationTargetException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorupdateLastPayTime(f);
                            } catch (IllegalAccessException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorupdateLastPayTime(f);
                            } catch (InstantiationException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorupdateLastPayTime(f);
                            } catch (org.apache.axis2.AxisFault e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorupdateLastPayTime(f);
                            }
                        } else {
                            callback.receiveErrorupdateLastPayTime(f);
                        }
                    } else {
                        callback.receiveErrorupdateLastPayTime(f);
                    }
                } else {
                    callback.receiveErrorupdateLastPayTime(error);
                }
            }

            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
                    .getInboundFaultFromMessageContext(faultContext);
                onError(fault);
            }

            public void onComplete() {
                try {
                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                } catch (org.apache.axis2.AxisFault axisFault) {
                    callback.receiveErrorupdateLastPayTime(axisFault);
                }
            }
        });

        org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if (_operations[3].getMessageReceiver() == null
            && _operationClient.getOptions().isUseSeparateListener()) {
            _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
            _operations[3].setMessageReceiver(_callbackReceiver);
        }

        //execute the operation client
        _operationClient.execute(false);

    }

    /**
     * Auto generated method signature
     * 
     * @see com.paopao.hzgzf.modules.gzf.webservice.DataService#assignRoom
     * @param assignRoom20
    
     * @throws com.paopao.hzgzf.modules.gzf.webservice.IDataService_AssignRoom_FaultExceptionFault_FaultMessage : 
     */

    public org.tempuri.AssignRoomResponse assignRoom(

    org.tempuri.AssignRoom assignRoom20)

                                        throws java.rmi.RemoteException

                                        ,
                                        com.paopao.hzgzf.modules.gzf.webservice.IDataService_AssignRoom_FaultExceptionFault_FaultMessage {
        org.apache.axis2.context.MessageContext _messageContext = null;
        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient
                .createClient(_operations[4].getName());
            _operationClient.getOptions().setAction("http://tempuri.org/IDataService/AssignRoom");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(_operationClient,
                org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                "&");

            // create a message context
            _messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                assignRoom20, optimizeContent(new javax.xml.namespace.QName("http://tempuri.org/",
                    "assignRoom")), new javax.xml.namespace.QName("http://tempuri.org/",
                    "assignRoom"));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            //execute the operation client
            _operationClient.execute(true);

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
                .getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

            Object object = fromOM(_returnEnv.getBody().getFirstElement(),
                org.tempuri.AssignRoomResponse.class, getEnvelopeNamespaces(_returnEnv));

            return (org.tempuri.AssignRoomResponse) object;

        } catch (org.apache.axis2.AxisFault f) {

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(
                    faultElt.getQName(), "AssignRoom"))) {
                    //make the fault by reflection
                    try {
                        String exceptionClassName = (String) faultExceptionClassNameMap
                            .get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
                                "AssignRoom"));
                        Class exceptionClass = Class
                            .forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass
                            .getConstructor(String.class);
                        Exception ex = (Exception) constructor.newInstance(f
                            .getMessage());
                        //message class
                        String messageClassName = (String) faultMessageMap
                            .get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
                                "AssignRoom"));
                        Class messageClass = Class.forName(messageClassName);
                        Object messageObject = fromOM(faultElt, messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                            new Class[] { messageClass });
                        m.invoke(ex, new Object[] { messageObject });

                        if (ex instanceof com.paopao.hzgzf.modules.gzf.webservice.IDataService_AssignRoom_FaultExceptionFault_FaultMessage) {
                            throw (com.paopao.hzgzf.modules.gzf.webservice.IDataService_AssignRoom_FaultExceptionFault_FaultMessage) ex;
                        }

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (ClassCastException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        } finally {
            if (_messageContext.getTransportOut() != null) {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
    }

    /**
    * Auto generated method signature for Asynchronous Invocations
    * 
    * @see com.paopao.hzgzf.modules.gzf.webservice.DataService#startassignRoom
        * @param assignRoom20
    
    */
    public void startassignRoom(

    org.tempuri.AssignRoom assignRoom20,

    final com.paopao.hzgzf.modules.gzf.webservice.DataServiceCallbackHandler callback)

    throws java.rmi.RemoteException {

        org.apache.axis2.client.OperationClient _operationClient = _serviceClient
            .createClient(_operations[4].getName());
        _operationClient.getOptions().setAction("http://tempuri.org/IDataService/AssignRoom");
        _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

        addPropertyToOperationClient(_operationClient,
            org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

        // create SOAP envelope with that payload
        org.apache.axiom.soap.SOAPEnvelope env = null;
        final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

        //Style is Doc.

        env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
            assignRoom20, optimizeContent(new javax.xml.namespace.QName("http://tempuri.org/",
                "assignRoom")), new javax.xml.namespace.QName("http://tempuri.org/", "assignRoom"));

        // adding SOAP soap_headers
        _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);

        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                try {
                    org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

                    Object object = fromOM(resultEnv.getBody().getFirstElement(),
                        org.tempuri.AssignRoomResponse.class, getEnvelopeNamespaces(resultEnv));
                    callback.receiveResultassignRoom((org.tempuri.AssignRoomResponse) object);

                } catch (org.apache.axis2.AxisFault e) {
                    callback.receiveErrorassignRoom(e);
                }
            }

            public void onError(Exception error) {
                if (error instanceof org.apache.axis2.AxisFault) {
                    org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
                    org.apache.axiom.om.OMElement faultElt = f.getDetail();
                    if (faultElt != null) {
                        if (faultExceptionNameMap
                            .containsKey(new org.apache.axis2.client.FaultMapKey(faultElt
                                .getQName(), "AssignRoom"))) {
                            //make the fault by reflection
                            try {
                                String exceptionClassName = (String) faultExceptionClassNameMap
                                    .get(new org.apache.axis2.client.FaultMapKey(faultElt
                                        .getQName(), "AssignRoom"));
                                Class exceptionClass = Class
                                    .forName(exceptionClassName);
                                java.lang.reflect.Constructor constructor = exceptionClass
                                    .getConstructor(String.class);
                                Exception ex = (Exception) constructor
                                    .newInstance(f.getMessage());
                                //message class
                                String messageClassName = (String) faultMessageMap
                                    .get(new org.apache.axis2.client.FaultMapKey(faultElt
                                        .getQName(), "AssignRoom"));
                                Class messageClass = Class
                                    .forName(messageClassName);
                                Object messageObject = fromOM(faultElt, messageClass,
                                    null);
                                java.lang.reflect.Method m = exceptionClass.getMethod(
                                    "setFaultMessage", new Class[] { messageClass });
                                m.invoke(ex, new Object[] { messageObject });

                                if (ex instanceof com.paopao.hzgzf.modules.gzf.webservice.IDataService_AssignRoom_FaultExceptionFault_FaultMessage) {
                                    callback
                                        .receiveErrorassignRoom((com.paopao.hzgzf.modules.gzf.webservice.IDataService_AssignRoom_FaultExceptionFault_FaultMessage) ex);
                                    return;
                                }

                                callback.receiveErrorassignRoom(new java.rmi.RemoteException(ex
                                    .getMessage(), ex));
                            } catch (ClassCastException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorassignRoom(f);
                            } catch (ClassNotFoundException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorassignRoom(f);
                            } catch (NoSuchMethodException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorassignRoom(f);
                            } catch (java.lang.reflect.InvocationTargetException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorassignRoom(f);
                            } catch (IllegalAccessException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorassignRoom(f);
                            } catch (InstantiationException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorassignRoom(f);
                            } catch (org.apache.axis2.AxisFault e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErrorassignRoom(f);
                            }
                        } else {
                            callback.receiveErrorassignRoom(f);
                        }
                    } else {
                        callback.receiveErrorassignRoom(f);
                    }
                } else {
                    callback.receiveErrorassignRoom(error);
                }
            }

            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
                    .getInboundFaultFromMessageContext(faultContext);
                onError(fault);
            }

            public void onComplete() {
                try {
                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                } catch (org.apache.axis2.AxisFault axisFault) {
                    callback.receiveErrorassignRoom(axisFault);
                }
            }
        });

        org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if (_operations[4].getMessageReceiver() == null
            && _operationClient.getOptions().isUseSeparateListener()) {
            _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
            _operations[4].setMessageReceiver(_callbackReceiver);
        }

        //execute the operation client
        _operationClient.execute(false);

    }

    /**
     * Auto generated method signature
     * 
     * @see com.paopao.hzgzf.modules.gzf.webservice.DataService#addRenter
     * @param addRenter22
    
     * @throws com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRenter_FaultExceptionFault_FaultMessage : 
     */

    public org.tempuri.AddRenterResponse addRenter(

    org.tempuri.AddRenter addRenter22)

                                      throws java.rmi.RemoteException

                                      ,
                                      com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRenter_FaultExceptionFault_FaultMessage {
        org.apache.axis2.context.MessageContext _messageContext = null;
        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient
                .createClient(_operations[5].getName());
            _operationClient.getOptions().setAction("http://tempuri.org/IDataService/AddRenter");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(_operationClient,
                org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                "&");

            // create a message context
            _messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
                addRenter22, optimizeContent(new javax.xml.namespace.QName("http://tempuri.org/",
                    "addRenter")),
                new javax.xml.namespace.QName("http://tempuri.org/", "addRenter"));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            //execute the operation client
            _operationClient.execute(true);

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient
                .getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

            Object object = fromOM(_returnEnv.getBody().getFirstElement(),
                org.tempuri.AddRenterResponse.class, getEnvelopeNamespaces(_returnEnv));

            return (org.tempuri.AddRenterResponse) object;

        } catch (org.apache.axis2.AxisFault f) {

            org.apache.axiom.om.OMElement faultElt = f.getDetail();
            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(new org.apache.axis2.client.FaultMapKey(
                    faultElt.getQName(), "AddRenter"))) {
                    //make the fault by reflection
                    try {
                        String exceptionClassName = (String) faultExceptionClassNameMap
                            .get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
                                "AddRenter"));
                        Class exceptionClass = Class
                            .forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass
                            .getConstructor(String.class);
                        Exception ex = (Exception) constructor.newInstance(f
                            .getMessage());
                        //message class
                        String messageClassName = (String) faultMessageMap
                            .get(new org.apache.axis2.client.FaultMapKey(faultElt.getQName(),
                                "AddRenter"));
                        Class messageClass = Class.forName(messageClassName);
                        Object messageObject = fromOM(faultElt, messageClass, null);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                            new Class[] { messageClass });
                        m.invoke(ex, new Object[] { messageObject });

                        if (ex instanceof com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRenter_FaultExceptionFault_FaultMessage) {
                            throw (com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRenter_FaultExceptionFault_FaultMessage) ex;
                        }

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (ClassCastException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        } finally {
            if (_messageContext.getTransportOut() != null) {
                _messageContext.getTransportOut().getSender().cleanup(_messageContext);
            }
        }
    }

    /**
    * Auto generated method signature for Asynchronous Invocations
    * 
    * @see com.paopao.hzgzf.modules.gzf.webservice.DataService#startaddRenter
        * @param addRenter22
    
    */
    public void startaddRenter(

    org.tempuri.AddRenter addRenter22,

    final com.paopao.hzgzf.modules.gzf.webservice.DataServiceCallbackHandler callback)

    throws java.rmi.RemoteException {

        org.apache.axis2.client.OperationClient _operationClient = _serviceClient
            .createClient(_operations[5].getName());
        _operationClient.getOptions().setAction("http://tempuri.org/IDataService/AddRenter");
        _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

        addPropertyToOperationClient(_operationClient,
            org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR, "&");

        // create SOAP envelope with that payload
        org.apache.axiom.soap.SOAPEnvelope env = null;
        final org.apache.axis2.context.MessageContext _messageContext = new org.apache.axis2.context.MessageContext();

        //Style is Doc.

        env = toEnvelope(getFactory(_operationClient.getOptions().getSoapVersionURI()),
            addRenter22, optimizeContent(new javax.xml.namespace.QName("http://tempuri.org/",
                "addRenter")), new javax.xml.namespace.QName("http://tempuri.org/", "addRenter"));

        // adding SOAP soap_headers
        _serviceClient.addHeadersToEnvelope(env);
        // create message context with that soap envelope
        _messageContext.setEnvelope(env);

        // add the message context to the operation client
        _operationClient.addMessageContext(_messageContext);

        _operationClient.setCallback(new org.apache.axis2.client.async.AxisCallback() {
            public void onMessage(org.apache.axis2.context.MessageContext resultContext) {
                try {
                    org.apache.axiom.soap.SOAPEnvelope resultEnv = resultContext.getEnvelope();

                    Object object = fromOM(resultEnv.getBody().getFirstElement(),
                        org.tempuri.AddRenterResponse.class, getEnvelopeNamespaces(resultEnv));
                    callback.receiveResultaddRenter((org.tempuri.AddRenterResponse) object);

                } catch (org.apache.axis2.AxisFault e) {
                    callback.receiveErroraddRenter(e);
                }
            }

            public void onError(Exception error) {
                if (error instanceof org.apache.axis2.AxisFault) {
                    org.apache.axis2.AxisFault f = (org.apache.axis2.AxisFault) error;
                    org.apache.axiom.om.OMElement faultElt = f.getDetail();
                    if (faultElt != null) {
                        if (faultExceptionNameMap
                            .containsKey(new org.apache.axis2.client.FaultMapKey(faultElt
                                .getQName(), "AddRenter"))) {
                            //make the fault by reflection
                            try {
                                String exceptionClassName = (String) faultExceptionClassNameMap
                                    .get(new org.apache.axis2.client.FaultMapKey(faultElt
                                        .getQName(), "AddRenter"));
                                Class exceptionClass = Class
                                    .forName(exceptionClassName);
                                java.lang.reflect.Constructor constructor = exceptionClass
                                    .getConstructor(String.class);
                                Exception ex = (Exception) constructor
                                    .newInstance(f.getMessage());
                                //message class
                                String messageClassName = (String) faultMessageMap
                                    .get(new org.apache.axis2.client.FaultMapKey(faultElt
                                        .getQName(), "AddRenter"));
                                Class messageClass = Class
                                    .forName(messageClassName);
                                Object messageObject = fromOM(faultElt, messageClass,
                                    null);
                                java.lang.reflect.Method m = exceptionClass.getMethod(
                                    "setFaultMessage", new Class[] { messageClass });
                                m.invoke(ex, new Object[] { messageObject });

                                if (ex instanceof com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRenter_FaultExceptionFault_FaultMessage) {
                                    callback
                                        .receiveErroraddRenter((com.paopao.hzgzf.modules.gzf.webservice.IDataService_AddRenter_FaultExceptionFault_FaultMessage) ex);
                                    return;
                                }

                                callback.receiveErroraddRenter(new java.rmi.RemoteException(ex
                                    .getMessage(), ex));
                            } catch (ClassCastException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErroraddRenter(f);
                            } catch (ClassNotFoundException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErroraddRenter(f);
                            } catch (NoSuchMethodException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErroraddRenter(f);
                            } catch (java.lang.reflect.InvocationTargetException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErroraddRenter(f);
                            } catch (IllegalAccessException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErroraddRenter(f);
                            } catch (InstantiationException e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErroraddRenter(f);
                            } catch (org.apache.axis2.AxisFault e) {
                                // we cannot intantiate the class - throw the original Axis fault
                                callback.receiveErroraddRenter(f);
                            }
                        } else {
                            callback.receiveErroraddRenter(f);
                        }
                    } else {
                        callback.receiveErroraddRenter(f);
                    }
                } else {
                    callback.receiveErroraddRenter(error);
                }
            }

            public void onFault(org.apache.axis2.context.MessageContext faultContext) {
                org.apache.axis2.AxisFault fault = org.apache.axis2.util.Utils
                    .getInboundFaultFromMessageContext(faultContext);
                onError(fault);
            }

            public void onComplete() {
                try {
                    _messageContext.getTransportOut().getSender().cleanup(_messageContext);
                } catch (org.apache.axis2.AxisFault axisFault) {
                    callback.receiveErroraddRenter(axisFault);
                }
            }
        });

        org.apache.axis2.util.CallbackReceiver _callbackReceiver = null;
        if (_operations[5].getMessageReceiver() == null
            && _operationClient.getOptions().isUseSeparateListener()) {
            _callbackReceiver = new org.apache.axis2.util.CallbackReceiver();
            _operations[5].setMessageReceiver(_callbackReceiver);
        }

        //execute the operation client
        _operationClient.execute(false);

    }

    /**
     *  A utility method that copies the namepaces from the SOAPEnvelope
     */
    private java.util.Map getEnvelopeNamespaces(org.apache.axiom.soap.SOAPEnvelope env) {
        java.util.Map returnMap = new java.util.HashMap();
        java.util.Iterator namespaceIterator = env.getAllDeclaredNamespaces();
        while (namespaceIterator.hasNext()) {
            org.apache.axiom.om.OMNamespace ns = (org.apache.axiom.om.OMNamespace) namespaceIterator
                .next();
            returnMap.put(ns.getPrefix(), ns.getNamespaceURI());
        }
        return returnMap;
    }

    private javax.xml.namespace.QName[] opNameArray = null;

    private boolean optimizeContent(javax.xml.namespace.QName opName) {

        if (opNameArray == null) {
            return false;
        }
        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;
            }
        }
        return false;
    }

    //http://122.224.179.211:9015/DataService.svc
    private org.apache.axiom.om.OMElement toOM(org.tempuri.AddRoom param, boolean optimizeContent)
                                                                                                  throws org.apache.axis2.AxisFault {

        try {
            return param.getOMElement(org.tempuri.AddRoom.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(org.tempuri.AddRoomResponse param,
                                               boolean optimizeContent)
                                                                       throws org.apache.axis2.AxisFault {

        try {
            return param.getOMElement(org.tempuri.AddRoomResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE param,
                                               boolean optimizeContent)
                                                                       throws org.apache.axis2.AxisFault {

        try {
            return param.getOMElement(
                org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(org.tempuri.Test param, boolean optimizeContent)
                                                                                               throws org.apache.axis2.AxisFault {

        try {
            return param.getOMElement(org.tempuri.Test.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(org.tempuri.TestResponse param,
                                               boolean optimizeContent)
                                                                       throws org.apache.axis2.AxisFault {

        try {
            return param.getOMElement(org.tempuri.TestResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(org.tempuri.UpdateRenter param,
                                               boolean optimizeContent)
                                                                       throws org.apache.axis2.AxisFault {

        try {
            return param.getOMElement(org.tempuri.UpdateRenter.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(org.tempuri.UpdateRenterResponse param,
                                               boolean optimizeContent)
                                                                       throws org.apache.axis2.AxisFault {

        try {
            return param.getOMElement(org.tempuri.UpdateRenterResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(org.tempuri.UpdateLastPayTime param,
                                               boolean optimizeContent)
                                                                       throws org.apache.axis2.AxisFault {

        try {
            return param.getOMElement(org.tempuri.UpdateLastPayTime.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(org.tempuri.UpdateLastPayTimeResponse param,
                                               boolean optimizeContent)
                                                                       throws org.apache.axis2.AxisFault {

        try {
            return param.getOMElement(org.tempuri.UpdateLastPayTimeResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(org.tempuri.AssignRoom param, boolean optimizeContent)
                                                                                                     throws org.apache.axis2.AxisFault {

        try {
            return param.getOMElement(org.tempuri.AssignRoom.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(org.tempuri.AssignRoomResponse param,
                                               boolean optimizeContent)
                                                                       throws org.apache.axis2.AxisFault {

        try {
            return param.getOMElement(org.tempuri.AssignRoomResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(org.tempuri.AddRenter param, boolean optimizeContent)
                                                                                                    throws org.apache.axis2.AxisFault {

        try {
            return param.getOMElement(org.tempuri.AddRenter.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.om.OMElement toOM(org.tempuri.AddRenterResponse param,
                                               boolean optimizeContent)
                                                                       throws org.apache.axis2.AxisFault {

        try {
            return param.getOMElement(org.tempuri.AddRenterResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
                                                          org.tempuri.AddRoom param,
                                                          boolean optimizeContent,
                                                          javax.xml.namespace.QName methodQName)
                                                                                                throws org.apache.axis2.AxisFault {

        try {

            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody().addChild(
                param.getOMElement(org.tempuri.AddRoom.MY_QNAME, factory));
            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    /* methods to provide back word compatibility */

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
                                                          org.tempuri.Test param,
                                                          boolean optimizeContent,
                                                          javax.xml.namespace.QName methodQName)
                                                                                                throws org.apache.axis2.AxisFault {

        try {

            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody()
                .addChild(param.getOMElement(org.tempuri.Test.MY_QNAME, factory));
            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    /* methods to provide back word compatibility */

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
                                                          org.tempuri.UpdateRenter param,
                                                          boolean optimizeContent,
                                                          javax.xml.namespace.QName methodQName)
                                                                                                throws org.apache.axis2.AxisFault {

        try {

            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody().addChild(
                param.getOMElement(org.tempuri.UpdateRenter.MY_QNAME, factory));
            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    /* methods to provide back word compatibility */

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
                                                          org.tempuri.UpdateLastPayTime param,
                                                          boolean optimizeContent,
                                                          javax.xml.namespace.QName methodQName)
                                                                                                throws org.apache.axis2.AxisFault {

        try {

            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody().addChild(
                param.getOMElement(org.tempuri.UpdateLastPayTime.MY_QNAME, factory));
            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    /* methods to provide back word compatibility */

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
                                                          org.tempuri.AssignRoom param,
                                                          boolean optimizeContent,
                                                          javax.xml.namespace.QName methodQName)
                                                                                                throws org.apache.axis2.AxisFault {

        try {

            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody().addChild(
                param.getOMElement(org.tempuri.AssignRoom.MY_QNAME, factory));
            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    /* methods to provide back word compatibility */

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory,
                                                          org.tempuri.AddRenter param,
                                                          boolean optimizeContent,
                                                          javax.xml.namespace.QName methodQName)
                                                                                                throws org.apache.axis2.AxisFault {

        try {

            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody().addChild(
                param.getOMElement(org.tempuri.AddRenter.MY_QNAME, factory));
            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

    }

    /* methods to provide back word compatibility */

    /**
    *  get the default envelope
    */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private Object fromOM(org.apache.axiom.om.OMElement param, Class type,
                                    java.util.Map extraNamespaces)
                                                                  throws org.apache.axis2.AxisFault {

        try {

            if (org.tempuri.AddRoom.class.equals(type)) {

                return org.tempuri.AddRoom.Factory.parse(param.getXMLStreamReaderWithoutCaching());

            }

            if (org.tempuri.AddRoomResponse.class.equals(type)) {

                return org.tempuri.AddRoomResponse.Factory.parse(param
                    .getXMLStreamReaderWithoutCaching());

            }

            if (org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE.class
                .equals(type)) {

                return org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE.Factory
                    .parse(param.getXMLStreamReaderWithoutCaching());

            }

            if (org.tempuri.Test.class.equals(type)) {

                return org.tempuri.Test.Factory.parse(param.getXMLStreamReaderWithoutCaching());

            }

            if (org.tempuri.TestResponse.class.equals(type)) {

                return org.tempuri.TestResponse.Factory.parse(param
                    .getXMLStreamReaderWithoutCaching());

            }

            if (org.tempuri.UpdateRenter.class.equals(type)) {

                return org.tempuri.UpdateRenter.Factory.parse(param
                    .getXMLStreamReaderWithoutCaching());

            }

            if (org.tempuri.UpdateRenterResponse.class.equals(type)) {

                return org.tempuri.UpdateRenterResponse.Factory.parse(param
                    .getXMLStreamReaderWithoutCaching());

            }

            if (org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE.class
                .equals(type)) {

                return org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE.Factory
                    .parse(param.getXMLStreamReaderWithoutCaching());

            }

            if (org.tempuri.UpdateLastPayTime.class.equals(type)) {

                return org.tempuri.UpdateLastPayTime.Factory.parse(param
                    .getXMLStreamReaderWithoutCaching());

            }

            if (org.tempuri.UpdateLastPayTimeResponse.class.equals(type)) {

                return org.tempuri.UpdateLastPayTimeResponse.Factory.parse(param
                    .getXMLStreamReaderWithoutCaching());

            }

            if (org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE.class
                .equals(type)) {

                return org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE.Factory
                    .parse(param.getXMLStreamReaderWithoutCaching());

            }

            if (org.tempuri.AssignRoom.class.equals(type)) {

                return org.tempuri.AssignRoom.Factory.parse(param
                    .getXMLStreamReaderWithoutCaching());

            }

            if (org.tempuri.AssignRoomResponse.class.equals(type)) {

                return org.tempuri.AssignRoomResponse.Factory.parse(param
                    .getXMLStreamReaderWithoutCaching());

            }

            if (org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE.class
                .equals(type)) {

                return org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE.Factory
                    .parse(param.getXMLStreamReaderWithoutCaching());

            }

            if (org.tempuri.AddRenter.class.equals(type)) {

                return org.tempuri.AddRenter.Factory
                    .parse(param.getXMLStreamReaderWithoutCaching());

            }

            if (org.tempuri.AddRenterResponse.class.equals(type)) {

                return org.tempuri.AddRenterResponse.Factory.parse(param
                    .getXMLStreamReaderWithoutCaching());

            }

            if (org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE.class
                .equals(type)) {

                return org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionE.Factory
                    .parse(param.getXMLStreamReaderWithoutCaching());

            }

        } catch (Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
        return null;
    }

}
