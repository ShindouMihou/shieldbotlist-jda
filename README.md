[![](https://jitpack.io/v/ShindouMihou/shieldbotlist-jda.svg)](https://jitpack.io/#ShindouMihou/shieldbotlist-jda)
# Shield Bot List API (for Java)
This is an unofficial API built for Java users, feel free to improve it as much as you like.
It will be maintained by me for the meantime, but I will be accepting pull requests if ever there is a need for one.
- This version is for JDA, you can find the other versions below.
- Javacord: https://github.com/ShindouMihou/shieldbotlist-javacord
- Discord4j: https://github.com/ShindouMihou/shieldbotlist-discord4j

### How to install?

Follow the instructions provided on:
https://jitpack.io/#ShindouMihou/shieldbotlist-jda/v1.0.2

#### Maven

1.) Add the jitpack.io repository.

```
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
  
2.) Add the dependency.

```
	<dependency>
	    <groupId>com.github.ShindouMihou</groupId>
	    <artifactId>shieldbotlist-jda</artifactId>
	    <version>v1.0.2</version>
	</dependency>
```

#### Gradle

1.) Add it in your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
  
2.) Add the dependency

```
	dependencies {
	        implementation 'com.github.ShindouMihou:shieldbotlist-jda:v1.0.2'
	}
```
  
### How to use:

The API is easy to use, only requiring your client (event.getApi), your bot ID (can be found on https://discordapp.com/developers) and your authorization token (can be found on Edit Bot page on https://shieldbotlist.tk), for example:

```
public void updateSBL(JDA api, String token, long botId){
ShieldBotApi api = new ShieldBotApi.Builder().token(token).botId(botId).build();
api.setServerCount(api);
}
```
You can also store the object into a values class like how I do, and use it on a Timer (please note, rate limit for the API is one every 30 seconds), here's an example of my method of using this. (I recommend you use your own methods though, test around and see what you like the most.)

#### Values.class:
```
public class Values {
    private static ShieldBotApi sbh = null;

    public static ShieldBotApi getSbh() {
        return sbh;
    }
    
    
    public static void setApi(ShieldBotApi api) {
    Values.sbh = api; 
    }
```

#### Main.class:
```
public void updateSBL(JDA api, long token, String botId){
ShieldBotApi api = new ShieldBotApi.Builder().token(token).botId(botId).build();
Values.setApi(api);

/* You can also store this timer somewhere, or just directly use it. **/
Timer timer = updateSbl(api);
}

// Create a Timer that will set the server count periodically.
private static Timer updateSbl(JDA bot) {
        Timer timer = new Timer();
        TimerTask task = new SBLTask(bot);
	
	// 60 * 15 is equal to 15 minutes.
        timer.schedule(task, 0, 1000 * 60 * 15);
        return timer;
}
```
#### SBLTask.class:
```
public class SBLTask extends TimerTask{
    
    DiscordApi bot;

    public SBLTask(JDA bot){ this.bot = bot; }
    
    @Override
    public void run() {
    /* Checks if SBH is initialized **/
        if(Values.getSbh() != null){
            sbh.setServerCount(bot);
        }
    }
    
}
```

### Java Documentations
For now, you can find the javadocs on my website (https://docs.paradoxium.tk).
