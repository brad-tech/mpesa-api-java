package brad.tech.api.safaricom.daraja.v1.c2b;

import brad.tech.api.safaricom.daraja.MPesaAPIClientBase;
import brad.tech.api.safaricom.daraja.v1.MPesaStandardResponse;
import org.apache.http.client.methods.HttpPost;

import java.io.IOException;
import java.util.HashMap;

public abstract class C2BAPIClientBase extends MPesaAPIClientBase {

    /**
     * This cascades the url to the base class.
     *
     * @param url the C2B API url
     */
    public C2BAPIClientBase(String url) {
        super(url);
    }

    protected MPesaStandardResponse executeC2BRequest(HttpPost httpPost) {
        MPesaStandardResponse response = null;

        try {
            HashMap<String, Object> map = getJsonPayload(httpPost).getJsonMap();
            if (map != null) {
                String conversationID = (String) map.get("ConversationID");
                String originatorConversationID = (String) map.get("OriginatorConversationID");
                String responseDesc = (String) map.get("ResponseDescription");

                response = new MPesaStandardResponse(conversationID, originatorConversationID, responseDesc);
            }
        } catch (IOException ex) {
            this.errorHandler.handleException("Error executing C2B Request", ex);
        }

        return response;
    }
}
