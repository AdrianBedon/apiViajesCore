package com.arbc.development.mvc.services;

import org.springframework.stereotype.Service;

import com.google.cloud.kms.v1.CryptoKeyName;
import com.google.cloud.kms.v1.DecryptResponse;
import com.google.cloud.kms.v1.EncryptResponse;
import com.google.cloud.kms.v1.KeyManagementServiceClient;
import com.google.protobuf.ByteString;

@Service
public class KMSService {

    private final KeyManagementServiceClient client;
    private final String projectId = "kmsseguridad";
    private final String locationId = "southamerica-west1";
    private final String keyRingId = "keys_seguridad";
    private final String keyId = "key_seguridad";

    public KMSService() throws Exception {
        this.client = KeyManagementServiceClient.create();
    }

    public byte[] encrypt(String plaintext) throws Exception {
        CryptoKeyName keyName = CryptoKeyName.of(projectId, locationId, keyRingId, keyId);
        ByteString plaintexByteString = ByteString.copyFromUtf8(plaintext);
        EncryptResponse response = client.encrypt(keyName, plaintexByteString);
        return response.getCiphertext().toByteArray();
    }

    public String decrypt(byte[] ciphertext) throws Exception {
        CryptoKeyName keyName = CryptoKeyName.of(projectId, locationId, keyRingId, keyId);
        ByteString ciphertextByteString = ByteString.copyFrom(ciphertext);
        DecryptResponse response = client.decrypt(keyName, ciphertextByteString);
        return response.getPlaintext().toStringUtf8();
    }

}
