package com.example.avywhale.blockchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SmartContractHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartContractHandler.class);

    private final SolanaClient solanaClient;

    public SmartContractHandler(SolanaClient solanaClient) {
        this.solanaClient = solanaClient;
    }


    public String deploySmartContract(byte[] compiledCode, String deployerPrivateKey) {
        LOGGER.info("Deploying smart contract with deployer private key: {}", deployerPrivateKey);
        try {
                        String contractAddress = solanaClient.deploySmartContract(deployerPrivateKey, compiledCode);
            LOGGER.info("Smart contract deployed at address: {}", contractAddress);
            return contractAddress;
        } catch (Exception e) {
            LOGGER.error("Error during smart contract deployment: ", e);
            throw new RuntimeException("Smart contract deployment failed", e);
        }
    }


    public String invokeSmartContract(String contractAddress, String methodName, Object[] parameters, String invokerPrivateKey) {
        LOGGER.info("Invoking method {} on smart contract {} with parameters {}", methodName, contractAddress, parameters);
        try {
                        String[] stringParameters = Arrays.stream(parameters)
                    .map(String::valueOf)
                    .toArray(String[]::new);

            String result = solanaClient.invokeSmartContract(contractAddress, methodName, stringParameters);
            LOGGER.info("Smart contract method invocation result: {}", result);
            return result;
        } catch (Exception e) {
            LOGGER.error("Error during smart contract invocation: ", e);
            throw new RuntimeException("Smart contract invocation failed", e);
        }
    }


    public String getSmartContractState(String contractAddress) {
        LOGGER.info("Fetching state for smart contract: {}", contractAddress);
        try {
                        String state = solanaClient.getSmartContractState(contractAddress);
            LOGGER.info("Smart contract state: {}", state);
            return state;
        } catch (Exception e) {
            LOGGER.error("Error while fetching smart contract state: ", e);
            throw new RuntimeException("Failed to fetch smart contract state", e);
        }
    }
}
