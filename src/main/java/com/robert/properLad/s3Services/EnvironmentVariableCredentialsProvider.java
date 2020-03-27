package com.robert.properLad.s3Services;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;

import static com.amazonaws.SDKGlobalConfiguration.ACCESS_KEY_ENV_VAR;
import static com.amazonaws.SDKGlobalConfiguration.ALTERNATE_ACCESS_KEY_ENV_VAR;
import static com.amazonaws.SDKGlobalConfiguration.ALTERNATE_SECRET_KEY_ENV_VAR;
import static com.amazonaws.SDKGlobalConfiguration.AWS_SESSION_TOKEN_ENV_VAR;
import static com.amazonaws.SDKGlobalConfiguration.SECRET_KEY_ENV_VAR;

import com.amazonaws.SdkClientException;
import com.amazonaws.util.StringUtils;

public class EnvironmentVariableCredentialsProvider implements AWSCredentialsProvider {
	@Override
	public AWSCredentials getCredentials() {
        String accessKey = System.getenv(ACCESS_KEY_ENV_VAR);
        if (accessKey == null) {
            accessKey = System.getenv(ALTERNATE_ACCESS_KEY_ENV_VAR);
        }

        String secretKey = System.getenv(SECRET_KEY_ENV_VAR);
        if (secretKey == null) {
            secretKey = System.getenv(ALTERNATE_SECRET_KEY_ENV_VAR);
        }

        accessKey = StringUtils.trim(accessKey);
        secretKey = StringUtils.trim(secretKey);
        String sessionToken = StringUtils.trim(System.getenv(AWS_SESSION_TOKEN_ENV_VAR));

        if (StringUtils.isNullOrEmpty(accessKey) || StringUtils.isNullOrEmpty(secretKey)) {

            throw new SdkClientException(
                    "Unable to load AWS credentials from environment variables " +
                    "(" + ACCESS_KEY_ENV_VAR + " (or " + ALTERNATE_ACCESS_KEY_ENV_VAR + ") and " +
                    SECRET_KEY_ENV_VAR + " (or " + ALTERNATE_SECRET_KEY_ENV_VAR + "))");
        }

        return sessionToken == null ?
                new BasicAWSCredentials(accessKey, secretKey)
                :
                new BasicSessionCredentials(accessKey, secretKey, sessionToken);
    }

    @Override
    public void refresh() {
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
