
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

        
            package org.tempuri;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static Object getTypeObject(String namespaceURI,
                                                       String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws Exception{

              
                  if (
                  "http://schemas.datacontract.org/2004/07/System.ServiceModel".equals(namespaceURI) &&
                  "FaultException.FaultReasonData".equals(typeName)){
                   
                            return  org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionFaultReasonData.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.microsoft.com/2003/10/Serialization/".equals(namespaceURI) &&
                  "char".equals(typeName)){
                   
                            return  com.microsoft.schemas._2003._10.serialization._char.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.datacontract.org/2004/07/System.ServiceModel".equals(namespaceURI) &&
                  "FaultException".equals(typeName)){
                   
                            return  org.datacontract.schemas._2004._07.system_servicemodel.FaultException.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.datacontract.org/2004/07/System.ServiceModel".equals(namespaceURI) &&
                  "FaultException.FaultCodeData".equals(typeName)){
                   
                            return  org.datacontract.schemas._2004._07.system_servicemodel.FaultExceptionFaultCodeData.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.datacontract.org/2004/07/CommunityServerSync".equals(namespaceURI) &&
                  "Room".equals(typeName)){
                   
                            return  org.datacontract.schemas._2004._07.communityserversync.Room.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.microsoft.com/2003/10/Serialization/Arrays".equals(namespaceURI) &&
                  "ArrayOfstring".equals(typeName)){
                   
                            return  com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.datacontract.org/2004/07/System.ServiceModel".equals(namespaceURI) &&
                  "ArrayOfFaultException.FaultCodeData".equals(typeName)){
                   
                            return  org.datacontract.schemas._2004._07.system_servicemodel.ArrayOfFaultExceptionFaultCodeData.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.datacontract.org/2004/07/CommunityServerSync".equals(namespaceURI) &&
                  "Renter".equals(typeName)){
                   
                            return  org.datacontract.schemas._2004._07.communityserversync.Renter.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.microsoft.com/2003/10/Serialization/".equals(namespaceURI) &&
                  "guid".equals(typeName)){
                   
                            return  com.microsoft.schemas._2003._10.serialization.Guid.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.datacontract.org/2004/07/System.ServiceModel".equals(namespaceURI) &&
                  "ArrayOfFaultException.FaultReasonData".equals(typeName)){
                   
                            return  org.datacontract.schemas._2004._07.system_servicemodel.ArrayOfFaultExceptionFaultReasonData.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.microsoft.com/2003/10/Serialization/".equals(namespaceURI) &&
                  "duration".equals(typeName)){
                   
                            return  com.microsoft.schemas._2003._10.serialization.Duration.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.datacontract.org/2004/07/System".equals(namespaceURI) &&
                  "Exception".equals(typeName)){
                   
                            return  org.datacontract.schemas._2004._07.system.Exception.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.datacontract.org/2004/07/System".equals(namespaceURI) &&
                  "SystemException".equals(typeName)){
                   
                            return  org.datacontract.schemas._2004._07.system.SystemException.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://schemas.datacontract.org/2004/07/System.ServiceModel".equals(namespaceURI) &&
                  "CommunicationException".equals(typeName)){
                   
                            return  org.datacontract.schemas._2004._07.system_servicemodel.CommunicationException.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    