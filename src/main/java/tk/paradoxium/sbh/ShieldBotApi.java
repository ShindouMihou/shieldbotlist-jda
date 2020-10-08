package tk.paradoxium.sbh;

import discord4j.core.DiscordClient;
import tk.paradoxium.sbh.impl.ShieldBotApiImpl;

public interface ShieldBotApi {

    /**
     * Sets the server count for the bot, this will automatically get the server size from Discord4j.
     * For security reasons, the API will automatically fetch the server count for you (api.getServers().size()).
     * Please use after a minimum of 30 seconds per request, otherwise it will return an exception.
     * Documentations can be found https://docs.paradoxium.tk
     * @param api The Discord API.
     */
    void setServerCount(DiscordClient api);

    class Builder {

        /*
        Please do not make any unnecessary modifications to these variables.
        They are null for a reason.
         */
        private Long botId = null;
        private String token = null;

        /**
         * Sets the token for the API, please use one from shieldbotlist.tk, not from Discord itself.
         * @param token The token from shieldbotlist.tk.
         * @return Builder
         */
        public Builder token(String token) {
            this.token = token;
            return this;
        }

        /**
         * Sets the bot ID for the API, please use the one from discordapp.com/developers.
         * @param botId The bot ID from discordapp.com/developers.
         * @return Builder
         */
        public Builder botId(String botId) {
            this.botId = Long.parseLong(botId);
            return this;
        }

        /**
         * Sets the bot ID for the API, please use the one from discordapp.com/developers.
         * @param botId The bot ID from discordapp.com/developers.
         * @return Builder
         */
        public Builder botId(long botId) {
            this.botId = botId;
            return this;
        }

        /**
         * Builds the method.
         * @return ShieldBotAPI
         */
        public ShieldBotApi build() {
            if (token == null || token.equals(""))
                throw new IllegalArgumentException("Token is null, please fetch one from shieldbotlist.tk");
            if (botId == null)
                throw new IllegalArgumentException("The bot ID is null, please fetch one from discordapp.com/developers");

            return new ShieldBotApiImpl(token, botId);
        }
    }

}
