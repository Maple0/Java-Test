package hello;

import org.joda.time.LocalTime;
import com.azure.storage.*;
import com.azure.storage.blob.*;
import com.azure.identity.*;

public class HelloWorld {
    public static void main(String[] args) {
      LocalTime currentTime = new LocalTime();
		  System.out.println("The current local time is: " + currentTime);

        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());

        String accountName  = "grabdemsftshardingaudit0";
        String endpoint=String.format("https://%s.blob.core.windows.net",accountName);
        
        System.out.println(endpoint);
        BlobServiceClient blobStorageClient = new BlobServiceClientBuilder()
            .endpoint(endpoint)
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();
        blobStorageClient.getAccountInfo();

    }
}
