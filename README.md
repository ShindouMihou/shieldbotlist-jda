[![](https://jitpack.io/v/ShindouMihou/shieldbotlist.svg)](https://jitpack.io/#ShindouMihou/shieldbotlist) [![Codacy Badge](https://app.codacy.com/project/badge/Grade/2dcc194334cf4777908449549afac20c)](https://www.codacy.com/gh/ShindouMihou/shieldbotlist/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ShindouMihou/shieldbotlist&amp;utm_campaign=Badge_Grade)
# Shield Bot List API (for Java)
This is an unofficial API built for Java users, feel free to improve it as much as you like.
It will be maintained by me for the meantime, but I will be accepting pull requests if ever there is a need for one.

### How to install?

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
	    <artifactId>shieldbotlist</artifactId>
	    <version>v1.0</version>
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
	        implementation 'com.github.ShindouMihou:shieldbotlist:v1.0'
	}
```
  
### How to use:

```
ShieldBotApi sbh = new ShieldBotApi("token", clientID);
sbh.setServerCount(int)
```

Replace int with the server count, choose how you like, for example in Javacord:

```
public void setupSBH(DiscordApi bot, String token, long clientID) {
    ShieldBotApi sbh = new ShieldBotApi("token", clientID);
    sbh.setServerCount(bot.getServers().size());
    }
```
