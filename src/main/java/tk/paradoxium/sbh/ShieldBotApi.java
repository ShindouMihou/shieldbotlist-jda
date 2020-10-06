package tk.paradoxium.sbh;

public interface ShieldBotApi {

    void setServerCount(int serverCount);

    class Builder {

        /* Required **/
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
            if (token == null || token == "")
                throw new IllegalArgumentException("Token is null, please fetch one from shieldbotlist.tk");
            if (botId == null)
                throw new IllegalArgumentException("The bot ID is null, please fetch one from discordapp.com/developers");

            return new ShieldBotApiImpl(token, botId);
        }
    }

}
