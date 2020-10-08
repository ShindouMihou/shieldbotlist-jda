package tk.paradoxium.sbh.impl;

import net.dv8tion.jda.api.JDA;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import tk.paradoxium.sbh.ShieldBotApi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShieldBotApiImpl implements ShieldBotApi {

    /*
    The one that handles everything.
    It contains all the basic settings, such as token, botId and DiscordApi.
    If fallen in the wrong hands, can be used to request information that breaks privacy laws.
    Please do not touch if unauthorized to, do not redistribute without permission and etc.
    We are not responsible for any unauthorized distributions of this.
    - Shindou Mihou.
     */

    // Required variables.
    private final HttpUrl url;
    private final OkHttpClient client;

    /**
     * Used to create the Shield Bot API class.
     * @param token The token you receive from ShieldBotList.tk (not from Discord).
     * @param botId Your bot client ID, can be found on developer portal on Discord (discordapp.com/developers).
     */
    public ShieldBotApiImpl(String token, Long botId) {
        this.url = new HttpUrl.Builder()
                .scheme("https")
                .host("shieldbotlist.tk")
                .addPathSegment("api")
                .addPathSegment("auth")
                .addPathSegment("stats")
                .addPathSegment(botId.toString())
                .build();
        this.client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder().addHeader("authorization", token).build();
                    return chain.proceed(request);
                }).build();
    }

    /**
     * Sets the server count for the bot, this will automatically get the server size from JDA.
     * For security reasons, the API will automatically fetch the server count for you (api.getServers().size()).
     * Please use after a minimum of 30 seconds per request, otherwise it will return an exception.
     * @param api The Discord API.
     */
    public void setServerCount(JDA api){
        if(api == null)
            throw new IllegalArgumentException("API is null, are you sure you are using the proper library?");
        JSONObject object = new JSONObject().put("server_count", api.getGuilds().size());
        send(object);
    }

    /**
     * Sends to ShieldBotList the entire request.
     * @param body The body.
     */
    private void send(JSONObject body){
        MediaType JSON = MediaType.parse("application/json");
        RequestBody x = RequestBody.create(body.toString(), JSON);
        Request request = new Request.Builder()
                .post(x).url(url).build();

        Call caller = client.newCall(request);
        caller.enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Logger.getLogger(ShieldBotApiImpl.class.getName()).log(Level.SEVERE, "Exception occurred, this could be caused by server being offline, cancellation, a connectivity problem or timeout. " +
                        "It is possible that the remote server accepted the request before the failure.", e);
            }


            /**
             * The last final safe if everything else fails.
             * @param call The caller variable.
             * @param response The response variable.
             */
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                try {
                    if(!response.isSuccessful()){
                        /* I don't know what to do here, leave it there for now **/
                        Logger.getLogger(ShieldBotApiImpl.class.getName()).log(Level.SEVERE, "Exception occurred, this could be caused by server being offline, cancellation, a connectivity problem or timeout. " +
                                "It is possible that the remote server accepted the request before the failure.");
                    }
                } catch (Exception e){
                    Logger.getLogger(ShieldBotApiImpl.class.getName()).log(Level.SEVERE, "Exception occurred", e);
                } finally {
                    response.body().close();
                }
            }
        });
    }

}
