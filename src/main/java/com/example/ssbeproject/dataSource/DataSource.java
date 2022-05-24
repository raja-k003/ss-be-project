package com.example.ssbeproject.dataSource;

import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Configuration
@AutoConfigureAfter(MongoAutoConfiguration.class)
public class DataSource {
    @Autowired
    private MongoProperties mongoProperties;


    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(mongoProperties.getUri());
    }

    private Morphia morphia() {

        final Morphia morphia = new Morphia();
        return morphia;
    }

    @Bean(name = "ssproject")
    public Datastore sampleDBSource(MongoClient mongoClient) {

        final Datastore datastore = morphia().createDatastore(mongoClient, "ssproject");
        System.out.println("SampleDB source created!");
        return datastore;
    }

    @Bean
    public Boolean disableSSLValidation() throws Exception {

        final SSLContext sslContext = SSLContext.getInstance("SSL");

        sslContext.init(null, new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                System.out.println("checkClientTrusted");
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                System.out.println("checkServerTrusted");
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }}, null);

        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                System.out.println("verify host: {} " + hostname);
                return true;
            }
        });
        return true;
    }
}
