package hello;

import java.util.Base64;
import java.io.*;
import org.joda.time.LocalTime;
import com.azure.storage.*;
import com.azure.storage.blob.*;
import com.azure.storage.blob.models.*;
import com.azure.storage.common.StorageSharedKeyCredential;
import com.azure.core.http.rest.*;
import com.microsoft.azure.msiAuthTokenProvider.*;

public class HelloWorld {
    public static void main(String[] args) throws AzureMSICredentialException,IOException{

        String accountName  = "grabdemsftshardingaudit0";
        String endpoint=String.format("https://%s.blob.core.windows.net",accountName);
     
        MSICredentials credsProvider = MSICredentials.getMSICredentials();
        credsProvider.updateClientId("3b62b63f-0895-44be-a050-c179c3c9b96d");
        MSIToken  token = credsProvider.getToken(null);

        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, token.accessToken().toString());
        BlobServiceClient blobStorageClient=new BlobServiceClientBuilder().endpoint(endpoint).credential(credential).buildClient();
       
        System.out.println("storage account name: "+ blobStorageClient.getAccountName());
        System.out.println("storage account url: "+ blobStorageClient.getAccountUrl());
        

    }
}


  /* 
        System.out.println(endpoint);
        BlobServiceClient blobStorageClient = new BlobServiceClientBuilder()
            .endpoint(endpoint)
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();
        BlobContainerClient containerClient= blobStorageClient.getBlobContainerClient("insights-logs-storageread");
        System.out.println("Container URL:"+containerClient.getBlobContainerUrl());
        */