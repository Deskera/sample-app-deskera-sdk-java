package com.deskera.developer.sampleproject.constants;

public class Constants {
    public final static String DESKERA_CONNECT_URL_PARAMS
            = "?client_id={client_id}&scope={scope}&state={state}&response_type=code&redirect_uri={redirect_uri}";
    public final static String DESKERA_DEFAULT_SCOPE = "write";

    public final static String PLACEHOLDER_SCOPE = "{scope}";
    public final static String PLACEHOLDER_STATE = "{state}";
    public final static String PLACEHOLDER_CLIENT_ID = "{client_id}";
    public final static String PLACEHOLDER_REDIRECT_URI = "{redirect_uri}";

    public static final String CONNECT_API = "/connect";
    public static final String CALLBACK_API = "/oauth2redirect";

    public static final String PROCESS_CALLBACK = "Process Callback from Deskera";
    public static final String PROCESS_CALLBACK_SUCCESS_RESPONSE = "Callback has been processed successfully";
    public static final String PROCESS_CALLBACK_FAILURE_RESPONSE = "Error in processing callback";
}